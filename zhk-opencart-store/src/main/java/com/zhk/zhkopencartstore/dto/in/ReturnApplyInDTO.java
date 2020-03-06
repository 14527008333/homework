package com.zhk.zhkopencartstore.dto.in;

import lombok.Data;

import java.util.Date;

@Data
public class ReturnApplyInDTO {

    private Integer orderId;

    private Long orderTime;

    private String customerName;

    private String mobile;

    private String email;

    private Byte action;

    private String productCode;

    private String productName;

    private Integer quantity;

    private Integer reason;

    private Byte opened;

    private String comment;

}
