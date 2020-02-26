package com.zhk.zhkopencart.dao;

import com.zhk.zhkopencart.po.Administrator;

public interface AdministratorMapper {
    int insert(Administrator record);

    int insertSelective(Administrator record);
}