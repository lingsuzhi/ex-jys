package com.lsz.jys.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;


public class SignUtils {

    /**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";

    public final static String Sign_Header_Name = "xjjd-sign";
    /**
     * 签名算法
     */
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    // http://web.chacuo.net/netrsakeypair 在网站随机生成
    public static final String Public_Key = "-----BEGIN PUBLIC KEY-----\n" +
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDEQ9jBuH79QeRBMPpJnREz2mP2\n" +
            "KVOIIOSSJSopobToPYQ3hwoafdtA7wN+RCojj0EZ8ruL3pkE7hUGTHBFF3dhV/DF\n" +
            "kNbsOh8Kh/VWEbV+CJy1Q0j+YuOfJdFarB3uilaNi72+w2dZWYC74/ABo8f6px55\n" +
            "WMGyWIXLII9M3sDO2wIDAQAB\n" +
            "-----END PUBLIC KEY-----\n";
    public static final String Private_Key = "-----BEGIN PRIVATE KEY-----\n" +
            "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMRD2MG4fv1B5EEw\n" +
            "+kmdETPaY/YpU4gg5JIlKimhtOg9hDeHChp920DvA35EKiOPQRnyu4vemQTuFQZM\n" +
            "cEUXd2FX8MWQ1uw6HwqH9VYRtX4InLVDSP5i458l0VqsHe6KVo2Lvb7DZ1lZgLvj\n" +
            "8AGjx/qnHnlYwbJYhcsgj0zewM7bAgMBAAECgYB9TVeorlKc/CFvIYbZ0qZ19XsW\n" +
            "YOIMqftj9shJVg06rHOqQ9PGgA2oEjCnv3srwrzshRUJwm7tP9c6czjGU6oJ494Z\n" +
            "tX6K7notDZhovCxGYBfDXGsgywICCd4LFfMdgfH6j5YgQ80JF5t/Qgl4HQN78YfR\n" +
            "aZFjerwnXdYHZZ0PkQJBAPH9F41to7NXhs8Idth3My5BY8JOiTPseDV1f9UCZekX\n" +
            "kpVOQQbM1me41rIH63YmmYOMG+y3bQz5cnYbNa3CNgMCQQDPoQKwN8O1R05rlfuH\n" +
            "povtD1AVT1MlXewvRhkdBwQTCHZXxNmReR4wVn9n7XhLy+vDcm7vyGGfX2AfzU7E\n" +
            "WnhJAkEA7YzlpQl/TNvcU7rcaA1D6eF5G9La72TwcJJDBgkPn+1rETS7swA8bZ/O\n" +
            "6v/JTrf/tVpCQ3OhEr6BdZ2qaUOgnwJBAK/IdFs2yxWl6inB0DNXeK3aQgXYo99F\n" +
            "9llLsqVVtEhc6LyEJ2M3NKzd/n3BmhAp9FADiRKvBp+EgwBJtL8ejSkCQCEmu8Yv\n" +
            "HaaedQIEHMsbB0xXG7g0/SvbJQfqkngA2J0L39PAUFZebHFrRCgxOg8Tlz5rixoa\n" +
            "IjipeH+lBnH8eFY=\n" +
            "-----END PRIVATE KEY-----\n";

    /**
     * 用私钥对信息生成数字签名
     */
    public static String sign(byte[] data, String skey) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(skey.split("-----")[2]);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateK);
        signature.update(data);
        return Base64.encodeBase64String(signature.sign());
    }

    public static String sign(Object data, String skey) throws Exception {
        byte[] serialize = SerializeUtil.serialize(data);
        return sign(serialize, skey);
    }

    public static boolean verify(Object data, String pkey, String sign) throws Exception {
        byte[] serialize = SerializeUtil.serialize(data);
        return verify(serialize, pkey, sign);
    }

    /**
     * 校验数字签名
     */
    public static boolean verify(byte[] data, String pkey, String sign)
            throws Exception {
        byte[] keyBytes = Base64.decodeBase64(pkey.split("-----")[2]);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicK = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(publicK);
        signature.update(data);
        return signature.verify(Base64.decodeBase64(sign));
    }

    public static boolean checkSign(Object data) {
        String sign = TokenUtil.getHeader(SignUtils.Sign_Header_Name);
        if (StringUtils.isEmpty(sign)) {
            return false;
        }
        try {
            if (SignUtils.verify(data, SignUtils.Public_Key, sign)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
//    public static void main(String[] args) throws Exception {
//        //使用示例
//        AssetsChangeDTO assetsChangeDTO = new AssetsChangeDTO();
//        assetsChangeDTO.setMemberId(888L);
//        assetsChangeDTO.setCoin("BTC");
//        assetsChangeDTO.setAmount(new BigDecimal("1.1"));
//        assetsChangeDTO.setBalanceOperateEnum(BalanceOperateEnum.Recharge);
//        String sign = sign(assetsChangeDTO, Private_Key);
//
//        boolean verify = verify(assetsChangeDTO, Public_Key, sign);
//        System.out.println(verify);
//        System.out.println(sign);
//    }

}