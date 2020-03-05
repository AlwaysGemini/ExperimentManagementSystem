package com.gemini.always.experimentmanagementsystem.bean.queryBean;

import com.gemini.always.experimentmanagementsystem.custom.customDialog.QueryItem;

public class QueryExperimentTeachingAssignment {
    @QueryItem(id = 0, name = "学年", type = QueryItem.TYPE_SPINNER)
    private String year;

    @QueryItem(id = 1, name = "学期", type = QueryItem.TYPE_SPINNER)
    private String semester;

    @QueryItem(id = 2, name = "开课学院", type = QueryItem.TYPE_SPINNER)
    private String college;

    @QueryItem(id = 3, name = "课程", type = QueryItem.TYPE_EDITTEXT, hint = "按课程代码、名称模糊查询")
    private String course;
}
