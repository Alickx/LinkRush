package com.alickx.linkrush.api.manager;

import com.alibaba.fastjson2.JSON;
import com.alickx.linkrush.api.constant.RedisKeyConstant;
import com.alickx.linkrush.api.dto.LinkInfoDTO;
import com.alickx.linkrush.common.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class LinkManagerService {

    private final RedisUtil redisUtil;

    @Transactional(rollbackFor = Exception.class)
    public void saveLinkInfo(LinkInfoDTO linkInfoDTO) {

        // cache 转换成json直接用string结构存储
        String jsonString = JSON.toJSONString(linkInfoDTO);
        long seconds = LocalDateTime.now().until(linkInfoDTO.getExpirationDate(), ChronoUnit.SECONDS);
        redisUtil.setEx(RedisKeyConstant.LINK_CACHE_PREFIX + linkInfoDTO.getLinkShareCode(), jsonString, seconds, TimeUnit.SECONDS);
    }
}
