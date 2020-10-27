package ru.sbt.mipt.oop.elements;

public class StringId implements ComponentId {
    private String id;

    public StringId(String id) {
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        return (this.toString().equals(obj.toString()));
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}
