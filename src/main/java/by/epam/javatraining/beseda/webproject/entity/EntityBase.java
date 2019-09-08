package by.epam.javatraining.beseda.webproject.entity;

import by.epam.javatraining.beseda.webproject.entity.exception.EntityIdException;

import java.io.Serializable;
import java.util.Objects;

public abstract class EntityBase implements Serializable, Comparable<EntityBase> {
    protected int id = 0;

    public EntityBase() {
    }

    public EntityBase(int id) {
        this.id = id;
    }

    public void setId(int id) throws EntityIdException {
        if (id > 0) {
            this.id = id;
        } else {
            throw new EntityIdException();
        }
    }

    public int getId() {
        return id;
    }

    @Override
    public int compareTo(EntityBase o) {
        return this.id - o.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntityBase)) return false;
        EntityBase that = (EntityBase) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public abstract String toString();
}
