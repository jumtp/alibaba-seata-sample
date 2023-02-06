package com.example.goods.bo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.goods.GoodsInfo;
import com.example.goods.SimpleGoodsInfo;
import com.example.goods.constant.GoodsStatus;
import com.example.goods.converter.GoodsStatusConverter;
import lombok.*;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.JdbcType;

import java.time.LocalDateTime;


@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(autoResultMap = true)
public class Goods {
    private Long id;
    private Long category_id;
    private String name;
    private String description;
    private String pic;
    private Integer price;
    private Integer salesQuantity;
    private Integer inventory;
    @TableField(jdbcType = JdbcType.INTEGER, typeHandler = GoodsStatusConverter.class)
    private GoodsStatus status;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    public static Goods to(GoodsInfo goodsInfo) {
        return new Goods()
                .setCategory_id(goodsInfo.getCategory_id())
                .setName(goodsInfo.getName())
                .setDescription(goodsInfo.getDescription())
                .setPic(goodsInfo.getPic())
                .setPrice(goodsInfo.getPrice())
                .setSalesQuantity(goodsInfo.getSalesQuantity())
                .setInventory(goodsInfo.getInventory())
                .setStatus(GoodsStatus.OFFLINE);//验证?
    }
    public SimpleGoodsInfo simpleGoodsInfo() {
        return new SimpleGoodsInfo()
                .setId(this.getId())
                .setName(this.getName())
                .setPic(this.getPic())
                .setPrice(this.getPrice());
    }
}
