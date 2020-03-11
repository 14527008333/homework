package com.zhk.zhkopencart.dto.out;

import lombok.Data;

@Data
public class OrderHistoryCreateOutDTO {

    private Integer orderId;

    private Byte orderStatus;

    private String comment;

    private Boolean customerNotified;
}
