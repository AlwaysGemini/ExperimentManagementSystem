package com.gemini.always.experimentmanagementsystem.ui.activity;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.gemini.always.experimentmanagementsystem.R;
import com.gemini.always.experimentmanagementsystem.adapter.ExpandableItemAdapter;
import com.gemini.always.experimentmanagementsystem.bean.Item;
import com.gemini.always.experimentmanagementsystem.bean.Level0Item;
import com.gemini.always.experimentmanagementsystem.bean.User;
import com.gemini.always.experimentmanagementsystem.util.XToastUtils;
import com.githang.statusbar.StatusBarCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.chad.library.adapter.base.BaseQuickAdapter.SCALEIN;

public class MainActivity extends AppCompatActivity {

    private ExpandableItemAdapter adapter;
    private List<MultiItemEntity> data = new ArrayList<>();
    Level0Item[] level0Item = new Level0Item[8];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StatusBarCompat.setStatusBarColor(this,Color.parseColor("#FF108EE9"));
        /*if (!User.isLogin()){
            FragmentSelectActivity.startFragmentSelecter(this,"LoginFragment");
            finish();
            return;
        }*/

        initView();
        initData();
    }

    private void initView(){

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        //创建布局管理
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        //创建适配器
        adapter = new ExpandableItemAdapter(this, data);
        //开启动画（默认为渐显效果）
        adapter.openLoadAnimation(SCALEIN);
        //设置不重复执行动画
        adapter.isFirstOnly(true);
        adapter.setUpFetchEnable(true);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onItemClick(final BaseQuickAdapter adapter, View view, final int position) {
                switch (((Item)(Objects.requireNonNull(adapter.getItem(position)))).getItemName()){
                    case "实验机构":
                        FragmentSelectActivity.startFragmentSelecter(getApplicationContext(),"ExperimentalOrganizationFragment");
                        break;
                }
            }
        });

        recyclerView.setAdapter(adapter);
    }

    private void initData(){
        level0Item[0] = new Level0Item("实验课程项目");
        data.add(level0Item[0]);

        level0Item[1] = new Level0Item("实验资源管理");
        level0Item[1].addSubItem(new Item("实验机构"));
        level0Item[1].addSubItem(new Item("实验人员管理"));
        level0Item[1].addSubItem(new Item("实验仪器设备管理"));
        level0Item[1].addSubItem(new Item("实验耗材管理"));
        level0Item[1].addSubItem(new Item("代码维护"));
        data.add(level0Item[1]);

        level0Item[2] = new Level0Item("实验项目管理");
        level0Item[2].addSubItem(new Item("实验项目管理"));
        level0Item[2].addSubItem(new Item("课程实验大纲"));
        data.add(level0Item[2]);

        level0Item[3] = new Level0Item("实验开课排课");
        level0Item[3].addSubItem(new Item("实验教学班维护"));
        level0Item[3].addSubItem(new Item("实验教学任务书"));
        level0Item[3].addSubItem(new Item("实验项目教学任务书"));
        level0Item[3].addSubItem(new Item("实验排课"));
        level0Item[3].addSubItem(new Item("实验项目指导书审核"));
        level0Item[3].addSubItem(new Item("实验项目指导书查看"));
        data.add(level0Item[3]);

        level0Item[4] = new Level0Item("实验选课管理");
        level0Item[4].addSubItem(new Item("选课规则设置"));
        level0Item[4].addSubItem(new Item("选课名单调整"));
        level0Item[4].addSubItem(new Item("生成配课"));
        data.add(level0Item[4]);

        level0Item[5] = new Level0Item("实验成绩管理");
        level0Item[5].addSubItem(new Item("实验项目成绩录入"));
        level0Item[5].addSubItem(new Item("实验成绩汇总入"));
        level0Item[5].addSubItem(new Item("实验成绩审核"));
        level0Item[5].addSubItem(new Item("实验考勤成绩"));
        data.add(level0Item[5]);

        level0Item[6] = new Level0Item("开放性实验管理");
        level0Item[6].addSubItem(new Item("项目申请"));
        level0Item[6].addSubItem(new Item("项目审核"));
        level0Item[6].addSubItem(new Item("实验安排"));
        level0Item[6].addSubItem(new Item("预约控制"));
        level0Item[6].addSubItem(new Item("名单调整"));
        level0Item[6].addSubItem(new Item("成绩管理"));
        data.add(level0Item[6]);

        level0Item[7] = new Level0Item("实验课表打印");
        level0Item[7].addSubItem(new Item("课程课表打印"));
        level0Item[7].addSubItem(new Item("教师课表打印"));
        level0Item[7].addSubItem(new Item("场地课表打印"));
        data.add(level0Item[7]);

        adapter.notifyDataSetChanged();
    }
}
