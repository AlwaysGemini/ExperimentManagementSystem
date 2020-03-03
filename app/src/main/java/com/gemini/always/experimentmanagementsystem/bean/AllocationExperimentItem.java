package com.gemini.always.experimentmanagementsystem.bean;

import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AllocationExperimentItem {
    private String experiment_name;
    private int week;
    private int day;
    private int start_time;
    private int end_time;

    public static List<AllocationExperimentItem> toList(JSONArray jsonArray) {
        List<AllocationExperimentItem> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                AllocationExperimentItem item = new AllocationExperimentItem();
                item.setExperiment_name(jsonObject.getString("experiment_name"));
                list.add(item);
            } catch (JSONException e) {
                Logger.e(e, "JSONException:");
            }
        }
        return list;
    }

    public String getExperiment_name() {
        return experiment_name;
    }

    public void setExperiment_name(String experiment_name) {
        this.experiment_name = experiment_name;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getStart_time() {
        return start_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public int getEnd_time() {
        return end_time;
    }

    public void setEnd_time(int end_time) {
        this.end_time = end_time;
    }
}
