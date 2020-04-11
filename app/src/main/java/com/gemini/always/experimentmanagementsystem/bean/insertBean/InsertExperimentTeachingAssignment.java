package com.gemini.always.experimentmanagementsystem.bean.insertBean;

import com.gemini.always.experimentmanagementsystem.custom.customDialog.DialogItem;

public class InsertExperimentTeachingAssignment {
    @DialogItem(id = 0, name = "实验教学班代码", type = DialogItem.TYPE_EditText)
    private String experimental_teaching_class_id;

    @DialogItem(id = 1, name = "任课老师代码", type = DialogItem.TYPE_EditText)
    private String teacher_id;

    @DialogItem(id = 2, name = "实验项目代码", type = DialogItem.TYPE_EditText)
    private String experiment_item_ud;
}
