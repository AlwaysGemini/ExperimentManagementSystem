package com.gemini.always.experimentmanagementsystem.bean.tableBean;

import com.gemini.always.experimentmanagementsystem.custom.customTableView.Table;
import com.gemini.always.experimentmanagementsystem.custom.customTableView.TableColumn;

@Table
public class ExperimentItemTable {
    @TableColumn(id = 1, name = "实验项目代码")
    private String experiment_item_id;

    @TableColumn(id = 2, name = "实验项目名称")
    private String experiment_item_name;

    @TableColumn(id = 3, name = "实验内容")
    private String experiment_content;

    @TableColumn(id = 4, name = "实验学时")
    private String experiment_hours;

    @TableColumn(id = 5, name = "实验学分")
    private String experiment_credit;

    @TableColumn(id = 6, name = "实验属性")
    private String experiment_attribute;

    @TableColumn(id = 7, name = "实验类别")
    private String experiment_type;

    @TableColumn(id = 8, name = "实验类型")
    private String experiment_category;

    @TableColumn(id = 9, name = "所属单位")
    private String subordinate_unit;

    @TableColumn(id = 10, name = "所属单位学科")
    private String subordinate_discipline;

    @TableColumn(id = 11, name = "实验要求")
    private String experiment_requirements;

    public String getExperiment_item_id() {
        return experiment_item_id;
    }

    public void setExperiment_item_id(String experiment_item_id) {
        this.experiment_item_id = experiment_item_id;
    }

    public String getExperiment_item_name() {
        return experiment_item_name;
    }

    public void setExperiment_item_name(String experiment_item_name) {
        this.experiment_item_name = experiment_item_name;
    }

    public String getExperiment_content() {
        return experiment_content;
    }

    public void setExperiment_content(String experiment_content) {
        this.experiment_content = experiment_content;
    }

    public String getExperiment_hours() {
        return experiment_hours;
    }

    public void setExperiment_hours(String experiment_hours) {
        this.experiment_hours = experiment_hours;
    }

    public String getExperiment_credit() {
        return experiment_credit;
    }

    public void setExperiment_credit(String experiment_credit) {
        this.experiment_credit = experiment_credit;
    }

    public String getExperiment_attribute() {
        return experiment_attribute;
    }

    public void setExperiment_attribute(String experiment_attribute) {
        this.experiment_attribute = experiment_attribute;
    }

    public String getExperiment_type() {
        return experiment_type;
    }

    public void setExperiment_type(String experiment_type) {
        this.experiment_type = experiment_type;
    }

    public String getExperiment_category() {
        return experiment_category;
    }

    public void setExperiment_category(String experiment_category) {
        this.experiment_category = experiment_category;
    }

    public String getSubordinate_unit() {
        return subordinate_unit;
    }

    public void setSubordinate_unit(String subordinate_unit) {
        this.subordinate_unit = subordinate_unit;
    }

    public String getExperiment_requirements() {
        return experiment_requirements;
    }

    public void setExperiment_requirements(String experiment_requirements) {
        this.experiment_requirements = experiment_requirements;
    }

    public String getSubordinate_discipline() {
        return subordinate_discipline;
    }

    public void setSubordinate_discipline(String subordinate_discipline) {
        this.subordinate_discipline = subordinate_discipline;
    }
}
