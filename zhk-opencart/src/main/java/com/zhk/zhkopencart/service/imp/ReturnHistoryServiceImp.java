package com.zhk.zhkopencart.service.imp;

import com.zhk.zhkopencart.dao.ReturnHistoryMapper;
import com.zhk.zhkopencart.po.ReturnHistory;
import com.zhk.zhkopencart.service.ReturnHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReturnHistoryServiceImp implements ReturnHistoryService {

    @Autowired
    private ReturnHistoryMapper returnHistoryMapper;

    @Override
    public List<ReturnHistory> getReturnListByReturnId(Integer returnId) {
        List<ReturnHistory> returnHistories= returnHistoryMapper.getReturnListByReturnId(returnId);
        return returnHistories;
    }

    @Override
    public Integer create(ReturnHistory returnHistory) {
        returnHistoryMapper.insertSelective(returnHistory);
        return returnHistory.getReturnHistoryId().intValue();
    }
}
