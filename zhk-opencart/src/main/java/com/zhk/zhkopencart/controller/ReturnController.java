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

        return null;
    }


    @PostMapping("update")
    public void update(@RequestPart(required = false) ReturnCreateDTO returnCreateDTO){

    }
}
