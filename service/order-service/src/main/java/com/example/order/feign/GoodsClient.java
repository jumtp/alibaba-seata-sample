package com.example.order.feign;

import com.example.common.TableId;
import com.example.goods.DeductGoodsInventory;
import com.example.goods.SimpleGoodsInfo;
import com.example.order.feign.impl.GoodsClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(contextId = "GoodsClient", value = "goods-service", fallbackFactory = GoodsClientImpl.class)
public interface GoodsClient {

    @PostMapping(value = "/goods/simple-goods-info")
    List<SimpleGoodsInfo> getSimpleGoodsInfoByTableId(TableId tableId);

    @PostMapping(value = "/goods/deduct-inventory")
    Boolean deductGoodsInventory(List<DeductGoodsInventory> deductGoodsInventories);
}
