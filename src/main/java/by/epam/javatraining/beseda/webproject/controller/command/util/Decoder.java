package by.epam.javatraining.beseda.webproject.controller.command.util;

import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.util.GeneralProperties;

import java.io.UnsupportedEncodingException;

/**
 * Class used for decoding a string of text, in case of different encodings at
 * client and server side.
 */
public class Decoder {

	/**
	 * Decodes a string of text from SERVER_ENCODING to PROJECT_ENCODING(these
	 * parameters should be set in a property-file).
	 *
	 * @param string Input string to decode
	 * @return string in utf-8 encoding
	 * @throws ServiceLayerException
	 */
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