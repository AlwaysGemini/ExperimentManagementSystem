package com.gemini.always.experimentmanagementsystem;

/**
 * 页面枚举
 * @since 2020/1/23 下午2:23
 */
public enum ContentPage {

    实验教学中心(0),
    实验室(1),
    实验分室(2),
    实验房间(3);

    private final int position;

    ContentPage(int pos) {
        position = pos;
    }

    public static ContentPage getPage(int position) {
       return ContentPage.values()[position];
    }

    public static int size() {
       return ContentPage.values().length;
    }

    public static String[] getPageNames() {
        ContentPage[] pages = ContentPage.values();
        String[] pageNames = new String[pages.length];
        for (int i = 0; i < pages.length; i++) {
            pageNames[i] = pages[i].name();
        }
        return pageNames;
    }

    public int getPosition() {
        return position;
    }
}
