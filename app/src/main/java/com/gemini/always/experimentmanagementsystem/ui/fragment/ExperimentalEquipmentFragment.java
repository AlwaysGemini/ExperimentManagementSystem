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
import com.gemini.always.experimentmanagementsystem.bean.ExperimentalEquipmentTable;
import com.gemini.always.experimentmanagementsystem.presenter.ExperimentalEquipmentPresenter;
import com.gemini.always.experimentmanagementsystem.util.ExcelUtils;
import com.gemini.always.experimentmanagementsystem.util.FileUtils;
import com.gemini.always.experimentmanagementsystem.util.JsonUtil;
import com.gemini.always.experimentmanagementsystem.util.ListUtil;
import com.gemini.always.experimentmanagementsystem.util.XToastUtils;
import com.gemini.always.experimentmanagementsystem.view.ExperimentalEquipmentView;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.orhanobut.logger.Logger;
import com.thl.filechooser.FileChooser;
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
import butterknife.OnClick;
import butterknife.Unbinder;

public class ExperimentalEquipmentFragment extends BaseFragment<ExperimentalEquipmentView, ExperimentalEquipmentPresenter> implements ExperimentalEquipmentView {

    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.button_setting_query_condition)
    RoundButton buttonSettingQueryCondition;
    @BindView(R.id.button_query)
    RoundButton buttonQuery;
    @BindView(R.id.table)
    SmartTable table;
    @BindView(R.id.ll_stateful)
    StatefulLayout llStateful;
    @BindView(R.id.fab_import)
    FloatingActionButton fabImport;
    @BindView(R.id.fab_export)
    FloatingActionButton fabExport;
    @BindView(R.id.fab_add)
    FloatingActionButton fabAdd;
    @BindView(R.id.fab_menu)
    FloatingActionsMenu fabMenu;
    Unbinder unbinder;

    private List<ExperimentalEquipmentTable> list = new ArrayList<>();

    private EditText edit_id;
    private EditText edit_experimental_equipment_name;
    private EditText edit_value;
    private EditText edit_laboratory_room_name;
    private EditText edit_is_movable;
    private EditText edit_procurement_time;

    private String edited_id = "";
    private String edited_experimental_equipment_name = "";
    private String edited_value = "";
    private String edited_laboratory_room_name = "";
    private String edited_is_movable = "";
    private String edited_procurement_time = "";

    private MaterialSpinner spinner_teaching_experiment_center;
    private MaterialSpinner spinner_laboratory_name;
    private MaterialSpinner spinner_experimental_compartment_name;
    private MaterialSpinner spinner_laboratory_room_name;
    private MaterialSpinner spinner_is_movable;
    private MaterialSpinner spinner_experimental_equipment_name;

    private String selected_teaching_experiment_center = "全部";
    private String selected_laboratory_name = "全部";
    private String selected_experimental_compartment_name = "全部";
    private String selected_laboratory_room_name = "全部";
    private String selected_is_movable = "全部";
    private String selected_experimental_equipment_name = "全部";

    private ArrayAdapter<String> spinnerTeachingExperimentCenterArrayAdapter;
    private List<String> teachingExperimentCenterList = new ArrayList<>();
    private ArrayAdapter<String> spinnerLaboratoryNameArrayAdapter;
    private List<String> laboratoryNameList = new ArrayList<>();
    private ArrayAdapter<String> spinnerExperimentalCompartmentNameArrayAdapter;
    private List<String> experimentalCompartmentNameList = new ArrayList<>();
    private ArrayAdapter<String> spinnerLaboratoryRoomNameArrayAdapter;
    private List<String> laboratoryRoomNameList = new ArrayList<>();
    private ArrayAdapter<String> spinnerIsMovableArrayAdapter;
    private List<String> isMovableList = new ArrayList<>();
    private ArrayAdapter<String> spinnerExperimentalEquipmentNameArrayAdapter;
    private List<String> experimentalEquipmentNameList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_fragment_base_query_table_have_no_fuzzy_query, container, false);

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
        titlebar.setTitle("实验器材设备");
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
    public ExperimentalEquipmentPresenter createPresenter() {
        return new ExperimentalEquipmentPresenter();
    }

    @Override
    public ExperimentalEquipmentView createView() {
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
                getPresenter().insertData(edited_id,
                        edited_experimental_equipment_name,
                        edited_value,
                        edited_laboratory_room_name,
                        edited_is_movable,
                        edited_procurement_time);
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
                getPresenter().getData(selected_teaching_experiment_center,
                        selected_laboratory_name,
                        selected_experimental_compartment_name,
                        selected_laboratory_room_name,
                        selected_is_movable,
                        selected_experimental_equipment_name);
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
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(0), "teaching_experiment_center_name", teachingExperimentCenterList);
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(1), "laboratory_name", laboratoryNameList);
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(2), "experimental_compartment_name", experimentalCompartmentNameList);
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(3), "laboratory_room_name", laboratoryRoomNameList);
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(4), "is_movable", isMovableList);
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(5), "experimental_equipment_name", experimentalEquipmentNameList);
            } catch (JSONException e) {
                Logger.e(e, "JSONException:");
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
                    list.addAll(JsonUtil.stringToList(responseJson.getJSONArray("data").toString(), ExperimentalEquipmentTable.class));
                    table.setData(list);
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

    @OnClick({R.id.button_setting_query_condition, R.id.button_query, R.id.fab_import, R.id.fab_export, R.id.fab_add, R.id.fab_menu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_setting_query_condition:
                MaterialDialog dialog = new MaterialDialog.Builder(Objects.requireNonNull(getContext()))
                        .customView(R.layout.dialog_custom_query_condition_experimental_equipment, true)
                        .title(R.string.title_set_query_condition)
                        .positiveText("确定")
                        .positiveColorRes(R.color.colorPrimary)
                        .negativeText("取消")
                        .negativeColorRes(R.color.colorPrimary)
                        .show();

                selected_teaching_experiment_center = "全部";
                selected_laboratory_name = "全部";
                selected_experimental_compartment_name = "全部";
                selected_laboratory_room_name = "全部";
                selected_is_movable = "全部";
                selected_experimental_equipment_name = "全部";

                spinner_teaching_experiment_center = Objects.requireNonNull(dialog.getWindow()).findViewById(R.id.spinner_teaching_experiment_center);
                spinner_laboratory_name = dialog.getWindow().findViewById(R.id.spinner_laboratory_name);
                spinner_experimental_compartment_name = dialog.getWindow().findViewById(R.id.spinner_experimental_compartment_name);
                spinner_laboratory_room_name = dialog.getWindow().findViewById(R.id.spinner_laboratory_room_name);
                spinner_is_movable = dialog.getWindow().findViewById(R.id.spinner_is_movable);
                spinner_experimental_equipment_name = dialog.getWindow().findViewById(R.id.spinner_experimental_equipment_name);

                spinnerTeachingExperimentCenterArrayAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), R.layout.module_spinner_item, teachingExperimentCenterList);
                spinnerLaboratoryNameArrayAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), R.layout.module_spinner_item, laboratoryNameList);
                spinnerExperimentalCompartmentNameArrayAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), R.layout.module_spinner_item, experimentalCompartmentNameList);
                spinnerLaboratoryRoomNameArrayAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), R.layout.module_spinner_item, laboratoryRoomNameList);
                spinnerIsMovableArrayAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), R.layout.module_spinner_item, isMovableList);
                spinnerExperimentalEquipmentNameArrayAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), R.layout.module_spinner_item, experimentalEquipmentNameList);

                spinnerTeachingExperimentCenterArrayAdapter.setDropDownViewResource(R.layout.module_spinner_item);
                spinnerLaboratoryNameArrayAdapter.setDropDownViewResource(R.layout.module_spinner_item);
                spinnerExperimentalCompartmentNameArrayAdapter.setDropDownViewResource(R.layout.module_spinner_item);
                spinnerLaboratoryRoomNameArrayAdapter.setDropDownViewResource(R.layout.module_spinner_item);
                spinnerIsMovableArrayAdapter.setDropDownViewResource(R.layout.module_spinner_item);
                spinnerExperimentalEquipmentNameArrayAdapter.setDropDownViewResource(R.layout.module_spinner_item);

                spinner_teaching_experiment_center.setAdapter(spinnerTeachingExperimentCenterArrayAdapter);
                spinner_laboratory_name.setAdapter(spinnerLaboratoryNameArrayAdapter);
                spinner_experimental_compartment_name.setAdapter(spinnerExperimentalCompartmentNameArrayAdapter);
                spinner_laboratory_room_name.setAdapter(spinnerLaboratoryRoomNameArrayAdapter);
                spinner_is_movable.setAdapter(spinnerIsMovableArrayAdapter);
                spinner_experimental_equipment_name.setAdapter(spinnerExperimentalEquipmentNameArrayAdapter);

                spinner_teaching_experiment_center.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                        selected_teaching_experiment_center = teachingExperimentCenterList.get(position);
                    }
                });

                spinner_laboratory_name.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                        selected_laboratory_name = laboratoryNameList.get(position);
                    }
                });

                spinner_experimental_compartment_name.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                        selected_experimental_compartment_name = experimentalCompartmentNameList.get(position);
                    }
                });

                spinner_laboratory_room_name.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                        selected_laboratory_room_name = laboratoryRoomNameList.get(position);
                    }
                });

                spinner_is_movable.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                        selected_is_movable = isMovableList.get(position);
                    }
                });

                spinner_experimental_equipment_name.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                        selected_experimental_equipment_name = experimentalEquipmentNameList.get(position);
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
                                    List<ExperimentalEquipmentTable> list = ExcelUtils.readExcelContent(filePath, ExperimentalEquipmentTable.class);
                                    for (ExperimentalEquipmentTable experimentalEquipmentTable : list) {
                                        getPresenter().insertData(experimentalEquipmentTable);
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
                        ExcelUtils.createExcel(getContext(), "实验仪器设备管理表格", list, ExperimentalEquipmentTable.class);
                        Objects.requireNonNull(getActivity()).runOnUiThread(() -> XToastUtils.toast("导出成功,文件保存在:" + getActivity().getExternalFilesDir(null)));
                    }
                }.start();
                break;
            case R.id.fab_add:
                new MaterialDialog.Builder(Objects.requireNonNull(getContext()))
                        .customView(R.layout.dialog_custom_experimental_equipment, true)
                        .title(R.string.title_add)
                        .positiveText("确定")
                        .positiveColorRes(R.color.colorPrimary)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                                edit_id = dialog.findViewById(R.id.edit_id);
                                edit_experimental_equipment_name = dialog.findViewById(R.id.edit_experimental_equipment_name);
                                edit_value = dialog.findViewById(R.id.edit_value);
                                edit_laboratory_room_name = dialog.findViewById(R.id.edit_laboratory_room_name);
                                edit_is_movable = dialog.findViewById(R.id.edit_is_movable);
                                edit_procurement_time = dialog.findViewById(R.id.edit_procurement_time);

                                edited_id = edit_id.getText().toString();
                                edited_experimental_equipment_name = edit_experimental_equipment_name.getText().toString();
                                edited_value = edit_value.getText().toString();
                                edited_laboratory_room_name = edit_laboratory_room_name.getText().toString();
                                edited_is_movable = edit_is_movable.getText().toString();
                                edited_procurement_time = edit_procurement_time.getText().toString();
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
