package ru.adel.service;

public interface InjectionService {
    <T> T injectDependencies(Class<T> type);
}
