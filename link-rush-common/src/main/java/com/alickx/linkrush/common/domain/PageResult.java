package com.alickx.linkrush.common.domain;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {

    private List<T> records;

    private Long total;

}
