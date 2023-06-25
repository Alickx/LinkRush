package com.alickx.linkrush.api.service;

import com.alickx.linkrush.api.dto.LinkInfoDTO;
import com.alickx.linkrush.api.vo.LinkCreateVO;

public interface LinkService {

    LinkInfoDTO linkCreate(LinkCreateVO linkCreateVO);

    LinkInfoDTO queryLinkInfo(String linkShareCode);
}
