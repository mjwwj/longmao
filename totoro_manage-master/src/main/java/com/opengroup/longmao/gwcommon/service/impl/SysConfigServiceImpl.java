package com.opengroup.longmao.gwcommon.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.opengroup.longmao.gwcommon.configuration.redis.cache.CacheClientString;
import com.opengroup.longmao.gwcommon.entity.po.Live;
import com.opengroup.longmao.gwcommon.entity.po.SysConfig;
import com.opengroup.longmao.gwcommon.enums.IsDeleteEnum;
import com.opengroup.longmao.gwcommon.repository.master.SysConfigRepositoryMaster;
import com.opengroup.longmao.gwcommon.repository.queryFilter.SysConfigQueryFilter;
import com.opengroup.longmao.gwcommon.repository.slave.LiveRepositorySlave;
import com.opengroup.longmao.gwcommon.repository.slave.SysConfigRepositorySlave;
import com.opengroup.longmao.gwcommon.service.SysConfigService;
import com.opengroup.longmao.gwcommon.tools.date.DateUtil;
import com.opengroup.longmao.gwcommon.tools.result.CommConstant;

/**
 * 【系统配置信息】 Service接口实现
 *
 * @version 1.0
 * @author Hermit 2017年05月09日 上午10:52:17
 */ 
@Service
public class SysConfigServiceImpl implements SysConfigService{

	@Autowired
	private SysConfigRepositorySlave sysConfigRepositorySlave;
	
	@Autowired
	private SysConfigRepositoryMaster sysConfigRepositoryMaster;
	
	@Autowired
	private LiveRepositorySlave liveRepositorySlave;
	
	@Autowired
	private CacheClientString cacheString;

	/**
	 * 
	 * 【查询系统配置信息】
	 * 
	 * (non-Javadoc)
	 * @see com.opengroup.longmao.gwcommon.service.SysConfigService#findSysConfig()
	 */
	@Override
	public List<SysConfig> findSysConfig(){
		SysConfigQueryFilter query = new SysConfigQueryFilter();
        query.setIsDelete(IsDeleteEnum.UN_DELETE.getVal());
        Sort sort = new Sort(Direction.ASC, "id");
        // 查询数据
        List<SysConfig> sysL = sysConfigRepositorySlave.findAll(query, sort);
        return sysL;
	}
	
	/**
	 * @Title: updateSysConfig 
	 * @Description: 【修改系统配置信息】 
	 * @param sysId
	 * @param sysKey
	 * @param sysVal
	 * @return Boolean
	 */
	@Override
	public Boolean updateSysConfig(Long sysId, String sysKey, String sysVal) {
		SysConfig sys = sysConfigRepositorySlave.findOne(sysId);
		if (null != sys) {
			sys.setName(sysKey);
			sys.setVal(sysVal);
			sys.setUtime(DateUtil.currentSecond());
			SysConfig config = sysConfigRepositoryMaster.save(sys);
			//服务端关闭全线直播
			stopLive(config);
			return true;
		}
		return false;
	}
	
	/**
	 * @Title: stopLive 
	 * @Description: 服务端关闭全线直播 
	 * @param sys
	 * @return void
	 */
	public void stopLive(SysConfig sys) {
		if (sys.getName().contains("live") && "2".equals(sys.getVal())) {
			//查询所有在播主播信息 0 直播中,1非直播中
			List<Live> list = liveRepositorySlave.queryAllLive(0);
			if (CollectionUtils.isNotEmpty(list)) {
				for (Live l : list) {
					cacheString.set(CommConstant.LIVE_HEART_ID, l.getId(), "1", 120L);
				}
			}
		}
	}
	
}