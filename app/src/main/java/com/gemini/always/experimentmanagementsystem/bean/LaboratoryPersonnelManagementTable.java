package com.gemini.always.experimentmanagementsystem.bean;

import com.gemini.always.experimentmanagementsystem.custom.customTableView.Table;
import com.gemini.always.experimentmanagementsystem.custom.customTableView.TableColumn;

@Table
public class LaboratoryPersonnelManagementTable {
    @TableColumn(id = 1, name = "工号")
    private String job_number;
    @TableColumn(id = 2, name = "姓名")
    private String name;
    @TableColumn(id = 3, name = "性别")
    private String sex;
    @TableColumn(id = 4, name = "职称")
    private String title;
    @TableColumn(id = 5, name = "实验教学中心")
    private String teaching_experiment_center_name;
    @TableColumn(id = 6, name = "实验室")
    private String laboratory_name;
    @TableColumn(id = 7, name = "在职状态")
    private String incumbency;

    public String getJob_number() {
        return job_number;
    }

    public void setJob_number(String job_number) {
        this.job_number = job_number;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getIncumbency() {
        return incumbency;
    }

    public void setIncumbency(String incumbency) {
        this.incumbency = incumbency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
