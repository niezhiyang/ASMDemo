package com.nzy.plugin.time;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;


/**
 * @author niezhiyang
 * since 2020/12/14
 */
public class AutoClassVisitor extends ClassVisitor {



    public AutoClassVisitor(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
    }


    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);
        return new TimeMethodVisitor(api, methodVisitor, access, name, descriptor);
    }

}
