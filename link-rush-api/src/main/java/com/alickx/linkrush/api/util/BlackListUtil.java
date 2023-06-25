package com.alickx.linkrush.api.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.URLUtil;
import com.alickx.linkrush.api.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class BlackListUtil {

    private static final Set<String> blackList = new HashSet<>();

    static {
        // 读取resource文件夹下的BlackList文件
        boolean exist = FileUtil.exist("BlackList.txt");
        if (exist) {
            blackList.addAll(FileUtil.readUtf8Lines("BlackList.txt"));
        } else {
            log.warn("黑名单文件不存在");
        }
    }

    public static Boolean isExist(String targetLink) {

        // 使用正则表达式获取链接中域名
        URI uri = URLUtil.toURI(targetLink);
        // 获取域名
        String host = uri.getHost();

        String[] split = host.split(CommonConstant.LINK_SPLIT);
        String domain = split[split.length - 2] + CommonConstant.LINK_SPLIT + split[split.length - 1];

        return blackList.contains(domain);
    }

}
