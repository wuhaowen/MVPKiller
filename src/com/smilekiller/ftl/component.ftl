package ${info.packagePath};

import ${info.appComponent};

import dagger.Component;


@Component(dependencies = ${info.appComponentName}.class , modules = ${info.presenterModuleName}.class)
public interface ${info.componentName} {
    void inject(${info.name} ${info.name?uncap_first});
}
