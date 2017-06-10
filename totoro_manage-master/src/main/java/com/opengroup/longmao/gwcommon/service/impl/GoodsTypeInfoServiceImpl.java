package com.opengroup.longmao.gwcommon.service.impl;

import java.util.List;

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
import com.opengroup.longmao.gwcommon.entity.po.GoodsTypeInfo;
import com.opengroup.longmao.gwcommon.enums.IsDeleteEnum;
import com.opengroup.longmao.gwcommon.enums.IsOrNotEnum;
import com.opengroup.longmao.gwcommon.repository.master.GoodsTypeInfoRepositoryMaster;
import com.opengroup.longmao.gwcommon.repository.queryFilter.GoodsTypeInfoQueryFilter;
import com.opengroup.longmao.gwcommon.repository.slave.GoodsTypeInfoRepositorySlave;
import com.opengroup.longmao.gwcommon.service.GoodsTypeInfoService;
import com.opengroup.longmao.gwcommon.tools.date.DateUtil;

/**
 * 【类型】 Service接口实现
 *
 * @version 1.0
 * @author Hermit 2017年05月15日 下午16:34:49
 */
@Service
public class GoodsTypeInfoServiceImpl implements GoodsTypeInfoService {

	@Autowired
	private GoodsTypeInfoRepositoryMaster goodsTypeInfoRepositoryMaster;

	@Autowired
	private GoodsTypeInfoRepositorySlave goodsTypeInfoRepositorySlave;

	/**
	 * 【分页查询类型】
	 * 
	 * @param goodsTypeInfo
	 * @param pageNo
	 * @param pageSize
	 * @param sortField
	 * @return goodsTypeInfo
	 * @version 1.0
	 * @author Hermit 2017年05月15日 下午16:34:49
	 */
	@Override
	public Page<GoodsTypeInfo> findGoodsTypeInfo(GoodsTypeInfo goodsTypeInfo, Integer pageNo, Integer pageSize,
			String sortField) {
		// 组合查询语句
		GoodsTypeInfoQueryFilter query = new GoodsTypeInfoQueryFilter();
		query.setIsDelete(IsDeleteEnum.UN_DELETE.getVal());
		if (goodsTypeInfo != null) {
			if (goodsTypeInfo.getGoodsTypeId() != null) {
				query.setGoodsTypeId(goodsTypeInfo.getGoodsTypeId());
			}
			if (StringUtils.isNotBlank(goodsTypeInfo.getName())) {
				query.setName(goodsTypeInfo.getName());
			}
			if (goodsTypeInfo.getIsEnable() != null) {
				query.setIsEnable(goodsTypeInfo.getIsEnable());
			}
		}
		// 字段排序
		Sort sort = new Sort(Direction.DESC, sortField);
		// 分页
		Pageable page = new PageRequest(pageNo, pageSize, sort);
		// 查询分页数据
		Page<GoodsTypeInfo> pageList = goodsTypeInfoRepositorySlave.findAll(query, page);
		if (CollectionUtils.isNotEmpty(pageList.getContent())) {
			for (GoodsTypeInfo gti : pageList.getContent()) {
				if (gti.getIsEnable() != null) {
					gti.setIsEnablestr(IsOrNotEnum.getEnumByNumber(gti.getIsEnable()).getDesc());
				}
				if (gti.getCtime() != null) {
					gti.setcTimeStr(DateUtil.timestampToDates(gti.getCtime(),DateUtil.TIME_PATTON_DEFAULT));
				}
				if (gti.getUtime() != null ) {
					gti.setuTimeStr(DateUtil.timestampToDates(gti.getUtime(),DateUtil.TIME_PATTON_DEFAULT));
				}
			}
		}
		return pageList;
	}

	/**
	 * 【根据goodsTypeId查询类型】
	 * 
	 * @param goodsTypeId
	 * @return goodsTypeInfo
	 * @version 1.0
	 * @author Hermit 2017年05月15日 下午16:34:49
	 */
	@Override
	public GoodsTypeInfo findGoodsTypeInfo(Long goodsTypeId) {
		GoodsTypeInfo goodsTypeInfo = null;
		if (goodsTypeId != null && goodsTypeId > 0) {
			goodsTypeInfo = goodsTypeInfoRepositorySlave.findOne(goodsTypeId);
			if (goodsTypeInfo.getIsEnable() != null) {
				goodsTypeInfo.setIsEnablestr(IsOrNotEnum.getEnumByNumber(goodsTypeInfo.getIsEnable()).getDesc());
			}
			if (goodsTypeInfo.getCtime() != null) {
				goodsTypeInfo.setcTimeStr(DateUtil.timestampToDates(goodsTypeInfo.getCtime(),DateUtil.TIME_PATTON_DEFAULT));
			}
			if (goodsTypeInfo.getUtime() != null ) {
				goodsTypeInfo.setuTimeStr(DateUtil.timestampToDates(goodsTypeInfo.getUtime(),DateUtil.TIME_PATTON_DEFAULT));
			}
		} else {
			GwsLogger.info("goodsTypeId不存在");
		}
		return goodsTypeInfo;
	}

	/**
	 * 【保存类型】
	 * 
	 * @param goodsTypeInfo
	 * @return goodsTypeInfo
	 * @version 1.0
	 * @author Hermit 2017年05月15日 下午16:34:49
	 */
	@Override
	public GoodsTypeInfo saveGoodsTypeInfo(GoodsTypeInfo goodsTypeInfo) {
		// 判断对象是否存在
		if (goodsTypeInfo != null) {
			// id
			Integer id = goodsTypeInfoRepositorySlave.getGoodsTypeInfoMaxId();
			goodsTypeInfo.setGoodsTypeId(Long.valueOf(id+1));
			goodsTypeInfo.setUtime(DateUtil.currentSecond());
			goodsTypeInfo.setCtime(DateUtil.currentSecond());
			goodsTypeInfo.setIsDelete(IsDeleteEnum.UN_DELETE.getVal());
			goodsTypeInfo = goodsTypeInfoRepositoryMaster.save(goodsTypeInfo);
			GwsLogger.info("类型保存成功");
		} else {
			GwsLogger.info("类型对象不存在，保存失败:goodsTypeInfo={}", ToStringBuilder.reflectionToString(goodsTypeInfo));
			return null;
		}
		return goodsTypeInfo;
	}

	/**
	 * 【修改类型】
	 * 
	 * @param goodsTypeInfo
	 * @return goodsTypeInfo
	 * @version 1.0
	 * @author Hermit 2017年05月15日 下午16:34:49
	 */
	@Override
	public GoodsTypeInfo updateGoodsTypeInfo(GoodsTypeInfo goodsTypeInfo) {
		if (goodsTypeInfo.getGoodsTypeId() != null) {
			// 先从库中查出该对象
			GoodsTypeInfo goodsTypeInfoBean = goodsTypeInfoRepositorySlave.findOne(goodsTypeInfo.getGoodsTypeId());
			// 判断对象是否存在
			if (goodsTypeInfoBean != null) {
				goodsTypeInfoBean.setUtime(DateUtil.currentSecond());
				goodsTypeInfoBean.setIsEnable(goodsTypeInfo.getIsEnable());
				goodsTypeInfoBean.setName(goodsTypeInfo.getName());
				goodsTypeInfo = goodsTypeInfoRepositoryMaster.save(goodsTypeInfoBean);
				GwsLogger.info("类型修改成功");
			} else {
				GwsLogger.info("类型对象不存在，修改失败:goodsTypeInfo={}", ToStringBuilder.reflectionToString(goodsTypeInfo));
				return null;
			}
		} else {
			GwsLogger.error("类型id不存在，修改失败:goodsTypeInfo={}", ToStringBuilder.reflectionToString(goodsTypeInfo));
			return null;
		}
		return goodsTypeInfo;
	}

	/**
	 * 【根据id删除类型】
	 * 
	 * @param id
	 * @return void
	 * @version 1.0
	 * @author Hermit 2017年05月15日 下午16:34:49
	 */
	@Override
	public void deleteGoodsTypeInfo(Long id) {
		// 先从库中查出该对象
		GoodsTypeInfo goodsTypeInfo = goodsTypeInfoRepositorySlave.findOne(id);
		// 判断对象是否存在
		if (goodsTypeInfo != null) {
			// 将用户状态改为删除
			goodsTypeInfo.setIsDelete(IsDeleteEnum.DELETE.getVal());
			GoodsTypeInfo newGoodsTypeInfo = goodsTypeInfoRepositoryMaster.save(goodsTypeInfo);
			// 判断对象是否存在
			if (newGoodsTypeInfo != null) {
				GwsLogger.info("类型删除成功");
			} else {
				GwsLogger.info("类型删除失败:id={}", id);
			}
		} else {
			GwsLogger.info("类型对象不存在:id={}", id);
		}
	}

	/**
	 * 
	 * 【查询所有的类型】
	 * 
	 * (non-Javadoc)
	 * @see com.opengroup.longmao.gwcommon.service.GoodsTypeInfoService#findGoodsTypeInfo(java.lang.String)
	 */
	@Override
	public List<GoodsTypeInfo> findGoodsTypeInfo(String sortField) {
		// 组合查询语句
		GoodsTypeInfoQueryFilter query = new GoodsTypeInfoQueryFilter();
		query.setIsDelete(IsDeleteEnum.UN_DELETE.getVal());
		return goodsTypeInfoRepositorySlave.findAll(query);
	}

}
