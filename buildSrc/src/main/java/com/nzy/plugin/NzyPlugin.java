package com.nzy.plugin;

import com.android.build.gradle.AppExtension;
import com.nzy.plugin.time.TimeTransform;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class NzyPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        System.out.println("-------NzyPlugin------");
        // 注册 Transform, AppExtension 依赖 gradle，所以该模块需要导入 gradle
        AppExtension appExtension = project.getExtensions().getByType(AppExtension.class);
        // 去除 Log
//        appExtension.registerTransform(new RemoveLogTransform(project));
        // 打印每个方法的时间
        appExtension.registerTransform(new TimeTransform(project));
    }
}
