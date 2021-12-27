package com.nzy.plugin.thread;

import com.nzy.plugin.time.LoggerUtil;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;


/**
 * @author niezhiyang
 * since 2020/12/14
 */
public class ThreadClassVisitor extends ClassVisitor {
    private String mClassName;

    public ThreadClassVisitor(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
    }


    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);
//        LoggerUtil.e(name +" --- "+ descriptor+ "---"+signature);

//        if (mClassName.contains("com/nzy/asmdemo/ThreadDemo") && name.equals("<init>")) {
//            LoggerUtil.e(name + " --- " + descriptor + "---" + signature+"---");
//           return new ThreadAdapterVisitor(api, methodVisitor, access, name, descriptor);
//        }
        return methodVisitor;
    }

    /**
     * 该方法是当扫描类时第一个调用的方法，主要用于类声明使用。下面是对方法中各个参数的示意：
     * visit( 类版本 , 修饰符 , 类名 , 泛型信息 , 继承的父类 , 实现的接口)
     *
     * @param version
     * @param access
     * @param name
     * @param signature
     * @param superName
     * @param interfaces
     */
    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        mClassName = name;
//        if (mClassName.contains("java/lang/Thread") || mClassName.contains("okhttp3/OkHttpClient$Builder")) {
           LoggerUtil.e(name + " --- ");
//        }

        super.visit(version, access, name, signature, superName, interfaces);
    }

    /**
     * 该方法是当扫描器扫描到类注解声明时进行调用。下面是对方法中各个参数的示意：
     * visitAnnotation(注解类型 , 注解是否能够在 JVM 中可见)
     *
     * @param descriptor
     * @param visible
     * @return
     */
    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        return super.visitAnnotation(descriptor, visible);
    }

    /**
     * 该方法是当扫描器扫描到类中字段时进行调用。下面是对方法中各个参数的示意：
     * visitField(修饰符 , 字段名 , 字段类型 , 泛型描述 , 默认值)
     *
     * @param access
     * @param name
     * @param descriptor
     * @param signature
     * @param value
     * @return
     */
    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        return super.visitField(access, name, descriptor, signature, value);
    }

    /**
     * 该方法是当扫描器完成类扫描时才会调用，若是想在类中追加某些方法
     */
    @Override
    public void visitEnd() {
        super.visitEnd();
    }
}
