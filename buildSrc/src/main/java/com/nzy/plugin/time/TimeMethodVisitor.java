package com.nzy.plugin.time;


import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

/**
 * @author niezhiyang
 * since 2020/12/14
 */
public class TimeMethodVisitor extends AdviceAdapter {
    public static final String ANNOTATION_METHOD = "Lcom/nzy/asmdemo/DebugLog;";
    boolean needInject = false;
    private MethodVisitor methodVisitor;
    private String name;


    protected TimeMethodVisitor(int api, MethodVisitor methodVisitor, int access, String name, String descriptor) {
        super(api, methodVisitor, access, name, descriptor);
        this.methodVisitor = methodVisitor;
        this.name = name;

    }


    @Override
    protected void onMethodEnter() {
        super.onMethodEnter();

        if (needInject) {
            Label mLabel0 = new Label();
            methodVisitor.visitLabel(mLabel0);
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
            methodVisitor.visitVarInsn(LSTORE, 100000);
        }

    }


    @Override
    protected void onMethodExit(int opcode) {
        super.onMethodExit(opcode);
        if (needInject) {
            // 使用ASM View Plugin查看就能写出来
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
            // 这个300000相当于变量名字，var300000
            // 有一个参数 会占据一个var1，所以避免跟参数重复，所以加一个大值
            methodVisitor.visitVarInsn(LSTORE, 300000);
            Label label2 = new Label();
            methodVisitor.visitLabel(label2);
            methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            methodVisitor.visitVarInsn(LLOAD, 300000);
            methodVisitor.visitVarInsn(LLOAD, 100000);
            methodVisitor.visitInsn(LSUB);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V", false);
            Label label3 = new Label();
            methodVisitor.visitLabel(label3);
            methodVisitor.visitInsn(RETURN);
            Label label4 = new Label();
            methodVisitor.visitLabel(label4);
        }

    }


    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        AnnotationVisitor annotationVisitor = super.visitAnnotation(desc, visible);
        if (desc.equals(ANNOTATION_METHOD)) {
            needInject = true;
        }
        return annotationVisitor;
    }
}
