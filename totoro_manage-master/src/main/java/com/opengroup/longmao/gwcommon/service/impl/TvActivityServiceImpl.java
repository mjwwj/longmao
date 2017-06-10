package com.opengroup.longmao.gwcommon.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.opengroup.longmao.gwcommon.configuration.log.GwsLogger;
import com.opengroup.longmao.gwcommon.configuration.redis.cache.IdGlobalGenerator;
import com.opengroup.longmao.gwcommon.entity.po.TvActivity;
import com.opengroup.longmao.gwcommon.enums.IsDeleteEnum;
import com.opengroup.longmao.gwcommon.enums.StatusUpDownEnum;
import com.opengroup.longmao.gwcommon.repository.master.TvActivityRepositoryMaster;
import com.opengroup.longmao.gwcommon.repository.queryFilter.TvActivityQueryFilter;
import com.opengroup.longmao.gwcommon.repository.slave.TvActivityRepositorySlave;
import com.opengroup.longmao.gwcommon.service.TvActivityService;
import com.opengroup.longmao.gwcommon.tools.date.DateUtil;

/**
 * 【活动配置表】 Service接口实现
 *
 * @version 1.0
 * @author Hermit 2017年04月26日 上午11:11:50
 */
@Service
public class TvActivityServiceImpl implements TvActivityService {

	@Autowired
	private TvActivityRepositoryMaster tvActivityRepositoryMaster;

	@Autowired
	private TvActivityRepositorySlave tvActivityRepositorySlave;

	@Autowired
	private IdGlobalGenerator idGlobalGenerator;

	/**
	 * 【分页查询活动配置表】
	 * 
	 * @param tvActivity
	 * @param pageNo
	 * @param pageSize
	 * @param sortField
	 * @return tvActivity
	 * @version 1.0
	 * @author Hermit 2017年04月26日 上午11:11:50
	 */
	@Override
	public Page<TvActivity> findTvActivity(TvActivity tvActivity, Integer pageNo, Integer pageSize, String sortField) {
		// 组合查询语句
		TvActivityQueryFilter query = new TvActivityQueryFilter();
		// query.setIsId(tvActivity.getId());
		query.setIsDelete(IsDeleteEnum.UN_DELETE.getVal());
		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Direction.DESC, sortField));
		orders.add(new Order(Direction.DESC, "activityStatus"));
		// 字段排序
		Sort sort = new Sort(orders);

		// 分页
		Pageable page = new PageRequest(pageNo, pageSize, sort);
		// 查询分页数据
		Page<TvActivity> pageList = tvActivityRepositorySlave.findAll(query, page);
		return pageList;
	}

	/**
	 * 【根据id查询活动配置表】
	 * 
	 * @param id
	 * @return tvActivity
	 * @version 1.0
	 * @author Hermit 2017年04月26日 上午11:11:50
	 */
	@Override
	public TvActivity findTvActivity(Long id) {
		TvActivity tvActivity = null;
		if (StringUtils.isNotBlank(id.toString())) {
			tvActivity = tvActivityRepositorySlave.findOne(id);
		} else {
			GwsLogger.info("id不存在");
		}
		return tvActivity;
	}

	/**
	 * 【保存活动配置表】
	 * 
	 * @param tvActivity
	 * @return tvActivity
	 * @version 1.0
	 * @author Hermit 2017年04月26日 上午11:11:50
	 */
	@Override
	public TvActivity saveTvActivity(TvActivity tvActivity) {
		// 判断对象是否存在
		if (tvActivity != null) {
			// id统一生成
			Long id = idGlobalGenerator.getSeqId(TvActivity.class);
			tvActivity.setActivityId(id);
			long num=tvActivityRepositorySlave.count();
			tvActivity.setSortNum((short)num);
			tvActivity.setCtime(DateUtil.currentSecond());
			tvActivity.setUtime(DateUtil.currentSecond());
			tvActivity.setActivityStatus(StatusUpDownEnum.DOWN.getVal());
			tvActivity.setIsDelete(IsDeleteEnum.UN_DELETE.getVal());
			tvActivity = tvActivityRepositoryMaster.save(tvActivity);
			GwsLogger.info("活动配置表保存成功");
		} else {
			GwsLogger.info("活动配置表对象不存在，保存失败:tvActivity={}", ToStringBuilder.reflectionToString(tvActivity));
			return null;
		}
		return tvActivity;
	}

	/**
	 * 【修改活动配置表】
	 * 
	 * @param tvActivity
	 * @return tvActivity
	 * @version 1.0
	 * @author Hermit 2017年04月26日 上午11:11:50
	 */
	@Override
	public TvActivity updateTvActivity(TvActivity tvActivity) {
		if (null != tvActivity.getActivityId()) {
			// 先从库中查出该对象
			TvActivity tvActivityBean = tvActivityRepositorySlave.findOne(Long.valueOf(tvActivity.getActivityId()));
			// 判断对象是否存在
			if (tvActivityBean != null) {
				// 该处数据填充代码请自行补全....
				tvActivityBean.setActivityName(tvActivity.getActivityName());
				tvActivityBean.setActivityPicUrl(tvActivity.getActivityPicUrl());
				tvActivityBean.setActivityUrl(tvActivity.getActivityUrl());
				tvActivityBean.setDownTime(tvActivity.getDownTime());
				tvActivityBean.setUpTime(tvActivity.getUpTime());
				tvActivityBean.setUtime(DateUtil.currentSecond());
				tvActivity = tvActivityRepositoryMaster.save(tvActivityBean);
				GwsLogger.info("活动配置表修改成功");
			} else {
				GwsLogger.info("活动配置表对象不存在，修改失败:tvActivity={}", ToStringBuilder.reflectionToString(tvActivity));
				return null;
			}
		} else {
			GwsLogger.error("活动配置表id不存在，修改失败:tvActivity={}", ToStringBuilder.reflectionToString(tvActivity));
			return null;
		}
		return tvActivity;
	}

	/**
	 * 【根据id删除活动配置表】
	 * 
	 * @param id
	 * @return void
	 * @version 1.0
	 * @author Hermit 2017年04月26日 上午11:11:50
	 */
	@Override
	public void deleteTvActivity(Long id) {
		// 先从库中查出该对象
		TvActivity tvActivity = tvActivityRepositorySlave.findOne(id);
		// 判断对象是否存在
		if (tvActivity != null) {
			// 将用户状态改为删除
			tvActivity.setIsDelete(IsDeleteEnum.DELETE.getVal());
			TvActivity newTvActivity = tvActivityRepositoryMaster.save(tvActivity);
			// 判断对象是否存在
			if (newTvActivity != null) {
				GwsLogger.info("活动配置表删除成功");
			} else {
				GwsLogger.info("活动配置表删除失败:id={}", id);
			}
		} else {
			GwsLogger.info("活动配置表对象不存在:id={}", id);
		}
	}

	@Override
	public TvActivity updateTvActivityById(Long id,Short activityStatus) {
		TvActivity newTvActivity=null;
		if (null != id) {
			// 先从库中查出该对象
			TvActivity tvActivityBean = tvActivityRepositorySlave.findOne(id);
			// 判断对象是否存在
			if (tvActivityBean != null) {
				// 该处数据填充代码请自行补全....
				tvActivityBean.setUtime(DateUtil.currentSecond());
				tvActivityBean.setActivityStatus(activityStatus);
				newTvActivity = tvActivityRepositoryMaster.save(tvActivityBean);
				GwsLogger.info("活动配置表修改成功");
			} else {
				GwsLogger.info("活动配置表对象不存在，修改失败:id={},activityStatus={}", id,activityStatus);
				return null;
			}
		} else {
			GwsLogger.error("活动配置表id不存在，修改失败:id={},activityStatus={}", id,activityStatus);
			return null;
		}
		return newTvActivity;
	}

}
