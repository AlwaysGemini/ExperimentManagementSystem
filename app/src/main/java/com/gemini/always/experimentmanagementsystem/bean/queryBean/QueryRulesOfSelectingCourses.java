package com.gemini.always.experimentmanagementsystem.bean.queryBean;

import com.gemini.always.experimentmanagementsystem.custom.customDialog.QueryItem;

public class QueryRulesOfSelectingCourses {
    @QueryItem(id = 0, name = "学年", type = QueryItem.TYPE_SPINNER)
    private String year;

    @QueryItem(id = 1, name = "学期", type = QueryItem.TYPE_SPINNER)
    private String semester;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
