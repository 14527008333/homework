package com.zhk.zhkopencartstore.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.zhk.zhkopencartstore.constant.ExceptionConstant;
import com.zhk.zhkopencartstore.dto.in.CustomerChangerPasswordInDTO;
import com.zhk.zhkopencartstore.dto.in.CustomerRegisterInDTO;
import com.zhk.zhkopencartstore.dto.in.CustomerUpdateInDTO;
import com.zhk.zhkopencartstore.dto.in.ResetPswInDTO;
import com.zhk.zhkopencartstore.dto.out.CustomerLoginOutDTO;
import com.zhk.zhkopencartstore.dto.out.CustomerShowOutDTO;
import com.zhk.zhkopencartstore.enums.CustomerStatusEnum;
import com.zhk.zhkopencartstore.exception.ClientException;
import com.zhk.zhkopencartstore.po.Customer;
import com.zhk.zhkopencartstore.service.CustomerService;
import com.zhk.zhkopencartstore.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.DatatypeConverter;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("customer")
@CrossOrigin
public class CustomerController {

    private HashMap<String,String> pwdResetCode =new HashMap();

    @Autowired
    private CustomerService customerService;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private SecureRandom secureRandom;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEamil;


    @PostMapping("register")
    public Integer registerCustomer(@RequestBody CustomerRegisterInDTO customerRegisterInDTO){
        Customer customer = new Customer();
        customer.setUsername(customerRegisterInDTO.getUsername());
        customer.setRealName(customerRegisterInDTO.getRealName());
        customer.setEmail(customerRegisterInDTO.getEmail());
        customer.setMobile(customerRegisterInDTO.getMobile());
        Byte newsSubscribed = customerRegisterInDTO.getNewsSubscribed();
        String bCryptPassword = BCrypt.withDefaults().hashToString(12, customerRegisterInDTO.getPassword().toCharArray());
        customer.setEncryptedPassword(bCryptPassword);
        if(newsSubscribed==0||newsSubscribed==null){
            customer.setNewsSubscribed(false);
        }else{
            customer.setNewsSubscribed(true);
        }
        customer.setCreateTime(new Date());
        customer.setStatus((byte)CustomerStatusEnum.启用.ordinal());
        customer.setRewordPoints(0);
       Integer customerId= customerService.registerCustomer(customer);
        return customerId;
    }

    @GetMapping("login")
    public CustomerLoginOutDTO registerCustomer(@RequestParam String userName, @RequestParam String password) throws ClientException {
       Customer customer= customerService.getCustomerByUserName(userName);
        if(customer==null){
            throw new ClientException(ExceptionConstant.CUSTOMER_USERNAME_NOT_EXIST_ERRCODE,ExceptionConstant.CUSTOMER_USERNAME_NOT_EXIST_ERRMSG);
        }
        //对密码进行校验
        BCrypt.Result verify = BCrypt.verifyer().verify(password.toCharArray(), customer.getEncryptedPassword());

        //校验通过发出Token令牌否则抛出密码错误
        if(verify.verified){
            CustomerLoginOutDTO administratorLoginOutDTO = jwtUtil.issueToken(customer);
            return administratorLoginOutDTO;
        }
        else{
            throw new ClientException(ExceptionConstant.CUSTOMER_PASSWORD_INVALID_ERRCODE,ExceptionConstant.CUSTOMER_PASSWORD_INVALID_ERRMSG);
        }

    }

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

    @PostMapping("updateProfile")
    public void updateProfile(@RequestBody CustomerUpdateInDTO customerUpdateInDTO, @RequestAttribute Integer customerId){

        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setUsername(customerUpdateInDTO.getUsername());
        customer.setRealName(customerUpdateInDTO.getRealName());
        customer.setAvatarUrl(customerUpdateInDTO.getAvatarUrl());
        customer.setEmail(customerUpdateInDTO.getEmail());
        customer.setMobile(customerUpdateInDTO.getMobile());
        if(customerUpdateInDTO.getNewsSubscribed()==0||customerUpdateInDTO.getNewsSubscribed()==null){
            customer.setNewsSubscribed(false);
        }else if (customerUpdateInDTO.getNewsSubscribed()==1){
            customer.setNewsSubscribed(true);
        }
        customer.setDefaultAddressId(customerUpdateInDTO.getDefaultAddressId());
        customerService.updateProfile(customer);
    }

    @PostMapping("customerChangePassword")
    public void customerChangePassword(@RequestBody CustomerChangerPasswordInDTO customerChangerPasswordInDTO,
                                       @RequestAttribute Integer customerId) throws ClientException {
        //根据customerId获取用户对象，得到密码
        Customer customerById = customerService.getProfileById(customerId);
        BCrypt.Result verify = BCrypt.verifyer().verify(customerChangerPasswordInDTO.getOriginalPassword().toCharArray(), customerById.getEncryptedPassword());

        //判断原始密码是否正确
        if(verify.verified){
            String newPassword = BCrypt.withDefaults().hashToString(12, customerChangerPasswordInDTO.getNewPassword().toCharArray());
            customerById.setEncryptedPassword(newPassword);
            customerService.updateProfile(customerById);
        }else{
            throw new ClientException(ExceptionConstant.CUSTOMER_PASSWORD_INVALID_ERRCODE,ExceptionConstant.CUSTOMER_PASSWORD_INVALID_ERRMSG);
        }

    }

    @PostMapping("getPwdResetCode")
    public void getPwdResetCode(@RequestParam(required = false) String email) throws ClientException {

        //判断邮箱是否为空
        if(email==null){
            throw new ClientException(ExceptionConstant.CUSTOMER_INPUT_EMAIL_IS_EMPTY_ERRCODE,ExceptionConstant.CUSTOMER_INPUT_EMAIL_IS_EMPTY_ERRMSG);
        }

        //判断是否存在该邮箱的用户
        Customer profileById = customerService.getCustomerByEmail(email);
        if(profileById==null){
            throw new ClientException(ExceptionConstant.CUSTOMER_USERNAME_NOT_EXIST_ERRCODE,ExceptionConstant.CUSTOMER_USERNAME_NOT_EXIST_ERRMSG);
        }

        //获取随机值 并为用户发送邮箱验证码
        byte[] bytes = secureRandom.generateSeed(6);
        String emailCode = DatatypeConverter.printHexBinary(bytes);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromEamil);
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("jart电商平台重置密码邮箱验证码");
        simpleMailMessage.setText(emailCode);
        javaMailSender.send(simpleMailMessage);
        //存入Map对象 以便验证取出
        pwdResetCode.put(profileById.getEmail(),emailCode);
    }

    @PostMapping("resetPsw")
    public void resetPsw(@RequestBody ResetPswInDTO resetPswInDTO) throws ClientException {

        //判断用户是否输入邮箱
        if(resetPswInDTO.getEmail()==null){
            throw new ClientException(ExceptionConstant.CUSTOMER_INPUT_EMAIL_IS_EMPTY_ERRCODE,ExceptionConstant.CUSTOMER_INPUT_EMAIL_IS_EMPTY_ERRMSG);
        }
        //判断用户是否输入验证码
        String resetCode = resetPswInDTO.getResetCode();
        if(resetCode==null){
            throw new ClientException(ExceptionConstant.CUSTOMER_RESETCODE_IS_EMPTY_ERRCODE,ExceptionConstant.CUSTOMER_RESETCODE_IS_EMPTY_ERRMSG);
        }
        //判断是否发送验证码  Map对象里面是否有对应值
        String hashResetCode = pwdResetCode.get(resetPswInDTO.getEmail());
        if (hashResetCode==null){
            throw new ClientException(ExceptionConstant.CUSTOMER_NOT_SEND_RESETCODE_ERRCODE,ExceptionConstant.CUSTOMER_NOT_SEND_RESETCODE_ERRMSG);
        }

        //判断验证码是否一致
        if(!resetCode.equals(hashResetCode)){
            throw new ClientException(ExceptionConstant.CUSTOMER_RESETCODE_INVALID_ERRCODE,ExceptionConstant.CUSTOMER_RESETCODE_INVALID_ERRMSG);
        }

        //判断是否存在该邮箱的用户
        Customer customerByEmail = customerService.getCustomerByEmail(resetPswInDTO.getEmail());
        if(customerByEmail==null){
            throw new ClientException(ExceptionConstant.CUSTOMER_USERNAME_NOT_EXIST_ERRCODE,ExceptionConstant.CUSTOMER_USERNAME_NOT_EXIST_ERRMSG);
        }

        //判断是否输入了新密码
        String newPassword = resetPswInDTO.getNewPassword();
        if (newPassword==null){
            throw new ClientException(ExceptionConstant.CUSTOMER_NEWPWD_IS_EMPTY_ERRCODE,ExceptionConstant.CUSTOMER_NEWPWD_IS_EMPTY_ERRMSG);
        }

        //给新密码加密并修改
        String newEncryptedPassword = BCrypt.withDefaults().hashToString(12, newPassword.toCharArray());
        customerByEmail.setEncryptedPassword(newEncryptedPassword);
        customerService.updateProfile(customerByEmail);

        pwdResetCode.remove(resetPswInDTO.getEmail());
    }
}
