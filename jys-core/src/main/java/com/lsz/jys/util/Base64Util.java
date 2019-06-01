package com.lsz.jys.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

public class Base64Util {
    /**
     * BASE64解密
     * @param key 文本
     * @return 内容
     */
    public static String decryptBASE64(String key) {
        try {
            return new String ((new BASE64Decoder()).decodeBuffer(key));
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * BASE64加密
     * @param key 文本
     * @return 内容
     */
    public static String encryptBASE64(String key){
        return (new BASE64Encoder()).encodeBuffer(key.getBytes());
    }
}
