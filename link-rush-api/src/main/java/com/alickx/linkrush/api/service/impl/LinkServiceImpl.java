package com.alickx.linkrush.api.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alickx.linkrush.api.constant.CommonConstant;
import com.alickx.linkrush.api.constant.KafkaTopicConstant;
import com.alickx.linkrush.api.constant.RedisKeyConstant;
import com.alickx.linkrush.api.constant.enums.LinkExpireTypeEnum;
import com.alickx.linkrush.api.dto.LinkInfoDTO;
import com.alickx.linkrush.api.manager.LinkManagerService;
import com.alickx.linkrush.api.sender.LinkSender;
import com.alickx.linkrush.api.service.LinkService;
import com.alickx.linkrush.api.service.LinkShareService;
import com.alickx.linkrush.api.util.BlackListUtil;
import com.alickx.linkrush.api.vo.LinkCreateVO;
import com.alickx.linkrush.common.exception.BusinessException;
import com.alickx.linkrush.common.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class LinkServiceImpl implements LinkService {

    private final LinkShareService linkShareService;

    private final LinkManagerService linkManagerService;

    private final RedisUtil redisUtil;

    private final LinkSender linkSender;

    private static final Integer MAX_LINK_COUNT = 3;

    @Override
    public LinkInfoDTO linkCreate(LinkCreateVO linkCreateVO) {

        String targetLink = linkCreateVO.getTargetLink();
        Integer expireType = linkCreateVO.getExpireType();

        LinkExpireTypeEnum expireTypeEnum = LinkExpireTypeEnum.getByCode(expireType);
        if (expireTypeEnum == null) {
            throw new BusinessException("过期类型非法");
        }

        // 检测目标链接是否为合法链接，链接格式是否正确等
        if (!this.validation(targetLink)) {
            throw new BusinessException("目标链接或参数不合法");
        }

        // 检查该域名共有创建了多少个短链，上限是3个


        // 生成分享码
        String linkShareCode = linkShareService.createLinkShareCode();


        // 保存到缓存
        String shortLink = this.getCurrentDomain() + linkShareCode;
        Long expireTime = expireTypeEnum.getExpireTime();
        LocalDateTime expirationDate = LocalDateTime.now().plusSeconds(expireTime);

        LinkInfoDTO linkInfoDTO = new LinkInfoDTO();
        linkInfoDTO.setTargetLink(targetLink);
        linkInfoDTO.setShortLink(shortLink);
        linkInfoDTO.setLinkShareCode(linkShareCode);
        linkInfoDTO.setExpirationDate(expirationDate);

        linkManagerService.saveLinkInfo(linkInfoDTO);

        // 发送消息队列
        linkSender.send(KafkaTopicConstant.LINK_PERSISTENCE_TOPIC,linkInfoDTO);
        return linkInfoDTO;

    }

    @Override
    public LinkInfoDTO queryLinkInfo(String linkShareCode) {

        // 检查是否为合法的分享码
        if (!linkShareService.isValidLinkShareCode(linkShareCode)) {
            throw new BusinessException("分享码不合法");
        }

        // 从缓存中获取链接信息
        String jsonString = redisUtil.get(RedisKeyConstant.LINK_CACHE_PREFIX + linkShareCode);
        if (StrUtil.isBlank(jsonString)) {
            throw new BusinessException("分享码不存在或已过期");
        }

        LinkInfoDTO linkInfoDTO = JSON.parseObject(jsonString, LinkInfoDTO.class);
        linkSender.send(KafkaTopicConstant.LINK_QUERY_EVENT_TOPIC,linkInfoDTO);

        return linkInfoDTO;

    }

    private Boolean validation(String targetLink) {

        // 检查链接是否为正确的链接格式
        if (!this.isValidFormat(targetLink)) {
            log.error("目标链接不合法，链接格式不正确，targetLink:{}", targetLink);
            return false;
        }

        // 检查是否存在于黑名单文件中
        if (BlackListUtil.isExist(targetLink)) {
            log.error("目标链接不合法，存在于黑名单中，targetLink:{}", targetLink);
            return false;
        }

        return true;

    }

    private Boolean isValidFormat(String targetLink) {
        // 检查链接格式是否正确
        return targetLink.matches(CommonConstant.LINK_FORMAT_REGEX);
    }


    private String getCurrentDomain() {
        return "https://linkrush.com/";
    }


}
