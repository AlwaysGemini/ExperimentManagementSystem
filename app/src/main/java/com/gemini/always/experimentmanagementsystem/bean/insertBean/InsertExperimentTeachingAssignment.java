package com.gemini.always.experimentmanagementsystem.bean.insertBean;

import com.gemini.always.experimentmanagementsystem.custom.customDialog.AddItem;

public class InsertExperimentTeachingAssignment {
    @AddItem(id = 0, name = "实验教学班代码")
    private String experimental_teaching_class_id;

    @AddItem(id = 1, name = "任课老师代码")
    private String teacher_id;

    @AddItem(id = 2, name = "实验项目代码")
    private String experiment_item_ud;
}
