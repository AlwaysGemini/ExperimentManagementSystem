package com.gemini.always.experimentmanagementsystem.bean.queryBean;

import com.gemini.always.experimentmanagementsystem.custom.customDialog.QueryItem;

public class QueryCourseExperimentOutline {
    @QueryItem(id = 0, name = "课程", type = QueryItem.TYPE_EDITTEXT, hint = "按课程代码或名称模糊查询")
    private String course;

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
