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

  var $form = $('[data-form="search"]'),// 查询表单
      $formBtn = $form.find(':submit'), //查询按钮
      $J_detail = $('#J_detail'),//通过id查找详情
  	  $exprotBtn = $('.exportBtn'); // 导出按钮

  
  /**
   * 页面初始化
   */
  function init () {
    //通过id查找详情
    $(document).on('click', '.J_viewBtn', getGuessById);
    
    //导出
	$exprotBtn.click(exprot);
    
//    //查询
//    $formBtn.click();
	  
//    //审核
//    $(document).on('click', '.J_auditing', updateGuessStatus);
  }
  
  //导出
  function exprot () {
	    S.confirm('导出会消耗服务器性能,你确定要导出吗?', function () {
	  	  var data = {};
		  data[$form.data('param')] = JSON.stringify($form.formData());
		  location.href = CONFIG.addr.guessExprot+'?' + $.param(data);
	    });
  }
  /**
   * 通过id查找
   */
  function getGuessById () {
      var $this = $(this);
      $this.postBtn(CONFIG.addr.getGuessById, $this.data(), function (req) {
    	  $J_detail.find('[data-insert]').html('');
    	  $J_detail.insert(req);
    	  if(req.status==0){
    		  $J_detail.find('.J_auditing').show();
    		  $J_detail.find('.J_auditing').attr('data-guess-id',req.id);
    	  }else{
    		  $J_detail.find('.J_auditing').hide();
    	  }
    	  $J_detail.modal('show');
      })
  }
  
  /**
   * 审核
   */
//  function updateGuessStatus () {
//    var $this = $(this);
//    $this.postBtn(CONFIG.addr.updateGuessStatus, $this.data(), function () {
//    	S.alert('审核成功!', function () {
//            location.reload();
//            //$formBtn.click();
//       });
//    });
//  }
  
  // 页面JS初始化
  init();

});