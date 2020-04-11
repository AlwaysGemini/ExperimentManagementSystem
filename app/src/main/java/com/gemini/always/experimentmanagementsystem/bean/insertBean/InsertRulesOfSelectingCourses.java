package com.gemini.always.experimentmanagementsystem.bean.insertBean;

import com.gemini.always.experimentmanagementsystem.custom.customDialog.DialogItem;

public class InsertRulesOfSelectingCourses {
    @DialogItem(id = 0, name = "学年", type = DialogItem.TYPE_EditText)
    private String year;

    @DialogItem(id = 1, name = "学期", type = DialogItem.TYPE_EditText)
    private String semester;

    @DialogItem(id = 2, name = "选课开始时间", type = DialogItem.TYPE_EditText)
    private String start_time;

    @DialogItem(id = 3, name = "选课结束时间", type = DialogItem.TYPE_EditText)
    private String end_time;

    @DialogItem(id = 4, name = "是否允许退选", type = DialogItem.TYPE_EditText)
    private String is_withdrawable;

    @DialogItem(id = 5, name = "是否允许改选", type = DialogItem.TYPE_EditText)
    private String is_reelect;

    @DialogItem(id = 6, name = "是否选择容量", type = DialogItem.TYPE_EditText)
    private String is_selective_capacity;

    @DialogItem(id = 7, name = "是否即选即用", type = DialogItem.TYPE_EditText)
    private String is_click_to_run;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getIs_withdrawable() {
        return is_withdrawable;
    }

    public void setIs_withdrawable(String is_withdrawable) {
        this.is_withdrawable = is_withdrawable;
    }

    public String getIs_reelect() {
        return is_reelect;
    }

    public void setIs_reelect(String is_reelect) {
        this.is_reelect = is_reelect;
    }

    public String getIs_selective_capacity() {
        return is_selective_capacity;
    }

    public void setIs_selective_capacity(String is_selective_capacity) {
        this.is_selective_capacity = is_selective_capacity;
    }

    public String getIs_click_to_run() {
        return is_click_to_run;
    }

    public void setIs_click_to_run(String is_click_to_run) {
        this.is_click_to_run = is_click_to_run;
    }
}
