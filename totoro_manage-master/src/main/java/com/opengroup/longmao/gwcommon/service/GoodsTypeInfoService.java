package com.opengroup.longmao.gwcommon.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.opengroup.longmao.gwcommon.entity.po.GoodsTypeInfo;

/**
 * 【类型】 service接口
 *
 * @version 1.0
 * @author Hermit 2017年05月15日 下午16:34:49
 */ 
public interface GoodsTypeInfoService {

	 /**
	 * 【保存类型】
	 * @param goodsTypeInfo
	 * @return goodsTypeInfo
	 * @version 1.0
	 * @author Hermit 2017年05月15日 下午16:34:49
	 */ 
	 GoodsTypeInfo saveGoodsTypeInfo(GoodsTypeInfo goodsTypeInfo);

	 /**
	 * 【删除类型】
	 * @param id
	 * @return void
	 * @version 1.0
	 * @author Hermit 2017年05月15日 下午16:34:49
	 */ 
	 void deleteGoodsTypeInfo(Long goodsTypeId);

	 /**
	 * 【修改类型】
	 * @param goodsTypeInfo
	 * @return goodsTypeInfo
	 * @version 1.0
	 * @author Hermit 2017年05月15日 下午16:34:49
	 */ 
	 GoodsTypeInfo updateGoodsTypeInfo(GoodsTypeInfo goodsTypeInfo);

	 /**
	 * 【查询类型】
	 * @param id
	 * @return GoodsTypeInfo
	 * @version 1.0
	 * @author Hermit 2017年05月15日 下午16:34:49
	 */ 
	 GoodsTypeInfo findGoodsTypeInfo(Long goodsTypeId);

	 /**
	 * 【查询类型】
	 * @param goodsTypeInfo
	 * @param pageNo
	 * @param pageSize
	 * @param sortField
	 * @return Page<GoodsTypeInfo>
	 * @version 1.0
	 * @author Hermit 2017年05月15日 下午16:34:49
	 */ 
	 Page<GoodsTypeInfo> findGoodsTypeInfo(GoodsTypeInfo goodsTypeInfo,Integer pageNo,Integer pageSize,String sortField);
	 
	 /**
	 * 【查询类型】
	 * @param sortField
	 * @version 1.0
	 * @author Hermit 2017年05月15日 下午16:34:49
	 */ 
	 List<GoodsTypeInfo> findGoodsTypeInfo(String sortField);
}

