package com.opengroup.longmao.gwcommon.configuration.jpa;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.CrudMethodMetadata;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.opengroup.longmao.gwcommon.configuration.log.GwsLogger;
import com.opengroup.longmao.gwcommon.configuration.query.core.BaseQuery;
import com.opengroup.longmao.gwcommon.configuration.query.core.BaseQueryPredicateBuilder;
import com.opengroup.longmao.gwcommon.configuration.query.core.BaseRepository;
import com.opengroup.longmao.gwcommon.configuration.query.core.QueryToSpecification;

/**
 * 扩展对JAP功能加强
 *
 * @version 
 * @author liuyi  2016年4月16日 下午2:40:16
 * 
 */
@Transactional(readOnly=false,propagation=Propagation.SUPPORTS)
@NoRepositoryBean
public class BaseSimpleJpaRepositoryEx<T, ID extends Serializable>
			extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {
	
	private EntityManager baseEm;
	private JpaEntityInformation<T, ?> baseEmInfo;
	
	/**
	 * BaseSimpleJpaRepositoryEx 构造器
	 * @param entityInformation
	 * @param entityManager
	 */
	public BaseSimpleJpaRepositoryEx(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		baseEm = entityManager;
		baseEmInfo = entityInformation;
	}

	/**
	 * 自定义查询条件转换实现
	 * 
	 * (non-Javadoc)
	 * @see com.gws.utils.query.core.GwsBaseRepository#getConditonByQuery(com.gws.utils.query.core.BaseQuery)
	 */
	private Specification<T> getConditonByQuery(BaseQuery query) {
		return new QueryToSpecification(query);
	}

	/**
	 * 封装自定义组合查询列表方法
	 * 
	 * (non-Javadoc)
	 * @see com.gws.utils.query.core.GwsBaseRepository#findAll(com.gws.utils.query.core.BaseQuery)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(BaseQuery query) {
		if(query.getSort()!=null){
			return findAll(getConditonByQuery(query), query.getSort());
		}
		else if(query.getPage()!=null){
			return (List<T>) findAll(getConditonByQuery(query),query.getPage());

		}else{
			return (List<T>) findAll(getConditonByQuery(query));
		}
		
	}
	
	/**
	 * 
	 * 自定义组合查询分页方法
	 * 
	 * @author liuyi 2016年4月18日
	 * @param query
	 * @param pageable
	 * @return
	 */
	@Override
	public Page<T> findAll(BaseQuery query,Pageable pageable) {
		return findAll(getConditonByQuery(query), pageable);
	}

	/**
	 * 查询条件
	 * 
	 * (non-Javadoc)
	 * @see com.gws.utils.query.core.GwsBaseRepository#findOne(com.gws.utils.query.core.BaseQuery)
	 */
	@Override
	public T findOne(BaseQuery query) {
		return findOne(getConditonByQuery(query));
	}
	
	
	@Override
	public List<T> findAll() {
		return super.findAll();
	}
	
	@Override
	protected CrudMethodMetadata getRepositoryMethodMetadata() {
		// TODO 这是系统自动生成描述，请在此补完后续代码
		return super.getRepositoryMethodMetadata();
	}

	@Override
	protected Class<T> getDomainClass() {
		// TODO 这是系统自动生成描述，请在此补完后续代码
		return super.getDomainClass();
	}

	@Override
	public T findOne(ID id) {
		// TODO 这是系统自动生成描述，请在此补完后续代码
		return super.findOne(id);
	}

	@Override
	protected Map<String, Object> getQueryHints() {
		// TODO 这是系统自动生成描述，请在此补完后续代码
		return super.getQueryHints();
	}

	@Override
	public T getOne(ID id) {
		// TODO 这是系统自动生成描述，请在此补完后续代码
		return super.getOne(id);
	}

	@Override
	public boolean exists(ID id) {
		// TODO 这是系统自动生成描述，请在此补完后续代码
		return super.exists(id);
	}

	@Override
	public List<T> findAll(Iterable<ID> ids) {
		// TODO 这是系统自动生成描述，请在此补完后续代码
		return super.findAll(ids);
	}

	@Override
	public List<T> findAll(Sort sort) {
		// TODO 这是系统自动生成描述，请在此补完后续代码
		return super.findAll(sort);
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		// TODO 这是系统自动生成描述，请在此补完后续代码
		return super.findAll(pageable);
	}

	@Override
	public T findOne(Specification<T> spec) {
		// TODO 这是系统自动生成描述，请在此补完后续代码
		return super.findOne(spec);
	}

	@Override
	public List<T> findAll(Specification<T> spec) {
		// TODO 这是系统自动生成描述，请在此补完后续代码
		return super.findAll(spec);
	}

	@Override
	public Page<T> findAll(Specification<T> spec, Pageable pageable) {
		// TODO 这是系统自动生成描述，请在此补完后续代码
		return super.findAll(spec, pageable);
	}

	@Override
	public List<T> findAll(Specification<T> spec, Sort sort) {
		// TODO 这是系统自动生成描述，请在此补完后续代码
		return super.findAll(spec, sort);
	}

	@Override
	public <S extends T> S findOne(Example<S> example) {
		// TODO 这是系统自动生成描述，请在此补完后续代码
		return super.findOne(example);
	}

	@Override
	public <S extends T> long count(Example<S> example) {
		// TODO 这是系统自动生成描述，请在此补完后续代码
		return super.count(example);
	}

	@Override
	public <S extends T> boolean exists(Example<S> example) {
		// TODO 这是系统自动生成描述，请在此补完后续代码
		return super.exists(example);
	}

	@Override
	public <S extends T> List<S> findAll(Example<S> example) {
		// TODO 这是系统自动生成描述，请在此补完后续代码
		return super.findAll(example);
	}

	@Override
	public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
		// TODO 这是系统自动生成描述，请在此补完后续代码
		return super.findAll(example, sort);
	}

	@Override
	public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO 这是系统自动生成描述，请在此补完后续代码
		return super.findAll(example, pageable);
	}

	@Override
	public long count() {
		// TODO 这是系统自动生成描述，请在此补完后续代码
		return super.count();
	}

	@Override
	public long count(Specification<T> spec) {
		// TODO 这是系统自动生成描述，请在此补完后续代码
		return super.count(spec);
	}

	@Override
	protected Page<T> readPage(TypedQuery<T> query, Pageable pageable, Specification<T> spec) {
		// TODO 这是系统自动生成描述，请在此补完后续代码
		return super.readPage(query, pageable, spec);
	}

	@Override
	protected <S extends T> Page<S> readPage(TypedQuery<S> query, Class<S> domainClass, Pageable pageable,
			Specification<S> spec) {
		// TODO 这是系统自动生成描述，请在此补完后续代码
		return super.readPage(query, domainClass, pageable, spec);
	}

	@Override
	protected TypedQuery<T> getQuery(Specification<T> spec, Pageable pageable) {
		// TODO 这是系统自动生成描述，请在此补完后续代码
		return super.getQuery(spec, pageable);
	}

	@Override
	protected <S extends T> TypedQuery<S> getQuery(Specification<S> spec, Class<S> domainClass, Pageable pageable) {
		// TODO 这是系统自动生成描述，请在此补完后续代码
		return super.getQuery(spec, domainClass, pageable);
	}

	@Override
	protected TypedQuery<T> getQuery(Specification<T> spec, Sort sort) {
		// TODO 这是系统自动生成描述，请在此补完后续代码
		return super.getQuery(spec, sort);
	}

	@Override
	protected <S extends T> TypedQuery<S> getQuery(Specification<S> spec, Class<S> domainClass, Sort sort) {
		// TODO 这是系统自动生成描述，请在此补完后续代码
		return super.getQuery(spec, domainClass, sort);
	}

	@Override
	protected TypedQuery<Long> getCountQuery(Specification<T> spec) {
		// TODO 这是系统自动生成描述，请在此补完后续代码
		return super.getCountQuery(spec);
	}

	@Override
	protected <S extends T> TypedQuery<Long> getCountQuery(Specification<S> spec, Class<S> domainClass) {
		// TODO 这是系统自动生成描述，请在此补完后续代码
		return super.getCountQuery(spec, domainClass);
	}

	/**
	 * 封装自定义组合查询排序列表方法
	 * 
	 * (non-Javadoc)
	 * @see com.gws.utils.query.core.GwsBaseRepository#findAll(com.gws.utils.query.core.BaseQuery, org.springframework.data.domain.Sort)
	 */
	@Override
	public List<T> findAll(BaseQuery query, Sort sort) {
		return findAll(getConditonByQuery(query), sort);
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Override
	public <S extends T> List<S> save(Iterable<S> arg0) {
		return super.save(arg0);
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Override
	public <S extends T> S save(S entity) {
		return super.save(entity);
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Override
	public <S extends T> S saveAndFlush(S entity) {
		return super.saveAndFlush(entity);
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Override
	public void setRepositoryMethodMetadata(CrudMethodMetadata crudMethodMetadata) {
		super.setRepositoryMethodMetadata(crudMethodMetadata);
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Override
	public void delete(ID id) {
		super.delete(id);
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Override
	public void delete(T entity) {
		super.delete(entity);
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Override
	public void delete(Iterable<? extends T> entities) {
		super.delete(entities);
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Override
	public void deleteInBatch(Iterable<T> entities) {
		super.deleteInBatch(entities);
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Override
	public void deleteAll() {
		super.deleteAll();
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Override
	public void deleteAllInBatch() {
		super.deleteAllInBatch();
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Override
	public void flush() {
		super.flush();
	}
	
	/**
	 * 
	 * 自定义更新update方法
	 * 
	 * @author liuyi 2016年7月16日
	 * @param id
	 * @param where
	 * @return
	 */
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public int  update(T t,BaseQuery where,String... updateFileds){
		CriteriaBuilder cb =baseEm.getEntityManagerFactory().getCriteriaBuilder();
		CriteriaUpdate<T> update =  (CriteriaUpdate<T>) cb.createCriteriaUpdate(t.getClass());
		Root<T> root = update.from((Class<T>) t.getClass());

		for(String fieldName:updateFileds){
			try {
				Object o = PropertyUtils.getProperty(t, fieldName);
				update.set(fieldName, o);
			} catch (Exception e) {
				GwsLogger.error("update error:"+e);
			}
		}
		update.where(BaseQueryPredicateBuilder.getPredicate2(root, cb,where));
		return baseEm.createQuery(update).executeUpdate();
	}
	
	/**
	 * 
	 * 根据唯一主键更新相关数据
	 * 
	 * @author liuyi 2016年7月16日
	 * @param id
	 * @param where
	 * @return
	 */
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public int updateById(T t,ID id,String... updateFileds){
		CriteriaBuilder cb =baseEm.getEntityManagerFactory().getCriteriaBuilder();
		CriteriaUpdate<T> update =  (CriteriaUpdate<T>) cb.createCriteriaUpdate(t.getClass());
		Root<T> root = update.from((Class<T>) t.getClass());
		for(String fieldName:updateFileds){
			try {
				Object o = PropertyUtils.getProperty(t, fieldName);
				update.set(fieldName, o);
			} catch (Exception e) {
				GwsLogger.error("update error:"+e);
			}
		}
		//定位主键信息
		Iterable<String> idAttributeNames = baseEmInfo.getIdAttributeNames();
		
		for(String key:idAttributeNames){
			if(key!=null&&key!=""){
				update.where(cb.equal(root.get(key), id));
				break;
			}
		}
		return baseEm.createQuery(update).executeUpdate();
	
	}
	

}
