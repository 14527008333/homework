package com.zhk.zhkopencart.po;

import lombok.Data;

import java.util.Date;

@Data
public class OrderHistory {
    private Integer orderHistoryId;

    private Integer orderId;

    private Date time;

    private Integer orderStatus;

    private String comment;

    private Integer customer;
}
