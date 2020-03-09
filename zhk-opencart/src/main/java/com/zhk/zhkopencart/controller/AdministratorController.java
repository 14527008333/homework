package com.zhk.zhkopencart.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.github.pagehelper.Page;
import com.zhk.zhkopencart.constant.ExceptionConstant;
import com.zhk.zhkopencart.dto.in.AdministratorChangePasswordInDTO;
import com.zhk.zhkopencart.dto.in.AdministratorCreateDTO;
import com.zhk.zhkopencart.dto.in.AdministratorUpdateDTO;
import com.zhk.zhkopencart.dto.in.ResetPswInDTO;
import com.zhk.zhkopencart.dto.out.AdministratorListDTO;
import com.zhk.zhkopencart.dto.out.AdministratorLoginOutDTO;
import com.zhk.zhkopencart.dto.out.AdministratorShowDTO;
import com.zhk.zhkopencart.dto.out.PageDTO;
import com.zhk.zhkopencart.enums.AdministratorStatusEnum;
import com.zhk.zhkopencart.exception.ClientException;
import com.zhk.zhkopencart.po.Administrator;
import com.zhk.zhkopencart.service.AdministratorServer;
import com.zhk.zhkopencart.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.DatatypeConverter;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("administrator")
@CrossOrigin
public class AdministratorController {

    private HashMap<String,String> pwdResetCode =new HashMap();

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEamil;

    @Autowired
    private AdministratorServer administratorServer;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private SecureRandom secureRandom;

    @GetMapping("list")
    public PageDTO<AdministratorListDTO> getUserList(@RequestParam(required = false,defaultValue = "1")Integer pageNum){

        Page<AdministratorListDTO> administratorListDTOS= administratorServer.getUserList(pageNum);

        PageDTO<AdministratorListDTO> administratorListDTOPageDTO = new PageDTO<>();
        administratorListDTOPageDTO.setPageNum(administratorListDTOS.getPageNum());
        administratorListDTOPageDTO.setPageSize(administratorListDTOS.getPageSize());
        administratorListDTOPageDTO.setTotal(administratorListDTOS.getTotal());
        administratorListDTOPageDTO.setList(administratorListDTOS);

        return administratorListDTOPageDTO;
    }


    @GetMapping("login")
    public AdministratorLoginOutDTO login(@RequestParam(required = false) String userName, @RequestParam(required = false) String password) throws ClientException {
        //根据用户名获取用户对象并判断是否为空  为空抛出用户不存在
        Administrator administrator= administratorServer.selectByUsername(userName);
        if(administrator==null){
            throw new ClientException(ExceptionConstant.ADMINISTRATOR_USERNAME_NOT_EXIST_ERRCODE,ExceptionConstant.ADMINISTRATOR_USERNAME_NOT_EXIST_ERRMSG);
        }
        //对密码进行校验
        BCrypt.Result verify = BCrypt.verifyer().verify(password.toCharArray(), administrator.getEncryptedPassword());

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
    public AdministratorShowDTO show(@RequestAttribute Integer administratorId){
        Administrator administrator = administratorServer.getAdministratorById(administratorId);
        AdministratorShowDTO administratorShowDTO = new AdministratorShowDTO();
        administratorShowDTO.setAdministratorId(administrator.getAdministratorId());
        administratorShowDTO.setUserName(administrator.getUsername());
        administratorShowDTO.setRealName(administrator.getRealName());
        administratorShowDTO.setEmail(administrator.getEmail());
        administratorShowDTO.setCreateTime(administrator.getCreateTime());
        administratorShowDTO.setAvatarUrl(administrator.getAvatarUrl());

        return administratorShowDTO;
    }
    @PostMapping("createAdministrator")
    public Integer create(@RequestBody AdministratorCreateDTO administratorCreateDTO){

        Administrator administrator = new Administrator();

        administrator.setUsername(administratorCreateDTO.getUserName());
        administrator.setRealName(administratorCreateDTO.getRealName());
        administrator.setEmail(administratorCreateDTO.getEmail());
        administrator.setAvatarUrl(administratorCreateDTO.getAvatarUrl());
        String bCryptAdminPwd = BCrypt.withDefaults().hashToString(12, administratorCreateDTO.getPassword().toCharArray());
        administrator.setEncryptedPassword(bCryptAdminPwd);
        administrator.setStatus((byte)AdministratorStatusEnum.启用.ordinal());
        administrator.setCreateTime(new Date());

       Integer administratorId= administratorServer.create(administrator);

        return administratorId;
    }


    @PostMapping("updateOneself")
    public void updateOneself(@RequestBody AdministratorUpdateDTO administratorUpdateDTO,
                       @RequestAttribute Integer administratorId){
        Administrator administrator = new Administrator();

        administrator.setAdministratorId(administratorId);
        administrator.setUsername(administratorUpdateDTO.getUserName());
        administrator.setRealName(administratorUpdateDTO.getRealName());
        administrator.setEmail(administratorUpdateDTO.getEmail());
        administrator.setAvatarUrl(administratorUpdateDTO.getAvatarUrl());

        administratorServer.update(administrator);
    }


    @PostMapping("administratorChangePassword")
    public void administratorChangePassword(@RequestBody AdministratorChangePasswordInDTO administratorChangePasswordInDTO,
                              @RequestAttribute Integer administratorId) throws ClientException {

        Administrator administratorById = administratorServer.getAdministratorById(administratorId);
        String encryptedPassword = administratorById.getEncryptedPassword();
        String originalPassword = administratorChangePasswordInDTO.getOriginalPassword();
        String newPassword = administratorChangePasswordInDTO.getNewPassword();
        if(originalPassword==null||newPassword==null){
            throw new ClientException(ExceptionConstant.ADNINISTRATOR_PASSWORD_NOT_EXIST_ERRCODE,ExceptionConstant.ADNINISTRATOR_PASSWORD_NOT_EXIST_ERRMSG);
        }
        BCrypt.Result verify = BCrypt.verifyer().verify(originalPassword.toCharArray(), encryptedPassword);
        if(verify.verified){
            String newBCryptPwd = BCrypt.withDefaults().hashToString(12, newPassword.toCharArray());
            administratorById.setEncryptedPassword(newBCryptPwd);
            administratorServer.update(administratorById);
        }else {
            throw new ClientException(ExceptionConstant.ADNINISTRATOR_PASSWORD_INVALID_ERRCODE,ExceptionConstant.ADNINISTRATOR_PASSWORD_INVALID_ERRMSG);
        }

    }

    @PostMapping("getPwdResetCode")
    public void getPwdResetCode(@RequestParam(required = false) String email) throws ClientException {

        //判断邮箱是否为空
        if(email==null){
            throw new ClientException(ExceptionConstant.ADMINISTRATOR_INPUT_EMAIL_IS_EMPTY_ERRCODE,ExceptionConstant.ADMINISTRATOR_INPUT_EMAIL_IS_EMPTY_ERRMSG);
        }

        //判断是否存在该邮箱的用户
        Administrator administrator = administratorServer.getAdministratorByEmail(email);
        if(administrator==null){
            throw new ClientException(ExceptionConstant.ADMINISTRATOR_USERNAME_NOT_EXIST_ERRCODE,ExceptionConstant.ADMINISTRATOR_USERNAME_NOT_EXIST_ERRMSG);
        }

        //获取随机值 并为用户发送邮箱验证码
        byte[] bytes = secureRandom.generateSeed(3);
        String emailCode = DatatypeConverter.printHexBinary(bytes);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromEamil);
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("jart电商平台重置密码邮箱验证码");
        simpleMailMessage.setText(emailCode);
        javaMailSender.send(simpleMailMessage);
        //存入Map对象 以便验证取出
        pwdResetCode.put(email,emailCode);
    }

    @PostMapping("resetPsw")
    public void resetPsw(@RequestBody ResetPswInDTO resetPswInDTO) throws ClientException {

        //判断用户是否输入邮箱
        if(resetPswInDTO.getEmail()==null){
            throw new ClientException(ExceptionConstant.ADMINISTRATOR_INPUT_EMAIL_IS_EMPTY_ERRCODE,ExceptionConstant.ADMINISTRATOR_INPUT_EMAIL_IS_EMPTY_ERRMSG);
        }
        //判断用户是否输入验证码
        String resetCode = resetPswInDTO.getResetCode();
        if(resetCode==null){
            throw new ClientException(ExceptionConstant.ADMINISTRATOR_RESETCODE_IS_EMPTY_ERRCODE,ExceptionConstant.ADMINISTRATOR_RESETCODE_IS_EMPTY_ERRMSG);
        }
        //判断是否发送验证码  Map对象里面是否有对应值
        String hashResetCode = pwdResetCode.get(resetPswInDTO.getEmail());
        if (hashResetCode==null){
            throw new ClientException(ExceptionConstant.ADMINISTRATOR_NOT_SEND_RESETCODE_ERRCODE,ExceptionConstant.ADMINISTRATOR_NOT_SEND_RESETCODE_ERRMSG);
        }

        //判断验证码是否一致
        if(!resetCode.equals(hashResetCode)){
            throw new ClientException(ExceptionConstant.ADMINISTRATOR_RESETCODE_INVALID_ERRCODE,ExceptionConstant.ADMINISTRATOR_RESETCODE_INVALID_ERRMSG);
        }

        //判断是否存在该邮箱的用户
        Administrator administrator = administratorServer.getAdministratorByEmail(resetPswInDTO.getEmail());
        if(administrator==null){
            throw new ClientException(ExceptionConstant.ADMINISTRATOR_USERNAME_NOT_EXIST_ERRCODE,ExceptionConstant.ADMINISTRATOR_USERNAME_NOT_EXIST_ERRMSG);
        }

        //判断是否输入了新密码
        String newPassword = resetPswInDTO.getNewPassword();
        if (newPassword==null){
            throw new ClientException(ExceptionConstant.ADMINISTRATOR_NEWPWD_IS_EMPTY_ERRCODE,ExceptionConstant.ADMINISTRATOR_NEWPWD_IS_EMPTY_ERRMSG);
        }

        //给新密码加密并修改
        String newEncryptedPassword = BCrypt.withDefaults().hashToString(12, newPassword.toCharArray());
        administrator.setEncryptedPassword(newEncryptedPassword);
        administratorServer.update(administrator);
    }



}
