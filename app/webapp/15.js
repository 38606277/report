(window.webpackJsonp=window.webpackJsonp||[]).push([[15],{1250:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var r=function(){function e(e,t){for(var a=0;a<t.length;a++){var r=t[a];r.enumerable=r.enumerable||!1,r.configurable=!0,"value"in r&&(r.writable=!0),Object.defineProperty(e,r.key,r)}}return function(t,a,r){return a&&e(t.prototype,a),r&&e(t,r),t}}();a(1048);var n,l=a(442);var o=new((n=l)&&n.__esModule?n:{default:n}).default,u=function(){function e(){!function(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}(this,e)}return r(e,[{key:"get",value:function(){}}],[{key:"getBaseUrl",value:function(){return window.getServerUrl()}},{key:"post",value:function(t,a){if(null==o.getStorage("userInfo")&&"/reportServer/user/encodePwd"!=t&&"/reportServer/user/Reactlogin"!=t||""==o.getStorage("userInfo")&&"/reportServer/user/encodePwd"!=t&&"/reportServer/user/Reactlogin"!=t)return window.location.href="#login",new Promise(function(e,t){});var r=e.getBaseUrl()+t,n={method:"POST",headers:{credentials:JSON.stringify(o.getStorage("userInfo")||"")},body:a};return fetch(r,n).then(function(e){return e.json()}).catch(function(e){return e.json()})}}]),e}();t.default=u},1252:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var r,n=function(){function e(e,t){for(var a=0;a<t.length;a++){var r=t[a];r.enumerable=r.enumerable||!1,r.configurable=!0,"value"in r&&(r.writable=!0),Object.defineProperty(e,r.key,r)}}return function(t,a,r){return a&&e(t.prototype,a),r&&e(t,r),t}}(),l=a(1250),o=(r=l)&&r.__esModule?r:{default:r};var u=function(){function e(){!function(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}(this,e)}return n(e,[{key:"getDbList",value:function(){return o.default.post("reportServer/DBConnection/ListAll",{})}},{key:"getDb",value:function(e){return o.default.post("reportServer/DBConnection/GetByName",e)}},{key:"saveDb",value:function(e){if("null"==e._name){return o.default.post("reportServer/DBConnection/save",JSON.stringify(e))}return o.default.post("reportServer/DBConnection/update",JSON.stringify(e))}},{key:"deleteDb",value:function(e){return o.default.post("reportServer/DBConnection/Delete",e)}},{key:"testDb",value:function(e){return o.default.post("reportServer/DBConnection/test",JSON.stringify(e))}}]),e}();t.default=u},1259:function(e,t,a){var r=a(1260);"string"==typeof r&&(r=[[e.i,r,""]]);var n={hmr:!0,transform:void 0};a(22)(r,n);r.locals&&(e.exports=r.locals)},1260:function(e,t,a){(e.exports=a(21)(!1)).push([e.i,".ant-table-thead .headerRow {\n  text-align: center; }\n\n.ant-table-tbody .headerRow {\n  text-align: center; }\n\n.checkbox {\n  text-align: center;\n  margin: auto;\n  padding: 0; }\n",""])},1370:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r=k(a(428)),n=k(a(120)),l=k(a(66)),o=k(a(1042)),u=k(a(1049)),i=k(a(161)),f=k(a(11)),s=k(a(214)),d=k(a(1053)),c=k(a(44)),p=k(a(213)),m=k(a(1043)),y=k(a(159)),h=k(a(138)),v=k(a(1045)),g=function(){function e(e,t){for(var a=0;a<t.length;a++){var r=t[a];r.enumerable=r.enumerable||!1,r.configurable=!0,"value"in r&&(r.writable=!0),Object.defineProperty(e,r.key,r)}}return function(t,a,r){return a&&e(t.prototype,a),r&&e(t,r),t}}();a(459),a(445),a(446),a(1041),a(1050),a(454),a(1054),a(1196),a(1052),a(157),a(449),a(443),a(450),a(447),a(1044);var b=k(a(1)),E=k(a(1188));a(1189),a(1190),a(1191);var _=k(a(1250)),w=k(a(1252)),S=k(a(1371));function k(e){return e&&e.__esModule?e:{default:e}}a(1259);var x=v.default.Item,C=h.default.Option,O=(y.default.Group,m.default.TextArea),D=p.default.TabPane,P=(c.default.ButtonGroup,new w.default),j={lineNumbers:!0,mode:{name:"text/x-mysql"},extraKeys:{Ctrl:"autocomplete"},theme:"default"},I=function(e){function t(e){!function(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}(this,t);var a=function(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}(this,(t.__proto__||Object.getPrototypeOf(t)).call(this,e));return a.state={},a.outParam=null,a.func_data={},a.state={action:a.props.match.params.action,dict_id:a.props.match.params.id,inData:[],outData:[],dbList:[],funcClassList:[]},a}return function(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}(t,b.default.Component),g(t,[{key:"componentDidMount",value:function(){var e=this;if("update"==this.state.action){_.default.post("reportServer/dict/getDictByID/"+this.state.dict_id,null).then(function(t){if("1000"==t.resultCode){e.setState({outData:t.data.out}),e.props.form.setFieldsValue(t.data),e.outParam.setFormValue(e.state.outData),e.refs.editorsql.codeMirror.setValue(t.data.dict_sql);var a=e.refs.editorsql;a.codeMirror.setSize("100%","500px"),a.codeMirror.border="solid  1px"}else d.default.error(t.message)})}P.getDbList().then(function(t){e.setState({dbList:t})})}},{key:"onSaveClick",value:function(){var e=this.props.form.getFieldsValue();this.setState({outData:this.outParam.getFormValue()}),e.dict_sql=this.refs.editorsql.codeMirror.getValue(),e.out=this.state.outData,console.log(e),"create"==this.state.action?_.default.post("reportServer/dict/createDict",JSON.stringify(e)).then(function(e){"1000"==e.resultCode?d.default.success("创建成功！"):d.default.error(e.message)}):"update"==this.state.action&&_.default.post("reportServer/dict/updateDict",JSON.stringify(e)).then(function(e){"1000"==e.resultCode?d.default.success("更新成功！"):d.default.error(e.message)})}},{key:"onGenerateClick",value:function(){var e=this,t={sqlType:"sql",sql:this.refs.editorsql.codeMirror.getValue()};_.default.post("reportServer/sql/getInputOutputParas",JSON.stringify(t)).then(function(t){if(t.resultCode=1e3){alert(JSON.stringify(t.data)),d.default.success("生成成功!");var a=[],r=[],n=!0,l=!1,o=void 0;try{for(var u,i=t.data[Symbol.iterator]();!(n=(u=i.next()).done);n=!0){var f=u.value;if("in"==f.type){var s={dict_id:"",authtype_id:"",in_name:f.name,dict_name:"",isformula:0,authtype_desc:"",datatype:f.datatype,func_id:"",in_id:f.id,validate:""};a.push(s)}else if("out"==f.type){var c={dict_id:void 0,out_id:f.id,out_name:f.name};r.push(c)}}}catch(e){l=!0,o=e}finally{try{!n&&i.return&&i.return()}finally{if(l)throw o}}e.setState({outData:r}),e.outParam.setFormValue(e.state.outData)}else d.default.error(t.message)})}},{key:"BeginImport",value:function(){_.default.post("reportServer/dict/initImportDictValue/"+this.state.dict_id,null).then(function(e){"1000"==e.resultCode?d.default.success("导入成功！"):d.default.error(e.message)})}},{key:"render",value:function(){var e=this,t=this.props.form.getFieldDecorator;return b.default.createElement("div",{id:"page-wrapper",style:{background:"#ECECEC",padding:"0px"}},b.default.createElement(o.default,{title:"创建字典",bodyStyle:{padding:"5px"},headStyle:{height:"60px"},extra:b.default.createElement(i.default,{overlay:b.default.createElement(s.default,{onClick:this.handleMenuClick},b.default.createElement(s.default.Item,{key:"1"},"存储过程"),b.default.createElement(s.default.Item,{key:"2"},"http请求"),b.default.createElement(s.default.Item,{key:"3"},"WEBService"))},b.default.createElement(c.default,{style:{marginLeft:8}},"SQL ",b.default.createElement(f.default,{type:"down"})))},b.default.createElement(v.default,{layout:"inline"},b.default.createElement(n.default,{gutter:0},b.default.createElement(l.default,{span:10},b.default.createElement(o.default,{bodyStyle:{padding:"8px"}},b.default.createElement("div",null,b.default.createElement(c.default,{type:"primary",icon:"tool",onClick:function(){return e.onGenerateClick()},style:{marginRight:"10px"}},"生成字典"),b.default.createElement(c.default,{icon:"save",onClick:function(){return e.onSaveClick()},style:{marginRight:"10px"}},"保存"),b.default.createElement(c.default,{icon:"list",onClick:function(){return window.location="#/dict/DictList"},style:{marginRight:"10px"}},"退出")),b.default.createElement(u.default,{style:{margin:"8px 0 8px 0"}}),b.default.createElement(x,{label:"选择数据库",style:{marginBottom:"10px"}},t("dict_db",{rules:[{required:"true",message:"必须选择数据库"}]})(b.default.createElement(h.default,{setValue:this.form,style:{width:"160px"}},this.state.dbList.map(function(e){return b.default.createElement(C,{key:e.name,value:e.name},e.name)})))),b.default.createElement(p.default,{type:"card",tabBarExtraContent:b.default.createElement(c.default,{icon:"profile",style:{color:"blue"}})},b.default.createElement(D,{tab:"输入SQL",key:"1"},b.default.createElement(E.default,{ref:"editorsql",value:"",style:{height:"600px",width:"450px",border:"1px"},options:j}))))),b.default.createElement(l.default,{span:14},b.default.createElement(o.default,{bodyStyle:{padding:"5px"}},b.default.createElement(n.default,null,b.default.createElement(l.default,{span:12},b.default.createElement(x,{label:" 字典名称"},t("dict_name",{rules:[{required:!0,message:"函数名称是必须的"}]})(b.default.createElement(m.default,{style:{minWidth:"100px"}})))),b.default.createElement(l.default,{span:12},b.default.createElement(x,{label:"字典ID"},t("dict_id",{})(b.default.createElement(m.default,{disabled:!0}))))),b.default.createElement(n.default,null,b.default.createElement(l.default,{span:24},b.default.createElement(x,{label:"字典说明",style:{marginLeft:"14px"}},t("dict_desc",{})(b.default.createElement(O,{placeholder:"此字典主要完成什么功能...",autosize:{minRows:1,maxRows:6},style:{width:"490px"}}))))),b.default.createElement(n.default,{style:{marginTop:"20px"}},b.default.createElement(l.default,{span:6,offset:1},b.default.createElement(c.default,{type:"primary",onClick:function(){return e.BeginImport()},style:{width:"120px"}},"开始同步")),b.default.createElement(l.default,{span:12},b.default.createElement(r.default,{percent:30}))),b.default.createElement(p.default,{type:"card",style:{marginTop:"15px"},onChange:this.tabOnChange},b.default.createElement(D,{tab:"输出参数",key:"2",forceRender:!0},b.default.createElement(S.default,{onRef:function(t){return e.outParam=t}})))))))))}}]),t}();t.default=I=v.default.create({})(I)},1371:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r=f(a(1047)),n=f(a(1045)),l=f(a(1043)),o=f(a(138)),u=function(){function e(e,t){for(var a=0;a<t.length;a++){var r=t[a];r.enumerable=r.enumerable||!1,r.configurable=!0,"value"in r&&(r.writable=!0),Object.defineProperty(e,r.key,r)}}return function(t,a,r){return a&&e(t.prototype,a),r&&e(t,r),t}}();a(1046),a(1044),a(443),a(447);var i=f(a(1));function f(e){return e&&e.__esModule?e:{default:e}}a(1259);o.default.Option;var s=function(e){function t(e){!function(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}(this,t);var a=function(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}(this,(t.__proto__||Object.getPrototypeOf(t)).call(this,e));return a.columns=[{title:"列ID",dataIndex:"out_id",key:"out_id",width:"120px",className:"headerRow"},{title:"列名",dataIndex:"out_name",key:"out_name",className:"headerRow",render:function(e,t,r){return i.default.createElement(n.default,null,i.default.createElement(n.default.Item,{style:{margin:0}},a.props.form.getFieldDecorator(r+"-out_name",{rules:[{required:!0,message:"列名是必须的！"}]})(i.default.createElement(l.default,{onChange:function(e){return a.changeEvent(e)}}))))}}],a.props.onRef(a),a.state={data:[],formData:{}},a}return function(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}(t,i.default.Component),u(t,[{key:"componentDidMount",value:function(){}},{key:"setFormValue",value:function(e){this.setState({data:e});for(var t={},a=0;a<this.state.data.length;a++){var r=this.state.data[a],n=Object.getOwnPropertyNames(r),l=!0,o=!1,u=void 0;try{for(var i,f=n[Symbol.iterator]();!(l=(i=f.next()).done);l=!0){var s=i.value;t[a+"-"+s]=this.state.data[a][s]}}catch(e){o=!0,u=e}finally{try{!l&&f.return&&f.return()}finally{if(o)throw u}}}console.log(t),this.setState({formData:t}),this.props.form.setFieldsValue(this.state.formData)}},{key:"getFormValue",value:function(){return this.state.data}},{key:"changeEvent",value:function(e){console.log(e.target.id,e.target.value);var t=e.target.id,a=t.split("-")[0],r=t.split("-")[1];this.state.data[a][r]=e.target.value}},{key:"buttonClick",value:function(){console.log(this.props.form.getFieldsValue()),console.log(this.state.data)}},{key:"changeColumn",value:function(){this.refs.table.columns=this.columns1,this.arr.push(i.default.createElement(l.default,null))}},{key:"render",value:function(){return i.default.createElement(r.default,{ref:"table",columns:this.columns,dataSource:this.state.data,size:"small",bordered:!0,pagination:!1})}}]),t}();t.default=s=n.default.create()(s)}}]);