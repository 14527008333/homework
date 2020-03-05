package com.zhk.zhkopencartstore.dto.in;

import lombok.Data;

@Data
public class CustomerUpdateInDTO {

    private String username;

    private String realName;

    private String avatarUrl;

    private String email;

    private String mobile;

    private Byte newsSubscribed;

    private Integer defaultAddressId;
}
