package com.opengroup.longmao.gwcommon.tools.csv;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opengroup.longmao.gwcommon.tools.result.RetResult;

public final class CSVHandleUtil {

	private static CSVReader reader;

	private CSVHandleUtil() {
	}

	//对csv文件处理
	public static RetResult readCSV(String cardType, MultipartFile file,HttpServletRequest request) throws IOException {
		// 得到上传文件名称
		String fileName = file.getOriginalFilename();
		// 分割后插入时间，生成新的文件名
		String newName = CSVHandleUtil.createNewName(fileName);
		// 文件保存路径
		String fileDir = CSVConstant.CSV_SAVE_PATH;
		// 获取文件的后缀
		String suffix = fileName.substring(fileName.lastIndexOf("."));
		// 初始化list，装在csv数据
		List<String[]> csvData = new ArrayList<String[]>();

		String code = CSVConstant.CSV_COD_0000;
		String message = CSVConstant.CSV_MSG_0000;
		// 判断文件是否为空
		if (file != null && file.isEmpty()) {
			code = CSVConstant.CSV_COD_0001;
			message = CSVConstant.CSV_MSG_0001;
			return RetResult.setRetDate(code, message, null);
		}
		// 文件后缀不正确
		if (!suffix.equals(CSVConstant.FILE_EXT)) {
			code = CSVConstant.CSV_COD_0002;
			message = CSVConstant.CSV_MSG_0002;
			return RetResult.setRetDate(code, message, null);
		}
		if (file.getSize() > CSVConstant.MAX_MB) {
			// 文件太大，不能超过10M;
			code = CSVConstant.CSV_COD_0003;
			message = CSVConstant.CSV_MSG_0003;
			return RetResult.setRetDate(code, message, null);
		}
		// 上传CSV文件(抛出IllegalStateException, IOException异常)
		File CSVFile = uploadCSV(file, fileDir,newName);
		// 文件不存在
		if (!CSVFile.exists()) {
			code = CSVConstant.CSV_COD_0004;
			message = CSVConstant.CSV_MSG_0004;
			return RetResult.setRetDate(code, message, null);
		}
		// 将数据装入list，泛型string 数组
		csvData = CSVHandleUtil.getCSVData(CSVFile);

		if (csvData.size() > CSVConstant.MAX_LINE) {
			code = CSVConstant.CSV_COD_0006;
			message = CSVConstant.CSV_MSG_0006;
			return RetResult.setRetDate(code, message, null);
		}
		
		// 除去空行
		excludingTail(csvData);
		
		// 数据为空
		if (CollectionUtils.isEmpty(csvData)) {
			code = CSVConstant.CSV_COD_0005;
			message = CSVConstant.CSV_MSG_0005;
			return RetResult.setRetDate(code, message, null);
		}
		
		RetResult result1 = checkCSVHeaderWhenBinds(csvData);
		// 检查文件头和绑定信息，状态不是200，表示有问题
		if (!result1.getCode().equals(CSVConstant.CSV_COD_0000)) {
			code = result1.getCode();
			message = result1.getMessage();
			return RetResult.setRetDate(code, message, result1.getData());
		}
		RetResult result2 = checkCSVBodyWhenBinds(csvData, cardType);
		// 检查数据和数据绑定格式信息，状态不是200，表示有问题
		if (!result2.getCode().equals(CSVConstant.CSV_COD_0000)) {
			code = result2.getCode();
			message = result2.getMessage();
			return RetResult.setRetDate(code, message, result2.getData());
		}
		return RetResult.setRetDate(code, message, csvData);
	}

	// 获取当前时间
	public static String getCurrentDate() {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = format.format(date);
		return time;
	}

	// 分割后创建新的文件名
	public static String createNewName(String fileName) {
		// 获取文件的后缀
		String[] str = fileName.split("\\.", 2);
		String newName = str[0] + "_" + getCurrentDate() + "." + str[1];
		return newName;
	}

	// 上传文件
	public static File uploadCSV(MultipartFile file, String fileDir,String newName)throws IllegalStateException, IOException {
		
		File f= new File(fileDir);
		//如果文件夹不存在则创建    
		if  (!f.exists() && !f.isDirectory()){  
			f.mkdir(); 
		} 
		File CSVFile= new File(fileDir + newName);
		// 转存文件
		file.transferTo(CSVFile);
		return CSVFile;
	}

	// 读取CSV文件数据
	public static List<String[]> getCSVData(File CSVFile) throws IOException {
		List<String[]> csvData = new ArrayList<String[]>();
		// 使用openCSV读取文件，以逗号分割
		FileReader fReader = new FileReader(CSVFile);
		reader = new CSVReader(fReader, ',');
		// 使用开源opencsv插件读取csv
		csvData = reader.readAll();
		return csvData;
	}

	// 去除空行数据，只要每行中有一个单元格有数据，就表示该行是有效的，一行中所有的单元格都是空的，就去除该行
	public static void excludingTail(List<String[]> csvData) {
		// 装载所有空行
		List<String[]> toExclud = new ArrayList<String[]>();
		// 从最后一行开始检查
		for (int tail = csvData.size() - 1; tail >= 0; tail--) {
			// 得到每一行数据
			String[] row = csvData.get(tail);
			if (row != null && row.length > 0) {
				// true表示有效，false 表示无效
				Boolean isBlank = true;
				// 遍历每一行中的单元
				for (String cell : row) {
					// 只要每行中有一个单元格有数据，就跳出去不在检查，表示该行是有效的
					if (StringUtils.isNotBlank(cell)) {
						isBlank = false;
						break;
					}
				}
				// 如果if是满足true的条件就跳出最外面循环体，表式是有效数据，不删除
				if (!isBlank) {
					break;
				}
			}
			// 行数据为空就添加到toExclud
			// 第"+tail+"行单元格为空
			toExclud.add(row);
		}
		// 清除刚刚遍历的所有空行
		csvData.removeAll(toExclud);
	}

	// 检查单元格是否为空
	public static boolean checkCSVCells(String[] row) {
		// 遍历行中的单元格
		for (String cell : row) {
			if (StringUtils.isBlank(cell)) {
				// 单元格为空
				return false;
			}
		}
		return true;
	}

	// 检查文件头绑定信息
	public static RetResult checkCSVHeaderWhenBinds(List<String[]> csvData) {
		String code = CSVConstant.CSV_COD_0000;
		String message = CSVConstant.CSV_MSG_0000;
//		if(CollectionUtils.isEmpty(csvData)){
//			code = CSVConstant.CSV_COD_0008;
//			message = CSVConstant.CSV_MSG_0008;
//			return RetResult.setRetDate(code, message, null);
//		}
		// 得到文件第一行文件头信息
		String[] CSVHeader = csvData.get(0);
		// 判断文件头是否不存在
		if (CSVHeader == null || CSVHeader.length <= 0) {
			// CSV文件头为不存在
			code = CSVConstant.CSV_COD_0008;
			message = CSVConstant.CSV_MSG_0008;
			return RetResult.setRetDate(code, message, null);
		}
		// 判断绑定列是否正确
		if (!CSVConstant.BINDS_COLUMNS.equals(CSVHeader.length)) {
			// CSV文件列数不正确
			code = CSVConstant.CSV_COD_0009;
			message = CSVConstant.CSV_MSG_0009;
			return RetResult.setRetDate(code, message, null);
		}
		// 检查单元格是否为空
		if (!checkCSVCells(CSVHeader)) {
			// 单元格为空
			code = CSVConstant.CSV_COD_0010;
			message = CSVConstant.CSV_MSG_0010;
			return RetResult.setRetDate(code, message, null);
		}

		// 对文件头信息进行格式检查（根据数据表格来定）
		// 例如：表头第一列必须是卡号：
		String numberColumn = CSVHeader[0].trim();
		if (!numberColumn.equals("cardNo")) {
			// 第一列应该是‘卡号‘
			code = CSVConstant.CSV_COD_0011;
			message = CSVConstant.CSV_MSG_0011;
			return RetResult.setRetDate(code, message, null);
		}
		// 例如：表头第二列必须是密码：
		String passwordColumn = CSVHeader[1].trim();
		if (!passwordColumn.equals("cardPwd")) {
			// 第二列应该是‘卡密‘
			code = CSVConstant.CSV_COD_0012;
			message = CSVConstant.CSV_MSG_0012;
			return RetResult.setRetDate(code, message, null);
		}
		// 例如：表头第三列必须是面额：
		String priceColumn = CSVHeader[2].trim();
		if (!priceColumn.equals("cardPrice")) {
			// 第三列应该是‘面额‘
			code = CSVConstant.CSV_COD_0013;
			message = CSVConstant.CSV_MSG_0013;
			return RetResult.setRetDate(code, message, null);
		}
		return RetResult.setRetDate(code, message, null);
	}

	// 检查文件内容绑定信息
	public static RetResult checkCSVBodyWhenBinds(List<String[]> csvData,String cardType) {
		String code = CSVConstant.CSV_COD_0000;
		String message = CSVConstant.CSV_MSG_0000;
		int rowCount = 1;
		// 遍历每一条csv数据
		for (String[] bodyData : csvData) {
			// 跳过表头，从第二行开始读取
			if (rowCount == 1) {
				rowCount++;
				continue;
			}
			// 判断行中字符串个数是否小于0
			if (bodyData.length <= 0) {
				// 行中字符串长度不正确
				code = CSVConstant.CSV_COD_0014;
				message = CSVConstant.CSV_MSG_0014;
				return RetResult.setRetDate(code, message, null);
			}
			// 判断行中单元是否为指定单元数
			if (!CSVConstant.BINDS_COLUMNS.equals(bodyData.length)) {
				// 行中列数量和指定列数不匹配
				code = CSVConstant.CSV_COD_0015;
				message = CSVConstant.CSV_MSG_0015;
				return RetResult.setRetDate(code, message, null);
			}
			// 保证每个单元格中的都有数据
			if (!checkCSVCells(bodyData)) {
				// 单元格数据为空
				code = CSVConstant.CSV_COD_0016;
				message = CSVConstant.CSV_MSG_0016;
				return RetResult.setRetDate(code, message, null);
			}

			// 对文件数据信息进行格式检查（根据数据表格来定）
			// 例如：数据第一列是卡号：不包括零的非负整数（正整数 > 0)
			String firstColumn = bodyData[0].trim();
			if (!RegexpUtils.isHardRegexpValidate(firstColumn,RegexpUtils.POSITIVE_INTEGER_REGEXP)) {
				// if
				// (!RegexpUtils.isHardRegexpValidate(firstColumn,"^\\+?[1-9][0-9]*$"))
				// {
				// 第一列数据应该是不包括零的非负整数（正整数 > 0)
				code = CSVConstant.CSV_COD_0017;
				message = CSVConstant.CSV_MSG_0017;
				return RetResult.setRetDate(code, message, null);
			}
			// 例如：数据第二列必须是卡密：正整数
			String secondColumn = bodyData[1].trim();
			// if
			// (!RegexpUtils.isHardRegexpValidate(secondColumn,"^[0-9]\\d*$")) {
			if (!RegexpUtils.isHardRegexpValidate(secondColumn,RegexpUtils.POSITIVE_INTEGER_REGEXP)) {
				// 第二列数据应该是正整数
				code = CSVConstant.CSV_COD_0018;
				message = CSVConstant.CSV_MSG_0018;
				return RetResult.setRetDate(code, message, null);
			}
			// 例如：第三列必须是面额：必须是非负整数（正整数 > 0)
			String thirdColumn = bodyData[2].trim();
			if (!RegexpUtils.isHardRegexpValidate(thirdColumn,RegexpUtils.NON_ZERO_NEGATIVE_INTEGERS_REGEXP)) {
				// 第三列数据应该是非负整数（正整数 > 0）
				code = CSVConstant.CSV_COD_0019;
				message = CSVConstant.CSV_MSG_0019;
				return RetResult.setRetDate(code, message, null);
			}
			//判断数据中的面额是否是被定义的面额
			boolean f = Arrays.asList(CSVConstant.CARD_PRICE).contains(thirdColumn);
			if(!f){
				// 第三列存在非法面额
				code = CSVConstant.CSV_COD_0042;
				message = CSVConstant.CSV_MSG_0042;
				return RetResult.setRetDate(code, message, null);
			}
			// 根据选择的充值卡类型判断
			// 选择联通,联通充值卡序列号15位，密码19位
			if (cardType.trim().equals("1")) {
				if (firstColumn.length() != CSVConstant.LT_CARD_NO_LENGTH || secondColumn.length() != CSVConstant.LT_CARD_PWD_LENGTH) {
					// 联通充值卡的充值卡号是15位，密码19位
					code = CSVConstant.CSV_COD_0020;
					message = CSVConstant.CSV_MSG_0020;
					return RetResult.setRetDate(code, message, null);
				}
			}
			// 选择移动,移动充值卡序列号17位，密码18位
			if (cardType.trim().equals("2")) {
				if (firstColumn.length() != CSVConstant.YD_CARD_NO_LENGTH || secondColumn.length() != CSVConstant.YD_CARD_PWD_LENGTH) {
					// 移动充值卡的充值卡号是17位，密码18位
					code = CSVConstant.CSV_COD_0021;
					message = CSVConstant.CSV_MSG_0021;
					return RetResult.setRetDate(code, message, null);
				}
			}
			// 选择电信,电信充值卡序列号19位，密码18位
			if (cardType.trim().equals("3")) {
				if (firstColumn.length() != CSVConstant.DX_CARD_NO_LENGTH || secondColumn.length() != CSVConstant.DX_CARD_PWD_LENGTH) {
					// 电信充值卡的充值卡号是19位，密码18位
					code = CSVConstant.CSV_COD_0022;
					message = CSVConstant.CSV_MSG_0022;
					return RetResult.setRetDate(code, message, null);
				}
			}
		}
		return RetResult.setRetDate(code, message, null);
	}
}