package com.smilekiller;

import com.google.common.base.Strings;
import com.intellij.codeInsight.CodeInsightActionHandler;
import com.intellij.codeInsight.generation.actions.BaseGenerateAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.PsiJavaFileImpl;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiUtilBase;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wuhaowen on 2017/3/15.
 */
public class MVPKiller extends BaseGenerateAction {
    Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
    Map<String, Object> root = new HashMap<>();

    MvpPersistentStateComponent mvpPersistentStateComponent;
    PersistentConfig persistentConfig;

    public MVPKiller() {
        super(null);
    }

    public MVPKiller(CodeInsightActionHandler handler) {
        super(handler);
    }

    @Override
    public void actionPerformed(AnActionEvent event) {
        mvpPersistentStateComponent = MvpPersistentStateComponent.getInstance();
        persistentConfig = mvpPersistentStateComponent.getPersistentConfig();



        Project project = event.getData(PlatformDataKeys.PROJECT);

        if (persistentConfig == null
                ||Strings.isNullOrEmpty(persistentConfig.appComponent)
                ||Strings.isNullOrEmpty(persistentConfig.basePresenter)
                ||Strings.isNullOrEmpty(persistentConfig.baseView)){

            Messages.showMessageDialog(project, "请先设置基础数据，\"设置\" > \"tools\" > \"MVPKiller\"" ,"警告", Messages.getInformationIcon());

            return;
        }

        Editor editor = event.getData(PlatformDataKeys.EDITOR);
        PsiFile mFile = PsiUtilBase.getPsiFileInEditor(editor, project);
        PsiClass psiClass = getTargetClass(editor, mFile);

        String path = mFile.getVirtualFile().getParent().getPath();
        String packageName = ((PsiJavaFileImpl) mFile).getPackageName();
        String className = psiClass.getName();

        String name = className.replace("Activity", "").replace("Fragment", "");

        String contractName = name + "Contract";
        String presenterName = name + "Presenter";
        String presenterModuleName = presenterName + "Module";
        String componentName = name + "Component";


        MvpBean bean = new MvpBean();
        bean.name = className;
        bean.path = path;
        bean.packagePath = packageName;
        bean.contractName = contractName;
        bean.presenterName = presenterName;
        bean.presenterModuleName = presenterModuleName;
        bean.componentName = componentName;
        bean.appComponent = persistentConfig.appComponent;
        bean.basePresenter = persistentConfig.basePresenter;
        bean.baseView = persistentConfig.baseView;
        bean.appComponentName = bean.appComponent.substring(bean.appComponent.lastIndexOf(".") + 1);
        bean.basePresenterName = bean.basePresenter.substring(bean.basePresenter.lastIndexOf(".") + 1);
        bean.baseViewName = bean.baseView.substring(bean.baseView.lastIndexOf(".") + 1);
        new WriteCommandAction.Simple(psiClass.getProject(), psiClass.getContainingFile()) {
            @Override
            protected void run() throws Throwable {
                gen(bean);
                makeClassImplementView(psiClass,contractName+".View");
                project.getBaseDir().refresh(false,true);

            }
        }.execute();


    }

    private void gen(MvpBean bean) {
        cfg.setClassForTemplateLoading(this.getClass(), "/com/smilekiller/ftl");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        root.put("info", bean);

        gen(bean.path,bean.componentName,"component.ftl");
        gen(bean.path,bean.presenterName,"presenter.ftl");
        gen(bean.path,bean.contractName,"contract.ftl");
        gen(bean.path,bean.presenterModuleName,"presenter_module.ftl");

    }

    private void gen(String path,String name,String ftl) {
        try {
            Template componentTemp = cfg.getTemplate(ftl);
            File dir = new File(path);
            OutputStream fos = new FileOutputStream(new File(dir, name + ".java")); //java文件的生成目录
            Writer out = new OutputStreamWriter(fos);
            componentTemp.process(root, out);
            fos.flush();
            fos.close();
        }catch (Exception e){

        }
    }

//    private PsiClass getPsiClassFromContext(AnActionEvent e) {
//        PsiFile psiFile = e.getData(LangDataKeys.PSI_FILE);
//        Editor editor = e.getData(PlatformDataKeys.EDITOR);
//
//        if (psiFile == null || editor == null) {
//            return null;
//        }
//
//        int offset = editor.getCaretModel().getOffset();
//        PsiElement element = psiFile.findElementAt(offset);
//
//        return PsiTreeUtil.getParentOfType(element, PsiClass.class);
//    }

    private void makeClassImplementView(PsiClass mClass,String interfaceName) {
        if (hasImplementView(mClass,interfaceName)) return;


        final PsiClassType[] implementsListTypes = mClass.getImplementsListTypes();
        final String implementsType = interfaceName;

        for (PsiClassType implementsListType : implementsListTypes) {
            PsiClass resolved = implementsListType.resolve();

            // Already implements Parcelable, no need to add it
            if (resolved != null && implementsType.equals(resolved.getQualifiedName())) {
                return;
            }
        }
        PsiElementFactory elementFactory = JavaPsiFacade.getElementFactory(mClass.getProject());
        PsiJavaCodeReferenceElement implementsReference = elementFactory.createReferenceFromText(implementsType, mClass);
        PsiReferenceList implementsList = mClass.getImplementsList();

        if (implementsList != null) {
            implementsList.add(implementsReference);
        }
    }

    private boolean hasImplementView(PsiClass mClass,String interfaceName) {
        PsiClassType[] superTypes = mClass.getSuperTypes();
        for (PsiClassType superType : superTypes) {
            if (PsiUtils.isOfType(superType, interfaceName)) {
                return true;
            }
        }
        return false;
    }

}
