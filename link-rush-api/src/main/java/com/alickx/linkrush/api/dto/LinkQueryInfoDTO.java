package com.alickx.linkrush.api.dto;

import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Data
public class LinkQueryInfoDTO {

    private String targetLink;

    private String linkShareCode;

    private String shortLink;

    private LocalDateTime expireDate;

    private String os;

    private String browser;

    private String ip;

    private String region;

    private String referer;

    private String device;

    private LocalDateTime queryTime;

}
