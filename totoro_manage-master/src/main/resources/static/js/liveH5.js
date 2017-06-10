/**
 * Created by Storm on 2016/4/12.
 */
require.config({
	paths : {
		config : 'base/config',
		jquery : 'base/jquery.min',
		storm : 'base/storm-1.5',
		common : 'base/common',
		upload : 'base/uploadPreview',
		form : 'base/jquery.form',
		WdatePicker : 'My97DatePicker/WdatePicker'
	}
});
require([ 'config', 'jquery', 'storm', 'common', 'upload', 'form', 'WdatePicker' ], function(CONFIG, $, S) {
	'use strict';

	var $form = $('[data-form="search"]'); // 查询表单
	var $formBtn = $form.find(':submit'); 
	
	var $showH5 = $('#wp_showH5');//添加修改显示层
	
	var $addActive = $('#wp_addActive');//添加form
	var $addActiveformBtn = $addActive.find(':submit'); 
	
	var $modifyActive = $('#wp_ModifyActive');//修改form
	var $modifyActiveformBtn = $modifyActive.find(':submit');
	
	
	/**
	 * 页面初始化
	 */
	function init() {

		// 表单按钮事件
//		 $tvBannerBtn.click();

		// 显示banner添加modal
		$(document).on('click', '.wp_addsave', modalAddH5Show);

		// 活动添加按钮事件
		$addActiveformBtn.click(tvActiveAdd);
//		$addsaveBtn.click(cleanBanner);
		
		//active修改按钮事件
		$modifyActiveformBtn.click(activeModifyTrue);
		
		 // 删除active
		 $(document).on('click', '.wp_deleteBtn', activeDelete);
		 
		 //修改Active弹框
		 $(document).on('click', '.wp_modifyBtn', activeModify);
		 
		 $(document).on("mouseover mouseout", '.showload_img', function(event){//banner 查看大图
			 if(event.type == "mouseover"){//鼠标悬浮
				 $(this).next().show();
			 }else if(event.type == "mouseout"){ //鼠标离开
				 $(this).next().hide();
			 }
		 });
		 
		 new uploadPreview({ // 本地预览图片
			UpBtn : "active_upimg",
			DivShow : "active_divimg",
			ImgShow : "active_showimg",
		 });
	}

	function modalAddH5Show() {//显示添加弹框
		$showH5.modal('show');
		
		$addActive.find('[data-insert]').val('');
		$('.upload_img').attr('src','http://opq77mx3q.bkt.clouddn.com/170515-156-156.png');
	}
	
	$("#active_upimg").change(function() {//banner图片上传
		var data = {};
		$("#wp_activeupload").ajaxSubmit({
			type: "post", // 提交方式 get/post
			url: CONFIG.addr.fileupload, // 需要提交的 url
			data: data,
			success: function(data) {
				console.log(data);
				if(data.code = '000'){
					$("#active_upimg").val('');
					var r = data.data.downurl;
					r = r.substring(0,r.indexOf("?"));
					$("input[name=activeIco]").val(r);
				}
			},
			complete: function() {
				
			},
			error: function() {
				
			}
		});
	});
	
	/**
	 * 活动添加
	 * 
	 * @param e
	 * @returns {boolean}
	 */
	function tvActiveAdd(e) {
		e.stopPropagation();
		$addActive.validate(function() {
			$addActive.setForm(function() {
				S.alert('添加活动成功!');
				$addActive.find(':reset').trigger('click');
				$showH5.modal('hide');
				$formBtn.click();
			});
		});
		return false;
	}
	
	/**
	 * active修改数据回填
	 */
	function activeModify() {
		$showH5.modal('show');
		
		$modifyActive.modal('show')
		$addActive.modal('hide');
		
		$("input[name=activeIco]").val('');
		
		var $this = $(this);
		$this.postBtn(CONFIG.addr.findLiveH5, $this.data(), function(data) {
			var activeIco = data.activeIco;
			var isEnables = data.isEnable;
			$('.upload_img').attr('src',activeIco);
			
			$('.isEnable_class option').each(function(){
				if($(this).val() == isEnables){
					$(this).attr("selected","selected");
				}
			});
			
			$modifyActive.find('[data-insert]').html('');
			$modifyActive.insert(data);
		});
	}
	
	/**
	 * 活动修改数据
	 */
	function activeModifyTrue(e) {
		e.stopPropagation();
		$modifyActive.validate(function() {
			$modifyActive.setForm(function() {
				S.alert('修改活动成功!');
				$showH5.modal('hide');
				$modifyActive.modal('hide')
				$addActive.modal('show');
				$formBtn.click();
			});
		});
		return false;
	}

	/**
	 * active删除
	 */
	function activeDelete() {
		var $this = $(this);
		S.confirm('你确定要删除该活动?', function() {
			$this.postBtn(CONFIG.addr.deleteLiveH5, $this.data(), function() {
				S.alert('删除成功!');
//				$form.trigger('Storm.search.get');
				$formBtn.click();
			})
		});
	}

	
	// 页面JS初始化
	init();

});