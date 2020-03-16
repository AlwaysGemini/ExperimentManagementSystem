package com.gemini.always.experimentmanagementsystem.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gemini.always.experimentmanagementsystem.R;
import com.gemini.always.experimentmanagementsystem.base.BaseFragment;
import com.gemini.always.experimentmanagementsystem.bean.tableBean.ExperimentProjectInstructionUploadTable;
import com.gemini.always.experimentmanagementsystem.custom.customTableView.MyTableView;
import com.gemini.always.experimentmanagementsystem.presenter.ExperimentProjectInstructionUploadPresenter;
import com.gemini.always.experimentmanagementsystem.util.JsonUtil;
import com.gemini.always.experimentmanagementsystem.util.XToastUtils;
import com.gemini.always.experimentmanagementsystem.view.ExperimentProjectInstructionUploadView;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.orhanobut.logger.Logger;
import com.thl.filechooser.FileChooser;
import com.thl.filechooser.FileInfo;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.statelayout.StatefulLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ExperimentProjectInstructionUploadFragment extends BaseFragment<ExperimentProjectInstructionUploadView, ExperimentProjectInstructionUploadPresenter> implements ExperimentProjectInstructionUploadView {

    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.table)
    MyTableView table;
    @BindView(R.id.ll_stateful)
    StatefulLayout llStateful;
    @BindView(R.id.fab_delete)
    FloatingActionButton fabDelete;
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

    private String title = "实验项目指导书提交";
    private Class tableClass = ExperimentProjectInstructionUploadTable.class;
    private List<ExperimentProjectInstructionUploadTable> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_fragment_base_my_table, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        fabExport.setVisibility(View.GONE);
        fabImport.setVisibility(View.GONE);
        fabAdd.setVisibility(View.GONE);
        fabDelete.setIcon(R.drawable.icon_upload);
        fabDelete.setTitle("上传");
        fabDelete.setVisibility(View.GONE);

        titlebar.setTitle(title);
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

    @Override
    public ExperimentProjectInstructionUploadPresenter createPresenter() {
        return new ExperimentProjectInstructionUploadPresenter();
    }

    @Override
    public ExperimentProjectInstructionUploadView createView() {
        return this;
    }

    private void getExperimentProjectInstructionState() {
        new Thread() {
            @Override
            public void run() {
                getPresenter().getExperimentProjectInstructionState();
            }
        }.start();
    }

    private void uploadExperimentProjectInstruction(String filePath,
                                                    String fileName,
                                                    String experiment_project_instruction_id) {
        new Thread() {
            @Override
            public void run() {
                getPresenter().uploadExperimentProjectInstruction(filePath, fileName, experiment_project_instruction_id);
            }
        }.start();
    }

    @Override
    public void onGetExperimentProjectInstructionStateResult(Boolean isSuccess, JSONObject responseJson) {
        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            if (isSuccess) {
                try {
                    llStateful.showContent();
                    list.clear();
                    list.addAll(JsonUtil.stringToList(responseJson.getJSONArray("data").toString(), tableClass));
                    table.setData(list, tableClass);
                    table.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            if (table.getIsShowCheckBox()) {
                                table.setCheckedPosition(position, !table.getIsCheckPosition(position));
                            }
                        }
                    });
                    table.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                            if (!table.getIsShowCheckBox()) {
                                table.setIsShowCheckBox(true);
                                table.setCheckedPosition(position, true);
                                ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1.0f, 0, 1.0f, 100, 100);
                                scaleAnimation.setDuration(500);
                                fabDelete.setVisibility(View.VISIBLE);
                                fabDelete.startAnimation(scaleAnimation);
                            }
                            return false;
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
    public void onUploadExperimentProjectInstructionResult(Boolean isSuccess, JSONObject responseJson) {
        if (isSuccess) {
            Objects.requireNonNull(getActivity()).runOnUiThread(() -> XToastUtils.toast("上传成功"));
        }
    }

    @OnClick({R.id.fab_delete, R.id.fab_import, R.id.fab_export, R.id.fab_add, R.id.fab_query, R.id.fab_menu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_query:
                getExperimentProjectInstructionState();
                break;
            case R.id.fab_import:

                break;
            case R.id.fab_export:

                break;
            case R.id.fab_add:

                break;
            case R.id.fab_menu:

                break;
            case R.id.fab_delete:
                if (table.getCheckedList().size() != 1) {
                    XToastUtils.error("只能单个上传");
                } else {
                    FileChooser fileChooser = new FileChooser(this, new FileChooser.FileChoosenListener() {
                        @Override
                        public void onFileChoosen(String filePath) {
                            new Thread() {
                                @Override
                                public void run() {
                                    uploadExperimentProjectInstruction(filePath,
                                            list.get(table.getCheckedList().get(0) - 1).getExperiment_item_name() + "项目指导书.pdf",
                                            list.get(table.getCheckedList().get(0) - 1).getExperiment_project_instruction_id());
                                }
                            }.start();
                        }
                    });
                    fileChooser.setTitle("选择需要提交的文件");
                    fileChooser.setDoneText("确定");
                    fileChooser.setChooseType(FileInfo.FILE_TYPE_FILE);
                    fileChooser.showFile(true);
                    fileChooser.setThemeColor(R.color.colorAccent);
                    fileChooser.open();
                }
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
