package com.zhk.zhkopencart.service;

import com.zhk.zhkopencart.po.ReturnHistory;

import java.util.List;

public interface ReturnHistoryService {
    List<ReturnHistory> getReturnListByReturnId(Integer returnId);

    Integer create(ReturnHistory returnHistory);
}
