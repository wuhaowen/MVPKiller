package ${info.packagePath};

import dagger.Module;
import dagger.Provides;

@Module
public class ${info.presenterModuleName} {
    ${info.contractName}.View mView;
    public ${info.presenterModuleName}(${info.contractName}.View view) {
        this.mView = view;
    }


    @Provides
    ${info.contractName}.View provide${info.contractName}View(){
        return mView;
    }
}
