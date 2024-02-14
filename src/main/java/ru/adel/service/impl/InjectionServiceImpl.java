package ru.adel.service.impl;

import ru.adel.annotation.Init;
import ru.adel.annotation.Component;
import ru.adel.service.InjectionService;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InjectionServiceImpl implements InjectionService {
    @Override
    public <T> T injectDependencies(Class<T> type) {
        try {
            T instance = type.getDeclaredConstructor().newInstance();
            initializeAnnotatedFields(instance);
            initializeAnnotatedMethods(instance);
            return instance;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to inject dependencies for class: " + type.getName());
        }
    }

    private <T> void initializeAnnotatedFields(T instance) throws Exception {
        Class<?> type = instance.getClass();

        for (Field field : type.getDeclaredFields()) {
            if (field.isAnnotationPresent(Component.class)) {
                field.setAccessible(true);
                if (field.get(instance) == null) {
                    Object fieldValue = injectDependencies(field.getType());
                    field.set(instance, fieldValue);
                }
            }
        }
    }
    private  <T> void initializeAnnotatedMethods(T instance) {
        Method[] methods = instance.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Init.class)) {
                try {
                    method.setAccessible(true);
                    method.invoke(instance);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                    throw new RuntimeException("Failed to invoke init method: " + method.getName());
                }
            }
        }
    }
}
