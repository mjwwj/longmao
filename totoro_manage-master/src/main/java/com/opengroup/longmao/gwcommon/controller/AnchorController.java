/**
 * @Title: AnchorController.java 
 * @Package com.opengroup.longmao.gwcommon.controller 
 * @Description:
 * @author Mr.Zhu
 * @version V1.5
 */
package com.opengroup.longmao.gwcommon.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opengroup.longmao.gwcommon.configuration.annotation.JsonParam;
import com.opengroup.longmao.gwcommon.configuration.log.GwsLogger;
import com.opengroup.longmao.gwcommon.entity.dto.OrderInfoDTO;
import com.opengroup.longmao.gwcommon.entity.po.IdentityInfo;
import com.opengroup.longmao.gwcommon.entity.po.OrderInfo;
import com.opengroup.longmao.gwcommon.entity.po.User;
import com.opengroup.longmao.gwcommon.entity.vo.AnchorVO;
import com.opengroup.longmao.gwcommon.entity.vo.OrderInfoVO;
import com.opengroup.longmao.gwcommon.service.AnchorService;
import com.opengroup.longmao.gwcommon.tools.ExportExcelUtil;
import com.opengroup.longmao.gwcommon.tools.result.CommConstant;
import com.opengroup.longmao.gwcommon.tools.result.ImplException;
import com.opengroup.longmao.gwcommon.tools.result.RetResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName: AnchorController 
 * @Description: 运营临时Controller
 * @author Mr.Zhu
 */
@EnableSwagger2
@Api(value = "运营临时Controller", tags = "manage")
@Controller
public class AnchorController {
	
	@Autowired
	private AnchorService anchorService;
	
	@RequestMapping("/anchor.html")
	public String anchorInfo() {
		return "anchor/list";
	}
	
	@RequestMapping("/cash.html")
	public String cashInfo() {
		return "anchor/cash";
	}
	
	@ApiOperation(value = "查询所有用户信息并分页", notes = "查询所有用户信息并分页")
	@ApiImplicitParams({ @ApiImplicitParam(name = "anchorVO", value = "详细实体AnchorVO", required = true, dataType = "AnchorVO"),
			@ApiImplicitParam(name = "pageNo", value = "第几页", required = false, dataType = "Long"),
			@ApiImplicitParam(name = "pageSize", value = "每页条数", required = false, dataType = "String") })
	@RequestMapping(value = "/findAnchor", method = RequestMethod.POST)
	public @ResponseBody RetResult findAnchor(@JsonParam AnchorVO anchorVO, Integer pageNo, Integer pageSize) {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		// 查询所有用户信息表
		Page<User> page = null;
		try {
			page = anchorService.findAllUser(anchorVO, pageNo - 1, pageSize, "gmtCreate");
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("查询所有用户信息异常:code={},message={},user={},pageNo={},pageSize={},softField={},e={}", code,
					message, ToStringBuilder.reflectionToString(anchorVO), pageNo - 1, pageSize, e);
		}
		return RetResult.setRetDate(code, message, page);
	}
	
	@ApiOperation(value = "查询主播提现记录信息并分页", notes = "查询主播提现记录信息并分页")
	@ApiImplicitParams({ @ApiImplicitParam(name = "orderVO", value = "详细实体OrderInfoVO", required = true, dataType = "OrderInfoVO"),
			@ApiImplicitParam(name = "pageNo", value = "第几页", required = false, dataType = "Long"),
			@ApiImplicitParam(name = "pageSize", value = "每页条数", required = false, dataType = "String") })
	@RequestMapping(value = "/findAllOrder", method = RequestMethod.POST)
	public @ResponseBody RetResult findAllOrder(@JsonParam OrderInfoVO orderVO, Integer pageNo, Integer pageSize) {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		// 查询所有用户信息表
		Page<OrderInfo> page = null;
		try {
			page = anchorService.findAllOrder(orderVO, pageNo - 1, pageSize, "ctime");
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("查询主播提现记录信息异常:code={},message={},orderVO={},pageNo={},pageSize={},softField={},e={}", code,
					message, ToStringBuilder.reflectionToString(orderVO), pageNo - 1, pageSize, e);
		}
		return RetResult.setRetDate(code, message, page);
	}
	
	/**
	 * 
	 * 	提现导出
	 * 
	 * @author Hermit 2016年5月17日
	 */
	@RequestMapping(value = "/cashExprot")
	public  @ResponseBody void cashExprot(@JsonParam OrderInfoVO orderVO,HttpServletResponse response){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("提现导出:code={},message={}，orderVO={}",code,message,ToStringBuilder.reflectionToString(orderVO));
		try {
			List<OrderInfoDTO> data = anchorService.cashExprot(orderVO,"ctime");
			List<String[]> stringList =  new ArrayList<String[]>();
			for(OrderInfoDTO dto : data){
				String[] str = {
					dto.getOrderId().toString(),
					dto.getBuyerUid().toString(),
					dto.getOrderPrice().toString(),
					dto.getDividedPrice().toString(),
					dto.getTimeStr(),
					dto.getRemark().toString(),
					dto.getOrderStatusStr()
				};
				stringList.add(str);
			}
			//表头
			String[] header = CommConstant.CSV_HEADER;
            //报表名称
			String excelTitle = "提现导出统计";
			 List<String> extInfo = new  ArrayList<String>();
			 extInfo.add(excelTitle);
			//导出Excel表格
			HSSFWorkbook wb = ExportExcelUtil.export(excelTitle, header, stringList, extInfo, 7);
			//输出Excel
			response.setContentType("application/vnd.ms-excel");    
			response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode(excelTitle,"utf-8")+".xls");    
			OutputStream ouputStream = response.getOutputStream();    
			wb.write(ouputStream);    
			ouputStream.flush();    
			ouputStream.close();    
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("提现导出异常:code={},message={},e={}",code,message,e);
			e.printStackTrace();
		}
		GwsLogger.info("提现导出结束:code={},message={}",code,message);
	}	
	
//	@ApiOperation(value = "查询主播提现记录信息", notes = "查询主播提现记录信息")
//	@ApiImplicitParams({ @ApiImplicitParam(name = "orderVO", value = "详细实体OrderInfoVO", required = true, dataType = "OrderInfoVO")})
//	@RequestMapping(value = "/findAllOrder", method = RequestMethod.POST)
//	public @ResponseBody RetResult findAllOrder(@JsonParam OrderInfoVO orderVO) {
//		String code = CommConstant.GWSCOD0000;
//		String message = CommConstant.GWSMSG0000;
//		// 查询所有用户信息表
//		List<OrderInfo> list = null;
//		try {
//			list = anchorService.findAllOrder(orderVO, "ctime");
//		} catch (Exception e) {
//			code = CommConstant.GWSCOD0001;
//			message = CommConstant.GWSMSG0001;
//			GwsLogger.error("查询主播提现记录信息异常:code={},message={},orderVO={},softField={},e={}", code,
//					message, ToStringBuilder.reflectionToString(orderVO), e);
//		}
//		return RetResult.setRetDate(code, message, list);
//	}
	
	@ApiOperation(value = "主播认证信息接口", notes = "获取主播认证信息相关数据")
	@ApiImplicitParams({ @ApiImplicitParam(name="userId", value="用户ID", required=true, dataType="String")})
	@RequestMapping(value = "/anchorLog", method = RequestMethod.POST)
	public @ResponseBody RetResult anchorLog(@RequestParam("userId") String userId) {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		IdentityInfo info = null;
		
		try {
			if (StringUtils.isNotBlank(userId)) {
				info = anchorService.queryIdentityByUserId(userId);
			}
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("主播认证信息异常:code={},message={},userId={},e={}", code, message, userId, e);
		}
		return RetResult.setRetDate(code, message, info);
	}
	
	@ApiOperation(value = "主播认证接口", notes = "主播认证")
	@ApiImplicitParams({ @ApiImplicitParam(name = "anchor", value = "详细实体AnchorVO", required = true, dataType = "AnchorVO")})
	@RequestMapping(value = "/editAnchor", method = RequestMethod.POST)
	public @ResponseBody RetResult editIdentity(@JsonParam AnchorVO anchor) {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;

		boolean flag = false;
		try {
			if (null != anchor) {
				flag = anchorService.editIdentity(anchor);
			}
		} catch (ImplException e) {
			code = e.getCode();
			message = e.getMessage();
			GwsLogger.error("主播认证异常:code={},message={},vo={},e={}", code,
					message, ToStringBuilder.reflectionToString(anchor), e);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("主播认证异常:code={},message={},vo={},e={}", code,
					message, ToStringBuilder.reflectionToString(anchor), e);
		}
		return RetResult.setRetDate(code, message, flag);
	}
	
	@ApiOperation(value = "取消主播身份", notes = "取消主播身份,为普通用户")
	@ApiImplicitParams({ @ApiImplicitParam(name="userId", value="用户ID", required=true, dataType="String")})
	@RequestMapping(value = "/cancelAnchor", method = RequestMethod.POST)
	public @ResponseBody RetResult cancelAnchor(@RequestParam("userId") String userId) {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		boolean flag = false;
		
		if (StringUtils.isBlank(userId)) {
			return RetResult.setRetDate(CommConstant.GWSCOD0001, "参数不存在!", userId);
		}
		try {
			flag = anchorService.cancelAnchor(userId);
		} catch (ImplException e) {
			code = e.getCode();
			message = e.getMessage();
			GwsLogger.error("主播认证异常:code={},message={},userId={},e={}", code, message, userId, e);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("主播认证异常:code={},message={},userId={},e={}", code, message, userId, e);
		}
		return RetResult.setRetDate(code, message, flag);
	}
	
	@ApiOperation(value = "主播兑换比率接口", notes = "主播认证")
	@ApiImplicitParams({ @ApiImplicitParam(name="userId", value="用户ID", required=true, dataType="String"),
		@ApiImplicitParam(name="ratio", value="兑换比率", required=true, dataType="String")})
	@RequestMapping(value = "/editAnchorRatio", method = RequestMethod.POST)
	public @ResponseBody RetResult editAnchorRatio(@RequestParam("userId") String userId, @RequestParam("ratio") String ratio) {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;

		boolean flag = false;
		try {
			if (StringUtils.isNotBlank(userId)) {
				flag = anchorService.editIdentity(userId, ratio);
			}
		} catch (ImplException e) {
			code = e.getCode();
			message = e.getMessage();
			GwsLogger.error("主播兑换比率异常:code={},message={},userId={},ratio={},e={}", code, message, userId, ratio, e);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("主播兑换比率异常:code={},message={},userId={},ratio={},e={}", code, message, userId, ratio, e);
		}
		return RetResult.setRetDate(code, message, flag);
	}
	
	@ApiOperation(value = "主播开播权限接口", notes = "主播是否开播/禁播")
	@ApiImplicitParams({ @ApiImplicitParam(name="userId", value="用户ID", required=true, dataType="String"),
		@ApiImplicitParam(name="isLive", value="是否开播", required=true, dataType="String")})
	@RequestMapping(value = "/editAnchorLive", method = RequestMethod.POST)
	public @ResponseBody RetResult editAnchorLive(@RequestParam("userId") String userId, @RequestParam("isLive") Short isLive) {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;

		boolean flag = false;
		try {
			if (StringUtils.isNotBlank(userId)) {
				flag = anchorService.editIdentity(userId, isLive);
			}
		} catch (ImplException e) {
			code = e.getCode();
			message = e.getMessage();
			GwsLogger.error("主播开播权限异常:code={},message={},userId={},isLive={},e={}", code, message, userId, isLive, e);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("主播开播权限异常:code={},message={},userId={},isLive={},e={}", code, message, userId, isLive, e);
		}
		return RetResult.setRetDate(code, message, flag);
	}
	
	@ApiOperation(value = "主播审核接口", notes = "主播认证审核")
	@ApiImplicitParams({ @ApiImplicitParam(name="userId", value="用户ID", required=true, dataType="String"),
		@ApiImplicitParam(name="type", value="用户类型", required=true, dataType="String")})
	@RequestMapping(value = "/editUser", method = RequestMethod.POST)
	public @ResponseBody RetResult editUser(@RequestParam("userId") String userId, @RequestParam("type") String type) {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;

		boolean flag = false;
		try {
			if (StringUtils.isNotBlank(userId)) {
				flag = anchorService.editUser(userId, Integer.valueOf(type));
			}
		} catch (ImplException e) {
			code = e.getCode();
			message = e.getMessage();
			GwsLogger.error("主播认证审核异常:code={},message={},userId={},type={},e={}", code, message, userId, type, e);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("主播认证审核异常:code={},message={},userId={},type={},e={}", code, message, userId, type, e);
		}
		return RetResult.setRetDate(code, message, flag);
	}
	
	@ApiOperation(value = "提现接口", notes = "卡路里提现RMB")
	@ApiImplicitParams({ @ApiImplicitParam(name="userId", value="用户ID", required=true, dataType="String"),
		@ApiImplicitParam(name="orderId", value="订单ID", required=false, dataType="String"),
		@ApiImplicitParam(name="status", value="订单状态", required=false, dataType="String"),
		@ApiImplicitParam(name="remark", value="订单备注", required=false, dataType="String")
	})
	@RequestMapping(value = "/extractCash", method = RequestMethod.POST)
	public @ResponseBody RetResult extractCash(@RequestParam("userId") String userId, String orderId, String status,
			String remark) {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		boolean flag = false;

		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", userId);
			map.put("orderId", orderId);
			map.put("status", status);
			map.put("remark", remark);

			flag = anchorService.extractCash(map);
		} catch (ImplException e) {
			code = e.getCode();
			message = e.getMessage();
			GwsLogger.error("卡路里提现确认异常:code={},message={},userId={},orderId={},status={},remark={},e={}", code, message, userId,
					orderId, status, remark, e);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("卡路里提现确认异常:code={},message={},userId={},orderId={},status={},remark={},e={}", code, message, userId,
					orderId, status, remark, e);
		}
		return RetResult.setRetDate(code, message, flag);
	}
	
	@ApiOperation(value = "提现记录操作接口", notes = "获取提现记录相关数据")
	@ApiImplicitParams({ @ApiImplicitParam(name="userId", value="用户ID", required=true, dataType="String")})
	@RequestMapping(value = "/extractCashLog", method = RequestMethod.POST)
	public @ResponseBody RetResult extractCashLog(@RequestParam("userId") String userId) {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		List<OrderInfo> oL = null;
		
		try {
			if (StringUtils.isNotBlank(userId)) {
				oL = anchorService.queryExtractCashLogByUserId(userId);
			}
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("获取提现记录异常:code={},message={},userId={},e={}", code, message, userId, e);
		}
		return RetResult.setRetDate(code, message, oL);
	}
	
}
