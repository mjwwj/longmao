/**
 * Created by Storm on 2016/4/12.
 */
require.config({
  paths: {
	    config : 'base/config',
		jquery : 'base/jquery.min',
		storm : 'base/storm-1.5',
		common : 'base/common'
  }
});
require(['config', 'jquery', 'storm', 'common'], function (CONFIG, $, S) {
  'use strict';
  
  var $sysConfigForm = $('#J_sysconfigForm'); 
  var $sysConfigFormBtn = $sysConfigForm.find(':submit'); //权限表单修改保存按钮
  var $form = $('[data-form="search"]'),// 查询系统配置表单
  $formBtn = $form.find(':submit');  //查询系统配置按钮
  /**
   * 页面初始化
   */
  function init () {
	  //查询系统配置
	  $formBtn.click();
	  $('[data-select="selectType"]').each(selectType);
	  //修改系统数据
	  $sysConfigFormBtn.on('click',submitsysConfigForm);
	  $(document).on('click', '.J_editBtn', editSys);
	// 表单按钮事件
//    $formBtn.click(adminQuery);J_editBtn

    // 创建用户绑定事件
//	$adminCreateBtn.click(adminCreate);
//
//    // 用户编辑
//    $(document).on('click', '.J_adminEditBtn', adminEdit);
//
//    // 用户删除
//    $(document).on('click', '.J_adminDeleteBtn', adminDelete);
//    
//    //保存创建数据
//    $adminCreateFormBtn.on('click',submitadminCreateForm);
//    
//    //保存修改数据
//    $adminEditFormBtn.on('click',submitadminEditForm);
//    
//    // 权限编辑
//    $(document).on('click', '.J_adminRightsBtn', adminRights);
//   
//	//权限修改数据
//	$adminRightsFormBtn.on('click',submitadminRightsForm);
  }
  
  function editSys(e) {
	  var $this = $(this);
	  var sysId = $this.attr("data-id");
	  var sysKey = $("#key_" + sysId).val();
	  var sysVal = $("#val_" + sysId).val();
	  
	  $this.postBtn(CONFIG.addr.editSysConfig, {
			"sysId" : sysId,
			"sysKey" : sysKey,
			"sysVal" : sysVal
		}, function() {
			S.alert('修改系统配置成功!', function() {
				$formBtn.click();
			});
	  });
  }
  
  /**
   * 查询系统配置
   */
//  function sysConfig () {
//    var $this = $(this);
//	$this.postBtn(CONFIG.addr.findSysConfig, $this.data(), function (data) {
//		
//		$('input[name=id]').val(data.id);
//		$('select[name=appletSet]').find('option').each(function(){//支付回填
//			if($(this).val() == data.appletSet){
//				$(this).attr('selected','selected');
//			}
//		});
//		$('select[name=liveSet]').find('option').each(function(){//全线直播回填
//			if($(this).val() == data.liveSet){
//				$(this).attr('selected','selected');
//			}
//		});
//		$('select[name=guessSet]').find('option').each(function(){//全线竞猜回填
//			if($(this).val() == data.guessSet){
//				$(this).attr('selected','selected');
//			}
//		});
//	})
//  }
/**
 * 选择类型
 */
  function selectType () {
    var $this = $(this), html = '';
    $.each(CONFIG.selectType, function (index, value) {
      html += '<option value="' + (index+1) + '">' + value + '</option>';
    });
    $this.html(html);
  }
  
  /**
   * 提交修改系统数据
   * @param e
   */
  function submitsysConfigForm () {
	  $sysConfigForm.validate(function () {
		$sysConfigForm.setForm(function () {
        S.alert('保存成功!', function () {
        	location.reload();//刷新当前页面
        });
      });
    });
    return false;
  }

  // 页面JS初始化
  init();

});