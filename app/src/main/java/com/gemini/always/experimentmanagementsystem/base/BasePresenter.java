package com.gemini.always.experimentmanagementsystem.base;

public abstract class BasePresenter<V> {
    private V view;

    public V getView() {
        return view;
    }

    public void attachView(V view) {
        this.view = view;
    }

    public void detachView() {
        this.view = null;
    }
}
