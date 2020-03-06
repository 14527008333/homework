package com.zhk.zhkopencartstore.dto.out;

import lombok.Data;

@Data
public class AddressLsitOutDTO {

    private Integer addressId;

    private String receiverName;

    private String receiverMobile;

    private String content;

    private String tag;
}
