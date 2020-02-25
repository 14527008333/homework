package com.zhk.zhkopencart.dto.out;

import lombok.Data;

@Data
public class InvoiceShowDTO {

    private String productName;

    private Integer productQuantity;

    private Double unitPrice;

    private Double shipPrice;

    private String comment;
}
