package com.gemini.always.experimentmanagementsystem.custom.customDialog;

import com.xuexiang.xui.widget.spinner.materialspinner.MaterialSpinner;

import java.util.List;

/**
 * @version V1.0
 * @Title:
 * @ClassName: com.gemini.always.experimentmanagementsystem.custom.ustomDialog.DialogListEditTextItem.java
 * @Description: 自定义的Dialog中的Spinner
 * @author: 周清
 * @date: 2020-02-07 21:43
 */
public class DialogListSpinnerItem {
    private String text;
    private MaterialSpinner spinner;
    private List<String> spinnerDataList;
    private String selected;

    public DialogListSpinnerItem(String text, List<String> spinnerDataList) {
        this.text = text;
        this.spinnerDataList = spinnerDataList;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MaterialSpinner getSpinner() {
        return spinner;
    }

    public void setSpinner(MaterialSpinner spinner) {
        this.spinner = spinner;
    }

    public List<String> getSpinnerDataList() {
        return spinnerDataList;
    }

    public void setSpinnerDataList(List<String> spinnerDataList) {
        this.spinnerDataList = spinnerDataList;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }
}
