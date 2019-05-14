package by.epam.javatraining.beseda.webproject.util;

import org.apache.log4j.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class PasswordHash {

    static Logger log = Logger.getLogger(PasswordHash.class.getSimpleName());

    public static byte[] getHash(String password) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        byte[] hash=null;
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 512, 128);        //65536, 128

        SecretKeyFactory factory = null;
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            hash = factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            log.error("Error encrypting a password: " + e);
        }
        return hash;
    }
}
