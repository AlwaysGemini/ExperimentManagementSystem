package com.gemini.always.experimentmanagementsystem.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.bin.david.form.core.SmartTable;
import com.gemini.always.experimentmanagementsystem.R;
import com.gemini.always.experimentmanagementsystem.base.BaseFragment;
import com.gemini.always.experimentmanagementsystem.bean.LaboratoryPersonnelManagementTable;
import com.gemini.always.experimentmanagementsystem.presenter.LaboratoryPersonnelManagementPresenter;
import com.gemini.always.experimentmanagementsystem.util.ExcelUtils;
import com.gemini.always.experimentmanagementsystem.util.FileUtils;
import com.gemini.always.experimentmanagementsystem.util.JsonUtil;
import com.gemini.always.experimentmanagementsystem.util.ListUtil;
import com.gemini.always.experimentmanagementsystem.util.XToastUtils;
import com.gemini.always.experimentmanagementsystem.view.LaboratoryPersonnelManagementView;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.orhanobut.logger.Logger;
import com.thl.filechooser.FileChooser;
import com.xuexiang.xui.widget.actionbar.TitleBar;
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
import butterknife.OnClick;
import butterknife.Unbinder;

public class LaboratoryPersonnelManagementFragment extends BaseFragment<LaboratoryPersonnelManagementView, LaboratoryPersonnelManagementPresenter> implements LaboratoryPersonnelManagementView, View.OnClickListener {

    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.table)
    SmartTable table;
    @BindView(R.id.ll_stateful)
    StatefulLayout llStateful;
    Unbinder unbinder;
    @BindView(R.id.fab_import)
    com.getbase.floatingactionbutton.FloatingActionButton fabImport;
    @BindView(R.id.fab_export)
    com.getbase.floatingactionbutton.FloatingActionButton fabExport;
    @BindView(R.id.fab_add)
    com.getbase.floatingactionbutton.FloatingActionButton fabAdd;
    @BindView(R.id.fab_query)
    com.getbase.floatingactionbutton.FloatingActionButton fabQuery;
    @BindView(R.id.fab_menu)
    FloatingActionsMenu fabMenu;

    List<LaboratoryPersonnelManagementTable> list = new ArrayList<>();

    private EditText edit_job_number;
    private EditText edit_title;
    private EditText edit_laboratory_name;
    private EditText edit_incumbency;

    private String edited_job_number = "";
    private String edited_title = "";
    private String edited_laboratory_name = "";
    private String edited_incumbency = "";

    private MaterialSpinner spinner_affiliated_teaching_experiment_center;
    private MaterialSpinner spinner_laboratory_name;
    private MaterialSpinner spinner_incumbency;
    private EditText edit_teacher;

    private String selected_affiliated_teaching_experiment_center = "全部";
    private String selected_laboratory_name = "全部";
    private String selected_incumbency = "全部";
    private String edited_teacher = "";

    private ArrayAdapter<String> spinnerAffiliatedTeachingExperimentCenterArrayAdapter;
    private List<String> affiliatedTeachingExperimentCenterList = new ArrayList<>();
    private ArrayAdapter<String> spinnerLaboratoryNameArrayAdapter;
    private List<String> laboratoryNameList = new ArrayList<>();
    private ArrayAdapter<String> spinnerIncumbencyArrayAdapter;
    private List<String> incumbencyList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_fragment_base_query_table_have_title_bar, container, false);

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
    }

    @Override
    @OnClick({R.id.fab_import, R.id.fab_export, R.id.fab_add, R.id.fab_query, R.id.fab_menu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_query:
                getQueryConditionList();
                MaterialDialog dialog = new MaterialDialog.Builder(Objects.requireNonNull(getContext()))
                        .customView(R.layout.dialog_custom_query_condition_laboratory_personnerl_management, true)
                        .title(R.string.title_set_query_condition)
                        .positiveText(R.string.btn_confirm)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                getData();
                            }
                        })
                        .positiveColorRes(R.color.colorPrimary)
                        .negativeText(R.string.btn_cancel)
                        .negativeColorRes(R.color.colorPrimary)
                        .show();

                selected_affiliated_teaching_experiment_center = getString(R.string.all);
                selected_laboratory_name = getString(R.string.all);
                selected_incumbency = getString(R.string.all);
                edited_teacher = "";

                spinner_affiliated_teaching_experiment_center = dialog.getWindow().findViewById(R.id.spinner_teaching_experiment_center_name);
                spinner_laboratory_name = dialog.getWindow().findViewById(R.id.spinner_laboratory_name);
                spinner_incumbency = dialog.getWindow().findViewById(R.id.spinner_incumbency);
                edit_teacher = dialog.getWindow().findViewById(R.id.edit_teacher);

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
            case R.id.fab_import:
                FileChooser fileChooser = new FileChooser(this, new FileChooser.FileChoosenListener() {
                    @Override
                    public void onFileChoosen(String filePath) {
                        new Thread() {
                            @Override
                            public void run() {
                                if (FileUtils.getFormatName(filePath).equals("xlsx")) {
                                    Objects.requireNonNull(getActivity()).runOnUiThread(() -> XToastUtils.toast(filePath));
                                    List<LaboratoryPersonnelManagementTable> list = ExcelUtils.readExcelContent(filePath, LaboratoryPersonnelManagementTable.class);
                                    for (LaboratoryPersonnelManagementTable laboratoryPersonnelManagementTable : list) {
                                        getPresenter().insertData(laboratoryPersonnelManagementTable);
                                    }
                                } else {
                                    Objects.requireNonNull(getActivity()).runOnUiThread(() -> XToastUtils.error("请选择.xlsx格式的表格文件"));
                                }
                            }
                        }.start();
                    }
                });
                FileUtils.initFileChooser(fileChooser);
                break;
            case R.id.fab_export:
                new Thread() {
                    @Override
                    public void run() {
                        ExcelUtils.createExcel(getContext(), "实验室人员管理表格", list, LaboratoryPersonnelManagementTable.class);
                        Objects.requireNonNull(getActivity()).runOnUiThread(() -> XToastUtils.toast("导出成功,文件保存在:" + getActivity().getExternalFilesDir(null)));
                    }
                }.start();
                break;
            case R.id.fab_add:
                new MaterialDialog.Builder(Objects.requireNonNull(getContext()))
                        .customView(R.layout.dialog_custom_laboratory_personnel_management, true)
                        .title(R.string.title_add)
                        .positiveText(R.string.btn_confirm)
                        .positiveColorRes(R.color.colorPrimary)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                edit_job_number = dialog.findViewById(R.id.edit_job_number);
                                edit_title = dialog.findViewById(R.id.edit_title);
                                edit_laboratory_name = dialog.findViewById(R.id.edit_laboratory_name);
                                edit_incumbency = dialog.findViewById(R.id.edit_incumbency);

                                edited_job_number = edit_job_number.getText().toString();
                                edited_title = edit_title.getText().toString();
                                edited_laboratory_name = edit_laboratory_name.getText().toString();
                                edited_incumbency = edit_incumbency.getText().toString();

                                insertData();
                            }
                        })
                        .negativeText(R.string.btn_cancel)
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
        if (edit_teacher != null) {
            edited_teacher = edit_teacher.getText().toString();
        } else {
            edited_teacher = "";
        }
        new Thread() {
            @Override
            public void run() {
                getPresenter().getData(selected_affiliated_teaching_experiment_center, selected_laboratory_name, selected_incumbency, edited_teacher);
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
        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            if (isSuccess) {
                try {
                    llStateful.showContent();
                    table.setData(JsonUtil.stringToList(responseJson.getJSONArray("data").toString(), LaboratoryPersonnelManagementTable.class));
                } catch (JSONException e) {
                    Logger.e(e, "JSONException");
                }
            } else {
                try {
                    XToastUtils.toast(responseJson.getString("msg"));
                } catch (JSONException e) {
                    Logger.e(e, "JSONException:");
                }
                llStateful.showEmpty();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
