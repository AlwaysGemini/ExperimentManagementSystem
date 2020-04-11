package com.gemini.always.experimentmanagementsystem.bean.insertBean;

import com.gemini.always.experimentmanagementsystem.custom.customDialog.DialogItem;

public class InsertLaboratoryPersonnelManagement {
    @DialogItem(id = 0, name = "工号", type = DialogItem.TYPE_EditText)
    private String job_number;

    @DialogItem(id = 1, name = "职称", type = DialogItem.TYPE_EditText)
    private String title;

    @DialogItem(id = 2, name = "实验室代码", type = DialogItem.TYPE_EditText)
    private String laboratory_id;

    @DialogItem(id = 3, name = "在职状态", type = DialogItem.TYPE_EditText)
    private String incumbency;

    public String getJob_number() {
        return job_number;
    }

    public void setJob_number(String job_number) {
        this.job_number = job_number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLaboratory_id() {
        return laboratory_id;
    }

    public void setLaboratory_id(String laboratory_id) {
        this.laboratory_id = laboratory_id;
    }

    public String getIncumbency() {
        return incumbency;
    }

    public void setIncumbency(String incumbency) {
        this.incumbency = incumbency;
    }
}
