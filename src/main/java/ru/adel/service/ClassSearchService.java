package ru.adel.service;

import java.util.Set;

public interface ClassSearchService {
    Set<Class<?>> findClasses(String packageName);
}