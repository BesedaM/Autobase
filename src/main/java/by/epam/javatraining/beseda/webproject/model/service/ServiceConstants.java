package by.epam.javatraining.beseda.webproject.model.service;

public class ServiceConstants {

    //password check
    public static int PASSWORD_LENGTH;
    public static String IS_WHITESPACE_CHARACTER;
    public static String IS_DIGIT;
    public static String IS_ALPHABETIC_CHARACTER;

    static {
        PASSWORD_LENGTH = 8;
        IS_WHITESPACE_CHARACTER = "\\s";
        IS_DIGIT = "\\d";
        IS_ALPHABETIC_CHARACTER = "\\p{Alpha}";
    }
}
