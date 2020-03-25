package com.gemini.always.experimentmanagementsystem.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gemini.always.experimentmanagementsystem.R;
import com.gemini.always.experimentmanagementsystem.base.BaseFragment;
import com.gemini.always.experimentmanagementsystem.bean.User;
import com.gemini.always.experimentmanagementsystem.presenter.LoginPresenter;
import com.gemini.always.experimentmanagementsystem.ui.activity.MainActivity;
import com.gemini.always.experimentmanagementsystem.util.XToastUtils;
import com.gemini.always.experimentmanagementsystem.view.LoginView;
import com.google.android.material.textfield.TextInputLayout;
import com.orhanobut.logger.Logger;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @version V1.0
 * @Title:
 * @ClassName: com.gemini.always.experimentmanagementsystem.ui.fragment.LoginFragment.java
 * @Description: 登陆模块
 * @author: 周清
 * @date: 2020-02-07 21:48
 */
public class LoginFragment extends BaseFragment<LoginView, LoginPresenter> implements LoginView, View.OnClickListener {


    @BindView(R.id.image_school_badge)
    ImageView imageSchoolBadge;
    @BindView(R.id.edit_account)
    EditText editAccount;
    @BindView(R.id.tl_account)
    TextInputLayout tlAccount;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.tl_password)
    TextInputLayout tlPassword;
    @BindView(R.id.edit_forget_password)
    TextView editForgetPassword;
    @BindView(R.id.button_login)
    RoundButton buttonLogin;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        buttonLogin.setOnClickListener(this);
        editForgetPassword.setOnClickListener(this);
    }

    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    public LoginView createView() {
        return this;
    }

    @Override
    public void onLoginResult(Boolean isSuccess, JSONObject responseJson) {
        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            try {
                XToastUtils.toast(responseJson.getString("msg"));
            } catch (JSONException e) {
                Logger.e(e, "JSONException:");
            }
        });
        if (isSuccess) {
            User.login(getContext(), responseJson);
            MainActivity.startMainActivity(getContext());
            Objects.requireNonNull(getActivity()).finish();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_login:
                if (!editAccount.getText().toString().isEmpty() && !editPassword.toString().isEmpty()) {
                    new Thread() {
                        @Override
                        public void run() {
                            getPresenter().login(editAccount.getText().toString(), editPassword.getText().toString());
                        }
                    }.start();
                } else if (editAccount.getText().toString().isEmpty() || editPassword.toString().isEmpty()) {
                    XToastUtils.toast("账号密码不可为空");
                }
                break;
            case R.id.edit_forget_password:

                break;
        }
    }
}
