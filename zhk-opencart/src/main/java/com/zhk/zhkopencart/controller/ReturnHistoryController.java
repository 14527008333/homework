package com.zhk.zhkopencart.controller;

import com.zhk.zhkopencart.dto.in.ReturnHistoryCreateInDTO;
import com.zhk.zhkopencart.dto.out.ReturnHistoryListOutDTO;
import com.zhk.zhkopencart.po.ReturnHistory;
import com.zhk.zhkopencart.service.ReturnHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("returnHistory")
@CrossOrigin
public class ReturnHistoryController {

    @Autowired
    private ReturnHistoryService returnHistoryService;

    @GetMapping("list")
    public List<ReturnHistoryListOutDTO> getReturnListByReturnId(@RequestParam Integer returnId){

        List<ReturnHistory> returnHistories= returnHistoryService.getReturnListByReturnId(returnId);

        List<ReturnHistoryListOutDTO> returnHistoryListOutDTOS = returnHistories.stream().map(returnHistory -> {
            ReturnHistoryListOutDTO returnHistoryListOutDTO = new ReturnHistoryListOutDTO();

            returnHistoryListOutDTO.setReturnHistoryId(returnHistory.getReturnHistoryId());
            returnHistoryListOutDTO.setTimestamp(returnHistory.getTime().getTime());
            returnHistoryListOutDTO.setComment(returnHistory.getComment());
            returnHistoryListOutDTO.setReturnStatus(returnHistory.getReturnStatus());
            returnHistoryListOutDTO.setCustomerNotified(returnHistory.getCustomerNotified());

            return returnHistoryListOutDTO;
        }).collect(Collectors.toList());
        return returnHistoryListOutDTOS;
    }

    @PostMapping("create")
    public Integer create (@RequestBody ReturnHistoryCreateInDTO returnHistoryCreateInDTO){

        ReturnHistory returnHistory = new ReturnHistory();
        returnHistory.setReturnId(returnHistoryCreateInDTO.getReturnId());
        returnHistory.setComment(returnHistoryCreateInDTO.getComment());
        returnHistory.setCustomerNotified(returnHistoryCreateInDTO.getCustomerNotified());
        returnHistory.setTime(new Date());
        returnHistory.setReturnStatus(returnHistoryCreateInDTO.getReturnStatus());

       Integer returnHistoryId= returnHistoryService.create(returnHistory);

       return returnHistoryId;

    }
}
