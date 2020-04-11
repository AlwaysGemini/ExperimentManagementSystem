package com.gemini.always.experimentmanagementsystem.bean.insertBean;

import com.gemini.always.experimentmanagementsystem.custom.customDialog.DialogItem;

public class InsertLaboratoryRoom {
    @DialogItem(id = 0, name = "实验分室代码", type = DialogItem.TYPE_EditText)
    private String laboratory_room_id;

    @DialogItem(id = 1, name = "实验分室名称", type = DialogItem.TYPE_EditText)
    private String laboratory_room_name;

    @DialogItem(id = 2, name = "隶属实验分室代码", type = DialogItem.TYPE_EditText)
    private String laboratory_compartment_id;

    @DialogItem(id = 3, name = "实验场地性质", type = DialogItem.TYPE_EditText)
    private String nature_of_experimental_site;

    @DialogItem(id = 4, name = "科研基地类别", type = DialogItem.TYPE_EditText)
    private String category_of_scientific_research_base;

    @DialogItem(id = 5, name = "实验房间负责人", type = DialogItem.TYPE_EditText)
    private String person_in_charge_of_the_experimental_room;

    @DialogItem(id = 6, name = "共建情况", type = DialogItem.TYPE_EditText)
    private String status_of_joint_construction;

    @DialogItem(id = 7, name = "校区", type = DialogItem.TYPE_EditText)
    private String campus;

    @DialogItem(id = 8, name = "容量", type = DialogItem.TYPE_EditText)
    private String capacity;

    @DialogItem(id = 9, name = "备注", type = DialogItem.TYPE_EditText)
    private String remarks;

    @DialogItem(id = 10, name = "启用标志", type = DialogItem.TYPE_EditText)
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
