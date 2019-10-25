package by.epam.javatraining.beseda.webproject.aaaaaaaaaaaaa;

import by.epam.javatraining.beseda.webproject.service.PasswordHash;

public class Test {

    static PasswordHash encoder = new PasswordHash();
    static String password = "admin";

    public static void main(String[] args) {
        System.out.println(password);

        String encodedPassword = encoder.encode(password);
        System.out.println(encodedPassword);
        System.out.println(encoder.matches(password, encodedPassword));

    }
}
