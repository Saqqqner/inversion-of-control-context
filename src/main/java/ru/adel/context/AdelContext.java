package ru.adel.context;

import ru.adel.annotation.Component;
import ru.adel.component.Person;
import ru.adel.service.ClassSearchService;
import ru.adel.service.InjectionService;
import ru.adel.service.impl.ClassSearchServiceImpl;
import ru.adel.service.impl.InjectionServiceImpl;

import java.util.Set;

public class AdelContext {

    private final String packageName;
    private final ClassSearchService classSearchService;
    private final InjectionService injectionService;

    public AdelContext(String packageName) {
        this.packageName = packageName;
        this.classSearchService = new ClassSearchServiceImpl();
        this.injectionService = new InjectionServiceImpl();
    }

    public static void main(String[] args) {
        AdelContext context = new AdelContext("ru.adel");
        Person adelComponent = context.getObject(Person.class);
        adelComponent.sayBye();
    }

    public <T> T getObject(Class<T> type) {
        Set<Class<?>> classes = classSearchService.findClasses(packageName);
        for (Class<?> clazz : classes) {
            System.out.println("Found class: " + clazz.getName());
            if (clazz.isAnnotationPresent(Component.class) && type.isAssignableFrom(clazz)) {
                return injectionService.injectDependencies(type);
            }
        }
        System.out.println("No suitable class found for type: " + type.getName());
        throw new RuntimeException("No suitable class found for type: " + type.getName());
    }


}
