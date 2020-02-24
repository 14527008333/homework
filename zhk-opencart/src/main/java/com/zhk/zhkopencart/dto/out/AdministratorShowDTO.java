package com.zhk.zhkopencart.dto.out;

import lombok.Data;

@Data
public class AdministratorShowDTO extends AdministratorListDTO{
    private String encryptedPassword;
}
