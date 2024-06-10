package com.demo.springboot3demo.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Objects;

/**
 * @author BHG
 */
public class AES {

    private static final Logger logger = LoggerFactory.getLogger(AES.class);
    private static final String ENCODING = "UTF-8";
    private static final String PASSWORD = "1234567890jfdkjsf";

    /**
     * AES加密
     *
     * @param content 明文
     * @return 密文
     */
    public static String encryptAES(String content) {
        if (!StringUtils.hasLength(content)) {
            throw new IllegalArgumentException("明文不能为空！");
        }
        byte[] encryptResult = encrypt(content, PASSWORD);
        String encryptResultStr = parseByte2HexStr(encryptResult);
        // BASE64位加密
        encryptResultStr = bowingEncrypt(encryptResultStr);
        return encryptResultStr;
    }

    /**
     * AES解密
     *
     * @param encryptResultStr 密文
     * @return 明文
     */
    public static String decryptAES(String encryptResultStr) {
        if (!StringUtils.hasLength(encryptResultStr)) {
            throw new IllegalArgumentException("密文不能为空");
        }
        // BASE64位解密
        try {
            String decrypt = bowingDecrypt(encryptResultStr);
            byte[] decryptFrom = parseHexStr2Byte(decrypt);
            byte[] decryptResult = decrypt(decryptFrom, PASSWORD);
            return new String(decryptResult);
        } catch (Exception e) { // 当密文不规范时会报错，可忽略，但调用的地方需要考虑
            return null;
        }
    }

    /**
     * 加密字符串
     */
    private static String bowingEncrypt(String str) {
        String result = str;
        if (str != null && !str.isEmpty()) {
            try {
                byte[] encodeByte = str.getBytes(ENCODING);
                result = Base64.getEncoder().encodeToString(encodeByte);
            } catch (Exception e) {
               logger.error("加密异常", e);
            }
        }
        // base64加密超过一定长度会自动换行 需要去除换行符
        return Objects.requireNonNull(result).replaceAll("\r\n", "").replaceAll("\r", "").replaceAll("\n", "");
    }

    /**
     * 解密字符串
     */
    private static String bowingDecrypt(String str) {
        byte[] encodeByte = Base64.getDecoder().decode(str);
        return new String(encodeByte);
    }

    /**
     * 加密
     */
    private static byte[] encrypt(String content, String password) {
        try {
            byte[] byteContent = content.getBytes(StandardCharsets.UTF_8);
            return buildCipher(Cipher.ENCRYPT_MODE, password).doFinal(byteContent);
        } catch (Exception e) {
            logger.error("加密异常", e);
            throw new IllegalArgumentException();
        }
    }

    /**
     * 解密
     *
     * @param content  待解密内容
     * @param password 解密密钥
     */


    private static byte[] decrypt(byte[] content, String password) {
        try {
            return buildCipher(Cipher.DECRYPT_MODE, password).doFinal(content);
        } catch (Exception e) {
            logger.error("解密异常", e);
            throw new IllegalArgumentException();
        }
    }

    private static Cipher buildCipher(int cipherMode, String security) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        // 防止linux下 随机生成key
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(security.getBytes());
        kgen.init(128, secureRandom);
        // kgen.init(128, new SecureRandom(password.getBytes()));
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(cipherMode, key);
        return cipher;
    }

    /**
     * 将二进制转换成16进制
     */
    private static String parseByte2HexStr(byte[] buf) {
        StringBuilder sb = new StringBuilder();
        for (byte b : buf) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     */
    private static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.isEmpty()) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    public static void main(String[] args) {

        String str = "hello world";
        String secret = "1234567890jfdkjsf";

        String encrypt = bowingEncrypt(str);

        System.out.println(encrypt);


    }

}
