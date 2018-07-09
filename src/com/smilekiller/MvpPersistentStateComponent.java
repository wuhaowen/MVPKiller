package com.smilekiller;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by wuhaowen on 2017/3/15.
 */
@State(
        name = "MvpPersistentStateComponent",
        storages = {
                @Storage("mvpkiller.xml")}
)
public class MvpPersistentStateComponent implements ApplicationComponent ,  PersistentStateComponent<PersistentConfig>{

    private PersistentConfig persistentConfig = new PersistentConfig();

    public static MvpPersistentStateComponent getInstance(){
        return ApplicationManager.getApplication().getComponent(MvpPersistentStateComponent.class);
    }

    public MvpPersistentStateComponent() {
    }

    @Nullable
    @Override
    public PersistentConfig getState() {
        return persistentConfig;
    }

    @Override
    public void loadState(PersistentConfig persistentConfig) {
        this.persistentConfig = persistentConfig;
    }

    public PersistentConfig getPersistentConfig() {
        return persistentConfig;
    }

    public void setPersistentConfig(PersistentConfig persistentConfig) {
        this.persistentConfig = persistentConfig;
    }

    @Override
    public void initComponent() {

    }

    @Override
    public void disposeComponent() {

    }

    @NotNull
    @Override
    public String getComponentName() {
        return this.getClass().getName();
    }
}
