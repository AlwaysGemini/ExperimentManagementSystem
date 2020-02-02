package com.gemini.always.experimentmanagementsystem.bean;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

@SmartTable
public class LaboratoryPersonnelManagementTable {
    @SmartColumn(id = 1, name = "工号")
    private String job_number;
    @SmartColumn(id = 2, name = "姓名")
    private String full_name;
    @SmartColumn(id = 3, name = "性别")
    private String sex;
    @SmartColumn(id = 4, name = "职称")
    private String title;
    @SmartColumn(id = 5, name = "实验教学中心")
    private String teaching_experiment_center_name;
    @SmartColumn(id = 6, name = "实验室")
    private String laboratory_name;
    @SmartColumn(id = 7, name = "在职状态")
    private String incumbency;

    public String getJob_number() {
        return job_number;
    }

    public void setJob_number(String job_number) {
        this.job_number = job_number;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
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
}
