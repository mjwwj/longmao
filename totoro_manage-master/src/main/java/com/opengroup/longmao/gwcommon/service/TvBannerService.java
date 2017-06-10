package com.opengroup.longmao.gwcommon.service;

import org.springframework.data.domain.Page;
import com.opengroup.longmao.gwcommon.entity.po.TvBanner;
import com.opengroup.longmao.gwcommon.entity.vo.TvBannerVO;

/**
 * 【banner配置表】 service接口
 *
 * @version 1.0
 * @author Hermit 2017年04月26日 上午11:11:17
 */ 
public interface TvBannerService {

	 /**
	  * 
	  * 【保存banner配置表】
	  * 
	  * @author Hermit 2017年5月10日
	  * @param tvBannerVO
	  * @return
	  */
	 TvBanner saveTvBanner(TvBannerVO tvBannerVO);

	 /**
	 * 【删除banner配置表】
	 * @param id
	 * @return void
	 * @version 1.0
	 * @author Hermit 2017年04月26日 上午11:11:17
	 */ 
	 void deleteTvBanner(Long id);

	 /**
	 * 【修改banner配置表】
	 * @param tvBanner
	 * @return tvBanner
	 * @version 1.0
	 * @author Hermit 2017年04月26日 上午11:11:17
	 */ 
	 TvBanner updateTvBanner(TvBanner tvBanner);

	 /**
	 * 【查询banner配置表】
	 * @param id
	 * @return TvBanner
	 * @version 1.0
	 * @author Hermit 2017年04月26日 上午11:11:17
	 */ 
	 TvBanner findTvBanner(Long id);

	 /**
	 * 【查询banner配置表】
	 * @param tvBanner
	 * @param pageNo
	 * @param pageSize
	 * @param sortField
	 * @return Page<TvBanner>
	 * @version 1.0
	 * @author Hermit 2017年04月26日 上午11:11:17
	 */ 
	 Page<TvBanner> findTvBanner(TvBanner tvBanner,Integer pageNo,Integer pageSize,String sortField);

}

