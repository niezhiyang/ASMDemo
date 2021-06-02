package com.nzy.plugin;

import com.android.build.gradle.AppExtension;
import com.nzy.plugin.time.TimeTransform;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * @author momo
 */
public class TimePlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        System.out.println("-------NzyPlugin------");
        // 注册 Transform, AppExtension 依赖 gradle，所以该模块需要导入 gradle
        AppExtension appExtension = project.getExtensions().getByType(AppExtension.class);
        // 打印每个方法的时间
        appExtension.registerTransform(new TimeTransform(project));
    }
}
