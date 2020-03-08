package com.zhk.zhkopencartstore.dto.in;

import lombok.Data;

@Data
public class ResetPswInDTO {

    private String email;

    private String resetCode;

    private String newPassword;

}
