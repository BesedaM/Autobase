package by.epam.javatraining.beseda.webproject.entity.user;

import by.epam.javatraining.beseda.webproject.entity.exception.user.IllegalUserNameException;
import by.epam.javatraining.beseda.webproject.entity.exception.user.IllegalUserPhoneNumberException;
import by.epam.javatraining.beseda.webproject.entity.exception.user.IllegalUserSurnameException;

import java.util.Objects;

public class Person extends User {
    protected String name;
    protected String surname;
    protected String phone;

    public Person() {
        super();
    }

    public Person(String login, byte[] password) {
        super(login, password);
    }

    public Person(User userData, String name, String surname, String phone) {
        super(userData);
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    public Person(String login, byte[] password, String name, String surname, String phone) {
        super(login, password);
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    public void setName(String name) throws IllegalUserNameException {
        if(name!=null) {
            this.name = name;
        }else{
            throw new IllegalUserNameException();
        }
    }

    public void setSurname(String surname) throws IllegalUserSurnameException {
        if(surname!=null) {
            this.surname = surname;
        }else{
            throw new IllegalUserSurnameException();
        }
    }

    public void setPhone(String phone) throws IllegalUserPhoneNumberException {
        if(phone!=null) {
            this.phone = phone;
        }else{
            throw new IllegalUserPhoneNumberException();
        }
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        if (!super.equals(o)) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(surname, person.surname) &&
                Objects.equals(phone, person.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, surname, phone);
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '}';
    }
}
