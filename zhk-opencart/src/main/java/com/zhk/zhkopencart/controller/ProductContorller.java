package com.zhk.zhkopencart.controller;

import com.zhk.zhkopencart.dto.in.ProductCreateDTO;
import com.zhk.zhkopencart.dto.in.ProductUpdateDTO;
import com.zhk.zhkopencart.dto.out.ProductLsitDTO;
import com.zhk.zhkopencart.dto.out.ProductShowDTO;
import com.zhk.zhkopencart.service.imp.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductContorller {

    @Autowired
    private ProductService productService;


    @GetMapping("list")
    public List<ProductLsitDTO> getUserList(@RequestParam(required = false) String productName,
                                            @RequestParam(required = false) Double price,
                                            @RequestParam(required = false)Integer quantity,
                                            @RequestParam(required = false)Integer status,
                                            @RequestParam(required = false,defaultValue = "1")Integer pageNum){




        return null;
    }

    @PostMapping("create")
    public Integer create(@RequestPart(required = false)ProductCreateDTO productCreateDTO){

        return null;
    }

    @PostMapping("update")
    public void update(@RequestPart(required = false) ProductUpdateDTO productUpdateDTO){

    }

    @GetMapping("show")
    public ProductShowDTO show(@RequestParam(required = false) String productId){

        return null;
    }







}

