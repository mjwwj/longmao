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
	
	var $wpGoods = $('#wp_goodshandle'); //商品弹框
	
	var $wpGoodsHandle = $('#wp_goodsHandleform'); //修改表单
	var $wpGoodsHandleFormBtn = $wpGoodsHandle.find(':submit');
	var $wpGoodsHandleFormReset = $wpGoodsHandle.find(':reset');
	
	
	function init() {//页面初始化
		$('[data-select="selectType"]').each(selectType);
		$('[data-select="auditStatus"]').each(auditStatus);
		$(document).on('click', '.wp_handle', goodsHandle);//修改
		$wpGoodsHandleFormBtn.click(submitHandleModify);//提交修改
	}
	
	function goodsHandle(){//修改显示弹框
		$('[data-select="selectType"]').each(selectType);
		$('[data-select="auditStatus"]').each(auditStatus);
		
		var $this = $(this);
		console.log($this.data());
		$this.postBtn(CONFIG.addr.findGoodsInfoById, $this.data(), function(data) {//获取单个商品
			
			
			$('select[name=isEnable]').find('option').each(function() {// 展示回填
				if ($(this).val() == data.isEnable) {
					$(this).attr('selected', 'selected');
				}
			});
			$('select[name=isSellOut]').find('option').each(function() {// 售完回填
				if ($(this).val() == data.isSellOut) {
					$(this).attr('selected', 'selected');
				}
			});
			$('select[name=auditStatus]').find('option').each(function() {// 审核回填
				if ($(this).val() == data.auditStatus) {
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
	function auditStatus() { //选择类型
		var $this = $(this), html = '';
		$.each(CONFIG.auditStatus, function(index, value) {
			html += '<option value="' + (index + 1) + '">' + value
					+ '</option>';
		});
		$this.html(html);
	}
	// 页面JS初始化
	init();

});