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
import com.opengroup.longmao.gwcommon.entity.po.TvIllegalReport;
import com.opengroup.longmao.gwcommon.enums.FeedBackDealEnum;
import com.opengroup.longmao.gwcommon.enums.IsDeleteEnum;
import com.opengroup.longmao.gwcommon.enums.ReportTypeEnum;
import com.opengroup.longmao.gwcommon.repository.master.TvIllegalReportRepositoryMaster;
import com.opengroup.longmao.gwcommon.repository.queryFilter.TvIllegalReportQueryFilter;
import com.opengroup.longmao.gwcommon.repository.slave.TvIllegalReportRepositorySlave;
import com.opengroup.longmao.gwcommon.service.AdminInfoService;
import com.opengroup.longmao.gwcommon.service.TvIllegalReportService;
import com.opengroup.longmao.gwcommon.tools.date.DateUtil;

/**
 * 【举报情况表】 Service接口实现
 *
 * @version 1.0
 * @author Hermit 2017年04月25日 上午11:08:03
 */
@Service
public class TvIllegalReportServiceImpl implements TvIllegalReportService {

	@Autowired
	private TvIllegalReportRepositoryMaster tvIllegalReportRepositoryMaster;

	@Autowired
	private TvIllegalReportRepositorySlave tvIllegalReportRepositorySlave;
	
	@Autowired
	private AdminInfoService adminInfoService;

	/**
	 * 【分页查询举报情况表】
	 * 
	 * @param tvIllegalReport
	 * @param pageNo
	 * @param pageSize
	 * @param sortField
	 * @return tvIllegalReport
	 * @version 1.0
	 * @author Hermit 2017年04月25日 上午11:08:03
	 */
	@Override
	public Page<TvIllegalReport> findTvIllegalReport(TvIllegalReport tvIllegalReport, Integer pageNo, Integer pageSize,
			String sortField) {
		// 组合查询语句
		TvIllegalReportQueryFilter query = new TvIllegalReportQueryFilter();
		if(null!=tvIllegalReport.getReportType()){
			query.setReportType(tvIllegalReport.getReportType());
		}
		if(null!=tvIllegalReport.getAnchorId()){
			query.setAnchorId(tvIllegalReport.getAnchorId());;
		}
		if(null!=tvIllegalReport.getDealStatus()){
			query.setDealStatus(tvIllegalReport.getDealStatus());;
		}
		if(null!=tvIllegalReport.getReportId()){
			query.setReportId(tvIllegalReport.getReportId());;
		}
		if(null!=tvIllegalReport.getUserId()){
			query.setUserId(tvIllegalReport.getUserId());;
		}
		query.setIsDelete(IsDeleteEnum.UN_DELETE.getVal());
		// 字段排序
		Sort sort = new Sort(Direction.DESC, sortField);
		// 分页
		Pageable page = new PageRequest(pageNo, pageSize, sort);
		// 查询分页数据
		Page<TvIllegalReport> pageList =tvIllegalReportRepositorySlave.findAll(query, page);
		for(TvIllegalReport tip:pageList.getContent()){
			tip.setCtimeStr(DateUtil.timestampToDates(tip.getCtime(), DateUtil.TIME_PATTON_DEFAULT));
			tip.setUtimeStr(DateUtil.timestampToDates(tip.getUtime(), DateUtil.TIME_PATTON_DEFAULT));
			if(null!=tip.getDealTime()){
				tip.setDealTimeStr(DateUtil.format(new Date(tip.getDealTime()), DateUtil.TIME_PATTON_DEFAULT));
			}
			if(null!=tip.getDealUser()&&tip.getDealUser()>0){
				AdminInfo info=adminInfoService.getAdminInfoById(tip.getDealUser());
				if(null!=info&&StringUtils.isNotEmpty(info.getUserAlias())){
					tip.setDealUserStr(info.getUserAlias());
				}
			}
			tip.setDealStatusStr(FeedBackDealEnum.getEnumByNumber(tip.getDealStatus()).getDesc());
			tip.setReportTypeStr(ReportTypeEnum.getEnumByNumber(tip.getReportType()).getDesc());
			
		}
		return pageList;
	}

	/**
	 * 【根据id查询举报情况表】
	 * 
	 * @param id
	 * @return tvIllegalReport
	 * @version 1.0
	 * @author Hermit 2017年04月25日 上午11:08:03
	 */
	@Override
	public TvIllegalReport findTvIllegalReport(Long id) {
		TvIllegalReport tvIllegalReport = null;
		if (StringUtils.isNotBlank(id.toString())) {
			tvIllegalReport = tvIllegalReportRepositorySlave.findOne(id);
			if(null!=tvIllegalReport.getDealUser()&&tvIllegalReport.getDealUser()>0){
				AdminInfo info=adminInfoService.getAdminInfoById(tvIllegalReport.getDealUser());
				if(null!=info&&StringUtils.isNotEmpty(info.getUserAlias())){
					tvIllegalReport.setDealUserStr(info.getUserAlias());
				}
			}
		} else {
			GwsLogger.info("id不存在");
		}
		return tvIllegalReport;
	}

	/**
	 * 【保存举报情况表】
	 * 
	 * @param tvIllegalReport
	 * @return tvIllegalReport
	 * @version 1.0
	 * @author Hermit 2017年04月25日 上午11:08:03
	 */
	@Override
	public TvIllegalReport saveTvIllegalReport(TvIllegalReport tvIllegalReport) {
		// 判断对象是否存在
		if (tvIllegalReport != null) {
			// id统一生成
			// Long id = idGlobalGenerator.getSeqId(TvIllegalReport.class);
			// tvIllegalReport.setId(id);
			tvIllegalReport = tvIllegalReportRepositoryMaster.save(tvIllegalReport);
			GwsLogger.info("举报情况表保存成功");
		} else {
			GwsLogger.info("举报情况表对象不存在，保存失败:tvIllegalReport={}", ToStringBuilder.reflectionToString(tvIllegalReport));
			return null;
		}
		return tvIllegalReport;
	}

	/**
	 * 【修改举报情况表】
	 * 
	 * @param tvIllegalReport
	 * @return tvIllegalReport
	 * @version 1.0
	 * @author Hermit 2017年04月25日 上午11:08:03
	 */
	@Override
	public TvIllegalReport updateTvIllegalReport(TvIllegalReport tvIllegalReport) {
		if (null!=tvIllegalReport.getReportId()) {
			// 先从库中查出该对象
			TvIllegalReport tvIllegalReportBean = tvIllegalReportRepositorySlave.findOne(Long.valueOf(tvIllegalReport.getReportId()));
			// 判断对象是否存在
			if (tvIllegalReportBean != null) {
				// 该处数据填充代码请自行补全....
				tvIllegalReportBean.setDealStatus(FeedBackDealEnum.DEAL.getVal());
				tvIllegalReportBean.setDealTime(DateUtil.currentSecond());
				tvIllegalReportBean.setDealUser(tvIllegalReport.getDealUser());
				tvIllegalReportBean.setDealRemark(tvIllegalReport.getDealRemark());
				tvIllegalReport = tvIllegalReportRepositoryMaster.save(tvIllegalReportBean);
				GwsLogger.info("举报情况表修改成功");
			} else {
				GwsLogger.info("举报情况表对象不存在，修改失败:tvIllegalReport={}",
						ToStringBuilder.reflectionToString(tvIllegalReport));
				return null;
			}
		} else {
			GwsLogger.error("举报情况表id不存在，修改失败:tvIllegalReport={}", ToStringBuilder.reflectionToString(tvIllegalReport));
			return null;
		}
		return tvIllegalReport;
	}

	/**
	 * 【根据id删除举报情况表】
	 * 
	 * @param id
	 * @return void
	 * @version 1.0
	 * @author Hermit 2017年04月25日 上午11:08:03
	 */
	@Override
	public void deleteTvIllegalReport(Long id) {
		// 先从库中查出该对象
		TvIllegalReport tvIllegalReport = tvIllegalReportRepositorySlave.findOne(id);
		// 判断对象是否存在
		if (tvIllegalReport != null) {
			// 将用户状态改为删除
			// tvIllegalReport.setIsDelete(IsDeleteEnum.YES.getVal());
			TvIllegalReport newTvIllegalReport = tvIllegalReportRepositoryMaster.save(tvIllegalReport);
			// 判断对象是否存在
			if (newTvIllegalReport != null) {
				GwsLogger.info("举报情况表删除成功");
			} else {
				GwsLogger.info("举报情况表删除失败:id={}", id);
			}
		} else {
			GwsLogger.info("举报情况表对象不存在:id={}", id);
		}
	}

}
