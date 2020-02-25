package com.zhk.zhkopencart.dto.out;

import com.zhk.zhkopencart.po.OrderHistory;

import java.util.Date;
import java.util.List;

public class OrderShowDTO {

    private Integer orderId;

    private String customerName;

    private Integer status;

    private Double totalPrice;

    private Integer rewordPoints;

    private Date createTime;

    private Date updateTime;

    private List<OrderHistory> orderHistoryList;
}
