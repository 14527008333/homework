package com.zhk.zhkopencart.service;

import com.github.pagehelper.Page;
import com.zhk.zhkopencart.dto.in.ProductCreateDTO;
import com.zhk.zhkopencart.dto.in.ProductUpdateDTO;
import com.zhk.zhkopencart.dto.out.ProductLsitDTO;
import com.zhk.zhkopencart.dto.out.ProductShowDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductService {
    Page<ProductLsitDTO> getqueryList(String productName, Double price, Integer quantity, Integer status, Integer pageNum);

    Integer createProduct(ProductCreateDTO productCreateDTO);

    void updateProdcut(ProductUpdateDTO productUpdateDTO);

    ProductShowDTO getProductAndProductDetailById(String productId);
}
