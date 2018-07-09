package ${info.packagePath};

import javax.inject.Inject;

public class ${info.presenterName} implements ${info.contractName}.Presenter {
    ${info.contractName}.View mView;

    @Inject
    public ${info.presenterName}(${info.contractName}.View view) {
        this.mView = view;
    }

}