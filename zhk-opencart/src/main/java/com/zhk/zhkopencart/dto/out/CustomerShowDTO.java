package com.zhk.zhkopencart.dto.out;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerShowDTO {

    private Integer customerId;

    private String userName;

    private String realName;

    private String email;

    private String mobil;

    private String avatarUrl;

    private Integer status;

    private Boolean newsSubscribed;

    private Integer rewordPoints;

    private Long createTime;

    private Integer defaultAddressId;

    private String defaultAddress;

}
