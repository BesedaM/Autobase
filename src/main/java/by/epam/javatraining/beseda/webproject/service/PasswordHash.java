package by.epam.javatraining.beseda.webproject.service;

import org.apache.log4j.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import static by.epam.javatraining.beseda.webproject.service.ServiceConstants.SALT_HASHING;
import static by.epam.javatraining.beseda.webproject.service.ServiceConstants.ITERATION_COUNT;
import static by.epam.javatraining.beseda.webproject.service.ServiceConstants.KEY_LENGTH;

import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

/**
 * Class for passwords hashing.
 */
public class PasswordHash {

	private static Logger log = Logger.getLogger(ERROR_LOGGER);

	private PasswordHash() {
	}

	/**
	 * Creates byte array representation of the password.
	 *
	 * @param password password to hash
	 * @return byte array
	 */
	public static final byte[] getHash(String password) {
		byte[] hash = null;
		if (password != null) {
			byte[] salt = SALT_HASHING;

			KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATION_COUNT, KEY_LENGTH);

			try {
				SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
				hash = factory.generateSecret(spec).getEncoded();
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
				log.error("Error encrypting a password: " + e);
			}
		}
		return hash;
	}
}