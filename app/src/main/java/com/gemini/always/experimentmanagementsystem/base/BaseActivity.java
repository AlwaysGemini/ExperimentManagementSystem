package com.gemini.always.experimentmanagementsystem.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity<V, P extends BasePresenter<V>> extends AppCompatActivity {
    private P presenter;
    private V view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (this.presenter == null) {
            this.presenter = createPresenter();
        }
        if (this.view == null) {
            this.view = createView();
        }
        if (this.presenter != null && this.view != null) {
            this.presenter.attachView(this.view);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (this.presenter != null && this.view != null) {
            this.presenter.detachView();
        }
    }

    public P getPresenter() {
        return presenter;
    }

    public abstract P createPresenter();

    public abstract V createView();
}
