package com.opengroup.longmao.gwcommon.repository.slave;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.opengroup.longmao.gwcommon.configuration.query.core.BaseRepository;
import com.opengroup.longmao.gwcommon.entity.po.Live;

/**
 * 【主播直播信息】 RepositorySlave接口
 *
 * @version 1.0
 * @author Hermit 2017年05月19日 下午19:00:20
 */ 
public interface LiveRepositorySlave extends BaseRepository<Live, Long> {
	
	/**
	 * @Title: queryAllLive 
	 * @Description: 查询主播直播列表 
	 * @param stats
	 * @return List<LiLive>
	 */
	@Query("select l from Live l where l.stats = ?1")
	List<Live> queryAllLive(Integer stats);
}

