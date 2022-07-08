package kr.co.clozet.boardpicture.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * packageName    : kr.co.clozet.boardpicture.util
 * fileName       : MD5Generator
 * author         : kimyunseop
 * date           : 2022-07-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-08        kimyunseop       최초 생성
 */
public class MD5Generator {
    private String result;

    public MD5Generator(String input) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest mdMD5 = MessageDigest.getInstance("MD5");
        mdMD5.update(input.getBytes("UTF-8"));
        byte[] md5Hash = mdMD5.digest();
        StringBuilder hexMD5hash = new StringBuilder();
        for(byte b : md5Hash) {
            String hexString = String.format("%02x", b);
            hexMD5hash.append(hexString);
        }
        result = hexMD5hash.toString();
    }

    public String toString() {
        return result;
    }
}