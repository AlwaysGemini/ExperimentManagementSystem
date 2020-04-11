package com.gemini.always.experimentmanagementsystem.bean.tableBean;

import com.gemini.always.experimentmanagementsystem.custom.customTableView.TableColumn;

public class ExperimentalItemAchievementEntryTable {
    private String experiment_item_id;
    private String experiment_course_match_id;

    @TableColumn(id = 1, name = "学年")
    private String year;

    @TableColumn(id = 2, name = "学期")
    private String semester;

    @TableColumn(id = 3, name = "状态")
    private String state;

    @TableColumn(id = 4, name = "开课学院")
    private String college;

    @TableColumn(id = 5, name = "课程代码")
    private String course_id;

    @TableColumn(id = 6, name = "课程名称")
    private String course_chinese_name;

    @TableColumn(id = 7, name = "教学班名称")
    private String experimental_teaching_class_name;

    @TableColumn(id = 8, name = "实验项目")
    private String experiment_item_name;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_chinese_name() {
        return course_chinese_name;
    }

    public void setCourse_chinese_name(String course_chinese_name) {
        this.course_chinese_name = course_chinese_name;
    }

    public String getExperimental_teaching_class_name() {
        return experimental_teaching_class_name;
    }

    public void setExperimental_teaching_class_name(String experimental_teaching_class_name) {
        this.experimental_teaching_class_name = experimental_teaching_class_name;
    }

    public String getExperiment_item_name() {
        return experiment_item_name;
    }

    public void setExperiment_item_name(String experiment_item_name) {
        this.experiment_item_name = experiment_item_name;
    }

    public String getExperiment_item_id() {
        return experiment_item_id;
    }

    public void setExperiment_item_id(String experiment_item_id) {
        this.experiment_item_id = experiment_item_id;
    }

    public String getExperiment_course_match_id() {
        return experiment_course_match_id;
    }

    public void setExperiment_course_match_id(String experiment_course_match_id) {
        this.experiment_course_match_id = experiment_course_match_id;
    }
}
