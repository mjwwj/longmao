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

  var $form = $('[data-form="search"]'),// 查询用户表单
      $formBtn = $form.find(':submit'),  //查询用户按钮
      $addAnchorForm = $("#J_AddAnchor"),//开通主播表单
      $addAnchorBtn = $addAnchorForm.find(':submit'),//开通主播按钮
      $editAnchorForm = $("#J_EditAnchor"),//编辑主播表单
      $editAnchorBtn = $editAnchorForm.find(':submit'),//编辑主播按钮
      $J_detail = $('#J_detail'),//显示主播详情
  	  $J_showEdit = $('#J_showEdit');//显示开通主播菜单

  
  /**
   * 页面初始化
   */
  function init () {
    //查询
    //$formBtn.click();
    //通过id查找详情
    $(document).on('click', '.J_AnchorLogBtn', anchorLog);
    //SHOW审核
    $(document).on('click', '.J_viewBtn', showEdit);
    //创建
    $addAnchorBtn.on('click', addAnchor);
    //编辑
    $editAnchorBtn.on('click', editAnchor);
    //开播
    $(document).on('click', '.J_openLive', openLive);
    //禁播
    $(document).on('click', '.J_banLive', banLive);
    //取消主播
    $(document).on('click', '.J_cancel', cancel);
  }
  

  /**
   * 通过id查找
   */
  function anchorLog() {
      var $this = $(this);
      var uId = $this.attr("data-id");
      $this.postBtn(CONFIG.addr.anchorLog, {"userId":uId}, function (req) {
    	  var anchorType = "0";
    	  if ("" == req) {
    		  $J_detail.find('[data-insert]').val("");
    		  $("#add_ratio").val("0.50");
    		  $("#add_userId").val($this.attr("data-id"));
    		  $J_detail.find('.J_openLive').hide();
    		  $J_detail.find('.J_banLive').hide();
    	  } else {
    		  $J_detail.find('[data-insert]').val("");
        	  $J_detail.insert(req);
        	  if(1==req.isLive){
        		  $J_detail.find('.J_openLive').hide();
        		  $J_detail.find('.J_banLive').show();
        		  $J_detail.find('.J_banLive').attr('data-id', req.userId);
        		  $J_detail.find('.J_cancel').attr('data-id', req.userId);
        	  }else{
        		  $J_detail.find('.J_banLive').hide();
        		  $J_detail.find('.J_openLive').show();
        		  $J_detail.find('.J_openLive').attr('data-id', req.userId);
        		  $J_detail.find('.J_cancel').attr('data-id', req.userId);
        	  }
        	  anchorType = req.anchorType;
    	  }
    	  $J_detail.find('.J_anchorType').val(anchorType);
    	  $J_detail.modal('show');
      })
  }
  function addAnchor(e) {
	  e.preventDefault();
	  $addAnchorForm.validate(function () {
		  $addAnchorForm.setForm(function () {
              S.alert('主播开通成功!');
              $J_showEdit.trigger('Storm.modal.hide');
  	          //查询
  	          $formBtn.click();
      });
    });
    return false;
  }
  function editAnchor(e) {
	  e.preventDefault();
	  $editAnchorForm.validate(function () {
    	  $editAnchorForm.setForm(function () {
              S.alert('主播信息修改成功!');
              $J_detail.trigger('Storm.modal.hide');
  	          //查询
  	          $formBtn.click();
      });
    });
    return false;
  }
  function showEdit() {
	  var $this = $(this);
	  $("#edit_userId").val($this.attr("data-id"));
	  $J_showEdit.modal('show');
  }
  /**
   * 禁播
   */
  function banLive() {
    var $this = $(this);
    var uId = $this.attr("data-id");
    $this.postBtn(CONFIG.addr.editAnchorLive, {"userId":uId, "isLive" : "0"}, function () {
    	S.alert('禁止主播直播成功!', function () {
    		$J_detail.trigger('Storm.modal.hide');
    		$formBtn.click();
       });
    });
  }
  /**
   * 开播
   */
  function openLive() {
    var $this = $(this);
    var uId = $this.attr("data-id");
    $this.postBtn(CONFIG.addr.editAnchorLive, {"userId":uId, "isLive" : "1"}, function () {
    	S.alert('开通主播直播成功!', function () {
    		$J_detail.trigger('Storm.modal.hide');
    		$formBtn.click();
       });
    });
  }
  /**
   * 取消主播
   */
  function cancel() {
    var $this = $(this);
    var uId = $this.attr("data-id");
    S.confirm({
	    width:300,
	    hasHeader: true,// 是否需要头部
        headerTxt: "主播身份-配置",
		text: "设置【" + uId + "】主播为普通用户？",
	    confirm: function(){
	    	$this.postBtn(CONFIG.addr.cancelAnchor, {"userId":uId}, function () {
	        	S.alert('取消主播身份成功!', function () {
	        		$J_detail.trigger('Storm.modal.hide');
	        		$formBtn.click();
	           });
	        });
	    },
	    cancel: function(){
	    	$J_detail.trigger('Storm.modal.hide');
	    }
   });
  }
  // 页面JS初始化
  init();

});