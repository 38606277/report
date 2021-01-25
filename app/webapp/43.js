(window["webpackJsonp"] = window["webpackJsonp"] || []).push([[43],{

/***/ "./src/page/dataAsset/addlist.jsx":
/*!****************************************!*\
  !*** ./src/page/dataAsset/addlist.jsx ***!
  \****************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
  value: true
});

var _card = __webpack_require__(/*! antd/es/card */ "./node_modules/_antd@4.9.1@antd/es/card/index.js");

var _card2 = _interopRequireDefault(_card);

var _modal = __webpack_require__(/*! antd/es/modal */ "./node_modules/_antd@4.9.1@antd/es/modal/index.js");

var _modal2 = _interopRequireDefault(_modal);

var _table = __webpack_require__(/*! antd/es/table */ "./node_modules/_antd@4.9.1@antd/es/table/index.js");

var _table2 = _interopRequireDefault(_table);

var _button = __webpack_require__(/*! antd/es/button */ "./node_modules/_antd@4.9.1@antd/es/button/index.js");

var _button2 = _interopRequireDefault(_button);

var _tag = __webpack_require__(/*! antd/es/tag */ "./node_modules/_antd@4.9.1@antd/es/tag/index.js");

var _tag2 = _interopRequireDefault(_tag);

var _popconfirm = __webpack_require__(/*! antd/es/popconfirm */ "./node_modules/_antd@4.9.1@antd/es/popconfirm/index.js");

var _popconfirm2 = _interopRequireDefault(_popconfirm);

var _form = __webpack_require__(/*! antd/es/form */ "./node_modules/_antd@4.9.1@antd/es/form/index.js");

var _form2 = _interopRequireDefault(_form);

var _input = __webpack_require__(/*! antd/es/input */ "./node_modules/_antd@4.9.1@antd/es/input/index.js");

var _input2 = _interopRequireDefault(_input);

var _inputNumber = __webpack_require__(/*! antd/es/input-number */ "./node_modules/_antd@4.9.1@antd/es/input-number/index.js");

var _inputNumber2 = _interopRequireDefault(_inputNumber);

var _message2 = __webpack_require__(/*! antd/es/message */ "./node_modules/_antd@4.9.1@antd/es/message/index.js");

var _message3 = _interopRequireDefault(_message2);

var _slicedToArray = function () { function sliceIterator(arr, i) { var _arr = []; var _n = true; var _d = false; var _e = undefined; try { for (var _i = arr[Symbol.iterator](), _s; !(_n = (_s = _i.next()).done); _n = true) { _arr.push(_s.value); if (i && _arr.length === i) break; } } catch (err) { _d = true; _e = err; } finally { try { if (!_n && _i["return"]) _i["return"](); } finally { if (_d) throw _e; } } return _arr; } return function (arr, i) { if (Array.isArray(arr)) { return arr; } else if (Symbol.iterator in Object(arr)) { return sliceIterator(arr, i); } else { throw new TypeError("Invalid attempt to destructure non-iterable instance"); } }; }();

var _extends = Object.assign || function (target) { for (var i = 1; i < arguments.length; i++) { var source = arguments[i]; for (var key in source) { if (Object.prototype.hasOwnProperty.call(source, key)) { target[key] = source[key]; } } } return target; };

__webpack_require__(/*! antd/es/card/style/css */ "./node_modules/_antd@4.9.1@antd/es/card/style/css.js");

__webpack_require__(/*! antd/es/modal/style/css */ "./node_modules/_antd@4.9.1@antd/es/modal/style/css.js");

__webpack_require__(/*! antd/es/table/style/css */ "./node_modules/_antd@4.9.1@antd/es/table/style/css.js");

__webpack_require__(/*! antd/es/button/style/css */ "./node_modules/_antd@4.9.1@antd/es/button/style/css.js");

__webpack_require__(/*! antd/es/tag/style/css */ "./node_modules/_antd@4.9.1@antd/es/tag/style/css.js");

__webpack_require__(/*! antd/es/popconfirm/style/css */ "./node_modules/_antd@4.9.1@antd/es/popconfirm/style/css.js");

__webpack_require__(/*! antd/es/form/style/css */ "./node_modules/_antd@4.9.1@antd/es/form/style/css.js");

__webpack_require__(/*! antd/es/input/style/css */ "./node_modules/_antd@4.9.1@antd/es/input/style/css.js");

__webpack_require__(/*! antd/es/input-number/style/css */ "./node_modules/_antd@4.9.1@antd/es/input-number/style/css.js");

__webpack_require__(/*! antd/es/message/style/css */ "./node_modules/_antd@4.9.1@antd/es/message/style/css.js");

var _react = __webpack_require__(/*! react */ "./node_modules/_react@16.12.0@react/index.js");

var _react2 = _interopRequireDefault(_react);

var _HttpService = __webpack_require__(/*! ../../util/HttpService.jsx */ "./src/util/HttpService.jsx");

var _HttpService2 = _interopRequireDefault(_HttpService);

var _LogcalStorge = __webpack_require__(/*! ../../util/LogcalStorge.jsx */ "./src/util/LogcalStorge.jsx");

var _LogcalStorge2 = _interopRequireDefault(_LogcalStorge);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _toConsumableArray(arr) { if (Array.isArray(arr)) { for (var i = 0, arr2 = Array(arr.length); i < arr.length; i++) { arr2[i] = arr[i]; } return arr2; } else { return Array.from(arr); } }

function _asyncToGenerator(fn) { return function () { var gen = fn.apply(this, arguments); return new Promise(function (resolve, reject) { function step(key, arg) { try { var info = gen[key](arg); var value = info.value; } catch (error) { reject(error); return; } if (info.done) { resolve(value); } else { return Promise.resolve(value).then(function (value) { step("next", value); }, function (err) { step("throw", err); }); } } return step("next"); }); }; }

function _objectWithoutProperties(obj, keys) { var target = {}; for (var i in obj) { if (keys.indexOf(i) >= 0) continue; if (!Object.prototype.hasOwnProperty.call(obj, i)) continue; target[i] = obj[i]; } return target; } // import React,{useState,useEffect} from 'react'

// import {Table,Tag,Card,Button ,Modal, Input,Form} from 'antd'
// import LocalStorge  from '../../util/LogcalStorge.jsx';
// const localStorge = new LocalStorge();
// let addlist=(statlist)=>{//添加主
//   let {setisModalVisible,setn,setsetstr}=statlist
//   setsetstr('添加一项')
//   setisModalVisible(true)
//   setn('主')
// };
// let handleOk =()=>{
//   setisModalVisible(false)
// };
// let addchildren=(statlist,record)=>{//添加子
//   let {setisModalVisible,setn,setsetstr}=statlist
//   console.log(record)
//   setisModalVisible(true)
//   setsetstr('添加一项')
//   setn('次')
// }
// let setSonList = (record,statlist) =>{
//   let {setisModalVisible,setn,setsetstr}=statlist
//   setsetstr('修改此项')
//   setisModalVisible(true)
//   setn('次')
//   console.log(record)
// }
// export default()=>{
//   let [list,setlist]=useState([]);
//   let [clisst,setclist]=useState([]);
//   let [username,setusername]=useState('');//主导航名称
//   let [urlname,seturlname]=useState('')//主导航路径
//   let [iconname,seticonname]=useState('')//主导航ICon
//   let [isModalVisible,setisModalVisible]=useState(false);
//   let userId=localStorge.getStorage('userInfo').id;
//   let [n,setn]=useState('')//记录是子还是父增加
//   let [setstr,setsetstr]=useState('添加')
//   let statlist={
//     list,setlist,clisst,setclist,username,setusername,urlname,seturlname,iconname,seticonname,isModalVisible,setisModalVisible,
//     n,setn,setstr,setsetstr
//   }
//     useEffect(()=>{
//       (
//         async () =>{
//          await HttpService.post('/reportServer/auth/getMenuListNew',JSON.stringify({userId:userId})).then(res=>{
//            let children=[]
//            let arr=res.data.map((item,index)=>{
//              console.log(item.children)
//              children.push(item.children)
//             return{ 
//               func_icon:item.func_icon,
//               func_name:item.func_name,
//               func_id:item.func_id,
//               func_url:item.func_url,
//               key:index}
//            })
//            setclist(children)
//            setlist(arr)

//          })
//         }
//       )();
//     },[])
//     const columns = [
//       { title: '主导航名称', dataIndex: 'func_name', key: 'func_name' },
//       { title: '主导航路径', dataIndex: 'func_url', key: 'func_url' },
//       { title: '主导icon图标', dataIndex: 'func_icon', key: 'func_icon' },
//       {
//         title: '操作',
//         dataIndex: '',
//         key: 'x',
//         render: (record) => <span>
//             <Tag color="#87d068" onClick={()=>{addchildren(statlist,record)}}>添加子节点</Tag>
//         </span>,
//       },
//     ];
//   const columns2=[
//     {
//       title:'子导航名称',
//       dataIndex: 'func_name',
//       key:'func_name'
//     },{ 
//       title: '子导航路径',
//        dataIndex: 'func_url',
//        key: 'func_url' },
//     {
//        title: '子导航icon图标', 
//        dataIndex: 'func_icon',
//         key: 'func_icon' 
//     },
//     {
//       title:'操作',
//       dataIndex:'',
//       key:'x',
//       render:(record)=>
//       <span>
//         <Tag color="#87d068" onClick={()=>setSonList(record,statlist)}>修改子节点</Tag>
//         <Tag color="#87d068">删除子节点</Tag>
//       </span>
//     }
//   ]
//     const expandedRowRender=(record,index)=>{//子列表
//       let arr= clisst[index].map((item,indexs)=>{
//         return {
//           ...item,
//           key:indexs
//         }
//       })
//         return (<Table columns={columns2} dataSource={arr}  pagination={false} bordered={true} size='small' 
//         style={
//           {
//             marginBottom:'20px',
//           }
//         }
//         title={()=>{
//           return (<div style={{textAlign:'center'}}>子序列</div>)
//         }} 

//         />)
//     }
//     return(
//         <Card title='侧导航设置'>
//           <Button type="primary" style={{float:'right',marginBottom:'20px'}} onClick={()=>addlist(statlist)}>增加新的主导航</Button>
//           <Table
//                 bordered={true}
//                 pagination={false}
//                 columns={columns}
//                 dataSource={list}
//                 expandable={{
//                 expandedRowRender: (record,index) =>{
//                   return expandedRowRender(record,index)
//                 }
//                 }}
//             />
// <Modal title={setstr+n+"导航"} visible={isModalVisible} onOk={()=>handleOk} onCancel={()=>setisModalVisible(!isModalVisible)}>
//   <p>
//     <Form.Item
//       label={n+"导航名称"}
//       name="username"
//     >
//       <Input placeholder='请输入' value={username} onChange={(e)=>{setusername(e.target.value)}}/>
//     </Form.Item>
//   </p>
//   <p>
//     <Form.Item
//       label={n+"导航路径"}
//       name="urlname"
//     >
//       <Input placeholder='请输入' value={urlname} onChange={(e)=>{seturlname(e.target.value)}}/>
//     </Form.Item>
//   </p>
//   <p>
//     <Form.Item
//       label={n+"导导航icon"}
//       name="iconname"
//     >
//       <Input placeholder='请输入antd中的icn字段' value={iconname} onChange={(e)=>{seticonname(e.target.value)}}/>
//     </Form.Item>
//   </p>
// </Modal>
//         </Card>
//     )
// }


var localStorge = new _LogcalStorge2.default();
var getList = function getList(setData) {
  _HttpService2.default.post('/reportServer/menu/getAllList', null).then(function (res) {
    if (res.resultCode === "1000") {
      setData(res.data);
    }
    // let newdata=[]
    // res.data.forEach((item,index)=>{
    //   newdata.push(
    //     {
    //       ...item,
    //       key:index,
    //       children: item.children.map((items,indexs)=>{
    //         return {
    //           ...items,
    //           key:index+"_"+indexs
    //         }
    //       })
    //     }
    //   ) 
    // })
  });
};
var addlist = function addlist(statlist) {
  //添加主
  // func_name  urlname iconname
  var setisModalVisible = statlist.setisModalVisible,
      setStr = statlist.setStr;

  console.log("chenggong");
  setisModalVisible(true);
  setStr('主');
};
var addSon = function addSon(record, statlist) {
  var setisModalVisible = statlist.setisModalVisible,
      setStr = statlist.setStr,
      setFid = statlist.setFid;

  setFid(record.func_id);
  setisModalVisible(true);
  setStr('次');
};
var isNullVerification = function isNullVerification(arr, obj, method, fn) {
  //非空验证
  if (obj[arr.indexOf('')]) {
    _message3.default.warning(obj[arr.indexOf('')]);
    return;
  }
  fn();
  method(false);
};
var handleOk = function handleOk(statlist) {
  var setisModalVisible = statlist.setisModalVisible,
      urlname = statlist.urlname,
      iconname = statlist.iconname,
      func_name = statlist.func_name,
      str = statlist.str,
      Fid = statlist.Fid,
      setusername = statlist.setusername,
      seturlname = statlist.seturlname,
      seticonname = statlist.seticonname,
      addList = statlist.addList;

  var TipsArr = [func_name, urlname];
  var TipsObj = { //非空校验
    0: '导航名称必填',
    1: '导航路径必填'
  };
  isNullVerification(TipsArr, TipsObj, setisModalVisible, function () {

    if (str === "主") {
      addList(null, 0, func_name, urlname, iconname, null, null, null, null);
    } else {
      addList(null, Fid, func_name, urlname, iconname, null, null, null, null);
    }
    // console.log(1)
  });
};
var EditableCell = function EditableCell(_ref) {
  var editing = _ref.editing,
      dataIndex = _ref.dataIndex,
      title = _ref.title,
      inputType = _ref.inputType,
      record = _ref.record,
      index = _ref.index,
      children = _ref.children,
      restProps = _objectWithoutProperties(_ref, ['editing', 'dataIndex', 'title', 'inputType', 'record', 'index', 'children']);

  var inputNode = inputType === 'number' ? _react2.default.createElement(_inputNumber2.default, null) : _react2.default.createElement(_input2.default, null);
  // console.log(inputNode)
  return _react2.default.createElement(
    'td',
    restProps,
    editing ? _react2.default.createElement(
      _form2.default.Item,
      {
        name: dataIndex,
        style: {
          margin: 0
        },
        rules: [{
          required: true,
          message: 'Please Input ' + title + '!'
        }]
      },
      inputNode
    ) : children
  );
};

var H_input = function H_input(props) {
  var style = props.style,
      text = props.text,
      value = props.value,
      change = props.change;

  return _react2.default.createElement(
    'div',
    { style: _extends({ display: 'flex', height: '32px', lineHeight: '32px', margin: "10px 0" }, style) },
    _react2.default.createElement(
      'span',
      { style: { flex: '2' } },
      text
    ),
    _react2.default.createElement(
      'span',
      { style: { flex: '8' } },
      _react2.default.createElement(
        'span',
        { style: { display: 'inline-block', width: "5%", textAlign: 'center' } },
        ':'
      ),
      _react2.default.createElement(_input2.default, { style: { width: '95%' }, placeholder: '\u8BF7\u8F93\u5165', value: value, onChange: function onChange(e) {
          change(e.target.value);
        } })
    )
  );
};

exports.default = function () {
  var _Form$useForm = _form2.default.useForm(),
      _Form$useForm2 = _slicedToArray(_Form$useForm, 1),
      form = _Form$useForm2[0];

  var _useState = (0, _react.useState)([]),
      _useState2 = _slicedToArray(_useState, 2),
      data = _useState2[0],
      setData = _useState2[1];

  var _useState3 = (0, _react.useState)(''),
      _useState4 = _slicedToArray(_useState3, 2),
      editingKey = _useState4[0],
      setEditingKey = _useState4[1];

  var _useState5 = (0, _react.useState)(false),
      _useState6 = _slicedToArray(_useState5, 2),
      isModalVisible = _useState6[0],
      setisModalVisible = _useState6[1]; //控制弹窗


  var _useState7 = (0, _react.useState)(''),
      _useState8 = _slicedToArray(_useState7, 2),
      str = _useState8[0],
      setStr = _useState8[1]; //是主菜'单还是次菜单


  var _useState9 = (0, _react.useState)(''),
      _useState10 = _slicedToArray(_useState9, 2),
      func_name = _useState10[0],
      setfunc_name = _useState10[1]; //主导航名称


  var _useState11 = (0, _react.useState)(''),
      _useState12 = _slicedToArray(_useState11, 2),
      urlname = _useState12[0],
      seturlname = _useState12[1]; //主导航路径


  var _useState13 = (0, _react.useState)(''),
      _useState14 = _slicedToArray(_useState13, 2),
      iconname = _useState14[0],
      seticonname = _useState14[1]; //主导航ICon


  var _useState15 = (0, _react.useState)(null),
      _useState16 = _slicedToArray(_useState15, 2),
      Fid = _useState16[0],
      setFid = _useState16[1];
  // const userId=localStorge.getStorage('userInfo').id;//获取用户id


  var addList = function addList(func_id, func_pid, func_name, func_url, func_icon, func_type, func_desc, order, target) {
    var obj = {
      func_id: func_id,
      func_pid: func_pid,
      func_name: func_name,
      func_url: func_url,
      func_icon: func_icon,
      func_type: "reactWeb",
      valid: 1,
      func_desc: func_desc,
      order: order,
      target: target
    };
    _HttpService2.default.post('/reportServer/menu/saveMenu', JSON.stringify(_extends({}, obj))).then(function (res) {
      if (res.resultCode === "1000") {
        _message3.default.success(res.message);
        getList(setData);
      } else {
        getList(setData);
        return;
      }
    });
  };
  var remoList = function remoList(record) {
    _HttpService2.default.post('/reportServer/menu/deleteMenuById', JSON.stringify({ func_id: record.func_id })).then(function (res) {
      if (res.resultCode === "1000") {
        _message3.default.success(res.message);
        getList(setData);
      } else {
        getList(setData);
      }
    });

    // console.log(record)
  };
  var inputList = [//弹框input数据
  {
    text: '导航名称',
    variable: func_name,
    method: setfunc_name
  }, {
    text: '导航路径',
    variable: urlname,
    method: seturlname
  }, {
    text: '导航ICON',
    variable: iconname,
    method: seticonname
  }];
  var statlist = {
    isModalVisible: isModalVisible, setisModalVisible: setisModalVisible,
    str: str, setStr: setStr,
    func_name: func_name, setfunc_name: setfunc_name,
    urlname: urlname, seturlname: seturlname,
    iconname: iconname, seticonname: seticonname,
    Fid: Fid, setFid: setFid, addList: addList
  };
  (0, _react.useEffect)(function () {
    _asyncToGenerator( /*#__PURE__*/regeneratorRuntime.mark(function _callee() {
      return regeneratorRuntime.wrap(function _callee$(_context) {
        while (1) {
          switch (_context.prev = _context.next) {
            case 0:
              _context.next = 2;
              return getList(setData);

            case 2:
            case 'end':
              return _context.stop();
          }
        }
      }, _callee, undefined);
    }))();
  }, []);
  (0, _react.useEffect)(function () {
    if (!isModalVisible) {
      setfunc_name('');
      seturlname('');
      seticonname('');
    }
  }, [isModalVisible]);
  var isEditing = function isEditing(record) {
    return record.key === editingKey;
  };

  var edit = function edit(record) {
    console.log(record);
    form.setFieldsValue(_extends({}, record));
    setEditingKey(record.key);
  };

  var cancel = function () {
    var _ref3 = _asyncToGenerator( /*#__PURE__*/regeneratorRuntime.mark(function _callee2(record) {
      var key, row, obj, newData, index, item;
      return regeneratorRuntime.wrap(function _callee2$(_context2) {
        while (1) {
          switch (_context2.prev = _context2.next) {
            case 0:
              key = record.key;
              _context2.prev = 1;
              _context2.next = 4;
              return form.validateFields();

            case 4:
              row = _context2.sent;
              _context2.next = 7;
              return _extends({}, record, row);

            case 7:
              obj = _context2.sent;

              console.log(1);

              if (!obj.func_name) {
                _context2.next = 12;
                break;
              }

              _context2.next = 12;
              return addList(obj.func_id, obj.func_pid, obj.func_name, obj.func_url, obj.func_icon, obj.func_type, obj.valid, obj.func_desc, obj.order, obj.target);

            case 12:
              // (func_id,func_pid,func_name,func_url,func_icon,func_type,valid,func_desc,order,target)
              // 
              newData = [].concat(_toConsumableArray(data));
              index = newData.findIndex(function (item) {
                return key === item.key;
              });


              if (index > -1) {
                item = newData[index];

                newData.splice(index, 1, _extends({}, item, row));
                setData(newData);
                setEditingKey('');
              } else {
                // newData.push(row);
                setData(newData);
                setEditingKey('');
              }
              _context2.next = 20;
              break;

            case 17:
              _context2.prev = 17;
              _context2.t0 = _context2['catch'](1);

              console.log('Validate Failed:', _context2.t0);

            case 20:
            case 'end':
              return _context2.stop();
          }
        }
      }, _callee2, undefined, [[1, 17]]);
    }));

    return function cancel(_x) {
      return _ref3.apply(this, arguments);
    };
  }();

  var save = function save() {
    setEditingKey('');
  };

  var columns = [{
    title: '导航名称',
    dataIndex: 'func_name',
    width: '25%',
    key: "func_name",
    editable: true
  }, {
    title: '导航_icon',
    dataIndex: 'func_icon',
    width: '15%',
    key: "func_icon",
    editable: true
  }, {
    title: '导航地址',
    dataIndex: 'func_url',
    width: '40%',
    key: "func_url",
    editable: true
  }, {
    title: '操作',
    dataIndex: 'x',
    key: "x",
    render: function render(_, record) {
      var editable = isEditing(record);
      return editable ? _react2.default.createElement(
        'span',
        null,
        _react2.default.createElement(
          'a',
          {
            href: 'javascript:;',
            onClick: function onClick() {
              return save();
            },
            style: {
              marginRight: 8
            }
          },
          '\u53D6\u6D88\u4FEE\u6539'
        ),
        _react2.default.createElement(
          _popconfirm2.default,
          { title: '\u786E\u8BA4\u662F\u5426\u4FEE\u6539?',
            onConfirm: function onConfirm() {
              return cancel(record);
            },
            onCancel: function onCancel() {
              return save();
            },
            okText: '\u786E\u8BA4',
            cancelText: '\u53D6\u6D88'
          },
          _react2.default.createElement(
            'a',
            null,
            '\u4FEE\u6539'
          )
        )
      ) : _react2.default.createElement(
        'a',
        null,
        _react2.default.createElement(
          _tag2.default,
          { disabled: editingKey !== '', onClick: function onClick() {
              return edit(record);
            }, color: '#87d068' },
          '\u4FEE\u6539'
        ),
        _react2.default.createElement(
          _tag2.default,
          { color: 'red', onClick: function onClick() {
              return remoList(record);
            } },
          '\u5220\u9664'
        ),
        _react2.default.createElement(
          _tag2.default,
          { color: '#108ee9', onClick: function onClick() {
              addSon(record, statlist);
            } },
          '\u6DFB\u52A0'
        )
      );
    }
  }];
  var mergedColumns = columns.map(function (col) {
    if (!col.editable) {
      return col;
    }
    return _extends({}, col, {
      onCell: function onCell(record) {
        return {
          record: record,
          inputType: col.dataIndex === 'age' ? 'number' : 'text',
          dataIndex: col.dataIndex,
          title: col.title,
          editing: isEditing(record)
        };
      }
    });
  });
  return _react2.default.createElement(
    _card2.default,
    { title: '\u4FA7\u5BFC\u822A\u8BBE\u7F6E' },
    _react2.default.createElement(
      _button2.default,
      { type: 'primary', style: { float: 'right', marginBottom: '20px' }, onClick: function onClick() {
          return addlist(statlist);
        } },
      '\u589E\u52A0\u65B0\u7684\u4E3B\u5BFC\u822A'
    ),
    _react2.default.createElement(
      _form2.default,
      { form: form, component: false },
      _react2.default.createElement(_table2.default, {
        components: {
          body: {
            cell: EditableCell
          }
        },
        bordered: true,
        dataSource: data,
        columns: mergedColumns,
        rowClassName: 'editable-row',
        pagination: {
          onChange: cancel
        }
      })
    ),
    _react2.default.createElement(
      _modal2.default,
      { title: "添加" + str + "导航", visible: isModalVisible, onOk: function onOk() {
          return handleOk(statlist);
        }, onCancel: function onCancel() {
          return setisModalVisible(!isModalVisible);
        },
        cancelText: '\u53D6\u6D88', okText: '\u786E\u5B9A'
      },
      inputList.map(function (item, index) {
        return _react2.default.createElement(H_input, { key: index, text: str + item.text, value: item.variable, change: item.method });
      })
    )
  );
};

/***/ }),

/***/ "./src/util/HttpService.jsx":
/*!**********************************!*\
  !*** ./src/util/HttpService.jsx ***!
  \**********************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
    value: true
});
exports.default = undefined;

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

__webpack_require__(/*! whatwg-fetch */ "./node_modules/_whatwg-fetch@2.0.4@whatwg-fetch/fetch.js");

var _LogcalStorge = __webpack_require__(/*! ./LogcalStorge.jsx */ "./src/util/LogcalStorge.jsx");

var _LogcalStorge2 = _interopRequireDefault(_LogcalStorge);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

var localStorge = new _LogcalStorge2.default();

var HttpService = function () {
    function HttpService() {
        _classCallCheck(this, HttpService);
    }

    _createClass(HttpService, null, [{
        key: 'getBaseUrl',
        value: function getBaseUrl() {

            var url = window.getServerUrl(); //"http://localhost:8080/";
            return url;
        }

        //

    }, {
        key: 'post',
        value: function post(url, param) {
            // console.log(url,param)
            if (undefined == localStorge.getStorage('userInfo') && url != '/reportServer/user/encodePwd' && url != '/reportServer/user/Reactlogin' || '' == localStorge.getStorage('userInfo') && url != '/reportServer/user/encodePwd' && url != '/reportServer/user/Reactlogin') {
                window.location.href = '#login';
                return new Promise(function (resolve, reject) {});
            } else {
                var fullUrl = HttpService.getBaseUrl() + url;
                var opts = {
                    method: 'POST',
                    headers: {
                        credentials: JSON.stringify(localStorge.getStorage('userInfo') || '')
                    },
                    body: param
                };

                return fetch(fullUrl, opts).then(function (response) {
                    // console.log(response.json())
                    return response.json();
                }).catch(function (error) {
                    // console.log(error)
                    return error.json();
                });
            }
        }
    }, {
        key: 'getFile',
        value: function getFile(url) {
            if (undefined == localStorge.getStorage('userInfo') && url != '/reportServer/user/encodePwd' && url != '/reportServer/user/Reactlogin' || '' == localStorge.getStorage('userInfo') && url != '/reportServer/user/encodePwd' && url != '/reportServer/user/Reactlogin') {
                window.location.href = '#login';
                return new Promise(function (resolve, reject) {});
            } else {
                var fullUrl = HttpService.getBaseUrl() + url;
                var opts = {
                    method: 'GET',
                    headers: {
                        credentials: JSON.stringify(localStorge.getStorage('userInfo') || '')
                    }
                };

                return fetch(fullUrl, opts);
            }
        }
    }]);

    return HttpService;
}();

exports.default = HttpService;

/***/ })

}]);
//# sourceMappingURL=43.js.map