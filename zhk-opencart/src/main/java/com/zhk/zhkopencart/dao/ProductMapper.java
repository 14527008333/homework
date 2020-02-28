package com.zhk.zhkopencart.dao;

import com.github.pagehelper.Page;
import com.zhk.zhkopencart.dto.out.ProductLsitDTO;
import com.zhk.zhkopencart.dto.out.ProductShowDTO;
import com.zhk.zhkopencart.po.Product;
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

    Page<ProductLsitDTO> getqueryList(@Param("productName") String productName, @Param("price")Double price, @Param("quantity")Integer quantity, @Param("status")Integer status);

    ProductShowDTO getProductAndProductDetailById(@Param("productId")String productId);
}