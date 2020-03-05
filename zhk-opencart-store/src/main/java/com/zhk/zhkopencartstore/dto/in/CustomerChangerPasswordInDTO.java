package com.zhk.zhkopencartstore.dto.in;

import lombok.Data;

@Data
public class CustomerChangerPasswordInDTO {

    private String originalPassword;

    private String newPassword;
}
