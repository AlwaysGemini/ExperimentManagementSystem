package com.gemini.always.experimentmanagementsystem.custom;

public class DialogListItem {
    public static final int TYPE_SPINNER = 0;
    public static final int TYPE_EDITTEXT = 1;
    private DialogListSpinnerItem spinnerItem;
    private DialogListEditTextItem editTextItem;
    private int type;

    public DialogListItem(DialogListSpinnerItem spinnerItem){
        this.spinnerItem = spinnerItem;
        this.type = TYPE_SPINNER;
    }

    public DialogListItem(DialogListEditTextItem editTextItem){
        this.editTextItem = editTextItem;
        this.type = TYPE_EDITTEXT;
    }

    public DialogListSpinnerItem getSpinnerItem() {
        return spinnerItem;
    }

    public void setSpinnerItem(DialogListSpinnerItem spinnerItem) {
        this.spinnerItem = spinnerItem;
    }

    public DialogListEditTextItem getEditTextItem() {
        return editTextItem;
    }

    public void setEditTextItem(DialogListEditTextItem editTextItem) {
        this.editTextItem = editTextItem;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
