package com.zhk.zhkopencart.controller;

import com.github.pagehelper.Page;
import com.zhk.zhkopencart.dto.in.ProductCreateDTO;
import com.zhk.zhkopencart.dto.in.ProductUpdateDTO;
import com.zhk.zhkopencart.dto.out.PageDTO;
import com.zhk.zhkopencart.dto.out.ProductLsitDTO;
import com.zhk.zhkopencart.dto.out.ProductShowDTO;
import com.zhk.zhkopencart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
@CrossOrigin
public class ProductContorller {

    @Autowired
    private ProductService productService;


    @GetMapping("list")
    public PageDTO<ProductLsitDTO> getUserList(@RequestParam(required = false) String productName,
                                               @RequestParam(required = false) Double price,
                                               @RequestParam(required = false)Integer quantity,
                                               @RequestParam(required = false)Integer status,
                                               @RequestParam(required = false,defaultValue = "1")Integer pageNum){

         Page<ProductLsitDTO> pageLsit =productService.getqueryList(productName,price,quantity,status,pageNum);
        PageDTO<ProductLsitDTO> productLsitDTOPageDTO = new PageDTO<>();

        productLsitDTOPageDTO.setPageNum(pageLsit.getPageNum());
        productLsitDTOPageDTO.setPageSize(pageLsit.getPageSize());
        productLsitDTOPageDTO.setTotal(pageLsit.getTotal());
        productLsitDTOPageDTO.setList(pageLsit);
        System.out.println(productLsitDTOPageDTO);
        return productLsitDTOPageDTO;
    }

    @PostMapping("create")
    @Transactional
    public Integer create(@RequestBody ProductCreateDTO productCreateDTO){

       Integer id= productService.createProduct(productCreateDTO);
        return id;
    }

    @PostMapping("update")
    @Transactional
    public void update(@RequestBody ProductUpdateDTO productUpdateDTO){
        productService.updateProdcut(productUpdateDTO);
    }

    @GetMapping("show")
    public ProductShowDTO show(@RequestParam(required = false) String productId){
        ProductShowDTO productShowDTO= productService.getProductAndProductDetailById(productId);
        return productShowDTO;
    }







}

