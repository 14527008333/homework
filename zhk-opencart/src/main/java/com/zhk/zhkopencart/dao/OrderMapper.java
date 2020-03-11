package com.zhk.zhkopencart.dao;

import com.github.pagehelper.Page;
import com.zhk.zhkopencart.dto.out.OrderListDTO;
import com.zhk.zhkopencart.po.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface OrderMapper {
    int deleteByPrimaryKey(Long orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    Page<OrderListDTO> getOrderList(@Param("customerName") String customerName, @Param("status")Integer status,
                                    @Param("totalPrice")Double totalPrice, @Param("createTime") Date createTime,
                                    @Param("updateTime")Date updateTime);
}