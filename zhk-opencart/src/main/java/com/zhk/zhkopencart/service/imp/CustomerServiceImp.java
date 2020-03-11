package com.zhk.zhkopencart.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhk.zhkopencart.dao.CustomerMapper;
import com.zhk.zhkopencart.dto.in.CustomerUpdateStatusInDTO;
import com.zhk.zhkopencart.po.Customer;
import com.zhk.zhkopencart.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CustomerServiceImp implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public Page<Customer> getCustomerList(String userName, String email, Integer status, Long createTime, Integer pageNum) {

        PageHelper.startPage(pageNum,3);
        Page<Customer> customerPage= customerMapper.getCustomerList(userName,email,status,createTime==null ? null:new Date(createTime));
        return customerPage;
    }

    @Override
    public Customer getCustomerById(Integer customerId) {
        Customer customer = customerMapper.selectByPrimaryKey(customerId);
        return customer;
    }

    @Override
    public void CustomerUpdateStatusInDTO(CustomerUpdateStatusInDTO customerUpdateStatusInDTO) {
        Customer customer = new Customer();
        customer.setCustomerId(customerUpdateStatusInDTO.getCustomerId());
        customer.setStatus(customerUpdateStatusInDTO.getStatus());
        customerMapper.updateByPrimaryKeySelective(customer);
    }
}
