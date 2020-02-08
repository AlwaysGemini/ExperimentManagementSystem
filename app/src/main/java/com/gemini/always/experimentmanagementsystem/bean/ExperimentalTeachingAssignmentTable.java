package com.gemini.always.experimentmanagementsystem.bean;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

/**
 * @Author: 周清
 * @Description:
 * @Date: Created in 9:34 2020/2/8
 */
@SmartTable
public class ExperimentalTeachingAssignmentTable {

    @SmartColumn(id = 1, name = "学年")
    private String school_year;
    @SmartColumn(id = 2, name = "学期")
    private String semester;
    @SmartColumn(id = 3, name = "开课学院")
    private String school_of_commencement;
    @SmartColumn(id = 4, name = "课程代码")
    private String course_code;
    @SmartColumn(id = 5, name = "课程名称")
    private String course_name;
    @SmartColumn(id = 6, name = "教学班名称")
    private String name_of_teaching_class;
    @SmartColumn(id = 7, name = "任课老师")
    private String teacher;
    @SmartColumn(id = 8, name = "包含实验项目")
    private String including_experimental_items;
    @SmartColumn(id = 9, name = "选修项目数")
    private String number_of_electives;

    public String getSchool_year() {
        return school_year;
    }

    public void setSchool_year(String school_year) {
        this.school_year = school_year;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getSchool_of_commencement() {
        return school_of_commencement;
    }

    public void setSchool_of_commencement(String school_of_commencement) {
        this.school_of_commencement = school_of_commencement;
    }

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

    public String getName_of_teaching_class() {
        return name_of_teaching_class;
    }

    public void setName_of_teaching_class(String name_of_teaching_class) {
        this.name_of_teaching_class = name_of_teaching_class;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getIncluding_experimental_items() {
        return including_experimental_items;
    }

    public void setIncluding_experimental_items(String including_experimental_items) {
        this.including_experimental_items = including_experimental_items;
    }

    public String getNumber_of_electives() {
        return number_of_electives;
    }

    public void setNumber_of_electives(String number_of_electives) {
        this.number_of_electives = number_of_electives;
    }
}
