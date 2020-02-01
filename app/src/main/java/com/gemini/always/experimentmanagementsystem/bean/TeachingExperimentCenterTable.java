package com.gemini.always.experimentmanagementsystem.bean;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

@SmartTable
public class TeachingExperimentCenterTable {

    @SmartColumn(id = 1, name = "教学实验中心代码")
    private String teaching_experiment_center_code;
    @SmartColumn(id = 2, name = "教学实验中心名称")
    private String teaching_experiment_center_name;
    @SmartColumn(id = 3, name = "实验室类型")
    private String laboratory_type;
    @SmartColumn(id = 4, name = "所属单位")
    private String subordinate_unit;
    @SmartColumn(id = 5, name = "所属学科")
    private String subordinate_discipline;
    @SmartColumn(id = 6, name = "建立年份")
    private String year_of_establishment;
    @SmartColumn(id = 7, name = "备注")
    private String remarks;
    @SmartColumn(id = 8, name = "启用标志")
    private String enable_flag;

    public static String[] getFields() {
        return new String[]{"teaching_experiment_center_code",
                "teaching_experiment_center_name",
                "laboratory_type",
                "subordinate_unit",
                "subordinate_discipline",
                "year_of_establishment",
                "remarks",
                "enable_flag"};
    }

    public static String[] getColumnNames() {
        return new String[]{"教学实验中心代码",
                "教学实验中心名称",
                "实验室类型",
                "所属单位",
                "所属学科",
                "建立年份",
                "备注",
                "启用标志"};
    }

    public String getLaboratory_type() {
        return laboratory_type;
    }

    public void setLaboratory_type(String laboratory_type) {
        this.laboratory_type = laboratory_type;
    }

    public String getSubordinate_unit() {
        return subordinate_unit;
    }

    public void setSubordinate_unit(String subordinate_unit) {
        this.subordinate_unit = subordinate_unit;
    }

    public String getSubordinate_discipline() {
        return subordinate_discipline;
    }

    public void setSubordinate_discipline(String subordinate_discipline) {
        this.subordinate_discipline = subordinate_discipline;
    }

    public String getYear_of_establishment() {
        return year_of_establishment;
    }

    public void setYear_of_establishment(String year_of_establishment) {
        this.year_of_establishment = year_of_establishment;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getEnable_flag() {
        return enable_flag;
    }

    public void setEnable_flag(String enable_flag) {
        this.enable_flag = enable_flag;
    }

    public String getTeaching_experiment_center_code() {
        return teaching_experiment_center_code;
    }

    public void setTeaching_experiment_center_code(String teaching_experiment_center_code) {
        this.teaching_experiment_center_code = teaching_experiment_center_code;
    }

    public String getTeaching_experiment_center_name() {
        return teaching_experiment_center_name;
    }

    public void setTeaching_experiment_center_name(String teaching_experiment_center_name) {
        this.teaching_experiment_center_name = teaching_experiment_center_name;
    }
}
