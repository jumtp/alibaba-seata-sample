package com.example.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.TableId;
import com.example.goods.DeductGoodsInventory;
import com.example.goods.SimpleGoodsInfo;
import com.example.goods.bo.Goods;
import com.example.goods.mapper.GoodsMapper;
import com.example.goods.service.IGoodsService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    private final GoodsMapper goodsMapper;

    public GoodsServiceImpl(GoodsMapper goodsMapper) {
        this.goodsMapper = goodsMapper;
    }

    @Override
    public List<SimpleGoodsInfo> getSimpleGoodsInfoByTableId(TableId tableId) {
        List<Long> ids = tableId.getIdList().stream().map(TableId.Id::getId).toList();
        List<Goods> goods = goodsMapper.selectBatchIds(ids);
        return goods.stream().map(Goods::simpleGoodsInfo).toList();
    }

    @Override
    @Transactional
    public Boolean deductGoodsInventory(List<DeductGoodsInventory> deductGoodsInventories) {
        log.info("GoodsServiceImpl XID : [{}]", RootContext.getXID());
        deductGoodsInventories.forEach(d -> {
            if (d.getCount() <= 0) {
                throw new RuntimeException("扣减的库存不能小于等于0!");
            }
        });

        List<Goods> goods = goodsMapper.selectBatchIds(
                deductGoodsInventories.stream()
                        .map(DeductGoodsInventory::getGoodsId).toList());
        if (goods == null || goods.isEmpty() || deductGoodsInventories.size() != goods.size()) {
            throw new RuntimeException("请求失败");
        }

        Map<Long, DeductGoodsInventory> inventoryMap = deductGoodsInventories.stream()
                .collect(Collectors.toMap(DeductGoodsInventory::getGoodsId, Function.identity()));
        goods.forEach(g -> {
            Integer inventory = g.getInventory();
            Integer needDeductInventory = inventoryMap.get(g.getId()).getCount();
            if (inventory < needDeductInventory) {
                log.error("商品库存不足,剩余库存:[{}],请求库存:[{}]", inventory, needDeductInventory);
                throw new RuntimeException("库存不足!");
            }
            g.setInventory(inventory - needDeductInventory);
            g.setSalesQuantity(g.getSalesQuantity() + needDeductInventory);
            log.info("减少库存:[{}],剩余库存:[{}]", needDeductInventory, inventory - needDeductInventory);
        });

        super.updateBatchById(goods);
        return true;
    }


}
