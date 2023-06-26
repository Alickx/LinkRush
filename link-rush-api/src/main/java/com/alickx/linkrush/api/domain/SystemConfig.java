package com.alickx.linkrush.api.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class SystemConfig {

    @TableId
    private Long id;

    private String configName;

    private String configValue;

}
