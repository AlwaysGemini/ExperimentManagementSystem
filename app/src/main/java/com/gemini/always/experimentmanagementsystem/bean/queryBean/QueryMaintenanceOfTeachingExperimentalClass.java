package com.gemini.always.experimentmanagementsystem.bean.queryBean;

import com.gemini.always.experimentmanagementsystem.custom.customDialog.QueryItem;

public class QueryMaintenanceOfTeachingExperimentalClass {
    @QueryItem(id = 0, name = "学年", type = QueryItem.TYPE_SPINNER)
    private String school_year;

    @QueryItem(id = 1, name = "学期", type = QueryItem.TYPE_SPINNER)
    private String semester;

    @QueryItem(id = 2, name = "开课学院", type = QueryItem.TYPE_SPINNER)
    private String school_of_commencement;

    @QueryItem(id = 3, name = "教学班名称", type = QueryItem.TYPE_EDITTEXT)
    private String experimental_teaching_class_name;

    @QueryItem(id = 4, name = "课程", type = QueryItem.TYPE_EDITTEXT, hint = "按课程代码、名称模糊查询")
    private String course;

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

    public String getExperimental_teaching_class_name() {
        return experimental_teaching_class_name;
    }

    public void setExperimental_teaching_class_name(String experimental_teaching_class_name) {
        this.experimental_teaching_class_name = experimental_teaching_class_name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
