package my.exhibitions.servlet.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncodingUtil {

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
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();//log this
        }
        return stringBuilder.toString();
    }
}
