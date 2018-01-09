package com.xingrongjinfu.utils;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import net.sf.json.JSONObject;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;



/**
 * Created by dkyear on 2016/3/4. DES 加密、解密 ISO ANDRIOD 通用
 */
public class DesUtils {
	private final static String DES = "DES";
	// 加密键byte数组
	private final static String key = "FAST2016";

	/**
	 * Description 根据键值进行加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String data) throws EncryptExcption {
		try {
			byte[] bt = encrypt(data.getBytes("utf-8"), key.getBytes());
			String strs = new BASE64Encoder().encode(bt);
			return strs;
		} catch (Exception e) {
			throw new EncryptExcption("加密出错");
		}
	}

	/**
	 * Description 根据键值进行解密
	 * 
	 * @param data
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public static String decrypt(String data) throws DecryptExcption {
		try {
			if (data == null) {
				return null;
			}
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] buf = decoder.decodeBuffer(data);
			byte[] bt = decrypt(buf, key.getBytes());
			return new String(bt, "UTF-8").replaceAll("\\r\\n", "");
		} catch (Exception e) {
			throw new DecryptExcption("解密出错");
		}

	}

	/**
	 * Description 根据键值进行加密
	 * 
	 * @param data
	 * @param key
	 *            加密键byte数组
	 * @return
	 * @throws Exception
	 */
	private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		// 生成一个可信任的随机数源
		SecureRandom sr = new SecureRandom();

		// 从原始密钥数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);

		// 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);

		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance(DES);

		// 用密钥初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

		return cipher.doFinal(data);

	}

	/**
	 * Description 根据键值进行解密
	 * 
	 * @param data
	 * @param key
	 *            加密键byte数组
	 * @return
	 * @throws Exception
	 */
	private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		// 生成一个可信任的随机数源
		SecureRandom sr = new SecureRandom();

		// 从原始密钥数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);

		// 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);

		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance(DES);

		// 用密钥初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

		return cipher.doFinal(data);

	}

	public static String SHA1(String decript) {
		try {
			MessageDigest digest = MessageDigest
					.getInstance("SHA-1");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}


	/**
	 * String 转MAP
	 * @param args
	 * @throws Exception
	 */
	public static HashMap stringToMap(String data) throws DecryptExcption {
		try {
			JSONObject j = JSONObject.fromObject(data);
			HashMap<String, Object> map = (HashMap<String, Object>) JSONObject.toBean(j, HashMap.class);
			return map;
		} catch (Exception e) {
			throw new DecryptExcption("解析出错");
		}
	}


	public static void main(String[] args) throws Exception {
		String data = "{\n" + "\"APP_VERSION\": \"v1.0\",\n"
				+ "    \t\"ACTION\": \"syncDepartment\",\n"
				+ "   \t\"TOKEN_ID\": \"\",\n"
				+ "    \t\"DEVICE_ID\": \"999kkkk\"\n" + "}\n";
		String encoding = encrypt(data);
		System.err.println(data);
		System.err.println("encoding = " + encoding);
		System.err.println("decoding = " + decrypt(encoding));
	}

}
