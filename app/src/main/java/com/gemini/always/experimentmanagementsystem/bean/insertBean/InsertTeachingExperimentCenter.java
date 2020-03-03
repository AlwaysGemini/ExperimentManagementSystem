package com.gemini.always.experimentmanagementsystem.bean.insertBean;

import com.gemini.always.experimentmanagementsystem.custom.customDialog.AddItem;

public class InsertTeachingExperimentCenter {
    @AddItem(id = 0, name = "教学实验中心代码")
    private String teaching_experiment_center_code;

    @AddItem(id = 1, name = "教学实验中心名称")
    private String teaching_experiment_center_name;

    @AddItem(id = 2, name = "实验室类型")
    private String laboratory_type;

    @AddItem(id = 3, name = "所属单位")
    private String subordinate_unit;

    @AddItem(id = 4, name = "所属学科")
    private String subordinate_discipline;

    @AddItem(id = 5, name = "建立年份")
    private String year_of_establishment;

    @AddItem(id = 6, name = "备注")
    private String remarks;

    @AddItem(id = 7, name = "启用标志")
    private String enable_flag;
}
