package com.zhk.zhkopencart.dao;

import com.zhk.zhkopencart.po.ProductDetail;

public interface ProductDetailMapper {
    int insert(ProductDetail record);

    int insertSelective(ProductDetail record);
}