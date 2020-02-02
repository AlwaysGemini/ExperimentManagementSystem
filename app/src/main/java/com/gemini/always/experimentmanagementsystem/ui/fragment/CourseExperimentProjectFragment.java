package com.gemini.always.experimentmanagementsystem.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.bin.david.form.core.SmartTable;
import com.gemini.always.experimentmanagementsystem.R;
import com.gemini.always.experimentmanagementsystem.base.BaseFragment;
import com.gemini.always.experimentmanagementsystem.bean.CourseExperimentProjectTable;
import com.gemini.always.experimentmanagementsystem.presenter.CourseExperimentProjectPresenter;
import com.gemini.always.experimentmanagementsystem.util.JsonUtil;
import com.gemini.always.experimentmanagementsystem.util.ListUtil;
import com.gemini.always.experimentmanagementsystem.util.XToastUtils;
import com.gemini.always.experimentmanagementsystem.view.CourseExperimentProjectView;
import com.orhanobut.logger.Logger;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.xuexiang.xui.widget.spinner.materialspinner.MaterialSpinner;
import com.xuexiang.xui.widget.statelayout.StatefulLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CourseExperimentProjectFragment extends BaseFragment<CourseExperimentProjectView, CourseExperimentProjectPresenter> implements CourseExperimentProjectView, View.OnClickListener {

    @BindView(R.id.table)
    SmartTable tableCourseExperimentProject;
    Unbinder unbinder;
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.button_setting_query_condition)
    RoundButton buttonSettingQueryCondition;
    @BindView(R.id.edit_fuzzy_query)
    EditText editFuzzyQuery;
    @BindView(R.id.button_query)
    RoundButton buttonQuery;
    @BindView(R.id.line_query)
    RelativeLayout lineQuery;
    @BindView(R.id.ll_stateful)
    StatefulLayout llStateful;

    private List<CourseExperimentProjectTable> list = new ArrayList<>();

    private MaterialSpinner spinnerInstructionalSchool;
    private MaterialSpinner spinnerCourseCategory;
    private MaterialSpinner spinnerCourseAssignment;
    private MaterialSpinner spinnerCourseEnablingGrade;

    private ArrayAdapter<String> spinnerInstructionalSchoolArrayAdapter;
    private List<String> instructionalSchoolList = new ArrayList<>();
    private ArrayAdapter<String> spinnerCourseCategoryArrayAdapter;
    private List<String> courseCategoryList = new ArrayList<>();
    private ArrayAdapter<String> spinnerCourseAssignmentArrayAdapter;
    private List<String> courseAssignmentList = new ArrayList<>();
    private ArrayAdapter<String> spinnerCourseEnablingGradeArrayAdapter;
    private List<String> courseEnablingGradeList = new ArrayList<>();

    private String selectedInstructionalSchool = "全部";
    private String selectedCourseCategory = "全部";
    private String selectedCourseAssignment = "全部";
    private String selectedCourseEnablingGrade = "全部";
    private String selectedCourse = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_fragment_base_query_table_have_fuzzy_query, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        getListData();
    }

    private void initView() {
        tableCourseExperimentProject.getConfig().setShowXSequence(false);
        tableCourseExperimentProject.getConfig().setShowYSequence(false);
        tableCourseExperimentProject.getConfig().setShowTableTitle(false);
        tableCourseExperimentProject.setZoom(true);

        buttonSettingQueryCondition.setOnClickListener(this);
        buttonQuery.setOnClickListener(this);

        titlebar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Objects.requireNonNull(getActivity()).getSupportFragmentManager().getBackStackEntryCount() >= 1) {
                    getActivity().getSupportFragmentManager().popBackStack();
                } else {
                    getActivity().finish();
                }
            }
        });
    }

    private void initData() {
        new Thread() {
            @Override
            public void run() {
                if (editFuzzyQuery.getText().toString() != null) {
                    selectedCourse = editFuzzyQuery.getText().toString();
                } else {
                    selectedCourse = "";
                }
                getPresenter().getData(selectedInstructionalSchool,
                        selectedCourseCategory,
                        selectedCourseAssignment,
                        selectedCourseEnablingGrade,
                        selectedCourse);
            }
        }.start();
    }

    @Override
    public CourseExperimentProjectPresenter createPresenter() {
        return new CourseExperimentProjectPresenter();
    }

    @Override
    public CourseExperimentProjectView createView() {
        return this;
    }

    @Override
    public void onGetDataResult(Boolean isSuccess, JSONObject responseJson) {
        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            if (isSuccess) {
                try {

                    llStateful.showContent();
                    list.clear();
                    list.addAll(JsonUtil.stringToList(responseJson.getJSONArray("data").toString(), CourseExperimentProjectTable.class));
                    tableCourseExperimentProject.setData(list);
                } catch (JSONException e) {
                    Logger.e(e, "JSONException");
                }
            } else {
                llStateful.showEmpty();
            }
        });
    }

    @Override
    public void onGetQueryConditionListResult(Boolean isSuccess, JSONObject responseJson) {
        if (isSuccess) {
            try {
                JSONArray jsonArray = responseJson.getJSONArray("data");
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(0), "instructional_school", instructionalSchoolList);
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(1), "course_category", courseCategoryList);
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(2), "course_assignment", courseAssignmentList);
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(3), "course_enabling_grade", courseEnablingGradeList);
            } catch (JSONException e) {
                XToastUtils.toast(e.getMessage());
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_setting_query_condition:
                MaterialDialog dialog = new MaterialDialog.Builder(Objects.requireNonNull(getContext()))
                        .customView(R.layout.dialog_custom_course_experiment_project, true)
                        .title(R.string.title_set_query_condition)
                        .positiveText("确定")
                        .positiveColorRes(R.color.colorPrimary)
                        .negativeText("取消")
                        .negativeColorRes(R.color.colorPrimary)
                        .show();

                selectedInstructionalSchool = "全部";
                selectedCourseCategory = "全部";
                selectedCourseAssignment = "全部";
                selectedCourseEnablingGrade = "全部";

                spinnerInstructionalSchool = dialog.getWindow().findViewById(R.id.spinner_instructional_school);
                spinnerCourseCategory = dialog.getWindow().findViewById(R.id.spinner_course_category);
                spinnerCourseAssignment = dialog.getWindow().findViewById(R.id.spinner_course_assignment);
                spinnerCourseEnablingGrade = dialog.getWindow().findViewById(R.id.spinner_course_enabling_grade);

                spinnerInstructionalSchoolArrayAdapter = new ArrayAdapter<String>(Objects.requireNonNull(getContext()), R.layout.module_spinner_item, instructionalSchoolList);
                spinnerCourseCategoryArrayAdapter = new ArrayAdapter<String>(Objects.requireNonNull(getContext()), R.layout.module_spinner_item, courseCategoryList);
                spinnerCourseAssignmentArrayAdapter = new ArrayAdapter<String>(Objects.requireNonNull(getContext()), R.layout.module_spinner_item, courseAssignmentList);
                spinnerCourseEnablingGradeArrayAdapter = new ArrayAdapter<String>(Objects.requireNonNull(getContext()), R.layout.module_spinner_item, courseEnablingGradeList);

                spinnerInstructionalSchoolArrayAdapter.setDropDownViewResource(R.layout.module_spinner_item);
                spinnerCourseCategoryArrayAdapter.setDropDownViewResource(R.layout.module_spinner_item);
                spinnerCourseAssignmentArrayAdapter.setDropDownViewResource(R.layout.module_spinner_item);
                spinnerCourseEnablingGradeArrayAdapter.setDropDownViewResource(R.layout.module_spinner_item);

                spinnerInstructionalSchool.setAdapter(spinnerInstructionalSchoolArrayAdapter);
                spinnerCourseCategory.setAdapter(spinnerCourseCategoryArrayAdapter);
                spinnerCourseAssignment.setAdapter(spinnerCourseAssignmentArrayAdapter);
                spinnerCourseEnablingGrade.setAdapter(spinnerCourseEnablingGradeArrayAdapter);

                spinnerInstructionalSchool.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                        selectedInstructionalSchool = instructionalSchoolList.get(position);
                    }
                });
                spinnerCourseCategory.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                        selectedCourseCategory = courseCategoryList.get(position);
                    }
                });
                spinnerCourseAssignment.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                        selectedCourseAssignment = courseAssignmentList.get(position);
                    }
                });
                spinnerCourseEnablingGrade.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                        selectedCourseEnablingGrade = courseEnablingGradeList.get(position);
                    }
                });
                break;
            case R.id.button_query:
                initData();
                break;
        }
    }

    private void getListData() {
        new Thread() {
            @Override
            public void run() {
                getPresenter().getQueryConditionList();
            }
        }.start();
    }
}
