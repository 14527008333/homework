package com.zhk.zhkopencart.controller;

import com.github.pagehelper.Page;
import com.zhk.zhkopencart.dto.in.ReturnCreateDTO;
import com.zhk.zhkopencart.dto.out.PageDTO;
import com.zhk.zhkopencart.dto.out.ReturnListDTO;
import com.zhk.zhkopencart.dto.out.ReturnShowDTO;
import com.zhk.zhkopencart.po.Return;
import com.zhk.zhkopencart.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("return")
public class ReturnController {

    @Autowired
    private ReturnService returnService;

    @GetMapping("list")
    public PageDTO<ReturnListDTO> getReturnList(@RequestParam(required = false) Integer returnId,
                                          @RequestParam(required = false) Integer orderId,
                                          @RequestParam(required = false) String customerName,
                                          @RequestParam(required = false) String productName,
                                          @RequestParam(required = false) Integer status,
                                          @RequestParam(required = false)Long createTime,
                                          @RequestParam(required = false)Long updateTime,
                                          @RequestParam(required = false,defaultValue = "1")Integer pageNum){
        Page<Return> returns= returnService.getReturnList(returnId,orderId,customerName,productName,status,createTime,updateTime,pageNum);

        List<ReturnListDTO> collect = returns.stream().map(myReturn -> {
            ReturnListDTO returnListDTO = new ReturnListDTO();

            returnListDTO.setReturnId(myReturn.getReturnId());
            returnListDTO.setOrderId(myReturn.getOrderId().intValue());
            returnListDTO.setCustomerId(myReturn.getCustomerId());
            returnListDTO.setCustomerName(myReturn.getCustomerName());
            returnListDTO.setProductName(myReturn.getProductName());
            returnListDTO.setStatus(myReturn.getStatus().intValue());
            returnListDTO.setCreateTime(myReturn.getCreateTime().getTime());
            returnListDTO.setUpdateTime(myReturn.getUpdateTime().getTime());

            return returnListDTO;
        }).collect(Collectors.toList());

        PageDTO<ReturnListDTO> returnListDTOPageDTO = new PageDTO<>();
        returnListDTOPageDTO.setTotal(returns.getTotal());
        returnListDTOPageDTO.setPageNum(returns.getPageNum());
        returnListDTOPageDTO.setPageSize(returns.getPageSize());
        returnListDTOPageDTO.setList(collect);
        return returnListDTOPageDTO;
    }


    @GetMapping("show")
    public ReturnShowDTO show(@RequestParam Integer returnId){
        Return aReturn= returnService.getReturnById(returnId);

        ReturnShowDTO returnShowDTO = new ReturnShowDTO();
        returnShowDTO.setReturnId(aReturn.getReturnId());
        returnShowDTO.setOrderId(aReturn.getOrderId().intValue());
        returnShowDTO.setCustomerName(aReturn.getCustomerName());
        returnShowDTO.setProductName(aReturn.getProductName());
        returnShowDTO.setEmail(aReturn.getEmail());
        returnShowDTO.setMobile(aReturn.getMobile());
        returnShowDTO.setReturnAction(aReturn.getAction().intValue());
        returnShowDTO.setQuantity(aReturn.getQuantity());
        returnShowDTO.setReason(aReturn.getReason().intValue());
        returnShowDTO.setOpened(aReturn.getOpened());
        returnShowDTO.setStatus(aReturn.getStatus().intValue());
        returnShowDTO.setComment(aReturn.getComment());
        returnShowDTO.setCreateTime(aReturn.getCreateTime().getTime());
        returnShowDTO.setUpdateTime(aReturn.getUpdateTime().getTime());
        return returnShowDTO;
    }


    @PostMapping("updateAction")
    public void updateAction(@RequestPart(required = false) ReturnCreateDTO returnCreateDTO){

        Return aReturn = new Return();
        aReturn.setReturnId(returnCreateDTO.getReturnId());
        aReturn.setAction(returnCreateDTO.getAction());
        aReturn.setUpdateTime(new Date());
        returnService.update(aReturn);
    }
}
