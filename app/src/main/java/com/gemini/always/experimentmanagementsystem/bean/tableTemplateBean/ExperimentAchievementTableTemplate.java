package com.gemini.always.experimentmanagementsystem.bean.tableTemplateBean;

import com.gemini.always.experimentmanagementsystem.custom.customTableView.TableColumn;

public class ExperimentAchievementTableTemplate {
    private String experiment_course_match_id;

    @TableColumn(id = 1, name = "实验选课代码")
    private String experimental_course_selection_id;

    @TableColumn(id = 2, name = "实验教学班")
    private String experimental_teaching_class_name;

    @TableColumn(id = 3, name = "姓名")
    private String user_name;

    @TableColumn(id = 4, name = "学号")
    private String student_id;

    @TableColumn(id = 5, name = "成绩")
    private String score;

    public String getExperiment_course_match_id() {
        return experiment_course_match_id;
    }

    public void setExperiment_course_match_id(String experiment_course_match_id) {
        this.experiment_course_match_id = experiment_course_match_id;
    }

    public String getExperimental_course_selection_id() {
        return experimental_course_selection_id;
    }

    public void setExperimental_course_selection_id(String experimental_course_selection_id) {
        this.experimental_course_selection_id = experimental_course_selection_id;
    }

    public String getExperimental_teaching_class_name() {
        return experimental_teaching_class_name;
    }

    public void setExperimental_teaching_class_name(String experimental_teaching_class_name) {
        this.experimental_teaching_class_name = experimental_teaching_class_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
