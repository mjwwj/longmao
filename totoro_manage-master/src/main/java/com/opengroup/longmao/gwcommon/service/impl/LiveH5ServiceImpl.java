package com.opengroup.longmao.gwcommon.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.opengroup.longmao.gwcommon.configuration.log.GwsLogger;
import com.opengroup.longmao.gwcommon.configuration.redis.cache.IdGlobalGenerator;
import com.opengroup.longmao.gwcommon.entity.po.LiveH5;
import com.opengroup.longmao.gwcommon.entity.vo.LiveH5VO;
import com.opengroup.longmao.gwcommon.enums.IsDeleteEnum;
import com.opengroup.longmao.gwcommon.enums.IsOrNotEnum;
import com.opengroup.longmao.gwcommon.repository.master.LiveH5RepositoryMaster;
import com.opengroup.longmao.gwcommon.repository.queryFilter.LiveH5QueryFilter;
import com.opengroup.longmao.gwcommon.repository.slave.LiveH5RepositorySlave;
import com.opengroup.longmao.gwcommon.service.LiveH5Service;
import com.opengroup.longmao.gwcommon.tools.date.DateUtil;

/**
 * 【直播间H5活动入口】 Service接口实现
 *
 * @version 1.0
 * @author Hermit 2017年05月12日 下午16:09:19
 */ 
@Service
public class LiveH5ServiceImpl implements LiveH5Service{

	@Autowired
	private LiveH5RepositoryMaster liveH5RepositoryMaster;

	@Autowired
	private LiveH5RepositorySlave liveH5RepositorySlave;

	@Autowired
	private IdGlobalGenerator idGlobalGenerator;

	/**
	* 【分页查询直播间H5活动入口】
	* @param liveH5
	* @param pageNo
	* @param pageSize
	* @param sortField
	* @return liveH5
	* @version 1.0
	* @author Hermit 2017年05月12日 下午16:09:19
	*/ 
	@Override
	public Page<LiveH5> findLiveH5(LiveH5 liveH5,Integer pageNo, Integer pageSize, String sortField){
		// 组合查询语句
		LiveH5QueryFilter query = new LiveH5QueryFilter();
		query.setIsDelete(IsDeleteEnum.UN_DELETE.getVal());
		if(liveH5!= null){
			if(liveH5.getId()!=null&&liveH5.getId()>0){
				query.setId(liveH5.getId());
			}
			if(StringUtils.isNotBlank(liveH5.getActiveName())){
				query.setActiveName(liveH5.getActiveName());
			}
			if(liveH5.getIsEnable()!=null){
				query.setIsEnable(liveH5.getIsEnable());
			}
		}
		
		//字段排序
		Sort sort = new Sort(Direction.DESC, sortField);
		// 分页
		Pageable page = new PageRequest(pageNo, pageSize, sort);
		// 查询分页数据
		Page<LiveH5> pageList = liveH5RepositorySlave.findAll(query, page);
		if(CollectionUtils.isNotEmpty( pageList.getContent())){
			for (LiveH5 h : pageList.getContent()) {
				if (h.getDownTime() != null){
					h.setDownTimeStr(DateUtil.timestampToDates(h.getDownTime(),DateUtil.TIME_PATTON_DEFAULT));
				}
				if (h.getUpTime() != null){
					h.setUpTimeStr(DateUtil.timestampToDates(h.getUpTime(), DateUtil.TIME_PATTON_DEFAULT));
				}
				if (h.getIsEnable() != null){
					h.setIsEnableStr(IsOrNotEnum.getEnumByNumber(h.getIsEnable()).getDesc());
				}
			}
		}
		return pageList;
	}
	
	/**
	* 【根据id查询直播间H5活动入口】
	* @param id
	* @return liveH5
	* @version 1.0
	* @author Hermit 2017年05月12日 下午16:09:19
	*/ 
	@Override
	public LiveH5 findLiveH5(Long id){
		LiveH5 liveH5 = liveH5RepositorySlave.findOne(id);
		if(liveH5 != null){
			if (liveH5.getDownTime() != null){
				liveH5.setDownTimeStr(DateUtil.timestampToDates(liveH5.getDownTime(),DateUtil.TIME_PATTON_DEFAULT));
	        }
			if (liveH5.getUpTime() != null){
				liveH5.setUpTimeStr(DateUtil.timestampToDates(liveH5.getUpTime(), DateUtil.TIME_PATTON_DEFAULT));
			}
			if (liveH5.getIsEnable() != null){
				liveH5.setIsEnableStr(IsOrNotEnum.getEnumByNumber(liveH5.getIsEnable()).getDesc());
			}
		}
		return liveH5;
	}
	
	/**
	* 【保存直播间H5活动入口】
	* @param liveH5VO
	* @return liveH5
	* @version 1.0
	* @author Hermit 2017年05月12日 下午16:09:19
	*/ 
	@Override
	public LiveH5 saveLiveH5(LiveH5VO liveH5VO){
		   LiveH5 liveH5 = new LiveH5();
	   if(liveH5VO!=null){
		   //id统一生成
		   Long id = idGlobalGenerator.getSeqId(LiveH5.class);
		   liveH5.setId(id);
		   liveH5.setUtime(DateUtil.currentSecond());
		   liveH5.setCtime(DateUtil.currentSecond());
		   liveH5.setIsDelete(IsDeleteEnum.UN_DELETE.getVal());
		   liveH5.setIsEnable(liveH5VO.getIsEnable());
		   liveH5.setActiveLink(liveH5VO.getActiveLink());
		   liveH5.setActiveIco(liveH5VO.getActiveIco());
		   liveH5.setDirections(liveH5VO.getDirections());
		   liveH5.setActiveName(liveH5VO.getActiveName());
		   liveH5.setDownTime(DateUtil.getTimeFormat(liveH5VO.getDownTimeStr(), DateUtil.TIME_PATTON_DEFAULT));
		   liveH5.setUpTime(DateUtil.getTimeFormat(liveH5VO.getUpTimeStr(), DateUtil.TIME_PATTON_DEFAULT));
		   
		   liveH5 = liveH5RepositoryMaster.save(liveH5);
		   GwsLogger.info("直播间H5活动入口保存成功");
		}else{
			GwsLogger.info("直播间H5活动入口对象不存在，保存失败:liveH5={}",ToStringBuilder.reflectionToString(liveH5));
		    return null;
		}
		return liveH5;
	}

	/**
	* 【修改直播间H5活动入口】
	* @param liveH5
	* @return liveH5
	* @version 1.0
	* @author Hermit 2017年05月12日 下午16:09:19
	*/ 
	@Override
	public LiveH5 updateLiveH5(LiveH5 liveH5){
		if(liveH5!=null && liveH5.getId()!=null && liveH5.getId()>0){
		    //先从库中查出该对象
			LiveH5 liveH5Bean = liveH5RepositorySlave.findOne(liveH5.getId());
		    //判断对象是否存在
			if(liveH5Bean!= null){
				liveH5Bean.setIsEnable(liveH5.getIsEnable());
				liveH5Bean.setActiveLink(liveH5.getActiveLink());
				liveH5Bean.setActiveIco(liveH5.getActiveIco());
				liveH5Bean.setDirections(liveH5.getDirections());
				liveH5Bean.setActiveName(liveH5.getActiveName());
				liveH5Bean.setDownTime(DateUtil.getTimeFormat(liveH5.getDownTimeStr(), DateUtil.TIME_PATTON_DEFAULT));
				liveH5Bean.setUpTime(DateUtil.getTimeFormat(liveH5.getUpTimeStr(), DateUtil.TIME_PATTON_DEFAULT));
				liveH5Bean.setUtime(DateUtil.currentSecond());
				liveH5 = liveH5RepositoryMaster.save(liveH5Bean);
			   GwsLogger.info("直播间H5活动入口修改成功");
			}else{
			    GwsLogger.info("直播间H5活动入口对象不存在，修改失败:liveH5={}",ToStringBuilder.reflectionToString(liveH5));
		        return null;
			}
		}else{
			 GwsLogger.error("直播间H5活动入口id不存在，修改失败:liveH5={}",ToStringBuilder.reflectionToString(liveH5));
		     return null;
		}
		return liveH5;
	}

	/**
	 * 【根据id删除直播间H5活动入口】
	 * @param id
	 * @return void
	 * @version 1.0
	 * @author Hermit 2017年05月12日 下午16:09:19
	 */ 
	@Override
	public void deleteLiveH5(Long id){
		//先从库中查出该对象
		LiveH5 liveH5 = liveH5RepositorySlave.findOne(id);
		//判断对象是否存在
		if(liveH5!=null){
			//将用户状态改为删除
			liveH5.setIsDelete(IsDeleteEnum.DELETE.getVal());
			LiveH5 newLiveH5 = liveH5RepositoryMaster.save(liveH5);
			//判断对象是否存在
			if(newLiveH5!=null){
				GwsLogger.info("直播间H5活动入口删除成功");
			}else{
				GwsLogger.info("直播间H5活动入口删除失败:id={}",id);
			}
		}else{
			GwsLogger.info("直播间H5活动入口对象不存在:id={}",id);
		}
	}

}

