package com.zhk.zhkopencartstore.service.Imp;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhk.zhkopencartstore.dao.ProductDetailMapper;
import com.zhk.zhkopencartstore.dao.ProductMapper;
import com.zhk.zhkopencartstore.dto.out.ProductLsitDTO;
import com.zhk.zhkopencartstore.dto.out.ProductShowDTO;
import com.zhk.zhkopencartstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Page<ProductLsitDTO> getqueryList(String productName,Double price,Integer quantity,Integer pageNum) {
        PageHelper.startPage(pageNum,3);
        Page<ProductLsitDTO> productLsitDTOPage= productMapper.getqueryList(productName,price,quantity);
        return productLsitDTOPage;
    }



    @Override
    public ProductShowDTO getProductAndProductDetailById(Integer productId) {

        ProductShowDTO productShowDTO = productMapper.getProductAndProductDetailById(productId);
        String otherImageJson = productShowDTO.getOtherImageJson();
        List<String> strings = JSON.parseArray(otherImageJson, String.class);
        productShowDTO.setOtherImageJson(null);
        productShowDTO.setOtherImageUrls(strings);
        return productShowDTO;
    }
}
