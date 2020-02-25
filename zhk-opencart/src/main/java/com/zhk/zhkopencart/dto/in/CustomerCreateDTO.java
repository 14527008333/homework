package com.zhk.zhkopencart.dto.in;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerCreateDTO {

    private String userName;

    private String realName;

    private String email;

    private String mobil;

    private String avatarUrl;

    private Integer status;

    private String password;

    private Integer newsSubscribed;

    private Date createTime;
}
