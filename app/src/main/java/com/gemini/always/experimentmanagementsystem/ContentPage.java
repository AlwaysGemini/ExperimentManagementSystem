/*
 * Copyright (C) 2018 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gemini.always.experimentmanagementsystem;

/**
 * 页面枚举
 * @author xuexiang
 * @since 2018/12/26 下午2:23
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
