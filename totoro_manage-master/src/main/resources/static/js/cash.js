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

  var $form = $('[data-form="search"]'),// 查询用户提现记录表单
      $formBtn = $form.find(':submit'),  //查询用户提现记录按钮
  	  $J_showCash = $('#J_showCash'),//显示主播提现记录菜单
  	  $exprotBtn = $('.exprotBtn'); // 导出按钮

  /**
   * 页面初始化
   */
  function init () {
	  //查询
	  //$formBtn.click();
	  //处理提现
	  $(document).on('click', '.J_viewBtn', showCash);
	  $(document).on('click', '.J_extractCashSuccess', extractCashSuccess);
	  $(document).on('click', '.J_extractCashFail', extractCashFail);
	  //导出
	  $exprotBtn.click(exprot);
  }
  
  //导出
  function exprot () {
	    S.confirm('导出会消耗服务器性能,你确定要导出吗?', function () {
	  	  var data = {};
		  data[$form.data('param')] = JSON.stringify($form.formData());
		  location.href = CONFIG.addr.cashExprot+'?' + $.param(data);
	    });
}
  
  function showCash() {
	  var $this = $(this);
	  $("#o_oid").html($this.attr("data-oid"));
	  $("#o_uid").html($this.attr("data-uid"));
	  $("#o_price").html($this.attr("data-price"));
	  $("#o_param").html($this.attr("data-param"));
	  $("#o_time").html($this.attr("data-time"));
	  $("#o_remark").val("");
	  $J_showCash.find('.btn-danger').attr('data-oid', $this.attr("data-oid"));
	  $J_showCash.modal('show');
  }
  
  function extractCashSuccess() {
	  var $this = $(this);
	  var d_oId = $this.attr("data-oid");
	  extractCash(d_oId, 'success');
  }
  
  function extractCashFail() {
	  var $this = $(this);
	  var d_oId = $this.attr("data-oid");
	  extractCash(d_oId, 'fail');
  }

  function extractCash(d_oId, status) {
	  var $this = $(this);
	  var oId = $("#o_oid").html();
	  var uId = $("#o_uid").html();
	  var remark = $("#o_remark").val();
	  var str = "success" === status ? "转账成功" : "转账失败";

	  if ("fail" == status && "" == remark) {
		  S.alert('请填写失败原因!');
	  } else {
		  S.confirm({
			    width:300,
			    hasHeader: true,// 是否需要头部
		        headerTxt: "提现处理-转账状态",
				text: "【" + oId + "】状态:" + str + "？",
			    confirm: function(){
			    	if (d_oId === oId) {
						  $this.postBtn(CONFIG.addr.extractCash, {
							"orderId" : oId,
							"userId" : uId,
							"status" : status,
							"remark" : remark
						}, function() {
							S.alert('处理提现成功!', function() {
								$J_showCash.trigger('Storm.modal.hide');
								$formBtn.click();
							});
						});
				  } else {
					  S.alert('提现编号有误,请刷新再操作!', function () {
				  			$J_showCash.trigger('Storm.modal.hide');
				  	  }); 
				  }
			    },
			    cancel: function(){
			    	$J_showCash.trigger('Storm.modal.hide');
			    }
		  });
	  }
  }

  // 页面JS初始化
  init();

});