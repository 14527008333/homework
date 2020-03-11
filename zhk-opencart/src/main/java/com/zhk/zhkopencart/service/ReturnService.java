package com.zhk.zhkopencart.service;

import com.github.pagehelper.Page;
import com.zhk.zhkopencart.po.Return;

public interface ReturnService {
    Page<Return> getReturnList(Integer returnId, Integer orderId, String customerName, String productName, Integer status, Long createTime, Long updateTime, Integer pageNum);

    Return getReturnById(Integer returnId);
}
