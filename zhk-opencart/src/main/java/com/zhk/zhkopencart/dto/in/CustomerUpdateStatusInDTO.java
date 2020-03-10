package com.zhk.zhkopencart.dto.in;

import lombok.Data;

@Data
public class CustomerUpdateStatusInDTO {
    private Integer customerId;

    private Byte status;
}
