package com.gemini.always.experimentmanagementsystem.bean.tableBean;

import com.gemini.always.experimentmanagementsystem.custom.customTableView.TableColumn;

public class UnmatchedOfExperimentCourseTable {
    private String experimental_teaching_class_id;

    @TableColumn(id = 1, name = "学年")
    private String year;

    @TableColumn(id = 2, name = "学期")
    private String semester;

    @TableColumn(id = 3, name = "教学班名称")
    private String experimental_teaching_class_name;

    @TableColumn(id = 4, name = "教学班组成")
    private String composition_of_teaching_class;

    @TableColumn(id = 5, name = "开课学院")
    private String college;

    @TableColumn(id = 6, name = "课程代码")
    private String course_id;

    @TableColumn(id = 7, name = "课程名称")
    private String course_chinese_name;

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

    public String getExperimental_teaching_class_name() {
        return experimental_teaching_class_name;
    }

    public void setExperimental_teaching_class_name(String experimental_teaching_class_name) {
        this.experimental_teaching_class_name = experimental_teaching_class_name;
    }

    public String getComposition_of_teaching_class() {
        return composition_of_teaching_class;
    }

    public void setComposition_of_teaching_class(String composition_of_teaching_class) {
        this.composition_of_teaching_class = composition_of_teaching_class;
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

    public String getExperimental_teaching_class_id() {
        return experimental_teaching_class_id;
    }

    public void setExperimental_teaching_class_id(String experimental_teaching_class_id) {
        this.experimental_teaching_class_id = experimental_teaching_class_id;
    }
}
