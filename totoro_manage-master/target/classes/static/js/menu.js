/**
 * Created by Storm on 2016/4/12.
 */
require.config({
	paths : {
	    config : 'base/config',
		jquery : 'base/jquery.min',
		storm : 'base/storm-1.5',
		common : 'base/common'
	},
	shim : {
		'jquery.ztree' : [ 'jquery' ]
	}
});

require([ 'config', 'jquery', 'storm', 'common'],function(CONFIG, $, S) {
   'use strict';

			// 创建菜单
			var $menuCreateBtn = $('#J_menuCreateBtn'); // 创建菜单按钮
			var $menuCreateForm = $('#J_menuCreateForm');// 菜单创建表单
			var $menuCreateFormBtn = $menuCreateForm.find(':submit'); // 菜单表单提交按钮
			var $menuCreate = $('#J_menuCreate');// 菜单创建模态框
			var $onSelectClick = $("#allParent");//下拉菜单事件

			// 修改菜单
			var $menuEditForm = $('#J_menuEditForm'); // 菜单修改表单
			var $menuEditFormBtn = $menuEditForm.find(':submit'); // 菜单表单修改保存按钮
			var $menuEdit = $('#J_menuEdit'); // 菜单修改模态框

			// 查询按钮
			var $menuQueryBtn = $('#J_menuQueryBtn');

			var temp = $('#temp').html(), // 模板文件
			$content = $('#content'); // 渲染元素

			// js 初始化
			function init() {

				// 查询菜单
				$menuQueryBtn.on('click', queryPrentMenu);
				// 创建菜单绑定事件
				$menuCreateBtn.click(menuCreate);
				// 保存菜单
				$menuCreateFormBtn.on('click', submitMenuCreateForm);
				// 菜单编辑
				$(document).on('click', '.J_menuEditBtn', menuEdit);
				// 保存修改数据
				$menuEditFormBtn.on('click', submitMenuEditForm);
				// 下拉菜单事件
				$onSelectClick.change(setMUR);
			}

			// 菜单排序
		    window.menuSort = function(menuId, sort) {
				var postData = {
						"menuId" : menuId,
						"sort" : sort
					};
				S.post(CONFIG.addr.menuSort, postData, function(data) {
					queryPrentMenu();
				});
			}
			
			// 查询父菜单列表
			function queryPrentMenu() {
				S.post(CONFIG.addr.prentMenu, {}, function(data) {
					$content.html(S.template(data, temp));
				});
			}

			/**
			 * 菜单创建
			 */
			function menuCreate() {
				$("#menuName").val('');
				$("#menuUrl").val('');
				var $this = $(this);
				$this.postBtn(CONFIG.addr.prentMenu, $this.data(), function(data) {
					// 显示模态框
					modalCreateShow();
					$('#allParent').html(S.template(data, $('#allParentTemp').html()));});
				$("#resCreate").hide();
			}
			
			//下拉菜单选择事件
			function setMUR(){
				if($("#allParent").val()==""){
				   $("#resCreate").hide();	
				}else{
				   $("#resCreate").show();
				}
			}

			/**
			 * 模态框显示
			 */
			function modalCreateShow() {
				$menuCreate.find(':reset').trigger('click');
				$menuCreate.modal('show');
			}

			/**
			 * 提交菜单创建表单
			 * 
			 * @param e
			 */
			function submitMenuCreateForm() {
				$menuCreateForm.validate(function() {
					$menuCreateForm.setForm(function() {
						S.alert('保存成功!', function() {
							queryPrentMenu();
						});
						$menuCreate.trigger('Storm.modal.hide');
					});
				});
				return false;
			}

			/**
			 * 判断菜单菜单删除类型
			 */
			window.delmenu = function(menuId, isParent) {
				if (isParent) {
					S.confirm('你确定要删除该菜单吗？其下子菜单将一并删除！', function() {
						delmenuMethod(menuId);
					})
				} else {
					S.confirm('你确定要删除该菜单吗？', function() {
						delmenuMethod(menuId);
					});
				}
			}

			// 菜单删除
			function delmenuMethod(menuId) {
				var url = CONFIG.addr.delMenu + "?menuId=" + menuId;
				S.post(url, {}, function(data) {
					S.alert('删除成功!');
					queryPrentMenu();
				});
			}

			/**
			 * 菜单编辑
			 */
			function menuEdit() {
				var $this = $(this);
				$this.postBtn(CONFIG.addr.editMenu, $this.data(),function(data) {
					// 显示模态框
					modalEditShow();
					$('#parentSelect').html(
						 S.template(data.menuList, $('#parentTemp').html()));
						 S.insert({
							menuId : data.menu.menuId,
							menuUrl : data.menu.menuUrl,
							menuName : data.menu.menuName
					});
					// 判断菜单id是否存在
					if ($("#menuId").val() != "") {
						// 获得该菜单的父类id
						var parentId = data.menu.parentId;
						// 如果父类id不存在，说明这是大类，就禁用大类选择框
						if (parentId == undefined) {
							$("#parentSelect").attr("disabled", true);
							$("#resEdit").hide();
						} else {
							// 如果父类id存在，说明这是小类，就启用大类选择框，默认填充该小类对应的大类
							$("#parentSelect").attr("disabled", false);
							$("#parentSelect").val(parentId);
							$("#resEdit").show();
						}
					}
				});
			}

			/**
			 * 提交菜单编辑表单
			 * 
			 * @param e
			 */
			function submitMenuEditForm(e) {
				e.preventDefault();
				$menuEditForm.validate(function() {
					$menuEditForm.setForm(function() {
						S.alert('修改成功!');
						$menuEdit.trigger('Storm.modal.hide');
						queryPrentMenu();
					});
				});
				return false;
			}

			/**
			 * 模态框显示
			 */
			function modalEditShow() {
				$menuEdit.find(':reset').trigger('click');
				$menuEdit.modal('show');
			}

			// 展开折叠
			window.openClose = function(menuId, curObj, trIndex) {
				var txt = $(curObj).text();
				if (txt == "展开") {
					$(curObj).text("折叠");
					$("#tr" + menuId).after(
							"<tr id='tempTr" + menuId
									+ "'><td colspan='4'>数据载入中</td></tr>");
					if (trIndex % 2 == 0) {
						$("#tempTr" + menuId).addClass("main_table_even");
					}
					var url = CONFIG.addr.subList + "?menuId=" + menuId;
					S.post(url,{},function(data) {
						// alert(data);
						if (data.length > 0) {
							var html = "";
							$.each(data,function(i) {
								html = "<tr style='height:24px;line-height:24px;' name='subTr"+ menuId+ "'>";
								html += "<td></td>";
								html += "<td><span style='width:80px;display:inline-block;'></span>";
								if (i == data.length - 1)
									html += "<img src='img/joinbottom.gif' style='vertical-align: middle;'/>";
								else
									html += "<img src='img/join.gif' style='vertical-align: middle;'/>";
							    html += "<span style='width:100px;text-align:left;display:inline-block;'>"+ this.menuName+ "</span>";
								html += "</td>";
								html += "<td>"+ this.menuUrl+ "</td>";
								if(menuId!=1){
									html += "<td><a href='###' data-menu-id='"+ this.menuId+ "' class=' J_menuEditBtn'>修改</a> | <a href='###' onclick='delmenu("+ this.menuId+ ",false)'>删除</a></td>";
								}else{
									html += "<td><a href='###' data-menu-id='"+ this.menuId+ "' class=' J_menuEditBtn'>修改</a> </td>";
								}
								html += "</tr>";
								$("#tempTr"+ menuId).before(html);
							});
							$("#tempTr" + menuId).remove();
							if (trIndex % 2 == 0) {
								$("tr[name='subTr"+ menuId + "']").addClass("main_table_even");
							}
							// alert($(".main_table").html());
							} else {
								$("#tempTr" + menuId + " > td").html("没有相关数据");
							}
					}, "json");
				} else {
					$("#tempTr" + menuId).remove();
					$("tr[name='subTr" + menuId + "']").remove();
					$(curObj).text("展开");
				}
			}

	init();

		});