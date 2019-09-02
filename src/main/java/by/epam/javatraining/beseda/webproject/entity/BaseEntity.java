package by.epam.javatraining.beseda.webproject.entity;

import by.epam.javatraining.beseda.webproject.entity.exception.IllegalEntityIdException;

import java.io.Serializable;
import java.util.Objects;

public abstract class BaseEntity implements Serializable, Comparable<BaseEntity> {
    protected int id = 0;

    public BaseEntity() {
    }

    public BaseEntity(int id) {
        this.id = id;
    }

    public void setId(int id) throws IllegalEntityIdException {
        if (id > 0) {
            this.id = id;
        } else {
            throw new IllegalEntityIdException();
        }
    }

    public int getId() {
        return id;
    }

    @Override
    public int compareTo(BaseEntity o) {
        return this.id - o.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity)) return false;
        BaseEntity that = (BaseEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public abstract String toString();
}
