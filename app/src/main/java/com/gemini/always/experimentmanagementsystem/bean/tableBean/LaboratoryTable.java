package com.gemini.always.experimentmanagementsystem.bean.tableBean;

import com.gemini.always.experimentmanagementsystem.custom.customTableView.Table;
import com.gemini.always.experimentmanagementsystem.custom.customTableView.TableColumn;

@Table
public class LaboratoryTable {

    @TableColumn(id = 1, name = "实验室代码")
    private String laboratory_id;

    @TableColumn(id = 2, name = "实验室名称")
    private String laboratory_name;

    @TableColumn(id = 3, name = "隶属教学实验中心")
    private String teaching_experiment_center_name;

    @TableColumn(id = 4, name = "实验室负责人")
    private String laboratory_director;

    @TableColumn(id = 5, name = "规章制度")
    private String rules_and_regulations;

    @TableColumn(id = 6, name = "备注")
    private String remarks;

    @TableColumn(id = 7, name = "启用标志")
    private String enable_flag;

    public String getLaboratory_id() {
        return laboratory_id;
    }

    public void setLaboratory_id(String laboratory_id) {
        this.laboratory_id = laboratory_id;
    }

    public String getLaboratory_name() {
        return laboratory_name;
    }

    public void setLaboratory_name(String laboratory_name) {
        this.laboratory_name = laboratory_name;
    }

    public String getTeaching_experiment_center_name() {
        return teaching_experiment_center_name;
    }

    public void setTeaching_experiment_center_name(String teaching_experiment_center_name) {
        this.teaching_experiment_center_name = teaching_experiment_center_name;
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
