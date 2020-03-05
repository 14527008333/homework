package com.zhk.zhkopencartstore.dto.out;

import lombok.Data;

@Data
public class CustomerShowOutDTO {

    private String username;

    private String realName;

    private String avatarUrl;

    private String email;

    private String mobile;

    private Byte newsSubscribed;

    private Long CreateTime;
}
