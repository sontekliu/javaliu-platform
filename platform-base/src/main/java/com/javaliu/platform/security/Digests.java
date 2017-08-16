package com.javaliu.platform.security;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.Validate;
import org.apache.shiro.crypto.hash.Sha1Hash;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 * 支持SHA-1/MD5消息摘要的工具类.
 * 
 * 返回ByteSource，可进一步被编码为Hex, Base64或UrlSafeBase64
 * 
 * @author calvin
 */
public class Digests {

	/**
	 * Hash 迭代次数
	 */
	private static final int HASH_ITERATIONS = 100;

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
		Sha1Hash sha1Hash = new Sha1Hash(plainPassword, salt, HASH_ITERATIONS);
		return sha1Hash.toHex();
	}

	public static void main(String args[]){
		String password = password("admin","07a349c0");
		//5f8a857efbeab45cee4eec62134c5cc97b46b0c8
		System.out.println(password);
		System.out.println(password.length());
		System.out.println(new Sha1Hash("admin").toHex());
	}

}
