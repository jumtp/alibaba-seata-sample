package com.example.goods.controller;

import com.example.common.TableId;
import com.example.goods.DeductGoodsInventory;
import com.example.goods.SimpleGoodsInfo;
import com.example.goods.service.IGoodsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    private final IGoodsService goodsService;

    public GoodsController(IGoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @PostMapping("/simple-goods-info")
    public List<SimpleGoodsInfo> getSimpleGoodsInfoByTableId(@RequestBody TableId tableId) {
        return goodsService.getSimpleGoodsInfoByTableId(tableId);
    }

    @PostMapping("/deduct-inventory")
    public Boolean deductInventory(@RequestBody List<DeductGoodsInventory> deductGoodsInventories) {
        return goodsService.deductGoodsInventory(deductGoodsInventories);
    }
}
