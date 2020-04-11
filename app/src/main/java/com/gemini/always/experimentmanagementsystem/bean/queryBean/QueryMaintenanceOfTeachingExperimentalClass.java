package com.gemini.always.experimentmanagementsystem.bean.queryBean;

import com.gemini.always.experimentmanagementsystem.custom.customDialog.DialogItem;

public class QueryMaintenanceOfTeachingExperimentalClass {
    @DialogItem(id = 0, name = "学年", type = DialogItem.TYPE_Spinner)
    private String school_year;

    @DialogItem(id = 1, name = "学期", type = DialogItem.TYPE_Spinner)
    private String semester;

    @DialogItem(id = 2, name = "开课学院", type = DialogItem.TYPE_Spinner)
    private String school_of_commencement;

    @DialogItem(id = 3, name = "教学班名称", type = DialogItem.TYPE_EditText)
    private String experimental_teaching_class_name;

    @DialogItem(id = 4, name = "课程", type = DialogItem.TYPE_EditText, hint = "按课程代码、名称模糊查询")
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
