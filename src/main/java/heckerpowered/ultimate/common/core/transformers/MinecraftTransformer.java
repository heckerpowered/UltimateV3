package heckerpowered.ultimate.common.core.transformers;

import javax.annotation.Nonnull;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import net.minecraft.launchwrapper.IClassTransformer;

/**
 * Minecraft class transformer, change title.
 *
 * @author Heckerpowered
 */
public final class MinecraftTransformer implements IClassTransformer {

    @Override
    public byte[] transform(@Nonnull final String name, @Nonnull final String transformedName,
            @Nonnull final byte[] basicClass) {
        if (name.equals("net.minecraft.client.Minecraft") || name.equals("bao")) {
            final ClassReader classReader = new ClassReader(basicClass);
            final ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_FRAMES);
            final ClassVisitor classVisitor = new ClassVisitor(Opcodes.ASM5, classWriter) {
                @Override
                public MethodVisitor visitMethod(int access, String name, String desc, String signature,
                        String[] exceptions) {
                    if (name.equals("startGame") || name.equals("ag")) {
                        return new MethodVisitor(Opcodes.ASM5,
                                super.visitMethod(access, name, desc, signature, exceptions)) {
                            @Override
                            public void visitLdcInsn(Object cst) {
                                if (cst.equals("Minecraft 1.7.10")) {
                                    super.visitLdcInsn("Minecraft 1.7.10 | Ultimate V3");
                                    return;
                                }

                                super.visitLdcInsn(cst);
                            };
                        };
                    }
                    return super.visitMethod(access, name, desc, signature, exceptions);
                }
            };

            classReader.accept(classVisitor, 0);
            return classWriter.toByteArray();
        }

        return basicClass;
    }

}
