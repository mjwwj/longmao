package com.opengroup.longmao.gwcommon.service.impl;

import org.apache.commons.collections.CollectionUtils;
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
import com.opengroup.longmao.gwcommon.entity.po.GoodsCategoryInfo;
import com.opengroup.longmao.gwcommon.enums.GoodsTypeEnum;
import com.opengroup.longmao.gwcommon.enums.IsDeleteEnum;
import com.opengroup.longmao.gwcommon.enums.IsOrNotEnum;
import com.opengroup.longmao.gwcommon.repository.master.GoodsCategoryInfoRepositoryMaster;
import com.opengroup.longmao.gwcommon.repository.queryFilter.GoodsCategoryInfoQueryFilter;
import com.opengroup.longmao.gwcommon.repository.slave.GoodsCategoryInfoRepositorySlave;
import com.opengroup.longmao.gwcommon.service.GoodsCategoryInfoService;
import com.opengroup.longmao.gwcommon.tools.date.DateUtil;

/**
 * 【类别】 Service接口实现
 *
 * @version 1.0
 * @author Hermit 2017年05月15日 下午16:35:03
 */ 
@Service
public class GoodsCategoryInfoServiceImpl implements GoodsCategoryInfoService{

	@Autowired
	private GoodsCategoryInfoRepositoryMaster goodsCategoryInfoRepositoryMaster;

	@Autowired
	private GoodsCategoryInfoRepositorySlave goodsCategoryInfoRepositorySlave;

	/**
	* 【分页查询类别】
	* @param goodsCategoryInfo
	* @param pageNo
	* @param pageSize
	* @param sortField
	* @return goodsCategoryInfo
	* @version 1.0
	* @author Hermit 2017年05月15日 下午16:35:03
	*/ 
	@Override
	public Page<GoodsCategoryInfo> findGoodsCategoryInfo(GoodsCategoryInfo goodsCategoryInfo,Integer pageNo, Integer pageSize, String sortField){
		// 组合查询语句
		GoodsCategoryInfoQueryFilter query = new GoodsCategoryInfoQueryFilter();
		query.setGoodsCategoryId(goodsCategoryInfo.getGoodsCategoryId());
		query.setIsDelete(IsDeleteEnum.UN_DELETE.getVal());
		
		if(goodsCategoryInfo!=null){
			if (goodsCategoryInfo.getGoodsCategoryId() != null) {
				query.setGoodsCategoryId(goodsCategoryInfo.getGoodsCategoryId());
			}
			if (goodsCategoryInfo.getGoodsTypeId() != null) {
				query.setGoodsTypeId(goodsCategoryInfo.getGoodsTypeId());
			}
			if (StringUtils.isNotBlank(goodsCategoryInfo.getName())) {
				query.setName(goodsCategoryInfo.getName());
			}
			if (goodsCategoryInfo.getIsEnable() != null) {
				query.setIsEnable(goodsCategoryInfo.getIsEnable());
			}
		}
		
		//字段排序
		Sort sort = new Sort(Direction.DESC, sortField);
		// 分页
		Pageable page = new PageRequest(pageNo, pageSize, sort);
		// 查询分页数据
		Page<GoodsCategoryInfo> pageList = goodsCategoryInfoRepositorySlave.findAll(query, page);
		if (CollectionUtils.isNotEmpty(pageList.getContent())) {
			for (GoodsCategoryInfo gci : pageList.getContent()) {
				if (gci.getIsEnable() != null) {
					gci.setIsEnablestr(IsOrNotEnum.getEnumByNumber(gci.getIsEnable()).getDesc());
				}
				if (gci.getGoodsTypeId() != null) {
					gci.setGoodsTypeStr(GoodsTypeEnum.getEnumByNumber(gci.getGoodsTypeId()).getDesc());
				}
				if (goodsCategoryInfo.getCtime() != null) {
					goodsCategoryInfo.setcTimeStr(DateUtil.timestampToDates(goodsCategoryInfo.getCtime(),DateUtil.TIME_PATTON_DEFAULT));
				}
				if (goodsCategoryInfo.getUtime() != null ) {
					goodsCategoryInfo.setuTimeStr(DateUtil.timestampToDates(goodsCategoryInfo.getUtime(),DateUtil.TIME_PATTON_DEFAULT));
				}
			}
		}
		return pageList;
	}
	
	
	/**
	* 【根据id查询类别】
	* @param id
	* @return goodsCategoryInfo
	* @version 1.0
	* @author Hermit 2017年05月15日 下午16:35:03
	*/ 
	@Override
	public GoodsCategoryInfo findGoodsCategoryInfo(Long id){
	    GoodsCategoryInfo goodsCategoryInfo = null;
		if(StringUtils.isNotBlank(id.toString())){
	      goodsCategoryInfo = goodsCategoryInfoRepositorySlave.findOne(id);
	      if(goodsCategoryInfo!=null){
				if (goodsCategoryInfo.getIsEnable() != null) {
					goodsCategoryInfo.setIsEnablestr(IsOrNotEnum.getEnumByNumber(goodsCategoryInfo.getIsEnable()).getDesc());
				}
				if (goodsCategoryInfo.getGoodsTypeId() != null) {
					goodsCategoryInfo.setGoodsTypeStr(GoodsTypeEnum.getEnumByNumber(goodsCategoryInfo.getGoodsTypeId()).getDesc());
				}
				if (goodsCategoryInfo.getCtime() != null) {
					goodsCategoryInfo.setcTimeStr(DateUtil.timestampToDates(goodsCategoryInfo.getCtime(),DateUtil.TIME_PATTON_DEFAULT));
				}
				if (goodsCategoryInfo.getUtime() != null ) {
					goodsCategoryInfo.setuTimeStr(DateUtil.timestampToDates(goodsCategoryInfo.getUtime(),DateUtil.TIME_PATTON_DEFAULT));
				}
	      }
		}else{
		  GwsLogger.info("id不存在");
		}
		return goodsCategoryInfo;
	}
	
	/**
	* 【保存类别】
	* @param goodsCategoryInfo
	* @return goodsCategoryInfo
	* @version 1.0
	* @author Hermit 2017年05月15日 下午16:35:03
	*/ 
	@Override
	public GoodsCategoryInfo saveGoodsCategoryInfo(GoodsCategoryInfo goodsCategoryInfo){
		//判断对象是否存在
		if(goodsCategoryInfo!= null){
			// id
		   Integer id = goodsCategoryInfoRepositorySlave.getGoodsCategoryInfoMaxId();
		   goodsCategoryInfo.setGoodsCategoryId(Long.valueOf(id+1));
		   goodsCategoryInfo.setUtime(DateUtil.currentSecond());
		   goodsCategoryInfo.setCtime(DateUtil.currentSecond());
		   goodsCategoryInfo.setIsDelete(IsDeleteEnum.UN_DELETE.getVal());
		   goodsCategoryInfo = goodsCategoryInfoRepositoryMaster.save(goodsCategoryInfo);
		   GwsLogger.info("类别保存成功");
		}else{
			GwsLogger.info("类别对象不存在，保存失败:goodsCategoryInfo={}",ToStringBuilder.reflectionToString(goodsCategoryInfo));
		    return null;
		}
		return goodsCategoryInfo;
	}

	/**
	* 【修改类别】
	* @param goodsCategoryInfo
	* @return goodsCategoryInfo
	* @version 1.0
	* @author Hermit 2017年05月15日 下午16:35:03
	*/ 
	@Override
	public GoodsCategoryInfo updateGoodsCategoryInfo(GoodsCategoryInfo goodsCategoryInfo){
		if(goodsCategoryInfo.getGoodsCategoryId()!=null){
		    //先从库中查出该对象
	        GoodsCategoryInfo goodsCategoryInfoBean = goodsCategoryInfoRepositorySlave.findOne(goodsCategoryInfo.getGoodsCategoryId());
		    //判断对象是否存在
			if(goodsCategoryInfoBean!= null){
				goodsCategoryInfoBean.setUtime(DateUtil.currentSecond());
				goodsCategoryInfoBean.setIsEnable(goodsCategoryInfo.getIsEnable());
				goodsCategoryInfoBean.setName(goodsCategoryInfo.getName());
				goodsCategoryInfoBean.setGoodsTypeId(goodsCategoryInfo.getGoodsTypeId());
				goodsCategoryInfoBean.setGoodsCategoryId(goodsCategoryInfo.getGoodsCategoryId());
				goodsCategoryInfoBean.setPrice(goodsCategoryInfo.getPrice());
			    goodsCategoryInfo = goodsCategoryInfoRepositoryMaster.save(goodsCategoryInfoBean);
			   GwsLogger.info("类别修改成功");
			}else{
			    GwsLogger.info("类别对象不存在，修改失败:goodsCategoryInfo={}",ToStringBuilder.reflectionToString(goodsCategoryInfo));
		        return null;
			}
		}else{
			 GwsLogger.error("类别id不存在，修改失败:goodsCategoryInfo={}",ToStringBuilder.reflectionToString(goodsCategoryInfo));
		     return null;
		}
		return goodsCategoryInfo;
	}

	/**
	 * 【根据id删除类别】
	 * @param id
	 * @return void
	 * @version 1.0
	 * @author Hermit 2017年05月15日 下午16:35:03
	 */ 
	@Override
	public void deleteGoodsCategoryInfo(Long id){
		//先从库中查出该对象
		GoodsCategoryInfo goodsCategoryInfo = goodsCategoryInfoRepositorySlave.findOne(id);
		//判断对象是否存在
		if(goodsCategoryInfo!=null){
			//将用户状态改为删除
			goodsCategoryInfo.setIsDelete(IsDeleteEnum.DELETE.getVal());
			GoodsCategoryInfo newGoodsCategoryInfo = goodsCategoryInfoRepositoryMaster.save(goodsCategoryInfo);
			//判断对象是否存在
			if(newGoodsCategoryInfo!=null){
				GwsLogger.info("类别删除成功");
			}else{
				GwsLogger.info("类别删除失败:id={}",id);
			}
		}else{
			GwsLogger.info("类别对象不存在:id={}",id);
		}
	}

}

