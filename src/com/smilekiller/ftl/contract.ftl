package ${info.packagePath};

import ${info.basePresenter};
import ${info.baseView};

public interface ${info.contractName} {

    interface Presenter extends ${info.basePresenterName} {

    }

    interface View extends ${info.baseViewName}<Presenter> {

    }
}