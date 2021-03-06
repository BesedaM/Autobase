package by.epam.javatraining.beseda.webproject.dao.util.databaseconstants;

public class DBConstants {

	public static final String OPENING_SQUARE_BRACKET;
	public static final String CLOSING_SQUARE_BRACKET;
	public static final String SPACE;
	public static final String EMPTY_CHARACTER;
	public static final String QUESTION_MARK;
	public static final int MILLISECONDS_IN_SECOND;
	public static final int SECONDS_IN_MINUTE;
	public static final int MINUTES_IN_HOUR;
	public static final int HOURS_OFFSET;
	public static final int TIME_ZONE_OFFSET_IN_MILLISECONDS;

	static {
		OPENING_SQUARE_BRACKET = "[";
		CLOSING_SQUARE_BRACKET = "]";
		SPACE = " ";
		EMPTY_CHARACTER = "";
		QUESTION_MARK = "?";

		MILLISECONDS_IN_SECOND = 1000;
		SECONDS_IN_MINUTE = 60;
		MINUTES_IN_HOUR = 60;
		HOURS_OFFSET = 3;
		TIME_ZONE_OFFSET_IN_MILLISECONDS = HOURS_OFFSET * MINUTES_IN_HOUR 
				* SECONDS_IN_MINUTE * MILLISECONDS_IN_SECOND;
	}
}