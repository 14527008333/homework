package com.zhk.zhkopencart.dto.out;

import lombok.Data;

@Data
public class ProductLsitDTO {

    private Integer productId;

    private String productName;

    private Double price;

    private Integer quantity;

    private Integer status;

    private String mainImageUrl;

}
