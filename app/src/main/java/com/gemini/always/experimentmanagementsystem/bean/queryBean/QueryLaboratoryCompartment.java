package com.gemini.always.experimentmanagementsystem.bean.queryBean;

import com.gemini.always.experimentmanagementsystem.custom.customDialog.DialogItem;

public class QueryLaboratoryCompartment {
    @DialogItem(id = 0, name = "隶属教学实验中心", type = DialogItem.TYPE_Spinner)
    private String teaching_experiment_center_name;

    @DialogItem(id = 1, name = "隶属实验室", type = DialogItem.TYPE_Spinner)
    private String laboratory_name;

    @DialogItem(id = 2, name = "启用标志", type = DialogItem.TYPE_Spinner)
    private String enable_flag;

    public String getTeaching_experiment_center_name() {
        return teaching_experiment_center_name;
    }

    public void setTeaching_experiment_center_name(String teaching_experiment_center_name) {
        this.teaching_experiment_center_name = teaching_experiment_center_name;
    }

    public String getLaboratory_name() {
        return laboratory_name;
    }

    public void setLaboratory_name(String laboratory_name) {
        this.laboratory_name = laboratory_name;
    }

    public String getEnable_flag() {
        return enable_flag;
    }

    public void setEnable_flag(String enable_flag) {
        this.enable_flag = enable_flag;
    }
}
