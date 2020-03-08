package com.zhk.zhkopencartstore.dto.in;

import lombok.Data;

@Data
public class CustomerRegisterInDTO {

    private String username;

    private String realName;

    private String email;

    private String mobile;

    private String password;

    private Byte newsSubscribed;

}
