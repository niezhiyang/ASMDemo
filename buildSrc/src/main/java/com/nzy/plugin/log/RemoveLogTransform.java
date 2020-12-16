package com.nzy.plugin.log;

import com.android.build.api.transform.DirectoryInput;
import com.android.build.api.transform.JarInput;
import com.android.build.api.transform.QualifiedContent;
import com.android.build.api.transform.Transform;
import com.android.build.api.transform.TransformException;
import com.android.build.api.transform.TransformInput;
import com.android.build.api.transform.TransformInvocation;
import com.android.build.api.transform.TransformOutputProvider;
import com.android.build.gradle.internal.pipeline.TransformManager;

import org.gradle.api.Project;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;

/**
 * @author niezhiyang
 * since 2020/12/11
 */
public class RemoveLogTransform extends Transform {
    private static final String NAME = "RemoveLogTransform";

    public Project mProject;

    public RemoveLogTransform(Project project) {
        mProject = project;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS;
    }

    /**
     * 指Transform要操作内容的范围，官方文档Scope有7种类型：
     * <p>
     * EXTERNAL_LIBRARIES        只有外部库
     * PROJECT                   只有项目内容
     * PROJECT_LOCAL_DEPS        只有项目的本地依赖(本地jar)
     * PROVIDED_ONLY             只提供本地或远程依赖项
     * SUB_PROJECTS              只有子项目。
     * SUB_PROJECTS_LOCAL_DEPS   只有子项目的本地依赖项(本地jar)。
     * TESTED_CODE               由当前变量(包括依赖项)测试的代码
     * SCOPE_FULL_PROJECT        整个项目
     */
    @Override
    public Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT;
    }

    @Override
    public boolean isIncremental() {
        return false;
    }

    @Override
    public void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation);

        // inputs中是传过来的输入流，其中有两种格式，一种是jar包格式一种是目录格式。
        Collection<TransformInput> inputs = transformInvocation.getInputs();

        // 获取到输出目录，最后将修改的文件复制到输出目录，这一步必须做不然编译会报错
        TransformOutputProvider outputProvider = transformInvocation.getOutputProvider();


        // 遍历
        for (TransformInput input : inputs) {
            // 处理jar
            for (JarInput jarInput : input.getJarInputs()) {

            }

            // 处理jar
            for (DirectoryInput directoryInput : input.getDirectoryInputs()) {

            }
        }


    }
}
