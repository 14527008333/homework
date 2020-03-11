package com.zhk.zhkopencart.dto.out;

import lombok.Data;

@Data
public class ReturnHistoryListOutDTO {
    private Long returnHistoryId;

    private Long timestamp;

    private Byte returnStatus;

    private String comment;

    private Boolean customerNotified;
}
