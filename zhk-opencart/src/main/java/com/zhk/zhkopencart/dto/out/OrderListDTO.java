package com.zhk.zhkopencart.dto.out;

import lombok.Data;

import java.util.Date;

@Data
public class OrderListDTO {

    private Integer orderId;

    private String customerName;

    private Integer status;

    private Double totalPrice;

    private Integer rewordPoints;

    private Date createTime;

    private Date updateTime;

}
