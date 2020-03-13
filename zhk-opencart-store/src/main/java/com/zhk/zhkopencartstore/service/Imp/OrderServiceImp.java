package com.zhk.zhkopencartstore.service.Imp;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhk.zhkopencartstore.dao.*;
import com.zhk.zhkopencartstore.dto.in.OrderProductInDTO;
import com.zhk.zhkopencartstore.dto.in.OrdercheckoutInDTO;
import com.zhk.zhkopencartstore.dto.out.OrderHistoryListOutDTO;
import com.zhk.zhkopencartstore.dto.out.OrderShowOutDTO;
import com.zhk.zhkopencartstore.dto.out.ProductShowDTO;
import com.zhk.zhkopencartstore.enums.OrderStatusEnum;
import com.zhk.zhkopencartstore.po.Address;
import com.zhk.zhkopencartstore.po.Order;
import com.zhk.zhkopencartstore.po.OrderDetail;
import com.zhk.zhkopencartstore.po.OrderHistory;
import com.zhk.zhkopencartstore.service.OrderService;
import com.zhk.zhkopencartstore.service.ProductService;
import com.zhk.zhkopencartstore.vo.OrderProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private ProductService productService;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private OrderHistoryMapper orderHistoryMapper;

    @Override
    public Long orderCheckout(OrdercheckoutInDTO ordercheckoutInDTO, Integer customerId) {

        List<OrderProductInDTO> orderProducts = ordercheckoutInDTO.getOrderProducts();

        List<OrderProductVO> orderProductVOS= orderProducts.stream().map(orderProduct -> {
            ProductShowDTO productById = productService.getProductAndProductDetailById(orderProduct.getProductId());
            OrderProductVO orderProductVO = new OrderProductVO();
            orderProductVO.setProductId(productById.getProductId());
            orderProductVO.setProductCode(productById.getProductCode());
            orderProductVO.setProductName(productById.getProductName());
            orderProductVO.setQuantity(orderProduct.getQuantity());
            orderProductVO.setUnitPrice(productById.getPrice());
            orderProductVO.setTotalPrice(productById.getPrice()*orderProduct.getQuantity());
            orderProductVO.setUnitRewordPoints(productById.getRewordPoints());
            orderProductVO.setTotalRewordPoints(productById.getRewordPoints()*orderProduct.getQuantity());
            return orderProductVO;
        }).collect(Collectors.toList());
        Double totalPrice = orderProductVOS.stream().mapToDouble(orderProduct -> orderProduct.getTotalPrice()).sum();
        Integer totalRewordPoints = orderProductVOS.stream().mapToInt(orderProduct -> orderProduct.getTotalRewordPoints()).sum();

        Order order = new Order();
        order.setCustomerId(customerId);
        order.setStatus((byte)OrderStatusEnum.待处理.ordinal());
        order.setTotalPrice(totalPrice);
        order.setRewordPoints(totalRewordPoints);
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        Integer orderId = orderMapper.insertSelective(order);

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(new Long(order.getOrderId()));
        orderDetail.setShipMethod(ordercheckoutInDTO.getShipMethod());
        Address address = addressMapper.selectByPrimaryKey(ordercheckoutInDTO.getShipAddressId());
        orderDetail.setShipAddress(address.getContent());
        orderDetail.setShipPrice(15.5);
        orderDetail.setPayMethod(ordercheckoutInDTO.getPayMethod());
        Address address1 = addressMapper.selectByPrimaryKey(ordercheckoutInDTO.getInvoiceAddressId());
        orderDetail.setInvoiceAddress(address1.getContent());
        orderDetail.setInvoicePrice(totalPrice);
        orderDetail.setComment(ordercheckoutInDTO.getComment());
        orderDetail.setOrderProducts(JSON.toJSONString(orderProductVOS));

        Integer orderDetailId = orderDetailMapper.insertSelective(orderDetail);
        return order.getOrderId();
    }

    @Override
    public Page<Order> getOrderList(Integer pageNum, Integer customerId) {

        PageHelper.startPage(pageNum,3);
        Page<Order> orders= orderMapper.getOrderList(customerId);

        return orders;
    }

    @Override
    public OrderShowOutDTO OrderShowById(Integer orderId) {
        Order order = orderMapper.selectByPrimaryKey(new Long(orderId));
        OrderDetail orderDetail = orderDetailMapper.selectByPrimaryKey(new Long(orderId));
        OrderShowOutDTO orderShowOutDTO = new OrderShowOutDTO();
        orderShowOutDTO.setOrderId(order.getOrderId());
        orderShowOutDTO.setStatus(order.getStatus());
        orderShowOutDTO.setTotalPrice(order.getTotalPrice());
        orderShowOutDTO.setRewordPoints(order.getRewordPoints());
        orderShowOutDTO.setCreateTimestamp(order.getCreateTime().getTime());
        orderShowOutDTO.setUpdateTimestamp(order.getUpdateTime().getTime());
        orderShowOutDTO.setShipMethod(orderDetail.getShipMethod());
        orderShowOutDTO.setShipAddress(orderDetail.getShipAddress());
        orderShowOutDTO.setShipPrice(orderDetail.getShipPrice());
        orderShowOutDTO.setPayMethod(orderDetail.getPayMethod());
        orderShowOutDTO.setInvoiceAddress(orderDetail.getInvoiceAddress());
        orderShowOutDTO.setInvoicePrice(orderDetail.getInvoicePrice());
        orderShowOutDTO.setComment(orderDetail.getComment());
        orderShowOutDTO.setOrderProducts(JSON.parseArray(orderDetail.getOrderProducts(),OrderProductVO.class));

        List<OrderHistory> orderHistories=orderHistoryMapper.getOrderHistoryById(new Long(orderId));

        List<OrderHistoryListOutDTO> orderHistoryListOutDTOS= orderHistories.stream().map(orderHistorie->{
            OrderHistoryListOutDTO orderHistoryListOutDTO = new OrderHistoryListOutDTO();
            orderHistoryListOutDTO.setTimestamp(orderHistorie.getTime().getTime());
            orderHistoryListOutDTO.setOrderStatus(orderHistorie.getOrderStatus());
            orderHistoryListOutDTO.setComment(orderHistorie.getComment());

            return orderHistoryListOutDTO;
        }).collect(Collectors.toList());

        orderShowOutDTO.setOrderHistories(orderHistoryListOutDTOS);
        return orderShowOutDTO;
    }

}
