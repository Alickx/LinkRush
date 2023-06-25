package com.alickx.linkrush.handler.receiver;

import com.alibaba.fastjson2.JSON;
import com.alickx.linkrush.api.constant.KafkaTopicConstant;
import com.alickx.linkrush.api.dto.LinkInfoDTO;
import com.alickx.linkrush.handler.service.LinkInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class LinkReceiver {

    private final LinkInfoService linkInfoService;

    @KafkaListener(topics = KafkaTopicConstant.LINK_PERSISTENCE_TOPIC, groupId = KafkaTopicConstant.LINK_PERSISTENCE_GROUP)
    public void onPersistLink(String message) {

        log.info("onPersistLink : {}", message);
        LinkInfoDTO linkInfoDTO = JSON.parseObject(message, LinkInfoDTO.class);
        linkInfoService.saveLinkInfo(linkInfoDTO);
    }

    @KafkaListener(topics = KafkaTopicConstant.LINK_QUERY_EVENT_TOPIC, groupId = KafkaTopicConstant.LINK_QUERY_EVENT_GROUP)
    public void onQueryLink(String message) {

        log.info("QUERY EVENT : {}", message);

    }

}
