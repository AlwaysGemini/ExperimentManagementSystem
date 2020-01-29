package com.gemini.always.experimentmanagementsystem.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import androidx.annotation.ArrayRes;

import static com.xuexiang.xui.utils.ResUtils.getResources;

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
