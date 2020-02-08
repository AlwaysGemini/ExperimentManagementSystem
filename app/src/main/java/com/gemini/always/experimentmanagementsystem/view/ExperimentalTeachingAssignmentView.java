package com.gemini.always.experimentmanagementsystem.view;

import com.gemini.always.experimentmanagementsystem.base.MVPView;

import org.json.JSONObject;

/**
 * @Author: 周清
 * @Description:
 * @Date: Created in 9:40 2020/2/8
 */
public interface ExperimentalTeachingAssignmentView extends MVPView {
    void onInsertDataResult(Boolean isSuccess, JSONObject responseJson);

    void onGetQueryConditionListResult(Boolean isSuccess, JSONObject responseJson);

    void onGetDataResult(Boolean isSuccess, JSONObject responseJson);
}
