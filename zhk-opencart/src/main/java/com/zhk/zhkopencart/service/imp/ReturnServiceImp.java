package com.zhk.zhkopencart.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhk.zhkopencart.dao.ReturnMapper;
import com.zhk.zhkopencart.po.Return;
import com.zhk.zhkopencart.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReturnServiceImp implements ReturnService {

    @Autowired
    private ReturnMapper returnMapper;

    @Override
    public Page<Return> getReturnList(Integer returnId, Integer orderId,
                                      String customerName, String productName,
                                      Integer status, Long createTime,
                                      Long updateTime, Integer pageNum) {

        PageHelper.startPage(pageNum,3);
        Page<Return> returns= returnMapper.getReturnList(returnId,orderId,customerName,productName,status,createTime==null?null:new Date(createTime),updateTime==null?null:new Date(updateTime));

        return returns;
    }
}
