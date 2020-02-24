package com.zhk.zhkopencart.dto.in;

import lombok.Data;

import java.util.Date;

@Data
public class AdministratorCreateDTO {

    private String userName;

    private String realName;

    private String email;

    private String avatarUrl;

    private String password;

}
