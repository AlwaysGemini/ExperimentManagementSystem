package com.gemini.always.experimentmanagementsystem.bean.tableBean;

import com.gemini.always.experimentmanagementsystem.custom.customTableView.Table;
import com.gemini.always.experimentmanagementsystem.custom.customTableView.TableColumn;

@Table
public class ExperimentalConsumablesManagementTable {
    @TableColumn(id = 1, name = "实验耗材代码")
    private String experimental_consumables_id;

    @TableColumn(id = 2, name = "实验耗材名称")
    private String experimental_consumables_name;

    @TableColumn(id = 3, name = "当前库存量")
    private String current_inventory;

    @TableColumn(id = 4, name = "最大库存量")
    private String maximum_inventory;

    @TableColumn(id = 5, name = "最小库存量")
    private String minimum_inventory;

    @TableColumn(id = 6, name = "型号规格")
    private String model_and_specification;

    @TableColumn(id = 7, name = "单位")
    private String unit;

    @TableColumn(id = 8, name = "单价")
    private String unit_price;

    @TableColumn(id = 9, name = "教学实验中心")
    private String teaching_experiment_center_name;

    @TableColumn(id = 10, name = "实验室")
    private String laboratory_name;

    @TableColumn(id = 11, name = "实验分室")
    private String laboratory_compartment_name;

    @TableColumn(id = 12, name = "实验房间")
    private String laboratory_room_name;

    public String getExperimental_consumables_id() {
        return experimental_consumables_id;
    }

    public void setExperimental_consumables_id(String experimental_consumables_id) {
        this.experimental_consumables_id = experimental_consumables_id;
    }

    public String getExperimental_consumables_name() {
        return experimental_consumables_name;
    }

    public void setExperimental_consumables_name(String experimental_consumables_name) {
        this.experimental_consumables_name = experimental_consumables_name;
    }

    public String getCurrent_inventory() {
        return current_inventory;
    }

    public void setCurrent_inventory(String current_inventory) {
        this.current_inventory = current_inventory;
    }

    public String getMaximum_inventory() {
        return maximum_inventory;
    }

    public void setMaximum_inventory(String maximum_inventory) {
        this.maximum_inventory = maximum_inventory;
    }

    public String getMinimum_inventory() {
        return minimum_inventory;
    }

    public void setMinimum_inventory(String minimum_inventory) {
        this.minimum_inventory = minimum_inventory;
    }

    public String getModel_and_specification() {
        return model_and_specification;
    }

    public void setModel_and_specification(String model_and_specification) {
        this.model_and_specification = model_and_specification;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(String unit_price) {
        this.unit_price = unit_price;
    }

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

    public String getLaboratory_room_name() {
        return laboratory_room_name;
    }

    public void setLaboratory_room_name(String laboratory_room_name) {
        this.laboratory_room_name = laboratory_room_name;
    }
}
