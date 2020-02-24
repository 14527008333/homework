package com.zhk.zhkopencart.controller;

import com.zhk.zhkopencart.dto.in.AdministratorCreateDTO;
import com.zhk.zhkopencart.dto.in.AdministratorUpdateDTO;
import com.zhk.zhkopencart.dto.out.AdministratorListDTO;
import com.zhk.zhkopencart.dto.out.AdministratorShowDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("administrator")
public class AdministratorController {

    @GetMapping("list")
    public List<AdministratorListDTO> getUserList(@RequestParam(required = false) String userName,
                                                  @RequestParam(required = false) Integer status,
                                                  @RequestParam(required = false)Long createTime,
                                                  @RequestParam(required = false)String realName,
                                                  @RequestParam(required = false,defaultValue = "1")Integer pageNum){

        return null;
    }


    @GetMapping("login")
    public boolean login(@RequestParam(required = false) String userName, @RequestParam(required = false) String password){

        return true;
    }

    @GetMapping("show")
    public AdministratorShowDTO show(@RequestParam(required = false) Integer administratorId){

        return null;
    }

    @PostMapping("create")
    public Integer create(@RequestPart(required = false)AdministratorCreateDTO administratorCreateDTO){

        return null;
    }

    @PostMapping("update")
    public void update(@RequestPart(required = false) AdministratorUpdateDTO administratorUpdateDTO){

    }



}
