webpackJsonp([10],{1566:function(e,t,n){var r=n(1567);"string"==typeof r&&(r=[[e.i,r,""]]);var a={hmr:!0};a.transform=void 0;n(400)(r,a);r.locals&&(e.exports=r.locals)},1567:function(e,t,n){t=e.exports=n(399)(!1),t.push([e.i,"body{font-family:Open Sans,sans-serif;background:#f3f3f3}#wrapper{width:100%}.top-navbar{position:absolute;width:100%;z-index:300;-webkit-box-shadow:0 3px 3px rgba(0,0,0,.05);-moz-box-shadow:0 3px 3px rgba(0,0,0,.05);box-shadow:0 3px 3px rgba(0,0,0,.05)}.navbar-side{z-index:1;position:absolute;top:50px;bottom:0;background:#ececec}#page-wrapperNew{margin-left:270px;position:relative;top:55px}.page-header{padding-bottom:9px;margin:10px 0 20px;border-bottom:1px solid transparent}.user-image{margin:25px auto;-webkit-border-radius:10px;-moz-border-radius:10px;border-radius:10px;max-height:170px;max-width:170px}.top-navbar{margin:0}.navbar-side{border:none}.top-navbar{background:#fff;border-bottom:none}@media(max-width:768px){.top-navbar{position:relative}.top-navbar .navbar-brand{width:100%}.navbar-side{z-index:1;width:100%;position:relative;top:0}#page-wrapperNew{margin:0;top:0}}",""])},401:function(e,t,n){"use strict";function r(e){return e&&e.__esModule?e:{default:e}}function a(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function o(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function i(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var p=function(){function e(e,t){for(var n=0;n<t.length;n++){var r=t[n];r.enumerable=r.enumerable||!1,r.configurable=!0,"value"in r&&(r.writable=!0),Object.defineProperty(e,r.key,r)}}return function(t,n,r){return n&&e(t.prototype,n),r&&e(t,r),t}}(),l=n(10),d=r(l),u=n(152),s=r(u),f=n(153),c=r(f);n(1566);var b=(0,s.default)({loader:function(){return n.e(28).then(n.bind(null,1568))},loading:c.default,delay:3e3}),h=(0,s.default)({loader:function(){return n.e(29).then(n.bind(null,1576))},loading:c.default,delay:3e3}),x=function(e){function t(e){a(this,t);var n=o(this,(t.__proto__||Object.getPrototypeOf(t)).call(this,e));return n.onChildChanged=function(e){n.setState({collapsed:e,windthleft:1==e?"90px":"265px"})},n.state={collapsed:!1,windthleft:"265px"},n}return i(t,e),p(t,[{key:"render",value:function(){return d.default.createElement("div",{id:"wrapper"},d.default.createElement(h,{callbackParent:this.onChildChanged}),d.default.createElement(b,{collapsed:this.state.collapsed}),d.default.createElement("div",{id:"page-wrapperNew",style:{marginLeft:this.state.windthleft}}," ",this.props.children))}}]),t}(d.default.Component);t.default=x}});