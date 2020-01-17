package com.gemini.always.experimentmanagementsystem.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.gemini.always.experimentmanagementsystem.ui.activity.FragmentSelectActivity;
import com.gemini.always.experimentmanagementsystem.bean.Item;
import com.gemini.always.experimentmanagementsystem.bean.Level0Item;
import com.gemini.always.experimentmanagementsystem.R;

import java.util.List;

public class ExpandableItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    private static final int TYPE_LEVEL_0 = 0;
    private static final int TYPE_Item = 1;

    private Context context;

    public ExpandableItemAdapter(Context context, List<MultiItemEntity> data) {
        super(data);
        this.context = context;
        addItemType(TYPE_LEVEL_0, R.layout.item_expandable_lv0);
        addItemType(TYPE_Item, R.layout.item_expandable_lv1);
    }

    @Override
    protected void convert(final BaseViewHolder holder, final MultiItemEntity item) {
        switch (holder.getItemViewType()) {
            case TYPE_LEVEL_0:
                //set view content
                final Level0Item item0 = (Level0Item) item;
                holder.setText(R.id.item_name,item0.getItemName());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getAdapterPosition();
                        //ToastUtil.showToast(context,((Level0Item) item).getItemName());
                        if (pos == 0){
                            FragmentSelectActivity.startFragmentSelecter(context,"CourseExperimentProjectFragment");
                        }
                        if (item0.isExpanded()) {
                            collapse(pos);
                        } else {
                            expand(pos);
                        }
                    }});
                break;
            case TYPE_Item:
                // similar with level 0
                final Item item1 = (Item) item;
                holder.setText(R.id.item_name, item1.getItemName());
                break;
        }
    }
}
