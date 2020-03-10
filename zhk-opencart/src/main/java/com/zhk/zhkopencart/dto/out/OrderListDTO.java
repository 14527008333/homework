package com.zhk.zhkopencart.dto.out;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

public class OrderListDTO {

    private Integer orderId;

    private Integer customerId;

    private String customerName;

    private Integer status;

    private Double totalPrice;

    private Integer rewordPoints;

    @JsonIgnore
    private Date createTime;

    private Long createTimetamp;

    @JsonIgnore
    private Date updateTime;

    private Long updateTimetamp;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getRewordPoints() {
        return rewordPoints;
    }

    public void setRewordPoints(Integer rewordPoints) {
        this.rewordPoints = rewordPoints;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateTimetamp() {
        return createTime==null?null:this.createTime.getTime();
    }

    public void setCreateTimetamp(Long createTimetamp) {
        this.createTimetamp = createTimetamp;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateTimetamp() {
       return updateTime==null?null:this.updateTime.getTime();
    }

    public void setUpdateTimetamp(Long updateTimetamp) {
        this.updateTimetamp = updateTimetamp;
    }
}
