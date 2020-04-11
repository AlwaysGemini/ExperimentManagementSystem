package com.gemini.always.experimentmanagementsystem.bean.insertBean;

import com.gemini.always.experimentmanagementsystem.custom.customDialog.DialogItem;

public class InsertCourseExperimentOutline {
    @DialogItem(id = 0, name = "授课任务代码", type = DialogItem.TYPE_EditText)
    private String allocation_of_courses_id;

    @DialogItem(id = 1, name = "实验成绩占比(%)", type = DialogItem.TYPE_EditText)
    private String proportion_of_experimental_results;

    @DialogItem(id = 2, name = "实验项目代码", type = DialogItem.TYPE_EditText)
    private String experimental_project_name;

    public String getAllocation_of_courses_id() {
        return allocation_of_courses_id;
    }

    public void setAllocation_of_courses_id(String allocation_of_courses_id) {
        this.allocation_of_courses_id = allocation_of_courses_id;
    }

    public String getProportion_of_experimental_results() {
        return proportion_of_experimental_results;
    }

    public void setProportion_of_experimental_results(String proportion_of_experimental_results) {
        this.proportion_of_experimental_results = proportion_of_experimental_results;
    }

    public String getExperimental_project_name() {
        return experimental_project_name;
    }

    public void setExperimental_project_name(String experimental_project_name) {
        this.experimental_project_name = experimental_project_name;
    }
}
