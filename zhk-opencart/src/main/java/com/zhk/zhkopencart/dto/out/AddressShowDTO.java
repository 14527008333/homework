package com.zhk.zhkopencart.dto.out;

import lombok.Data;

@Data
public class AddressShowDTO {
    private Integer addressId;

    private String receiverName;

    private String receiverMobile;

    private String content;

    private String tag;
}
