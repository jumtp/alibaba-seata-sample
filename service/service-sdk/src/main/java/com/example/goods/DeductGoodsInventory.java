package com.example.goods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeductGoodsInventory {
    private Long goodsId;
    private Integer count;
}
