package com.gemini.always.experimentmanagementsystem.bean;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

@SmartTable
public class CourseExperimentProjectTable {

    @SmartColumn(id = 1, name = "课程代码")
    private String course_code;
    @SmartColumn(id = 2, name = "课程中文名称")
    private String course_chinese_name;
    @SmartColumn(id = 3, name = "课程英文名称")
    private String course_english_name;
    @SmartColumn(id = 4, name = "课程视频")
    private String course_video;
    @SmartColumn(id = 5, name = "开课学院")
    private String instructional_school;
    @SmartColumn(id = 6, name = "学分")
    private String credit;
    @SmartColumn(id = 7, name = "总学时")
    private String total_hours;
    @SmartColumn(id = 8, name = "周学时")
    private String week_hours;
    @SmartColumn(id = 9, name = "实验项目数")
    private String number_of_experimental_items;
    @SmartColumn(id = 10, name = "课程类别")
    private String course_category;
    @SmartColumn(id = 11, name = "课程归属")
    private String course_assignment;
    @SmartColumn(id = 12, name = "课程启用年级")
    private String course_enabling_grade;

    public static String[] getFields() {
        return new String[]{"course_code",
                "course_chinese_name",
                "course_english_name",
                "course_video",
                "instructional_school",
                "credit",
                "total_hours",
                "week_hours",
                "number_of_experimental_items",
                "course_category",
                "course_assignment",
                "course_enabling_grade"};
    }

    public static String[] getColumnNames() {
        return new String[]{"课程代码",
                "课程中文名称",
                "课程英文名称",
                "课程视频",
                "开课学院",
                "学分",
                "总学时",
                "实验项目数",
                "实验项目数",
                "课程类别",
                "课程归属",
                "课程启用年级"};
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public String getCourse_chinese_name() {
        return course_chinese_name;
    }

    public void setCourse_chinese_name(String course_chinese_name) {
        this.course_chinese_name = course_chinese_name;
    }

    public String getCourse_english_name() {
        return course_english_name;
    }

    public void setCourse_english_name(String course_english_name) {
        this.course_english_name = course_english_name;
    }

    public String getCourse_video() {
        return course_video;
    }

    public void setCourse_video(String course_video) {
        this.course_video = course_video;
    }

    public String getInstructional_school() {
        return instructional_school;
    }

    public void setInstructional_school(String instructional_school) {
        this.instructional_school = instructional_school;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getTotal_hours() {
        return total_hours;
    }

    public void setTotal_hours(String total_hours) {
        this.total_hours = total_hours;
    }

    public String getWeek_hours() {
        return week_hours;
    }

    public void setWeek_hours(String week_hours) {
        this.week_hours = week_hours;
    }

    public String getNumber_of_experimental_items() {
        return number_of_experimental_items;
    }

    public void setNumber_of_experimental_items(String number_of_experimental_items) {
        this.number_of_experimental_items = number_of_experimental_items;
    }

    public String getCourse_category() {
        return course_category;
    }

    public void setCourse_category(String course_category) {
        this.course_category = course_category;
    }

    public String getCourse_assignment() {
        return course_assignment;
    }

    public void setCourse_assignment(String course_assignment) {
        this.course_assignment = course_assignment;
    }

    public String getCourse_enabling_grade() {
        return course_enabling_grade;
    }

    public void setCourse_enabling_grade(String course_enabling_grade) {
        this.course_enabling_grade = course_enabling_grade;
    }
}
