package com.zhk.zhkopencart.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.zhk.zhkopencart.constant.ExceptionConstant;
import com.zhk.zhkopencart.dto.in.AdministratorCreateDTO;
import com.zhk.zhkopencart.dto.in.AdministratorUpdateDTO;
import com.zhk.zhkopencart.dto.out.AdministratorListDTO;
import com.zhk.zhkopencart.dto.out.AdministratorLoginOutDTO;
import com.zhk.zhkopencart.dto.out.AdministratorShowDTO;
import com.zhk.zhkopencart.exception.ClientException;
import com.zhk.zhkopencart.po.Administrator;
import com.zhk.zhkopencart.service.AdministratorServer;
import com.zhk.zhkopencart.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("administrator")
public class AdministratorController {


    @Autowired
    private AdministratorServer administratorServer;

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("list")
    public List<AdministratorListDTO> getUserList(@RequestParam(required = false) String userName,
                                                  @RequestParam(required = false) Integer status,
                                                  @RequestParam(required = false)Long createTime,
                                                  @RequestParam(required = false)String realName,
                                                  @RequestParam(required = false,defaultValue = "1")Integer pageNum){

        return null;
    }


    @GetMapping("login")
    public AdministratorLoginOutDTO login(@RequestParam(required = false) String userName, @RequestParam(required = false) String password) throws ClientException {
        //根据用户名获取用户对象并判断是否为空  为空抛出用户不存在
        Administrator administrator= administratorServer.selectByUsername(userName);
        if(administrator==null){
            throw new ClientException(ExceptionConstant.ADMINISTRATOR_USERNAME_NOT_EXIST_ERRCODE,ExceptionConstant.ADMINISTRATOR_USERNAME_NOT_EXIST_ERRMSG);
        }
        //对密码进行校验
        BCrypt.Result verify = BCrypt.verifyer().verify(userName.toCharArray(), administrator.getEncryptedPassword());

        //校验通过发出Token令牌否则抛出密码错误
       if(verify.verified){
           AdministratorLoginOutDTO administratorLoginOutDTO = jwtUtil.issueToken(administrator);
           return administratorLoginOutDTO;
       }
       else{
            throw new ClientException(ExceptionConstant.ADNINISTRATOR_PASSWORD_INVALID_ERRCODE,ExceptionConstant.ADNINISTRATOR_PASSWORD_INVALID_ERRMSG);
       }
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
