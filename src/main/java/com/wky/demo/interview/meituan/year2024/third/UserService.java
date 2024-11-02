package com.wky.demo.interview.meituan.year2024.third;

import java.util.HashMap;
import java.util.Map;

public class UserService {

    public static final int PAGE_SOURCE_A = 1;
    public static final int PAGE_SOURCE_B = 2;

    private static final Map<Integer, PageDimension> PAGE_DIMENSION_MAP = new HashMap<>();

    static {
        PAGE_DIMENSION_MAP.put(PAGE_SOURCE_A, new PageDimension(1, 1));
        PAGE_DIMENSION_MAP.put(PAGE_SOURCE_B, new PageDimension(2, 2));
    }

    /**
     * 返回尺寸为 height * width 的头像 url
     * 可直接使用
     */
    private static String getUserPicWithTargetSize(int height, int width) {
        return String.format("http://userpic_mock h %d w %d", height, width);
    }

    /**
     * 返回页面对应尺寸的头像 url
     */
    private String getUserPicForPage(int pageSource) {
        PageDimension dimension = PAGE_DIMENSION_MAP.get(pageSource);
        if (dimension == null) {
            throw new IllegalArgumentException("Invalid page source: " + pageSource);
        }
        return getUserPicWithTargetSize(dimension.getHeight(), dimension.getWidth());
    }

    // 封装页面尺寸
    private static class PageDimension {
        private final int height;
        private final int width;

        public PageDimension(int height, int width) {
            this.height = height;
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public int getWidth() {
            return width;
        }
    }

    public static void main(String[] args) {
        UserService userService = new UserService();
        System.out.println(userService.getUserPicForPage(UserService.PAGE_SOURCE_A));
        System.out.println(userService.getUserPicForPage(UserService.PAGE_SOURCE_B));
    }

}