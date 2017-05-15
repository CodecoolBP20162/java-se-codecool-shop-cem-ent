package com.codecool.shop.model;


import java.lang.reflect.Field;

public class BaseModel {

    protected int id;
    protected String name;
    protected String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(this);
                if (value != null) {
                    sb.append(field.getName() + ":" + value + ",");
                }
            } catch (IllegalAccessException e) {

            }
        }
        return sb.toString();
    }

    BaseModel(String name) {
        this.name = name;
    }

    BaseModel(String name, String description) {
        this.name = name;
        this.description = description;
    }

}
