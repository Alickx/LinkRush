package com.alickx.linkrush.api.service.impl;

import com.alickx.linkrush.api.constant.RedisKeyConstant;
import com.alickx.linkrush.api.service.LinkShareService;
import com.alickx.linkrush.common.util.RedisUtil;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class LinkShareServiceImpl implements LinkShareService {

    private final RedisUtil redisUtil;

    private static final Set<Character> CHAR_LIST = new HashSet<>();

    @PostConstruct
    public void initCharList() {

        // 添加数字和字母到列表中
        for (int i = 0; i < 10; i++) {
            CHAR_LIST.add((char)('0' + i));  // 添加数字 '0' 到 '9'
        }

        for (int i = 0; i < 26; i++) {
            CHAR_LIST.add((char)('A' + i));  // 添加大写字母 'A' 到 'Z'
            CHAR_LIST.add((char)('a' + i));  // 添加小写字母 'a' 到 'z'
        }

    }

    @Override
    public String createLinkShareCode() {

        List<Character> characters = new ArrayList<>(CHAR_LIST);

        String shareCode;

        while (true) {
            // 洗牌算法
            Collections.shuffle(characters);

            // 从洗牌后的列表中取前六个字符
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < 6; i++) {
                result.append(characters.get(i));
            }
            String code = result.toString();

            if (redisUtil.get(RedisKeyConstant.LINK_CACHE_PREFIX + code) == null) {
                shareCode = code;
                break;
            }
        }

        return shareCode;
    }

    @Override
    public boolean isValidLinkShareCode(String linkShareCode) {

        // 分享码只有六位
        if (linkShareCode.length() != 6) {
            return false;
        }

        // 分享码只由数字和字母组成
        for (int i = 0; i < 6; i++) {
            char c = linkShareCode.charAt(i);
            if (!CHAR_LIST.contains(c)) {
                return false;
            }
        }

        return  true;

    }

}
