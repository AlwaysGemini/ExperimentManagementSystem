package com.gemini.always.experimentmanagementsystem.bean.insertBean;

import com.gemini.always.experimentmanagementsystem.custom.customDialog.QueryItem;

public class InsertUnmatchedOfExperimentCourse {
    @QueryItem(id = 0, name = "容量", type = QueryItem.TYPE_EDITTEXT)
    private String capacity;

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }
}
