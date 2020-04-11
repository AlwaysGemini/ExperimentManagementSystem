package com.gemini.always.experimentmanagementsystem.bean.queryBean;

import com.gemini.always.experimentmanagementsystem.custom.customDialog.DialogItem;

public class QueryLaboratoryPersonnelManagement {
    @DialogItem(id = 0, name = "实验教学中心", type = DialogItem.TYPE_Spinner)
    private String teaching_experiment_center_name;

    @DialogItem(id = 1, name = "实验室", type = DialogItem.TYPE_Spinner)
    private String laboratory_name;

    @DialogItem(id = 2, name = "在职状态", type = DialogItem.TYPE_Spinner)
    private String incumbency;

    @DialogItem(id = 3, name = "教师", type = DialogItem.TYPE_EditText, hint = "按教工号、姓名模糊查询")
    private String name;

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

    public String getIncumbency() {
        return incumbency;
    }

    public void setIncumbency(String incumbency) {
        this.incumbency = incumbency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
