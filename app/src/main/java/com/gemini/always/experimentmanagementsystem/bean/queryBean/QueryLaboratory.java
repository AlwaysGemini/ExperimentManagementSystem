package com.gemini.always.experimentmanagementsystem.bean.queryBean;

import com.gemini.always.experimentmanagementsystem.custom.customDialog.QueryItem;

public class QueryLaboratory {
    @QueryItem(id = 0, name = "隶属教学实验中心", type = QueryItem.TYPE_SPINNER)
    private String teaching_experiment_center;

    @QueryItem(id = 1, name = "实验室名称", type = QueryItem.TYPE_SPINNER)
    private String laboratory_name;

    @QueryItem(id = 2, name = "启用标志", type = QueryItem.TYPE_SPINNER)
    private String enable_flag;

    public String getTeaching_experiment_center() {
        return teaching_experiment_center;
    }

    public void setTeaching_experiment_center(String teaching_experiment_center) {
        this.teaching_experiment_center = teaching_experiment_center;
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
