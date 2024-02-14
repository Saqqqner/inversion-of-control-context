package ru.adel.service.impl;


import ru.adel.service.ClassSearchService;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
public class ClassSearchServiceImpl implements ClassSearchService {
    @Override
    public Set<Class<?>> findClasses(String packageName) {
        Set<Class<?>> classes = new HashSet<>();
        String path = "target/classes/"  + packageName.replace('.', File.separatorChar);
        File directory = new File(path);
        if (directory.exists()) {
            findClassesInDirectory(directory, packageName, classes);
        }
        return classes;
    }

    private void findClassesInDirectory(File directory, String packageName, Set<Class<?>> classes) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    findClassesInDirectory(file, packageName + "." + file.getName(), classes);
                } else if (file.getName().endsWith(".class")) {
                    String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
                    try {
                        classes.add(Class.forName(className));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

