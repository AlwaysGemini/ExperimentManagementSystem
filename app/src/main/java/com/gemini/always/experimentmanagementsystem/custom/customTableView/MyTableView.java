package com.gemini.always.experimentmanagementsystem.custom.customTableView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gemini.always.experimentmanagementsystem.R;

import java.util.List;

/**
 * @Author: 周清
 * @Description:
 * @Date: Created in 15:30 2020/2/9
 */
public class MyTableView extends RelativeLayout {

    private Context context;
    private RecyclerView recyclerView;
    private TableAdapter<Object> adapter;

    public MyTableView(Context context) {
        super(context);
        initView(context);
    }

    public MyTableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView(context);
    }

    public MyTableView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.module_view_recycler, this, true);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    public <T> void setData(List<T> list, Class clazz) {
        //创建适配器
        adapter = new TableAdapter(context, R.layout.item_table, list, clazz);
        adapter.setUpFetchEnable(true);
        if (list.size() != 0) {
            adapter.removeAllHeaderView();
            adapter.addHeaderView();
        }
        recyclerView.setAdapter(adapter);
    }

    public void setOnItemClickListener(BaseQuickAdapter.OnItemClickListener onItemClickListener) {
        adapter.setOnItemClickListener(onItemClickListener);
    }

    public void setOnItemLongClickListener(BaseQuickAdapter.OnItemLongClickListener onItemLongClickListener) {
        adapter.setOnItemLongClickListener(onItemLongClickListener);
    }

    public boolean getIsShowCheckBox() {
        return adapter.getIsShowCheckBox();
    }

    public void setIsShowCheckBox(boolean isShowCheckBox) {
        adapter.showCheckBox(isShowCheckBox);
        adapter.setHeadIsChecked(false);
    }

    public void setCheckedPosition(int position, boolean isChecked) {
        adapter.setCheckedPosition(position, isChecked);
    }

    public boolean getIsCheckPosition(int position) {
        return adapter.getIsCheckPosition(position);
    }

    public void setHeadIsChecked(Boolean isChecked) {
        adapter.setHeadIsChecked(isChecked);
    }

    public void notifyDataSetChanged() {
        adapter.notifyDataSetChanged();
    }

    public List<Integer> getCheckedList() {
        return adapter.getCheckedList();
    }
}
