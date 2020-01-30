package com.gemini.always.experimentmanagementsystem.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.bin.david.form.core.SmartTable;
import com.gemini.always.experimentmanagementsystem.R;
import com.gemini.always.experimentmanagementsystem.base.BaseFragment;
import com.gemini.always.experimentmanagementsystem.bean.LaboratoryPersonnelManagementTable;
import com.gemini.always.experimentmanagementsystem.presenter.LaboratoryPersonnelManagementPresenter;
import com.gemini.always.experimentmanagementsystem.util.JsonUtil;
import com.gemini.always.experimentmanagementsystem.util.ListUtil;
import com.gemini.always.experimentmanagementsystem.util.XToastUtils;
import com.gemini.always.experimentmanagementsystem.view.LaboratoryPersonnelManagementView;
import com.orhanobut.logger.Logger;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;
import com.xuexiang.xui.widget.dialog.materialdialog.DialogAction;
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

public class LaboratoryPersonnelManagementFragment extends BaseFragment<LaboratoryPersonnelManagementView, LaboratoryPersonnelManagementPresenter> implements LaboratoryPersonnelManagementView, View.OnClickListener {

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
    @BindView(R.id.table)
    SmartTable table;
    @BindView(R.id.ll_stateful)
    StatefulLayout llStateful;
    Unbinder unbinder;
    @BindView(R.id.button_add)
    FloatingActionButton buttonAdd;

    private String edited_fuzzy_query = "";

    private EditText edit_job_number;
    private EditText edit_full_name;
    private EditText edit_sex;
    private EditText edit_title;
    private EditText edit_laboratory_name;
    private EditText edit_incumbency;

    private String edited_job_number = "";
    private String edited_full_name = "";
    private String edited_sex = "";
    private String edited_title = "";
    private String edited_laboratory_name = "";
    private String edited_incumbency = "";

    private MaterialSpinner spinner_affiliated_teaching_experiment_center;
    private MaterialSpinner spinner_laboratory_name;
    private MaterialSpinner spinner_incumbency;

    private String selected_affiliated_teaching_experiment_center = "全部";
    private String selected_laboratory_name = "全部";
    private String selected_incumbency = "全部";

    private ArrayAdapter<String> spinnerAffiliatedTeachingExperimentCenterArrayAdapter;
    private List<String> affiliatedTeachingExperimentCenterList = new ArrayList<>();
    private ArrayAdapter<String> spinnerLaboratoryNameArrayAdapter;
    private List<String> laboratoryNameList = new ArrayList<>();
    private ArrayAdapter<String> spinnerIncumbencyArrayAdapter;
    private List<String> incumbencyList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_fragment_laboratory_personnel_management, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        getQueryConditionList();
    }

    private void initView() {

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

        table.getConfig().setShowXSequence(false);
        table.getConfig().setShowYSequence(false);
        table.getConfig().setShowTableTitle(false);
        table.setZoom(true);

        buttonSettingQueryCondition.setOnClickListener(this);
        buttonQuery.setOnClickListener(this);
        buttonAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_setting_query_condition:
                getQueryConditionList();
                MaterialDialog dialog = new MaterialDialog.Builder(Objects.requireNonNull(getContext()))
                        .customView(R.layout.dialog_custom_query_condition_laboratory_personnerl_management, true)
                        .title(R.string.title_set_query_condition)
                        .positiveText("确定")
                        .positiveColorRes(R.color.colorPrimary)
                        .negativeText("取消")
                        .negativeColorRes(R.color.colorPrimary)
                        .show();

                selected_affiliated_teaching_experiment_center = "全部";
                selected_laboratory_name = "全部";
                selected_incumbency = "全部";

                spinner_affiliated_teaching_experiment_center = dialog.getWindow().findViewById(R.id.spinner_teaching_experiment_center_name);
                spinner_laboratory_name = dialog.getWindow().findViewById(R.id.spinner_laboratory_name);
                spinner_incumbency = dialog.getWindow().findViewById(R.id.spinner_incumbency);

                spinnerAffiliatedTeachingExperimentCenterArrayAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), R.layout.module_spinner_item, affiliatedTeachingExperimentCenterList);
                spinnerLaboratoryNameArrayAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), R.layout.module_spinner_item, laboratoryNameList);
                spinnerIncumbencyArrayAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), R.layout.module_spinner_item, incumbencyList);

                spinnerAffiliatedTeachingExperimentCenterArrayAdapter.setDropDownViewResource(R.layout.module_spinner_item);
                spinnerLaboratoryNameArrayAdapter.setDropDownViewResource(R.layout.module_spinner_item);
                spinnerIncumbencyArrayAdapter.setDropDownViewResource(R.layout.module_spinner_item);

                spinner_affiliated_teaching_experiment_center.setAdapter(spinnerAffiliatedTeachingExperimentCenterArrayAdapter);
                spinner_laboratory_name.setAdapter(spinnerLaboratoryNameArrayAdapter);
                spinner_incumbency.setAdapter(spinnerIncumbencyArrayAdapter);

                spinner_affiliated_teaching_experiment_center.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                        selected_affiliated_teaching_experiment_center = affiliatedTeachingExperimentCenterList.get(position);
                    }
                });

                spinner_laboratory_name.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                        selected_laboratory_name = laboratoryNameList.get(position);
                    }
                });

                spinner_incumbency.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                        selected_incumbency = incumbencyList.get(position);
                    }
                });
                break;
            case R.id.button_query:
                getData();
                break;
            case R.id.button_add:
                new MaterialDialog.Builder(Objects.requireNonNull(getContext()))
                        .customView(R.layout.dialog_custom_laboratory_personnel_management, true)
                        .title(R.string.title_add)
                        .positiveText("确定")
                        .positiveColorRes(R.color.colorPrimary)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                edit_job_number = dialog.findViewById(R.id.edit_job_number);
                                edit_full_name = dialog.findViewById(R.id.edit_full_name);
                                edit_sex = dialog.findViewById(R.id.edit_sex);
                                edit_title = dialog.findViewById(R.id.edit_title);
                                edit_laboratory_name = dialog.findViewById(R.id.edit_laboratory_name);
                                edit_incumbency = dialog.findViewById(R.id.edit_incumbency);

                                edited_job_number = edit_job_number.getText().toString();
                                edited_full_name = edit_full_name.getText().toString();
                                edited_sex = edit_sex.getText().toString();
                                edited_title = edit_title.getText().toString();
                                edited_laboratory_name = edit_laboratory_name.getText().toString();
                                edited_incumbency = edit_incumbency.getText().toString();

                                insertData();
                            }
                        })
                        .negativeText("取消")
                        .negativeColorRes(R.color.colorPrimary)
                        .show();
                break;
        }
    }

    private void insertData() {
        new Thread() {
            @Override
            public void run() {
                getPresenter().insertData(edited_job_number,
                        edited_full_name,
                        edited_sex,
                        edited_title,
                        edited_laboratory_name,
                        edited_incumbency);
            }
        }.start();
    }

    private void getQueryConditionList() {
        new Thread() {
            @Override
            public void run() {
                getPresenter().getQueryConditionList();
            }
        }.start();
    }

    private void getData() {
        if (editFuzzyQuery.getText().toString() != null) {
            edited_fuzzy_query = editFuzzyQuery.getText().toString();
        } else {
            edited_fuzzy_query = "";
        }
        new Thread() {
            @Override
            public void run() {
                getPresenter().getData(selected_affiliated_teaching_experiment_center, selected_laboratory_name, selected_incumbency, edited_fuzzy_query);
            }
        }.start();
    }


    @Override
    public LaboratoryPersonnelManagementPresenter createPresenter() {
        return new LaboratoryPersonnelManagementPresenter();
    }

    @Override
    public LaboratoryPersonnelManagementView createView() {
        return this;
    }

    @Override
    public void onInsertDataResult(Boolean isSuccess, JSONObject responseJson) {
        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            try {
                XToastUtils.toast(responseJson.getString("msg"));
            } catch (JSONException e) {
                Logger.e(e, "JSONException:");
            }
        });
    }

    @Override
    public void onGetQueryConditionListResult(Boolean isSuccess, JSONObject responseJson) {
        if (isSuccess) {
            try {
                JSONArray jsonArray = responseJson.getJSONArray("data");
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(0), "teaching_experiment_center_name", affiliatedTeachingExperimentCenterList);
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(1), "laboratory_name", laboratoryNameList);
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(2), "incumbency", incumbencyList);
            } catch (JSONException e) {
                XToastUtils.toast(e.getMessage());
            }
        }
    }

    @Override
    public void onGetDataResult(Boolean isSuccess, JSONObject responseJson) {
        if (isSuccess) {
            try {
                llStateful.showContent();
                table.setData(JsonUtil.stringToList(responseJson.getJSONArray("data").toString(), LaboratoryPersonnelManagementTable.class));
            } catch (JSONException e) {
                Logger.e(e, "JSONException");
            }
        } else {
            llStateful.showEmpty();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
