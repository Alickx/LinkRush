package com.alickx.linkrush.api.service;

public interface LinkShareService {

    String createLinkShareCode();

    boolean isValidLinkShareCode(String linkShareCode);
}
