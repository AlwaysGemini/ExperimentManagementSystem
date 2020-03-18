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
import com.gemini.always.experimentmanagementsystem.bean.tableBean.ExperimentProjectInstructionExaminingTable;
import com.gemini.always.experimentmanagementsystem.custom.customTableView.MyTableView;
import com.gemini.always.experimentmanagementsystem.presenter.ExperimentProjectInstructionExaminingPresenter;
import com.gemini.always.experimentmanagementsystem.util.JsonUtil;
import com.gemini.always.experimentmanagementsystem.util.XToastUtils;
import com.gemini.always.experimentmanagementsystem.view.ExperimentProjectInstructionExaminingView;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.orhanobut.logger.Logger;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
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

public class ExperimentProjectInstructionExaminingFragment extends BaseFragment<ExperimentProjectInstructionExaminingView, ExperimentProjectInstructionExaminingPresenter> implements ExperimentProjectInstructionExaminingView, View.OnClickListener {

    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.table)
    MyTableView table;
    @BindView(R.id.ll_stateful)
    StatefulLayout llStateful;
    @BindView(R.id.fab_examining)
    FloatingActionButton fabExamining;
    @BindView(R.id.fab_query)
    FloatingActionButton fabQuery;
    @BindView(R.id.fab_menu)
    FloatingActionsMenu fabMenu;
    Unbinder unbinder;

    private String title = "实验项目指导书审核";
    private Class tableClass = ExperimentProjectInstructionExaminingTable.class;
    private List<ExperimentProjectInstructionExaminingTable> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_fragment_experiment_project_instruction_examining, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public ExperimentProjectInstructionExaminingPresenter createPresenter() {
        return new ExperimentProjectInstructionExaminingPresenter();
    }

    @Override
    public ExperimentProjectInstructionExaminingView createView() {
        initView();

        return this;
    }

    private void initView() {
        fabExamining.setVisibility(View.GONE);

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

    private void getExperimentProjectInstructionState() {
        new Thread() {
            @Override
            public void run() {
                getPresenter().getExperimentProjectInstructionState();
            }
        }.start();
    }

    private void examining(String experiment_project_instruction_id,
                           String state) {
        new Thread() {
            @Override
            public void run() {
                getPresenter().examining(experiment_project_instruction_id, state);
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
                                fabExamining.setVisibility(View.VISIBLE);
                                fabExamining.startAnimation(scaleAnimation);
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
    public void onExaminingExperimentProjectInstructionResult(Boolean isSuccess, JSONObject responseJson) {
        if (isSuccess) {
            Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
                try {
                    XToastUtils.toast(responseJson.getString("msg"));
                } catch (JSONException e) {
                    Logger.e(e, "JSONException:");
                }
            });
        }
    }

    @OnClick({R.id.fab_examining, R.id.fab_query})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_examining:
                ArrayList<String> items = new ArrayList<>();
                items.add("审核通过");
                items.add("审核不通过");
                new MaterialDialog.Builder(Objects.requireNonNull(getActivity()))
                        .title("审核")
                        .items(items)
                        .itemsCallbackSingleChoice(
                                0,
                                new MaterialDialog.ListCallbackSingleChoice() {
                                    @Override
                                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                        examining(list.get(table.getCheckedList().get(0) - 1).getExperiment_project_instruction_id(),
                                                text.toString());
                                        return true;
                                    }
                                })
                        .positiveText(R.string.btn_confirm)
                        .negativeText(R.string.btn_cancel)
                        .show();

                break;
            case R.id.fab_query:
                getExperimentProjectInstructionState();
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
