package com.nzy.plugin.log;

import com.nzy.plugin.BaseTransform;

import org.gradle.api.Project;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

/**
 * @author niezhiyang
 * since 2020/12/11
 */
public class RemoveLogTransform extends BaseTransform {

    public RemoveLogTransform(Project project) {
        super(project);
    }

    @Override
    protected ClassVisitor initClassVisitor(ClassWriter classWriter) {
        return null;
    }
}
