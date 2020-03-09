package com.zhk.zhkopencart.dto.in;

import lombok.Data;

@Data
public class ResetPswInDTO {

    private String email;

    private String resetCode;

    private String newPassword;

}
