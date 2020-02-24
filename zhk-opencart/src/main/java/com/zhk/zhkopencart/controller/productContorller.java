package com.zhk.zhkopencart.controller;

import com.zhk.zhkopencart.dto.in.ProductCreateDTO;
import com.zhk.zhkopencart.dto.in.ProductUpdateDTO;
import com.zhk.zhkopencart.dto.out.ProductLsitDTO;
import com.zhk.zhkopencart.dto.out.ProductShowDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class productContorller {

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

    @PostMapping("create")
    public void create(@RequestPart(required = false) ProductUpdateDTO productUpdateDTO){

    }

    @GetMapping("list")
    public ProductShowDTO getUserList(@RequestParam(required = false) String productId){

        return null;
    }







}

