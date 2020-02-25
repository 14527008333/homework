package com.zhk.zhkopencart.dto.out;

import com.zhk.zhkopencart.po.ReturnHistory;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ReturnShowDTO {
    private Integer returnId;

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

    private List<ReturnHistory> returnHistoryList;
}
