package com.opengroup.longmao.gwcommon.service;

import org.springframework.data.domain.Page;
import com.opengroup.longmao.gwcommon.entity.po.GoodsCategoryInfo;

/**
 * 【类别】 service接口
 *
 * @version 1.0
 * @author Hermit 2017年05月15日 下午16:35:03
 */ 
public interface GoodsCategoryInfoService {

	 /**
	 * 【保存类别】
	 * @param goodsCategoryInfo
	 * @return goodsCategoryInfo
	 * @version 1.0
	 * @author Hermit 2017年05月15日 下午16:35:03
	 */ 
	 GoodsCategoryInfo saveGoodsCategoryInfo(GoodsCategoryInfo goodsCategoryInfo);

	 /**
	 * 【删除类别】
	 * @param goodsCategoryId
	 * @return void
	 * @version 1.0
	 * @author Hermit 2017年05月15日 下午16:35:03
	 */ 
	 void deleteGoodsCategoryInfo(Long goodsCategoryId);

	 /**
	 * 【修改类别】
	 * @param goodsCategoryInfo
	 * @return goodsCategoryInfo
	 * @version 1.0
	 * @author Hermit 2017年05月15日 下午16:35:03
	 */ 
	 GoodsCategoryInfo updateGoodsCategoryInfo(GoodsCategoryInfo goodsCategoryInfo);

	 /**
	 * 【查询类别】
	 * @param goodsCategoryId
	 * @return GoodsCategoryInfo
	 * @version 1.0
	 * @author Hermit 2017年05月15日 下午16:35:03
	 */ 
	 GoodsCategoryInfo findGoodsCategoryInfo(Long goodsCategoryId);

	 /**
	 * 【查询类别】
	 * @param goodsCategoryInfo
	 * @param pageNo
	 * @param pageSize
	 * @param sortField
	 * @return Page<GoodsCategoryInfo>
	 * @version 1.0
	 * @author Hermit 2017年05月15日 下午16:35:03
	 */ 
	 Page<GoodsCategoryInfo> findGoodsCategoryInfo(GoodsCategoryInfo goodsCategoryInfo,Integer pageNo,Integer pageSize,String sortField);

}