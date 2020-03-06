package com.zhk.zhkopencartstore.controller;

import com.github.pagehelper.Page;
import com.zhk.zhkopencartstore.dto.out.PageOutDTO;
import com.zhk.zhkopencartstore.dto.out.ProductLsitDTO;
import com.zhk.zhkopencartstore.dto.out.ProductShowDTO;
import com.zhk.zhkopencartstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product")
@CrossOrigin
public class ProductContorller {

    @Autowired
    private ProductService productService;


    @GetMapping("list")
    public PageOutDTO<ProductLsitDTO> getUserList(@RequestParam(required = false,defaultValue = "1")Integer pageNum){

         Page<ProductLsitDTO> pageLsit =productService.getqueryList(pageNum);
        PageOutDTO<ProductLsitDTO> productLsitDTOPageDTO = new PageOutDTO<>();

        productLsitDTOPageDTO.setPageNum(pageLsit.getPageNum());
        productLsitDTOPageDTO.setPageSize(pageLsit.getPageSize());
        productLsitDTOPageDTO.setTotal(pageLsit.getTotal());
        productLsitDTOPageDTO.setList(pageLsit);
        return productLsitDTOPageDTO;
    }


    @GetMapping("show")
    public ProductShowDTO show(@RequestParam Integer productId){
        ProductShowDTO productShowDTO= productService.getProductAndProductDetailById(productId);
        return productShowDTO;
    }







}

