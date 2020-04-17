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
import com.gemini.always.experimentmanagementsystem.bean.tableBean.ExperimentProjectInstructionCheckTable;
import com.gemini.always.experimentmanagementsystem.custom.customTableView.MyTableView;
import com.gemini.always.experimentmanagementsystem.presenter.ExperimentProjectInstructionCheckPresenter;
import com.gemini.always.experimentmanagementsystem.util.JsonUtil;
import com.gemini.always.experimentmanagementsystem.util.XToastUtils;
import com.gemini.always.experimentmanagementsystem.view.ExperimentProjectInstructionCheckView;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.orhanobut.logger.Logger;
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

public class ExperimentProjectInstructionCheckFragment extends BaseFragment<ExperimentProjectInstructionCheckView, ExperimentProjectInstructionCheckPresenter> implements ExperimentProjectInstructionCheckView, View.OnClickListener {

    private static String TAG = "ExperimentProjectInstructionCheckFragment";

    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.table)
    MyTableView table;
    @BindView(R.id.ll_stateful)
    StatefulLayout llStateful;
    @BindView(R.id.fab_download)
    FloatingActionButton fabDownload;
    @BindView(R.id.fab_query)
    FloatingActionButton fabQuery;
    @BindView(R.id.fab_menu)
    FloatingActionsMenu fabMenu;
    Unbinder unbinder;

    private String title = "实验项目指导书查看";
    private Class tableClass = ExperimentProjectInstructionCheckTable.class;
    private List<ExperimentProjectInstructionCheckTable> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_fragment_experiment_project_instruction_check, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initView() {
        fabDownload.setVisibility(View.GONE);

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
    public ExperimentProjectInstructionCheckPresenter createPresenter() {
        return new ExperimentProjectInstructionCheckPresenter();
    }

    @Override
    public ExperimentProjectInstructionCheckView createView() {
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

    private void download(final String url) {
        new Thread() {
            @Override
            public void run() {
                getPresenter().download("/" + url, Objects.requireNonNull(Objects.requireNonNull(getActivity()).getExternalFilesDir(null)).getAbsolutePath(), url);
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
                                fabDownload.setVisibility(View.VISIBLE);
                                fabDownload.startAnimation(scaleAnimation);
                            }
                            return false;
                        }
                    });
                } catch (JSONException e) {
                    Logger.e(e, TAG);
                }
            } else {
                try {
                    XToastUtils.toast(responseJson.getString("msg"));
                } catch (JSONException e) {
                    Logger.e(e, TAG);
                }
                llStateful.showEmpty();
            }
        });
    }

    @Override
    public void onDownloadExperimentProjectInstructionResult(Boolean isSuccess, JSONObject responseJson) {
        if (isSuccess) {
            Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
                try {
                    XToastUtils.toast(responseJson.getString("msg"));
                } catch (JSONException e) {
                    Logger.e(e, TAG);
                }
            });
        }
    }

    @OnClick({R.id.fab_download, R.id.fab_query, R.id.fab_menu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_download:
                download(list.get(table.getCheckedList().get(0) - 1).getFile_name());
                break;
            case R.id.fab_query:
                getExperimentProjectInstructionState();
                break;
            case R.id.fab_menu:
                break;
        }
    }
}
