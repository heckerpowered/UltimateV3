package heckerpowered.ultimate.common.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class StackWalker {
    private static Class<?> reflection;
    private static Method getCallerClass;

    private StackWalker() {
    }

    public static final Class<?> getCallerClass(int depth) {
        if (reflection == null) {
            try {
                reflection = Class.forName("sun.reflect.Reflection");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        if (getCallerClass == null) {
            try {
                getCallerClass = reflection.getDeclaredMethod("getCallerClass", Integer.TYPE);
            } catch (NoSuchMethodException | SecurityException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            return (Class<?>) getCallerClass.invoke(reflection, depth);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
