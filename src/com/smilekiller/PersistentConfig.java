package com.smilekiller;

import java.io.Serializable;

/**
 * Created by wuhaowen on 2017/3/15.
 */
public class PersistentConfig implements Serializable{


    public String appComponent;
    public String baseView;
    public String basePresenter;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersistentConfig that = (PersistentConfig) o;

        if (!appComponent.equals(that.appComponent)) return false;
        if (!baseView.equals(that.baseView)) return false;
        return basePresenter.equals(that.basePresenter);
    }

    @Override
    public int hashCode() {
        int result = appComponent.hashCode();
        result = 31 * result + baseView.hashCode();
        result = 31 * result + basePresenter.hashCode();
        return result;
    }

    public String getAppComponent() {
        return appComponent;
    }

    public void setAppComponent(String appComponent) {
        this.appComponent = appComponent;
    }

    public String getBaseView() {
        return baseView;
    }

    public void setBaseView(String baseView) {
        this.baseView = baseView;
    }

    public String getBasePresenter() {
        return basePresenter;
    }

    public void setBasePresenter(String basePresenter) {
        this.basePresenter = basePresenter;
    }
}
