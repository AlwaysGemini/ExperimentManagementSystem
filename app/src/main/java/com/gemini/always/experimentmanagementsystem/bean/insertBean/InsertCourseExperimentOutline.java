package com.gemini.always.experimentmanagementsystem.bean.insertBean;

import com.gemini.always.experimentmanagementsystem.custom.customDialog.AddItem;

public class InsertCourseExperimentOutline {
    @AddItem(id = 0, name = "授课任务代码")
    private String allocation_of_courses_id;

    @AddItem(id = 1, name = "实验成绩占比(%)")
    private String proportion_of_experimental_results;

    @AddItem(id = 2, name = "实验项目代码")
    private String experimental_project_name;
}
