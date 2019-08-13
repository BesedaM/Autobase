package by.epam.javatraining.beseda.webproject.model.service;

import org.apache.log4j.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

/**
 * Class for passwords hashing
 */
public class PasswordHash {

    private static Logger log = Logger.getLogger(ERROR_LOGGER);

    /**
     * Creates byte array representation of the password
     *
     * @param password password to hash
     * @return byte array
     */
    public static byte[] getHash(String password) {
        byte[] hash = null;
        if (password != null) {
            byte[] salt = new byte[]{8, 45, 95, 4, -5, 88, 4};

            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 512, 128);

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
