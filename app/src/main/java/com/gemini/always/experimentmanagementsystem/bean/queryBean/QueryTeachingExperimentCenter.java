package com.gemini.always.experimentmanagementsystem.bean.queryBean;

import com.gemini.always.experimentmanagementsystem.custom.customDialog.DialogItem;

public class QueryTeachingExperimentCenter {
    @DialogItem(id = 0, name = "实验室类型", type = DialogItem.TYPE_Spinner)
    private String laboratory_type;

    @DialogItem(id = 1, name = "启用标志", type = DialogItem.TYPE_Spinner)
    private String enable_flag;

    public String getLaboratory_type() {
        return laboratory_type;
    }

    public void setLaboratory_type(String laboratory_type) {
        this.laboratory_type = laboratory_type;
    }

    public String getEnable_flag() {
        return enable_flag;
    }

    public void setEnable_flag(String enable_flag) {
        this.enable_flag = enable_flag;
    }
}
