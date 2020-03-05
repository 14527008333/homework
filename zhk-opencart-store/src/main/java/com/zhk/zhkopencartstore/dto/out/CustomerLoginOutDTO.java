package com.zhk.zhkopencartstore.dto.out;

import lombok.Data;

@Data
public class CustomerLoginOutDTO {

    private String token;
    private Long expireTimestamp;

}
