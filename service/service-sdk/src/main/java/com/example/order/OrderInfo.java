package com.example.order;

import com.example.goods.DeductGoodsInventory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class OrderInfo {

    private List<OrderItem> orderItems;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class OrderItem {
        private Long goodsId;
        private Integer count;

        public DeductGoodsInventory toDeductGoodsInventory() {
            return new DeductGoodsInventory(this.goodsId, this.count);
        }
    }
}
