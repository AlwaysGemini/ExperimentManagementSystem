package com.gemini.always.experimentmanagementsystem.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

import androidx.annotation.ArrayRes;
import androidx.core.content.ContextCompat;

import static com.xuexiang.xui.utils.ResUtils.getResources;

/**
 * @version V1.0
 * @Title:
 * @ClassName: com.gemini.always.experimentmanagementsystem.util.OtherUtils.java
 * @Description:
 * @author: 周清
 * @date: 2020-02-07 21:38
 */
public class OtherUtils {
    /**
     * 获取Drawable的数组
     *
     * @param context
     * @param resId
     * @return
     */
    public static Drawable[] getDrawableArray(Context context, @ArrayRes int resId) {
        TypedArray ta = getResources().obtainTypedArray(resId);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(context, id);
            }
        }
        ta.recycle();
        return icons;
    }
}
