package ru.everlast1ngw1nter.NauJava.models;

public class Product {
    private long id;

    private String name;

    private int calories;

    public Product(long id, String name, int calories) {
        this.id = id;
        this.name = name;
        this.calories = calories;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
