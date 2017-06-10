/**
 * Created by Storm on 2016/4/12.
 */
require.config({
	paths : {
		config : 'base/config',
		jquery : 'base/jquery.min',
		storm : 'base/storm-1.5',
		common : 'base/common',
		WdatePicker : 'My97DatePicker/WdatePicker'
	}
});
require([ 'config', 'jquery', 'storm', 'common', 'WdatePicker' ], function(CONFIG, $, S) {
	'use strict';

	
	var $form = $('[data-form="search"]'); // 查询表单
	var $formBtn = $form.find(':submit'); 
	
	var $tvFeedback = $('#wp_feedback'); // 用户反馈详情处理弹框
	
	var $tvFeedbackForm = $('#wp_feedbackform'); //用户反馈详情弹框表单
	var $tvFeedbackFormBtn = $tvFeedbackForm.find(':submit'); //用户反馈详情弹框处理按钮
	
	/**
	 * 
	 * 
	 * 页面初始化
	 */
	function init() {

		$(document).on('click', '.wp_handles', modalHandleFeedbackShow);//处理按钮反馈
		
		//反馈详情处理按钮点击事件
		$tvFeedbackFormBtn.click(HandleFeedback);
		 
	}
	
	function modalHandleFeedbackShow () {//显示反馈详情处理弹框
		var $this = $(this);
		$this.postBtn(CONFIG.addr.findTvFeedbackById, $this.data(), function(data) {//获取单个反馈信息
			
			if(data.dealStatus == 0){//未处理
				$tvFeedbackFormBtn.show();
				$('.input_textarea').removeClass('disnone').removeAttr('readonly','readonly');
				$('#handle_time').hide();
			} else if(data.dealStatus == 1){//已处理
				$tvFeedbackFormBtn.hide();
				$('.input_textarea').addClass('disnone').attr('readonly','readonly');
				$('#handle_time').show();
			}
			
			var feedbackUrl = data.feedbackRrl;
			feedbackUrl = feedbackUrl.split(";");
			data.feedbackUrl = feedbackUrl;
			$('#feedbackimg_list').html(S.template(data.feedbackUrl, $('#feedbackimg_Temp').html()));
			$(document).on("mouseover mouseout", '.feedbackimg_width', function(event){//banner 查看大图
				if(event.type == "mouseover"){//鼠标悬浮
					$(this).next().show();
				}else if(event.type == "mouseout"){ //鼠标离开
					$(this).next().hide();
				}
			});
			
			$tvFeedbackForm.find('[data-insert]').html('');
			$tvFeedbackForm.insert(data);
			
			$tvFeedback.find(':reset').trigger('click');
			$tvFeedback.modal('show');//显示反馈详情弹框弹框
		});
	}
	
	
	
	function HandleFeedback(e) {
		e.stopPropagation();
		$tvFeedbackForm.validate(function() {
			$tvFeedbackForm.setForm(function() {
				S.alert('处理反馈成功!');
				$tvFeedback.modal('hide');
				$formBtn.click();
			});
		});
		return false;
	}
	
//	function HandleFeedback () {//处理
//		var $this = $(this);
//		$this.postBtn(CONFIG.addr.updateTvFeedback, $this.data(), function(data) {//处理单个反馈信息
//			
//		});
		
//		var $this = $(this);
//		var data = {};
//		var dealUser = $('input[name=dealUser]').val();
//		var feedbackId = $('input[name=feedbackId]').val();
//		var dealRemark = $('#dealRemark').val();
//		console.log(data);
//		$this.postBtn(CONFIG.addr.updateTvFeedback, {'dealUser':dealUser,'feedbackId':feedbackId,'dealRemark':dealRemark}, function(data) {//处理单个反馈信息
//			
//		});
//	}
	
	
	// 页面JS初始化
	init();

});