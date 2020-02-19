package com.gemini.always.experimentmanagementsystem.custom.courseTimeTable;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.gemini.always.experimentmanagementsystem.R;
import com.gemini.always.experimentmanagementsystem.util.DensityUtils;

public class CourseTimeTableView extends LinearLayout {

    private static final int weekNum = 20;
    private static final int dayNum = 7;
    private static final int courseTimeNum = 12;
    private static final int cellHeight = 75;
    private Context context;
    private RelativeLayout[] days = new RelativeLayout[7];
    private LinearLayout[][] cell = new LinearLayout[dayNum][courseTimeNum];

    public CourseTimeTableView(Context context) {
        super(context);
        initView(context);
    }

    public CourseTimeTableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CourseTimeTableView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public CourseTimeTableView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        View.inflate(context, R.layout.module_view_course_time_table, this);    //挂载view,正确方法
        //View view = LayoutInflater.from(context).inflate(R.layout.module_view_course_time_table,null); //错误方法
        days[0] = findViewById(R.id.line_Monday);
        days[1] = findViewById(R.id.line_Tuesday);
        days[2] = findViewById(R.id.line_Wednesday);
        days[3] = findViewById(R.id.line_Thursday);
        days[4] = findViewById(R.id.line_Friday);
        days[5] = findViewById(R.id.line_Saturday);
        days[6] = findViewById(R.id.line_Sunday);
        for (int day = 0; day < dayNum; day++)
            for (int courseTime = 0; courseTime < courseTimeNum; courseTime++) {
                cell[day][courseTime] = new LinearLayout(context);
                RelativeLayout.LayoutParams RP_MM = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DensityUtils.dip2px(cellHeight));
                RP_MM.setMargins(0, DensityUtils.dip2px(courseTime * cellHeight), 0, 0);
                cell[day][courseTime].setLayoutParams(RP_MM);
                cell[day][courseTime].setGravity(Gravity.CENTER);
                cell[day][courseTime].setBackground(getResources().getDrawable(R.drawable.linearlayout_border));
                days[day].addView(cell[day][courseTime]);
            }
        //addView(view);
    }

    public void setFreeTime(int[][][] freeTime, int week) {
        for (int day = 0; day < dayNum; day++)
            for (int courseTime = 0; courseTime < courseTimeNum; courseTime++) {
                TextView textView = new TextView(context);
                textView.setText(String.valueOf(freeTime[week][day][courseTime]));
                //textView.setGravity(Gravity.CENTER);
                cell[day][courseTime].removeAllViews();
                cell[day][courseTime].addView(textView);
            }
    }
}
