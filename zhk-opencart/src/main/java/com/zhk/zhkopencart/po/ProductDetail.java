package com.zhk.zhkopencart.po;

import lombok.Data;
import org.omg.CORBA.INTERNAL;

import java.util.List;

@Data
public class ProductDetail {
    private Integer productId;

    private String description;

    private List<String> otherPicUrls;
}
