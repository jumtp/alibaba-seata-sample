package com.example.goods.constant;



import java.util.Objects;
import java.util.stream.Stream;

public enum GoodsStatus {

    ONLINE(101, "上线"),
    OFFLINE(102, "下线"),
    STOCK_OUT(103, "缺货"),
    ;

    public static GoodsStatus of(Integer status) {
        Objects.requireNonNull(status);
        return Stream.of(values())
                .filter(bean -> bean.status.equals(status))
                .findAny()
                .orElseThrow(() -> new RuntimeException("未知产品状态码"));
    }


    private final Integer status;
    private final String description;

    GoodsStatus(Integer status, String description) {
        this.status = status;
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
}
