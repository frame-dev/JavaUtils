package de.framedev.javautils;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
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

@SuppressWarnings({"SpellCheckingInspection","unused"})
public class ReflectionUtils {

    public ReflectionUtils() {
    }

    /**
     * Return the ClassName
     * @param class_ the Class
     * @return return the ClassName (Package and Name as example [de.framedev.javaproject.main.Main])
     */
    public String getClassName(Class<?> class_) {
        return class_.getPackage().getName() + "." + class_.getSimpleName();
    }

    public Object getEnumValue(String className, String of) {
        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        assert cls != null;
        for (Object obj : cls.getEnumConstants()) {
            try {
                Method m = cls.getMethod("valueOf", String.class);
                return m.invoke(obj, of);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public Object[] getEnumValues(String className) {
        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        assert cls != null;
        if (cls.isEnum()) {
            return cls.getEnumConstants();
        }
        return null;
    }

    public Field getField(String className, String fieldName) {
        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            assert cls != null;
            for (Field method : cls.getDeclaredFields()) {
                if (method.getName().equalsIgnoreCase(fieldName)) {
                    return method;
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Field getFieldSuperClass(String className, String fieldName) {
        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            assert cls != null;
            for (Field method : cls.getSuperclass().getDeclaredFields()) {
                if (method.getName().equals(fieldName)) {
                    return method;
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean hasConstructorAnnotation(Constructor<?> constructor, Class<? extends Annotation> class__) {
        return constructor.getAnnotation(class__) != null;
    }

    public boolean hasClassAnnotation(String className, Class<? extends Annotation> class__) {
        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            assert cls != null;
            return cls.getDeclaredAnnotation(class__) != null;
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean hasClassAnnotationSuperClass(String className, Class<? extends Annotation> class__) {
        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            assert cls != null;
            return cls.getSuperclass().getDeclaredAnnotation(class__) != null;
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean hasFieldAnnotation(String className, String fieldName, Class<? extends Annotation> class__) {
        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            assert cls != null;
            for (Field field : cls.getDeclaredFields()) {
                if (field.getName().equals(fieldName)) {
                    return field.getAnnotation(class__) != null;
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean hasFieldAnnotationSuperClass(String className, String fieldName, Class<? extends Annotation> class__) {
        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            assert cls != null;
            for (Field field : cls.getSuperclass().getDeclaredFields()) {
                if (field.getName().equals(fieldName)) {
                    return field.getAnnotation(class__) != null;
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Annotation[] getFieldAnnotations(String className, String fieldName) {
        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            assert cls != null;
            for (Field field : cls.getDeclaredFields()) {
                if (field.getName().equalsIgnoreCase(fieldName)) {
                    return field.getAnnotations();
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Annotation[] getFieldAnnotationsSuperClass(String className, String fieldName) {
        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            assert cls != null;
            for (Field field : cls.getSuperclass().getDeclaredFields()) {
                if (field.getName().equalsIgnoreCase(fieldName)) {
                    return field.getAnnotations();
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Annotation[] getMethodAnnotations(String className, String methodName) {
        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            assert cls != null;
            for (Method method : cls.getDeclaredMethods()) {
                if (method.getName().equalsIgnoreCase(methodName)) {
                    return method.getAnnotations();
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Annotation[] getMethodAnnotationsSuperClass(String className, String methodName) {
        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            assert cls != null;
            for (Method method : cls.getSuperclass().getDeclaredMethods()) {
                if (method.getName().equals(methodName)) {
                    return method.getAnnotations();
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean hasAnnotation(String className, String methodName, Class<? extends Annotation> class__) {
        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            assert cls != null;
            for (Method method : cls.getDeclaredMethods()) {
                if (method.getName().equalsIgnoreCase(methodName)) {
                    if (method.getAnnotation(class__) != null) return true;
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean hasAnnotationSuperClass(String className, String methodName, Class<? extends Annotation> class__) {
        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            assert cls != null;
            for (Method method : cls.getSuperclass().getDeclaredMethods()) {
                if (method.getName().equalsIgnoreCase(methodName)) {
                    if (method.getAnnotation(class__) != null) return true;
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Method getMethod(String className, String methodName) {
        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            assert cls != null;
            for (Method method : cls.getDeclaredMethods()) {
                if (method.getName().equalsIgnoreCase(methodName)) {
                    return method;
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Method getMethodSuperClass(String className, String methodName) {
        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            assert cls != null;
            for (Method method : cls.getSuperclass().getDeclaredMethods()) {
                if (method.getName().equalsIgnoreCase(methodName)) {
                    return method;
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Type> getParameterTypes(String className, String methodName) {
        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            assert cls != null;
            for (Method method : cls.getDeclaredMethods()) {
                if (method.getName().equalsIgnoreCase(methodName)) {
                    return Arrays.asList(method.getParameterTypes());
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Type> getParameterTypesSuperClass(String className, String methodName) {
        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            assert cls != null;
            for (Method method : cls.getSuperclass().getDeclaredMethods()) {
                if (method.getName().equalsIgnoreCase(methodName)) {
                    return Arrays.asList(method.getParameterTypes());
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object runMethod(String className, Object object, String methodName, Object... args) {
        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            assert cls != null;
            for (Method method : cls.getDeclaredMethods()) {
                if (method.getName().equalsIgnoreCase(methodName)) {
                    return method.invoke(object, args);
                }
            }
        } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    //
    public Object runMethodSupClass(String className, Object object, String methodName, Object... args) {
        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            assert cls != null;
            for (Method method : cls.getSuperclass().getDeclaredMethods()) {
                if (method.getName().equalsIgnoreCase(methodName)) {
                    return method.invoke(object, args);
                }
            }
        } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object runMethod(String className, Object object, String methodName) {
        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            assert cls != null;
            for (Method method : cls.getDeclaredMethods()) {
                if (method.getName().equalsIgnoreCase(methodName)) {
                    return method.invoke(object);
                }
            }
        } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object runMethodSuperClass(String className, Object object, String methodName) {
        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            assert cls != null;
            for (Method method : cls.getSuperclass().getDeclaredMethods()) {
                if (method.getName().equalsIgnoreCase(methodName)) {
                    return method.invoke(object);
                }
            }
        } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isVoidMethod(String className, Object object, String methodName) {
        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            assert cls != null;
            for (Method method : cls.getDeclaredMethods()) {
                if (method.getName().equalsIgnoreCase(methodName)) {
                    return method.invoke(object) == null;
                }
            }
        } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean isVoidMethodSuperClass(String className, Object object, String methodName) {
        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            assert cls != null;
            for (Method method : cls.getSuperclass().getDeclaredMethods()) {
                if (method.getName().equalsIgnoreCase(methodName)) {
                    return method.invoke(object) == null;
                }
            }
        } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean isVoidMethod(String className, Object object, String methodName, Object... args) {
        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            assert cls != null;
            for (Method method : cls.getDeclaredMethods()) {
                if (method.getName().equalsIgnoreCase(methodName)) {
                    return method.invoke(object, args) == null;
                }
            }
        } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean isVoidMethodSuperClass(String className, Object object, String methodName, Object... args) {
        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            assert cls != null;
            for (Method method : cls.getSuperclass().getDeclaredMethods()) {
                if (method.getName().equalsIgnoreCase(methodName)) {
                    return method.invoke(object, args) == null;
                }
            }
        } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean hasMethodArguments(String className, String methodName) {
        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            assert cls != null;
            for (Method method : cls.getDeclaredMethods()) {
                if (method.getName().equalsIgnoreCase(methodName)) {
                    return method.getParameterCount() > 0;
                }
            }
        } catch (SecurityException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean hasMethodArgumentsSuperClass(String className, String methodName) {
        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            assert cls != null;
            for (Method method : cls.getSuperclass().getDeclaredMethods()) {
                if (method.getName().equalsIgnoreCase(methodName)) {
                    return method.getParameterCount() > 0;
                }
            }
        } catch (SecurityException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void setField(String className, Object object, String fieldName, Object data) {
        Field field = getField(className, fieldName);
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

    public void setFieldSuperClass(String className, Object object, String fieldName, Object data) {
        Field field = getFieldSuperClass(className, fieldName);
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

    public Object getFieldValue(String className, Object object, String fieldName) {
        Field field = getField(className, fieldName);
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

    public Object getFieldValueSuperClass(String className, Object object, String fieldName) {
        Field field = getFieldSuperClass(className, fieldName);
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

    public Type getFieldType(String className, String fieldName) {
        Field field = getField(className, fieldName);
        if (field == null) {
            new Utils().getLogger().log(Level.SEVERE, "No Field Found!");
            return null;
        }
        field.setAccessible(true);
        return field.getType();
    }

    public Type getFieldTypeSuperClass(String className, String fieldName) {
        Field field = getFieldSuperClass(className, fieldName);
        if (field == null) {
            new Utils().getLogger().log(Level.SEVERE, "No Field Found!");
            return null;
        }
        field.setAccessible(true);
        return field.getType();
    }

    public Object newInstance(String className, List<Object> paramsObjects, boolean accessible, Class<?>... params) {
        Object object = null;
        try {
            Object[] objs = paramsObjects.toArray();
            Class<?> cls = Class.forName(className);
            Constructor<?> constructor = cls.getDeclaredConstructor(params);
            constructor.setAccessible(accessible);
            object = constructor.newInstance(objs);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return object;
    }

    public Object newInstance(String className, List<Object> paramsObjects, Class<?>... params) {
        Object object = null;
        try {
            Object[] objs = paramsObjects.toArray();
            Class<?> cls = Class.forName(className);
            Constructor<?> constructor = cls.getDeclaredConstructor(params);
            object = constructor.newInstance(objs);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return object;
    }

    public Object newInstance(String className) {
        Object object = null;
        try {
            Class<?> cls = Class.forName(className);
            Constructor<?> constructor = cls.getDeclaredConstructor();
            object = constructor.newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return object;
    }

    public Constructor<?>[] getConstructors(String className) {
        Constructor<?>[] object = null;
        try {
            Class<?> cls = Class.forName(className);
            object = cls.getDeclaredConstructors();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }

    public Constructor<?> getConstructor(String className, Class<?>... params) {
        Constructor<?> object = null;
        try {
            Class<?> cls = Class.forName(className);
            object = cls.getDeclaredConstructor(params);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return object;
    }

    public Constructor<?> getConstructor(String className) {
        Constructor<?> object = null;
        try {
            Class<?> cls = Class.forName(className);
            object = cls.getDeclaredConstructor();
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return object;
    }

    public Field[] getFields(String className) {
        try {
            Class<?> cls = Class.forName(className);
            return cls.getFields();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Field[] getFieldsSuperClass(String className) {
        try {
            Class<?> cls = Class.forName(className);
            return cls.getSuperclass().getFields();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
