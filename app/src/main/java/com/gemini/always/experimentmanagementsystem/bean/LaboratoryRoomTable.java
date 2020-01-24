package com.gemini.always.experimentmanagementsystem.bean;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

@SmartTable
public class LaboratoryRoomTable {

    @SmartColumn(id = 1 ,name = "实验分室代码")
    private String laboratory_room_code;
    @SmartColumn(id = 2 ,name = "实验分室名称")
    private String laboratory_room_name;
    @SmartColumn(id = 3 ,name = "隶属教学实验中心")
    private String affiliated_teaching_experiment_center;
    @SmartColumn(id = 4 ,name = "隶属实验室")
    private String affiliated_laboratory;
    @SmartColumn(id = 5 ,name = "隶属实验房间")
    private String affiliated_experimental_compartment;
    @SmartColumn(id = 6 ,name = "实验场地性质")
    private String nature_of_experimental_site;
    @SmartColumn(id = 7 ,name = "科研基地类别")
    private String category_of_scientific_research_base;
    @SmartColumn(id = 8 ,name = "实验房间负责人")
    private String person_in_charge_of_the_experimental_room;
    @SmartColumn(id = 9 ,name = "共建情况")
    private String status_of_joint_construction;
    @SmartColumn(id = 10 ,name = "校区")
    private String campus;
    @SmartColumn(id = 11 ,name = "容量")
    private String capacity;
    @SmartColumn(id = 12,name = "备注")
    private String remarks;
    @SmartColumn(id = 13,name = "启用标志")
    private String enable_flag;

    public String getLaboratory_room_code() {
        return laboratory_room_code;
    }

    public void setLaboratory_room_code(String laboratory_room_code) {
        this.laboratory_room_code = laboratory_room_code;
    }

    public String getLaboratory_room_name() {
        return laboratory_room_name;
    }

    public void setLaboratory_room_name(String laboratory_room_name) {
        this.laboratory_room_name = laboratory_room_name;
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

    public String getAffiliated_experimental_compartment() {
        return affiliated_experimental_compartment;
    }

    public void setAffiliated_experimental_compartment(String affiliated_experimental_compartment) {
        this.affiliated_experimental_compartment = affiliated_experimental_compartment;
    }

    public String getNature_of_experimental_site() {
        return nature_of_experimental_site;
    }

    public void setNature_of_experimental_site(String nature_of_experimental_site) {
        this.nature_of_experimental_site = nature_of_experimental_site;
    }

    public String getCategory_of_scientific_research_base() {
        return category_of_scientific_research_base;
    }

    public void setCategory_of_scientific_research_base(String category_of_scientific_research_base) {
        this.category_of_scientific_research_base = category_of_scientific_research_base;
    }

    public String getPerson_in_charge_of_the_experimental_room() {
        return person_in_charge_of_the_experimental_room;
    }

    public void setPerson_in_charge_of_the_experimental_room(String person_in_charge_of_the_experimental_room) {
        this.person_in_charge_of_the_experimental_room = person_in_charge_of_the_experimental_room;
    }

    public String getStatus_of_joint_construction() {
        return status_of_joint_construction;
    }

    public void setStatus_of_joint_construction(String status_of_joint_construction) {
        this.status_of_joint_construction = status_of_joint_construction;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
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
