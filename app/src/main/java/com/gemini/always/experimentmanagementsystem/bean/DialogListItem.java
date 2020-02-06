package com.gemini.always.experimentmanagementsystem.bean;

import android.widget.EditText;

import com.xuexiang.xui.widget.spinner.materialspinner.MaterialSpinner;

import java.util.List;

public class DialogListItem {
    private String text;
    private EditText editText;
    private String editedText;
    private MaterialSpinner spinner;
    private String selected;
    private List<String> dataList;
    private int type;

    public DialogListItem(String text, EditText editText) {
        this.text = text;
        this.editText = editText;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public EditText getEditText() {
        return editText;
    }

    public void setEditText(EditText editText) {
        this.editText = editText;
    }

    public String getEditedText() {
        return editedText;
    }

    public void setEditedText(String editedText) {
        this.editedText = editedText;
    }

    public MaterialSpinner getSpinner() {
        return spinner;
    }

    public void setSpinner(MaterialSpinner spinner) {
        this.spinner = spinner;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public List<String> getDataList() {
        return dataList;
    }

    public void setDataList(List<String> dataList) {
        this.dataList = dataList;
    }
}
