package com.zhk.zhkopencart.dto.in;

import lombok.Data;

@Data
public class AdministratorChangePasswordInDTO {
    private String originalPassword;

    private String newPassword;
}
