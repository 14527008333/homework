package com.zhk.zhkopencartstore.dto.out;

import lombok.Data;

import java.util.List;

@Data
public class ReturnShowOutDTO {

    private Integer returnId;
    private Long orderId;
    private Long orderTimestamp;
    private String customerName;
    private String mobile;
    private String email;
    private Byte status;
    private Byte action;
    private String productCode;
    private String productName;
    private Integer quantity;
    private Byte reason;
    private Boolean opened;
    private String comment;
    private Long createTimestamp;
    private Long updateTimestamp;
    private List<ReturnHistoryListOutDTO> returnHistories;

}
