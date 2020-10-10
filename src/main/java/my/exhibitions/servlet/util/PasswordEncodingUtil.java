package my.exhibitions.servlet.util;

import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncodingUtil {

    private static final Logger log = Logger.getLogger(PasswordEncodingUtil.class);

    private static final String ALGORITHM = "MD5";

    public static String encode(String password){
        StringBuilder stringBuilder = new StringBuilder(password);
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
            messageDigest.update(password.getBytes());
            byte[] bytes = messageDigest.digest();
            stringBuilder = new StringBuilder();
            for (byte aByte : bytes) {
                stringBuilder.append(String.format("%02x", aByte));
            }
        } catch (NoSuchAlgorithmException exception) {
            log.error(exception.getMessage(), exception);
        }
        return stringBuilder.toString();
    }
}
