package by.epam.javatraining.beseda.webproject.model.entity;

public class DefaultValue {

    public static final String COUNTRY;
    public static final String DISTRICT;
    public static final String EMPTY_STRING;

    public static final String ALPHA_NUMERIC_VALUE;

    public static final String SLASH;
    public static final int ZERO;

    static {
        COUNTRY = "Беларусь";
        DISTRICT = "Минский";
        EMPTY_STRING = "";

        ALPHA_NUMERIC_VALUE="^\\p{Alnum}";
        SLASH="/";

        ZERO = 0;

    }
}
