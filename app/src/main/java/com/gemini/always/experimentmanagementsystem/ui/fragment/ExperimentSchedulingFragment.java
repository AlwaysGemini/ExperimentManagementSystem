package com.gemini.always.experimentmanagementsystem.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gemini.always.experimentmanagementsystem.R;
import com.gemini.always.experimentmanagementsystem.adapter.AllocationExperimentAdapter;
import com.gemini.always.experimentmanagementsystem.base.BaseFragment;
import com.gemini.always.experimentmanagementsystem.bean.AllocationExperimentItem;
import com.gemini.always.experimentmanagementsystem.custom.courseTimeTable.CourseTimeTableView;
import com.gemini.always.experimentmanagementsystem.presenter.ExperimentSchedulingPresenter;
import com.gemini.always.experimentmanagementsystem.view.ExperimentSchedulingView;
import com.orhanobut.logger.Logger;
import com.xuexiang.xui.widget.actionbar.TitleBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ExperimentSchedulingFragment extends BaseFragment<ExperimentSchedulingView, ExperimentSchedulingPresenter> implements ExperimentSchedulingView {

    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.spinner_week)
    Spinner spinnerWeek;
    @BindView(R.id.courseTimeTable)
    CourseTimeTableView courseTimeTable;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;

    private int[][][] freeTimeData = new int[20][7][12];
    private List<AllocationExperimentItem> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_fragment_experiment_scheduling, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        initData();
    }

    private void initView() {
        titlebar.setTitle("实验排课");
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
        List<Integer> weekList = new ArrayList<>();
        for (int i = 1; i <= 20; i++)
            weekList.add(i);
        ArrayAdapter<Integer> weekAdapter = new ArrayAdapter<>(getActivity(), R.layout.module_spinner_item, weekList);
        weekAdapter.setDropDownViewResource(R.layout.module_spinner_item);
        spinnerWeek.setAdapter(weekAdapter);
        spinnerWeek.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Objects.requireNonNull(getActivity()).runOnUiThread(() -> courseTimeTable.setFreeTime(freeTimeData, weekList.get(position) - 1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        AllocationExperimentItem allocationExperimentItem = new AllocationExperimentItem();
        allocationExperimentItem.setExperiment_name("测试课程");
        list.add(allocationExperimentItem);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getActivity()), DividerItemDecoration.VERTICAL));
        AllocationExperimentAdapter adapter = new AllocationExperimentAdapter(getContext(), R.layout.module_item_allocation_experiment, list);
        adapter.removeAllHeaderView();
        View view = View.inflate(getContext(), R.layout.module_head_allocation_experiment, null);
        adapter.addHeaderView(view);
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        new Thread() {
            @Override
            public void run() {
                getPresenter().getFreeTimeData();
            }
        }.start();
    }

    @Override
    public ExperimentSchedulingPresenter createPresenter() {
        return new ExperimentSchedulingPresenter();
    }

    @Override
    public ExperimentSchedulingView createView() {
        return this;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onInsertDataResult(Boolean isSuccess, JSONObject responseJson) {

    }

    @Override
    public void getFreeTimeDataResult(Boolean isSuccess, JSONObject responseJson) {
        if (isSuccess) {
            try {
                JSONArray array = responseJson.getJSONArray("data");
                for (int week = 0; week < array.length(); week++) {
                    JSONArray dayArray = array.getJSONArray(week);
                    for (int day = 0; day < dayArray.length(); day++) {
                        JSONArray courseTimeArray = dayArray.getJSONArray(day);
                        for (int courseTime = 0; courseTime < courseTimeArray.length(); courseTime++)
                            freeTimeData[week][day][courseTime] = courseTimeArray.getInt(courseTime);
                    }
                }
                Objects.requireNonNull(getActivity()).runOnUiThread(() -> courseTimeTable.setFreeTime(freeTimeData, 0));
            } catch (JSONException e) {
                Logger.e(e, "JSONException:");
            }
        }
    }
}
