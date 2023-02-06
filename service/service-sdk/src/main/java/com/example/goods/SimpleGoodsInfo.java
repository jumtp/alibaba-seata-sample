package com.example.goods;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class SimpleGoodsInfo {
    private Long id;
    private String name;
    private String pic;
    private Integer price;
}
