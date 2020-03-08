package com.gemini.always.experimentmanagementsystem.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gemini.always.experimentmanagementsystem.R;
import com.gemini.always.experimentmanagementsystem.bean.AllocationExperimentItem;

import java.util.List;

public class AllocationExperimentAdapter extends BaseQuickAdapter<AllocationExperimentItem, BaseViewHolder> {

    public AllocationExperimentAdapter(int layoutResId, @Nullable List<AllocationExperimentItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AllocationExperimentItem item) {
        helper.setText(R.id.text_sequence, helper.getLayoutPosition() + "");
        helper.setText(R.id.text_experiment_item_name, item.getExperiment_item_name());
        helper.addOnClickListener(R.id.button_allocation);
    }
}
