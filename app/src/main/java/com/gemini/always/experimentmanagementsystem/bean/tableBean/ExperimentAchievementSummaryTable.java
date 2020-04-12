package com.gemini.always.experimentmanagementsystem.bean.tableBean;

import com.gemini.always.experimentmanagementsystem.custom.customTableView.TableColumn;

public class ExperimentAchievementSummaryTable {
    private String experiment_course_match_id;

    @TableColumn(id = 1, name = "学年")
    private String year;

    @TableColumn(id = 2, name = "学期")
    private String semester;

    @TableColumn(id = 3, name = "状态")
    private String experiment_achievement_table_state;

    @TableColumn(id = 4, name = "开课学院")
    private String college;

    @TableColumn(id = 5, name = "课程代码")
    private String course_id;

    @TableColumn(id = 6, name = "课程名称")
    private String course_chinese_name;

    @TableColumn(id = 7, name = "教学班名称")
    private String experimental_teaching_class_name;

    public String getExperiment_course_match_id() {
        return experiment_course_match_id;
    }

    public void setExperiment_course_match_id(String experiment_course_match_id) {
        this.experiment_course_match_id = experiment_course_match_id;
    }

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

    public String getExperiment_achievement_table_state() {
        return experiment_achievement_table_state;
    }

    public void setExperiment_achievement_table_state(String experiment_achievement_table_state) {
        this.experiment_achievement_table_state = experiment_achievement_table_state;
    }
}
