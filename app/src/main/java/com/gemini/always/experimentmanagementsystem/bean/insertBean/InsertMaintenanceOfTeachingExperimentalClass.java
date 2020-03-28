package com.gemini.always.experimentmanagementsystem.bean.insertBean;

import com.gemini.always.experimentmanagementsystem.custom.customDialog.DialogItem;

public class InsertMaintenanceOfTeachingExperimentalClass {
    @DialogItem(id = 0, name = "教学班代码")
    private String experimental_teaching_class_id;

    @DialogItem(id = 1, name = "教学班名称")
    private String experimental_teaching_class_name;

    @DialogItem(id = 2, name = "学年")
    private String school_year;

    @DialogItem(id = 3, name = "学期")
    private String semester;

    @DialogItem(id = 4, name = "教学班组成")
    private String composition_of_teaching_class;

    @DialogItem(id = 5, name = "授课任务代码")
    private String course_experiment_outline_id;

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

    public String getComposition_of_teaching_class() {
        return composition_of_teaching_class;
    }

    public void setComposition_of_teaching_class(String composition_of_teaching_class) {
        this.composition_of_teaching_class = composition_of_teaching_class;
    }

    public String getCourse_experiment_outline_id() {
        return course_experiment_outline_id;
    }

    public void setCourse_experiment_outline_id(String course_experiment_outline_id) {
        this.course_experiment_outline_id = course_experiment_outline_id;
    }
}
