package com.alickx.linkrush.api.sender;

import com.alibaba.fastjson2.JSON;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LinkSender {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, Object object) {
        kafkaTemplate.send(topic, JSON.toJSONString(object));
    }

}
