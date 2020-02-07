package com.gemini.always.experimentmanagementsystem.custom;

import android.widget.EditText;

public class DialogListEditTextItem {
    private String text;
    private EditText editText;
    private String hint;
    private String edited;

    public DialogListEditTextItem(String text,String hint){
        this.text = text;
        this.hint = hint;
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

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getEdited() {
        return edited;
    }

    public void setEdited(String edited) {
        this.edited = edited;
    }
}
