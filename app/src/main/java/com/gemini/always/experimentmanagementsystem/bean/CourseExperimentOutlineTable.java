package com.gemini.always.experimentmanagementsystem.bean;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

@SmartTable
public class CourseExperimentOutlineTable {

    @SmartColumn(id = 1, name = "课程代码")
    private String course_code;
    @SmartColumn(id = 2, name = "课程名称")
    private String course_name;
    @SmartColumn(id = 3, name = "实验成绩占比(%)")
    private String proportion_of_experimental_results;
    @SmartColumn(id = 4, name = "实验项目")
    private String experimental_project_name;

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
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

    public String getExperimental_project_name() {
        return experimental_project_name;
    }

    public void setExperimental_project_name(String experimental_project_name) {
        this.experimental_project_name = experimental_project_name;
    }
}
