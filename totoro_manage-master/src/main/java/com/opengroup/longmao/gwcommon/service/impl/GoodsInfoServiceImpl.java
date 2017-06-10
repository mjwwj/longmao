package com.opengroup.longmao.gwcommon.service.impl;

import java.util.Date;

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
import com.opengroup.longmao.gwcommon.entity.po.GoodsInfo;
import com.opengroup.longmao.gwcommon.enums.AuditStatusEnum;
import com.opengroup.longmao.gwcommon.enums.GoodsCategoryEnum;
import com.opengroup.longmao.gwcommon.enums.GoodsTypeEnum;
import com.opengroup.longmao.gwcommon.enums.IsDeleteEnum;
import com.opengroup.longmao.gwcommon.enums.IsOrNotEnum;
import com.opengroup.longmao.gwcommon.repository.master.GoodsInfoRepositoryMaster;
import com.opengroup.longmao.gwcommon.repository.queryFilter.GoodsInfoQueryFilter;
import com.opengroup.longmao.gwcommon.repository.slave.GoodsInfoRepositorySlave;
import com.opengroup.longmao.gwcommon.service.GoodsInfoService;
import com.opengroup.longmao.gwcommon.tools.date.DateUtil;

/**
 * 【商品信息表】 Service接口实现
 *
 * @version 1.0
 * @author Hermit 2017年05月16日 下午17:13:21
 */
@Service
public class GoodsInfoServiceImpl implements GoodsInfoService {

	@Autowired
	private GoodsInfoRepositoryMaster goodsInfoRepositoryMaster;

	@Autowired
	private GoodsInfoRepositorySlave goodsInfoRepositorySlave;

	/**
	 * 【分页查询商品信息表】
	 * 
	 * @param goodsInfo
	 * @param pageNo
	 * @param pageSize
	 * @param sortField
	 * @return goodsInfo
	 * @version 1.0
	 * @author Hermit 2017年05月16日 下午17:13:21
	 */
	@Override
	public Page<GoodsInfo> findGoodsInfo(GoodsInfo goodsInfo, Integer pageNo, Integer pageSize, String sortField) {
		// 组合查询语句
		GoodsInfoQueryFilter query = new GoodsInfoQueryFilter();
		// query.setIsId(goodsInfo.getId());
		query.setIsDelete(IsDeleteEnum.UN_DELETE.getVal());
		// 字段排序
		Sort sort = new Sort(Direction.DESC, sortField);
		// 分页
		Pageable page = new PageRequest(pageNo, pageSize, sort);
		// 查询分页数据
		Page<GoodsInfo> pageList = goodsInfoRepositorySlave.findAll(query, page);
		for (GoodsInfo gi : pageList.getContent()) {
			gi.setCtimeStr(DateUtil.format(new Date(gi.getCtime()), DateUtil.TIME_PATTON_DEFAULT));
			gi.setUtimeStr(DateUtil.format(new Date(gi.getUtime()), DateUtil.TIME_PATTON_DEFAULT));
			gi.setGoodsCategoryStr(gi.getGoodsCategoryId() == null ? ""
					: GoodsCategoryEnum.getEnumByNumber(gi.getGoodsCategoryId()).getDesc());
			gi.setGoodsTypeStr(
					gi.getGoodsTypeId() == null ? "" : GoodsTypeEnum.getEnumByNumber(gi.getGoodsTypeId()).getDesc());
			gi.setAuditStatusStr(
					gi.getAuditStatus() == null ? "" : AuditStatusEnum.getEnumByNumber(gi.getAuditStatus()).getDesc());
			gi.setIsEnableStr(gi.getIsEnable() == null ? "" : IsOrNotEnum.getEnumByNumber(gi.getIsEnable()).getDesc());
			gi.setIsSellOutStr(
					gi.getIsSellOut() == null ? "" : IsOrNotEnum.getEnumByNumber(gi.getIsSellOut()).getDesc());
		}
		return pageList;
	}

	/**
	 * 【根据id查询商品信息表】
	 * 
	 * @param id
	 * @return goodsInfo
	 * @version 1.0
	 * @author Hermit 2017年05月16日 下午17:13:21
	 */
	@Override
	public GoodsInfo findGoodsInfo(Long id) {
		GoodsInfo goodsInfo = null;
		if (StringUtils.isNotBlank(id.toString())) {
			goodsInfo = goodsInfoRepositorySlave.findOne(id);
			goodsInfo.setCtimeStr(DateUtil.format(new Date(goodsInfo.getCtime()), DateUtil.TIME_PATTON_DEFAULT));
			goodsInfo.setUtimeStr(DateUtil.format(new Date(goodsInfo.getUtime()), DateUtil.TIME_PATTON_DEFAULT));
			goodsInfo.setGoodsCategoryStr(goodsInfo.getGoodsCategoryId() == null ? ""
					: GoodsCategoryEnum.getEnumByNumber(goodsInfo.getGoodsCategoryId()).getDesc());
			goodsInfo.setGoodsTypeStr(goodsInfo.getGoodsTypeId() == null ? ""
					: GoodsTypeEnum.getEnumByNumber(goodsInfo.getGoodsTypeId()).getDesc());
			goodsInfo.setAuditStatusStr(goodsInfo.getAuditStatus() == null ? ""
					: AuditStatusEnum.getEnumByNumber(goodsInfo.getAuditStatus()).getDesc());
			goodsInfo.setIsEnableStr(goodsInfo.getIsEnable() == null ? ""
					: IsOrNotEnum.getEnumByNumber(goodsInfo.getIsEnable()).getDesc());
			goodsInfo.setIsSellOutStr(goodsInfo.getIsSellOut() == null ? ""
					: IsOrNotEnum.getEnumByNumber(goodsInfo.getIsSellOut()).getDesc());
		} else {
			GwsLogger.info("id不存在");
		}
		
		return goodsInfo;
	}

	/**
	 * 【保存商品信息表】
	 * 
	 * @param goodsInfo
	 * @return goodsInfo
	 * @version 1.0
	 * @author Hermit 2017年05月16日 下午17:13:21
	 */
	@Override
	public GoodsInfo saveGoodsInfo(GoodsInfo goodsInfo) {
		// 判断对象是否存在
		if (goodsInfo != null) {
			// id统一生成
			// Long id = idGlobalGenerator.getSeqId(GoodsInfo.class);
			// goodsInfo.setId(id);
			goodsInfo = goodsInfoRepositoryMaster.save(goodsInfo);
			GwsLogger.info("商品信息表保存成功");
		} else {
			GwsLogger.info("商品信息表对象不存在，保存失败:goodsInfo={}", ToStringBuilder.reflectionToString(goodsInfo));
			return null;
		}
		return goodsInfo;
	}

	/**
	 * 【修改商品信息表】
	 * 
	 * @param goodsInfo
	 * @return goodsInfo
	 * @version 1.0
	 * @author Hermit 2017年05月16日 下午17:13:21
	 */
	@Override
	public GoodsInfo updateGoodsInfo(GoodsInfo goodsInfo) {
		// 先从库中查出该对象
		GoodsInfo goodsInfoBean = goodsInfoRepositorySlave.findOne(goodsInfo.getGoodsId());
		// 判断对象是否存在
		if (goodsInfoBean != null) {
			// 该处数据填充代码请自行补全....
			if (StringUtils.isNotBlank(goodsInfo.getName())) {
				goodsInfoBean.setName(goodsInfo.getName());
			}
			if (null != goodsInfo.getPrice()) {
				goodsInfoBean.setPrice(goodsInfo.getPrice());
			}
			if (null != goodsInfo.getDiscount()) {
				goodsInfoBean.setDiscount(goodsInfo.getDiscount());
			}
			if (null != goodsInfo.getIsEnable()) {
				goodsInfoBean.setIsEnable(goodsInfo.getIsEnable());
			}
			goodsInfo = goodsInfoRepositoryMaster.save(goodsInfoBean);
			GwsLogger.info("商品信息表修改成功");
		} else {
			GwsLogger.info("商品信息表对象不存在，修改失败:goodsInfo={}", ToStringBuilder.reflectionToString(goodsInfo));
			return null;
		}
		return goodsInfo;
	}

	/**
	 * 【根据id删除商品信息表】
	 * 
	 * @param id
	 * @return void
	 * @version 1.0
	 * @author Hermit 2017年05月16日 下午17:13:21
	 */
	@Override
	public void deleteGoodsInfo(Long id) {
		// 先从库中查出该对象
		GoodsInfo goodsInfo = goodsInfoRepositorySlave.findOne(id);
		// 判断对象是否存在
		if (goodsInfo != null) {
			// 将用户状态改为删除
			goodsInfo.setIsDelete(IsDeleteEnum.DELETE.getVal());
			GoodsInfo newGoodsInfo = goodsInfoRepositoryMaster.save(goodsInfo);
			// 判断对象是否存在
			if (newGoodsInfo != null) {
				GwsLogger.info("商品信息表删除成功");
			} else {
				GwsLogger.info("商品信息表删除失败:id={}", id);
			}
		} else {
			GwsLogger.info("商品信息表对象不存在:id={}", id);
		}
	}

}
