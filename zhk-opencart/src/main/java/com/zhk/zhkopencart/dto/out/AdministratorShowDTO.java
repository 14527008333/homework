package com.zhk.zhkopencart.dto.out;

import lombok.Data;

import java.util.Date;

@Data
public class AdministratorShowDTO{
    private Integer administratorId;

    private String userName;

    private Date createTime;

    private String realName;

    private String email;

    private String avatarUrl;
}
