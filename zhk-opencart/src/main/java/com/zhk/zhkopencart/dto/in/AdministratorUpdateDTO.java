package com.zhk.zhkopencart.dto.in;

import lombok.Data;

@Data
public class AdministratorUpdateDTO{

    private Integer administratorId;

    private String userName;

    private String realName;

    private String email;

    private String avatarUrl;

}
