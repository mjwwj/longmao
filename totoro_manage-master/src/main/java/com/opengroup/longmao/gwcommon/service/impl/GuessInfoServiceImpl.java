package com.opengroup.longmao.gwcommon.service.impl;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.opengroup.longmao.gwcommon.entity.po.GuessInfo;
import com.opengroup.longmao.gwcommon.enums.GuessFinalResultEnum;
import com.opengroup.longmao.gwcommon.enums.GuessStatusEnum;
import com.opengroup.longmao.gwcommon.enums.IsOrNotEnum;
import com.opengroup.longmao.gwcommon.enums.RobStatusEnum;
import com.opengroup.longmao.gwcommon.repository.queryFilter.GuessInfoQueryFilter;
import com.opengroup.longmao.gwcommon.repository.slave.GuessInfoRepositorySlave;
import com.opengroup.longmao.gwcommon.service.GuessInfoService;
import com.opengroup.longmao.gwcommon.tools.date.DateUtil;

/**
 * service接口实现
 * @author Administrator
 *
 */
@Service
public class GuessInfoServiceImpl implements GuessInfoService {

	@Autowired
	private GuessInfoRepositorySlave guessInfoRepositorySlave;

	/**
	 * 根据id查询，返回对象
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.helian.services.GuessService#getGuessById(java.lang.String)
	 */
	@Override
	public GuessInfo getGuessById(Long guessId) {
		GuessInfo guessInfo = guessInfoRepositorySlave.findOne(guessId);
		//竞猜状态
		if (guessInfo.getStatus() != null && guessInfo.getStatus() >= 0) {
			GuessStatusEnum guessStatusEnum = GuessStatusEnum.getEnumByNumber(guessInfo.getStatus().shortValue());
			guessInfo.setStatusNameStr(guessStatusEnum.getDesc());
		}
		if (guessInfo.getFinalResult() != null && guessInfo.getFinalResult() >= 0) {
			GuessFinalResultEnum finalResultEnum = GuessFinalResultEnum.getEnumByNumber(guessInfo.getFinalResult().shortValue());
			guessInfo.setFinalResultStr(finalResultEnum.getDesc());
		}
		if (guessInfo.getCtime() != null) {
			guessInfo.setGmtCreateStr(DateUtil.timestampToDates(guessInfo.getCtime(),DateUtil.TIME_PATTON_DEFAULT));
		}
		if (guessInfo.getUtime() != null ) {
		   guessInfo.setGmtModifiedStr(DateUtil.timestampToDates(guessInfo.getUtime(),DateUtil.TIME_PATTON_DEFAULT));
		}
		//竞猜封盘时间
		if (guessInfo.getStopBetTime() != null ) {
		   guessInfo.setStopBetTimeStr(DateUtil.timestampToDates(guessInfo.getStopBetTime(), DateUtil.TIME_PATTON_DEFAULT));
		}
		//抢庄结束时间
		if (guessInfo.getStopRobTime() != null ) {
			   guessInfo.setStopRobTimeStr(DateUtil.timestampToDates(guessInfo.getStopRobTime(), DateUtil.TIME_PATTON_DEFAULT));
		}
		//是否自定义
		if (guessInfo.getIsCustom() != null && guessInfo.getIsCustom()>0) {
			   guessInfo.setIsCustomStr(IsOrNotEnum.getEnumByNumber(guessInfo.getIsCustom()).getDesc());
		}
		//是否抢庄
		if (guessInfo.getIsRob() != null && guessInfo.getIsRob()>0) {
			   guessInfo.setIsRobStr(IsOrNotEnum.getEnumByNumber(guessInfo.getIsRob()).getDesc());
		}
		//抢庄状态
		if (guessInfo.getRobStatus()!= null && guessInfo.getRobStatus()>=0) {
			   guessInfo.setRobStatusStr(RobStatusEnum.getEnumByNumber(guessInfo.getRobStatus()).getDesc());
		}
		return guessInfo;
	}

	/**
	 * 通过条件批量查询，返回泛型List
	 */
	@Override
	public Page<GuessInfo> findAllGuess(GuessInfo guessInfo, Integer pageNo, Integer pageSize, String sortField) {
		// 排序
		Sort sort = new Sort(Direction.ASC, sortField);
		// 分页
		Pageable page = new PageRequest(pageNo, pageSize, sort);
		// 组合查询语句
		GuessInfoQueryFilter query = new GuessInfoQueryFilter();
		if (null != guessInfo) {
			if (null != guessInfo.getStatus() && guessInfo.getStatus()>= 0) {
				query.setStatus(guessInfo.getStatus());
			}
			if (null != guessInfo.getAnchorId()&& guessInfo.getAnchorId()>= 0) {
				query.setAnchorId(guessInfo.getAnchorId());
			}
			if (null != guessInfo.getGuessId()&& guessInfo.getGuessId()>= 0) {
				query.setGuessId(guessInfo.getGuessId());
			}
			if (null != guessInfo.getRobStatus() && guessInfo.getRobStatus()>= 0) {
				query.setRobStatus(guessInfo.getRobStatus());
			}
			if (null != guessInfo.getFinalResult() && guessInfo.getFinalResult()>= 0) {
				query.setFinalResult(guessInfo.getFinalResult());
			}
		}
		// 查询分页数据
		Page<GuessInfo> pageList = guessInfoRepositorySlave.findAll(query, page);
		for (GuessInfo gi : pageList.getContent()) {
			//状态
			if (gi.getStatus() != null && gi.getStatus() >= 0) {
				GuessStatusEnum guessStatusEnum = GuessStatusEnum.getEnumByNumber(gi.getStatus().shortValue());
				gi.setStatusNameStr(guessStatusEnum.getDesc());
			}
			if (gi.getFinalResult() != null && gi.getFinalResult() >= 0) {
				GuessFinalResultEnum finalResultEnum = GuessFinalResultEnum.getEnumByNumber(gi.getFinalResult().shortValue());
				gi.setFinalResultStr(finalResultEnum.getDesc());
			}
			if (gi.getCtime() != null) {
				gi.setGmtCreateStr(DateUtil.timestampToDates(gi.getCtime(),DateUtil.TIME_PATTON_DEFAULT));
			}
			if (gi.getUtime() != null ) {
			   gi.setGmtModifiedStr(DateUtil.timestampToDates(gi.getUtime(),DateUtil.TIME_PATTON_DEFAULT));
			}
			//竞猜封盘时间
			if (gi.getStopBetTime() != null ) {
			   gi.setStopBetTimeStr(DateUtil.timestampToDates(gi.getStopBetTime(), DateUtil.TIME_PATTON_DEFAULT));
			}
			//抢庄结束时间
			if (gi.getStopRobTime() != null ) {
				gi.setStopRobTimeStr(DateUtil.timestampToDates(gi.getStopRobTime(), DateUtil.TIME_PATTON_DEFAULT));
			}
			//是否自定义
			if (gi.getIsCustom() != null && gi.getIsCustom()>0) {
				   gi.setIsCustomStr(IsOrNotEnum.getEnumByNumber(gi.getIsCustom()).getDesc());
			}
			//是否抢庄
			if (gi.getIsRob() != null && gi.getIsRob()>0) {
				   gi.setIsRobStr(IsOrNotEnum.getEnumByNumber(gi.getIsRob()).getDesc());
			}
			//抢庄状态
			if (gi.getRobStatus()!= null && gi.getRobStatus()>=0) {
				   gi.setRobStatusStr(RobStatusEnum.getEnumByNumber(gi.getRobStatus()).getDesc());
			}
		}
		return pageList;
	}

	/**
	 * 审核
	 * @throws ParseException 
	 */
	@Override
	public GuessInfo updateGuessStatus(Long guessId, Short status) {
		GuessInfo guessInfo = guessInfoRepositorySlave.findOne(guessId);
		GuessInfo newGuess = null;
		if (null != guessInfo) {
			guessInfo.setStatus(status);
			long currentTime = DateUtil.currentSecond();
			Integer disTime = guessInfo.getDisTime();
			Integer stopBetTime = (int)currentTime + disTime;
		    
		    guessInfo.setStopBetTime(stopBetTime);

		    guessInfo.setUtime(DateUtil.currentSecond());
			
			newGuess = guessInfoRepositorySlave.save(guessInfo);
		}
		return newGuess;
	}

	@Override
	public List<GuessInfo> guessExprot(GuessInfo guessInfo, String sortField) {
		// 排序
				Sort sort = new Sort(Direction.ASC, sortField);
				// 组合查询语句
				GuessInfoQueryFilter query = new GuessInfoQueryFilter();
				if (null != guessInfo) {
					if (null != guessInfo.getStatus() && guessInfo.getStatus()>= 0) {
						query.setStatus(guessInfo.getStatus());
					}
					if (null != guessInfo.getAnchorId()&& guessInfo.getAnchorId()>= 0) {
						query.setAnchorId(guessInfo.getAnchorId());
					}
					if (null != guessInfo.getGuessId()&& guessInfo.getGuessId()>= 0) {
						query.setGuessId(guessInfo.getGuessId());
					}
					if (null != guessInfo.getRobStatus() && guessInfo.getRobStatus()>= 0) {
						query.setRobStatus(guessInfo.getRobStatus());
					}
					if (null != guessInfo.getFinalResult() && guessInfo.getFinalResult()>= 0) {
						query.setFinalResult(guessInfo.getFinalResult());
					}
				}
				// 查询分页数据
				List<GuessInfo> pageList = guessInfoRepositorySlave.findAll(query, sort);
				for (GuessInfo gi : pageList) {
					//状态
					if (gi.getStatus() != null && gi.getStatus() >= 0) {
						GuessStatusEnum guessStatusEnum = GuessStatusEnum.getEnumByNumber(gi.getStatus().shortValue());
						gi.setStatusNameStr(guessStatusEnum.getDesc());
					}
					if (gi.getFinalResult() != null && gi.getFinalResult() >= 0) {
						GuessFinalResultEnum finalResultEnum = GuessFinalResultEnum.getEnumByNumber(gi.getFinalResult().shortValue());
						gi.setFinalResultStr(finalResultEnum.getDesc());
					}
					if (gi.getCtime() != null) {
						gi.setGmtCreateStr(DateUtil.timestampToDates(gi.getCtime(),DateUtil.TIME_PATTON_DEFAULT));
					}
					if (gi.getUtime() != null ) {
					   gi.setGmtModifiedStr(DateUtil.timestampToDates(gi.getUtime(),DateUtil.TIME_PATTON_DEFAULT));
					}
					//竞猜封盘时间
					if (gi.getStopBetTime() != null ) {
					   gi.setStopBetTimeStr(DateUtil.timestampToDates(gi.getStopBetTime(), DateUtil.TIME_PATTON_DEFAULT));
					}
					//抢庄结束时间
					if (gi.getStopRobTime() != null ) {
						gi.setStopRobTimeStr(DateUtil.timestampToDates(gi.getStopRobTime(), DateUtil.TIME_PATTON_DEFAULT));
					}
					//是否自定义
					if (gi.getIsCustom() != null && gi.getIsCustom()>0) {
						   gi.setIsCustomStr(IsOrNotEnum.getEnumByNumber(gi.getIsCustom()).getDesc());
					}
					//是否抢庄
					if (gi.getIsRob() != null && gi.getIsRob()>0) {
						   gi.setIsRobStr(IsOrNotEnum.getEnumByNumber(gi.getIsRob()).getDesc());
					}
					//抢庄状态
					if (gi.getRobStatus()!= null && gi.getRobStatus()>=0) {
						   gi.setRobStatusStr(RobStatusEnum.getEnumByNumber(gi.getRobStatus()).getDesc());
					}
				}
				return pageList;
	}

}
