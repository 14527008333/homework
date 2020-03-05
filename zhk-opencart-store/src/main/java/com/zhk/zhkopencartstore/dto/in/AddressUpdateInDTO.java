package com.zhk.zhkopencartstore.dto.in;

import lombok.Data;
@Data
public class AddressUpdateInDTO {

    private Integer addressId;

    private String receiverName;

    private String receiverMobile;

    private String content;

    private String tag;

}
