package com.gemini.always.experimentmanagementsystem.bean.tableBean;

import com.gemini.always.experimentmanagementsystem.custom.customTableView.Table;
import com.gemini.always.experimentmanagementsystem.custom.customTableView.TableColumn;

@Table
public class CourseExperimentOutlineTable {
    @TableColumn(id = 1, name = "课程代码")
    private String course_id;

    @TableColumn(id = 2, name = "课程名称")
    private String course_name;

    @TableColumn(id = 3, name = "实验成绩占比(%)")
    private String proportion_of_experimental_results;

    @TableColumn(id = 4, name = "实验项目")
    private String experiment_item_name;

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getProportion_of_experimental_results() {
        return proportion_of_experimental_results;
    }

    public void setProportion_of_experimental_results(String proportion_of_experimental_results) {
        this.proportion_of_experimental_results = proportion_of_experimental_results;
    }

    public String getExperiment_item_name() {
        return experiment_item_name;
    }

    public void setExperiment_item_name(String experiment_item_name) {
        this.experiment_item_name = experiment_item_name;
    }
}
