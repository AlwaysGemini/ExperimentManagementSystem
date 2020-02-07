package com.gemini.always.experimentmanagementsystem.bean;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

@SmartTable
public class MaintenanceOfTeachingExperimentalClassTable {

    @SmartColumn(id = 1, name = "学年")
    private String school_year;
    @SmartColumn(id = 2, name = "学期")
    private String semester;
    @SmartColumn(id = 3, name = "教学班名称")
    private String name_of_teaching_class;
    @SmartColumn(id = 4, name = "教学班组成")
    private String composition_of_teaching_class;
    @SmartColumn(id = 5, name = "开课学院")
    private String school_of_commencement;
    @SmartColumn(id = 6, name = "课程代码")
    private String course_code;
    @SmartColumn(id = 7, name = "课程名称")
    private String course_name;
    @SmartColumn(id = 8, name = "实验成绩占比(%)")
    private String proportion_of_experimental_results;

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

    public String getName_of_teaching_class() {
        return name_of_teaching_class;
    }

    public void setName_of_teaching_class(String name_of_teaching_class) {
        this.name_of_teaching_class = name_of_teaching_class;
    }

    public String getComposition_of_teaching_class() {
        return composition_of_teaching_class;
    }

    public void setComposition_of_teaching_class(String composition_of_teaching_class) {
        this.composition_of_teaching_class = composition_of_teaching_class;
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

    public String getProportion_of_experimental_results() {
        return proportion_of_experimental_results;
    }

    public void setProportion_of_experimental_results(String proportion_of_experimental_results) {
        this.proportion_of_experimental_results = proportion_of_experimental_results;
    }
}
