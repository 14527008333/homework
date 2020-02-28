package com.zhk.zhkopencart.dto.in;

import lombok.Data;

import java.util.List;

@Data
public class ProductUpdateDTO{
    private Integer productId;

    private String productName;

    private String productCode;

    private Double price;

    private Integer quantity;

    private Byte status;

    private String imageUrl;

    private Double discount;

    private String description;

    private Integer rewordPoints;

    private List<String> otherImageUrls;

}
