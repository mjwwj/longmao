package com.opengroup.longmao.gwcommon.repository.slave;

import org.springframework.data.jpa.repository.Query;

import com.opengroup.longmao.gwcommon.configuration.query.core.BaseRepository;
import com.opengroup.longmao.gwcommon.entity.po.User;

/**
 * 【用户信息表】 RepositorySlave接口
 *
 * @version 1.0
 * @author Hermit 2017年03月15日 上午09:59:49
 */ 
public interface UserRepositorySlave extends BaseRepository<User, Long> {
	
	@Query("select u from User u where u.userId = ?1")
	User queryUserByUserId(String userId);
}

