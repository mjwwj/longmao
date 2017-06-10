package com.opengroup.longmao.gwcommon.repository.slave;

import org.springframework.data.jpa.repository.Query;

import com.opengroup.longmao.gwcommon.configuration.query.core.BaseRepository;
import com.opengroup.longmao.gwcommon.entity.po.GoodsTypeInfo;

/**
 * 【类型】 RepositorySlave接口
 *
 * @version 1.0
 * @author Hermit 2017年05月15日 下午16:34:49
 */ 
public interface GoodsTypeInfoRepositorySlave extends BaseRepository<GoodsTypeInfo, Long> {

	/**
	 * 
	 *  查询表中最大id
	 * 
	 * @author zengjq 2017年4月17日
	 * @return
	 */
	@Query("select Max(g.goodsTypeId) from GoodsTypeInfo g")
	Integer getGoodsTypeInfoMaxId();
}

