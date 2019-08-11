package by.epam.javatraining.beseda.webproject.model.command.util;

import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.util.resourceloader.GeneralProperties;

import java.io.UnsupportedEncodingException;

public class Decoder {

    public static String decode(String string) throws ServiceLayerException {
        String newStr = string;
        if (!GeneralProperties.PROJECT_ENCODING.equals(GeneralProperties.SERVER_ENCODING)) {
            byte[] bytes;
            try {
                bytes = string.getBytes(GeneralProperties.SERVER_ENCODING);
                newStr = new String(bytes, GeneralProperties.PROJECT_ENCODING);
            } catch (UnsupportedEncodingException e) {
                throw new ServiceLayerException(e);
            }
        }
        return newStr;
    }

}
