package com.gemini.always.experimentmanagementsystem.bean;

import com.gemini.always.experimentmanagementsystem.custom.customDialog.QueryItem;
import com.gemini.always.experimentmanagementsystem.custom.customTableView.Table;
import com.gemini.always.experimentmanagementsystem.custom.customTableView.TableColumn;

@Table
public class CourseExperimentProjectTable {

    @TableColumn(id = 1, name = "课程代码")
    @QueryItem(id = 4, name = "课程", type = QueryItem.TYPE_EDITTEXT, hint = "按课程代码、名称模糊查询")
    private String course_code;

    @TableColumn(id = 2, name = "课程中文名称")
    private String course_chinese_name;

    @TableColumn(id = 3, name = "课程英文名称")
    private String course_english_name;

    @TableColumn(id = 4, name = "课程视频")
    private String course_video;

    @TableColumn(id = 5, name = "开课学院")
    @QueryItem(id = 0, name = "开课学院", type = QueryItem.TYPE_SPINNER)
    private String instructional_school;

    @TableColumn(id = 6, name = "学分")
    private String credit;

    @TableColumn(id = 7, name = "总学时")
    private String total_hours;

    @TableColumn(id = 8, name = "周学时")
    private String week_hours;

    @TableColumn(id = 9, name = "实验项目数")
    private String number_of_experimental_items;

    @TableColumn(id = 10, name = "课程类别")
    @QueryItem(id = 1, name = "课程类别", type = QueryItem.TYPE_SPINNER)
    private String course_category;

    @TableColumn(id = 11, name = "课程归属")
    @QueryItem(id = 2, name = "课程归属", type = QueryItem.TYPE_SPINNER)
    private String course_assignment;

    @TableColumn(id = 12, name = "课程启用年级")
    @QueryItem(id = 3, name = "启用年级", type = QueryItem.TYPE_SPINNER)
    private String course_enabling_grade;

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
