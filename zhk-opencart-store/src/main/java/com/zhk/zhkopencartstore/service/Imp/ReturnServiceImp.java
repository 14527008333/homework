package com.zhk.zhkopencartstore.service.Imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhk.zhkopencartstore.dao.ReturnHistoryMapper;
import com.zhk.zhkopencartstore.dao.ReturnMapper;
import com.zhk.zhkopencartstore.dto.in.ReturnApplyInDTO;
import com.zhk.zhkopencartstore.po.Return;
import com.zhk.zhkopencartstore.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReturnServiceImp implements ReturnService {

    @Autowired
    private ReturnMapper returnMapper;

    @Autowired
    private ReturnHistoryMapper returnHistoryMapper;

    @Override
    public Integer returnApply(Return returnApplyInDTO) {

        returnMapper.insert(returnApplyInDTO);

        return returnApplyInDTO.getReturnId();
    }

    @Override
    public Page<Return> getReturnList(Integer pageNum) {
        PageHelper.startPage(pageNum,3);
        Page<Return> returns= returnMapper.getReturnList();
        return returns;
    }
}
