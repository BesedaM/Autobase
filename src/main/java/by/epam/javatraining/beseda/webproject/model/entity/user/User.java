package by.epam.javatraining.beseda.webproject.model.entity.user;

import by.epam.javatraining.beseda.webproject.model.entity.BaseEntity;

import java.util.Objects;

public class User extends BaseEntity {

    protected String login;
    protected byte[] password;
    protected String role;

    public User() {
        super();
    }

    public User(String login, byte[] password) {
        super();
        this.login = login;
        this.password = password;
    }

    public User(String login, byte[] password, String role) {
        this(login, password);
        this.role = role;
    }

    public User(User other) {
        super(other.id);
        this.login = other.login;
        this.password = other.password;
        this.role = other.role;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public byte[] getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id +
                ", login='" + login + '\'' + '}';
    }
}
