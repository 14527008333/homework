package com.zhk.zhkopencart.dto.in;

import com.zhk.zhkopencart.po.ProductDetail;
import lombok.Data;

import java.util.List;

@Data
public class ProductCreateDTO {

    private String productName;

    private String productCode;

    private String productAbstract;

    private Double price;

    private Integer quantity;

    private Byte status;

    private String imageUrl;

    private Double discount;

    private String description;

    private Integer rewordPoints;

    private List<String> otherImageUrls;

}
