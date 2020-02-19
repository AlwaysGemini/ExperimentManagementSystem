package com.gemini.always.experimentmanagementsystem.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gemini.always.experimentmanagementsystem.R;
import com.gemini.always.experimentmanagementsystem.bean.AllocationExperimentItem;
import com.xuexiang.xui.widget.spinner.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

public class AllocationExperimentAdapter extends BaseQuickAdapter<AllocationExperimentItem, BaseViewHolder> {

    private Context context;
    private List<Integer> weekList = new ArrayList<>();
    private List<Integer> dayList = new ArrayList<>();
    private List<Integer> courseTimeList = new ArrayList<>();

    public AllocationExperimentAdapter(Context context, int layoutResId, @Nullable List<AllocationExperimentItem> data) {
        super(layoutResId, data);
        this.context = context;
        for (int i = 1; i <= 20; i++)
            weekList.add(i);
        for (int i = 1; i <= 7; i++)
            dayList.add(i);
        for (int i = 1; i <= 12; i++)
            courseTimeList.add(i);

    }

    @Override
    protected void convert(BaseViewHolder helper, AllocationExperimentItem item) {
        helper.setText(R.id.text_sequence, helper.getLayoutPosition() + "");
        helper.setText(R.id.text_experiment_name, item.getExperiment_name());
        MaterialSpinner spinner_week = helper.getView(R.id.spinner_week);
        MaterialSpinner spinner_day = helper.getView(R.id.spinner_day);
        MaterialSpinner spinner_start_time = helper.getView(R.id.spinner_start_time);
        MaterialSpinner spinner_end_time = helper.getView(R.id.spinner_end_time);
        ArrayAdapter<Integer> weekAdapter = new ArrayAdapter<>(context, R.layout.module_spinner_item, weekList);
        ArrayAdapter<Integer> dayAdapter = new ArrayAdapter<>(context, R.layout.module_spinner_item, dayList);
        ArrayAdapter<Integer> startTimeAdapter = new ArrayAdapter<>(context, R.layout.module_spinner_item, courseTimeList);
        ArrayAdapter<Integer> endTimeAdapter = new ArrayAdapter<>(context, R.layout.module_spinner_item, courseTimeList);
        weekAdapter.setDropDownViewResource(R.layout.module_spinner_item);
        dayAdapter.setDropDownViewResource(R.layout.module_spinner_item);
        startTimeAdapter.setDropDownViewResource(R.layout.module_spinner_item);
        endTimeAdapter.setDropDownViewResource(R.layout.module_spinner_item);
        spinner_week.setAdapter(weekAdapter);
        spinner_day.setAdapter(dayAdapter);
        spinner_start_time.setAdapter(startTimeAdapter);
        spinner_end_time.setAdapter(endTimeAdapter);
        spinner_week.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object object) {
                item.setWeek(weekList.get(position));
            }
        });
        spinner_day.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object object) {
                item.setWeek(dayList.get(position));
            }
        });
        spinner_start_time.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object object) {
                item.setWeek(courseTimeList.get(position));
            }
        });
        spinner_end_time.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object object) {
                item.setWeek(courseTimeList.get(position));
            }
        });
        //helper.getView(R.id.button_allocation).setOnClickListener();
    }
}
