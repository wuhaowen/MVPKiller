package com.smilekiller;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created by wuhaowen on 2017/3/15.
 */
public class Configurable implements SearchableConfigurable {

    private Setting setting;

    @NotNull
    @Override
    public String getId() {
        return "config.mvp_settings";
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "MvpKiller";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return "MvpKiller";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        setting = new Setting();
        return setting.panel;
    }

    @Override
    public boolean isModified() {


        return true;
    }

    @Override
    public void apply() throws ConfigurationException {
        MvpPersistentStateComponent mvpPersistentStateComponent = MvpPersistentStateComponent.getInstance();

        PersistentConfig o = new PersistentConfig();
        o.appComponent = this.setting.txtAppComponent.getText();
        o.baseView = this.setting.txtView.getText();
        o.basePresenter = this.setting.txtPresenter.getText();
        mvpPersistentStateComponent.setPersistentConfig(o);
    }

    @Override
    public void reset() {
        MvpPersistentStateComponent mvpPersistentStateComponent = MvpPersistentStateComponent.getInstance();

        PersistentConfig o = mvpPersistentStateComponent.getPersistentConfig();

        this.setting.txtAppComponent.setText(o.appComponent);
        this.setting.txtPresenter.setText(o.basePresenter);
        this.setting.txtView.setText(o.baseView);

    }
}
