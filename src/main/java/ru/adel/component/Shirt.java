package ru.adel.component;


import ru.adel.annotation.Component;

@Component
public class Shirt {
    Integer size = 0;

    public Shirt() {
    }

    public Shirt(Integer size) {
        this.size = size;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
