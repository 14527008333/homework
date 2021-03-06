package com.zhk.zhkopencartstore.dto.out;

import lombok.Data;

import java.util.List;

@Data
public class ProductShowDTO {

    private Integer productId;

    private String productName;

    private String productCode;

    private Double price;

    private String productAbstract;

    private Integer quantity;

    private Integer status;

    private String imageUrl;

    private String description;

    private Double discount;

    private Integer rewordPoints;

    private String otherImageJson;

    private List<String> otherImageUrls;
}
