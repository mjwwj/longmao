/**
 * Created by Storm on 2016/4/12.
 */
require.config({
  paths: {
    config: 'base/config',
    jquery: ['base/jquery-2.1.4.min', 'jquery.min'],
    storm: 'base/storm-1.5',
    common: 'base/common'
  },
  shim: {
    md5: {
      exports: 'md5'
    }
  }
});

require(['config', 'jquery', 'storm', 'base/md5', 'common'], function (CONFIG, $, S) {
  'use strict';

  var $login = $('#J_loginForm'); // 登录表单
  var $loginBtn = $login.find(':submit');// 登录表单提交按钮
      //$code = $('#J_code'); // 验证码

  /**
   * 页面Javascript初始化
   */
  function init () {

    // 登录表单按钮事件
    $loginBtn.click(login);
    
    // 更换验证码
    // $code.click(codeChange);

    // codeChange();
  }

  /**
   * 登录
   * @param e
   * @returns {boolean}
   */
  function login (e) {
    e.stopPropagation();
    $login.validate(function () {
	      $login.setForm(function () {
	        location.href = "/index";
	      });
    });
    return false;
  }

  // 更换验证码
  /*function codeChange () {
    $code.attr('src', CONFIG.addr.verifyCode + $.now());
  }*/

  // 页面JS初始化
  init();

});