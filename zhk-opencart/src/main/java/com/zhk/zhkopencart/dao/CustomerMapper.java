package com.zhk.zhkopencart.dao;

import com.github.pagehelper.Page;
import com.zhk.zhkopencart.po.Customer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface CustomerMapper {
    int deleteByPrimaryKey(Integer customerId);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer customerId);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

    Page<Customer> getCustomerList(@Param("userName") String userName, @Param("email")String email, @Param("status")Integer status, @Param("createTime") Date createTime);
}