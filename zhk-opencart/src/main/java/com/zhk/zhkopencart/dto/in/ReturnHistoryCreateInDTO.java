package com.zhk.zhkopencart.dto.in;

import lombok.Data;

@Data
public class ReturnHistoryCreateInDTO {

    private Integer returnId;

    private Byte returnStatus;

    private String comment;

    private Boolean customerNotified;



}
