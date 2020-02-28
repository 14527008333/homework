package com.zhk.zhkopencart.dto.out;

import lombok.Data;

import java.util.List;

@Data
public class PageDTO<T> {
    private long total;
    private Integer pageNum;
    private Integer pageSize;
    private List<T> list;
}
