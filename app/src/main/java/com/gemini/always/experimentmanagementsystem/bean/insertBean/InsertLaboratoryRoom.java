package com.gemini.always.experimentmanagementsystem.bean.insertBean;

import com.gemini.always.experimentmanagementsystem.custom.customDialog.AddItem;

public class InsertLaboratoryRoom {
    @AddItem(id = 0, name = "实验分室代码")
    private String laboratory_room_id;

    @AddItem(id = 1, name = "实验分室名称")
    private String laboratory_room_name;

    @AddItem(id = 2, name = "隶属实验分室代码")
    private String laboratory_compartment_id;

    @AddItem(id = 3, name = "实验场地性质")
    private String nature_of_experimental_site;

    @AddItem(id = 4, name = "科研基地类别")
    private String category_of_scientific_research_base;

    @AddItem(id = 5, name = "实验房间负责人")
    private String person_in_charge_of_the_experimental_room;

    @AddItem(id = 6, name = "共建情况")
    private String status_of_joint_construction;

    @AddItem(id = 7, name = "校区")
    private String campus;

    @AddItem(id = 8, name = "容量")
    private String capacity;

    @AddItem(id = 9, name = "备注")
    private String remarks;

    @AddItem(id = 10, name = "启用标志")
    private String enable_flag;

    public String getLaboratory_room_id() {
        return laboratory_room_id;
    }

    public void setLaboratory_room_id(String laboratory_room_id) {
        this.laboratory_room_id = laboratory_room_id;
    }

    public String getLaboratory_room_name() {
        return laboratory_room_name;
    }

    public void setLaboratory_room_name(String laboratory_room_name) {
        this.laboratory_room_name = laboratory_room_name;
    }

    public String getLaboratory_compartment_id() {
        return laboratory_compartment_id;
    }

    public void setLaboratory_compartment_id(String laboratory_compartment_id) {
        this.laboratory_compartment_id = laboratory_compartment_id;
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
