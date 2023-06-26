package com.alickx.linkrush.api.mapper;

import com.alickx.linkrush.api.domain.SystemConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface SystemConfigMapper extends BaseMapper<SystemConfig> {

    String getSystemConfigValue(@Param("configName") String configName);

}
