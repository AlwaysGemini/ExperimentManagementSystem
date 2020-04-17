package com.gemini.always.experimentmanagementsystem.bean.insertBean;

import com.gemini.always.experimentmanagementsystem.custom.customDialog.DialogItem;

public class InsertCourseExperimentOutline {
    @DialogItem(id = 0, name = "实验大纲代码", type = DialogItem.TYPE_EditText)
    private String course_experiment_outline_id;

    @DialogItem(id = 1, name = "授课任务代码", type = DialogItem.TYPE_EditText)
    private String allocation_of_courses_id;

    @DialogItem(id = 2, name = "实验成绩占比(%)", type = DialogItem.TYPE_EditText)
    private String proportion_of_experimental_results;

    @DialogItem(id = 3, name = "实验项目代码", type = DialogItem.TYPE_EditText)
    private String experiment_item_id;

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

    public String getExperiment_item_id() {
        return experiment_item_id;
    }

    public void setExperiment_item_id(String experiment_item_id) {
        this.experiment_item_id = experiment_item_id;
    }

    public String getCourse_experiment_outline_id() {
        return course_experiment_outline_id;
    }

    public void setCourse_experiment_outline_id(String course_experiment_outline_id) {
        this.course_experiment_outline_id = course_experiment_outline_id;
    }
}
