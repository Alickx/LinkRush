package com.alickx.linkrush.api.service.impl;

import com.alickx.linkrush.api.mapper.SystemConfigMapper;
import com.alickx.linkrush.api.service.SystemConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SystemConfigServiceImpl implements SystemConfigService {

    private final SystemConfigMapper systemConfigMapper;


    @Override
    @Cacheable(value = "systemConfig", key = "'sysDomain'")
    public String getSysDomain() {
        return systemConfigMapper.getSystemConfigValue("sys_domain");
    }
}
