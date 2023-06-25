package com.alickx.linkrush.handler.service.impl;

import com.alickx.linkrush.api.dto.LinkInfoDTO;
import com.alickx.linkrush.handler.convert.LinkInfoConvert;
import com.alickx.linkrush.handler.service.LinkInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alickx.linkrush.handler.domain.LinkInfo;
import com.alickx.linkrush.handler.mapper.LinkInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author alick
* @description 针对表【link_info】的数据库操作Service实现
* @createDate 2023-06-25 13:50:17
*/
@Service
public class LinkInfoServiceImpl extends ServiceImpl<LinkInfoMapper, LinkInfo>
    implements LinkInfoService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveLinkInfo(LinkInfoDTO linkInfoDTO) {
        LinkInfo linkInfo = LinkInfoConvert.INSTANCE.dtoToPo(linkInfoDTO);
        save(linkInfo);
    }
}




