package com.gemini.always.experimentmanagementsystem.bean.tableBean;

import com.gemini.always.experimentmanagementsystem.custom.customDialog.AddItem;
import com.gemini.always.experimentmanagementsystem.custom.customDialog.QueryItem;
import com.gemini.always.experimentmanagementsystem.custom.customTableView.Table;
import com.gemini.always.experimentmanagementsystem.custom.customTableView.TableColumn;

@Table
public class LaboratoryCompartmentTable {

    @TableColumn(id = 1, name = "实验分室代码")
    @AddItem(id = 0, name = "实验分室代码")
    private String laboratory_compartment_id;

    @TableColumn(id = 2, name = "实验分室名称")
    @AddItem(id = 1, name = "实验分室名称")
    private String laboratory_compartment_name;

    @TableColumn(id = 3, name = "隶属教学实验中心")
    @QueryItem(id = 0, name = "隶属教学实验中心", type = QueryItem.TYPE_SPINNER)
    private String teaching_experiment_center_name;

    @TableColumn(id = 4, name = "隶属实验室")
    @QueryItem(id = 1, name = "隶属实验室", type = QueryItem.TYPE_SPINNER)
    @AddItem(id = 2, name = "隶属实验室")
    private String laboratory_name;

    @TableColumn(id = 5, name = "备注")
    @AddItem(id = 3, name = "备注")
    private String remarks;

    @TableColumn(id = 6, name = "启用标志")
    @QueryItem(id = 2, name = "启用标志", type = QueryItem.TYPE_SPINNER)
    @AddItem(id = 4, name = "启用标志")
    private String enable_flag;

    public String getLaboratory_compartment_id() {
        return laboratory_compartment_id;
    }

    public void setLaboratory_compartment_id(String laboratory_compartment_id) {
        this.laboratory_compartment_id = laboratory_compartment_id;
    }

    public String getLaboratory_compartment_name() {
        return laboratory_compartment_name;
    }

    public void setLaboratory_compartment_name(String laboratory_compartment_name) {
        this.laboratory_compartment_name = laboratory_compartment_name;
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
