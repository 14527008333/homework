package com.zhk.zhkopencartstore.service.Imp;

import com.zhk.zhkopencartstore.dao.CustomerMapper;
import com.zhk.zhkopencartstore.po.Customer;
import com.zhk.zhkopencartstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImp implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public Customer getCustomerByUserName(String userName) {
        Customer customer= customerMapper.getCustomerByUserName(userName);
        return customer;
    }

    @Override
    public Customer getProfileById(Integer customerId) {
        Customer customer = customerMapper.selectByPrimaryKey(customerId);
        return customer;
    }

    @Override
    public void updateProfile(Customer customer) {
        customerMapper.updateByPrimaryKeySelective(customer);
    }

    @Override
    public Customer getCustomerByEmail(String email) {

        Customer customer= customerMapper.getCustomerByEmail(email);

        return customer;
    }

    @Override
    public Integer registerCustomer(Customer customer) {
        customerMapper.insertSelective(customer);
        return customer.getCustomerId();
    }
}
