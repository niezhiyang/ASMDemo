package com.nzy.plugin.thread;


import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

/**
 * @author niezhiyang
 * since 2020/12/14
 */
public class ThreadAdapterVisitor extends AdviceAdapter {
    public static final String ANNOTATION_METHOD = "Lcom/nzy/asmdemo/DebugLog;";
    boolean needInject = false;
    private MethodVisitor methodVisitor;
    private String name;


    protected ThreadAdapterVisitor(int api, MethodVisitor methodVisitor, int access, String name, String descriptor) {
        super(api, methodVisitor, access, name, descriptor);
        this.methodVisitor = methodVisitor;
        this.name = name;



    }



    @Override
    protected void onMethodEnter() {
        super.onMethodEnter();


    }


    @Override
    protected void onMethodExit(int opcode) {
        super.onMethodExit(opcode);


    }


}
