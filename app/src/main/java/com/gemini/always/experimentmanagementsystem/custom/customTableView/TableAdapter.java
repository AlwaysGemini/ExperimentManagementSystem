package com.gemini.always.experimentmanagementsystem.custom.customTableView;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gemini.always.experimentmanagementsystem.R;
import com.gemini.always.experimentmanagementsystem.util.DensityUtils;
import com.xuexiang.xui.widget.textview.autofit.AutoFitTextView;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.gemini.always.experimentmanagementsystem.util.ReflectUtil.invokeGet;

/**
 * @Author: 周清
 * @Description:
 * @Date: Created in 14:35 2020/2/9
 */
public class TableAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {

    private Context context;
    private Class clazz;
    private List<T> dataList;
    private boolean isDisplayCheckBox = false;
    private CheckBox headCheckBox;
    private int HEAD_CHECKBOX = 1;
    private int HEAD_CHECKBOX_CONTAINER = 0;
    private int width = 150;
    private int height = 25;
    /**
     * 防止Checkbox错乱 做setTag  getTag操作
     */
    private SparseBooleanArray mCheckStates = new SparseBooleanArray();

    public TableAdapter(Context context, int layoutResId, @Nullable List<T> data, Class clazz) {
        super(layoutResId, data);
        this.context = context;
        this.clazz = clazz;
        this.dataList = data;
    }

    @Override
    protected void convert(final BaseViewHolder helper, Object item) {
        int position = helper.getAdapterPosition();
        Field[] fields = clazz.getDeclaredFields();
        LinearLayout[] cell = new LinearLayout[fields.length];
        LinearLayout mContainer = helper.getView(R.id.item);
        //LinearLayout.LayoutParams LP_MM = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, DensityUtils.dip2px(height));
        //mContainer.setLayoutParams(LP_MM);
        mContainer.setTag(position);
        mContainer.removeAllViews();
        LinearLayout checkBoxContainer = new LinearLayout(context);
        checkBoxContainer.setTag(position);
        checkBoxContainer.setBackground(context.getDrawable(R.drawable.linearlayout_border));
        LinearLayout.LayoutParams LP_MM = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        checkBoxContainer.setLayoutParams(LP_MM);
        CheckBox checkBox = new CheckBox(context);
        checkBox.setTag(position);
        checkBox.setChecked(mCheckStates.get(position, false));
        checkBox.setGravity(Gravity.CENTER);
        checkBoxContainer.addView(checkBox);
        mContainer.addView(checkBoxContainer);
        if (isDisplayCheckBox) {
            this.getHeaderLayout().findViewById(HEAD_CHECKBOX_CONTAINER).setVisibility(View.VISIBLE);
            CheckBox headerCheckBox = getHeaderLayout().findViewById(HEAD_CHECKBOX);
            headerCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        for (int i = 1; i <= dataList.size(); i++) {
                            mCheckStates.put(i, true);
                        }
                    } else {
                        for (int i = 1; i <= dataList.size(); i++) {
                            mCheckStates.delete(i);
                        }
                    }
                    notifyDataSetChanged();
                }
            });
            checkBox.setVisibility(View.VISIBLE);
            checkBox.setChecked(mCheckStates.get(helper.getAdapterPosition(), false));
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    int pos = (int) compoundButton.getTag();
                    if (b) {
                        mCheckStates.put(pos, true);
                    } else {
                        mCheckStates.delete(pos);
                    }
                }
            });
        } else {
            this.getHeaderLayout().findViewById(HEAD_CHECKBOX_CONTAINER).setVisibility(View.GONE);
            checkBox.setVisibility(View.GONE);
            //取消掉Checkbox后不再保存当前选择的状态
            checkBox.setChecked(false);
            mCheckStates.clear();
        }
        int columnNum = 0;
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            Annotation fieldAnnotation = field.getAnnotation(TableColumn.class);
            if (fieldAnnotation != null) {
                columnNum++;
            }
        }
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            Annotation fieldAnnotation = field.getAnnotation(TableColumn.class);
            if (fieldAnnotation != null) {
                TableColumn smartColumn = (TableColumn) fieldAnnotation;
                int sequence = smartColumn.id() - 1;
                cell[sequence] = new LinearLayout(context); // 线性布局方式
                cell[sequence].setTag(sequence);
                cell[sequence].setOrientation(LinearLayout.HORIZONTAL); //
                cell[sequence].setGravity(Gravity.CENTER);
                cell[sequence].setBackground(context.getDrawable(R.drawable.linearlayout_border));
                LP_MM = new LinearLayout.LayoutParams(DensityUtils.dip2px(width), LinearLayout.LayoutParams.MATCH_PARENT);
                cell[sequence].setLayoutParams(LP_MM);
                final AutoFitTextView textView = new AutoFitTextView(context);
                textView.setSingleLine(true);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(DensityUtils.dip2px(10), 0, DensityUtils.dip2px(10), 0);//4个参数按顺序分别是左上右下
                textView.setLayoutParams(layoutParams);
                textView.setText((String) invokeGet(item, field.getName()));
                cell[sequence].addView(textView);
            }
        }
        for (int i = 0; i < columnNum; i++) {
            mContainer.addView(cell[i]);
        }
    }

    public void addHeaderView() {
        LinearLayout mContainer = new LinearLayout(context);
        LinearLayout.LayoutParams LP_MM = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, DensityUtils.dip2px(height));
        mContainer.setLayoutParams(LP_MM);
        LinearLayout checkBoxContainer = new LinearLayout(context);
        checkBoxContainer.setId(HEAD_CHECKBOX_CONTAINER);
        checkBoxContainer.setBackground(context.getDrawable(R.drawable.linearlayout_border));
        LP_MM = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        checkBoxContainer.setLayoutParams(LP_MM);
        headCheckBox = new CheckBox(context);
        headCheckBox.setId(HEAD_CHECKBOX);
        headCheckBox.setGravity(Gravity.CENTER);
        checkBoxContainer.addView(headCheckBox);
        mContainer.addView(checkBoxContainer);
        checkBoxContainer.setVisibility(View.GONE);
        Field[] fields = clazz.getDeclaredFields();
        int columnNum = 0;
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            Annotation fieldAnnotation = field.getAnnotation(TableColumn.class);
            if (fieldAnnotation != null) {
                columnNum++;
            }
        }
        LinearLayout[] cell = new LinearLayout[columnNum];
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            Annotation fieldAnnotation = field.getAnnotation(TableColumn.class);
            if (fieldAnnotation != null) {
                TableColumn smartColumn = (TableColumn) fieldAnnotation;
                int sequence = smartColumn.id() - 1;
                cell[sequence] = new LinearLayout(context); // 线性布局方式
                cell[sequence].setOrientation(LinearLayout.HORIZONTAL); //
                cell[sequence].setGravity(Gravity.CENTER);
                cell[sequence].setBackground(context.getDrawable(R.drawable.linearlayout_border));
                LP_MM = new LinearLayout.LayoutParams(DensityUtils.dip2px(width), LinearLayout.LayoutParams.MATCH_PARENT);
                cell[sequence].setLayoutParams(LP_MM);
                final AutoFitTextView textView = new AutoFitTextView(context);
                textView.setSingleLine(true);
                textView.setText(smartColumn.name());
                cell[sequence].addView(textView);
            }
        }
        for (int i = 0; i < columnNum; i++) {
            mContainer.addView(cell[i]);
        }
        super.addHeaderView(mContainer);
    }

    public void showCheckBox(boolean isDisplay) {
        this.isDisplayCheckBox = isDisplay;
        notifyDataSetChanged();
    }

    public boolean getIsShowCheckBox() {
        return this.isDisplayCheckBox;
    }

    public void setCheckedPosition(int position, boolean isChecked) {
        mCheckStates.put(position + 1, isChecked);
        notifyDataSetChanged();
    }

    public boolean getIsCheckPosition(int position) {
        return mCheckStates.get(position + 1, false);
    }

    public void setHeadIsChecked(Boolean isChecked) {
        this.headCheckBox.setChecked(isChecked);
    }

    public List<Integer> getCheckedList() {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= dataList.size(); i++) {
            if (mCheckStates.get(i, false))
                list.add(i);
        }
        return list;
    }
}
