package com.alickx.linkrush.api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LinkInfoDTO {

    private String targetLink;

    private String shortLink;

    private String linkShareCode;

    private LocalDateTime expirationDate;

}
