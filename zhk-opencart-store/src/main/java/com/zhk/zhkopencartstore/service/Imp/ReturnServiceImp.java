package com.zhk.zhkopencartstore.service.Imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhk.zhkopencartstore.dao.ReturnHistoryMapper;
import com.zhk.zhkopencartstore.dao.ReturnMapper;
import com.zhk.zhkopencartstore.dto.in.ReturnApplyInDTO;
import com.zhk.zhkopencartstore.dto.out.ReturnHistoryListOutDTO;
import com.zhk.zhkopencartstore.dto.out.ReturnShowOutDTO;
import com.zhk.zhkopencartstore.po.Return;
import com.zhk.zhkopencartstore.po.ReturnHistory;
import com.zhk.zhkopencartstore.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public Page<Return> getReturnList(Integer pageNum,Integer customerId) {
        PageHelper.startPage(pageNum,3);
        Page<Return> returns= returnMapper.getReturnList(customerId);
        return returns;
    }

    @Override
    public ReturnShowOutDTO returnShow(Integer returnId) {
        Return aReturn = returnMapper.selectByPrimaryKey(returnId);

        ReturnShowOutDTO returnShowOutDTO = new ReturnShowOutDTO();

        returnShowOutDTO.setReturnId(aReturn.getReturnId());
        returnShowOutDTO.setOrderId(aReturn.getOrderId());
        returnShowOutDTO.setOrderTimestamp(aReturn.getOrderTime().getTime());
        returnShowOutDTO.setCustomerName(aReturn.getCustomerName());
        returnShowOutDTO.setMobile(aReturn.getMobile());
        returnShowOutDTO.setEmail(aReturn.getEmail());
        returnShowOutDTO.setStatus(aReturn.getStatus());
        returnShowOutDTO.setAction(aReturn.getAction());
        returnShowOutDTO.setProductCode(aReturn.getProductCode());
        returnShowOutDTO.setProductName(aReturn.getProductName());
        returnShowOutDTO.setQuantity(aReturn.getQuantity());
        returnShowOutDTO.setReason(aReturn.getReason());
        returnShowOutDTO.setOpened(aReturn.getOpened());
        returnShowOutDTO.setComment(aReturn.getComment());
        returnShowOutDTO.setCreateTimestamp(aReturn.getCreateTime().getTime());
        returnShowOutDTO.setUpdateTimestamp(aReturn.getUpdateTime().getTime());

        List<ReturnHistory> returnHistoryList = returnHistoryMapper.selectbyReturnId(returnId);
        List<ReturnHistoryListOutDTO> returnHistoryListOutDTOS= returnHistoryList.stream().map(returnHistory -> {
            ReturnHistoryListOutDTO returnHistoryListOutDTO = new ReturnHistoryListOutDTO();

            returnHistoryListOutDTO.setReturnId(returnHistory.getReturnId());
            returnHistoryListOutDTO.setTimestamp(returnHistory.getTime().getTime());
            returnHistoryListOutDTO.setReturnStatus(returnHistory.getReturnStatus());
            returnHistoryListOutDTO.setComment(returnHistory.getComment());
            return returnHistoryListOutDTO;
        }).collect(Collectors.toList());
        returnShowOutDTO.setReturnHistories(returnHistoryListOutDTOS);
        return returnShowOutDTO;
    }
}
