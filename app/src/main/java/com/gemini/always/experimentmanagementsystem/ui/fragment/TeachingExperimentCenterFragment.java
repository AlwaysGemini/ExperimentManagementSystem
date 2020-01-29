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
import com.bin.david.form.data.column.Column;
import com.bin.david.form.data.table.TableData;
import com.gemini.always.experimentmanagementsystem.DataProvider;
import com.gemini.always.experimentmanagementsystem.R;
import com.gemini.always.experimentmanagementsystem.base.BaseFragment;
import com.gemini.always.experimentmanagementsystem.bean.TeachingExperimentCenterTable;
import com.gemini.always.experimentmanagementsystem.presenter.TeachingExperimentCenterPresenter;
import com.gemini.always.experimentmanagementsystem.util.JsonUtil;
import com.gemini.always.experimentmanagementsystem.util.ListUtil;
import com.gemini.always.experimentmanagementsystem.util.XToastUtils;
import com.gemini.always.experimentmanagementsystem.view.TeachingExperimentCenterView;
import com.orhanobut.logger.Logger;
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
import butterknife.Unbinder;

public class TeachingExperimentCenterFragment extends BaseFragment<TeachingExperimentCenterView, TeachingExperimentCenterPresenter> implements TeachingExperimentCenterView, View.OnClickListener {

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
    @BindView(R.id.button_add)
    FloatingActionButton buttonAdd;

    private EditText edit_code;
    private EditText edit_name;
    private EditText edit_type;
    private EditText edit_subordinate_unit;
    private EditText edit_subordinate_discipline;
    private EditText edit_year_of_establishment;
    private EditText edit_remarks;
    private EditText edit_enable_flag;

    private String edited_code = "";
    private String edited_name = "";
    private String edited_type = "";
    private String edited_subordinate_unit = "";
    private String edited_subordinate_discipline = "";
    private String edited_year_of_establishment = "";
    private String edited_remarks = "";
    private String edited_enable_flag = "";

    private MaterialSpinner spinner_laboratory_type;
    private MaterialSpinner spinner_enable_flag;

    private String selected_laboratory_type = "全部";
    private String selected_enable_flag = "全部";

    private ArrayAdapter<String> spinnerLaboratoryTypeArrayAdapter;
    private List<String> laboratoryTypeList = new ArrayList<>();
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

        buttonSettingQueryCondition.setOnClickListener(this);
        buttonQuery.setOnClickListener(this);
        buttonAdd.setOnClickListener(this);
    }

    @Override
    public TeachingExperimentCenterPresenter createPresenter() {
        return new TeachingExperimentCenterPresenter();
    }

    @Override
    public TeachingExperimentCenterView createView() {
        return this;
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
                        .customView(R.layout.dialog_custom_query_condition_teaching_experiment_center, true)
                        .title("增加")
                        .positiveText("确定")
                        .positiveColorRes(R.color.colorPrimary)
                        .negativeText("取消")
                        .negativeColorRes(R.color.colorPrimary)
                        .show();

                selected_laboratory_type = "全部";
                selected_enable_flag = "全部";

                spinner_laboratory_type = dialog.getWindow().findViewById(R.id.spinner_laboratory_type);
                spinner_enable_flag = dialog.getWindow().findViewById(R.id.spinner_enable_flag);

                spinnerLaboratoryTypeArrayAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), R.layout.module_spinner_item, laboratoryTypeList);
                spinnerEnableFlagArrayAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), R.layout.module_spinner_item, enableFlagList);

                spinnerLaboratoryTypeArrayAdapter.setDropDownViewResource(R.layout.module_spinner_item);
                spinnerEnableFlagArrayAdapter.setDropDownViewResource(R.layout.module_spinner_item);

                spinner_laboratory_type.setAdapter(spinnerLaboratoryTypeArrayAdapter);
                spinner_enable_flag.setAdapter(spinnerEnableFlagArrayAdapter);

                spinner_laboratory_type.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                        selected_laboratory_type = laboratoryTypeList.get(position);
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
            case R.id.button_add:
                new MaterialDialog.Builder(Objects.requireNonNull(getContext()))
                        .customView(R.layout.dialog_custom_teaching_experiment_center, true)
                        .title("增加")
                        .positiveText("确定")
                        .positiveColorRes(R.color.colorPrimary)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                edit_code = dialog.findViewById(R.id.edit_code);
                                edit_name = dialog.findViewById(R.id.edit_name);
                                edit_type = dialog.findViewById(R.id.edit_type);
                                edit_subordinate_unit = dialog.findViewById(R.id.edit_subordinate_unit);
                                edit_subordinate_discipline = dialog.findViewById(R.id.edit_subordinate_discipline);
                                edit_year_of_establishment = dialog.findViewById(R.id.edit_year_of_establishment);
                                edit_remarks = dialog.findViewById(R.id.edit_remarks);
                                edit_enable_flag = dialog.findViewById(R.id.edit_enable_flag);

                                edited_code = edit_code.getText().toString();
                                edited_name = edit_name.getText().toString();
                                edited_type = edit_type.getText().toString();
                                edited_subordinate_unit = edit_subordinate_unit.getText().toString();
                                edited_subordinate_discipline = edit_subordinate_discipline.getText().toString();
                                edited_year_of_establishment = edit_year_of_establishment.getText().toString();
                                edited_remarks = edit_remarks.getText().toString();
                                edited_enable_flag = edit_enable_flag.getText().toString();

                                insertData();
                            }
                        })
                        .negativeText("取消")
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                            }
                        })
                        .negativeColorRes(R.color.colorPrimary)
                        .show();
                break;
        }
    }

    private void insertData() {
        new Thread() {
            @Override
            public void run() {
                getPresenter().insertData(edited_code,
                        edited_name,
                        edited_type,
                        edited_subordinate_unit,
                        edited_subordinate_discipline,
                        edited_year_of_establishment,
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
                getPresenter().getData(selected_laboratory_type, selected_enable_flag);
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
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(0), "laboratory_type", laboratoryTypeList);
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(1), "enable_flag", enableFlagList);
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
                table.setData(JsonUtil.stringToList(responseJson.getJSONArray("data").toString(), TeachingExperimentCenterTable.class));
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
    }
}
