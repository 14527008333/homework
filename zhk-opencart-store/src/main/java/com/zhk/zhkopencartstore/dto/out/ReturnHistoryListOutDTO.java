package com.zhk.zhkopencartstore.dto.out;

import lombok.Data;

@Data
public class ReturnHistoryListOutDTO {
    private Integer returnId;
    private Long timestamp;
    private Byte returnStatus;
    private String comment;
}
