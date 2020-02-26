package com.zhk.zhkopencart.dto.in;

import lombok.Data;

@Data
public class ProductCreateDTO {

    private String productName;

    private Double price;

    private Integer quantity;

    private Integer status;

    private String imageUrl;

    private Double discount;

    private Integer rewordPoints;

    private ProductDetail productDetail;

}
