package com.nzy.plugin.time;


import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

import static com.nzy.plugin.time.AutoClassVisitor.ANNOTATION_METHOD;

/**
 * @author niezhiyang
 * since 2020/12/14
 */
public class TimeMethodVisitor extends AdviceAdapter {

    boolean needInject = false;
    private MethodVisitor methodVisitor;
    private String name;


    protected TimeMethodVisitor(int api, MethodVisitor methodVisitor, int access, String name, String descriptor) {
        super(api, methodVisitor, access, name, descriptor);
        this.methodVisitor = methodVisitor;
        this.name = name;
    }

    @Override
    public void visitInsn(int opcode) {
        super.visitInsn(opcode);
    }

    @Override
    protected void onMethodEnter() {
        super.onMethodEnter();

        if (needInject) {
            Label mLabel0 = new Label();
            methodVisitor.visitLabel(mLabel0);
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
            methodVisitor.visitVarInsn(LSTORE, 100);
        }

    }


    @Override
    protected void onMethodExit(int opcode) {
        super.onMethodExit(opcode);
        if (needInject) {
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
//        methodVisitor.visitLineNumber(12, label1);
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
            methodVisitor.visitVarInsn(LSTORE, 300);
            Label label2 = new Label();
            methodVisitor.visitLabel(label2);
//        methodVisitor.visitLineNumber(14, label2);
            methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            methodVisitor.visitVarInsn(LLOAD, 300);
            methodVisitor.visitVarInsn(LLOAD, 100);
            methodVisitor.visitInsn(LSUB);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V", false);
            Label label3 = new Label();
            methodVisitor.visitLabel(label3);
//        methodVisitor.visitLineNumber(17, label3);
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
//                            return new AnnotationVisitor(Opcodes.ASM6, annotationVisitor) {
//                                @Override
//                                public void visit(String name, Object value) {
//                                    super.visit(name, value);
//                                    if (name.equals("tag") && value instanceof String) {
//                                        tag = (String) value;
//                                    }
//                                }
//                            };
        }
        return annotationVisitor;
    }
}
