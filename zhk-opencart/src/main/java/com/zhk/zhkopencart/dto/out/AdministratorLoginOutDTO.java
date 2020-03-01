package com.zhk.zhkopencart.dto.out;

import lombok.Data;

@Data
public class AdministratorLoginOutDTO {

    private String token;
    private Long expireTimestamp;

}
