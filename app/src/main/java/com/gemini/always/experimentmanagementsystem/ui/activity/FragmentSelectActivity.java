package com.gemini.always.experimentmanagementsystem.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gemini.always.experimentmanagementsystem.R;
import com.gemini.always.experimentmanagementsystem.ui.fragment.CourseExperimentProjectFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.ExperimentalConsumablesManagementFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.ExperimentalEquipmentFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.ExperimentalOrganizationFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.LaboratoryPersonnelManagementFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.LoginFragment;
import com.gemini.always.experimentmanagementsystem.util.XToastUtils;
import com.githang.statusbar.StatusBarCompat;
import com.orhanobut.logger.Logger;

public class FragmentSelectActivity extends AppCompatActivity {

    //仅仅启动FragmentSelecter
    public static void startFragmentSelecter(Context context, String fragmentName) {
        Intent intent = new Intent(context, FragmentSelectActivity.class);
        intent.putExtra("Fragment", fragmentName);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    //启动FragmentSelecter并附带参数
    public static void startFragmentSelecter(Context context, String fragmentName, String... otherInformations) {
        if (otherInformations.length % 2 != 0) {
            Logger.e("输入参数数量错误");
        }
        Intent intent = new Intent(context, FragmentSelectActivity.class);
        intent.putExtra("Fragment", fragmentName);
        for (int i = 0; i < otherInformations.length; i += 2) {
            intent.putExtra(otherInformations[i], otherInformations[i + 1]);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_select);

        //StatusBarUtils.translucent(this);
        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#FF108EE9"));

        Intent intent = getIntent();
        switch (intent.getStringExtra("Fragment")) {
            case "LoginFragment":
                LoginFragment loginFragment = new LoginFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, loginFragment).commitAllowingStateLoss();
                break;
            case "CourseExperimentProjectFragment":
                CourseExperimentProjectFragment courseExperimentProjectFragment = new CourseExperimentProjectFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, courseExperimentProjectFragment).commitAllowingStateLoss();
                break;
            case "ExperimentalOrganizationFragment":
                ExperimentalOrganizationFragment experimentalOrganizationFragment = new ExperimentalOrganizationFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, experimentalOrganizationFragment).commitAllowingStateLoss();
                break;
            case "LaboratoryPersonnelManagementFragment":
                LaboratoryPersonnelManagementFragment laboratoryPersonnelManagementFragment = new LaboratoryPersonnelManagementFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, laboratoryPersonnelManagementFragment).commitAllowingStateLoss();
                break;
            case "ExperimentalEquipmentFragment":
                ExperimentalEquipmentFragment experimentalEquipmentFragment = new ExperimentalEquipmentFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, experimentalEquipmentFragment).commitAllowingStateLoss();
                break;
            case "ExperimentalConsumablesManagementFragment":
                ExperimentalConsumablesManagementFragment experimentalConsumablesManagementFragment = new ExperimentalConsumablesManagementFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, experimentalConsumablesManagementFragment).commitAllowingStateLoss();
                break;
            default:
                XToastUtils.toast("Fragment加载错误");
                break;
        }
    }
}
