(window.webpackJsonp=window.webpackJsonp||[]).push([[18],{1213:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r=function(){function e(e,t){for(var n=0;n<t.length;n++){var r=t[n];r.enumerable=r.enumerable||!1,r.configurable=!0,"value"in r&&(r.writable=!0),Object.defineProperty(e,r.key,r)}}return function(t,n,r){return n&&e(t.prototype,n),r&&e(t,r),t}}(),a=f(n(1)),l=n(444),u=f(n(1295)),o=f(n(1296)),i=f(n(1297));function f(e){return e&&e.__esModule?e:{default:e}}var s=function(e){function t(){return function(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}(this,t),function(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}(this,(t.__proto__||Object.getPrototypeOf(t)).apply(this,arguments))}return function(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}(t,a.default.Component),r(t,[{key:"render",value:function(){return a.default.createElement(l.Switch,null,a.default.createElement(l.Route,{path:"/dbs/dbsList",component:u.default}),a.default.createElement(l.Route,{path:"/dbs/dbInfo/:name",component:o.default}),a.default.createElement(l.Route,{path:"/dbs/dbView/:name",component:i.default}),a.default.createElement(l.Redirect,{exact:!0,from:"/dbs",to:"/dbs/dbsList"}))}}]),t}();t.default=s},1250:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var r=function(){function e(e,t){for(var n=0;n<t.length;n++){var r=t[n];r.enumerable=r.enumerable||!1,r.configurable=!0,"value"in r&&(r.writable=!0),Object.defineProperty(e,r.key,r)}}return function(t,n,r){return n&&e(t.prototype,n),r&&e(t,r),t}}();n(1048);var a,l=n(442);var u=new((a=l)&&a.__esModule?a:{default:a}).default,o=function(){function e(){!function(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}(this,e)}return r(e,[{key:"get",value:function(){}}],[{key:"getBaseUrl",value:function(){return window.getServerUrl()}},{key:"post",value:function(t,n){if(null==u.getStorage("userInfo")&&"/reportServer/user/encodePwd"!=t&&"/reportServer/user/Reactlogin"!=t||""==u.getStorage("userInfo")&&"/reportServer/user/encodePwd"!=t&&"/reportServer/user/Reactlogin"!=t)return window.location.href="#login",new Promise(function(e,t){});var r=e.getBaseUrl()+t,a={method:"POST",headers:{credentials:JSON.stringify(u.getStorage("userInfo")||"")},body:n};return fetch(r,a).then(function(e){return e.json()}).catch(function(e){return e.json()})}}]),e}();t.default=o},1252:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var r,a=function(){function e(e,t){for(var n=0;n<t.length;n++){var r=t[n];r.enumerable=r.enumerable||!1,r.configurable=!0,"value"in r&&(r.writable=!0),Object.defineProperty(e,r.key,r)}}return function(t,n,r){return n&&e(t.prototype,n),r&&e(t,r),t}}(),l=n(1250),u=(r=l)&&r.__esModule?r:{default:r};var o=function(){function e(){!function(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}(this,e)}return a(e,[{key:"getDbList",value:function(){return u.default.post("reportServer/DBConnection/ListAll",{})}},{key:"getDb",value:function(e){return u.default.post("reportServer/DBConnection/GetByName",e)}},{key:"saveDb",value:function(e){if("null"==e._name){return u.default.post("reportServer/DBConnection/save",JSON.stringify(e))}return u.default.post("reportServer/DBConnection/update",JSON.stringify(e))}},{key:"deleteDb",value:function(e){return u.default.post("reportServer/DBConnection/Delete",e)}},{key:"testDb",value:function(e){return u.default.post("reportServer/DBConnection/test",JSON.stringify(e))}}]),e}();t.default=o},1295:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r=s(n(1042)),a=s(n(1047)),l=s(n(44)),u=s(n(1049)),o=function(){function e(e,t){for(var n=0;n<t.length;n++){var r=t[n];r.enumerable=r.enumerable||!1,r.configurable=!0,"value"in r&&(r.writable=!0),Object.defineProperty(e,r.key,r)}}return function(t,n,r){return n&&e(t.prototype,n),r&&e(t,r),t}}();n(1041),n(1046),n(157),n(1050);var i=s(n(1)),f=n(444);function s(e){return e&&e.__esModule?e:{default:e}}var c=new(s(n(1252)).default),d=function(e){function t(e){!function(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}(this,t);var n=function(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}(this,(t.__proto__||Object.getPrototypeOf(t)).call(this,e));return n.state={list:[]},n}return function(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}(t,i.default.Component),o(t,[{key:"componentDidMount",value:function(){this.loadDbList()}},{key:"loadDbList",value:function(){var e=this;c.getDbList({}).then(function(t){e.setState({list:t})},function(t){e.setState({list:[]})})}},{key:"deleteDb",value:function(e){var t=this;confirm("确认删除吗？")&&c.deleteDb(e).then(function(e){alert("删除成功"),t.loadDbList()},function(e){alert("删除失败")})}},{key:"render",value:function(){var e=this;this.state.list.map(function(e,t){e.key=t});var t=this.state.list,n=[{title:"连接名称",dataIndex:"name",key:"name",render:function(e,t,n){return i.default.createElement(f.Link,{to:"/dbs/dbView/"+t.name},e)}},{title:"连接类型",dataIndex:"dbtype",key:"dbtype"},{title:"操作",dataIndex:"操作",render:function(t,n){return i.default.createElement("span",null,i.default.createElement(f.Link,{to:"/dbs/dbInfo/"+n.name},"编辑"),i.default.createElement(u.default,{type:"vertical"}),"system"!=n.name&&"form"!=n.name?i.default.createElement("a",{onClick:function(){return e.deleteDb(""+n.name)},href:"javascript:;"},"删除"):"")}}];return i.default.createElement("div",{id:"page-wrapper"},i.default.createElement(r.default,{title:"连接管理"},i.default.createElement(l.default,{href:"#/dbs/dbInfo/null",style:{float:"right",marginRight:"30px",marginBottom:"10px"},type:"primary"},"新建连接"),i.default.createElement(a.default,{dataSource:t,columns:n,pagination:!1,style:{marginTop:"30px"}})))}}]),t}();t.default=d},1296:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r=m(n(1042)),a=m(n(44)),l=m(n(120)),u=m(n(66)),o=m(n(1043)),i=m(n(138)),f=m(n(1045)),s=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var n=arguments[t];for(var r in n)Object.prototype.hasOwnProperty.call(n,r)&&(e[r]=n[r])}return e},c=function(){function e(e,t){for(var n=0;n<t.length;n++){var r=t[n];r.enumerable=r.enumerable||!1,r.configurable=!0,"value"in r&&(r.writable=!0),Object.defineProperty(e,r.key,r)}}return function(t,n,r){return n&&e(t.prototype,n),r&&e(t,r),t}}();n(1041),n(157),n(445),n(446),n(443),n(447),n(1044);var d=m(n(1)),p=m(n(1252));function m(e){return e&&e.__esModule?e:{default:e}}function b(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}var y=new(m(n(442)).default),h=f.default.Item,v=i.default.Option,w=new p.default,E=function(e){function t(e){!function(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}(this,t);var n=function(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}(this,(t.__proto__||Object.getPrototypeOf(t)).call(this,e));return n.state={confirmDirty:!1,_name:n.props.match.params.name},n.handleSubmit=n.handleSubmit.bind(n),n.handleConfirmBlur=n.handleConfirmBlur.bind(n),n}return function(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}(t,d.default.Component),c(t,[{key:"componentDidMount",value:function(){var e=this;null!=this.state._name&&""!=this.state._name&&"null"!=this.state._name&&w.getDb(this.state._name).then(function(t){e.props.form.setFieldsValue(t)},function(t){e.setState({}),y.errorTips(t)})}},{key:"onSelectChange",value:function(e,t){var n,r=null,a=null;"Oracle"==t?(r="oracle.jdbc.OracleDriver",a="jdbc:oracle:thin:@ip:port:xe"):"Mysql"==t?(r="com.mysql.cj.jdbc.Driver",a="jdbc:mysql://ip:port/xe"):"DB2"==t?(r="com.ibm.db2.jcc.DB2Driver",a="jdbc:db2://ip:port/xe"):"mongoDB"==t&&(r="",a="ip:port"),this.props.form.setFieldsValue((b(n={},e,t),b(n,"driver",r),b(n,"url",a),n))}},{key:"onValueChange",value:function(e){var t=e.target.name,n=e.target.value.trim();this.props.form.setFieldsValue(b({},t,n))}},{key:"handleSubmit",value:function(e){var t=this;e.preventDefault(),this.props.form.validateFieldsAndScroll(function(e,n){e||(n._name=t.state._name,w.saveDb(n).then(function(e){alert("保存成功"),window.location.href="#/dbs"},function(e){t.setState({}),y.errorTips(e)}))})}},{key:"handleConfirmBlur",value:function(e){var t=e.target.value;this.setState({confirmDirty:this.state.confirmDirty||!!t})}},{key:"clickTest",value:function(){var e=this;this.props.form.validateFieldsAndScroll(function(t,n){t||w.testDb([e.values,"update"]).then(function(e){alert(e.retMsg)},function(e){alert("连接失败")})})}},{key:"render",value:function(){var e=this,t=this.props.form.getFieldDecorator,n={labelCol:{xs:{span:24},sm:{span:8}},wrapperCol:{xs:{span:24},sm:{span:16}}};return d.default.createElement("div",{id:"page-wrapper"},d.default.createElement(r.default,{title:"null"==this.state._name?"新建连接":"编辑连接"},d.default.createElement(f.default,{onSubmit:this.handleSubmit},d.default.createElement(l.default,null,d.default.createElement(u.default,{xs:24,sm:12},d.default.createElement(h,s({},n,{label:"连接名称"}),t("name",{rules:[{required:!0,message:"请输入连接名称!"}]})(d.default.createElement(o.default,{type:"text",name:"name"})))),d.default.createElement(u.default,{xs:24,sm:12},d.default.createElement(h,s({},n,{label:"数据库类型"}),t("dbtype",{rules:[{required:!0,message:"请选择数据库类型!",whitespace:!0}]})(d.default.createElement(i.default,{name:"dbtype",style:{width:120},onChange:function(t){return e.onSelectChange("dbtype",t)}},d.default.createElement(v,{value:"Oracle"},"Oracle"),d.default.createElement(v,{value:"Mysql"},"Mysql"),d.default.createElement(v,{value:"DB2"},"DB2"),d.default.createElement(v,{value:"mongoDB"},"mongoDB")))))),d.default.createElement(l.default,null,d.default.createElement(u.default,{xs:24,sm:12},d.default.createElement(h,s({},n,{label:"驱动类型"}),t("driver",{rules:[{required:!0,message:"请输入驱动类型!",whitespace:!0}]})(d.default.createElement(o.default,{type:"text",name:"driver"})))),d.default.createElement(u.default,{xs:24,sm:12},d.default.createElement(h,s({},n,{label:"地址"}),t("url",{rules:[{required:!0,message:"请输入驱动类型!",whitespace:!0}]})(d.default.createElement(o.default,{type:"text",name:"url"}))))),d.default.createElement(l.default,null,d.default.createElement(u.default,{xs:24,sm:12},d.default.createElement(h,s({},n,{label:"用户名"}),t("username",{rules:[{required:!0,message:"请输入用户名!",whitespace:!0}]})(d.default.createElement(o.default,{type:"text",name:"username"})))),d.default.createElement(u.default,{xs:24,sm:12},d.default.createElement(h,s({},n,{label:"密码"}),t("password",{rules:[{required:!1,message:"请输入密码",whitespace:!0}]})(d.default.createElement(o.default,{type:"text",name:"password"}))))),d.default.createElement(h,{wrapperCol:{xs:{span:24,offset:0},sm:{span:16,offset:8}}},d.default.createElement(a.default,{type:"primary",onClick:function(){return e.clickTest()}},"测试"),d.default.createElement(a.default,{type:"primary",htmlType:"submit",style:{marginLeft:"30px"}},"保存"),d.default.createElement(a.default,{href:"#/dbs",type:"primary",style:{marginLeft:"30px"}},"返回")))))}}]),t}();t.default=f.default.create()(E)},1297:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r=p(n(1042)),a=p(n(44)),l=p(n(120)),u=p(n(66)),o=p(n(138)),i=p(n(1045)),f=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var n=arguments[t];for(var r in n)Object.prototype.hasOwnProperty.call(n,r)&&(e[r]=n[r])}return e},s=function(){function e(e,t){for(var n=0;n<t.length;n++){var r=t[n];r.enumerable=r.enumerable||!1,r.configurable=!0,"value"in r&&(r.writable=!0),Object.defineProperty(e,r.key,r)}}return function(t,n,r){return n&&e(t.prototype,n),r&&e(t,r),t}}();n(1041),n(157),n(445),n(446),n(447),n(1044);var c=p(n(1)),d=p(n(1252));function p(e){return e&&e.__esModule?e:{default:e}}var m=new(p(n(442)).default),b=i.default.Item,y=(o.default.Option,new d.default),h=function(e){function t(e){!function(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}(this,t);var n=function(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}(this,(t.__proto__||Object.getPrototypeOf(t)).call(this,e));return n.state={name:n.props.match.params.name},n}return function(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}(t,c.default.Component),s(t,[{key:"componentDidMount",value:function(){var e=this;y.getDb(this.state.name).then(function(t){e.setState(t)},function(t){e.setState({}),m.errorTips(t)})}},{key:"render",value:function(){this.props.form.getFieldDecorator;var e={labelCol:{xs:{span:24},sm:{span:8}},wrapperCol:{xs:{span:24},sm:{span:16}}};return c.default.createElement("div",{id:"page-wrapper"},c.default.createElement(r.default,{title:"查看连接"},c.default.createElement(i.default,{onSubmit:this.handleSubmit},c.default.createElement(l.default,null,c.default.createElement(u.default,{xs:24,sm:12},c.default.createElement(b,f({},e,{label:"连接名称"}),this.state.name)),c.default.createElement(u.default,{xs:24,sm:12},c.default.createElement(b,f({},e,{label:"连接类型"}),this.state.dbtype))),c.default.createElement(l.default,null,c.default.createElement(u.default,{xs:24,sm:12},c.default.createElement(b,f({},e,{label:"驱动类型"}),this.state.driver)),c.default.createElement(u.default,{xs:24,sm:12},c.default.createElement(b,f({},e,{label:"地址"}),this.state.url))),c.default.createElement(l.default,null,c.default.createElement(u.default,{xs:24,sm:12},c.default.createElement(b,f({},e,{label:"用户名"}),this.state.username)),c.default.createElement(u.default,{xs:24,sm:12},c.default.createElement(b,f({},e,{label:"密码"}),this.state.password))),c.default.createElement(b,{wrapperCol:{xs:{span:24,offset:0},sm:{span:16,offset:8}}},c.default.createElement(a.default,{href:"#/dbs",type:"primary"},"返回")))))}}]),t}();t.default=i.default.create()(h)}}]);