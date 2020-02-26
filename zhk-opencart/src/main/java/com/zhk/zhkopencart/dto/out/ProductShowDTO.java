package com.zhk.zhkopencart.dto.out;

import lombok.Data;

@Data
public class ProductShowDTO {

    private Integer productId;

    private String productName;

    private Double price;

    private Integer quantity;

    private Integer status;

    private String imageUrl;

    private Double discount;

    private Integer rewordPoints;

    private ProductDetail productDetail;
}
