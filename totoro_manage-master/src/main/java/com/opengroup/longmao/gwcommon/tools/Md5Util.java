/**
 * @Title: Md5Util.java 
 * @Package com.opengroup.longmao.gwcommon.tools 
 * @Description:
 * @author Mr.Zhu
 * @version V1.5
 */
package com.opengroup.longmao.gwcommon.tools;

import java.security.MessageDigest;

/**
 * @ClassName: Md5Util
 * @Description: MD5加密
 * @author Mr.Zhu
 */
public class Md5Util {

	/**
	 * @Title: thirtyTwo
	 * @Description: 32位MD5加密
	 * @param content
	 * @return String
	 */
	public static String thirtyTwo(String content) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] resultByte = md.digest(content.getBytes("UTF-8"));
			StringBuffer buf = new StringBuffer("");
			int i;
			for (int offset = 0; offset < resultByte.length; offset++) {
				i = resultByte[offset];
				i = i & 0xff;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return buf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
