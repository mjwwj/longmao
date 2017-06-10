/**
 * Author: Storm
 */
(function () {
  'use strict';
  var CONFIG;
  var Storm = {
    // 常用正则表达式
    regExp: {
      chinese: /[\u4E00-\u9FA5]/,
      email: /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/,
      phone: /^1\d{10}$/,
      space: /\s+/,
      special: /[':;*?~`!@#$%\^&+={}\[\]\\<>\(\),\. ]/,
      cnEnNum: /^[\u4E00-\u9FA5A-z0-9]$/,
      letterInt: /^[A-z0-9]+$/,
      letter: /^[A-z]+$/,
      num: /^[0-9]$/,
      intFirstNoZero: /^[1-9]([0-9]?)$/,
      money: /^([1-9][0-9]*)|([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})$/
    },
    /**
	 * 判断数据类型
	 * 
	 * @param arg
	 * @returns {{}}
	 */
    arg: function (arg) {
      var i = 0
          , // 循环数
          len = arg.length
          , // 传入参数长度
          result = {};
      // 返回值
      for (; i < len; i++) {
        result[this.type(arg[i])] = arg[i];
      }
      return result;
    },
    /**
	 * 加零
	 * 
	 * @param num
	 *            加零对象
	 * @param length
	 *            总长度
	 * @returns {string} 返回字符串
	 */
    zeroize: function (num, length) {
      // 定义参数
      var len = (num + '').length, // 获取数字长度
          i, // 循环相差长度
          tmp = '';
      // 所加的零
      length = length || 2;
      for (i = 0; i < length - len; i = i + 1) {
        tmp += '0'
      }
      return tmp + num;
    },
    /**
	 * 日期格式化
	 * 
	 * @param format
	 *            格式
	 * @param date
	 *            日期
	 * @returns {*|string|type[]|string[]|XML|void}
	 */
    dateFormat: function (date, format) {
      var arg = this.arg(arguments);
      date = arg['date'] || new Date();
      format = arg['string'] || 'YYYY-MM-DD';
      // 使用正则替换文本
      return format.replace(/"[^"]*"|'[^']*'|\b(?:D{1,2}|d{1,3}|m{1,4}|YY(?:YY)?|([hHMstT])\1?|[lLZ])\b/g, function (str) {
        switch (str) {
          case 'YYYY':
            return date.getFullYear();
          case 'YY':
            return (date.getFullYear() + '').substr(2);
          case 'MM':
            return Storm.zeroize(date.getMonth() + 1);
          case 'M':
            return date.getMonth() + 1;
          case 'DD':
            return Storm.zeroize(date.getDate());
          case 'D':
            return date.getDate();
          case 'hh':
            return Storm.zeroize(date.getHours());
          case 'h':
            return date.getHours();
          case 'mm':
            return Storm.zeroize(date.getMinutes());
          case 'm':
            return date.getMinutes();
          case 'ss':
            return Storm.zeroize(date.getSeconds());
          case 's':
            return date.getSeconds();
          case 'd':
            return date.getDay();
          case 'dd':
            return '\u5468' + ['\u65E5', '\u4E00', '\u4E8C', '\u4E09', '\u56DB', '\u4E94', '\u516D'][date.getDay()];
          case 'ddd':
            return '\u661F\u671F' + ['\u65E5', '\u4E00', '\u4E8C', '\u4E09', '\u56DB', '\u4E94', '\u516D'][date.getDay()];
          case 'tt':
            return date.getHours() < 12 ? 'am' : 'pm';
          case 'TT':
            return date.getHours() < 12 ? 'AM' : 'PM';
          case 't':
            return date.getHours() < 12 ? '\u4E0A\u5348' : '\u4E0B\u5348';
        }
      });
    },
    /**
	 * 判断是否为数组类型
	 * 
	 * @param {[type]}
	 *            obj [description]
	 * @return {Boolean} [description]
	 */
    isArray: function (obj) {
      return Array.isArray ? Array.isArray(obj) : Object.prototype.toString.call(obj) === '[object Array]';
    },
    /**
	 * 判断是否为JSON数据
	 * 
	 * @param {[type]}
	 *            obj [description]
	 * @return {Boolean} [description]
	 */
    isJson: function (obj) {
      // 判断为对象object, 并且不能获取到长度则为json
      return typeof (obj) === 'object' && Object.prototype.toString.call(obj).toLowerCase() === '[object object]' && !obj.length;
    },
    /**
	 * 判断是否为文档元素
	 * 
	 * @param obj
	 * @returns {boolean}
	 */
    isElement: function (obj) {
      // 存在对象并且对象节点值为1
      return !!(obj && obj.nodeType === 1);
    },
    /**
	 * 判断是否为对象
	 * 
	 * @param {[type]}
	 *            obj [description]
	 * @return {Boolean} [description]
	 */
    isObject: function (obj) {
      // 获取对象类型
      var type = typeof obj;
      // 类型为方法或者类型为对象并且布尔值为true
      return type === 'function' || type === 'object' && !!obj;
    },
    /**
	 * 是否为NaN类型
	 * 
	 * @param obj
	 * @returns {*|boolean}
	 */
    isNaN: function (obj) {
      // 是数字类型并且自身不等于自身则为NaN
      return this.isNumber(obj) && obj !== +obj;
    },
    /**
	 * 判读数据类型
	 * 
	 * @param obj
	 *            对象
	 * @returns {string} 字符串
	 */
    type: function (obj) {
      var type = Object.prototype.toString.call(obj)
          , length = type.length;
      type = type.substring(8, length - 1).toLowerCase();
      type = type === 'object' && this.isJson(obj) ? 'json' : type;
      return type + '';
    },
    /**
	 * 向浏览器输出错误信息
	 * 
	 * @param {[type]}
	 *            msg [description]
	 * @return {[type]} [description]
	 */
    error: function (msg) {
      throw new Error(msg);
    },
    /**
	 * 使用JSON替换字符
	 * 
	 * @param obj
	 *            对象
	 * @param template
	 *            模版
	 * @param lStr
	 *            左字符
	 * @param rStr
	 *            右字符
	 * @returns {string} 返回值
	 */
    replace: function (obj, template, lStr, rStr) {
      var result = '';
      if (!obj || !template) {
        return result;
      }

      // 判断是否为数组
      if (this.isArray(obj)) {
        $.each(obj, function (index, value) {
          result += S.replace(value, template, lStr, rStr);
        });
      }

      // 判断是否为JSON
      if (this.isJson(obj)) {
        var pattern = new RegExp(lStr + '[^' + rStr + ']+' + rStr, 'g');
        result = (result === '' ? template : result).replace(pattern, function (rep) {
          var key = rep.replace(new RegExp(lStr + '|' + rStr, 'g'), ''),
              resultRep = null;

          $.each(key.split('.'), function (index, value) {
            resultRep = (resultRep || obj)[value];
          });
          return resultRep;
        });
      }
      return result;
    },

    /**
	 * 使用JSON数据替换模版
	 * 
	 * @param json
	 * @param template
	 * @returns {*|string}
	 */
    replTemp: function (json, template) {
      return this.replace(json, template, '{{', '}}');
    },
    /**
	 * 从字符串中获取数据
	 * 
	 * @param string
	 *            字符串
	 * @param name
	 *            名称
	 * @returns {{}}
	 */
    splitStr: function (string, name) {
      var result = {}, length, temp, i;
      string = string.split('?')[1];
      if (!string) {
        return result
      }
      string = string.split('&');
      length = string.length;
      for (i = 0; i < length; i++) {
        temp = string[i].split('=');
        result[temp[0]] = temp[1];
      }
      return !name ? result : result[name];
    },
    /**
	 * 从链接中获取字符串
	 * 
	 * @returns {*|{}}
	 */
    splitHref: function (name) {
      return this.splitStr(location.search, name);
    },
    /**
	 * 获取两个数之间的随机数
	 * 
	 * @param max
	 *            最大值
	 * @param min
	 *            最小值| 0
	 * @returns {*}
	 */
    random: function (max, min) {
      min = min || 0;
      return Math.floor(Math.random() * (max - min + 1)) + min;
    },
    /**
	 * 删除两边的空格
	 * 
	 * @param str
	 * @param direction
	 * @returns {*|string|string[]|type[]|XML|void}
	 */
    trim: function (str, direction) {
      return str.replace(!direction ? /(^\s*)|(\s*$)/g : (direction === 'left' ? /(^\s*)/g : /(\s*$)/g), '');
    },
    /**
	 * 删除左边空格
	 * 
	 * @param {[type]}
	 *            str [description]
	 * @return {[type]} [description]
	 */
    lTrim: function (str) {
      return this.trim(str, 'left');
    },
    /**
	 * 删除右边空格
	 * 
	 * @param {[type]}
	 *            str [description]
	 * @return {[type]} [description]
	 */
    rTrim: function (str) {
      return this.trim(str, 'right');
    },
    /**
	 * post方法集合
	 * 
	 * @param request
	 *            请求地址
	 * @param data
	 *            请求数据
	 * @param callback
	 *            成功回调
	 * @param errorCallback
	 *            错误回调
	 */
    post: function (request, data, callback, errorCallback) {
      return $[CONFIG.ajaxType](request, data, function (rep) {
        Storm.ajaxData = rep;
        parseInt(rep.code) === 0 ? callback(rep.data, rep.message) : (errorCallback ? errorCallback(rep.message, rep.code, rep.data) : Storm.hint(rep.message));
      }, 'json').error(function (xhr, status) {
//    	  if (xhr.status === 200) {
//    		  location.href="/login";
//    	  }
    	  if (xhr.status === 403) {
    		  S.hint('您无权操作!');
    		  return false;
    	  }
    	  errorCallback ? errorCallback(xhr, status) : Storm.hint('\u7F51\u7EDC\u9519\u8BEF, \u8BF7\u91CD\u8BD5!');
          console.log(xhr, status);
      });
    },
    /**
	 * 秒转毫秒
	 * 
	 * @param value
	 *            值
	 * @param isUnit
	 *            是否需要单位
	 * @returns {*}
	 */
    s2ms: function (value, isUnit) {
      return value === undefined ? 0 : (/ms/.test(value) ? (!isUnit ? parseFloat(value) : value) : parseFloat(value) * 1000);
    },
    /**
	 * 动画回调
	 * 
	 * @param {[type]}
	 *            transition [description]
	 * @param {[type]}
	 *            result [description]
	 * @return {[type]} [description]
	 */
    transitionEnd: (function () {
      var element = document.createElement('Storm.transitionend')
          , transition = {
        WebkitTransition: 'webkitTransitionEnd',
        MozTransition: 'transitionend',
        OTransition: 'oTransitionEnd otransitionend',
        transition: 'transitionend'
      }
          , result = false;
      for (var name in transition) {
        if (element.style[name] !== undefined && transition.hasOwnProperty(name)) {
          return transition[name];
        }
      }
      return result;
    }()),
    /**
	 * 弹出框
	 * 
	 * @param options
	 * @returns {{}}
	 */
    popup: function (options) {
      var defaults = {
        toggle: 'show',
        // toggle
        className: 'popup-wrap',
        // className
        animation: 'zoom',
        // 动画效果
        bgAnim: 'fade',
        // 背景动画
        hasButton: true,
        // 是否需要按钮
        hasBackdrop: true,
        // 是否需要背景
        isFirstConfirm: true,
        // 是否确定按钮为先
        hasConfirm: true,
        // 是否需要确定按钮
        hasCancel: true,
        // 是否需要取消按钮
        hasHeader: false,
        // 是否需要头部
        headerTxt: '',
        // 头部文字
        confirmTxt: '\u786E\u5B9A',
        // 确定按钮文字
        cancelTxt: '\u53D6\u6D88',
        // 取消按钮文字
        text: '\u8BF7\u8F93\u5165\u63D0\u793A\u8BED\u8A00!',
        // 提示语言
        width: null,
        // 设置宽度
        height: null,
        // 设置高度
        $parent: $('body'),
        // 弹出框父元素
        confirm: function () {
        },
        // 确定函数
        cancel: function () {
        }// 取消函数
      }, _this = {}, html = '', o = $.extend({}, defaults, options), $elem = $('.' + o.className), $content;
      if (!$elem.length) {
        html = '<div class="popup ' + o.className + '">' + (o.hasBackdrop ? '<div class="popupBackdrop ' + o.bgAnim + '"></div>' : '') + '<div class="popupDialog">' + '<div class="popupContent ' + o.animation + '">' + (o.hasHeader ? '<div class="popupHeader">' + o.headerTxt + '</div>' : '') + '<div class="popupBody">' + o.text + '</div>' + (o.hasButton ? ('<div class="popupFooter">' + (o.hasConfirm ? ('<div class="popupConfirm">' + o.confirmTxt + '</div>') : '') + (o.hasCancel ? '<div class="popupCancel">' + o.cancelTxt + '</div>' : '') + '</div>') : '') + '</div></div></div>';
        $elem = $(html);
        $content = $elem.find('.popupContent');
        o.$parent.append($elem);
      } else {
        $content = $elem.find('.popupContent');
        $content.find('.popupBody').html(o.text);
        $content.find('.popupConfirm').html(o.confirmTxt);
        $content.find('.popupCancel').html(o.cancelTxt);
      }
      _this.width = function (width) {
        $content.width(width);
      }
      ;
      _this.height = function (height) {
        $content.height(height);
      }
      ;
      _this.backdrop = function (type) {
        var $backdrop = $elem.find('.popupBackdrop');
        type === 'show' ? $backdrop.show().scrollTop(0).addClass('active') : $backdrop.scrollTop(0).removeClass('active');
      }
      ;
      $elem.on('Storm.popup.show', _this.show);
      _this.show = function () {
        $elem.show();
        $content.scrollTop(0).addClass('active').transition(function () {
          $content.removeClass(o.animation);
        });
        _this.backdrop('show');
      }
      ;
      $elem.on('Storm.popup.hide', _this.hide);
      _this.hide = function (callback) {
        $content.addClass(o.animation).removeClass('active').transition(function () {
          $elem.hide();
          callback && callback();
        });
        _this.backdrop('hide');
      }
      ;
      o.width && _this.width(o.width);
      o.height && _this.height(o.height);
      $elem.find('.popupConfirm').one('click', function () {
        _this.hide(o.confirm);
      });
      $elem.find('.popupCancel').one('click', function () {
        _this.hide(o.cancel);
      });
      _this[o.toggle]();
      return _this;
    },
    /**
	 * 对话框
	 * 
	 * @param text
	 * @param callback
	 * @param cancelCallback
	 * @returns {*|{}}
	 */
    confirm: function (text, callback, cancelCallback) {
      var options = {
        className: 'confirm-wrap',
        confirm: callback,
        cancel: cancelCallback
      };
      if (typeof text === 'string') {
        options.text = text;
      } else {
        options = $.extend({}, options, text);
      }
      return this.popup(options);
    },
    /**
	 * 确定框
	 * 
	 * @param text
	 *            文本内容
	 * @param callback
	 *            回调
	 * @returns {*|{}}
	 */
    alert: function (text, callback) {
      var options = {
        className: 'alert-wrap',
        hasCancel: false,
        confirm: callback
      };
      if (typeof text === 'string') {
        options.text = text;
      } else {
        options = $.extend({}, options, text);
      }
      return this.popup(options);
    },
    /**
	 * 展示框
	 * 
	 * @param text
	 * @param callback
	 * @returns {*|{}}
	 */
    hint: function (text, callback) {
      var options = {
        className: 'hint-wrap',
        hasButton: false,
        hasBackdrop: false
      };
      if (typeof text === 'string') {
        options.text = text;
      } else {
        options = $.extend({}, options, text);
      }
      var hint = this.popup(options);
      setTimeout(function () {
        hint.hide(function () {
          callback && callback();
        });
      }, 1500);
      return hint;
    },
    /**
	 * loading动画
	 * 
	 * @return {[type]} [description]
	 */
    loading: function (toggle) {
      toggle = toggle || 'show';
      return this.popup({
        className: 'loading-wrap',
        toggle: toggle,
        text: '<div class="loading"></div>',
        hasButton: false,
        hasBackdrop: false
      });
    },
    /**
	 * 下拉加载更多
	 * 
	 * @param callback
	 * @param options
	 */
    dropDown: function (callback, options) {
      $(window).dropDown(callback, $(document), options);
    },
    /**
	 * 插入数据
	 * 
	 * @param name
	 *            名称
	 * @param value
	 *            值
	 * @param callback
	 *            回调
	 */
    insert: function (name, value, callback) {
      $('body').insert(name, value, callback);
    },
    /**
	 * 获取数据
	 * 
	 * @param name
	 * @returns {*|{}}
	 */
    outsert: function (name) {
      var array = typeof name === 'string' ? (arguments.length >= 2 ? arguments : [name]) : name
          , obj = {};

      array.forEach(function (value) {
        var $this = $('[data-insert="' + name + '"]')
            , type = $this.is('input') ? ($this.is('[type="radio"]') || $this.is('[type="checkbox"]') ? 'checked' : 'val') : 'html';
        obj[value] = $this[type]();
      });
      return typeof name === 'string' ? obj[name] : obj;
    },
    /**
	 * 倒计时
	 * 
	 * @param options
	 * @param callback
	 */
    countdown: function (options, callback) {
      $('body').countdown(options, callback);
    },
    /**
	 * 模版引擎用于渲染页面
	 * 
	 * @param json
	 *            json数据
	 * @param template
	 *            模版
	 * @returns {*}
	 */
    template: function (json, template) {
      var temp = template
          , index = 0
          , oldIndex = 0
          , i = 0
          , code = '\'use strict\';\nvar r = \'\', _this = this;\n'
          , match, length, tem;
        Storm.debug && (code += 'debugger;\n');
        match = temp.match(/{{(.| )+?}}/g);
        length = match.length;
        for (; i < length; i++) {
          index = temp.indexOf(match[i]);
          index <= oldIndex && (index = temp.substring(oldIndex, temp.length - 1).indexOf(match[i]) + oldIndex);
          code += 'r += \'' + temp.substring(oldIndex, index).replace(/\n|\s{2,}/g, ' ') + '\';\n';
          match[i] = match[i].replace(/{{|}}/g, '');
          tem = match[i].split(' ');
          code += /^each/g.test(match[i]) ? ('$.each(' + tem[1] + ', function (' + (tem.length === 3 ? 'index' : tem[3]) + ', ' + tem[2] + ') {') : (/^if/g.test(match[i]) ? 'if (' + (match[i].split('if ')[1]) + ') {' : (/^else if/g.test(match[i]) ? '} else if (' + (match[i].split('else if ')[1]) + ') {' : (/^else/g.test(match[i]) ? '} else {' : (/^\/each/g.test(match[i]) ? '});' : (/^\/(if)/g.test(match[i]) ? '}' : (/^var/g.test(match[i]) ? match[i] : ('r += ' + match[i] + ';')))))));
          code += '\n';
          oldIndex = index + match[i].length + 4;
        }
        code += 'r += \'' + temp.substring(oldIndex, temp.length).replace(/\n|\s{2,}/g, ' ') + '\';\nreturn r;';
        return new Function(code).apply(json);
    }
  };
  // 判断是否为'Arguments', 'Function', 'String', 'Number', 'Date', 'RegExp'类型
  ['Arguments', 'Function', 'String', 'Number', 'Date', 'RegExp'].forEach(function (value) {
    Storm['is' + value] = function (obj) {
      return Object.prototype.toString.call(obj) === '[object ' + value + ']';
    }
    ;
  });
  window.Storm = window.S = Storm;
  var StormJQueryFn = {
    /**
	 * 分页插件
	 * 
	 * @param page
	 *            当前页
	 * @param allPage
	 *            总页数
	 * @param callback
	 *            回调
	 * @returns {boolean} 返回值
	 */
    pagination: function (page, allPage, callback) {
      var options = {};
      if ($.isNumeric(page)) {
        options.page = parseInt(page);
        options.allPage = parseInt(allPage);
        options.success = callback;
      } else {
        options = arguments[0];
      }

      var defaults = {
            origin: 1, // 首页为0|1
            page: 1, // 当前页
            allPage: 10, // 总页数
            isFL: true, // 是否需要首尾页
            firstTxt: '&le;', // 首页文本
            firstClass: 'pagination-first', // 首页class
            lastTxt: '&ge;', // 尾页文本
            lastClass: 'pagination-last', // 尾页class
            isPN: true, // 是否需要上|下一页
            prevTxt: '&laquo;', // 上一页文本
            prevClass: 'pagination-prev', // 上一页class
            nextTxt: '&raquo;', // 下一页文本
            nextClass: 'pagination-next', // 下一页class
            btnNum: 5, // 按钮数量
            btnClass: 'pagination-num', // 按钮class
            activeClass: 'active', // 当前页class
            disableClass: 'disabled', // 禁用class
            success: function () {},
            createBtn: function (className, string) {
              return '<li class="' + className + '"><a href="javascript:;">' + string + '</a></li>';
            }
          },
          html = '',
          _this = this,
          i,
          j,
          $this = $(this),
          o = $.extend({}, defaults, $this.data(), options);

      if (o.allPage === 0) {
        return false;
      }
      if (o.origin === 0) {
        o.page++;
      }

      this.createBtn = function (className, string) {
        html += o.createBtn(className, string);
      };

      if (o.isFL) {
        _this.createBtn(o.firstClass + ' ' + (o.page === 1 ? o.disableClass : ''), o.firstTxt);
      }
      if (o.isPN) {
        _this.createBtn(o.prevClass + ' ' + (o.page === 1 ? o.disableClass : ''), o.prevTxt);
      }

      if (o.page <= Math.ceil(o.btnNum / 2)) {
        for (i = 1; i <= o.allPage && i <= o.btnNum; i++) {
          _this.createBtn((o.page === i ? (o.activeClass + ' ' + o.disableClass) : o.btnClass), i);
        }
      } else if (o.page > Math.ceil(o.btnNum / 2) && o.page < o.allPage - Math.floor(o.btnNum / 2)) {
        for (i = 1, j = o.page - Math.floor(o.btnNum / 2); i <= o.btnNum; i++, j++) {
          _this.createBtn((o.page === j ? (o.activeClass + ' ' + o.disableClass) : o.btnClass), j);
        }
      } else {
        for (i = 1; i <= o.btnNum; i++) {
          if (o.allPage - o.btnNum + i === 0) {
            continue;
          }
          _this.createBtn((o.page === o.allPage - o.btnNum + i ? (o.activeClass + ' ' + o.disableClass) : o.btnClass), (o.allPage - o.btnNum + i));
        }
      }

      if (o.isPN) {
        _this.createBtn(o.nextClass + ' ' + (o.page === o.allPage ? o.disableClass : ''), o.nextTxt);
      }
      if (o.isFL) {
        _this.createBtn(o.lastClass + ' ' + (o.page === o.allPage ? o.disableClass : ''), o.lastTxt);
      }

      $this.html(html);

      this.judge = function (className, number) {
        $this.children('.' + className).click(function () {
          if ($(this).hasClass(o.disableClass)) {
            return;
          }
          _this.success(number);
        });
      };

      this.success = function (number) {
        number = Number(number);
        if (o.origin === 0) {
          o.success(number - 1);
        } else {
          o.success(number);
        }
      };

      _this.judge(o.firstClass, 1);
      _this.judge(o.lastClass, o.allPage);
      _this.judge(o.prevClass, o.page - 1);
      _this.judge(o.nextClass, o.page + 1);

      $this.children('.' + o.btnClass).click(function () {
        _this.success($(this).text());
      });
    },
    /**
	 * 插入文本
	 * 
	 * @param name
	 * @param value
	 * @param callback
	 * @param isName
	 */
    insert: function (name, value, callback, isName) {
      var $this = $(this)
          , obj = {};

      isName = isName || 'data-insert';
      if (typeof name === 'string') {
        obj[name] = value;
      } else {
        obj = name;
        if (typeof value === 'string') {
          isName = value;
        } else {
          callback = value;
        }
      }
      $.each(obj, function (key, value) {
        var $ele = $this.find('[' + isName + '="' + key + '"]'),
            type = $ele.is('input') ? ($ele.is('[type="radio"]') || $ele.is('[type="checkbox"]') ? 'checked' : 'val') : 'html';

        type === 'checked' ? ($this.find('[' + isName + '="' + key + '"][value="' + value + '"]').prop('checked', true)) : $ele[type](value);

      });
      callback && callback();
    },
    /**
	 * 倒计时插件
	 * 
	 * @param options
	 *            选项
	 * @param callback
	 *            回调
	 */
    countdown: function (options, callback) {
      var $this = $(this), arg = Storm.arg(arguments), defaults = {
        countdown: $this.find('[data-countdown="countdown"]'),
        resend: $this.find('[data-countdown="resend"]'),
        $seconds: $this.find('[data-countdown="seconds"]'),
        seconds: 59
      }, timer;
      callback = arg.function;
      options = $.extend({}, defaults, $this.data(), arg.json || arg['array']);
      options.resend.hide();
      options.countdown.show();
      timer = setInterval(function () {
        if (options.seconds === 0) {
          options.resend.show();
          options.countdown.hide();
          options.$seconds.html(60);
          clearInterval(timer);
          callback && callback();
          return true;
        }
        options.$seconds.html(options.seconds);
        options.seconds--;
      }, 1000);
    },
    
    /**
	 * 模态框
	 * 
	 * @param options
	 *            选项
	 * @param callback
	 *            回调
	 * @returns {{}}
	 */
    modal: function (options, callback) {
      var $this = $(this)
          , $body = $('body')
          , _this = {}
          , defaults = {
        toggle: 'toggle'
      }
          , o = {};
      if (typeof options === 'string') {
        o = $.extend(defaults, $this.data(), {
          toggle: options
        });
      } else {
        o = $.extend(defaults, $this.data(), options);
      }
      o.callback = function (method) {
        callback && callback(method);
      }
      ;
      _this.show = function () {
        $this.show().scrollTop(0);
        $this.addClass('active').removeClass('disabled');
        $body.addClass('modal-body-hidden');
        o.callback('show');
      }
      ;
      _this.hide = function () {
        $this.addClass('disabled').removeClass('active');
        (!callback || o.callback('show')) && $this.transition(function () {
          $this.hide();
          $body.removeClass('modal-body-hidden');
        });
      }
      ;
      _this.toggle = function () {
        $this.is(':hidden') ? _this.show() : _this.hide();
      }
      ;

      $this.on('click', '[data-modal="confirm"]', function () {
        $(this).trigger('Storm.modal.confirm');
      });
      $this.on('click', '[data-modal="cancel"]', function () {
        $(this).trigger('Storm.modal.cancel');
      });
      $this.on('Storm.modal.show', _this.show);
      $this.on('Storm.modal.hide', _this.hide);
      $this.on('Storm.modal.toggle', _this.toggle);
      _this[o.toggle]();
      return _this;
    },
    /**
	 * 设置|获取 过渡过渡时间
	 * 
	 * @param {[type]}
	 *            value [description]
	 * @return {[type]} [description]
	 */
    tranDur: function (value) {
      var $this = $(this);
      return value === undefined ? Storm.s2ms($this.css('transition-duration')) : $this.css('transition-duration', value) && $this;
    },
    /**
	 * 设置|获取 动画的动画时间
	 * 
	 * @param {[type]}
	 *            value [description]
	 * @return {[type]} [description]
	 */
    animDur: function (value) {
      var $this = $(this);
      return value === undefined ? Storm.s2ms($this.css('animation-duration')) : $this.css('animation-duration', value) && $this;
    },
    /**
	 * 下拉加载
	 * 
	 * @param callback
	 *            回调
	 * @param $content
	 *            内容元素
	 * @param options
	 */
    dropDown: function (callback, $content, options) {
      var $this = $(this)
          , defaults = {
        range: 50
      };
      options = $.extend({}, defaults, $this.data, options);
      $this.scroll(function (e) {
        var target = e.delegateTarget;
        target.scrollY + target.innerHeight >= $content['outerHeight'](true) - options.range && callback();
      });
    },
    /**
	 * 动画结束回调
	 * 
	 * @param callback
	 *            回调
	 * @param duration
	 *            动画时长
	 * @returns {*}
	 */
    transition: function (callback, duration) {
      var isCalled = false
          , $this = $(this);
      callback = callback || function () {
          }
      ;
      duration = !$.isNumeric(duration) && (!duration ? $this.tranDur() : $this.animDur());
      if (!Storm.transitionEnd) {
        setTimeout(callback, duration);
        return false;
      }
      function goCallback() {
        if (isCalled) {
          return false;
        }
        isCalled = !isCalled;
        callback();
      }

      $(this).on(Storm.transitionEnd, goCallback);
      setTimeout(goCallback, duration + 10);
      return this;
    },
    /**
	 * 按钮禁止点击
	 * 
	 * @param options
	 * @returns {jQuery|HTMLElement}
	 */
    button: function (options) {
      var $this = $(this)
          , _this = this
          , defaults = {
        toggle: 'toggle',
        disabled: 'disabled',
        loadText: null
      }
          , type = $this.is('input') ? 'val' : 'html'
          , o = $.extend({}, defaults, $this.data());
      $.type(options) === 'string' ? o.toggle = options : o = $.extend({}, o, options);
      this.disable = function () {
        o.loadText && $this.data('text', $this[type]())[type](o.loadText);
        $this.prop('disabled', true).addClass(o.disabled);
        type === 'html' && $this.attr('disabled', 'disabled');
      }
      ;
      this.useable = function () {
        o.loadText && $this[type]($this.data('text'));
        $this.prop('disabled', false).removeClass(o.disabled);
        type === 'html' && $this.removeAttr('disabled');
      }
      ;
      this.toggle = function () {
        $this.is(':disabled') ? _this.useable() : _this.disable();
      }
      ;
      _this[o.toggle]();
      return $this;
    },
    /**
	 * 获取form表单数据
	 * 
	 * @returns {{}}
	 */
    formData: function () {
      var json = {};
      $.each($(this).serializeArray(), function (key, value) {
        json[value.name] = value.value;
      });
      return json;
    },
    /**
	 * 使用button触发post方法
	 * 
	 * @param request
	 *            请求地址
	 * @param data
	 *            请求数据
	 * @param callback
	 *            成功回调
	 * @param errorCallback
	 *            错误回调
	 */
    postBtn: function (request, data, callback, errorCallback) {
      var $this = $(this);
      if ($this.attr('disabled') === 'disabled') {
        return false;
      }
      $this.button('disable');
      return Storm.post(request, data, callback, function (msg, code, data) {
        $this.button('useable');
        errorCallback ? errorCallback(msg, code, data) : Storm.hint(msg);
      }).done(function () {
        $this.button('useable');
      }).error(function (xhr, status) {
        $this.button('useable');
        if (xhr.status === 403) {
    		  return false;
    	   }
        errorCallback ? errorCallback(xhr, status) : Storm.hint('\u7F51\u7EDC\u9519\u8BEF, \u8BF7\u91CD\u8BD5!');
      });
    },
    /**
	 * tab切换
	 * 
	 * @param options
	 * @returns {type[]|*}
	 */
    tab: function (options) {
      return this.each(function () {
        var $this = $(this)
            , _this = {}
            , defaults = {
          effect: 'fade',
          nav: $this.find('[data-tab="nav"]'),
          item: $this.find('[data-tab="item"]'),
          content: $this.find('[data-tab="content"]'),
          panel: $this.find('[data-tab="panel"]')
        }
            , o = $.extend(defaults, $this.data(), options);
        o.item.click(function () {
          $(this).trigger('Storm.tab.item');
        });
        o.panel.click(function () {
          $(this).trigger('Storm.tab.panel');
        });
        o.panel.on('Storm.tab.panel', function () {
          _this[o.effect]($(this).index());
        });
        o.item.on('Storm.tab.item', function () {
          _this[o.effect]($(this).index());
        });
        _this.fade = function (index) {
          o.item.removeClass('active').eq(index).addClass('active');
          o.panel.hide().removeClass('active').eq(index).show().scrollTop(0).addClass('active');
        }
        ;
      });
    },

    /**
	 * 表单验证
	 * 
	 * @param o
	 */
    validate: function (o) {
      var $this = this, // 表单
          elem = {}, // 元素属性
          _this = {}, // 方法集合
          defaults = { // 默认参数
            pattern: '\u8BF7\u8F93\u5165\u6709\u6548\u7684{{chinese}}', // 请输入有效的{{chinese}}
            required: '\u8BF7\u8F93\u5165{{chinese}}', // 请输入{{chinese}}
            selectRequired: '\u8BF7\u9009\u62E9{{chinese}}', // 请选择{{chinese}}
            minlength: '{{chinese}}\u5FC5\u9700\u5927\u4E8E{{minlength}}\u4E2A\u5B57\u7B26', // {{chinese}}必需大于{{minLength}}个字符
            firstspace: '{{chinese}}\u7B2C\u4E00\u4E2A\u5B57\u7B26\u4E0D\u80FD\u4E3A\u7A7A\u683C', // {{chinese}}第一个字符不能为空格
            lastspace: '{{chinese}}\u6700\u540E\u4E00\u4E2A\u5B57\u7B26\u4E0D\u80FD\u4E3A\u7A7A\u683C', // {{chinese}}最后一个字符不能为空格
            specialchar: '{{chinese}}\u4E0D\u80FD\u8F93\u5165\u7279\u6B8A\u5B57\u7B26', // {{chinese}}不能输入特殊字符
            spacechar: '{{chinese}}\u4E0D\u80FD\u8F93\u5165\u7A7A\u683C', // {{chinese}}不能输入空格
            flspace: '{{chinese}}\u9996\u5C3E\u5B57\u7B26\u4E0D\u80FD\u4E3A\u7A7A\u683C', // {{chinese}}首尾字符不能为空格
            specialcharReg: Storm.regExp.special, // 特殊字符正则
            arrtribtue: ['required', 'minlength', 'pattern', 'firstspace', 'lastspace', 'spacechar', 'specialchar'], // 要验证的属性,
																														// 验证顺序
            customAttr: [], // 用户自定义验证
            customAttrFn: {}, // 用户自定义验证方法
            isValidateAll: false, // 是否验证所有
            result: true, // 结果
            complete: function () {
            }, // 完成之后的执行方法
            error: function (msg) {
              S.hint(msg);
            }, // 错误方法
            success: function () {
            }, // 正确方法
            everyError: function () {
            }, // 每个元素错误的执行方法
            everySuccess: function () {
            } // 每个元素成功的执行方法
          },
          $names = $this.find('[name]');// 表单元素集合!

      if (typeof o === 'function') {
        o = $.extend({}, defaults, $this.data(), {
          success: o
        });
      } else {
        o = $.extend({}, defaults, $this.data(), o);// 合并参数
      }
      o.arrtribtue = o.arrtribtue.concat(o.customAttr);// 合并验证属性

      /**
		 * 遍历表单元素
		 */
      _this.each = function () {
        var result = true; // 定义一个成功返回值
        elem = {}; // 初始化元素属性
        $names.each(function () { // 开始遍历
          var $elem = $(this), // 元素本身
              name = $elem.attr('name'), // 元素名称
              type = $elem.attr('type'); // 元素类型

          // ///////////////////////////////////////
          // 是否存在name属性: 没有则不是表单元素
          // 是否存在于元素列表中: 避免radio|checkbox多次遍历
          // 是否为隐藏元素: 避免该元素为隐藏元素
          // ///////////////////////////////////////
          if (!name || name in elem || $elem.is(':hidden')) {
            return true;
          }

          // 获取元素属性
          elem[name] = {
            element: $elem, // 元素本身
            name: name, // 元素名称
            value: $elem.val(), // 元素值
            tagName: $elem[0].tagName.toLowerCase(), // 元素标签名称
            chinese: $elem.data('chinese'), // 元素中文名称
            pattern: $elem.attr('pattern'), // 元素正则
            type: type, // 元素类型
            required: $elem.attr('required'), // 元素必填
            minlength: parseInt($elem.attr('minlength')), // 元素最小长度
            placeholder: $elem.attr('placeholder'), // 占位符
            spacechar: _this.defaultVal($elem.attr('spacechar')), // 不允许有空格
            firstspace: _this.defaultVal($elem.attr('firstspace')), // 首字符为空,
																	// 默认为true
            lastspace: _this.defaultVal($elem.attr('lastspace')), // 尾字符为空,
																	// 默认为true
            specialchar: _this.defaultVal($elem.attr('specialchar')) // 特殊字符,
																		// 默认为true
          };

          // 是否为radio|checkbox
          (type === 'radio' || type === 'checkbox') && (elem[name].value = $this.find('[name="' + elem[name].name + '"][type="' + type + '"]:checked').val());

          // 验证是否通过规则
          if (!_this.validate(elem[name])) { // 否

            // 每次错误的执行方法
            o.everyError(elem[name]);

            // 表单元素是否有自定义错误方法
            name in o && 'error' in o[name] && o[name].error(elem[name]);

            // 判断是否验证所有
            if (!o.isValidateAll) {
              o.result = false;
              return false;
            } else {
              o.result = false;
            }
          }

          // 每个元素成功之后的执行方法
          o.everySuccess(elem[name]);

          // 表单元素是否有自定义成功方法
          name in o && 'success' in o[name] && o[name].success(elem[name]);

        });

        // 判断结果
        o.result ? o.success(result, elem) : o.complete(elem);
      };

      /**
		 * 特殊属性默认值
		 * 
		 * @param value
		 * @returns {boolean}
		 */
      _this.defaultVal = function (value) {
        return typeof value === 'undefined' ? true : !!value;
      };

      /**
		 * 验证失败执行的方法
		 * 
		 * @param type
		 * @param obj
		 */
      _this.error = function (type, obj) {
        o.error(o[obj.name] && o[obj.name][type] ? o[obj.name][type]
            : ((obj.type === 'radio' || obj.type === 'checkbox' || obj.tagName === 'select') && type === 'required' ? Storm.replTemp(obj, o.selectRequired)
            : Storm.replTemp(obj, o[type])));
      };

      /**
		 * 验证是否符合规则
		 * 
		 * @param obj
		 * @returns {boolean}
		 */
      _this.validate = function (obj) {

        var method = o[obj.name], // 获取用户定义验证方法
            len = o.arrtribtue.length, // 数组长度, 用于循环
            i = 0; // 计数器, 用于循环

        for (; i < len; i++) {
          if (!_this[o.arrtribtue[i]](obj)) {
            _this.error(o.arrtribtue[i], obj);
            return false;
          }

        }
        return !(method && 'custom' in method && !method.custom(obj));
      };

      /**
		 * 验证是否允许空格
		 * 
		 * @param obj
		 * @returns {boolean}
		 */
      _this.spacechar = function (obj) {
    	  if (obj.required || obj.value) {
    		  return !(!obj.spacechar && Storm.regExp.space.test(obj.value));
    	  }
    	  return true;
      };

      /**
		 * 验证是否允许特殊字符
		 * 
		 * @param obj
		 *            元素属性
		 * @returns {boolean}
		 */
      _this.specialchar = function (obj) {
    	  if (obj.required || obj.value) {
    		  return !(!obj.specialchar && o.specialcharReg.test(obj.value));
    	  }
    	  return true;
      };

      /**
		 * 验证是否允许尾字符为空格
		 * 
		 * @param obj
		 *            元素属性
		 * @returns {boolean}
		 */
      _this.lastspace = function (obj) {
    	  if (obj.required || obj.value) {
    		  return !(!obj.lastspace && obj.value[obj.value.length - 1] === ' ');
    	  }
    	  return true;
      };

      /**
		 * 验证是否允许首字符为空格
		 * 
		 * @param obj
		 * @returns {boolean}
		 */
      _this.firstspace = function (obj) {
    	  if (obj.required || obj.value) {
    		  return !(!obj.firstspace && obj.value[0] === ' ');
    	  }
    	  return true;
        
      };

      /**
		 * 验证属性是否符合正则
		 * 
		 * @param obj
		 * @returns {boolean}
		 */
      _this.pattern = function (obj) {
        var pattern;

        if (obj.pattern && (obj.required || obj.value)) {
          pattern = obj.pattern in Storm.regExp ? Storm.regExp[obj.pattern] : new RegExp('^' + obj.pattern + '$');
          if (!pattern.test(obj.value)) {
        	  return false;
          }
        }
        return true;
      };

      /**
		 * 验证表单元素是否符合最小长度
		 * 
		 * @param obj
		 * @returns {boolean}
		 */
      _this.minlength = function (obj) {
    	  if (obj.required || obj.value) {
    		  return !(obj.minlength && obj.value.length < obj.minlength);
    	  }
    	  return true;
      };

      /**
		 * 验证表单元素是否为必填
		 * 
		 * @param obj
		 * @returns {boolean}
		 */
      _this.required = function (obj) {
        return !(obj.required && !obj.value && (!obj.placeholder || obj.value !== obj.placeholder));
      };

      // 合并用户自定义验证方法
      _this = $.extend({}, _this, o.customAttrFn);
      
      _this.each();
    }
  };
  /**
	 * Storm初始化
	 */
  Storm.init = function ($) {
    jQuery.fn.extend(StormJQueryFn);
    // tab
    $('[data-tab="tab"]').tab();
    $(document).on('click', '[data-event]', function () {
      var data = $(this).data();
      $(data.target).trigger(data.event);
    });
    $(document).on('click', '[data-modal]', function () {
      var data = $(this).data();
      $(data.modal).modal(data.toggle);
    });
  };
  // 模块化写法
  if (typeof define === 'function' && define.amd) {
    define("storm", ['config', 'jquery'], function (config, jQuery) {
      CONFIG = config;
      Storm.init(jQuery);
      return Storm;
    });
  } else {
    Storm.init($);
  }
}());
