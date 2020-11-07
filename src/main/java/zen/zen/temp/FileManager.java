package zen.zen.temp;

import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Configuration
public class FileManager {

    public static String getHash(String path) throws IOException, NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        FileInputStream fileInputStream = new FileInputStream(path);
        byte[] dataBytes = new byte[1024];
        Integer nRead = 0;
        while((nRead = fileInputStream.read(dataBytes)) != -1) {
            messageDigest.update(dataBytes, 0, nRead);
        }
        byte[] mdBytes = messageDigest.digest();
        StringBuffer stringBuffer = new StringBuffer();
        for(Integer i = 0; i < mdBytes.length; i++) {
            stringBuffer.append(Integer.toString((mdBytes[i] & 0xff) + 0x100, 16)).substring(1);
        }
        return stringBuffer.toString();
    }

}
