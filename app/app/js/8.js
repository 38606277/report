webpackJsonp([8],{204:function(e,t,n){"use strict";function r(e){return e&&e.__esModule?e:{default:e}}function o(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function a(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function c(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}Object.defineProperty(t,"__esModule",{value:!0});var l=n(84),u=r(l),i=n(89),f=r(i),s=n(90),p=r(s),d=n(81),b=r(d),h=n(54),m=r(h),y=n(213),x=r(y),k=n(80),g=r(k),v=function(){function e(e,t){for(var n=0;n<t.length;n++){var r=t[n];r.enumerable=r.enumerable||!1,r.configurable=!0,"value"in r&&(r.writable=!0),Object.defineProperty(e,r.key,r)}}return function(t,n,r){return n&&e(t.prototype,n),r&&e(t,r),t}}();n(85),n(92),n(93),n(82),n(55),n(227),n(53);var O=n(0),_=r(O);n(83);var j=g.default.Item,C=(x.default.CheckboxItem,x.default.AgreeItem,function(e){function t(){return o(this,t),a(this,(t.__proto__||Object.getPrototypeOf(t)).call(this))}return c(t,e),v(t,[{key:"onSubmit",value:function(){}},{key:"onReset",value:function(){this.props.form.resetFields()}},{key:"onOpenChange",value:function(){}},{key:"render",value:function(){return _.default.createElement("div",null,_.default.createElement(b.default,{mode:"light",icon:_.default.createElement(m.default,{type:"left"}),onLeftClick:function(){return window.location.href="#/"}},"缴费"),_.default.createElement(g.default,{renderFooter:function(){return"户号为7位数字"}},_.default.createElement(p.default,{type:"text",placeholder:"CHX1234567",clear:!0},"编号"),_.default.createElement(p.default,{type:"text",defaultValue:"",placeholder:"张三丰",clear:!0},"用户名称"),_.default.createElement(p.default,{type:"text",defaultValue:"",placeholder:"解放大路120号",clear:!0},"地址"),_.default.createElement(p.default,{type:"text",defaultValue:"",placeholder:"22吨",clear:!0},"本月数量"),_.default.createElement(p.default,{type:"text",defaultValue:"",placeholder:"230",clear:!0},"应缴金额"),_.default.createElement(j,null,_.default.createElement(f.default,{type:"primary"}," 确定"),_.default.createElement(u.default,null))))}}]),t}(_.default.Component));t.default=C},210:function(e,t,n){"use strict";function r(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(t,"__esModule",{value:!0});var o=n(3),a=r(o),c=n(6),l=r(c),u=n(4),i=r(u),f=n(5),s=r(f),p=n(9),d=r(p),b=n(215),h=r(b),m=n(0),y=r(m),x=function(e,t){var n={};for(var r in e)Object.prototype.hasOwnProperty.call(e,r)&&t.indexOf(r)<0&&(n[r]=e[r]);if(null!=e&&"function"==typeof Object.getOwnPropertySymbols)for(var o=0,r=Object.getOwnPropertySymbols(e);o<r.length;o++)t.indexOf(r[o])<0&&(n[r[o]]=e[r[o]]);return n},k=function(e){function t(){return(0,a.default)(this,t),(0,i.default)(this,(t.__proto__||Object.getPrototypeOf(t)).apply(this,arguments))}return(0,s.default)(t,e),(0,l.default)(t,[{key:"render",value:function(){var e=this.props,t=e.className,n=e.style,r=x(e,["className","style"]),o=r.prefixCls,a=r.children,c=(0,d.default)(o+"-wrapper",t);"class"in r&&delete r.class;var l=y.default.createElement("label",{className:c,style:n},y.default.createElement(h.default,r),a);return this.props.wrapLabel?l:y.default.createElement(h.default,this.props)}}]),t}(y.default.Component);t.default=k,k.defaultProps={prefixCls:"am-checkbox",wrapLabel:!0},e.exports=t.default},211:function(e,t,n){"use strict";t.__esModule=!0,t.default=function(e,t){var n={};for(var r in e)t.indexOf(r)>=0||Object.prototype.hasOwnProperty.call(e,r)&&(n[r]=e[r]);return n}},212:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default=function(e){return Object.keys(e).reduce(function(t,n){return"aria-"!==n.substr(0,5)&&"data-"!==n.substr(0,5)&&"role"!==n||(t[n]=e[n]),t},{})},e.exports=t.default},213:function(e,t,n){"use strict";function r(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(t,"__esModule",{value:!0});var o=n(214),a=r(o),c=n(210),l=r(c),u=n(226),i=r(u);l.default.CheckboxItem=i.default,l.default.AgreeItem=a.default,t.default=l.default,e.exports=t.default},214:function(e,t,n){"use strict";function r(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(t,"__esModule",{value:!0});var o=n(8),a=r(o),c=n(3),l=r(c),u=n(6),i=r(u),f=n(4),s=r(f),p=n(5),d=r(p),b=n(9),h=r(b),m=n(0),y=r(m),x=n(212),k=r(x),g=n(210),v=r(g),O=function(e,t){var n={};for(var r in e)Object.prototype.hasOwnProperty.call(e,r)&&t.indexOf(r)<0&&(n[r]=e[r]);if(null!=e&&"function"==typeof Object.getOwnPropertySymbols)for(var o=0,r=Object.getOwnPropertySymbols(e);o<r.length;o++)t.indexOf(r[o])<0&&(n[r[o]]=e[r[o]]);return n},_=function(e){function t(){return(0,l.default)(this,t),(0,s.default)(this,(t.__proto__||Object.getPrototypeOf(t)).apply(this,arguments))}return(0,d.default)(t,e),(0,i.default)(t,[{key:"render",value:function(){var e=this.props,t=e.style,n=O(e,["style"]),r=n.prefixCls,o=n.className,c=(0,h.default)(r+"-agree",o);return y.default.createElement("div",(0,a.default)({},(0,k.default)(n),{className:c,style:t}),y.default.createElement(v.default,(0,a.default)({},n,{className:r+"-agree-label"})))}}]),t}(y.default.Component);t.default=_,_.defaultProps={prefixCls:"am-checkbox"},e.exports=t.default},215:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r=n(216);n.d(t,"default",function(){return r.a})},216:function(e,t,n){"use strict";var r=n(8),o=n.n(r),a=n(32),c=n.n(a),l=n(211),u=n.n(l),i=n(3),f=n.n(i),s=n(6),p=n.n(s),d=n(4),b=n.n(d),h=n(5),m=n.n(h),y=n(0),x=n.n(y),k=n(217),g=n.n(k),v=n(220),O=n.n(v),_=n(9),j=n.n(_),C=function(e){function t(e){f()(this,t);var n=b()(this,(t.__proto__||Object.getPrototypeOf(t)).call(this,e));P.call(n);var r="checked"in e?e.checked:e.defaultChecked;return n.state={checked:r},n}return m()(t,e),p()(t,[{key:"componentWillReceiveProps",value:function(e){"checked"in e&&this.setState({checked:e.checked})}},{key:"shouldComponentUpdate",value:function(){for(var e=arguments.length,t=Array(e),n=0;n<e;n++)t[n]=arguments[n];return O.a.shouldComponentUpdate.apply(this,t)}},{key:"render",value:function(){var e,t=this.props,n=t.prefixCls,r=t.className,a=t.style,l=t.name,i=t.type,f=t.disabled,s=t.readOnly,p=t.tabIndex,d=t.onClick,b=t.onFocus,h=t.onBlur,m=u()(t,["prefixCls","className","style","name","type","disabled","readOnly","tabIndex","onClick","onFocus","onBlur"]),y=Object.keys(m).reduce(function(e,t){return"aria-"!==t.substr(0,5)&&"data-"!==t.substr(0,5)&&"role"!==t||(e[t]=m[t]),e},{}),k=this.state.checked,g=j()(n,r,(e={},c()(e,n+"-checked",k),c()(e,n+"-disabled",f),e));return x.a.createElement("span",{className:g,style:a},x.a.createElement("input",o()({name:l,type:i,readOnly:s,disabled:f,tabIndex:p,className:n+"-input",checked:!!k,onClick:d,onFocus:b,onBlur:h,onChange:this.handleChange},y)),x.a.createElement("span",{className:n+"-inner"}))}}]),t}(x.a.Component);C.propTypes={prefixCls:g.a.string,className:g.a.string,style:g.a.object,name:g.a.string,type:g.a.string,defaultChecked:g.a.oneOfType([g.a.number,g.a.bool]),checked:g.a.oneOfType([g.a.number,g.a.bool]),disabled:g.a.bool,onFocus:g.a.func,onBlur:g.a.func,onChange:g.a.func,onClick:g.a.func,tabIndex:g.a.string,readOnly:g.a.bool},C.defaultProps={prefixCls:"rc-checkbox",className:"",style:{},type:"checkbox",defaultChecked:!1,onFocus:function(){},onBlur:function(){},onChange:function(){}};var P=function(){var e=this;this.handleChange=function(t){var n=e.props;n.disabled||("checked"in n||e.setState({checked:t.target.checked}),n.onChange({target:o()({},n,{checked:t.target.checked}),stopPropagation:function(){t.stopPropagation()},preventDefault:function(){t.preventDefault()}}))}};t.a=C},217:function(e,t,n){e.exports=n(218)()},218:function(e,t,n){"use strict";function r(){}var o=n(219);e.exports=function(){function e(e,t,n,r,a,c){if(c!==o){var l=new Error("Calling PropTypes validators directly is not supported by the `prop-types` package. Use PropTypes.checkPropTypes() to call them. Read more at http://fb.me/use-check-prop-types");throw l.name="Invariant Violation",l}}function t(){return e}e.isRequired=e;var n={array:e,bool:e,func:e,number:e,object:e,string:e,symbol:e,any:e,arrayOf:t,element:e,instanceOf:t,node:e,objectOf:t,oneOf:t,oneOfType:t,shape:t,exact:t};return n.checkPropTypes=r,n.PropTypes=n,n}},219:function(e,t,n){"use strict";e.exports="SECRET_DO_NOT_PASS_THIS_OR_YOU_WILL_BE_FIRED"},220:function(e,t,n){function r(e,t,n){return!o(e.props,t)||!o(e.state,n)}var o=n(221),a={shouldComponentUpdate:function(e,t){return r(this,e,t)}};e.exports=a},221:function(e,t,n){"use strict";var r=n(222);e.exports=function(e,t,n,o){var a=n?n.call(o,e,t):void 0;if(void 0!==a)return!!a;if(e===t)return!0;if("object"!=typeof e||null===e||"object"!=typeof t||null===t)return!1;var c=r(e),l=r(t),u=c.length;if(u!==l.length)return!1;o=o||null;for(var i=Object.prototype.hasOwnProperty.bind(t),f=0;f<u;f++){var s=c[f];if(!i(s))return!1;var p=e[s],d=t[s],b=n?n.call(o,p,d,s):void 0;if(!1===b||void 0===b&&p!==d)return!1}return!0}},222:function(e,t,n){function r(e){return null!=e&&a(y(e))}function o(e,t){return e="number"==typeof e||p.test(e)?+e:-1,t=null==t?m:t,e>-1&&e%1==0&&e<t}function a(e){return"number"==typeof e&&e>-1&&e%1==0&&e<=m}function c(e){for(var t=u(e),n=t.length,r=n&&e.length,c=!!r&&a(r)&&(s(e)||f(e)),l=-1,i=[];++l<n;){var p=t[l];(c&&o(p,r)||b.call(e,p))&&i.push(p)}return i}function l(e){var t=typeof e;return!!e&&("object"==t||"function"==t)}function u(e){if(null==e)return[];l(e)||(e=Object(e));var t=e.length;t=t&&a(t)&&(s(e)||f(e))&&t||0;for(var n=e.constructor,r=-1,c="function"==typeof n&&n.prototype===e,u=Array(t),i=t>0;++r<t;)u[r]=r+"";for(var p in e)i&&o(p,t)||"constructor"==p&&(c||!b.call(e,p))||u.push(p);return u}var i=n(223),f=n(224),s=n(225),p=/^\d+$/,d=Object.prototype,b=d.hasOwnProperty,h=i(Object,"keys"),m=9007199254740991,y=function(e){return function(t){return null==t?void 0:t[e]}}("length"),x=h?function(e){var t=null==e?void 0:e.constructor;return"function"==typeof t&&t.prototype===e||"function"!=typeof e&&r(e)?c(e):l(e)?h(e):[]}:c;e.exports=x},223:function(e,t){function n(e){return!!e&&"object"==typeof e}function r(e,t){var n=null==e?void 0:e[t];return c(n)?n:void 0}function o(e){return a(e)&&p.call(e)==l}function a(e){var t=typeof e;return!!e&&("object"==t||"function"==t)}function c(e){return null!=e&&(o(e)?d.test(f.call(e)):n(e)&&u.test(e))}var l="[object Function]",u=/^\[object .+?Constructor\]$/,i=Object.prototype,f=Function.prototype.toString,s=i.hasOwnProperty,p=i.toString,d=RegExp("^"+f.call(s).replace(/[\\^$.*+?()[\]{}|]/g,"\\$&").replace(/hasOwnProperty|(function).*?(?=\\\()| for .+?(?=\\\])/g,"$1.*?")+"$");e.exports=r},224:function(e,t){function n(e){return o(e)&&b.call(e,"callee")&&(!m.call(e,"callee")||h.call(e)==f)}function r(e){return null!=e&&c(e.length)&&!a(e)}function o(e){return u(e)&&r(e)}function a(e){var t=l(e)?h.call(e):"";return t==s||t==p}function c(e){return"number"==typeof e&&e>-1&&e%1==0&&e<=i}function l(e){var t=typeof e;return!!e&&("object"==t||"function"==t)}function u(e){return!!e&&"object"==typeof e}var i=9007199254740991,f="[object Arguments]",s="[object Function]",p="[object GeneratorFunction]",d=Object.prototype,b=d.hasOwnProperty,h=d.toString,m=d.propertyIsEnumerable;e.exports=n},225:function(e,t){function n(e){return!!e&&"object"==typeof e}function r(e){return"number"==typeof e&&e>-1&&e%1==0&&e<=h}function o(e){return a(e)&&p.call(e)==l}function a(e){var t=typeof e;return!!e&&("object"==t||"function"==t)}function c(e){return null!=e&&(o(e)?d.test(f.call(e)):n(e)&&u.test(e))}var l="[object Function]",u=/^\[object .+?Constructor\]$/,i=Object.prototype,f=Function.prototype.toString,s=i.hasOwnProperty,p=i.toString,d=RegExp("^"+f.call(s).replace(/[\\^$.*+?()[\]{}|]/g,"\\$&").replace(/hasOwnProperty|(function).*?(?=\\\()| for .+?(?=\\\])/g,"$1.*?")+"$"),b=function(e,t){var n=null==e?void 0:e[t];return c(n)?n:void 0}(Array,"isArray"),h=9007199254740991,m=b||function(e){return n(e)&&r(e.length)&&"[object Array]"==p.call(e)};e.exports=m},226:function(e,t,n){"use strict";function r(e){return e&&e.__esModule?e:{default:e}}function o(){}Object.defineProperty(t,"__esModule",{value:!0});var a=n(8),c=r(a),l=n(32),u=r(l),i=n(3),f=r(i),s=n(6),p=r(s),d=n(4),b=r(d),h=n(5),m=r(h),y=n(9),x=r(y),k=n(0),g=r(k),v=n(80),O=r(v),_=n(210),j=r(_),C=function(e,t){var n={};for(var r in e)Object.prototype.hasOwnProperty.call(e,r)&&t.indexOf(r)<0&&(n[r]=e[r]);if(null!=e&&"function"==typeof Object.getOwnPropertySymbols)for(var o=0,r=Object.getOwnPropertySymbols(e);o<r.length;o++)t.indexOf(r[o])<0&&(n[r[o]]=e[r[o]]);return n},P=O.default.Item,w=function(e){function t(){return(0,f.default)(this,t),(0,b.default)(this,(t.__proto__||Object.getPrototypeOf(t)).apply(this,arguments))}return(0,m.default)(t,e),(0,p.default)(t,[{key:"render",value:function(){var e=this,t=this.props,n=t.listPrefixCls,r=(t.onChange,t.disabled),a=t.checkboxProps,l=t.onClick,i=C(t,["listPrefixCls","onChange","disabled","checkboxProps","onClick"]),f=i.prefixCls,s=i.className,p=i.children,d=(0,x.default)(f+"-item",s,(0,u.default)({},f+"-item-disabled",!0===r));r||(i.onClick=l||o);var b={};return["name","defaultChecked","checked","onChange","disabled"].forEach(function(t){t in e.props&&(b[t]=e.props[t])}),g.default.createElement(P,(0,c.default)({},i,{prefixCls:n,className:d,thumb:g.default.createElement(j.default,(0,c.default)({},a,b))}),p)}}]),t}(g.default.Component);t.default=w,w.defaultProps={prefixCls:"am-checkbox",listPrefixCls:"am-list",checkboxProps:{}},e.exports=t.default},227:function(e,t,n){"use strict";n(20),n(53),n(228)},228:function(e,t,n){var r=n(229);"string"==typeof r&&(r=[[e.i,r,""]]);var o={hmr:!0};o.transform=void 0;n(200)(r,o);r.locals&&(e.exports=r.locals)},229:function(e,t,n){t=e.exports=n(199)(!1),t.push([e.i,'.am-checkbox{position:relative;display:inline-block;vertical-align:middle;width:21px;height:21px}.am-checkbox-inner{position:absolute;right:0;width:21px;height:21px;border:1px solid #ccc;border-radius:50%;-webkit-transform:rotate(0deg);-ms-transform:rotate(0deg);transform:rotate(0deg);-webkit-box-sizing:border-box;box-sizing:border-box}.am-checkbox-inner:after{position:absolute;display:none;top:1.5px;right:6px;z-index:999;width:5px;height:11px;border-style:solid;border-width:0 1px 1px 0;content:" ";-webkit-transform:rotate(45deg);-ms-transform:rotate(45deg);transform:rotate(45deg)}.am-checkbox-input{position:absolute;top:0;left:0;opacity:0;width:100%;height:100%;z-index:2;border:0 none;-webkit-appearance:none;-moz-appearance:none;appearance:none}.am-checkbox.am-checkbox-checked .am-checkbox-inner{border-color:#108ee9;background:#108ee9}.am-checkbox.am-checkbox-checked .am-checkbox-inner:after{display:block;border-color:#fff}.am-checkbox.am-checkbox-disabled{opacity:.3}.am-checkbox.am-checkbox-disabled.am-checkbox-checked .am-checkbox-inner{border-color:#888;background:none}.am-checkbox.am-checkbox-disabled.am-checkbox-checked .am-checkbox-inner:after{border-color:#888}.am-list .am-list-item.am-checkbox-item .am-list-thumb{width:21px;height:21px}.am-list .am-list-item.am-checkbox-item .am-list-thumb .am-checkbox{position:absolute;top:0;left:0;right:0;bottom:0;width:100%;height:44px}.am-list .am-list-item.am-checkbox-item .am-list-thumb .am-checkbox-inner{left:15px;top:12px}.am-list .am-list-item.am-checkbox-item.am-checkbox-item-disabled .am-list-content{color:#bbb}.am-checkbox-agree{position:relative;display:-webkit-box;display:-webkit-flex;display:-ms-flexbox;display:flex;-webkit-box-align:stretch;-webkit-align-items:stretch;-ms-flex-align:stretch;align-items:stretch;margin-left:15px;padding-top:9px;padding-bottom:9px}.am-checkbox-agree .am-checkbox{position:absolute;left:0;top:0;width:30px;height:100%}.am-checkbox-agree .am-checkbox-inner{left:0;top:12px}.am-checkbox-agree .am-checkbox-agree-label{display:inline-block;font-size:15px;color:#000;line-height:1.5;margin-left:30px;margin-top:1px}',""])}});