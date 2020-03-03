package com.gemini.always.experimentmanagementsystem.bean.tableBean;

import com.gemini.always.experimentmanagementsystem.custom.customDialog.AddItem;
import com.gemini.always.experimentmanagementsystem.custom.customDialog.QueryItem;
import com.gemini.always.experimentmanagementsystem.custom.customTableView.Table;
import com.gemini.always.experimentmanagementsystem.custom.customTableView.TableColumn;

@Table
public class ExperimentalProjectTable {

    @TableColumn(id = 1, name = "实验项目代码")
    @AddItem(id = 0, name = "实验项目代码")
    private String experimental_project_code;

    @TableColumn(id = 2, name = "实验项目名称")
    @QueryItem(id = 3, name = "实验项目名称", type = QueryItem.TYPE_EDITTEXT)
    @AddItem(id = 1, name = "实验项目名称")
    private String experimental_project_name;

    @TableColumn(id = 3, name = "实验内容")
    @AddItem(id = 2, name = "实验内容")
    private String experimental_content;

    @TableColumn(id = 4, name = "实验学时")
    @AddItem(id = 3, name = "实验学时")
    private String experimental_hours;

    @TableColumn(id = 5, name = "实验学分")
    @AddItem(id = 4, name = "实验学分")
    private String experimental_credits;

    @TableColumn(id = 6, name = "实验属性")
    @QueryItem(id = 0, name = "实验属性", type = QueryItem.TYPE_SPINNER)
    @AddItem(id = 5, name = "实验属性")
    private String experimental_properties;

    @TableColumn(id = 7, name = "实验类别")
    @QueryItem(id = 1, name = "实验类别", type = QueryItem.TYPE_SPINNER)
    @AddItem(id = 6, name = "实验类别")
    private String experimental_type;

    @TableColumn(id = 8, name = "实验类型")
    @QueryItem(id = 2, name = "实验类型", type = QueryItem.TYPE_SPINNER)
    @AddItem(id = 7, name = "实验类型")
    private String experimental_category;

    @TableColumn(id = 9, name = "所属单位")
    @AddItem(id = 8, name = "所属单位")
    private String affiliation;

    @TableColumn(id = 10, name = "所属单位学科")
    @AddItem(id = 9, name = "所属学科")
    private String subject;

    public String getExperimental_project_code() {
        return experimental_project_code;
    }

    public void setExperimental_project_code(String experimental_project_code) {
        this.experimental_project_code = experimental_project_code;
    }

    public String getExperimental_project_name() {
        return experimental_project_name;
    }

    public void setExperimental_project_name(String experimental_project_name) {
        this.experimental_project_name = experimental_project_name;
    }

    public String getExperimental_content() {
        return experimental_content;
    }

    public void setExperimental_content(String experimental_content) {
        this.experimental_content = experimental_content;
    }

    public String getExperimental_hours() {
        return experimental_hours;
    }

    public void setExperimental_hours(String experimental_hours) {
        this.experimental_hours = experimental_hours;
    }

    public String getExperimental_credits() {
        return experimental_credits;
    }

    public void setExperimental_credits(String experimental_credits) {
        this.experimental_credits = experimental_credits;
    }

    public String getExperimental_properties() {
        return experimental_properties;
    }

    public void setExperimental_properties(String experimental_properties) {
        this.experimental_properties = experimental_properties;
    }

    public String getExperimental_type() {
        return experimental_type;
    }

    public void setExperimental_type(String experimental_type) {
        this.experimental_type = experimental_type;
    }

    public String getExperimental_category() {
        return experimental_category;
    }

    public void setExperimental_category(String experimental_category) {
        this.experimental_category = experimental_category;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
