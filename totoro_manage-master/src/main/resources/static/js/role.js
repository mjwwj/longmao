/**
 * Created by Storm on 2016/4/12.
 */
require.config({
	paths : {
	    config : 'base/config',
	    jquery : 'base/jquery.min',
		storm : 'base/storm-1.5',
		common : 'base/common',
		domReady : 'jquery',
		'jquery.ztree' : 'zTree/jquery.ztree-2.6.min'
	},
	shim : {
		'jquery.ztree' : [ 'jquery' ]
	}
});

require([ 'config', 'jquery', 'storm', 'common', 'jquery.ztree' ], function(CONFIG, $, S) {
	'use strict';
	
	// 创建角色
	var $roleCreateBtn = $('#J_roleCreateBtn'); // 创建角色按钮
	var $roleCreateForm = $('#J_roleCreateForm');// 角色创建表单
	var $roleCreateFormBtn = $roleCreateForm.find(':submit'); // 角色表单提交按钮
	var $roleCreate = $('#J_roleCreate');// 角色创建模态框

	// 修改角色
	var $roleEditForm = $('#J_roleEditForm'); // 角色修改表单
	var $roleEditFormBtn = $roleEditForm.find(':submit'); // 角色表单修改保存按钮
	var $roleEdit = $('#J_roleEdit'); // 角色修改模态框

	// 编辑权限
	var $roleRightsForm = $('#J_roleRightsForm'); // 权限修改表单
	var $roleRightsFormBtn = $roleRightsForm.find(':submit'); // 权限表单修改保存按钮
	var $roleRights = $('#J_roleRights'); // 权限修改模态框

	// 查询按钮
	var $roleQueryBtn = $('#J_roleQueryBtn');

	var temp = $('#temp').html(), // 模板文件
	$content = $('#content'); // 渲染元素

	var zTree;

	// js 初始化
	function init() {

		// 查询角色
		$roleQueryBtn.click(queryRole);

		// 创建角色绑定事件
		$roleCreateBtn.click(roleCreate);
		// 保存角色
		$roleCreateFormBtn.on('click', submitRoleCreateForm);
		// 角色删除
		$(document).on('click', '.J_roleDeleteBtn', roleDelete);

		// 角色编辑
		$(document).on('click', '.J_roleEditBtn', roleEdit);
		// 保存修改数据
		$roleEditFormBtn.on('click', submitRoleEditForm);

		// 权限编辑
		$(document).on('click', '.J_roleRightsBtn', roleRights);
		// 权限修改数据
		$roleRightsFormBtn.on('click', submitRoleRightsForm);
	}

	// 查询角色列表
	function queryRole() {
		S.post(CONFIG.addr.roleAuthority, {}, function(data) {
			$content.html(S.template(data, temp));
		});
	}

	/**
	 * 角色创建
	 */
	function roleCreate() {
		var $this = $(this);
		// 显示模态框
		modalCreateShow();
	}

	/**
	 * 模态框显示
	 */
	function modalCreateShow() {
		$roleCreate.find(':reset').trigger('click');
		$roleCreate.modal('show');
	}

	/**
	 * 提交角色创建表单
	 * 
	 * @param e
	 */
	function submitRoleCreateForm() {
		$roleCreateForm.validate(function() {
			$roleCreateForm.setForm(function() {
				S.alert('保存成功!', function() {
					queryRole();
				});
				$roleCreate.trigger('Storm.modal.hide');
			});
		});
		return false;
	}

	/**
	 * 角色删除
	 */
	function roleDelete() {
		var $this = $(this);
		S.confirm('你确定要删除该角色?', function() {
			$this.postBtn(CONFIG.addr.deleteRole, $this.data(), function() {
				S.alert('删除成功!');
				queryRole();
			})
		});
	}

	/**
	 * 角色编辑
	 */
	function roleEdit() {
		var $this = $(this);
		$this.postBtn(CONFIG.addr.roleEdit, $this.data(), function(data) {
			// 显示模态框
			modalEditShow();
			$roleEditForm.insert(data, 'name');
		});
	}

	/**
	 * 提交角色编辑表单
	 * 
	 * @param e
	 */
	function submitRoleEditForm(e) {
		e.preventDefault();
		$roleEditForm.validate(function() {
			$roleEditForm.setForm(function() {
				S.alert('修改成功!');
				$roleEdit.trigger('Storm.modal.hide');
				queryRole();
			});
		});
		return false;
	}

	/**
	 * 模态框显示
	 */
	function modalEditShow() {
		$roleEdit.find(':reset').trigger('click');
		$roleEdit.modal('show');
	}

	/**
	 * 权限编辑
	 */
	function roleRights() {
		var $this = $(this);
		$this.postBtn(CONFIG.addr.auth, $this.data(), function(data) {
			// 显示模态框
			modalRightsShow();
			var zn = data.zTreeNodes;
			var zTreeNodes = eval(zn);
			var setting = {
				showLine : true,
				checkable : true
			};
			// 渲染节点菜单
			zTree = $("#tree").zTree(setting, zTreeNodes);
			// 渲染roleId
			$roleRightsForm.insert(data, 'name');
		});
	}

	/**
	 * 提交权限编辑表单
	 * 
	 * @param e
	 */
	function submitRoleRightsForm(e) {
		e.preventDefault();
		var $this = $(this);
		var nodes = zTree.getCheckedNodes();
		var tmpNode;
		var ids = "";
		for (var i = 0; i < nodes.length; i++) {
			tmpNode = nodes[i];
			if (i != nodes.length - 1) {
				ids += tmpNode.id + ",";
			} else {
				ids += tmpNode.id;
			}
		}
		var roleId = $('.roleIdClass').val();
		var postData = {
			"roleId" : roleId,
			"menuIds" : ids
		};
		$this.postBtn(CONFIG.addr.saveAuth, postData, function(data) {
			S.alert('权限编辑成功!');
			$roleRights.trigger('Storm.modal.hide');
		});
		return false;
	}

	/**
	 * 模态框显示
	 */
	function modalRightsShow() {
		$roleRights.find(':reset').trigger('click');
		$roleRights.modal('show');
	}

	init();

});