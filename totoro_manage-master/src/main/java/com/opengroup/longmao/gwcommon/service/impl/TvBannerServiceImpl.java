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
import com.opengroup.longmao.gwcommon.configuration.redis.cache.IdGlobalGenerator;
import com.opengroup.longmao.gwcommon.entity.po.TvBanner;
import com.opengroup.longmao.gwcommon.entity.vo.TvBannerVO;
import com.opengroup.longmao.gwcommon.enums.IsDeleteEnum;
import com.opengroup.longmao.gwcommon.enums.IsOrNotEnum;
import com.opengroup.longmao.gwcommon.repository.master.TvBannerRepositoryMaster;
import com.opengroup.longmao.gwcommon.repository.queryFilter.TvBannerQueryFilter;
import com.opengroup.longmao.gwcommon.repository.slave.TvBannerRepositorySlave;
import com.opengroup.longmao.gwcommon.service.TvBannerService;
import com.opengroup.longmao.gwcommon.tools.date.DateUtil;

/**
 * 【banner配置表】 Service接口实现
 *
 * @version 1.0
 * @author Hermit 2017年04月26日 上午11:11:17
 */
@Service
public class TvBannerServiceImpl implements TvBannerService {

	@Autowired
	private TvBannerRepositoryMaster tvBannerRepositoryMaster;

	@Autowired
	private TvBannerRepositorySlave tvBannerRepositorySlave;

	@Autowired
	private IdGlobalGenerator idGlobalGenerator;

	/**
	 * 【分页查询banner配置表】
	 * 
	 * @param tvBanner
	 * @param pageNo
	 * @param pageSize
	 * @param sortField
	 * @return tvBanner
	 * @version 1.0
	 * @author Hermit 2017年04月26日 上午11:11:17
	 */
	@Override
	public Page<TvBanner> findTvBanner(TvBanner tvBanner, Integer pageNo, Integer pageSize, String sortField) {
		// 组合查询语句
		TvBannerQueryFilter query = new TvBannerQueryFilter();
		// query.setIsId(tvBanner.getId());
		if(StringUtils.isNotBlank(tvBanner.getBannerName())){
			query.setBannerName(tvBanner.getBannerName());
		}
		if(null!=tvBanner.getBannerStatus()){
			query.setBannerStatus(tvBanner.getBannerStatus());
		}
		query.setIsDelete(IsDeleteEnum.UN_DELETE.getVal());
		// 字段排序
		Sort sort = new Sort(Direction.DESC, sortField);
		// 分页
		Pageable page = new PageRequest(pageNo, pageSize, sort);
		// 查询分页数据
		Page<TvBanner> pageList = tvBannerRepositorySlave.findAll(query, page);
		for (TvBanner tb : pageList.getContent()) {
			if (tb.getDownTime() != null) {
				tvBanner.setDownTimeStr(DateUtil.timestampToDates(tb.getDownTime(), DateUtil.TIME_PATTON_DEFAULT));
			}
			if (tb.getUpTime() != null) {
				tvBanner.setUpTimeStr(DateUtil.timestampToDates(tb.getUpTime(), DateUtil.TIME_PATTON_DEFAULT));
			}
			if (tb.getBannerStatus() != null) {
				tvBanner.setIsEnableStr(IsOrNotEnum.getEnumByNumber(tb.getBannerStatus()).getDesc());
			}
		}
		return pageList;
	}

	/**
	 * 【根据id查询banner配置表】
	 * 
	 * @param id
	 * @return tvBanner
	 * @version 1.0
	 * @author Hermit 2017年04月26日 上午11:11:17
	 */
	@Override
	public TvBanner findTvBanner(Long id) {
		TvBanner tvBanner = null;
		if (StringUtils.isNotBlank(id.toString())) {
			tvBanner = tvBannerRepositorySlave.findOne(id);
			if (tvBanner.getDownTime() != null) {
				tvBanner.setDownTimeStr(DateUtil.timestampToDates(tvBanner.getDownTime(), DateUtil.TIME_PATTON_DEFAULT));
			}
			if (tvBanner.getUpTime() != null) {
				tvBanner.setUpTimeStr(DateUtil.timestampToDates(tvBanner.getUpTime(), DateUtil.TIME_PATTON_DEFAULT));
			}
			if (tvBanner.getBannerStatus() != null) {
				tvBanner.setIsEnableStr(IsOrNotEnum.getEnumByNumber(tvBanner.getBannerStatus()).getDesc());
			}
		} else {
			GwsLogger.info("id不存在");
		}
		return tvBanner;
	}

	/**
	 * 【保存banner配置表】
	 * 
	 * @param tvBanner
	 * @return tvBanner
	 * @version 1.0
	 * @author Hermit 2017年04月26日 上午11:11:17
	 */
	@Override
	public TvBanner saveTvBanner(TvBannerVO tvBannerVO) {
		TvBanner tvBanner = new TvBanner();
		// 判断对象是否存在
		if (tvBannerVO != null) {
			// id统一生成
			Long id = idGlobalGenerator.getSeqId(TvBanner.class);
			tvBanner.setBannerId(id);
			tvBanner.setCtime(DateUtil.currentSecond());
			tvBanner.setUtime(DateUtil.currentSecond());
			tvBanner.setBannerStatus(tvBannerVO.getBannerStatus());
			tvBanner.setIsDelete(IsDeleteEnum.UN_DELETE.getVal());
			// long num = tvBannerRepositorySlave.count();
			tvBanner.setSortNum(tvBannerVO.getSortNum());
			tvBanner.setBannerName(tvBannerVO.getBannerName());
			tvBanner.setBannerPicUrl(tvBannerVO.getBannerPicUrl());
			tvBanner.setBannerUrl(tvBannerVO.getBannerUrl());
			if (StringUtils.isNotEmpty(tvBannerVO.getDownTimeStr()))
				tvBanner.setDownTime(DateUtil.getTimeFormat(tvBannerVO.getDownTimeStr(), DateUtil.TIME_PATTON_DEFAULT));

			if (StringUtils.isNotEmpty(tvBannerVO.getUpTimeStr()))
				tvBanner.setUpTime(DateUtil.getTimeFormat(tvBannerVO.getUpTimeStr(), DateUtil.TIME_PATTON_DEFAULT));
			tvBanner = tvBannerRepositoryMaster.save(tvBanner);
			GwsLogger.info("banner配置表保存成功");
		} else {
			GwsLogger.info("banner配置表对象不存在，保存失败:tvBanner={}", ToStringBuilder.reflectionToString(tvBanner));
			return null;
		}
		return tvBanner;
	}

	/**
	 * 【修改banner配置表】
	 * 
	 * @param tvBanner
	 * @return tvBanner
	 * @version 1.0
	 * @author Hermit 2017年04月26日 上午11:11:17
	 */
	@Override
	public TvBanner updateTvBanner(TvBanner tvBanner) {
		if (null != tvBanner.getBannerId()) {
			// 先从库中查出该对象
			TvBanner tvBannerBean = tvBannerRepositorySlave.findOne(tvBanner.getBannerId());
			// 判断对象是否存在
			if (tvBannerBean != null) {
				tvBannerBean.setBannerName(tvBanner.getBannerName());
				tvBannerBean.setBannerPicUrl(tvBanner.getBannerPicUrl());
				tvBannerBean.setBannerUrl(tvBanner.getBannerUrl());
				tvBannerBean.setBannerStatus(tvBanner.getBannerStatus());
				if (StringUtils.isNotEmpty(tvBanner.getDownTimeStr()))
					tvBannerBean.setDownTime(
							DateUtil.getTimeFormat(tvBanner.getDownTimeStr(), DateUtil.TIME_PATTON_DEFAULT));

				if (StringUtils.isNotEmpty(tvBanner.getUpTimeStr()))
					tvBannerBean
							.setUpTime(DateUtil.getTimeFormat(tvBanner.getUpTimeStr(), DateUtil.TIME_PATTON_DEFAULT));
				tvBannerBean.setUpTime(DateUtil.currentSecond());
				tvBanner = tvBannerRepositoryMaster.save(tvBannerBean);
				GwsLogger.info("banner配置表修改成功");
			} else {
				GwsLogger.info("banner配置表对象不存在，修改失败:tvBanner={}", ToStringBuilder.reflectionToString(tvBanner));
				return null;
			}
		} else {
			GwsLogger.error("banner配置表id不存在，修改失败:tvBanner={}", ToStringBuilder.reflectionToString(tvBanner));
			return null;
		}
		return tvBanner;
	}

	/**
	 * 【根据id删除banner配置表】
	 * 
	 * @param id
	 * @return void
	 * @version 1.0
	 * @author Hermit 2017年04月26日 上午11:11:17
	 */
	@Override
	public void deleteTvBanner(Long id) {
		// 先从库中查出该对象
		TvBanner tvBanner = tvBannerRepositorySlave.findOne(id);
		// 判断对象是否存在
		if (tvBanner != null) {
			// 将用户状态改为删除
			tvBanner.setIsDelete(IsDeleteEnum.DELETE.getVal());
			tvBanner.setUtime(DateUtil.currentSecond());
			TvBanner newTvBanner = tvBannerRepositoryMaster.save(tvBanner);
			// 判断对象是否存在
			if (newTvBanner != null) {
				GwsLogger.info("banner配置表删除成功");
			} else {
				GwsLogger.info("banner配置表删除失败:id={}", id);
			}
		} else {
			GwsLogger.info("banner配置表对象不存在:id={}", id);
		}
	}

}
