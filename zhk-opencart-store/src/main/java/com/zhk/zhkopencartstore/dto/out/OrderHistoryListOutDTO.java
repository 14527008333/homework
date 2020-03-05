package com.zhk.zhkopencartstore.dto.out;

import lombok.Data;

@Data
public class OrderHistoryListOutDTO {

    private Long timestamp;
    private Byte orderStatus;
    private String comment;
}
