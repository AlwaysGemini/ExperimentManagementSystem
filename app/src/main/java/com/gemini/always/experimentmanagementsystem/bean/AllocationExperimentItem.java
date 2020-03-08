package com.gemini.always.experimentmanagementsystem.bean;

import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AllocationExperimentItem {
    private String experiment_scheduling_id;
    private String experiment_item_name;
    private String teacher_id;

    public static List<AllocationExperimentItem> toList(JSONArray jsonArray) {
        List<AllocationExperimentItem> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                AllocationExperimentItem item = new AllocationExperimentItem();
                item.setExperiment_scheduling_id(jsonObject.getString("experiment_scheduling_id"));
                item.setExperiment_item_name(jsonObject.getString("experiment_item_name"));
                item.setTeacher_id(jsonObject.getString("teacher_id"));
                list.add(item);
            } catch (JSONException e) {
                Logger.e(e, "JSONException:");
            }
        }
        return list;
    }

    public String getExperiment_scheduling_id() {
        return experiment_scheduling_id;
    }

    public void setExperiment_scheduling_id(String experiment_scheduling_id) {
        this.experiment_scheduling_id = experiment_scheduling_id;
    }

    public String getExperiment_item_name() {
        return experiment_item_name;
    }

    public void setExperiment_item_name(String experiment_item_name) {
        this.experiment_item_name = experiment_item_name;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }
}
