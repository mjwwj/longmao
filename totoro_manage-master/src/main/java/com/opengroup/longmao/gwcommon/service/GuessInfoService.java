package com.opengroup.longmao.gwcommon.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.opengroup.longmao.gwcommon.entity.po.GuessInfo;

/**
 * 
 * service接口
 *
 * @version 
 * @author zengjq 2016年10月28日 上午11:50:55
 *
 */
public interface GuessInfoService {

	/**
	 * 
	 * 【通过条件批量查询用户，返回泛型List】
	 * 
	 * @author Hermit 2017年4月28日
	 * @param guessInfo
	 * @param pageNo
	 * @param pageSize
	 * @param sortField
	 * @return
	 */
	Page<GuessInfo> findAllGuess(GuessInfo guessInfo, Integer pageNo,Integer pageSize,String sortField);
	
	/**
	 * 
	 * 【根据id查询，返回竞猜信息对象】
	 * 
	 * @author Hermit 2017年4月28日
	 * @param guessId
	 * @return
	 */
	GuessInfo getGuessById(Long guessId);
	
	/**
	 * 
	 * 【修改竞猜信息】
	 * 
	 * @author Hermit 2017年4月28日
	 * @param guessId
	 * @param status
	 * @return
	 */
	GuessInfo updateGuessStatus(Long guessId, Short status);
	
	/**
	 * 【导出竞猜信息】
	 * @param guessInfo
	 * @param sortField
	 * @return
	 */
	List<GuessInfo> guessExprot(GuessInfo guessInfo,String sortField);
}
