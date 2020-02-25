package com.zhk.zhkopencart.dto.out;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerListDTO {
    private Integer customerId;

    private String userName;

    private String realName;

    private String avatarUrl;

    private String email;

    private Integer status;

    private Date createTime;

}
