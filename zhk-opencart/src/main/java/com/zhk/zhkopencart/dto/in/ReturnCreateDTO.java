package com.zhk.zhkopencart.dto.in;

import lombok.Data;

import java.util.Date;

@Data
public class ReturnCreateDTO {

    private Integer orderId;

    private String customerName;

    private String productName;

    private Date orderTime;

    private String email;

    private String mobile;

    private Integer returnAction;

    private Integer quantity;

    private Integer reason;

    private Integer opened;

    private Integer status;

    private Date createTime;

    private Date updateTime;
}
