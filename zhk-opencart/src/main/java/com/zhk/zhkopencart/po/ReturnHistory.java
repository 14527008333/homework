package com.zhk.zhkopencart.po;

import lombok.Data;

import java.util.Date;

@Data
public class ReturnHistory {

    private Integer returnHistoryId;

    private Date time;

    private Integer returnStatus;

    private String comment;

    private Integer customerNotified;
}
