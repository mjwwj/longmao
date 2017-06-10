/**
 * Created by Storm on 2016/4/12.
 */
require.config({
	paths : {
		config : 'base/config',
		jquery : 'base/jquery.min',
		storm : 'base/storm-1.5',
		common : 'base/common',
	    domReady: 'jquery','jquery.ztree': 'zTree/jquery.ztree-2.6.min'
	},
	  shim: {
		  'jquery.ztree': ['jquery']
		  }
});

require([ 'config', 'jquery', 'storm', 'common','jquery.ztree' ], function(CONFIG, $,S) {
	'use strict';

	/**
	 * 页面初始化
	 */
	function init() {

		// 得到系统信息
//		 getMonitorInfo();
	}

	/**
	 * 得到系统信息
	 */
	function getMonitorInfo() {
		S.post(CONFIG.addr.getMonitorInfo, {}, function(req) {
			$("#main").find('[data-insert]').html('');
			$("#main").insert(req);
		});
	}

	// 页面JS初始化
	init();

});