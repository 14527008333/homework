package com.zhk.zhkopencart.dto.out;

import com.zhk.zhkopencart.po.ProductDetail;
import lombok.Data;

import java.util.List;

@Data
public class ProductShowDTO {

    private Integer productId;

    private String productName;

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
