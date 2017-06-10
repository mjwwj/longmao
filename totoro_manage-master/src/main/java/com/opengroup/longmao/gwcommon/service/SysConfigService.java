package com.opengroup.longmao.gwcommon.service;

import java.util.List;

import com.opengroup.longmao.gwcommon.entity.po.SysConfig;

/**
 * 【系统配置信息】 service接口
 *
 * @version 1.0
 * @author Hermit 2017年05月09日 上午10:52:17
 */
public interface SysConfigService {

	/**
	 * 【查询系统配置信息】
	 * @return List<SysConfig>
	 * @version 1.0
	 * @author Yangst 2017年04月28日 上午10:52:17
	 */
	List<SysConfig> findSysConfig();

	/**
	 * @Title: updateSysConfig 
	 * @Description: 【修改系统配置信息】 
	 * @param sysId
	 * @param sysKey
	 * @param sysVal
	 * @return Boolean
	 */
	Boolean updateSysConfig(Long sysId, String sysKey, String sysVal);

}