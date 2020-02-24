package com.zhk.zhkopencart.controller;

import com.zhk.zhkopencart.dto.in.AdministratorUpdateDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("image")
public class ImageController {

    @PostMapping("upload")
    public String upload(@RequestPart(required = false) MultipartFile multipartFile){

        return null;
    }
}
