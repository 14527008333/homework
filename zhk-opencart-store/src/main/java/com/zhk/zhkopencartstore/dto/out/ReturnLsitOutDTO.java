package com.zhk.zhkopencartstore.dto.out;

import lombok.Data;

@Data
public class ReturnLsitOutDTO {

    private Integer returnId;

    private Long orderId;

    private String customerName;

    private String mobile;

    private String productName;

    private Long CreateTime;

}
