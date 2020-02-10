package com.gemini.always.experimentmanagementsystem.bean;

import com.gemini.always.experimentmanagementsystem.custom.customTableView.Table;
import com.gemini.always.experimentmanagementsystem.custom.customTableView.TableColumn;

import java.util.ArrayList;
import java.util.List;

@Table
public class ExperimentalCompartmentTable {

    @TableColumn(id = 1, name = "实验分室代码")
    private String experimental_compartment_code;
    @TableColumn(id = 2, name = "实验分室名称")
    private String experimental_compartment_name;
    @TableColumn(id = 3, name = "隶属教学实验中心")
    private String affiliated_teaching_experiment_center;
    @TableColumn(id = 4, name = "隶属实验室")
    private String affiliated_laboratory;
    @TableColumn(id = 5, name = "备注")
    private String remarks;
    @TableColumn(id = 6, name = "启用标志")
    private String enable_flag;

    public List<String> toList() {
        List<String> list = new ArrayList<>();
        list.add(experimental_compartment_code);
        list.add(experimental_compartment_name);
        list.add(affiliated_teaching_experiment_center);
        list.add(affiliated_laboratory);
        list.add(remarks);
        list.add(enable_flag);
        return list;
    }

    public String getExperimental_compartment_code() {
        return experimental_compartment_code;
    }

    public void setExperimental_compartment_code(String experimental_compartment_code) {
        this.experimental_compartment_code = experimental_compartment_code;
    }

    public String getExperimental_compartment_name() {
        return experimental_compartment_name;
    }

    public void setExperimental_compartment_name(String experimental_compartment_name) {
        this.experimental_compartment_name = experimental_compartment_name;
    }

    public String getAffiliated_teaching_experiment_center() {
        return affiliated_teaching_experiment_center;
    }

    public void setAffiliated_teaching_experiment_center(String affiliated_teaching_experiment_center) {
        this.affiliated_teaching_experiment_center = affiliated_teaching_experiment_center;
    }

    public String getAffiliated_laboratory() {
        return affiliated_laboratory;
    }

    public void setAffiliated_laboratory(String affiliated_laboratory) {
        this.affiliated_laboratory = affiliated_laboratory;
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
