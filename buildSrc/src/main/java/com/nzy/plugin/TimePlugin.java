package com.nzy.plugin;

import com.android.build.gradle.AppExtension;
import com.android.build.gradle.api.ApplicationVariant;
import com.android.build.gradle.tasks.MergeResources;
import com.nzy.plugin.time.LoggerUtil;
import com.nzy.plugin.time.TimeTransform;

import org.antlr.v4.misc.Utils;
import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.execution.TaskExecutionGraph;
import org.gradle.api.logging.LogLevel;
import org.gradle.api.logging.Logger;

/**
 * @author momo
 */
public class TimePlugin implements Plugin<Project> {

    private String TAG = "TimePlugin";
    private Project mProject;
    private Logger mLogger;
    private AppExtension mAppExtension;
    @Override
    public void apply(Project project) {
        System.out.println("-------NzyPlugin------");
        // 注册 Transform, AppExtension 依赖 gradle，所以该模块需要导入 gradle
        mAppExtension = project.getExtensions().getByType(AppExtension.class);
        LoggerUtil.setLog(project.getLogger());
        // 打印每个方法的时间
        mAppExtension.registerTransform(new TimeTransform(project));
        mProject = project;
        printAllTask();
        mLogger = project.getLogger();
    }

    private void printAllTask() {
        mProject.getGradle().getTaskGraph().whenReady(new Action<TaskExecutionGraph>() {
            @Override
            public void execute(TaskExecutionGraph taskGraph) {
//                List<Task> allTasks = taskGraph.getAllTasks();
//                for (int i = 0; i < allTasks.size(); i++) {
//                    Task task = allTasks.get(i);
//                    //打印出所有的task的名字
//                    mLogger.log(LogLevel.ERROR, TAG + "所有的task ：" + i + "-----" + task.getName());
//                }

                mLogger.log(LogLevel.ERROR, TAG + " mProject.whenReady");
            }
        });

        mProject.afterEvaluate(new Action<Project>() {
            @Override
            public void execute(Project project) {
                mLogger.log(LogLevel.ERROR, TAG + " mProject.afterEvaluate");
                initConfig();
            }
        });

    }

    private void initConfig() {
        mAppExtension.getApplicationVariants().all(new Action<ApplicationVariant>() {
            @Override
            public void execute(ApplicationVariant applicationVariant) {
                //当前用户是debug模式，并且没有配置debug运行执行热修复
                if (applicationVariant.getName().contains("debug") ) {
                    //开始压缩等一些列的
                    convert(applicationVariant);
                }

            }
        });

    }
    private void convert(ApplicationVariant variant) {
        //获得: debug/release
        String variantName = variant.getName();
        //首字母大写
        String capitalizeName = Utils.capitalize(variantName);

        // 这是项目的根路径
        String rootPath = mProject.getRootDir().getPath();

        // 这是 cwebp 工具的地址
//        FileUtil.TOOLS_DIRPATH = rootPath + "/mctools/";



        // 第一种拿到资源
        getRes1(capitalizeName);


        // 第二种拿到资源
        //getRes2(variant,  capitalizeName);


        // 第三种拿到资源
        getRes3(variant);

    }
    /**
     * 第一种拿到资源的方法
     *
     * @param capitalizeName
     */
    private void getRes1(String capitalizeName) {
        // 获得android的mergeDebugResources/mergeReleaseResources任务
        final Task mergeResTask =
                mProject.getTasks().findByName("merge" + capitalizeName + "Resources");
        if (mergeResTask != null) {
            mergeResTask.doFirst(new Action<Task>() {
                @Override
                public void execute(Task task) {
                    mLogger.log(LogLevel.ERROR, TAG + "前置 ------mProject.getRes1");
                }
            });
        }
    }
    private void getRes3(ApplicationVariant variant) {
        //获得 mergeResources 的task
        MergeResources mergeResources = variant.getMergeResourcesProvider().get();
        mergeResources.doFirst(new Action<Task>() {
            @Override
            public void execute(Task task) {
                mLogger.log(LogLevel.ERROR, TAG + "前置 ------mProject.getRes3");
            }
        });
    }


}
