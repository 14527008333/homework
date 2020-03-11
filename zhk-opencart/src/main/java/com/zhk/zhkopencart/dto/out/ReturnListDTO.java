package com.zhk.zhkopencart.dto.out;

import lombok.Data;

import java.util.Date;

@Data
public class ReturnListDTO {

    private Integer returnId;

    private Integer orderId;

    private Integer customerId;

    private String customerName;

    private String productName;

    private Integer status;

    private Long createTime;

    private Long updateTime;


}
