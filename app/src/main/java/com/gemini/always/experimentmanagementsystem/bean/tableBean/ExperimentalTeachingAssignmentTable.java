package com.gemini.always.experimentmanagementsystem.bean.tableBean;

import com.gemini.always.experimentmanagementsystem.custom.customDialog.AddItem;
import com.gemini.always.experimentmanagementsystem.custom.customDialog.QueryItem;
import com.gemini.always.experimentmanagementsystem.custom.customTableView.Table;
import com.gemini.always.experimentmanagementsystem.custom.customTableView.TableColumn;

/**
 * @Author: 周清
 * @Description:
 * @Date: Created in 9:34 2020/2/8
 */
@Table
public class ExperimentalTeachingAssignmentTable {

    @TableColumn(id = 1, name = "学年")
    @QueryItem(id = 0, name = "学年", type = QueryItem.TYPE_SPINNER)
    private String school_year;

    @TableColumn(id = 2, name = "学期")
    @QueryItem(id = 1, name = "学期", type = QueryItem.TYPE_SPINNER)
    private String semester;

    @TableColumn(id = 3, name = "开课学院")
    @QueryItem(id = 2, name = "开课学院", type = QueryItem.TYPE_SPINNER)
    private String school_of_commencement;

    @TableColumn(id = 4, name = "课程代码")
    private String course_code;

    @TableColumn(id = 5, name = "课程名称")
    @QueryItem(id = 3, name = "课程", type = QueryItem.TYPE_EDITTEXT, hint = "按课程代码、名称模糊查询")
    private String course_name;

    @TableColumn(id = 6, name = "教学班名称")
    @AddItem(id = 0, name = "教学班名称")
    private String name_of_teaching_class;

    @TableColumn(id = 7, name = "任课老师")
    @AddItem(id = 1, name = "任课老师")
    private String teacher;

    @TableColumn(id = 8, name = "包含实验项目")
    @AddItem(id = 2, name = "包含实验项目")
    private String including_experimental_items;

    @TableColumn(id = 9, name = "选修项目数")
    @AddItem(id = 3, name = "选修项目数")
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
