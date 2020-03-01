package com.zhk.zhkopencart.service.imp;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhk.zhkopencart.dao.ProductDetailMapper;
import com.zhk.zhkopencart.dao.ProductMapper;
import com.zhk.zhkopencart.dto.in.ProductCreateDTO;
import com.zhk.zhkopencart.dto.in.ProductUpdateDTO;
import com.zhk.zhkopencart.dto.out.PageDTO;
import com.zhk.zhkopencart.dto.out.ProductLsitDTO;
import com.zhk.zhkopencart.dto.out.ProductShowDTO;
import com.zhk.zhkopencart.po.Product;
import com.zhk.zhkopencart.po.ProductDetail;
import com.zhk.zhkopencart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductDetailMapper productDetailMapper;

    @Override
    public Page<ProductLsitDTO> getqueryList(String productName, Double price, Integer quantity, Integer status, Integer pageNum) {
        PageHelper.startPage(pageNum,3);
        Page<ProductLsitDTO> productLsitDTOPage= productMapper.getqueryList(productName,price,quantity,status);
        return productLsitDTOPage;
    }

    @Override
    public Integer createProduct(ProductCreateDTO productCreateDTO) {
        Product product = new Product();
        product.setProductCode(productCreateDTO.getProductCode());
        product.setProductName(productCreateDTO.getProductName());
        product.setProductAbstract(productCreateDTO.getProductAbstract());
        product.setPrice(productCreateDTO.getPrice());
        product.setDiscount(productCreateDTO.getDiscount());
        product.setStockQuantity(productCreateDTO.getQuantity());
        product.setStatus(productCreateDTO.getStatus());
        product.setMainPicUrl(productCreateDTO.getImageUrl());
        product.setRewordPoints(productCreateDTO.getRewordPoints());
        int i = productMapper.insert(product);

        ProductDetail productDetail = new ProductDetail();

        productDetail.setProductId(product.getProductId());
        productDetail.setDescription(productCreateDTO.getDescription());
        productDetail.setOtherPicUrls(JSON.toJSONString(productCreateDTO.getOtherImageUrls()));
        int i1 = productDetailMapper.insert(productDetail);

        return product.getProductId();
    }

    @Override
    public void updateProdcut(ProductUpdateDTO productUpdateDTO) {
        Product product = new Product();
        product.setProductId(productUpdateDTO.getProductId());
        product.setProductCode(productUpdateDTO.getProductCode());
        product.setProductName(productUpdateDTO.getProductName());
        product.setProductAbstract(productUpdateDTO.getProductAbstract());
        product.setPrice(productUpdateDTO.getPrice());
        product.setDiscount(productUpdateDTO.getDiscount());
        product.setStockQuantity(productUpdateDTO.getQuantity());
        product.setStatus(productUpdateDTO.getStatus());
        product.setMainPicUrl(productUpdateDTO.getImageUrl());
        product.setRewordPoints(productUpdateDTO.getRewordPoints());
        int productId = productMapper.updateByPrimaryKeySelective(product);

        ProductDetail productDetail = new ProductDetail();

        productDetail.setProductId(product.getProductId());
        productDetail.setDescription(productUpdateDTO.getDescription());
        productDetail.setOtherPicUrls(JSON.toJSONString(productUpdateDTO.getOtherImageUrls()));
        int productDetailId = productDetailMapper.updateByPrimaryKeySelective(productDetail);
    }

    @Override
    public ProductShowDTO getProductAndProductDetailById(String productId) {

        ProductShowDTO productShowDTO = productMapper.getProductAndProductDetailById(productId);
        String otherImageJson = productShowDTO.getOtherImageJson();
        List<String> strings = JSON.parseArray(otherImageJson, String.class);
        productShowDTO.setOtherImageJson(null);
        productShowDTO.setOtherImageUrls(strings);
        return productShowDTO;
    }
}
