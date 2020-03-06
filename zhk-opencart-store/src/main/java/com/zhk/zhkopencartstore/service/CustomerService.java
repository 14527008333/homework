package com.zhk.zhkopencartstore.service;

import com.zhk.zhkopencartstore.po.Customer;

public interface CustomerService {
    Customer getCustomerByUserName(String userName);

    Customer getProfileById(Integer customerId);
}
