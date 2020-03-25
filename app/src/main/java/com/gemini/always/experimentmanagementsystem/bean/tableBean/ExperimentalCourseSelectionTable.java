package com.gemini.always.experimentmanagementsystem.bean.tableBean;

import com.gemini.always.experimentmanagementsystem.custom.customTableView.TableColumn;

public class ExperimentalCourseSelectionTable {
    private String experimental_teaching_class_id;

    @TableColumn(id = 1, name = "实验教学班名称")
    private String experimental_teaching_class_name;
    @TableColumn(id = 2, name = "授课老师")
    private String teacher_name;
    @TableColumn(id = 3, name = "开课学院")
    private String college;
    @TableColumn(id = 4, name = "课程名称")
    private String course_chinese_name;

    public String getExperimental_teaching_class_id() {
        return experimental_teaching_class_id;
    }

    public void setExperimental_teaching_class_id(String experimental_teaching_class_id) {
        this.experimental_teaching_class_id = experimental_teaching_class_id;
    }

    public String getExperimental_teaching_class_name() {
        return experimental_teaching_class_name;
    }

    public void setExperimental_teaching_class_name(String experimental_teaching_class_name) {
        this.experimental_teaching_class_name = experimental_teaching_class_name;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getCourse_chinese_name() {
        return course_chinese_name;
    }

    public void setCourse_chinese_name(String course_chinese_name) {
        this.course_chinese_name = course_chinese_name;
    }
}
