package com.gemini.always.experimentmanagementsystem.bean.tableBean;

import com.gemini.always.experimentmanagementsystem.custom.customTableView.TableColumn;

import java.sql.Timestamp;

public class RulesOfSelectingCoursesTable {
    @TableColumn(id = 1, name = "学年")
    private String year;

    @TableColumn(id = 2, name = "学期")
    private String semester;

    @TableColumn(id = 3, name = "选课开始时间")
    private Timestamp start_time;

    @TableColumn(id = 4, name = "选课结束时间")
    private Timestamp end_time;

    @TableColumn(id = 5, name = "是否允许退选")
    private String is_withdrawable;

    @TableColumn(id = 6, name = "是否允许改选")
    private String is_reelect;

    @TableColumn(id = 7, name = "是否选择容量")
    private String is_selective_capacity;

    @TableColumn(id = 8, name = "是否即选即用")
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
        return start_time.toString();
    }

    public void setStart_time(Timestamp start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time.toString();
    }

    public void setEnd_time(Timestamp end_time) {
        this.end_time = end_time;
    }

    public String getIs_withdrawable() {
        return is_withdrawable.equals("true") ? "是" : "否";
    }

    public void setIs_withdrawable(String is_withdrawable) {
        this.is_withdrawable = is_withdrawable;
    }

    public String getIs_reelect() {
        return is_reelect.equals("true") ? "是" : "否";
    }

    public void setIs_reelect(String is_reelect) {
        this.is_reelect = is_reelect;
    }

    public String getIs_selective_capacity() {
        return is_selective_capacity.equals("true") ? "是" : "否";
    }

    public void setIs_selective_capacity(String is_selective_capacity) {
        this.is_selective_capacity = is_selective_capacity;
    }

    public String getIs_click_to_run() {
        return is_click_to_run.equals("true") ? "是" : "否";
    }

    public void setIs_click_to_run(String is_click_to_run) {
        this.is_click_to_run = is_click_to_run;
    }
}
