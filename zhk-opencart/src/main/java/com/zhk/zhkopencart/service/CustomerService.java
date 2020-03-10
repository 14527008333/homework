package com.zhk.zhkopencart.service;

import com.github.pagehelper.Page;
import com.zhk.zhkopencart.dto.in.CustomerUpdateStatusInDTO;
import com.zhk.zhkopencart.po.Customer;

public interface CustomerService {
    Page<Customer> getCustomerList(String userName, String email, Integer status, Long createTime, Integer pageNum);

    Customer getCustomerById(Integer customerId);

    void CustomerUpdateStatusInDTO(CustomerUpdateStatusInDTO customerUpdateStatusInDTO);
}
