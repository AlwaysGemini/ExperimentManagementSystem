package com.gemini.always.experimentmanagementsystem.custom.customTableView;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.View;
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
import java.util.Arrays;
import java.util.List;

import static com.gemini.always.experimentmanagementsystem.util.ReflectUtil.invokeGet;

/**
 * @Author: 周清
 * @Description:
 * @Date: Created in 14:35 2020/2/9
 */
public class TableAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {

    private static int MultipleSelection = 1;
    private static int SingleSelection = 2;

    private Context context;
    private Class clazz;
    private List<T> dataList;
    private boolean isDisplayCheckBox = false;
    private CheckBox headCheckBox;
    private int HEAD_CHECKBOX = 1;
    private int HEAD_CHECKBOX_CONTAINER = 0;
    private int width = 150;
    private int height = 25;
    private int selectMode = MultipleSelection;

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

        LinearLayout mContainer = helper.getView(R.id.item);
        mContainer.setTag(position);
        mContainer.removeAllViews();

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
        String[] rowData = new String[columnNum];
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            Annotation fieldAnnotation = field.getAnnotation(TableColumn.class);
            if (fieldAnnotation != null) {
                TableColumn smartColumn = (TableColumn) fieldAnnotation;
                int sequence = smartColumn.id() - 1;
                rowData[sequence] = (String) invokeGet(item, field.getName());
            }
        }
        mContainer.addView(createRow(position, Arrays.asList(rowData)));
    }

    public void addHeaderView() {
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
        String[] rowData = new String[columnNum];
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            Annotation fieldAnnotation = field.getAnnotation(TableColumn.class);
            if (fieldAnnotation != null) {
                TableColumn smartColumn = (TableColumn) fieldAnnotation;
                int sequence = smartColumn.id() - 1;
                rowData[sequence] = smartColumn.name();
            }
        }
        super.addHeaderView(createHeadRow(Arrays.asList(rowData)));
    }

    private CheckBox createCheckBox(int position) {
        CheckBox checkBox = new CheckBox(context);
        checkBox.setTag(position);
        checkBox.setChecked(mCheckStates.get(position, false));
        checkBox.setGravity(Gravity.CENTER);
        if (isDisplayCheckBox) {
            this.getHeaderLayout().findViewById(HEAD_CHECKBOX_CONTAINER).setVisibility(View.VISIBLE);
            CheckBox headerCheckBox = getHeaderLayout().findViewById(HEAD_CHECKBOX);
            headerCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (selectMode == MultipleSelection) {
                        if (b) {
                            for (int i = 1; i <= dataList.size(); i++) {
                                mCheckStates.put(i, true);
                            }
                        } else {
                            for (int i = 1; i <= dataList.size(); i++) {
                                mCheckStates.delete(i);
                            }
                        }
                    } else if (selectMode == SingleSelection) {
                        if (b) {
                            for (int i = 1; i <= dataList.size(); i++) {
                                if (i != position) {
                                    mCheckStates.delete(i);
                                } else {
                                    mCheckStates.put(i, true);
                                }
                            }
                        }
                    }
                    notifyDataSetChanged();
                }
            });
            checkBox.setVisibility(View.VISIBLE);
            checkBox.setChecked(mCheckStates.get(position, false));
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    int pos = (int) compoundButton.getTag();
                    if (selectMode == MultipleSelection) {
                        if (b) {
                            for (int i = 1; i <= dataList.size(); i++) {
                                mCheckStates.put(i, true);
                            }
                        } else {
                            for (int i = 1; i <= dataList.size(); i++) {
                                mCheckStates.delete(i);
                            }
                        }
                    } else if (selectMode == SingleSelection) {
                        if (b) {
                            for (int i = 1; i <= dataList.size(); i++) {
                                if (i != pos) {
                                    mCheckStates.delete(i);
                                } else {
                                    mCheckStates.put(i, true);
                                }
                            }
                        } else {
                            mCheckStates.delete(pos);
                        }
                    }
                    notifyDataSetChanged();
                }
            });
        } else {
            this.getHeaderLayout().findViewById(HEAD_CHECKBOX_CONTAINER).setVisibility(View.GONE);
            checkBox.setVisibility(View.GONE);
            //取消掉Checkbox后不再保存当前选择的状态
            checkBox.setChecked(false);
            mCheckStates.clear();
        }
        return checkBox;
    }

    private LinearLayout createRow(int position, List<String> data) {
        LinearLayout rowContainer = new LinearLayout(context);
        rowContainer.setTag(position);
        LinearLayout.LayoutParams LP_MM = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, DensityUtils.dip2px(height));
        rowContainer.setLayoutParams(LP_MM);

        LinearLayout checkBoxContainer = new LinearLayout(context);
        checkBoxContainer.setTag(position);
        checkBoxContainer.setBackground(context.getDrawable(R.drawable.linearlayout_border));
        LP_MM = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        checkBoxContainer.setLayoutParams(LP_MM);

        checkBoxContainer.addView(createCheckBox(position));
        rowContainer.addView(checkBoxContainer);

        for (String string : data) {
            LinearLayout cell = new LinearLayout(context);
            cell.setOrientation(LinearLayout.HORIZONTAL); //
            cell.setGravity(Gravity.CENTER);
            cell.setBackground(context.getDrawable(R.drawable.linearlayout_border));
            LP_MM = new LinearLayout.LayoutParams(DensityUtils.dip2px(width), LinearLayout.LayoutParams.MATCH_PARENT);
            cell.setLayoutParams(LP_MM);
            final AutoFitTextView textView = new AutoFitTextView(context);
            textView.setSingleLine(true);
            textView.setText(string);
            cell.addView(textView);
            rowContainer.addView(cell);
        }
        return rowContainer;
    }

    private LinearLayout createHeadRow(List<String> data) {
        LinearLayout rowContainer = new LinearLayout(context);
        LinearLayout.LayoutParams LP_MM = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, DensityUtils.dip2px(height));
        rowContainer.setLayoutParams(LP_MM);
        LinearLayout checkBoxContainer = new LinearLayout(context);
        checkBoxContainer.setId(HEAD_CHECKBOX_CONTAINER);
        checkBoxContainer.setBackground(context.getDrawable(R.drawable.linearlayout_border));
        LP_MM = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        checkBoxContainer.setLayoutParams(LP_MM);
        headCheckBox = new CheckBox(context);
        headCheckBox.setId(HEAD_CHECKBOX);
        headCheckBox.setGravity(Gravity.CENTER);
        checkBoxContainer.addView(headCheckBox);
        rowContainer.addView(checkBoxContainer);
        checkBoxContainer.setVisibility(View.GONE);

        for (String string : data) {
            LinearLayout cell = new LinearLayout(context);
            cell.setOrientation(LinearLayout.HORIZONTAL); //
            cell.setGravity(Gravity.CENTER);
            cell.setBackground(context.getDrawable(R.drawable.linearlayout_border));
            LP_MM = new LinearLayout.LayoutParams(DensityUtils.dip2px(width), LinearLayout.LayoutParams.MATCH_PARENT);
            cell.setLayoutParams(LP_MM);
            final AutoFitTextView textView = new AutoFitTextView(context);
            textView.setSingleLine(true);
            textView.setText(string);
            cell.addView(textView);
            rowContainer.addView(cell);
        }
        return rowContainer;
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
