package com.zhk.zhkopencartstore.controller;

import com.github.pagehelper.Page;
import com.zhk.zhkopencartstore.dto.in.ReturnApplyInDTO;
import com.zhk.zhkopencartstore.dto.out.PageOutDTO;
import com.zhk.zhkopencartstore.dto.out.ReturnLsitOutDTO;
import com.zhk.zhkopencartstore.enums.ReturnStatusEnum;
import com.zhk.zhkopencartstore.po.Return;
import com.zhk.zhkopencartstore.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("return")
@CrossOrigin
public class ReturnController {

    @Autowired
    private ReturnService returnService;

    @PostMapping("returnApply")
    public Integer returnApply(@RequestBody ReturnApplyInDTO returnApplyInDTO,@RequestAttribute Integer customerId){

        Return aReturn = new Return();
        aReturn.setOrderId(new Long(returnApplyInDTO.getOrderId()));
        aReturn.setOrderTime(new Date(returnApplyInDTO.getOrderTime()));
        aReturn.setCustomerId(customerId);
        aReturn.setCustomerName(returnApplyInDTO.getCustomerName());
        aReturn.setMobile(returnApplyInDTO.getMobile());
        aReturn.setEmail(returnApplyInDTO.getEmail());
        aReturn.setStatus((byte)ReturnStatusEnum.待处理.ordinal());
        aReturn.setAction(returnApplyInDTO.getAction());
        aReturn.setProductCode(returnApplyInDTO.getProductCode());
        aReturn.setProductName(returnApplyInDTO.getProductName());
        aReturn.setQuantity(returnApplyInDTO.getQuantity());
        Integer reason = returnApplyInDTO.getReason();
        int a=reason;
        aReturn.setReason((byte)a);
        if (returnApplyInDTO.getOpened()!=null){
            if(returnApplyInDTO.getOpened()==0){
                aReturn.setOpened(false);
            }else if(returnApplyInDTO.getOpened()==1){
                aReturn.setOpened(true);
            }
        }
        aReturn.setComment(returnApplyInDTO.getComment());
        Date date = new Date();
        aReturn.setCreateTime(date);
        aReturn.setUpdateTime(date);

        Integer integer = returnService.returnApply(aReturn);

        return integer;
    }

    @GetMapping("list")
    public PageOutDTO<ReturnLsitOutDTO> ReturnList(@RequestParam(required = false,defaultValue = "1")Integer pageNum){

        Page<Return> returnList = returnService.getReturnList(pageNum);

        List<ReturnLsitOutDTO> returnLsitOutDTOS= returnList.stream().map(aReturn->{
            ReturnLsitOutDTO returnLsitOutDTO = new ReturnLsitOutDTO();
            returnLsitOutDTO.setReturnId(aReturn.getReturnId());
            returnLsitOutDTO.setOrderId(aReturn.getOrderId());
            returnLsitOutDTO.setCustomerName(aReturn.getCustomerName());
            returnLsitOutDTO.setMobile(aReturn.getMobile());
            returnLsitOutDTO.setProductName(aReturn.getProductName());
            returnLsitOutDTO.setCreateTime(aReturn.getCreateTime().getTime());
            return returnLsitOutDTO;
        }).collect(Collectors.toList());

        PageOutDTO<ReturnLsitOutDTO> returnLsitOutDTOPageOutDTO = new PageOutDTO<>();
        returnLsitOutDTOPageOutDTO.setTotal(returnList.getTotal());
        returnLsitOutDTOPageOutDTO.setPageNum(returnList.getPageNum());
        returnLsitOutDTOPageOutDTO.setPageSize(returnList.getPageSize());
        returnLsitOutDTOPageOutDTO.setList(returnLsitOutDTOS);

        return returnLsitOutDTOPageOutDTO;
    }


}
