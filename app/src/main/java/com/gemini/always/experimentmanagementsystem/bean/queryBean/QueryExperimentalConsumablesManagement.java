package com.gemini.always.experimentmanagementsystem.bean.queryBean;

import com.gemini.always.experimentmanagementsystem.custom.customDialog.QueryItem;

public class QueryExperimentalConsumablesManagement {
    @QueryItem(id = 0, name = "教学实验中心", type = QueryItem.TYPE_SPINNER)
    private String teaching_experiment_center_name;

    @QueryItem(id = 1, name = "实验室", type = QueryItem.TYPE_SPINNER)
    private String laboratory_name;

    @QueryItem(id = 2, name = "实验分室", type = QueryItem.TYPE_SPINNER)
    private String experimental_compartment_name;

    @QueryItem(id = 3, name = "实验房间", type = QueryItem.TYPE_SPINNER)
    private String laboratory_room_name;

    @QueryItem(id = 4, name = "型号规格", type = QueryItem.TYPE_EDITTEXT, hint = "按型号规格模糊查询")
    private String model_and_specification;

    @QueryItem(id = 5, name = "实验耗材", type = QueryItem.TYPE_EDITTEXT, hint = "按实验耗材代码、名称模糊查询")
    private String experimental_consumables_name;

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

    public String getExperimental_compartment_name() {
        return experimental_compartment_name;
    }

    public void setExperimental_compartment_name(String experimental_compartment_name) {
        this.experimental_compartment_name = experimental_compartment_name;
    }

    public String getLaboratory_room_name() {
        return laboratory_room_name;
    }

    public void setLaboratory_room_name(String laboratory_room_name) {
        this.laboratory_room_name = laboratory_room_name;
    }

    public String getModel_and_specification() {
        return model_and_specification;
    }

    public void setModel_and_specification(String model_and_specification) {
        this.model_and_specification = model_and_specification;
    }

    public String getExperimental_consumables_name() {
        return experimental_consumables_name;
    }

    public void setExperimental_consumables_name(String experimental_consumables_name) {
        this.experimental_consumables_name = experimental_consumables_name;
    }
}
