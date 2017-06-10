package com.opengroup.longmao.gwcommon.service;

import org.springframework.data.domain.Page;
import com.opengroup.longmao.gwcommon.entity.po.GoodsInfo;

/**
 * 【商品信息表】 service接口
 *
 * @version 1.0
 * @author Hermit 2017年05月16日 下午17:13:21
 */ 
public interface GoodsInfoService {

	 /**
	 * 【保存商品信息表】
	 * @param goodsInfo
	 * @return goodsInfo
	 * @version 1.0
	 * @author Hermit 2017年05月16日 下午17:13:21
	 */ 
	 GoodsInfo saveGoodsInfo(GoodsInfo goodsInfo);

	 /**
	 * 【删除商品信息表】
	 * @param id
	 * @return void
	 * @version 1.0
	 * @author Hermit 2017年05月16日 下午17:13:21
	 */ 
	 void deleteGoodsInfo(Long id);


	 /**
	 * 【修改商品信息表】
	 * @param goodsInfo
	 * @return goodsInfo
	 * @version 1.0
	 * @author Hermit 2017年05月16日 下午17:13:21
	 */ 
	 GoodsInfo updateGoodsInfo(GoodsInfo goodsInfo);

	 /**
	 * 【查询商品信息表】
	 * @param id
	 * @return GoodsInfo
	 * @version 1.0
	 * @author Hermit 2017年05月16日 下午17:13:21
	 */ 
	 GoodsInfo findGoodsInfo(Long id);


	 /**
	 * 【查询商品信息表】
	 * @param goodsInfo
	 * @param pageNo
	 * @param pageSize
	 * @param sortField
	 * @return Page<GoodsInfo>
	 * @version 1.0
	 * @author Hermit 2017年05月16日 下午17:13:21
	 */ 
	 Page<GoodsInfo> findGoodsInfo(GoodsInfo goodsInfo,Integer pageNo,Integer pageSize,String sortField);


}

