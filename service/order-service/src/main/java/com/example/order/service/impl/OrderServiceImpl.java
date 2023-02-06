package com.example.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.account.BalanceInfo;
import com.example.common.TableId;
import com.example.filter.AccessContext;
import com.example.goods.SimpleGoodsInfo;
import com.example.order.OrderInfo;
import com.example.order.entity.Order;
import com.example.order.feign.BalanceClient;
import com.example.order.feign.GoodsClient;
import com.example.order.mapper.OrderMapper;
import com.example.order.service.IOrderService;
import com.example.vo.LoginUserInfo;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;


@Slf4j
@Service
public class OrderServiceImpl implements IOrderService {

    private final OrderMapper orderMapper;
    private final GoodsClient goodsClient;
    private final BalanceClient balanceClient;

    public OrderServiceImpl(OrderMapper orderMapper, GoodsClient goodsClient, BalanceClient balanceClient) {
        this.orderMapper = orderMapper;
        this.goodsClient = goodsClient;
        this.balanceClient = balanceClient;
    }


    @Override
    @Transactional
    @GlobalTransactional(rollbackFor = Exception.class)
    public TableId createOrder(OrderInfo orderInfo) {
        log.info("order service impl XID : [{}]",RootContext.getXID());
        LoginUserInfo loginUserInfo = AccessContext.getLoginUserInfo();
        Order order = new Order().setUserId(loginUserInfo.id())
                .setAddressId(1L)
                .setOrderDetail(JSON.toJSONString(orderInfo.getOrderItems()));

        orderMapper.insert(order);
        log.info("create order success: [{}],[{}]", loginUserInfo.id(), order);
        Boolean isDeductGoodsInventorySuccess = goodsClient.deductGoodsInventory(orderInfo.getOrderItems().stream().map(OrderInfo.OrderItem::toDeductGoodsInventory)
                .toList());

        List<SimpleGoodsInfo> simpleGoodsInfos = goodsClient.getSimpleGoodsInfoByTableId(new TableId(orderInfo.getOrderItems().stream().map(
                orderItem -> new TableId.Id(orderItem.getGoodsId())
        ).toList()));

        if (CollectionUtils.isEmpty(simpleGoodsInfos)) {
            throw new RuntimeException("商品不存在, 无商品id");
        }

        Map<Long, SimpleGoodsInfo> goodsInfoMap = simpleGoodsInfos.stream().collect(Collectors.toMap(SimpleGoodsInfo::getId, Function.identity()));
        long balance = 0;
        for (OrderInfo.OrderItem orderItem : orderInfo.getOrderItems()) {
            balance += (long) orderItem.getCount() * goodsInfoMap.get(orderItem.getGoodsId()).getPrice();
        }

        BalanceInfo balanceInfo = balanceClient.deductBalance(new BalanceInfo(loginUserInfo.id(), balance));
        if (Objects.isNull(balanceInfo)) {
            throw  new RuntimeException("扣减金额失败");
        }

        if (!isDeductGoodsInventorySuccess) {
            throw new RuntimeException("商品库存不足");
        }

        return new TableId(Collections.singletonList(new TableId.Id(order.getId())));
    }
}
