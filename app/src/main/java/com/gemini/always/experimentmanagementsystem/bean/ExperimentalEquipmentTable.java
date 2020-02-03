package com.gemini.always.experimentmanagementsystem.bean;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

@SmartTable
public class ExperimentalEquipmentTable {

    @SmartColumn(id = 1, name = "实验仪器代码")
    private String id;
    @SmartColumn(id = 2, name = "实验仪器名称")
    private String experimental_equipment_name;
    @SmartColumn(id = 3, name = "价值")
    private String value;
    @SmartColumn(id = 4, name = "教学实验中心")
    private String teaching_experiment_center_name;
    @SmartColumn(id = 5, name = "实验室")
    private String laboratory_name;
    @SmartColumn(id = 6, name = "实验分室")
    private String experimental_compartment_name;
    @SmartColumn(id = 7, name = "实验房间")
    private String laboratory_room_name;
    @SmartColumn(id = 8, name = "是否可搬动")
    private String is_movable;
    @SmartColumn(id = 9, name = "采购时间")
    private String procurement_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExperimental_equipment_name() {
        return experimental_equipment_name;
    }

    public void setExperimental_equipment_name(String experimental_equipment_name) {
        this.experimental_equipment_name = experimental_equipment_name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLaboratory_room_name() {
        return laboratory_room_name;
    }

    public void setLaboratory_room_name(String laboratory_room_name) {
        this.laboratory_room_name = laboratory_room_name;
    }

    public String getIs_movable() {
        return is_movable;
    }

    public void setIs_movable(String is_movable) {
        this.is_movable = is_movable;
    }

    public String getProcurement_time() {
        return procurement_time;
    }

    public void setProcurement_time(String procurement_time) {
        this.procurement_time = procurement_time;
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

    public String getExperimental_compartment_name() {
        return experimental_compartment_name;
    }

    public void setExperimental_compartment_name(String experimental_compartment_name) {
        this.experimental_compartment_name = experimental_compartment_name;
    }
}
