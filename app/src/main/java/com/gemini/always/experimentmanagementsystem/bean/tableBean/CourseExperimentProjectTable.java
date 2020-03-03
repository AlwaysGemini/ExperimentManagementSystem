package com.gemini.always.experimentmanagementsystem.bean.tableBean;

import com.gemini.always.experimentmanagementsystem.custom.customTableView.Table;
import com.gemini.always.experimentmanagementsystem.custom.customTableView.TableColumn;

@Table
public class CourseExperimentProjectTable {

    @TableColumn(id = 1, name = "课程代码")
    private String course_id;

    @TableColumn(id = 2, name = "课程中文名称")
    private String course_chinese_name;

    @TableColumn(id = 3, name = "课程英文名称")
    private String course_english_name;

    @TableColumn(id = 4, name = "课程视频")
    private String course_video;

    @TableColumn(id = 5, name = "开课学院")
    private String college;

    @TableColumn(id = 6, name = "学分")
    private String credit;

    @TableColumn(id = 7, name = "总学时")
    private String class_hour;

    @TableColumn(id = 8, name = "周学时")
    private String week_class_hour;

    @TableColumn(id = 9, name = "实验项目数")
    private String number_of_experimental_items;

    @TableColumn(id = 10, name = "课程类别")
    private String course_category;

    @TableColumn(id = 11, name = "课程归属")
    private String course_assignment;

    @TableColumn(id = 12, name = "课程启用年级")
    private String enabling_grade;

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

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getClass_hour() {
        return class_hour;
    }

    public void setClass_hour(String class_hour) {
        this.class_hour = class_hour;
    }

    public String getWeek_class_hour() {
        return week_class_hour;
    }

    public void setWeek_class_hour(String week_class_hour) {
        this.week_class_hour = week_class_hour;
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

    public String getEnabling_grade() {
        return enabling_grade;
    }

    public void setEnabling_grade(String enabling_grade) {
        this.enabling_grade = enabling_grade;
    }
}
