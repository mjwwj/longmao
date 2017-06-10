package com.opengroup.longmao.gwcommon.service;

import org.springframework.data.domain.Page;
import com.opengroup.longmao.gwcommon.entity.po.LiveH5;
import com.opengroup.longmao.gwcommon.entity.vo.LiveH5VO;

/**
 * 【直播间H5活动入口】 service接口
 *
 * @version 1.0
 * @author Hermit 2017年05月12日 下午16:09:19
 */ 
public interface LiveH5Service {

	 /**
	 * 【保存直播间H5活动入口】
	 * @param liveH5VO
	 * @return liveH5
	 * @version 1.0
	 * @author Hermit 2017年05月12日 下午16:09:19
	 */ 
	 LiveH5 saveLiveH5(LiveH5VO liveH5VO);

	 /**
	 * 【删除直播间H5活动入口】
	 * @param id
	 * @return void
	 * @version 1.0
	 * @author Hermit 2017年05月12日 下午16:09:19
	 */ 
	 void deleteLiveH5(Long id);


	 /**
	 * 【修改直播间H5活动入口】
	 * @param liveH5
	 * @return liveH5
	 * @version 1.0
	 * @author Hermit 2017年05月12日 下午16:09:19
	 */ 
	 LiveH5 updateLiveH5(LiveH5 liveH5);

	 /**
	 * 【查询直播间H5活动入口】
	 * @param id
	 * @return LiveH5
	 * @version 1.0
	 * @author Hermit 2017年05月12日 下午16:09:19
	 */ 
	 LiveH5 findLiveH5(Long id);


	 /**
	 * 【查询直播间H5活动入口】
	 * @param liveH5
	 * @param pageNo
	 * @param pageSize
	 * @param sortField
	 * @return Page<LiveH5>
	 * @version 1.0
	 * @author Hermit 2017年05月12日 下午16:09:19
	 */ 
	 Page<LiveH5> findLiveH5(LiveH5 liveH5,Integer pageNo,Integer pageSize,String sortField);


}

