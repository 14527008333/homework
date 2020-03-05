package com.zhk.zhkopencartstore.dao;

import com.github.pagehelper.Page;
import com.zhk.zhkopencartstore.dto.out.ProductLsitDTO;
import com.zhk.zhkopencartstore.dto.out.ProductShowDTO;
import com.zhk.zhkopencartstore.po.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMapper {
    int deleteByPrimaryKey(Integer productId);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer productId);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
    Page<ProductLsitDTO> getqueryList();

    ProductShowDTO getProductAndProductDetailById(@Param("productId")Integer productId);
}