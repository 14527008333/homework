package com.zhk.zhkopencart.dto.out;

import lombok.Data;

import java.util.Date;

@Data
public class OrderHistoryListOutDTO {
    private Integer orderHistoryId;

    private Long time;

    private Byte orderStatus;

    private String comment;

    private Boolean customerNotified;
}
