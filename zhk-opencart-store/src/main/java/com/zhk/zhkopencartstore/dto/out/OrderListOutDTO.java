package com.zhk.zhkopencartstore.dto.out;

import lombok.Data;

import java.util.Date;

@Data
public class OrderListOutDTO {

    private Long orderId;

    private Byte status;

    private Double totalPrice;

    private Integer rewordPoints;

    private Date createTime;

}
