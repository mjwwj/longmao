package com.opengroup.longmao.gwcommon.configuration.query.core;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * query基类，注意分库分表的sharding-col是不能用使用In查询的，路由不了。
 *
 * @version 
 * @author zengjq  2016年10月26日 下午3:48:02
 * 
 */
public abstract class BaseQuery {
	//排序对象
	private Sort sort;
	private Pageable Page;
	public Sort getSort() {
		return sort;
	}
	public void setSort(Sort sort) {
		this.sort = sort;
	}
	public Pageable getPage() {
		return Page;
	}
	public void setPage(Pageable page) {
		Page = page;
	}
	
}
