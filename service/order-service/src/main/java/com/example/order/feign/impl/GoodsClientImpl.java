package com.example.order.feign.impl;

import com.example.common.TableId;
import com.example.goods.DeductGoodsInventory;
import com.example.goods.SimpleGoodsInfo;
import com.example.order.feign.GoodsClient;
import com.example.order.utils.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@Slf4j
public class GoodsClientImpl implements FallbackFactory<GoodsClient> {

    private final JsonMapper jsonMapper;

    public GoodsClientImpl(JsonMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }


    @Override
    public GoodsClient create(Throwable cause) {
        return new GoodsClient() {
            @Override
            public List<SimpleGoodsInfo> getSimpleGoodsInfoByTableId(TableId tableId) {
                return Collections.emptyList();
            }

            @Override
            public Boolean deductGoodsInventory(List<DeductGoodsInventory> deductGoodsInventories) {
                String logMsg = "goods client feign request error in order service] deductGoodsInventory error";
                log.error("[{}}:[{}]", logMsg, jsonMapper.toString(deductGoodsInventories));
                return false;
            }
        };
    }
}
