package by.epam.javatraining.beseda.webproject.controller.util;

import org.apache.log4j.Logger;

import static by.epam.javatraining.beseda.webproject.controller.util.constant.CommandConstant.*;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

public class CurrentPageProcessor {

    private static Logger log=Logger.getLogger(ERROR_LOGGER);

    public static String processPage(String wholeName) {
        String address = wholeName
                .replace(FIRST_PAGE_DELETE_FROM_START, "")
                .replace(FIRST_PAGE_DELETE_FROM_END, "");
        return address;
    }
}
