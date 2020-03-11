package com.zhk.zhkopencart.dto.out;

import com.zhk.zhkopencart.po.OrderHistory;
import com.zhk.zhkopencart.vo.OrderProductVO;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderShowDTO {

    private Long orderId;

    private Byte status;

    private Double totalPrice;

    private Integer rewordPoints;

    private Long createTimestamp;

    private Long updateTimestamp;

    private Short shipMethod;

    private String shipAddress;

    private Double shipPrice;

    private Short payMethod;

    private String invoiceAddress;

    private Double invoicePrice;

    private String comment;

    private List<OrderProductVO> orderProducts;
}
