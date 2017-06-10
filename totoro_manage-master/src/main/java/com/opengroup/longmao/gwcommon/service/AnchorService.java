/**
 * @Title: AnchorService.java 
 * @Package com.opengroup.longmao.gwcommon.service 
 * @Description:
 * @author Mr.Zhu
 * @version V1.5
 */
package com.opengroup.longmao.gwcommon.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.opengroup.longmao.gwcommon.entity.dto.OrderInfoDTO;
import com.opengroup.longmao.gwcommon.entity.po.IdentityInfo;
import com.opengroup.longmao.gwcommon.entity.po.OrderInfo;
import com.opengroup.longmao.gwcommon.entity.po.User;
import com.opengroup.longmao.gwcommon.entity.vo.AnchorVO;
import com.opengroup.longmao.gwcommon.entity.vo.OrderInfoVO;

/**
 * @ClassName: AnchorService
 * @Description: TODO
 * @author Mr.Zhu
 */
public interface AnchorService {

	/**
	 * @Title: findAllUser 
	 * @Description: 分页查找主播信息 
	 * @param vo
	 * @param pageNo
	 * @param pageSize
	 * @param sortField
	 * @return Page<User>
	 */
	Page<User> findAllUser(AnchorVO vo, Integer pageNo, Integer pageSize, String sortField);
	
	/**
	 * @Title: findAllOrder 
	 * @Description: 分页查找主播提现信息 
	 * @param vo
	 * @param pageNo
	 * @param pageSize
	 * @param sortField
	 * @return Page<OrderInfo>
	 */
	Page<OrderInfo> findAllOrder(OrderInfoVO vo, Integer pageNo, Integer pageSize, String sortField);
	
	/**
	 * 
	 *  导出
	 * 
	 * @author zengJq 2017年05月10日
	 * @throws IOException 
	 */
	List<OrderInfoDTO> cashExprot(OrderInfoVO orderInfoVo,String sortField) throws IOException;
	
	/**
	 * @Title: findAllOrder 
	 * @Description: 查询主播提现记录信息 
	 * @param vo
	 * @param sortField
	 * @return List<OrderInfo>
	 */
	List<OrderInfo> findAllOrder(OrderInfoVO vo, String sortField);
	
	boolean extractCash(Map<String, Object> map);

	boolean editIdentity(AnchorVO vo);

	boolean editUser(String userId, Integer type);

	boolean editIdentity(String userId, String ratio);
	
	/**
	 * @Title: cancelAnchor 
	 * @Description: 取消主播身份 
	 * @param userId
	 * @return boolean
	 */
	boolean cancelAnchor(String userId);
	
	boolean editIdentity(String userId, Short isLive);

	IdentityInfo queryIdentityByUserId(String userId);

	List<OrderInfo> queryExtractCashLogByUserId(String userId);
	
	User queryUserByUserId(String userId);

}
