package com.zhk.zhkopencartstore.service;


import com.github.pagehelper.Page;
import com.zhk.zhkopencartstore.dto.in.ReturnApplyInDTO;
import com.zhk.zhkopencartstore.po.Return;

public interface ReturnService {
    Integer returnApply(Return insertReturn);

    Page<Return> getReturnList(Integer pageNum);
}
