package com.gemini.always.experimentmanagementsystem.bean;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

@SmartTable
public class LaboratoryTable {

    @SmartColumn(id = 1, name = "实验室代码")
    private String laboratory_code;
    @SmartColumn(id = 2, name = "实验室名称")
    private String laboratory_name;
    @SmartColumn(id = 3, name = "隶属教学实验中心")
    private String affiliated_teaching_experiment_center;
    @SmartColumn(id = 4, name = "实验室负责人")
    private String laboratory_director;
    @SmartColumn(id = 5, name = "规章制度")
    private String rules_and_regulations;
    @SmartColumn(id = 6, name = "备注")
    private String remarks;
    @SmartColumn(id = 7, name = "启用标志")
    private String enable_flag;

    public static String[] getFields() {
        return new String[]{"laboratory_code",
                "laboratory_name",
                "affiliated_teaching_experiment_center",
                "laboratory_director",
                "rules_and_regulations",
                "remarks",
                "enable_flag"};
    }

    public static String[] getColumnNames() {
        return new String[]{"实验室代码",
                "实验室名称",
                "隶属教学实验中心",
                "实验室负责人",
                "规章制度",
                "备注",
                "启用标志"};
    }

    public String getLaboratory_code() {
        return laboratory_code;
    }

    public void setLaboratory_code(String laboratory_code) {
        this.laboratory_code = laboratory_code;
    }

    public String getLaboratory_name() {
        return laboratory_name;
    }

    public void setLaboratory_name(String laboratory_name) {
        this.laboratory_name = laboratory_name;
    }

    public String getAffiliated_teaching_experiment_center() {
        return affiliated_teaching_experiment_center;
    }

    public void setAffiliated_teaching_experiment_center(String affiliated_teaching_experiment_center) {
        this.affiliated_teaching_experiment_center = affiliated_teaching_experiment_center;
    }

    public String getLaboratory_director() {
        return laboratory_director;
    }

    public void setLaboratory_director(String laboratory_director) {
        this.laboratory_director = laboratory_director;
    }

    public String getRules_and_regulations() {
        return rules_and_regulations;
    }

    public void setRules_and_regulations(String rules_and_regulations) {
        this.rules_and_regulations = rules_and_regulations;
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
}
