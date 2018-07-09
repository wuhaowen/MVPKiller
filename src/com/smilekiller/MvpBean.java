package com.smilekiller;

/**
 * Created by wuhaowen on 2017/3/15.
 */
public class MvpBean {
    public String name;
    public String path;
    public String packagePath;
    public String presenterModuleName;
    public String contractName;
    public String presenterName;
    public String basePresenterName;
    public String baseViewName;
    public String basePresenter;
    public String baseView;
    public String appComponent;
    public String appComponentName;
    public String componentName;

    @Override
    public String toString() {
        return "MvpBean{" +
                "path='" + path + '\'' +
                ", packagePath='" + packagePath + '\'' +
                ", presenterModuleName='" + presenterModuleName + '\'' +
                ", contractName='" + contractName + '\'' +
                ", presenterName='" + presenterName + '\'' +
                ", basePresenterName='" + basePresenterName + '\'' +
                ", baseViewName='" + baseViewName + '\'' +
                ", basePresenter='" + basePresenter + '\'' +
                ", baseView='" + baseView + '\'' +
                ", appComponent='" + appComponent + '\'' +
                ", appComponentName='" + appComponentName + '\'' +
                ", componentName='" + componentName + '\'' +
                '}';
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

    public String getPresenterModuleName() {
        return presenterModuleName;
    }

    public void setPresenterModuleName(String presenterModuleName) {
        this.presenterModuleName = presenterModuleName;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getPresenterName() {
        return presenterName;
    }

    public void setPresenterName(String presenterName) {
        this.presenterName = presenterName;
    }

    public String getBasePresenterName() {
        return basePresenterName;
    }

    public void setBasePresenterName(String basePresenterName) {
        this.basePresenterName = basePresenterName;
    }

    public String getBaseViewName() {
        return baseViewName;
    }

    public void setBaseViewName(String baseViewName) {
        this.baseViewName = baseViewName;
    }

    public String getBasePresenter() {
        return basePresenter;
    }

    public void setBasePresenter(String basePresenter) {
        this.basePresenter = basePresenter;
    }

    public String getBaseView() {
        return baseView;
    }

    public void setBaseView(String baseView) {
        this.baseView = baseView;
    }

    public String getAppComponent() {
        return appComponent;
    }

    public void setAppComponent(String appComponent) {
        this.appComponent = appComponent;
    }

    public String getAppComponentName() {
        return appComponentName;
    }

    public void setAppComponentName(String appComponentName) {
        this.appComponentName = appComponentName;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
