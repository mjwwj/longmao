package com.opengroup.longmao.gwcommon.repository.slave;

import org.springframework.data.jpa.repository.Query;

import com.opengroup.longmao.gwcommon.configuration.query.core.BaseRepository;
import com.opengroup.longmao.gwcommon.entity.po.GoodsCategoryInfo;

/**
 * 【类别】 RepositorySlave接口
 *
 * @version 1.0
 * @author Hermit 2017年05月15日 下午16:35:03
 */ 
public interface GoodsCategoryInfoRepositorySlave extends BaseRepository<GoodsCategoryInfo, Long> {


	/**
	 * 
	 *  查询表中最大id
	 * 
	 * @author zengjq 2017年4月17日
	 * @return
	 */
	@Query("select Max(g.goodsCategoryId) from GoodsCategoryInfo g")
	Integer getGoodsCategoryInfoMaxId();
}

