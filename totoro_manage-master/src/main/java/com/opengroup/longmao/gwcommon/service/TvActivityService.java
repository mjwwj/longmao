package com.opengroup.longmao.gwcommon.service;

import org.springframework.data.domain.Page;
import com.opengroup.longmao.gwcommon.entity.po.TvActivity;

/**
 * 【活动配置表】 service接口
 *
 * @version 1.0
 * @author Hermit 2017年04月26日 上午11:11:50
 */
public interface TvActivityService {

	/**
	 * 【保存活动配置表】
	 * 
	 * @param tvActivity
	 * @return tvActivity
	 * @version 1.0
	 * @author Hermit 2017年04月26日 上午11:11:50
	 */
	TvActivity saveTvActivity(TvActivity tvActivity);

	/**
	 * 【删除活动配置表】
	 * 
	 * @param id
	 * @return void
	 * @version 1.0
	 * @author Hermit 2017年04月26日 上午11:11:50
	 */
	void deleteTvActivity(Long id);

	/**
	 * 【修改活动配置表】
	 * 
	 * @param tvActivity
	 * @return tvActivity
	 * @version 1.0
	 * @author Hermit 2017年04月26日 上午11:11:50
	 */
	TvActivity updateTvActivity(TvActivity tvActivity);

	/**
	 * 【根据Id修改活动配置表】
	 * 
	 * @param id
	 * @return tvActivity
	 * @version 1.0
	 * @author Hermit 2017年04月26日 上午11:11:50
	 */
	TvActivity updateTvActivityById(Long id,Short activityStatus);

	/**
	 * 【查询活动配置表】
	 * 
	 * @param id
	 * @return TvActivity
	 * @version 1.0
	 * @author Hermit 2017年04月26日 上午11:11:50
	 */
	TvActivity findTvActivity(Long id);

	/**
	 * 【查询活动配置表】
	 * 
	 * @param tvActivity
	 * @param pageNo
	 * @param pageSize
	 * @param sortField
	 * @return Page<TvActivity>
	 * @version 1.0
	 * @author Hermit 2017年04月26日 上午11:11:50
	 */
	Page<TvActivity> findTvActivity(TvActivity tvActivity, Integer pageNo, Integer pageSize, String sortField);

}
