package com.javaliu.platform.security;

import org.apache.commons.codec.binary.Hex;
import org.apache.shiro.crypto.hash.SimpleHash;

import java.security.SecureRandom;

/**
 * <br> 类 名：Digests
 * <br> 描 述：密码加密算法
 * <br> 作 者：javaliu
 * <br> 创 建：2017年08月17日
 * <br> 版 本：V1.0
 */
public class Digests {
    /**
     * Hash 迭代次数
     */
    public static final int HASH_ITERATIONS = 100;

    /**
     * SHA-1 散列算法
     */
    public static final String SHA1 = "SHA-1";



    private static final SecureRandom random = new SecureRandom();

    /**
     * 生成随机的Byte[]作为salt.
     */
    public static String generateSalt() {
        byte[] bytes = new byte[4];
        random.nextBytes(bytes);
        return Hex.encodeHexString(bytes);
    }

    /**
     * 加密
     * @param plainPassword   明文
     * @param salt			  盐
     * @return				  密文
     */
    public static String password(String plainPassword, String salt){
        //Sha1Hash sha1Hash = new Sha1Hash(plainPassword, salt, HASH_ITERATIONS);
        SimpleHash simpleHash = new SimpleHash(SHA1, plainPassword, salt, HASH_ITERATIONS);
        return simpleHash.toHex();
    }
}
