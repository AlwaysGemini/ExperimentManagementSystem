package com.gemini.always.experimentmanagementsystem.view;

import com.gemini.always.experimentmanagementsystem.base.MVPView;
import com.gemini.always.experimentmanagementsystem.bean.CourseExperimentProjectTable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public interface CourseExperimentProjectView extends MVPView {
    void onGetDataResult(Boolean isSuccess, JSONObject responseJson);

    void onProgressingData(List<CourseExperimentProjectTable> list);
}
