/**
 * Created by Storm on 2016/4/12.
 */
require.config({
  paths: {
	    config : 'base/config',
		jquery : 'base/jquery.min',
		storm : 'base/storm-1.5',
		common : 'base/common',
        domReady: 'jquery','jquery.ztree': 'zTree/jquery.ztree-2.6.min'
  },
  shim: {
	  md5: {
	      exports: 'md5'
	    }
	  }
});
require(['config', 'jquery', 'storm', 'common','base/md5','jquery.ztree'], function (CONFIG, $, S) {
  'use strict';

  var $adminCreateBtn = $('#J_adminCreateBtn'), // 用户创建按钮
      $adminCreateForm = $('#J_adminCreateForm'), // 用户创建表单
      $adminEditForm = $('#J_adminEditForm'), // 用户修改表单
      $adminCreateFormBtn = $adminCreateForm.find(':submit'), // 用户表单创建提交按钮
      $adminEditFormBtn = $adminEditForm.find(':submit'), // 用户表单修改保存按钮
      $form = $('[data-form="search"]'), // 查询表单
      $adminCreate = $('#J_adminCreate'), // 用户创建弹出层
      $adminEdit = $('#J_adminEdit'); // 用户管理弹出层

  //编辑权限
  var $adminRightsForm = $('#J_adminRightsForm'); //权限修改表单
  var $adminRightsFormBtn = $adminRightsForm.find(':submit'); //权限表单修改保存按钮
  var $adminRights = $('#J_adminRights'); // 权限修改模态框
  
  //查询按钮
  var $formBtn = $form.find(':submit'); 
  
  var zTree;
  
  /**
   * 页面初始化
   */
  function init () {
	  
	// 表单按钮事件
//    $formBtn.click(adminQuery);

    // 创建用户绑定事件
	$adminCreateBtn.click(adminCreate);

    // 用户编辑
    $(document).on('click', '.J_adminEditBtn', adminEdit);

    // 用户删除
    $(document).on('click', '.J_adminDeleteBtn', adminDelete);
    
    //保存创建数据
    $adminCreateFormBtn.on('click',submitadminCreateForm);
    
    //保存修改数据
    $adminEditFormBtn.on('click',submitadminEditForm);
    
    // 权限编辑
    $(document).on('click', '.J_adminRightsBtn', adminRights);
   
	//权限修改数据
	$adminRightsFormBtn.on('click',submitadminRightsForm);
  }
  
  /**
   * 用户查询
   * @param e
   * @returns {boolean}
   */
  function adminQuery (e) {
    e.stopPropagation();
    $formBtn.validate(function () {
    	$formBtn.setForm(function () {
	        alert(111);
	      });
    });
    return false;
  }

  /**
   * 用户删除
   */
  function adminDelete () {
    var $this = $(this);
    S.confirm('你确定要删除该用户?', function () {
      $this.postBtn(CONFIG.addr.adminDelete, $this.data(), function () {
        S.alert('删除成功!');
        $form.trigger('Storm.search.get');
      })
    });
  }

  /**
   * 用户编辑
   */
  function adminEdit () {
    var $this = $(this);
    $this.postBtn(CONFIG.addr.adminDetail, $this.data(), function (data) {
      // 显示模态框
      modalEditShow();
      delete data.adminInfo.password;
      $adminEditForm.insert(data.adminInfo, 'name');
      $('#adminEditRoleList').html(S.template(data, $('#adminEditRoleTemp').html()));
    });
  }
  
  /**
   * 用户创建
   */
  function adminCreate () {
  var $this = $(this);
   $this.postBtn(CONFIG.addr.roleAuthority, {}, function (data) {
	    // 显示模态框
	    modalCreateShow();
	   $('#adminCreateRoleList').html(S.template(data, $('#adminCreateRoleTemp').html()));
    })
  }

  /**
   * 模态框显示
   */
  function modalCreateShow () {
    $adminCreate.find(':reset').trigger('click');
    $adminCreate.modal('show');
  }
  
  /**
   * 模态框显示
   */
  function modalEditShow () {
    $adminEdit.find(':reset').trigger('click');
    $adminEdit.modal('show');
  }

  /**
   * 提交用户创建表单
   * @param e
   */
  function submitadminCreateForm () {
    $adminCreateForm.validate(function () {
      $adminCreateForm.setForm(function () {
        S.alert('保存成功!', function () {
        	//location.reload();//刷新当前页面
     	     //查询
     	     $formBtn.click();
        });
        $adminCreate.trigger('Storm.modal.hide');
      });
    });
    return false;
  }


  /**
   * 提交编辑表单
   * @param e
   */
  function submitadminEditForm (e) {
	  e.preventDefault();
      $adminEditForm.validate(function () {
          $adminEditForm.setForm(function () {
              S.alert('保存成功!');
              $adminEdit.trigger('Storm.modal.hide');
  	          //查询
  	          $formBtn.click();
      });
    });
    return false;
  }

  /**
   * 权限编辑
   */
  function adminRights () {
    var $this = $(this);
    
    $this.postBtn(CONFIG.addr.adminAuth, $this.data(), function (data) {
    	// 显示模态框
    	modalRightsShow();
		var zn =data.zTreeNodes;
		var zTreeNodes = eval(zn);
		var setting = {
			    showLine: true,
			    checkable: true
			};
		//渲染节点菜单
		zTree = $("#tree").zTree(setting, zTreeNodes);
		//渲染adminId
        $adminRightsForm.insert(data, 'name');
    });
  }
  
  /**
   * 提交权限编辑表单
   * @param e
   */
  function submitadminRightsForm (e) {
	  e.preventDefault();
	  var $this = $(this);
	   var nodes = zTree.getCheckedNodes();
		var tmpNode;
		var ids = "";
		for(var i=0; i<nodes.length; i++){
			tmpNode = nodes[i];
			if(i!=nodes.length-1){
				ids += tmpNode.id+",";
			}else{
				ids += tmpNode.id;
			}
		}
		var userId = $('.userIdClass').val();
		var postData = {"userId":userId,"menuIds":ids};
		 $this.postBtn(CONFIG.addr.saveAdminAuth,postData,function(data){
			 S.alert('权限编辑成功!');
			  $adminRights.trigger('Storm.modal.hide');
		});
		 return false;
  
  }
  
  /**
   * 模态框显示
   */
  function modalRightsShow () {
    $adminRights.find(':reset').trigger('click');
    $adminRights.modal('show');
  }
  
  // 页面JS初始化
  init();

});