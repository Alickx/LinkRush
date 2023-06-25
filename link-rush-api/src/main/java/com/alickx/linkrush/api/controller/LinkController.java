package com.alickx.linkrush.api.controller;

import com.alickx.linkrush.common.domain.R;
import com.alickx.linkrush.api.dto.LinkInfoDTO;
import com.alickx.linkrush.api.service.LinkService;
import com.alickx.linkrush.api.vo.LinkCreateVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/link")
@RequiredArgsConstructor
public class LinkController {

    private final LinkService linkService;

    @PostMapping("/create")
    public R<LinkInfoDTO> linkCreate(@RequestBody LinkCreateVO linkCreateVO) {
        return R.ok(linkService.linkCreate(linkCreateVO));
    }

    @GetMapping("/query")
    public R<LinkInfoDTO> linkInfo(@RequestParam("linkShareCode") String linkShareCode) {
        return R.ok(linkService.queryLinkInfo(linkShareCode));
    }


}
