package de.framedev.javautils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

/**
 * / This Plugin was Created by FrameDev
 * / Package : de.framedev.javautils
 * / ClassName ReflectionUtils
 * / Date: 26.09.21
 * / Project: JavaUtils
 * / Copyrighted by FrameDev
 */

public class ReflectionUtils {

    public ReflectionUtils() {
    }

    public Field getField(Object object, String fieldName) {
        try {
            for (Field method : object.getClass().getDeclaredFields()) {
                if (method.getName() == fieldName) {
                    return method;
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Field getFieldSuperClass(Object object, String fieldName) {
        try {
            for (Field method : object.getClass().getSuperclass().getDeclaredFields()) {
                if (method.getName() == fieldName) {
                    return method;
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean hasFieldAnnotation(Object object, String fieldName, Class<? extends Annotation> class__) {
        try {
            for (Field field : object.getClass().getDeclaredFields()) {
                if (field.getName() == fieldName) {
                    return field.getAnnotation(class__) != null;
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean hasFieldAnnotationSuperClass(Object object, String fieldName, Class<? extends Annotation> class__) {
        try {
            for (Field field : object.getClass().getSuperclass().getDeclaredFields()) {
                if (field.getName() == fieldName) {
                    return field.getAnnotation(class__) != null;
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Annotation[] getFieldAnnotations(Object object, String fieldName) {
        try {
            for (Field field : object.getClass().getDeclaredFields()) {
                if (field.getName() == fieldName) {
                    return field.getAnnotations();
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Annotation[] getFieldAnnotationsSuperClass(Object object, String fieldName) {
        try {
            for (Field field : object.getClass().getSuperclass().getDeclaredFields()) {
                if (field.getName() == fieldName) {
                    return field.getAnnotations();
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Annotation[] getMethodAnnotations(Object object, String methodName) {
        try {
            for (Method method : object.getClass().getDeclaredMethods()) {
                if (method.getName() == methodName) {
                    return method.getAnnotations();
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Annotation[] getMethodAnnotationsSuperClass(Object object, String methodName) {
        try {
            for (Method method : object.getClass().getSuperclass().getDeclaredMethods()) {
                if (method.getName() == methodName) {
                    return method.getAnnotations();
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean hasAnnotation(Object object, String methodName, Class<? extends Annotation> class__) {
        try {
            for (Method method : object.getClass().getDeclaredMethods()) {
                if (method.getName() == methodName) {
                    if (method.getAnnotation(class__) != null) return true;
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean hasAnnotationSuperClass(Object object, String methodName, Class<? extends Annotation> class__) {
        try {
            for (Method method : object.getClass().getSuperclass().getDeclaredMethods()) {
                if (method.getName() == methodName) {
                    if (method.getAnnotation(class__) != null) return true;
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Method getMethod(Object object, String methodName) {
        try {
            for (Method method : object.getClass().getDeclaredMethods()) {
                if (method.getName() == methodName) {
                    return method;
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Method getMethodSuperClass(Object object, String methodName) {
        try {
            for (Method method : object.getClass().getSuperclass().getDeclaredMethods()) {
                if (method.getName() == methodName) {
                    return method;
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Type> getParameterTypes(Object object, String methodName) {
        try {
            for (Method method : object.getClass().getDeclaredMethods()) {
                if (method.getName() == methodName) {
                    return Arrays.asList(method.getParameterTypes());
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Type> getParameterTypesSuperClass(Object object, String methodName) {
        try {
            for (Method method : object.getClass().getSuperclass().getDeclaredMethods()) {
                if (method.getName() == methodName) {
                    return Arrays.asList(method.getParameterTypes());
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object runMethod(Object object, String methodName, Object... args) {
        try {
            for (Method method : object.getClass().getDeclaredMethods()) {
                if (method.getName() == methodName) {
                    return method.invoke(object, args);
                }
            }
        } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object runMethodSupClass(Object object, String methodName, Object... args) {
        try {
            for (Method method : object.getClass().getSuperclass().getDeclaredMethods()) {
                if (method.getName() == methodName) {
                    return method.invoke(object, args);
                }
            }
        } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object runMethod(Object object, String methodName) {
        try {
            for (Method method : object.getClass().getDeclaredMethods()) {
                if (method.getName() == methodName) {
                    return method.invoke(object);
                }
            }
        } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object runMethodSuperClass(Object object, String methodName) {
        try {
            for (Method method : object.getClass().getSuperclass().getDeclaredMethods()) {
                if (method.getName() == methodName) {
                    return method.invoke(object);
                }
            }
        } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isVoidMethod(Object object, String methodName) {
        try {
            for (Method method : object.getClass().getDeclaredMethods()) {
                if (method.getName() == methodName) {
                    return method.invoke(object) == null;
                }
            }
        } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean isVoidMethodSuperClass(Object object, String methodName) {
        try {
            for (Method method : object.getClass().getSuperclass().getDeclaredMethods()) {
                if (method.getName() == methodName) {
                    return method.invoke(object) == null;
                }
            }
        } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean isVoidMethod(Object object, String methodName, Object... args) {
        try {
            for (Method method : object.getClass().getDeclaredMethods()) {
                if (method.getName() == methodName) {
                    return method.invoke(object, args) == null;
                }
            }
        } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean isVoidMethodSuperClass(Object object, String methodName, Object... args) {
        try {
            for (Method method : object.getClass().getSuperclass().getDeclaredMethods()) {
                if (method.getName() == methodName) {
                    return method.invoke(object, args) == null;
                }
            }
        } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean hasMethodArguments(Object object, String methodName) {
        try {
            for (Method method : object.getClass().getDeclaredMethods()) {
                if (method.getName() == methodName) {
                    return method.getParameterCount() > 0;
                }
            }
        } catch (SecurityException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean hasMethodArgumentsSuperClass(Object object, String methodName) {
        try {
            for (Method method : object.getClass().getSuperclass().getDeclaredMethods()) {
                if (method.getName() == methodName) {
                    return method.getParameterCount() > 0;
                }
            }
        } catch (SecurityException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void setField(Object object, String fieldName, Object data) {
        Field field = getField(object, fieldName);
        if (field == null) {
            new Utils().getLogger().log(Level.SEVERE, "No Field Found!");
            return;
        }
        try {
            field.setAccessible(true);
            field.set(object, data);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void setFieldSuperClass(Object object, String fieldName, Object data) {
        Field field = getFieldSuperClass(object, fieldName);
        if (field == null) {
            new Utils().getLogger().log(Level.SEVERE, "No Field Found!");
            return;
        }
        try {
            field.setAccessible(true);
            field.set(object, data);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public Object getFieldValue(Object object, String fieldName) {
        Field field = getField(object, fieldName);
        if (field == null) {
            new Utils().getLogger().log(Level.SEVERE, "No Field Found!");
            return null;
        }
        try {
            field.setAccessible(true);
            return field.get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object getFieldValueSuperClass(Object object, String fieldName) {
        Field field = getFieldSuperClass(object, fieldName);
        if (field == null) {
            new Utils().getLogger().log(Level.SEVERE, "No Field Found!");
            return null;
        }
        try {
            field.setAccessible(true);
            return field.get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Type getFieldType(Object object, String fieldName) {
        Field field = getField(object, fieldName);
        if (field == null) {
            new Utils().getLogger().log(Level.SEVERE, "No Field Found!");
            return null;
        }
        field.setAccessible(true);
        return field.getType();
    }

    public Type getFieldTypeSuperClass(Object object, String fieldName) {
        Field field = getFieldSuperClass(object, fieldName);
        if (field == null) {
            new Utils().getLogger().log(Level.SEVERE, "No Field Found!");
            return null;
        }
        field.setAccessible(true);
        return field.getType();
    }
}
