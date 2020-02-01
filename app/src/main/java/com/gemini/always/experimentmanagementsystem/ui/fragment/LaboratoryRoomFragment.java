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
import com.gemini.always.experimentmanagementsystem.bean.LaboratoryRoomTable;
import com.gemini.always.experimentmanagementsystem.presenter.LaboratoryRoomPresenter;
import com.gemini.always.experimentmanagementsystem.util.ExcelUtils;
import com.gemini.always.experimentmanagementsystem.util.FileUtils;
import com.gemini.always.experimentmanagementsystem.util.JsonUtil;
import com.gemini.always.experimentmanagementsystem.util.ListUtil;
import com.gemini.always.experimentmanagementsystem.util.XToastUtils;
import com.gemini.always.experimentmanagementsystem.view.LaboratoryRoomView;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.orhanobut.logger.Logger;
import com.thl.filechooser.FileChooser;
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

public class LaboratoryRoomFragment extends BaseFragment<LaboratoryRoomView, LaboratoryRoomPresenter> implements LaboratoryRoomView, View.OnClickListener {

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

    private List<LaboratoryRoomTable> list = new ArrayList<>();

    private EditText edit_laboratory_room_code;
    private EditText edit_laboratory_room_name;
    private EditText edit_affiliated_experimental_compartment;
    private EditText edit_nature_of_experimental_site;
    private EditText edit_category_of_scientific_research_base;
    private EditText edit_person_in_charge_of_the_experimental_room;
    private EditText edit_status_of_joint_construction;
    private EditText edit_campus;
    private EditText edit_capacity;
    private EditText edit_remarks;
    private EditText edit_enable_flag;

    private String edited_laboratory_room_code = "";
    private String edited_laboratory_room_name = "";
    private String edited_affiliated_experimental_compartment = "";
    private String edited_nature_of_experimental_site = "";
    private String edited_category_of_scientific_research_base = "";
    private String edited_person_in_charge_of_the_experimental_room = "";
    private String edited_status_of_joint_construction = "";
    private String edited_campus = "";
    private String edited_capacity = "";
    private String edited_remarks = "";
    private String edited_enable_flag = "";

    private MaterialSpinner spinner_affiliated_teaching_experiment_center;
    private MaterialSpinner spinner_affiliated_laboratory_name;
    private MaterialSpinner spinner_affiliated_experimental_compartment_name;
    private MaterialSpinner spinner_nature_of_experimental_site;
    private MaterialSpinner spinner_category_of_scientific_research_base;
    private MaterialSpinner spinner_status_of_joint_construction;
    private MaterialSpinner spinner_campus;
    private MaterialSpinner spinner_enable_flag;

    private String selected_affiliated_teaching_experiment_center = "全部";
    private String selected_affiliated_laboratory_name = "全部";
    private String selected_affiliated_experimental_compartment_name = "全部";
    private String selected_nature_of_experimental_site = "全部";
    private String selected_category_of_scientific_research_base = "全部";
    private String selected_status_of_joint_construction = "全部";
    private String selected_campus = "全部";
    private String selected_enable_flag = "全部";

    private ArrayAdapter<String> spinnerAffiliatedTeachingExperimentCenterArrayAdapter;
    private List<String> affiliatedTeachingExperimentCenterList = new ArrayList<>();
    private ArrayAdapter<String> spinnerAffiliatedLaboratoryNameArrayAdapter;
    private List<String> affiliatedLaboratoryNameList = new ArrayList<>();
    private ArrayAdapter<String> spinnerAffiliatedExperimentalCompartmentNameArrayAdapter;
    private List<String> affiliatedExperimentalCompartmentNameList = new ArrayList<>();
    private ArrayAdapter<String> spinnerNatureOfExperimentalSiteArrayAdapter;
    private List<String> natureOfExperimentalSiteList = new ArrayList<>();
    private ArrayAdapter<String> spinnerCategoryOfScientificResearchBaseArrayAdapter;
    private List<String> categoryOfScientificResearchBaseList = new ArrayList<>();
    private ArrayAdapter<String> spinnerStatusOfJointConstructionArrayAdapter;
    private List<String> statusOfJointConstructionList = new ArrayList<>();
    private ArrayAdapter<String> spinnerCampusArrayAdapter;
    private List<String> campusList = new ArrayList<>();
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
    }

    private void initView() {
        table.getConfig().setShowXSequence(false);
        table.getConfig().setShowYSequence(false);
        table.getConfig().setShowTableTitle(false);
        table.setZoom(true);
    }

    @Override
    public LaboratoryRoomPresenter createPresenter() {
        return new LaboratoryRoomPresenter();
    }

    @Override
    public LaboratoryRoomView createView() {
        return this;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    @OnClick({R.id.button_setting_query_condition, R.id.button_query, R.id.fab_import, R.id.fab_export, R.id.fab_add, R.id.fab_menu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_setting_query_condition:
                MaterialDialog dialog = new MaterialDialog.Builder(Objects.requireNonNull(getContext()))
                        .customView(R.layout.dialog_custom_query_condition_laboratory_room, true)
                        .title(R.string.title_set_query_condition)
                        .positiveText("确定")
                        .positiveColorRes(R.color.colorPrimary)
                        .negativeText("取消")
                        .negativeColorRes(R.color.colorPrimary)
                        .show();

                selected_affiliated_teaching_experiment_center = "全部";
                selected_affiliated_laboratory_name = "全部";
                selected_affiliated_experimental_compartment_name = "全部";
                selected_nature_of_experimental_site = "全部";
                selected_category_of_scientific_research_base = "全部";
                selected_status_of_joint_construction = "全部";
                selected_campus = "全部";
                selected_enable_flag = "全部";

                spinner_affiliated_teaching_experiment_center = dialog.getWindow().findViewById(R.id.spinner_affiliated_teaching_experiment_center);
                spinner_affiliated_laboratory_name = dialog.getWindow().findViewById(R.id.spinner_affiliated_laboratory_name);
                spinner_affiliated_experimental_compartment_name = dialog.getWindow().findViewById(R.id.spinner_affiliated_experimental_compartment_name);
                spinner_nature_of_experimental_site = dialog.getWindow().findViewById(R.id.spinner_nature_of_experimental_site);
                spinner_category_of_scientific_research_base = dialog.getWindow().findViewById(R.id.spinner_category_of_scientific_research_base);
                spinner_status_of_joint_construction = dialog.getWindow().findViewById(R.id.spinner_status_of_joint_construction);
                spinner_campus = dialog.getWindow().findViewById(R.id.spinner_campus);
                spinner_enable_flag = dialog.getWindow().findViewById(R.id.spinner_enable_flag);

                spinnerAffiliatedTeachingExperimentCenterArrayAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), R.layout.module_spinner_item, affiliatedTeachingExperimentCenterList);
                spinnerAffiliatedLaboratoryNameArrayAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), R.layout.module_spinner_item, affiliatedLaboratoryNameList);
                spinnerAffiliatedExperimentalCompartmentNameArrayAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), R.layout.module_spinner_item, affiliatedExperimentalCompartmentNameList);
                spinnerNatureOfExperimentalSiteArrayAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), R.layout.module_spinner_item, natureOfExperimentalSiteList);
                spinnerCategoryOfScientificResearchBaseArrayAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), R.layout.module_spinner_item, enableFlagList);
                spinnerStatusOfJointConstructionArrayAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), R.layout.module_spinner_item, categoryOfScientificResearchBaseList);
                spinnerCampusArrayAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), R.layout.module_spinner_item, campusList);
                spinnerEnableFlagArrayAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), R.layout.module_spinner_item, enableFlagList);

                spinnerAffiliatedTeachingExperimentCenterArrayAdapter.setDropDownViewResource(R.layout.module_spinner_item);
                spinnerAffiliatedLaboratoryNameArrayAdapter.setDropDownViewResource(R.layout.module_spinner_item);
                spinnerAffiliatedExperimentalCompartmentNameArrayAdapter.setDropDownViewResource(R.layout.module_spinner_item);
                spinnerNatureOfExperimentalSiteArrayAdapter.setDropDownViewResource(R.layout.module_spinner_item);
                spinnerCategoryOfScientificResearchBaseArrayAdapter.setDropDownViewResource(R.layout.module_spinner_item);
                spinnerStatusOfJointConstructionArrayAdapter.setDropDownViewResource(R.layout.module_spinner_item);
                spinnerCampusArrayAdapter.setDropDownViewResource(R.layout.module_spinner_item);
                spinnerEnableFlagArrayAdapter.setDropDownViewResource(R.layout.module_spinner_item);

                spinner_affiliated_teaching_experiment_center.setAdapter(spinnerAffiliatedTeachingExperimentCenterArrayAdapter);
                spinner_affiliated_laboratory_name.setAdapter(spinnerAffiliatedLaboratoryNameArrayAdapter);
                spinner_affiliated_experimental_compartment_name.setAdapter(spinnerAffiliatedExperimentalCompartmentNameArrayAdapter);
                spinner_nature_of_experimental_site.setAdapter(spinnerNatureOfExperimentalSiteArrayAdapter);
                spinner_category_of_scientific_research_base.setAdapter(spinnerCategoryOfScientificResearchBaseArrayAdapter);
                spinner_status_of_joint_construction.setAdapter(spinnerStatusOfJointConstructionArrayAdapter);
                spinner_campus.setAdapter(spinnerCampusArrayAdapter);
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
                        selected_affiliated_laboratory_name = affiliatedLaboratoryNameList.get(position);
                    }
                });

                spinner_affiliated_experimental_compartment_name.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                        selected_affiliated_experimental_compartment_name = affiliatedExperimentalCompartmentNameList.get(position);
                    }
                });

                spinner_nature_of_experimental_site.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                        selected_nature_of_experimental_site = natureOfExperimentalSiteList.get(position);
                    }
                });

                spinner_category_of_scientific_research_base.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                        selected_category_of_scientific_research_base = categoryOfScientificResearchBaseList.get(position);
                    }
                });

                spinner_status_of_joint_construction.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                        selected_status_of_joint_construction = statusOfJointConstructionList.get(position);
                    }
                });

                spinner_campus.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                        selected_campus = campusList.get(position);
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
                                    Objects.requireNonNull(getActivity()).runOnUiThread(() -> XToastUtils.toast("文件保存在:" + filePath));
                                    List<LaboratoryRoomTable> list = ExcelUtils.readExcelContent(filePath, LaboratoryRoomTable.getFields(), LaboratoryRoomTable.class);
                                    for (LaboratoryRoomTable laboratoryRoomTable : list) {
                                        getPresenter().insertData(laboratoryRoomTable);
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
                        ExcelUtils.createExcel(getContext(), "LaboratoryRoomTable", list, LaboratoryRoomTable.getFields(), LaboratoryRoomTable.getColumnNames());
                        Objects.requireNonNull(getActivity()).runOnUiThread(() -> XToastUtils.toast("导出成功,文件保存在:" + getActivity().getExternalFilesDir(null)));
                    }
                }.start();
                break;
            case R.id.fab_add:
                new MaterialDialog.Builder(Objects.requireNonNull(getContext()))
                        .customView(R.layout.dialog_custom_laboratory_room, true)
                        .title(R.string.title_add)
                        .positiveText("确定")
                        .positiveColorRes(R.color.colorPrimary)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                                edit_laboratory_room_code = dialog.findViewById(R.id.edit_laboratory_room_code);
                                edit_laboratory_room_name = dialog.findViewById(R.id.edit_laboratory_room_name);
                                edit_affiliated_experimental_compartment = dialog.findViewById(R.id.edit_affiliated_experimental_compartment);
                                edit_nature_of_experimental_site = dialog.findViewById(R.id.edit_nature_of_experimental_site);
                                edit_category_of_scientific_research_base = dialog.findViewById(R.id.edit_category_of_scientific_research_base);
                                edit_person_in_charge_of_the_experimental_room = dialog.findViewById(R.id.edit_person_in_charge_of_the_experimental_room);
                                edit_status_of_joint_construction = dialog.findViewById(R.id.edit_status_of_joint_construction);
                                edit_campus = dialog.findViewById(R.id.edit_campus);
                                edit_capacity = dialog.findViewById(R.id.edit_capacity);
                                edit_remarks = dialog.findViewById(R.id.edit_remarks);
                                edit_enable_flag = dialog.findViewById(R.id.edit_enable_flag);

                                edited_laboratory_room_code = edit_laboratory_room_code.getText().toString();
                                edited_laboratory_room_name = edit_laboratory_room_name.getText().toString();
                                edited_affiliated_experimental_compartment = edit_affiliated_experimental_compartment.getText().toString();
                                edited_nature_of_experimental_site = edit_nature_of_experimental_site.getText().toString();
                                edited_category_of_scientific_research_base = edit_category_of_scientific_research_base.getText().toString();
                                edited_person_in_charge_of_the_experimental_room = edit_person_in_charge_of_the_experimental_room.getText().toString();
                                edited_status_of_joint_construction = edit_status_of_joint_construction.getText().toString();
                                edited_campus = edit_campus.getText().toString();
                                edited_capacity = edit_capacity.getText().toString();
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

    private void insertData() {
        new Thread() {
            @Override
            public void run() {
                getPresenter().insertData(edited_laboratory_room_code,
                        edited_laboratory_room_name,
                        edited_affiliated_experimental_compartment,
                        edited_nature_of_experimental_site,
                        edited_category_of_scientific_research_base,
                        edited_person_in_charge_of_the_experimental_room,
                        edited_status_of_joint_construction,
                        edited_campus,
                        edited_capacity,
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
                        selected_affiliated_laboratory_name,
                        selected_affiliated_experimental_compartment_name,
                        selected_nature_of_experimental_site,
                        selected_category_of_scientific_research_base,
                        selected_status_of_joint_construction,
                        selected_campus,
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
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(2), "affiliated_experimental_compartment", affiliatedExperimentalCompartmentNameList);
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(3), "nature_of_experimental_site", natureOfExperimentalSiteList);
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(4), "category_of_scientific_research_base", categoryOfScientificResearchBaseList);
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(5), "status_of_joint_construction", statusOfJointConstructionList);
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(6), "campus", campusList);
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(7), "enable_flag", enableFlagList);
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
                list.addAll(JsonUtil.stringToList(responseJson.getJSONArray("data").toString(), LaboratoryRoomTable.class));
                table.setData(list);
            } catch (JSONException e) {
                Logger.e(e, "JSONException");
            }
        } else {
            llStateful.showEmpty();
        }
    }
}
