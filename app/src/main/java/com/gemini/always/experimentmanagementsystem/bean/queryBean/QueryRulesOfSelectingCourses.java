package com.gemini.always.experimentmanagementsystem.bean.queryBean;

import com.gemini.always.experimentmanagementsystem.custom.customDialog.DialogItem;

public class QueryRulesOfSelectingCourses {
    @DialogItem(id = 0, name = "学年", type = DialogItem.TYPE_Spinner)
    private String year;

    @DialogItem(id = 1, name = "学期", type = DialogItem.TYPE_Spinner)
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
