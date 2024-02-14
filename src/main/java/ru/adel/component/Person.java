package ru.adel.component;

import ru.adel.annotation.Init;
import ru.adel.annotation.Component;
@Component
public class Person {
    private String name ;
    private Shirt shirt;

    public Person(String name, Shirt shirt) {
        this.name = name;
        this.shirt = shirt;
    }
    @Init
    public void init(){
        System.out.println("Метод инициализации, друг");
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Shirt getShirt() {
        return shirt;
    }

    public void setShirt(Shirt shirt) {
        this.shirt = shirt;
    }
    public void sayHello(){
        System.out.println("Привет ,друг!");
    }


    public void sayBye() {
        System.out.println("Пока ,друг");
    }
}
