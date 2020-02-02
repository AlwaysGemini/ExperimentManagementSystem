package com.gemini.always.experimentmanagementsystem.ui.fragment;

import android.Manifest;
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
import com.bin.david.form.data.column.Column;
import com.bin.david.form.data.table.TableData;
import com.gemini.always.experimentmanagementsystem.DataProvider;
import com.gemini.always.experimentmanagementsystem.R;
import com.gemini.always.experimentmanagementsystem.base.BaseFragment;
import com.gemini.always.experimentmanagementsystem.bean.ExperimentalCompartmentTable;
import com.gemini.always.experimentmanagementsystem.presenter.ExperimentalCompartmentPresenter;
import com.gemini.always.experimentmanagementsystem.util.ExcelUtils;
import com.gemini.always.experimentmanagementsystem.util.FileUtils;
import com.gemini.always.experimentmanagementsystem.util.JsonUtil;
import com.gemini.always.experimentmanagementsystem.util.ListUtil;
import com.gemini.always.experimentmanagementsystem.util.XToastUtils;
import com.gemini.always.experimentmanagementsystem.view.ExperimentalCompartmentView;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.orhanobut.logger.Logger;
import com.thl.filechooser.FileChooser;
import com.xuexiang.xui.adapter.simple.AdapterItem;
import com.xuexiang.xui.adapter.simple.XUISimpleAdapter;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;
import com.xuexiang.xui.widget.dialog.materialdialog.DialogAction;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.xuexiang.xui.widget.popupwindow.popup.XUISimplePopup;
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
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class ExperimentalCompartmentFragment extends BaseFragment<ExperimentalCompartmentView, ExperimentalCompartmentPresenter> implements ExperimentalCompartmentView, View.OnClickListener {

    @BindView(R.id.button_setting_query_condition)
    RoundButton buttonSettingQueryCondition;
    @BindView(R.id.button_query)
    RoundButton buttonQuery;
    @BindView(R.id.line_query)
    RelativeLayout lineQuery;
    @BindView(R.id.table)
    SmartTable table;
    @BindView(R.id.ll_stateful)
    StatefulLayout llStateful;
    Unbinder unbinder;
    @BindView(R.id.fab_import)
    FloatingActionButton fabImport;
    @BindView(R.id.fab_export)
    FloatingActionButton fabExport;
    @BindView(R.id.fab_add)
    FloatingActionButton fabAdd;
    @BindView(R.id.fab_menu)
    FloatingActionsMenu fabMenu;

    private List<ExperimentalCompartmentTable> list = new ArrayList<>();

    private EditText edit_experimental_compartment_code;
    private EditText edit_experimental_compartment_name;
    private EditText edit_affiliated_laboratory;
    private EditText edit_remarks;
    private EditText edit_enable_flag;

    private String edited_experimental_compartment_code = "";
    private String edited_experimental_compartment_name = "";
    private String edited_affiliated_laboratory = "";
    private String edited_remarks = "";
    private String edited_enable_flag = "";

    private MaterialSpinner spinner_affiliated_teaching_experiment_center;
    private MaterialSpinner spinner_affiliated_laboratory_name;
    private MaterialSpinner spinner_enable_flag;

    private String selected_affiliated_teaching_experiment_center = "全部";
    private String selected_affiliated_laboratory = "全部";
    private String selected_enable_flag = "全部";

    private ArrayAdapter<String> spinnerAffiliatedTeachingExperimentCenterArrayAdapter;
    private List<String> affiliatedTeachingExperimentCenterList = new ArrayList<>();
    private ArrayAdapter<String> spinnerAffiliatedLaboratoryNameArrayAdapter;
    private List<String> affiliatedLaboratoryNameList = new ArrayList<>();
    private ArrayAdapter<String> spinnerEnableFlagArrayAdapter;
    private List<String> enableFlagList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_fragment_base_query_table, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        getQueryConditionList();
        ExperimentalCompartmentFragmentPermissionsDispatcher.onClickWithCheck(ExperimentalCompartmentFragment.this, getView());
    }

    private void initView() {
        table.getConfig().setShowXSequence(false);
        table.getConfig().setShowYSequence(false);
        table.getConfig().setShowTableTitle(false);
        table.setZoom(true);
    }

    @Override
    public ExperimentalCompartmentPresenter createPresenter() {
        return new ExperimentalCompartmentPresenter();
    }

    @Override
    public ExperimentalCompartmentView createView() {
        return this;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void insertData() {
        new Thread() {
            @Override
            public void run() {
                getPresenter().insertData(edited_experimental_compartment_code,
                        edited_experimental_compartment_name,
                        edited_affiliated_laboratory,
                        edited_remarks,
                        edited_enable_flag);
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
        new Thread() {
            @Override
            public void run() {
                getPresenter().getData(selected_affiliated_teaching_experiment_center,
                        selected_affiliated_laboratory,
                        selected_enable_flag);
            }
        }.start();
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
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(0), "affiliated_teaching_experiment_center", affiliatedTeachingExperimentCenterList);
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(1), "affiliated_laboratory", affiliatedLaboratoryNameList);
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(2), "enable_flag", enableFlagList);
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
                    list.clear();
                    list.addAll(JsonUtil.stringToList(responseJson.getJSONArray("data").toString(), ExperimentalCompartmentTable.class));
                    table.setData(list);
                    table.getTableData().setOnRowClickListener(new TableData.OnRowClickListener() {
                        @Override
                        public void onClick(Column column, Object o, int col, int row) {
                            new XUISimplePopup(Objects.requireNonNull(getContext()), DataProvider.items)
                                    .create(new XUISimplePopup.OnPopupItemClickListener() {
                                        @Override
                                        public void onItemClick(XUISimpleAdapter adapter, AdapterItem item, int position) {
                                            XToastUtils.toast(item.getTitle().toString());
                                        }
                                    })
                                    .setHasDivider(true)
                                    .showDown(getView());
                        }
                    });
                } catch (JSONException e) {
                    Logger.e(e, "JSONException");
                }
            } else {
                llStateful.showEmpty();
            }
        });
    }

    @Override
    @NeedsPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
    @OnClick({R.id.button_setting_query_condition, R.id.button_query, R.id.fab_import, R.id.fab_export, R.id.fab_add, R.id.fab_menu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_setting_query_condition:
                MaterialDialog dialog = new MaterialDialog.Builder(Objects.requireNonNull(getContext()))
                        .customView(R.layout.dialog_custom_query_condition_experimental_compartment, true)
                        .title(R.string.title_set_query_condition)
                        .positiveText("确定")
                        .positiveColorRes(R.color.colorPrimary)
                        .negativeText("取消")
                        .negativeColorRes(R.color.colorPrimary)
                        .show();

                selected_affiliated_teaching_experiment_center = "全部";
                selected_affiliated_laboratory = "全部";
                selected_enable_flag = "全部";

                spinner_affiliated_teaching_experiment_center = dialog.getWindow().findViewById(R.id.spinner_affiliated_teaching_experiment_center);
                spinner_affiliated_laboratory_name = dialog.getWindow().findViewById(R.id.spinner_affiliated_laboratory_name);
                spinner_enable_flag = dialog.getWindow().findViewById(R.id.spinner_enable_flag);

                spinnerAffiliatedTeachingExperimentCenterArrayAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), R.layout.module_spinner_item, affiliatedTeachingExperimentCenterList);
                spinnerAffiliatedLaboratoryNameArrayAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), R.layout.module_spinner_item, affiliatedLaboratoryNameList);
                spinnerEnableFlagArrayAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), R.layout.module_spinner_item, enableFlagList);

                spinnerAffiliatedTeachingExperimentCenterArrayAdapter.setDropDownViewResource(R.layout.module_spinner_item);
                spinnerAffiliatedLaboratoryNameArrayAdapter.setDropDownViewResource(R.layout.module_spinner_item);
                spinnerEnableFlagArrayAdapter.setDropDownViewResource(R.layout.module_spinner_item);

                spinner_affiliated_teaching_experiment_center.setAdapter(spinnerAffiliatedTeachingExperimentCenterArrayAdapter);
                spinner_affiliated_laboratory_name.setAdapter(spinnerAffiliatedLaboratoryNameArrayAdapter);
                spinner_enable_flag.setAdapter(spinnerEnableFlagArrayAdapter);

                spinner_affiliated_teaching_experiment_center.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                        selected_affiliated_teaching_experiment_center = affiliatedTeachingExperimentCenterList.get(position);
                    }
                });

                spinner_affiliated_laboratory_name.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                        selected_affiliated_laboratory = affiliatedLaboratoryNameList.get(position);
                    }
                });

                spinner_enable_flag.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                        selected_enable_flag = enableFlagList.get(position);
                    }
                });
                break;
            case R.id.button_query:
                getData();
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
                                    List<ExperimentalCompartmentTable> list = ExcelUtils.readExcelContent(filePath, ExperimentalCompartmentTable.class);
                                    for (ExperimentalCompartmentTable experimentalCompartmentTable : list) {
                                        getPresenter().insertData(experimentalCompartmentTable);
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
                        ExcelUtils.createExcel(getContext(), "ExperimentalCompartmentTable", list, ExperimentalCompartmentTable.class);
                        Objects.requireNonNull(getActivity()).runOnUiThread(() -> XToastUtils.toast("导出成功,文件保存在:" + getActivity().getExternalFilesDir(null)));
                    }
                }.start();
                break;
            case R.id.fab_add:
                new MaterialDialog.Builder(Objects.requireNonNull(getContext()))
                        .customView(R.layout.dialog_custom_experiment_compartment, true)
                        .title("增加")
                        .positiveText("确定")
                        .positiveColorRes(R.color.colorPrimary)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                                edit_experimental_compartment_code = dialog.findViewById(R.id.edit_experimental_compartment_code);
                                edit_experimental_compartment_name = dialog.findViewById(R.id.edit_experimental_compartment_name);
                                edit_affiliated_laboratory = dialog.findViewById(R.id.edit_affiliated_laboratory);
                                edit_remarks = dialog.findViewById(R.id.edit_remarks);
                                edit_enable_flag = dialog.findViewById(R.id.edit_enable_flag);

                                edited_experimental_compartment_code = edit_experimental_compartment_code.getText().toString();
                                edited_experimental_compartment_name = edit_experimental_compartment_name.getText().toString();
                                edited_affiliated_laboratory = edit_affiliated_laboratory.getText().toString();
                                edited_remarks = edit_remarks.getText().toString();
                                edited_enable_flag = edit_enable_flag.getText().toString();

                                insertData();
                            }
                        })
                        .negativeText("取消")
                        .negativeColorRes(R.color.colorPrimary)
                        .show();
                break;
            case R.id.fab_menu:

                break;
        }
    }
}
