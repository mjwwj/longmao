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
	
	
	var $wpaddGoods = $('.wp_addgoods'); //商品添加按钮
	var $wpGoods = $('#wp_goodshandle'); //商品弹框
	var $wpGoodsadd = $('#wp_goodsadd'); //商品添加
	
//	var $tvFeedbackFormBtn = $tvFeedbackForm.find(':submit');
	
	var $wpGoodsHandle = $('#wp_goodsHandleform'); //修改表单
	var $wpGoodsHandleFormBtn = $wpGoodsHandle.find(':submit');
	
	var $wpGoodsaddForm = $('#wp_goodsaddform'); //修改表单
	var $wpGoodsaddFormBtn = $wpGoodsaddForm.find(':submit');
	
	function init() {//页面初始化
		$('[data-select="selectType"]').each(selectType);
		
		$(document).on('click', '.wp_detele', goodsDelete);//删除行
		$(document).on('click', '.wp_handle', goodsHandle);//修改
		
		$wpGoodsHandleFormBtn.click(submitHandleModify);//提交修改
		$wpaddGoods.click(showAddModal);//显示添加弹框
		
		$wpGoodsaddFormBtn.click(submitAdd);//提交添加
		
	}
	
	function goodsDelete(){//删除
		var $this = $(this);
		console.log($this.data());
		S.confirm('你确定要删除该商品?', function() {
			$this.postBtn(CONFIG.addr.deleteGoodsTypeInfo, $this.data(), function() {
				S.alert('删除成功!');
				$formBtn.click();
			})
		});
	}
	
	function goodsHandle(){//修改显示弹框
		var $this = $(this);
		console.log($this.data());
		$this.postBtn(CONFIG.addr.findGoodsTypeInfo, $this.data(), function(data) {//获取单个商品
			$('select[name=isEnable]').find('option').each(function() {// 展示回填
				if ($(this).val() == data.isEnable) {
					$(this).attr('selected', 'selected');
				}
			});
			$wpGoodsHandle.find('[data-insert]').html('');
			$wpGoodsHandle.insert(data);
			$wpGoods.modal('show');
		});
	}
	
	function submitHandleModify(e) {//提交修改
		e.stopPropagation();
		$wpGoodsHandle.validate(function() {
			$wpGoodsHandle.setForm(function() {
				S.alert('修改成功!');
				$wpGoods.modal('hide');
				$formBtn.click();
			});
		});
		return false;
	}
	
	function selectType() { //选择类型
		var $this = $(this), html = '';
		$.each(CONFIG.selectType, function(index, value) {
			html += '<option value="' + (index + 1) + '">' + value
					+ '</option>';
		});
		$this.html(html);
	}
	
	function showAddModal(){//显示添加弹框
		$wpGoodsaddForm.find('[data-insert]').val('');
		$wpGoodsadd.modal('show');
	}
	
	function submitAdd(e) {//提交添加
		e.stopPropagation();
		$wpGoodsaddForm.validate(function() {
			$wpGoodsaddForm.setForm(function() {
				S.alert('添加成功!');
				$wpGoodsadd.modal('hide');
				$formBtn.click();
			});
		});
		return false;
	}
	
	// 页面JS初始化
	init();

});