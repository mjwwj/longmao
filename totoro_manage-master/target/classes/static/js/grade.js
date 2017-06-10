/**
 * Created by Storm on 2016/4/12.
 */
require.config({
  paths: {
    config: 'base/config',
    jquery: ['base/jquery.min', 'jquery.min'],
    storm: 'base/storm-1.5',
    common: 'base/common'
  }
});
require(['config', 'jquery', 'storm', 'common'], function (CONFIG, $, S) {
  'use strict';

  var $form = $('[data-form="search"]'),// 查询用户规则表单
      $formBtn = $form.find(':submit'),  //查询用户规则按钮
  	  $J_showAddgrade = $('#J_showAddgrade'),//显示新增等级菜单
  	  $addGradeForm = $("#J_addGrade"),//新增等级规则表单
      $addGradeBtn = $addGradeForm.find(':submit'),//新增等级规则按钮
      $editGradeForm = $("#J_editGrade"),//编辑等级规则表单
      $editGradeBtn = $editGradeForm.find(':submit'),//编辑等级规则按钮
      $J_detail = $('#J_detail');//显示规则详情

  /**
   * 页面初始化
   */
  function init () {
    //查询
    //$formBtn.click();
    //SHOW
	$(document).on('click', '.J_showAddgradeBtn', showAddGrade);
	//ADD
	$addGradeBtn.on('click', addGrade);
	//查询
	$(document).on('click', '.J_viewBtn', gradeRule);
	//编辑
	$editGradeBtn.on('click', editGrade);
  }
  
  function gradeRule() {
      var $this = $(this);
      var uId = $this.attr("data-id");
      $this.postBtn(CONFIG.addr.findGradeRule, {"id":uId}, function (req) {
    	  var isMax = "0";
    	  if ("" == req) {
    		  $J_detail.find('[data-insert]').val("");
    		  $("#add_Id").val(uId);
    	  } else {
    		  $J_detail.find('[data-insert]').val("");
        	  $J_detail.insert(req);
        	  isMax = req.isMax;
    	  }
    	  $J_detail.find('.isMax').val(isMax);
    	  $J_detail.modal('show');
      })
  }
  
  function editGrade(e) {
	  e.preventDefault();
	  $editGradeForm.validate(function () {
		  $editGradeForm.setForm(function () {
              S.alert('等级规则修改成功!');
              $J_detail.trigger('Storm.modal.hide');
  	          //查询
  	          $formBtn.click();
      });
    });
    return false;
  }
  
  function showAddGrade() {
	  var $this = $(this);
	  $J_showAddgrade.modal('show');
  }
  
  function addGrade(e) {
	  e.preventDefault();
	  $addGradeForm.validate(function () {
		  $addGradeForm.setForm(function () {
              S.alert('新增等级规则成功!');
              $J_showAddgrade.trigger('Storm.modal.hide');
  	          //查询
  	          $formBtn.click();
      });
    });
    return false;
  }
  // 页面JS初始化
  init();

});