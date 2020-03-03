package com.gemini.always.experimentmanagementsystem.bean.tableBean;

import com.gemini.always.experimentmanagementsystem.custom.customDialog.AddItem;
import com.gemini.always.experimentmanagementsystem.custom.customDialog.QueryItem;
import com.gemini.always.experimentmanagementsystem.custom.customTableView.TableColumn;

public class TeachingAssignmentOfExperimentalProjectTable {

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

    @TableColumn(id = 7, name = "实验项目")
    private String experimental_project_name;

    @TableColumn(id = 8, name = "选课人数")
    @AddItem(id = 1, name = "选课人数")
    private String number_of_elective_courses;

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

    public String getExperimental_project_name() {
        return experimental_project_name;
    }

    public void setExperimental_project_name(String experimental_project_name) {
        this.experimental_project_name = experimental_project_name;
    }

    public String getNumber_of_elective_courses() {
        return number_of_elective_courses;
    }

    public void setNumber_of_elective_courses(String number_of_elective_courses) {
        this.number_of_elective_courses = number_of_elective_courses;
    }
}
