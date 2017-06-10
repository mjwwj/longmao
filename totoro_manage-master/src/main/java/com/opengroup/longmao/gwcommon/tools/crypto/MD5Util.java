package com.opengroup.longmao.gwcommon.tools.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.opengroup.longmao.gwcommon.tools.crypto.Base64;

/**
 * MD5加密工具
 * @author xiaoli<xiaoli@zjyushi.com>
 * @time Mar 27, 2015
 * @version 0.1
 * @since 0.1
 */
public class MD5Util {

	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			resultSb.append(byteToHexString(b[i]));

		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String MD5Encode(String origin, String charsetname) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (charsetname == null || "".equals(charsetname))
				resultString = byteArrayToHexString(md.digest(resultString
						.getBytes()));
			else
				resultString = byteArrayToHexString(md.digest(resultString
						.getBytes(charsetname)));
		} catch (Exception exception) {
		}
		return resultString;
	}
	/**
	 * MD5加密方法
	 * @author xiaoli<xiaoli@zjyushi.com>
	 * @time Mar 27, 2015
	 * @version 0.1
	 * @since 0.1
	 * @param input
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String MD5(String input){
		if (input == null || input.length() == 0)
			return "";

		String out = "";
		byte[] output;
		MessageDigest m;
		try {
			m = MessageDigest.getInstance("MD5");
			output = m.digest(input.getBytes());
			int len = output.length;
			
			for (int i = 0; i < len; i++) {
				
				String t = Integer.toHexString(output[i] & 0x00FF);
				
				out += (t.length() == 1) ? ("0" + t) : t;
				
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO 这是系统自动生成描述，请在此补完后续代码
			e.printStackTrace();
		}

		return out;
	}

	
	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	
	
	/**
	 * 解密字符串算法
	 * @param input
	 * @return
	 * @Author : wenfei
	 */
	public static String encode(String input){
		try {
			return new String(Base64.encode(input.getBytes("iso-8859-1")));
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}
