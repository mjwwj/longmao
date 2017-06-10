/**
 * Created by Storm on 2016/4/12.
 */
require.config({
	paths : {
		config : 'base/config',
		jquery : 'base/jquery.min',
		storm : 'base/storm-1.5',
		common : 'base/common',
		time : 'base/time',
		domReady : 'jquery',
		'jquery.ztree' : 'zTree/jquery.ztree-2.6.min'
	},
	shim : {
		'jquery.ztree' : [ 'jquery' ]
	}
});

require([ 'config', 'jquery', 'storm', 'common', 'jquery.ztree', 'time' ], function(CONFIG, $, S) {
	'use strict';

	function init() {//页面初始化
		$('.navbar-default').css({
			'height' : $(window).height()
		});
	}
	
	// 页面JS初始化
	init();
});