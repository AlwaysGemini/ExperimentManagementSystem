package com.gemini.always.experimentmanagementsystem.bean.queryBean;

import com.gemini.always.experimentmanagementsystem.custom.customDialog.QueryItem;

public class QueryLaboratoryRoom {
    @QueryItem(id = 0, name = "隶属教学实验中心", type = QueryItem.TYPE_SPINNER)
    private String teaching_experiment_center_name;

    @QueryItem(id = 1, name = "隶属实验室", type = QueryItem.TYPE_SPINNER)
    private String laboratory_name;

    @QueryItem(id = 2, name = "隶属实验分室", type = QueryItem.TYPE_SPINNER)
    private String laboratory_compartment_name;

    @QueryItem(id = 3, name = "实验场地性质", type = QueryItem.TYPE_SPINNER)
    private String nature_of_experimental_site;

    @QueryItem(id = 4, name = "科研基地类别", type = QueryItem.TYPE_SPINNER)
    private String category_of_scientific_research_base;

    @QueryItem(id = 5, name = "共建情况", type = QueryItem.TYPE_SPINNER)
    private String status_of_joint_construction;

    @QueryItem(id = 6, name = "校区", type = QueryItem.TYPE_SPINNER)
    private String campus;

    @QueryItem(id = 7, name = "启用标志", type = QueryItem.TYPE_SPINNER)
    private String enable_flag;
}
