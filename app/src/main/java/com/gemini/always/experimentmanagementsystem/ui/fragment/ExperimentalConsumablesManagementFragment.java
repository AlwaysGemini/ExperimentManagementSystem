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
import com.gemini.always.experimentmanagementsystem.bean.ExperimentalConsumablesManagementTable;
import com.gemini.always.experimentmanagementsystem.presenter.ExperimentalConsumablesManagementPresenter;
import com.gemini.always.experimentmanagementsystem.util.ExcelUtils;
import com.gemini.always.experimentmanagementsystem.util.FileUtils;
import com.gemini.always.experimentmanagementsystem.util.JsonUtil;
import com.gemini.always.experimentmanagementsystem.util.ListUtil;
import com.gemini.always.experimentmanagementsystem.util.XToastUtils;
import com.gemini.always.experimentmanagementsystem.view.ExperimentalConsumablesManagementView;
import com.getbase.floatingactionbutton.FloatingActionButton;
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

public class ExperimentalConsumablesManagementFragment extends BaseFragment<ExperimentalConsumablesManagementView, ExperimentalConsumablesManagementPresenter> implements ExperimentalConsumablesManagementView {


    @BindView(R.id.titlebar)
    TitleBar titlebar;
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
    @BindView(R.id.fab_query)
    FloatingActionButton fabQuery;
    @BindView(R.id.fab_menu)
    FloatingActionsMenu fabMenu;
    Unbinder unbinder;

    private List<ExperimentalConsumablesManagementTable> list = new ArrayList<>();

    private EditText edit_id;
    private EditText edit_experimental_consumables_name;
    private EditText edit_current_inventory;
    private EditText edit_maximum_inventory;
    private EditText edit_minimum_inventory;
    private EditText edit_model_specification;
    private EditText edit_unit;
    private EditText edit_unit_price;
    private EditText edit_laboratory_room_name;

    private String edited_id = "";
    private String edited_experimental_consumables_name = "";
    private String edited_current_inventory = "";
    private String edited_maximum_inventory = "";
    private String edited_minimum_inventory = "";
    private String edited_model_specification = "";
    private String edited_unit = "";
    private String edited_unit_price = "";
    private String edited_laboratory_room_name = "";

    private MaterialSpinner spinner_teaching_experiment_center;
    private MaterialSpinner spinner_laboratory_name;
    private MaterialSpinner spinner_experimental_compartment_name;
    private MaterialSpinner spinner_laboratory_room_name;
    private EditText edit_model_specification_for_query;
    private EditText edit_experimental_consumables_name_for_query;

    private String selected_teaching_experiment_center = "全部";
    private String selected_laboratory_name = "全部";
    private String selected_experimental_compartment_name = "全部";
    private String selected_laboratory_room_name = "全部";
    private String edited_model_specification_for_query = "";
    private String edited_experimental_consumables_name_for_query = "";

    private ArrayAdapter<String> spinnerTeachingExperimentCenterArrayAdapter;
    private List<String> teachingExperimentCenterList = new ArrayList<>();
    private ArrayAdapter<String> spinnerLaboratoryNameArrayAdapter;
    private List<String> laboratoryNameList = new ArrayList<>();
    private ArrayAdapter<String> spinnerExperimentalCompartmentNameArrayAdapter;
    private List<String> experimentalCompartmentNameList = new ArrayList<>();
    private ArrayAdapter<String> spinnerLaboratoryRoomNameArrayAdapter;
    private List<String> laboratoryRoomNameList = new ArrayList<>();

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
        titlebar.setTitle("实验耗材管理");
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
    public ExperimentalConsumablesManagementPresenter createPresenter() {
        return new ExperimentalConsumablesManagementPresenter();
    }

    @Override
    public ExperimentalConsumablesManagementView createView() {
        return this;
    }

    private void insertData() {
        new Thread() {
            @Override
            public void run() {
                getPresenter().insertData(edited_id,
                        edited_experimental_consumables_name,
                        edited_current_inventory,
                        edited_maximum_inventory,
                        edited_minimum_inventory,
                        edited_model_specification,
                        edited_unit,
                        edited_unit_price,
                        edited_laboratory_room_name);
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
                        edited_model_specification_for_query,
                        edited_experimental_consumables_name_for_query);
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
                    list.addAll(JsonUtil.stringToList(responseJson.getJSONArray("data").toString(), ExperimentalConsumablesManagementTable.class));
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

    @OnClick({R.id.fab_query, R.id.fab_import, R.id.fab_export, R.id.fab_add, R.id.fab_menu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_query:
                MaterialDialog dialog = new MaterialDialog.Builder(Objects.requireNonNull(getContext()))
                        .customView(R.layout.dialog_custom_query_condition_experimental_consumables_management, true)
                        .title(R.string.title_set_query_condition)
                        .positiveText("确定")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                if (edit_model_specification_for_query != null) {
                                    edited_model_specification_for_query = edit_model_specification_for_query.getText().toString();
                                    edited_experimental_consumables_name_for_query = edit_experimental_consumables_name_for_query.getText().toString();
                                }
                                getData();
                            }
                        })
                        .positiveColorRes(R.color.colorPrimary)
                        .negativeText("取消")
                        .negativeColorRes(R.color.colorPrimary)
                        .show();

                spinner_teaching_experiment_center = Objects.requireNonNull(dialog.getWindow()).findViewById(R.id.spinner_teaching_experiment_center);
                spinner_laboratory_name = dialog.getWindow().findViewById(R.id.spinner_laboratory_name);
                spinner_experimental_compartment_name = dialog.getWindow().findViewById(R.id.spinner_experimental_compartment_name);
                spinner_laboratory_room_name = dialog.getWindow().findViewById(R.id.spinner_laboratory_room_name);

                spinnerTeachingExperimentCenterArrayAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), R.layout.module_spinner_item, teachingExperimentCenterList);
                spinnerLaboratoryNameArrayAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), R.layout.module_spinner_item, laboratoryNameList);
                spinnerExperimentalCompartmentNameArrayAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), R.layout.module_spinner_item, experimentalCompartmentNameList);
                spinnerLaboratoryRoomNameArrayAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), R.layout.module_spinner_item, laboratoryRoomNameList);

                spinnerTeachingExperimentCenterArrayAdapter.setDropDownViewResource(R.layout.module_spinner_item);
                spinnerLaboratoryNameArrayAdapter.setDropDownViewResource(R.layout.module_spinner_item);
                spinnerExperimentalCompartmentNameArrayAdapter.setDropDownViewResource(R.layout.module_spinner_item);
                spinnerLaboratoryRoomNameArrayAdapter.setDropDownViewResource(R.layout.module_spinner_item);

                spinner_teaching_experiment_center.setAdapter(spinnerTeachingExperimentCenterArrayAdapter);
                spinner_laboratory_name.setAdapter(spinnerLaboratoryNameArrayAdapter);
                spinner_experimental_compartment_name.setAdapter(spinnerExperimentalCompartmentNameArrayAdapter);
                spinner_laboratory_room_name.setAdapter(spinnerLaboratoryRoomNameArrayAdapter);

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
                edit_model_specification_for_query = dialog.findViewById(R.id.edit_model_specification_for_query);
                edit_experimental_consumables_name_for_query = dialog.findViewById(R.id.edit_experimental_consumables_name_for_query);
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
                                    List<ExperimentalConsumablesManagementTable> list = ExcelUtils.readExcelContent(filePath, ExperimentalConsumablesManagementTable.class);
                                    for (ExperimentalConsumablesManagementTable experimentalConsumablesManagementTable : list) {
                                        getPresenter().insertData(experimentalConsumablesManagementTable);
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
                        ExcelUtils.createExcel(getContext(), "实验耗材管理表格", list, ExperimentalConsumablesManagementTable.class);
                        Objects.requireNonNull(getActivity()).runOnUiThread(() -> XToastUtils.toast("导出成功,文件保存在:" + getActivity().getExternalFilesDir(null)));
                    }
                }.start();
                break;
            case R.id.fab_add:
                new MaterialDialog.Builder(Objects.requireNonNull(getContext()))
                        .customView(R.layout.dialog_custom_experimental_consumables_management, true)
                        .title(R.string.title_add)
                        .positiveText("确定")
                        .positiveColorRes(R.color.colorPrimary)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                                edit_id = dialog.findViewById(R.id.edit_id);
                                edit_experimental_consumables_name = dialog.findViewById(R.id.edit_experimental_consumables_name);
                                edit_current_inventory = dialog.findViewById(R.id.edit_current_inventory);
                                edit_maximum_inventory = dialog.findViewById(R.id.edit_maximum_inventory);
                                edit_minimum_inventory = dialog.findViewById(R.id.edit_minimum_inventory);
                                edit_model_specification = dialog.findViewById(R.id.edit_model_specification);
                                edit_unit = dialog.findViewById(R.id.edit_unit);
                                edit_unit_price = dialog.findViewById(R.id.edit_unit_price);
                                edit_laboratory_room_name = dialog.findViewById(R.id.edit_laboratory_room_name);

                                edited_id = edit_id.getText().toString();
                                edited_experimental_consumables_name = edit_experimental_consumables_name.getText().toString();
                                edited_current_inventory = edit_current_inventory.getText().toString();
                                edited_maximum_inventory = edit_maximum_inventory.getText().toString();
                                edited_minimum_inventory = edit_minimum_inventory.getText().toString();
                                edited_model_specification = edit_model_specification.getText().toString();
                                edited_unit = edit_unit.getText().toString();
                                edited_unit_price = edit_unit_price.getText().toString();
                                edited_laboratory_room_name = edit_laboratory_room_name.getText().toString();
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
