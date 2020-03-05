package com.zhk.zhkopencartstore.dto.in;

import lombok.Data;

import java.util.List;

@Data
public class OrdercheckoutInDTO {

    private Short shipMethod;
    private Integer shipAddressId;
    private Short payMethod;
    private Integer invoiceAddressId;
    private String comment;
    private List<OrderProductInDTO> orderProducts;

}
