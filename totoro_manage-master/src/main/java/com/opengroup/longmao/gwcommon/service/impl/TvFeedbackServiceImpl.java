package com.opengroup.longmao.gwcommon.service.impl;

import java.util.Date;

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
import com.opengroup.longmao.gwcommon.entity.po.AdminInfo;
import com.opengroup.longmao.gwcommon.entity.po.TvFeedback;
import com.opengroup.longmao.gwcommon.enums.FeedBackDealEnum;
import com.opengroup.longmao.gwcommon.enums.FeedbackTypeEnum;
import com.opengroup.longmao.gwcommon.enums.IsDeleteEnum;
import com.opengroup.longmao.gwcommon.repository.master.TvFeedbackRepositoryMaster;
import com.opengroup.longmao.gwcommon.repository.queryFilter.TvFeedbackQueryFilter;
import com.opengroup.longmao.gwcommon.repository.slave.TvFeedbackRepositorySlave;
import com.opengroup.longmao.gwcommon.service.AdminInfoService;
import com.opengroup.longmao.gwcommon.service.TvFeedbackService;
import com.opengroup.longmao.gwcommon.tools.date.DateUtil;
/**
 * 【反馈意见表】 Service接口实现
 *
 * @version 1.0
 * @author Hermit 2017年04月25日 上午11:11:47
 */ 
@Service
public class TvFeedbackServiceImpl implements TvFeedbackService{

	@Autowired
	private TvFeedbackRepositoryMaster tvFeedbackRepositoryMaster;

	@Autowired
	private TvFeedbackRepositorySlave tvFeedbackRepositorySlave;
	
	@Autowired
	private AdminInfoService adminInfoService;

	/**
	* 【分页查询反馈意见表】
	* @param tvFeedback
	* @param pageNo
	* @param pageSize
	* @param sortField
	* @return tvFeedback
	* @version 1.0
	* @author Hermit 2017年04月25日 上午11:11:47
	*/ 
	@Override
	public Page<TvFeedback> findTvFeedback(TvFeedback tvFeedback,Integer pageNo, Integer pageSize, String sortField){
		// 组合查询语句
		TvFeedbackQueryFilter query = new TvFeedbackQueryFilter();
		//query.setIsId(tvFeedback.getId());
		if(null!=tvFeedback.getUserId()){
			query.setUserId(tvFeedback.getUserId());
		}
		if(null!=tvFeedback.getSystemType()){
			query.setSystemType(tvFeedback.getSystemType());
		}
		query.setIsDelete(IsDeleteEnum.UN_DELETE.getVal());
		//字段排序
		Sort sort = new Sort(Direction.DESC, sortField);
		// 分页
		Pageable page = new PageRequest(pageNo, pageSize, sort);
		// 查询分页数据
		Page<TvFeedback> pageList = tvFeedbackRepositorySlave.findAll(query, page);
		for(TvFeedback tfb:pageList.getContent()){
			tfb.setCtimeStr(DateUtil.timestampToDates(tfb.getCtime(), DateUtil.TIME_PATTON_DEFAULT));
			tfb.setUtimeStr(DateUtil.timestampToDates(tfb.getUtime(), DateUtil.TIME_PATTON_DEFAULT));
			if(null!=tfb.getDealTime()){
				tfb.setDealTimeStr(DateUtil.timestampToDates(tfb.getDealTime(), DateUtil.TIME_PATTON_DEFAULT));
			}
			if(null!=tfb.getDealUser()&&tfb.getDealUser()>0){
				AdminInfo info=adminInfoService.getAdminInfoById(tfb.getDealUser());
				if(null!=info&&StringUtils.isNotEmpty(info.getUserAlias())){
					tfb.setDealUserStr(info.getUserAlias());
				}
			}
			tfb.setFeedbackTypeStr(FeedbackTypeEnum.getEnumByNumber(tfb.getFeedbackType()).getDesc());
			tfb.setDealStatusStr(FeedBackDealEnum.getEnumByNumber(tfb.getDealStatus()).getDesc());
		}
		return pageList;
	}
	/**
	* 【根据id查询反馈意见表】
	* @param id
	* @return tvFeedback
	* @version 1.0
	* @author Hermit 2017年04月25日 上午11:11:47
	*/ 
	@Override
	public TvFeedback findTvFeedback(Long id){
	    TvFeedback tvFeedback = null;
		if(StringUtils.isNotBlank(id.toString())){
	      tvFeedback = tvFeedbackRepositorySlave.findOne(id);
	      if(null!=tvFeedback.getDealUser()&&tvFeedback.getDealUser()>0){
				AdminInfo info=adminInfoService.getAdminInfoById(tvFeedback.getDealUser());
				if(null!=info&&StringUtils.isNotEmpty(info.getUserAlias())){
					tvFeedback.setDealUserStr(info.getUserAlias());
				}
			}
		}else{
		  GwsLogger.info("id不存在");
		}
		return tvFeedback;
	}
	/**
	* 【保存反馈意见表】
	* @param tvFeedback
	* @return tvFeedback
	* @version 1.0
	* @author Hermit 2017年04月25日 上午11:11:47
	*/ 
	@Override
	public TvFeedback saveTvFeedback(TvFeedback tvFeedback){
		//判断对象是否存在
		if(tvFeedback!= null){
		   //id统一生成
		   //Long id = idGlobalGenerator.getSeqId(TvFeedback.class);
		   //tvFeedback.setId(id);
		   tvFeedback = tvFeedbackRepositoryMaster.save(tvFeedback);
		   GwsLogger.info("反馈意见表保存成功");
		}else{
			GwsLogger.info("反馈意见表对象不存在，保存失败:tvFeedback={}",ToStringBuilder.reflectionToString(tvFeedback));
		    return null;
		}
		return tvFeedback;
	}

	/**
	* 【修改反馈意见表】
	* @param tvFeedback
	* @return tvFeedback
	* @version 1.0
	* @author Hermit 2017年04月25日 上午11:11:47
	*/ 
	@Override
	public TvFeedback updateTvFeedback(TvFeedback tvFeedback){
		if(null!=tvFeedback.getFeedbackId()){
		    //先从库中查出该对象
	        TvFeedback tvFeedbackBean = tvFeedbackRepositorySlave.findOne(Long.valueOf(tvFeedback.getFeedbackId()));
		    //判断对象是否存在
			if(tvFeedbackBean!= null){
		       //该处数据填充代码请自行补全....
			   tvFeedbackBean.setDealStatus(FeedBackDealEnum.DEAL.getVal());
			   tvFeedbackBean.setDealTime(DateUtil.currentSecond());
			   tvFeedbackBean.setDealUser(tvFeedback.getDealUser());
			   tvFeedbackBean.setDealRemark(tvFeedback.getDealRemark());
			   tvFeedback = tvFeedbackRepositoryMaster.save(tvFeedbackBean);
			   GwsLogger.info("反馈意见表修改成功");
			}else{
			    GwsLogger.info("反馈意见表对象不存在，修改失败:tvFeedback={}",ToStringBuilder.reflectionToString(tvFeedback));
		        return null;
			}
		}else{
			 GwsLogger.error("反馈意见表id不存在，修改失败:tvFeedback={}",ToStringBuilder.reflectionToString(tvFeedback));
		     return null;
		}
		return tvFeedback;
	}

	/**
	 * 【根据id删除反馈意见表】
	 * @param id
	 * @return void
	 * @version 1.0
	 * @author Hermit 2017年04月25日 上午11:11:47
	 */ 
	@Override
	public void deleteTvFeedback(Long id){
		//先从库中查出该对象
		TvFeedback tvFeedback = tvFeedbackRepositorySlave.findOne(id);
		//判断对象是否存在
		if(tvFeedback!=null){
			//将用户状态改为删除
			//tvFeedback.setIsDelete(IsDeleteEnum.YES.getVal());
			TvFeedback newTvFeedback = tvFeedbackRepositoryMaster.save(tvFeedback);
			//判断对象是否存在
			if(newTvFeedback!=null){
				GwsLogger.info("反馈意见表删除成功");
			}else{
				GwsLogger.info("反馈意见表删除失败:id={}",id);
			}
		}else{
			GwsLogger.info("反馈意见表对象不存在:id={}",id);
		}
	}

}

