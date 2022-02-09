package ru.job4j.accident.model;

import java.util.Objects;

public class Inspector {
    private int id;
    private String name;

    public Inspector() {
    }

    public Inspector(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Inspector inspector = (Inspector) o;
        return id == inspector.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
