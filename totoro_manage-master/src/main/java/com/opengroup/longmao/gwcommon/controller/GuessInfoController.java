package com.opengroup.longmao.gwcommon.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opengroup.longmao.gwcommon.configuration.annotation.JsonParam;
import com.opengroup.longmao.gwcommon.configuration.log.GwsLogger;
import com.opengroup.longmao.gwcommon.entity.po.GuessInfo;
import com.opengroup.longmao.gwcommon.enums.GuessStatusEnum;
import com.opengroup.longmao.gwcommon.service.GuessInfoService;
import com.opengroup.longmao.gwcommon.tools.ExportExcelUtil;
import com.opengroup.longmao.gwcommon.tools.result.CommConstant;
import com.opengroup.longmao.gwcommon.tools.result.RetResult;

/**
 * Controller
 * @author Administrator
 *
 */
@Controller
public class GuessInfoController {

	@Autowired
	private GuessInfoService guessInfoService;

	/**
	 * 进入竞猜管理页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/guessInfo")
	public String guessInfo() {
		GwsLogger.info("进入竞猜管理页面");
		return "guess/list";
	}

	/**
	 * 查询所有
	 * @param guessVo
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/findAllGuess")
	public @ResponseBody RetResult findAllGuess(@JsonParam GuessInfo guessInfo, Integer pageNo,Integer pageSize) {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;

		GwsLogger.info("查询所有开始:code={},message={},guessInfo={},pageNo={},pageSize={}", code, message,ToStringBuilder.reflectionToString(guessInfo), pageNo, pageSize);
		// 查询所有未删除的用户
		Page<GuessInfo> guess = null;
		try {
			guess = guessInfoService.findAllGuess(guessInfo, pageNo - 1, pageSize, "status");
			GwsLogger.info("查询所有:size={}", guess.getContent().size());
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("查询所有信息异常:code={},message={},e={}", code, message, e);
		}
		GwsLogger.info("查询所有结束,code={},message={}", code, message);
		return RetResult.setRetDate(code, message, guess);
	}

	/**
     * 通过id查询对象
     * 
     * @param menuId
     * @param model
     * @return
     */
    @RequestMapping(value = "/getGuessById")
    public @ResponseBody RetResult getGuessById(Long guessId) {
        String code = CommConstant.GWSCOD0000;
        String message = CommConstant.GWSMSG0000;
         
        GwsLogger.info("通过guessId查询对象开始:guessId={}",guessId);
        GuessInfo g = null;
        try{
            if (guessId!=null&&guessId>0) {
            	g = guessInfoService.getGuessById(guessId);
            }
        }catch(Exception e){
            code = CommConstant.GWSCOD0001;
            message = CommConstant.GWSMSG0001;
            GwsLogger.error("通过id查询对象异常:code={},message={},e={}",code,message,e);
        }
        GwsLogger.info("通过id查询对象结束,code={},message={}",code,message);
        return RetResult.setRetDate(code, message, g);
    }
	

    /**
     * 
     * 【修改竞猜信息】
     * 
     * @author Hermit 2017年4月28日
     * @param guessId
     * @return
     */
    @RequestMapping(value = "/updateGuessStatus")
    public @ResponseBody RetResult updateGuessStatus(Long guessId) {
         
        String code = CommConstant.GWSCOD0000;
        String message = CommConstant.GWSMSG0000;
        GwsLogger.info("修改保存信息开始");
         
        GuessInfo guess = null;
        try{
        	guess = guessInfoService.updateGuessStatus(guessId, GuessStatusEnum.ONE.getVal());
            if(null==guess){
                code = CommConstant.GWSCOD0006;
                message = CommConstant.GWSMSG0006;
                GwsLogger.info("已存在:code={},message={}",code,message);
            }
        }catch(Exception e){
            code = CommConstant.GWSCOD0001;
            message = CommConstant.GWSMSG0001;
            GwsLogger.error("修改保存信息异常:code={},message={},e={}",code,message,e);
        }
        GwsLogger.info("修改保存信息结束:code={},message={},roleMenu={}",code,message,ToStringBuilder.reflectionToString(guess));
        return RetResult.setRetDate(code, message, guess);
    }
    
    
    /**
	 * 
	 * 	竞猜导出
	 * 
	 * @author Hermit 2016年5月17日
	 */
	@RequestMapping(value = "/guessExprot")
	public  @ResponseBody void guessExprot(@JsonParam GuessInfo guessInfo,HttpServletResponse response){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("提现导出:code={},message={}，guessInfo={}",code,message,ToStringBuilder.reflectionToString(guessInfo));
		try {
			List<GuessInfo> data = guessInfoService.guessExprot(guessInfo,"ctime");
			List<String[]> stringList =  new ArrayList<String[]>();
			for(GuessInfo gi : data){
				String[] str = {
					gi.getGuessId().toString(),
					gi.getAnchorId().toString(),
					gi.getGuessTitle(),
					gi.getGuessContent(),
					gi.getRobProfit().toString(),
					gi.getFinalRobUserId()==null?"":gi.getFinalRobUserId().toString(),
					gi.getPoolBean().toString(),
					gi.getStatusNameStr(),
					gi.getFinalResultStr(),
					gi.getGmtCreateStr()
				};
				stringList.add(str);
			}
			//表头
			String[] header = CommConstant.GUESS_CSV_HEADER;
            //报表名称
			String excelTitle = "竞猜导出统计";
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
}
