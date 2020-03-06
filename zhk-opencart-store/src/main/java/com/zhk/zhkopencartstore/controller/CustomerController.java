package com.zhk.zhkopencartstore.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.zhk.zhkopencartstore.constant.ExceptionConstant;
import com.zhk.zhkopencartstore.dto.in.CustomerChangerPasswordInDTO;
import com.zhk.zhkopencartstore.dto.in.CustomerRegisterInDTO;
import com.zhk.zhkopencartstore.dto.in.CustomerUpdateInDTO;
import com.zhk.zhkopencartstore.dto.out.CustomerLoginOutDTO;
import com.zhk.zhkopencartstore.dto.out.CustomerShowOutDTO;
import com.zhk.zhkopencartstore.exception.ClientException;
import com.zhk.zhkopencartstore.po.Customer;
import com.zhk.zhkopencartstore.service.CustomerService;
import com.zhk.zhkopencartstore.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer")
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("register")
    public Integer registerCustomer(@RequestBody CustomerRegisterInDTO customerRegisterInDTO){

        return null;
    }

    @GetMapping("login")
    public CustomerLoginOutDTO registerCustomer(@RequestParam String userName, @RequestParam String password) throws ClientException {
       Customer customer= customerService.getCustomerByUserName(userName);
        if(customer==null){
            throw new ClientException(ExceptionConstant.ADMINISTRATOR_USERNAME_NOT_EXIST_ERRCODE,ExceptionConstant.ADMINISTRATOR_USERNAME_NOT_EXIST_ERRMSG);
        }
        //对密码进行校验
        BCrypt.Result verify = BCrypt.verifyer().verify(password.toCharArray(), customer.getEncryptedPassword());

        //校验通过发出Token令牌否则抛出密码错误
        if(verify.verified){
            CustomerLoginOutDTO administratorLoginOutDTO = jwtUtil.issueToken(customer);
            return administratorLoginOutDTO;
        }
        else{
            throw new ClientException(ExceptionConstant.ADNINISTRATOR_PASSWORD_INVALID_ERRCODE,ExceptionConstant.ADNINISTRATOR_PASSWORD_INVALID_ERRMSG);
        }

    }
   /* private String username;

    private String realName;

    private String avatarUrl;

    private String email;

    private String mobile;

    private Byte newsSubscribed;

    private Long CreateTime;*/
    @GetMapping("getProfile")
    public CustomerShowOutDTO getProfile(@RequestAttribute Integer customerId){
        Customer customer= customerService.getProfileById(customerId);
        CustomerShowOutDTO customerShowOutDTO = new CustomerShowOutDTO();
        customerShowOutDTO.setUsername(customer.getUsername());
        customerShowOutDTO.setRealName(customer.getRealName());
        customerShowOutDTO.setAvatarUrl(customer.getAvatarUrl());
        customerShowOutDTO.setEmail(customer.getEmail());
        customerShowOutDTO.setMobile(customer.getMobile());
        customerShowOutDTO.setCreateTime(customer.getCreateTime().getTime());
        return customerShowOutDTO;
    }

    @GetMapping("updateProfile")
    public void updateProfile(@RequestBody CustomerUpdateInDTO customerUpdateInDTO, @RequestAttribute Integer customerId){

    }

    @GetMapping("customerChangePassword")
    public void customerChangePassword(@RequestBody CustomerChangerPasswordInDTO customerChangerPasswordInDTO,
                                       @RequestAttribute Integer customerId){

    }
}
