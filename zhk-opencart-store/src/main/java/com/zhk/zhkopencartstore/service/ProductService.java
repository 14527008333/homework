package com.zhk.zhkopencartstore.service;

import com.github.pagehelper.Page;
import com.zhk.zhkopencartstore.dto.out.ProductLsitDTO;
import com.zhk.zhkopencartstore.dto.out.ProductShowDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductService {
    Page<ProductLsitDTO> getqueryList(String productName,Double price,Integer quantity,Integer pageNum);

    ProductShowDTO getProductAndProductDetailById(Integer productId);
}
