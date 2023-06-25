package com.alickx.linkrush.handler.service;

import com.alickx.linkrush.api.dto.LinkInfoDTO;
import com.alickx.linkrush.handler.domain.LinkInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author alick
* @description 针对表【link_info】的数据库操作Service
* @createDate 2023-06-25 13:50:17
*/
public interface LinkInfoService extends IService<LinkInfo> {

    void saveLinkInfo(LinkInfoDTO linkInfoDTO);

}
