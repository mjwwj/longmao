package com.opengroup.longmao.gwcommon.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.opengroup.longmao.gwcommon.entity.po.AdminInfo;
import com.opengroup.longmao.gwcommon.entity.po.RoleAuthority;
import com.opengroup.longmao.gwcommon.entity.vo.AdminInfoVO;
import com.opengroup.longmao.gwcommon.enums.IsNormalEnum;
import com.opengroup.longmao.gwcommon.enums.IsOrNotEnum;
import com.opengroup.longmao.gwcommon.enums.UserSexEnum;
import com.opengroup.longmao.gwcommon.repository.master.AdminInfoRepositoryMaster;
import com.opengroup.longmao.gwcommon.repository.queryFilter.AdminInfoQueryFilter;
import com.opengroup.longmao.gwcommon.repository.slave.AdminInfoRepositorySlave;
import com.opengroup.longmao.gwcommon.service.AdminInfoService;
import com.opengroup.longmao.gwcommon.service.RoleAuthorityService;
import com.opengroup.longmao.gwcommon.tools.crypto.MD5Util;
import com.opengroup.longmao.gwcommon.tools.date.DateUtil;

/**
 * 
 * 用户管理service接口实现
 *
 * @version 
 * @author zengjq  2016年4月18日 上午11:51:22
 *
 */
@Service
public class AdminInfoServiceImpl implements AdminInfoService {

	@Autowired
	private AdminInfoRepositoryMaster adminInfoRepositoryMaster;

	@Autowired
	private AdminInfoRepositorySlave adminInfoRepositorySlave;

	@Autowired
	private RoleAuthorityService roleService;
	
	/**
	 * 
	 * 通过条件批量查询用户，返回泛型List
	 * 
	 * (non-Javadoc)
	 * @see com.gws.services.AdminInfoService#findAllAdminInfos(com.AdminInfoVO.entity.vo.adminInfoVo, java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	@Override
	public Page<AdminInfo> findAllAdminInfos(AdminInfoVO adminInfoVo, Integer pageNo,Integer pageSize,String sortField) {
		//排序
		Sort sort = new Sort(Direction.DESC, sortField);
		//分页
		Pageable page=new PageRequest(pageNo, pageSize,sort); 
		//组合查询语句
		AdminInfoQueryFilter query = new AdminInfoQueryFilter();
		if(null!=adminInfoVo){
			if(null!=adminInfoVo.getUserId()&&adminInfoVo.getUserId()>0){
				query.setUserId(adminInfoVo.getUserId());
			}
			if(StringUtils.isNotBlank(adminInfoVo.getUserName())){
				query.setUserName(adminInfoVo.getUserName());
			}
		}
		query.setIsDelete(IsNormalEnum.YES.getVal());
		//查询分页数据
		Page<AdminInfo> pageList = adminInfoRepositorySlave.findAll(query,page);
		for(AdminInfo AdminInfo:pageList.getContent()){
			if(AdminInfo.getRoleId()!=null&&AdminInfo.getRoleId()>0){
				//UserRoleEnum userRoleEnum =  UserRoleEnum.getEnumByNumber(AdminInfo.getRoleId());
				RoleAuthority ra = roleService.getRoleById(AdminInfo.getRoleId());
				if(null!=ra){
					if(ra.getIsDelete()==IsNormalEnum.YES.getVal()){
						AdminInfo.setUserRoleName(ra.getRoleName());
					}
				}
			}
			if(AdminInfo.getUserSex()!=null&&AdminInfo.getUserSex()>0){
				UserSexEnum userSexEnum = UserSexEnum.getEnumByNumber((AdminInfo.getUserSex()));
				AdminInfo.setUserSexName(userSexEnum.getDesc());
			}
			if(AdminInfo.getUserStatus()!=null&&AdminInfo.getUserStatus()>0){
				IsOrNotEnum userStatusEnum = IsOrNotEnum.getEnumByNumber((AdminInfo.getUserStatus()));
				AdminInfo.setUserStatusName(userStatusEnum.getDesc());
			}
		}
		return pageList;
	}

	/**
	 * 根据用户名查询，返回用户对象
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.gws.services.AdminInfoService#getAdminInfoByName(java.lang.String)
	 */
	@Override
	public AdminInfo getAdminInfoByName(String userName,Short userStatus,Short isDelete) {
		return  adminInfoRepositorySlave.getAdminInfoByName(userName,userStatus,isDelete);
	}

	/**
	 * 根据用户Id查询，返回用户对象
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.gws.services.AdminInfoService#getAdminInfoById(java.lang.Integer)
	 */
	@Override
	public AdminInfo getAdminInfoById(Long id) {
		return adminInfoRepositorySlave.findOne(id);
	}

	/**
	 * 
	 * 【新增管理员信息】
	 * 
	 * (non-Javadoc)
	 * @see com.opengroup.longmao.gwcommon.service.AdminInfoService#saveAdminInfo(com.opengroup.longmao.gwcommon.entity.po.AdminInfo)
	 */
	@Override
	public AdminInfo saveAdminInfo(AdminInfo adminInfo) {
		AdminInfo newAdminInfo = null;
		Integer userId = 0;
		try {
			if(StringUtils.isNotBlank(adminInfo.getUserName())){
				//先从库中查询用户信息
				AdminInfo adminInfoBean = adminInfoRepositorySlave.getAdminInfoByName(adminInfo.getUserName(),(short)1,IsNormalEnum.YES.getVal());
				//判断该账号是否存在
				if(adminInfoBean==null){
					Integer dbId = adminInfoRepositorySlave.getAdminInfoMaxId();
					if(null != dbId){
						userId = dbId + 1;
					}else{
						userId = userId + 1;
					}
					adminInfo.setUserId(Long.valueOf(userId));
					adminInfo.setPassword(MD5Util.MD5(adminInfo.getPassword()));
					adminInfo.setUserStatus(IsOrNotEnum.YES.getType());
					adminInfo.setCtime(DateUtil.currentSecond());
					adminInfo.setUtime(DateUtil.currentSecond());
					adminInfo.setIsDelete(IsNormalEnum.YES.getVal());
					
					newAdminInfo = adminInfoRepositoryMaster.save(adminInfo);
			    }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newAdminInfo;
	}

	/**
	 * 根据ID删除单条数据(逻辑删除，非物理删除)
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.gws.services.AdminInfoService#deleteById(com.gws.entity.AdminInfo)
	 */
	@Override
	public void deleteById(Long userId) {
		//通过id查询对象
		AdminInfo adminInfo  = adminInfoRepositorySlave.findOne(userId) ;
		//将用户状态改为删除
		adminInfo.setIsDelete(IsNormalEnum.NO.getVal());
		//做修改操作
		adminInfoRepositoryMaster.save(adminInfo);
	}

	/**
	 * 修改用户信息，并返回该条信息对象
	 * 
	 * (non-Javadoc)
	 * @see com.gws.services.AdminInfoService#updateAdminInfo(com.gws.entity.AdminInfo)
	 */
	@Override
	public AdminInfo updateAdminInfo(AdminInfo adminInfo) {
		//先从库中查询用户信息
		AdminInfo adminInfoDate = adminInfoRepositorySlave.findOne(adminInfo.getUserId());
		//将用户填写的新值set进去
		adminInfoDate.setUserName(adminInfo.getUserName());
		adminInfoDate.setUserAlias(adminInfo.getUserAlias());
		adminInfoDate.setUserMobile(adminInfo.getUserMobile());
		adminInfoDate.setUserAge(adminInfo.getUserAge());
		adminInfoDate.setUserEmail(adminInfo.getUserEmail());
		adminInfoDate.setUserSex(adminInfo.getUserSex());
		adminInfoDate.setRoleId(adminInfo.getRoleId());
		adminInfoDate.setUtime(DateUtil.currentSecond());
		//得到用户ip
		adminInfoDate.setUserLoginIp(adminInfo.getUserLoginIp());
		//登录时间
		adminInfoDate.setUserLoginTime(DateUtil.currentSecond());
		adminInfoDate.setCtime(DateUtil.currentSecond());
		if(StringUtils.isNotEmpty(adminInfo.getPassword())){
			 try {
				 if(!adminInfo.getPassword().equals(adminInfoDate.getPassword())){
					 adminInfoDate.setPassword(MD5Util.MD5(adminInfo.getPassword()));
				 }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 保存用户后并返回用户对象
		AdminInfo newAdminInfo = adminInfoRepositoryMaster.save(adminInfoDate);
		return newAdminInfo;
	}

	/**
	 * 
	 * 根据角色Id查询用户列表
	 * 
	 * (non-Javadoc)
	 * @see com.gws.services.AdminInfoService#getAdminInfoByRoleId(java.lang.Long)
	 */
	@Override
	public List<AdminInfo> getAdminInfoByRoleId(Integer roleId) {
		return adminInfoRepositorySlave.getAdminInfoByRoleId(roleId);
	}

	/**
	 * 
	 * 【保存用户权限】
	 * 
	 * (non-Javadoc)
	 * @see com.opengroup.longmao.gwcommon.service.AdminInfoService#saveAdminAuth(java.lang.Long, java.lang.String)
	 */
	@Override
	public AdminInfo saveAdminAuth(Long userId,String rights) {
		AdminInfo newAdminInfo = null;
		try {
			//先从库中查询用户信息
			AdminInfo adminInfoBean = adminInfoRepositorySlave.findOne(userId);
			//判断该账号是否存在
			if(adminInfoBean!=null){
				adminInfoBean.setRights(rights);
				adminInfoBean.setUtime(DateUtil.currentSecond());
				newAdminInfo = adminInfoRepositoryMaster.save(adminInfoBean);
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newAdminInfo;
	}

}
