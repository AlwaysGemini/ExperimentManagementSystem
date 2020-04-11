package com.gemini.always.experimentmanagementsystem.bean.insertBean;

import com.gemini.always.experimentmanagementsystem.custom.customDialog.DialogItem;

public class InsertExperimentScheduling {
    @DialogItem(id = 0, name = "实验人员", type = DialogItem.TYPE_EditText)
    private String instructor_id;
    @DialogItem(id = 1, name = "实验房间代码", type = DialogItem.TYPE_EditText)
    private String laboratory_room_id;
    @DialogItem(id = 2, name = "第几周", type = DialogItem.TYPE_EditText)
    private String week;
    @DialogItem(id = 3, name = "星期几", type = DialogItem.TYPE_EditText)
    private String day;
    @DialogItem(id = 4, name = "开始时间", type = DialogItem.TYPE_EditText)
    private String start_time;
    @DialogItem(id = 5, name = "长度", type = DialogItem.TYPE_EditText)
    private String length;

    public String getInstructor_id() {
        return instructor_id;
    }

    public void setInstructor_id(String instructor_id) {
        this.instructor_id = instructor_id;
    }

    public String getLaboratory_room_id() {
        return laboratory_room_id;
    }

    public void setLaboratory_room_id(String laboratory_room_id) {
        this.laboratory_room_id = laboratory_room_id;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
