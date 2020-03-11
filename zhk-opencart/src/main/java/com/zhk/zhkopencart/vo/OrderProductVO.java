package com.zhk.zhkopencart.vo;

import lombok.Data;

@Data
public class OrderProductVO {

    private Integer productId;

    private String productCode;

    private String productName;

    private Integer quantity;

    private Double unitPrice;

    private Double totalPrice;

    private Integer unitRewordPoints;

    private Integer totalRewordPoints;

}
