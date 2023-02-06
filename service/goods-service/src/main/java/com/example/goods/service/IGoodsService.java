package com.example.goods.service;

import com.example.common.TableId;
import com.example.goods.DeductGoodsInventory;
import com.example.goods.SimpleGoodsInfo;

import java.util.List;

public interface IGoodsService {

    List<SimpleGoodsInfo> getSimpleGoodsInfoByTableId(TableId tableId);

    Boolean deductGoodsInventory(List<DeductGoodsInventory> deductGoodsInventories);

}
