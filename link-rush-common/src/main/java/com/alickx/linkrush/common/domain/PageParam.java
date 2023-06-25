package com.alickx.linkrush.common.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PageParam {

    @Min(value = 1)
    @NotNull(message = "page不能为空")
    private Integer page;

    @NotNull(message = "size不能为空")
    private Integer size;

}
