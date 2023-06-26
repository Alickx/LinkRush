package com.alickx.linkrush.api.util;

import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.HttpServletRequest;

public class RequestUtil {

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (StrUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StrUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StrUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String getBrowser(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (StrUtil.isBlank(userAgent)) {
            return "unknown";
        }
        if (userAgent.contains("MSIE")) {
            return "MSIE";
        }
        if (userAgent.contains("Firefox")) {
            return "Firefox";
        }
        if (userAgent.contains("Chrome")) {
            return "Chrome";
        }
        if (userAgent.contains("Safari")) {
            return "Safari";
        }
        if (userAgent.contains("Camino")) {
            return "Camino";
        }
        if (userAgent.contains("Konqueror")) {
            return "Konqueror";
        }
        if (userAgent.contains("Opera")) {
            return "Opera";
        }
        return "unknown";
    }

    public static String getOs(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (StrUtil.isBlank(userAgent)) {
            return "unknown";
        }
        if (userAgent.contains("Windows")) {
            return "Windows";
        }
        if (userAgent.contains("Mac")) {
            return "Mac";
        }
        if (userAgent.contains("Linux")) {
            return "Linux";
        }
        if (userAgent.contains("Android")) {
            return "Android";
        }
        if (userAgent.contains("iPhone")) {
            return "iPhone";
        }
        return "unknown";
    }

    public static String getDevice(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (StrUtil.isBlank(userAgent)) {
            return "unknown";
        }
        if (userAgent.contains("Windows")) {
            return "PC";
        }
        if (userAgent.contains("Mac")) {
            return "PC";
        }
        if (userAgent.contains("Linux")) {
            return "PC";
        }
        if (userAgent.contains("Android")) {
            return "Mobile";
        }
        if (userAgent.contains("iPhone")) {
            return "Mobile";
        }
        return "unknown";
    }

    public static String getReferer(HttpServletRequest request) {
        String referer = request.getHeader("Referer");
        if (StrUtil.isBlank(referer)) {
            return "unknown";
        }
        return referer;
    }



}
