(function() {
	'use strict';

	var CONFIG = {

		// /////////
		// 是否在本地 //
		// 如果是后端开发人员, 并且域名为localhost 请修改为false
		// true: 并且域名为localhost, ajax执行get操作, ajax地址执行"前端专用"
		// flase: ajax执行post操作, ajax地址执行"后端专用"
		// /////////
		local : false,

		// ajax请求方式
		ajaxType : 'post',
		selectType : [ '是', '否' ],
		auditStatus : [ '待审核', '审核中', '审核通过', '审核失败' ]
	};

	// 判断是否为localhost, 并且CONFIG.local为true
	if (location.hostname === 'localhost' && CONFIG.local) { // true

		// 前端接口
		/*
		 * CONFIG.addr = { login : '/loginSubmit', // 登入 //verifyCode :
		 * '/code?t=' // 验证码 };
		 */

		// 本地ajax请求方式为post会报错
		// CONFIG.ajaxType = 'get';
	} else { // false

		// 后端接口
		CONFIG.addr = {
			login : '/loginSubmit', // 登入
			// verifyCode : '/code?t=', // 验证码
			logout : '/loginOut', // 登出
			adminList : '/findAdminInfos', // 管理员列表
			adminCreate : '/saveAdminInfo', // 新增管理员
			adminEdit : '/updateAdmin', // 管理员修改保存
			adminDetail : '/adminInfoSet', // 管理员详情
			adminDelete : '/deleteAdminById', // 管理员删除
			roleAuthority : '/roleAuthority',// 角色管理
			saveRole : '/saveRole',// 新增角色
			deleteRole : '/deleteRole',// 删除角色
			roleEdit : '/roleEdit',// 加载需要修改的角色
			updateRole : '/updateRole',// 保存修改的角色
			auth : '/auth',// 加载需要修改的角色权限
			saveAuth : '/saveAuth',// 保存修改的角色权限
			prentMenu : '/prentMenu',// 查询所有父类菜单列表
			editMenu : '/editMenu',// 菜单编辑
			delMenu : '/delMenu',// 删除菜单
			subList : '/subList',// 获取所有的子菜单
			adminAuth : '/adminAuth',// 加载需要修改的用户权限
			saveAdminAuth : '/saveAdminAuth',// 保存修改的用户权限
			leftRight : '/leftRight',// 左边菜单查询权限
			menuSort : '/menuSort',// 菜单排序
			findAnchor : '/findAnchor',// 主播列表
			findAllOrder : '/findAllOrder',// 订单列表
			anchorLog : '/anchorLog',// 通过ID查找主播日志
			editAnchorLive : '/editAnchorLive',// 通过ID编辑主播
			extractCash : '/extractCash',// 提现
			cashExprot:'/cashExprot',//提现导出
			findUserGradeRule : '/findUserGradeRule',// 等级规则列表
			saveUserGradeRule : '/saveUserGradeRule',// 新增等级规则
			findGradeRule : '/findGradeRule',// 通过ID查找等级
			getGuessById : '/getGuessById',//通过id查找竞猜
			findSysConfig : '/findSysConfig',//查询系统配置
			fileupload : '/fileupload', //上传banner图片
			deleteTvBanner : '/deleteTvBanner', //删除banner
			findTvBannerById : '/findTvBannerById', //查询单张banner图
			findTvFeedbackById : '/findTvFeedbackById', //查询单个反馈详情
			updateTvFeedback : '/updateTvFeedback', //处理单个反馈
			findTvIllegalReportById : '/findTvIllegalReportById', //查询单个举报详情
			deleteLiveH5 : '/deleteLiveH5', //删除单个活动
			findLiveH5 : '/findLiveH5',//查询单个活动
			findSysConfig : '/findSysConfig',//查询系统配置
			cancelAnchor : '/cancelAnchor',// 通过ID取消主播身份
			guessExprot:'/guessExprot',//竞猜导出
			deleteGoodsCategoryInfo : '/deleteGoodsCategoryInfo', //删除商品（类别）
			findGoodsCategoryInfo : '/findGoodsCategoryInfo',//查找单个商品（类别）
			deleteGoodsTypeInfo : '/deleteGoodsTypeInfo', //删除商品（类型）
			findGoodsTypeInfo : '/findGoodsTypeInfo', //查找单个商品（类型）
			findAllGoodsTypeInfo2 : '/findAllGoodsTypeInfo2', //查询所有的类型
			findOrderInfoById : '/findOrderInfoById',//查找单个公众号订单
			findGoodsInfoById : '/findGoodsInfoById',//查找单个公众号商品
			cancelAnchor : '/cancelAnchor',// 通过ID取消主播身份
			editSysConfig : '/updateSysConfig'//修改系统配置
		};
	}

	window.CONFIG = CONFIG;

	// 模块化写法
	if (typeof define === 'function' && define.amd) {
		define([], function() {
			return CONFIG;
		});
	}
}());