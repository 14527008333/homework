package com.zhk.zhkopencart.controller;

import com.zhk.zhkopencart.dto.in.AdministratorUpdateDTO;
import com.zhk.zhkopencart.util.FileUploadUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("image")
@CrossOrigin
public class ImageController {

    @PostMapping("upload")
    public String upload(HttpServletRequest request, @RequestParam(required = false) MultipartFile multipartFile){
        String load = FileUploadUtil.load(request.getSession(), multipartFile, "/E:/我/git_library/homework/zhk-opencart/src/main/upload/mainUpload");

        return load;
    }
}
