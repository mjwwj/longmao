package com.opengroup.longmao.gwcommon.tools.gen.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GenUtil {
	
	/**
	 * 得到实体类名
	 * @param tablename
	 * @return
	 */
	public static String bigTableName(String tablename) {
		return GenUtil.initcap(GenUtil.strChange(tablename));
	}
	
	/**
	 * 
	 * 获得系统当前时间
	 * 
	 * @author zengjq 2016年4月9日
	 * @return
	 */
	public static String getDate() {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy年MM月dd日 aHH:mm:ss");
		String time = format.format(date);
		return time;
	}

	/**
	 * 功能：将输入字符串的首字母改成大写
	 * 
	 * @param str
	 * @return
	 */
	public static String initcap(String str) {
		char[] ch = str.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0] - 32);
		}
		return new String(ch);
	}

	/**
	 * 首字母转大写
	 * 
	 * @author nan 2016年4月8日
	 * @param s
	 * @return
	 */
	// public static String toUpperCaseFirstOne(String s){
	// if(Character.isUpperCase(s.charAt(0)))
	// return s;
	// else
	// return (new
	// StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
	// }

	/**
	 * 功能：获得列的数据类型
	 * 
	 * @param sqlType
	 * @return
	 */
	public static String sqlType2JavaType(String sqlType) {
		if (sqlType.equalsIgnoreCase("bit")) {
			return "Boolean";
		} else if (sqlType.equalsIgnoreCase("tinyint")) {
			return "Byte";
		} else if (sqlType.equalsIgnoreCase("smallint")) {
			return "Short";
		} else if (sqlType.equalsIgnoreCase("int") || sqlType.equalsIgnoreCase("INT UNSIGNED")) {
			// INT UNSIGNED无符号整形
			return "Integer";
		} else if (sqlType.equalsIgnoreCase("bigint")) {
			return "Long";
		} else if (sqlType.equalsIgnoreCase("float")) {
			return "Float";
		} else if (sqlType.equalsIgnoreCase("numeric") || sqlType.equalsIgnoreCase("real")
				|| sqlType.equalsIgnoreCase("money") || sqlType.equalsIgnoreCase("smallmoney")|| sqlType.equalsIgnoreCase("double")) {
			return "Double";
		} else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")
				|| sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")
				|| sqlType.equalsIgnoreCase("text")) {
			return "String";
		} else if (sqlType.equalsIgnoreCase("datetime") || sqlType.equalsIgnoreCase("date")
				|| sqlType.equalsIgnoreCase("timestamp")) {
			return "Date";
		} else if (sqlType.equalsIgnoreCase("image")) {
			return "Blod";
		} else if (sqlType.equalsIgnoreCase("decimal")) {
			return "BigDecimal";
		}
		return null;
	}

	/**
	 * 去掉下划线并将下划线后面的首字母变大写
	 * @param s
	 * @return
	 */
	public static String strChange(String s) {
		String str[] = s.split("_");
		String tempString = str[0];
		for (int i = 1; i < str.length; i++) {
			str[i] = initcap(str[i]);
			tempString = tempString + str[i];
		}
		return tempString;
	}
}
