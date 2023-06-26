package com.alickx.linkrush.api.service;

import com.alickx.linkrush.api.dto.LinkInfoDTO;
import com.alickx.linkrush.api.vo.LinkCreateVO;
import jakarta.servlet.http.HttpServletRequest;

public interface LinkService {

    LinkInfoDTO linkCreate(LinkCreateVO linkCreateVO);

    LinkInfoDTO queryLinkInfo(String linkShareCode, HttpServletRequest request);
}
