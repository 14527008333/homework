package com.zhk.zhkopencartstore.dto.out;

import lombok.Data;

import java.util.List;

@Data
public class PageOutDTO<T> {
    private long total;
    private Integer pageNum;
    private Integer pageSize;
    private List<T> list;
}
