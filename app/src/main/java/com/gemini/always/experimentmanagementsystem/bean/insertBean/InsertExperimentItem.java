package com.gemini.always.experimentmanagementsystem.bean.insertBean;

import com.gemini.always.experimentmanagementsystem.custom.customDialog.DialogItem;

public class InsertExperimentItem {
    @DialogItem(id = 0, name = "实验项目代码", type = DialogItem.TYPE_EditText)
    private String experiment_item_id;

    @DialogItem(id = 1, name = "实验项目名称", type = DialogItem.TYPE_EditText)
    private String experiment_item_name;

    @DialogItem(id = 2, name = "实验内容", type = DialogItem.TYPE_EditText)
    private String experiment_content;

    @DialogItem(id = 3, name = "实验学时", type = DialogItem.TYPE_EditText)
    private String experiment_hours;

    @DialogItem(id = 4, name = "实验学分", type = DialogItem.TYPE_EditText)
    private String experiment_credit;

    @DialogItem(id = 5, name = "实验属性", type = DialogItem.TYPE_EditText)
    private String experiment_attribute;

    @DialogItem(id = 6, name = "实验类别", type = DialogItem.TYPE_EditText)
    private String experiment_type;

    @DialogItem(id = 7, name = "实验类型", type = DialogItem.TYPE_EditText)
    private String experiment_category;

    @DialogItem(id = 8, name = "所属单位", type = DialogItem.TYPE_EditText)
    private String subordinate_unit;

    @DialogItem(id = 9, name = "所属学科", type = DialogItem.TYPE_EditText)
    private String subordinate_discipline;

    @DialogItem(id = 10, name = "实验要求", type = DialogItem.TYPE_EditText)
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

    public String getSubordinate_discipline() {
        return subordinate_discipline;
    }

    public void setSubordinate_discipline(String subordinate_discipline) {
        this.subordinate_discipline = subordinate_discipline;
    }

    public String getExperiment_requirements() {
        return experiment_requirements;
    }

    public void setExperiment_requirements(String experiment_requirements) {
        this.experiment_requirements = experiment_requirements;
    }
}
