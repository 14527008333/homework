package com.zhk.zhkopencart.dto.in;

import lombok.Data;

@Data
public class AddressCreateDTO {

    private String receiverName;

    private String receiverMobile;

    private String content;

    private String tag;
}
