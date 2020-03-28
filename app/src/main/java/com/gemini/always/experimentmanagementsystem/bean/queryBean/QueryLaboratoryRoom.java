package com.gemini.always.experimentmanagementsystem.bean.queryBean;

import com.gemini.always.experimentmanagementsystem.custom.customDialog.DialogItem;

public class QueryLaboratoryRoom {
    @DialogItem(id = 0, name = "隶属教学实验中心", type = DialogItem.TYPE_SPINNER)
    private String teaching_experiment_center_name;

    @DialogItem(id = 1, name = "隶属实验室", type = DialogItem.TYPE_SPINNER)
    private String laboratory_name;

    @DialogItem(id = 2, name = "隶属实验分室", type = DialogItem.TYPE_SPINNER)
    private String laboratory_compartment_name;

    @DialogItem(id = 3, name = "实验场地性质", type = DialogItem.TYPE_SPINNER)
    private String nature_of_experimental_site;

    @DialogItem(id = 4, name = "科研基地类别", type = DialogItem.TYPE_SPINNER)
    private String category_of_scientific_research_base;

    @DialogItem(id = 5, name = "共建情况", type = DialogItem.TYPE_SPINNER)
    private String status_of_joint_construction;

    @DialogItem(id = 6, name = "校区", type = DialogItem.TYPE_SPINNER)
    private String campus;

    @DialogItem(id = 7, name = "启用标志", type = DialogItem.TYPE_SPINNER)
    private String enable_flag;

    public String getTeaching_experiment_center_name() {
        return teaching_experiment_center_name;
    }

    public void setTeaching_experiment_center_name(String teaching_experiment_center_name) {
        this.teaching_experiment_center_name = teaching_experiment_center_name;
    }

    public String getLaboratory_name() {
        return laboratory_name;
    }

    public void setLaboratory_name(String laboratory_name) {
        this.laboratory_name = laboratory_name;
    }

    public String getLaboratory_compartment_name() {
        return laboratory_compartment_name;
    }

    public void setLaboratory_compartment_name(String laboratory_compartment_name) {
        this.laboratory_compartment_name = laboratory_compartment_name;
    }

    public String getNature_of_experimental_site() {
        return nature_of_experimental_site;
    }

    public void setNature_of_experimental_site(String nature_of_experimental_site) {
        this.nature_of_experimental_site = nature_of_experimental_site;
    }

    public String getCategory_of_scientific_research_base() {
        return category_of_scientific_research_base;
    }

    public void setCategory_of_scientific_research_base(String category_of_scientific_research_base) {
        this.category_of_scientific_research_base = category_of_scientific_research_base;
    }

    public String getStatus_of_joint_construction() {
        return status_of_joint_construction;
    }

    public void setStatus_of_joint_construction(String status_of_joint_construction) {
        this.status_of_joint_construction = status_of_joint_construction;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getEnable_flag() {
        return enable_flag;
    }

    public void setEnable_flag(String enable_flag) {
        this.enable_flag = enable_flag;
    }
}
