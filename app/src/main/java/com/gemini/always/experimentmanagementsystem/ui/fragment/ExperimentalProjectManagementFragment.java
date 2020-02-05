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
import com.bin.david.form.data.column.Column;
import com.bin.david.form.data.table.TableData;
import com.gemini.always.experimentmanagementsystem.R;
import com.gemini.always.experimentmanagementsystem.base.BaseFragment;
import com.gemini.always.experimentmanagementsystem.bean.ExperimentalProjectTable;
import com.gemini.always.experimentmanagementsystem.presenter.ExperimentalProjectManagementPresenter;
import com.gemini.always.experimentmanagementsystem.util.ExcelUtils;
import com.gemini.always.experimentmanagementsystem.util.FileUtils;
import com.gemini.always.experimentmanagementsystem.util.JsonUtil;
import com.gemini.always.experimentmanagementsystem.util.ListUtil;
import com.gemini.always.experimentmanagementsystem.util.XToastUtils;
import com.gemini.always.experimentmanagementsystem.view.ExperimentalProjectManagementView;
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

public class ExperimentalProjectManagementFragment extends BaseFragment<ExperimentalProjectManagementView, ExperimentalProjectManagementPresenter> implements ExperimentalProjectManagementView {

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

    private List<ExperimentalProjectTable> list = new ArrayList<>();

    private EditText edit_experimental_project_code;
    private EditText edit_experimental_project_name;
    private EditText edit_experimental_content;
    private EditText edit_experimental_hours;
    private EditText edit_experimental_credits;
    private EditText edit_experimental_properties;
    private EditText edit_experimental_type;
    private EditText edit_experimental_category;
    private EditText edit_affiliation;
    private EditText edit_subject;

    private String edited_experimental_project_code = "";
    private String edited_experimental_project_name = "";
    private String edited_experimental_content = "";
    private String edited_experimental_hours = "";
    private String edited_experimental_credits = "";
    private String edited_experimental_properties = "";
    private String edited_experimental_type = "";
    private String edited_experimental_category = "";
    private String edited_affiliation = "";
    private String edited_subject = "";

    private MaterialSpinner spinner_experimental_properties;
    private MaterialSpinner spinner_experimental_type;
    private MaterialSpinner spinner_experimental_category;
    private EditText edit_experimental_project_name_for_query;

    private String selected_experimental_properties = "全部";
    private String selected_experimental_type = "全部";
    private String selected_experimental_category = "全部";
    private String edited_experimental_project_name_for_query = "";

    private ArrayAdapter<String> spinnerExperimentalPropertiesCenterArrayAdapter;
    private List<String> experimentalPropertiesList = new ArrayList<>();
    private ArrayAdapter<String> spinnerExperimentalTypeArrayAdapter;
    private List<String> experimentalTypeList = new ArrayList<>();
    private ArrayAdapter<String> spinnerExperimentalCategoryArrayAdapter;
    private List<String> experimentalCategoryList = new ArrayList<>();

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
        titlebar.setTitle("实验项目管理");
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
    public ExperimentalProjectManagementPresenter createPresenter() {
        return new ExperimentalProjectManagementPresenter();
    }

    @Override
    public ExperimentalProjectManagementView createView() {
        return this;
    }

    private void insertData() {
        new Thread() {
            @Override
            public void run() {
                getPresenter().insertData(edited_experimental_project_code,
                        edited_experimental_project_name,
                        edited_experimental_content,
                        edited_experimental_hours,
                        edited_experimental_credits,
                        edited_experimental_properties,
                        edited_experimental_type,
                        edited_experimental_category,
                        edited_affiliation,
                        edited_subject);
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
        edited_experimental_project_name_for_query = edit_experimental_project_name_for_query.getText().toString();
        new Thread() {
            @Override
            public void run() {
                getPresenter().getData(selected_experimental_properties,
                        selected_experimental_type,
                        selected_experimental_category,
                        edited_experimental_project_name_for_query);
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
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(0), "experimental_properties", experimentalPropertiesList);
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(1), "experimental_type", experimentalTypeList);
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(2), "experimental_category", experimentalCategoryList);
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
                    list.addAll(JsonUtil.stringToList(responseJson.getJSONArray("data").toString(), ExperimentalProjectTable.class));
                    table.setData(list);
                    table.getTableData().setOnRowClickListener(new TableData.OnRowClickListener() {
                        @Override
                        public void onClick(Column column, Object o, int col, int row) {

                            new MaterialDialog.Builder(Objects.requireNonNull(getContext()))
                                    .customView(R.layout.dialog_custom_experiment_compartment, true)
                                    .title("修改或者删除数据此条数据")
                                    .positiveText("修改")
                                    .positiveColorRes(R.color.colorPrimary)
                                    .neutralText("删除")
                                    .onNeutral(new MaterialDialog.SingleButtonCallback() {
                                        @Override
                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                            XToastUtils.error("删除");
                                        }
                                    })
                                    .negativeText("取消")
                                    .negativeColorRes(R.color.colorPrimary)
                                    .show();
                        }
                    });
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

    @OnClick({R.id.fab_import, R.id.fab_export, R.id.fab_add, R.id.fab_query, R.id.fab_menu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_query:
                MaterialDialog dialog = new MaterialDialog.Builder(Objects.requireNonNull(getContext()))
                        .customView(R.layout.dialog_custom_query_condition_experimental_project, true)
                        .title(R.string.title_set_query_condition)
                        .positiveText("确定")
                        .positiveColorRes(R.color.colorPrimary)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                getData();
                            }
                        })
                        .negativeText("取消")
                        .negativeColorRes(R.color.colorPrimary)
                        .show();

                selected_experimental_properties = "全部";
                selected_experimental_type = "全部";
                selected_experimental_category = "全部";
                edited_experimental_project_name_for_query = "";

                spinner_experimental_properties = dialog.getWindow().findViewById(R.id.spinner_experimental_properties);
                spinner_experimental_type = dialog.getWindow().findViewById(R.id.spinner_experimental_type);
                spinner_experimental_category = dialog.getWindow().findViewById(R.id.spinner_experimental_category);
                edit_experimental_project_name_for_query = dialog.getWindow().findViewById(R.id.edit_experimental_project_name);

                spinnerExperimentalPropertiesCenterArrayAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), R.layout.module_spinner_item, experimentalPropertiesList);
                spinnerExperimentalTypeArrayAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), R.layout.module_spinner_item, experimentalTypeList);
                spinnerExperimentalCategoryArrayAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), R.layout.module_spinner_item, experimentalCategoryList);

                spinnerExperimentalPropertiesCenterArrayAdapter.setDropDownViewResource(R.layout.module_spinner_item);
                spinnerExperimentalTypeArrayAdapter.setDropDownViewResource(R.layout.module_spinner_item);
                spinnerExperimentalCategoryArrayAdapter.setDropDownViewResource(R.layout.module_spinner_item);

                spinner_experimental_properties.setAdapter(spinnerExperimentalPropertiesCenterArrayAdapter);
                spinner_experimental_type.setAdapter(spinnerExperimentalTypeArrayAdapter);
                spinner_experimental_category.setAdapter(spinnerExperimentalCategoryArrayAdapter);

                spinner_experimental_properties.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                        selected_experimental_properties = experimentalPropertiesList.get(position);
                    }
                });

                spinner_experimental_type.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                        selected_experimental_type = experimentalTypeList.get(position);
                    }
                });

                spinner_experimental_category.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                        selected_experimental_category = experimentalCategoryList.get(position);
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
                                    List<ExperimentalProjectTable> list = ExcelUtils.readExcelContent(filePath, ExperimentalProjectTable.class);
                                    for (ExperimentalProjectTable experimentalProjectTable : list) {
                                        getPresenter().insertData(experimentalProjectTable);
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
                        ExcelUtils.createExcel(getContext(), "实验分室表格", list, ExperimentalProjectTable.class);
                        Objects.requireNonNull(getActivity()).runOnUiThread(() -> XToastUtils.toast("导出成功,文件保存在:" + getActivity().getExternalFilesDir(null)));
                    }
                }.start();
                break;
            case R.id.fab_add:
                new MaterialDialog.Builder(Objects.requireNonNull(getContext()))
                        .customView(R.layout.dialog_custom_experiment_project, true)
                        .title("增加")
                        .positiveText("确定")
                        .positiveColorRes(R.color.colorPrimary)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                                edit_experimental_project_code = dialog.findViewById(R.id.edit_experimental_project_code);
                                edit_experimental_project_name = dialog.findViewById(R.id.edit_experimental_project_name);
                                edit_experimental_content = dialog.findViewById(R.id.edit_experimental_content);
                                edit_experimental_hours = dialog.findViewById(R.id.edit_experimental_hours);
                                edit_experimental_credits = dialog.findViewById(R.id.edit_experimental_credits);
                                edit_experimental_properties = dialog.findViewById(R.id.edit_experimental_properties);
                                edit_experimental_type = dialog.findViewById(R.id.edit_experimental_type);
                                edit_experimental_category = dialog.findViewById(R.id.edit_experimental_category);
                                edit_affiliation = dialog.findViewById(R.id.edit_affiliation);
                                edit_subject = dialog.findViewById(R.id.edit_subject);

                                edited_experimental_project_code = edit_experimental_project_code.getText().toString();
                                edited_experimental_project_name = edit_experimental_project_name.getText().toString();
                                edited_experimental_content = edit_experimental_content.getText().toString();
                                edited_experimental_hours = edit_experimental_hours.getText().toString();
                                edited_experimental_credits = edit_experimental_credits.getText().toString();
                                edited_experimental_properties = edit_experimental_properties.getText().toString();
                                edited_experimental_type = edit_experimental_type.getText().toString();
                                edited_experimental_category = edit_experimental_category.getText().toString();
                                edited_affiliation = edit_affiliation.getText().toString();
                                edited_subject = edit_subject.getText().toString();

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
