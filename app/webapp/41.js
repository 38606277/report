(window["webpackJsonp"] = window["webpackJsonp"] || []).push([[41],{

/***/ "./src/page/function/EditIn.jsx":
/*!**************************************!*\
  !*** ./src/page/function/EditIn.jsx ***!
  \**************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
  value: true
});

var _table = __webpack_require__(/*! antd/es/table */ "./node_modules/_antd@4.9.1@antd/es/table/index.js");

var _table2 = _interopRequireDefault(_table);

var _checkbox = __webpack_require__(/*! antd/es/checkbox */ "./node_modules/_antd@4.9.1@antd/es/checkbox/index.js");

var _checkbox2 = _interopRequireDefault(_checkbox);

var _input = __webpack_require__(/*! antd/es/input */ "./node_modules/_antd@4.9.1@antd/es/input/index.js");

var _input2 = _interopRequireDefault(_input);

var _select = __webpack_require__(/*! antd/es/select */ "./node_modules/_antd@4.9.1@antd/es/select/index.js");

var _select2 = _interopRequireDefault(_select);

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

__webpack_require__(/*! antd/es/table/style/css */ "./node_modules/_antd@4.9.1@antd/es/table/style/css.js");

__webpack_require__(/*! antd/es/checkbox/style/css */ "./node_modules/_antd@4.9.1@antd/es/checkbox/style/css.js");

__webpack_require__(/*! antd/es/input/style/css */ "./node_modules/_antd@4.9.1@antd/es/input/style/css.js");

__webpack_require__(/*! antd/es/select/style/css */ "./node_modules/_antd@4.9.1@antd/es/select/style/css.js");

var _react = __webpack_require__(/*! react */ "./node_modules/_react@16.12.0@react/index.js");

var _react2 = _interopRequireDefault(_react);

var _compatible = __webpack_require__(/*! @ant-design/compatible */ "./node_modules/_@ant-design_compatible@1.0.8@@ant-design/compatible/es/index.js");

__webpack_require__(/*! @ant-design/compatible/assets/index.css */ "./node_modules/_@ant-design_compatible@1.0.8@@ant-design/compatible/assets/index.css");

var _HttpService = __webpack_require__(/*! ../../util/HttpService.jsx */ "./src/util/HttpService.jsx");

var _HttpService2 = _interopRequireDefault(_HttpService);

__webpack_require__(/*! ./function.scss */ "./src/page/function/function.scss");

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var Option = _select2.default.Option;

var level = [];

var EditIn = function (_React$Component) {
  _inherits(EditIn, _React$Component);

  // data1 = [{
  //   key: '1',
  //   in_id:"vender_name",
  //   in_name: '供应商名称',
  //   datatype: '字符串',
  //   dict: '1',
  //   isformula:true,
  //   auth:'公司',
  // }, {
  //   key: '2',
  //   in_id:2,
  //   in_name: '项目名称',
  //   datatype: '字符串',
  //   dict: '2',
  //   isformula:true,
  //   auth:'公司',
  // },{
  //   key: '3',
  //   in_id:3,
  //   in_name: '开始日期',
  //   datatype: '日期',
  //   dict: '3',
  //   isformula:true,
  //   auth:'部门',
  // }];

  function EditIn(props) {
    _classCallCheck(this, EditIn);

    var _this = _possibleConstructorReturn(this, (EditIn.__proto__ || Object.getPrototypeOf(EditIn)).call(this, props));

    _this.columns = [{
      title: '列ID',
      dataIndex: 'in_id',
      key: 'in_id',
      width: '120px',
      className: 'headerRow'
    }, {
      title: '列名',
      dataIndex: 'in_name',
      key: 'in_name',
      className: 'headerRow',
      render: function render(text, record, index) {
        return _react2.default.createElement(
          _compatible.Form,
          null,
          _react2.default.createElement(
            _compatible.Form.Item,
            { style: { margin: 0 } },
            _this.props.form.getFieldDecorator(index + '-' + 'in_name', {
              rules: [{
                required: true,
                message: '\u53C2\u6570\u540D\u662F\u5FC5\u987B\u7684\uFF01'
              }]

            })(_react2.default.createElement(_input2.default, { onChange: function onChange(e) {
                return _this.changeEvent(e);
              } }))
          )
        );
      }
    }, {
      title: '数据类型',
      dataIndex: 'datatype',
      key: 'datatype',
      className: 'headerRow',
      render: function render(text, record, index) {
        return _react2.default.createElement(
          _compatible.Form,
          null,
          _react2.default.createElement(
            _compatible.Form.Item,
            { style: { margin: 0 } },
            _this.props.form.getFieldDecorator(index + '-' + 'datatype', {
              rules: [{
                required: true,
                message: '\u6570\u636E\u7C7B\u578B\u662F\u5FC5\u987B\u7684\uFF01'
              }]
            })(_react2.default.createElement(
              _select2.default,
              { tyle: { minWidth: '80px' }, onChange: function onChange(value) {
                  return _this.SelectChangeEvent(index + '-' + 'datatype', value);
                } },
              _react2.default.createElement(
                Option,
                { value: 'string' },
                '\u5B57\u7B26\u4E32'
              ),
              _react2.default.createElement(
                Option,
                { value: 'number' },
                '\u6570\u5B57'
              ),
              _react2.default.createElement(
                Option,
                { value: 'date' },
                '\u65E5\u671F'
              )
            ))
          )
        );
      }
    }, {
      title: '数据字典',
      dataIndex: 'dict_id',
      key: 'dict_id',
      className: 'headerRow',
      render: function render(text, record, index) {
        return _react2.default.createElement(
          _compatible.Form,
          null,
          _react2.default.createElement(
            _compatible.Form.Item,
            { style: { margin: 0 } },
            _this.props.form.getFieldDecorator(index + '-' + 'dict_id', {})(_react2.default.createElement(
              _select2.default,
              { allowClear: true, style: { minWidth: '100px' }, onChange: function onChange(value) {
                  return _this.SelectChangeEvent(index + '-' + 'dict_id', value);
                } },
              _this.state.dictData.map(function (item) {
                return _react2.default.createElement(
                  Option,
                  { value: item.dict_id },
                  item.dict_name
                );
              })
            ))
          )
        );
      }

    }, {
      title: '表达式',
      key: 'isformula',
      dataIndex: 'isformula',
      width: '10%',
      className: 'headerRow',
      render: function render(text, record, index) {
        return _react2.default.createElement(
          _compatible.Form,
          null,
          _react2.default.createElement(
            _compatible.Form.Item,
            { style: { margin: 0 } },
            _this.props.form.getFieldDecorator(index + '-' + 'isformula', {
              valuePropName: 'checked' })(_react2.default.createElement(_checkbox2.default, { onChange: function onChange(e) {
                return _this.CheckBoxChangeEvent(e);
              } }))
          )
        );
      }
    }, {
      title: '数据权限',
      key: 'authtype_id',
      width: '16%',
      dataIndex: 'authtype_id',
      render: function render(text, record, index) {
        return _react2.default.createElement(
          _compatible.Form,
          null,
          _react2.default.createElement(
            _compatible.Form.Item,
            { style: { margin: 0 } },
            _this.props.form.getFieldDecorator(index + '-' + 'authtype_id', {
              rules: [{
                required: true,
                message: 'Please Input '
              }]
            })(_react2.default.createElement(
              _select2.default,
              { allowClear: true, style: { minWidth: '100px' }, onChange: function onChange(value) {
                  return _this.SelectChangeEvent(index + '-' + 'authtype_id', value);
                } },
              _this.state.authData.map(function (item) {
                return _react2.default.createElement(
                  Option,
                  { key: item.value, value: item.value },
                  item.name
                );
              })
            ))
          )
        );
      }
    }];

    _this.props.onRef(_this);
    _this.state = {
      data: [],
      formData: {},
      dictData: [],
      authData: []
    };
    return _this;
  }

  _createClass(EditIn, [{
    key: 'getTreeNode',
    value: function getTreeNode(nodes, nodeid) {
      for (var node in nodes) {
        level.push(node);
        if (node.value == nodeid) {
          break;
          return node;
        }
        if (node.key = 'children') {
          this.getTreeNode(node, key);
        }
      }
    }
  }, {
    key: 'componentDidMount',
    value: function componentDidMount() {
      var _this2 = this;

      //加载数据字字典
      _HttpService2.default.post("reportServer/dict/getAllDictName", null).then(function (res) {
        if (res.resultCode == "1000") {
          _this2.setState({
            dictData: res.data
          });
        } else message.error(res.message);
      });

      //加载数据权限
      _HttpService2.default.post("reportServer/authType/getAllAuthTypeList", null).then(function (res) {
        if (res.resultCode == "1000") {
          _this2.setState({
            authData: res.data
          });
        } else message.error(res.message);
      });
    }
  }, {
    key: 'setFormValue',
    value: function setFormValue(d) {

      this.setState({ data: d });
      var f = {};
      for (var i = 0; i < this.state.data.length; i++) {
        var rowObject = this.state.data[i];
        var keys = Object.getOwnPropertyNames(rowObject);
        var _iteratorNormalCompletion = true;
        var _didIteratorError = false;
        var _iteratorError = undefined;

        try {
          for (var _iterator = keys[Symbol.iterator](), _step; !(_iteratorNormalCompletion = (_step = _iterator.next()).done); _iteratorNormalCompletion = true) {
            var field = _step.value;

            var fieldName = i + '-' + field;
            f[fieldName] = this.state.data[i][field];
            //转换checkbox"1"，"0"为true,false
            // if (field=='isformula')
            // {
            //   f[fieldName]=this.state.data[i][field]=="1"?true:false;
            // }
          }
        } catch (err) {
          _didIteratorError = true;
          _iteratorError = err;
        } finally {
          try {
            if (!_iteratorNormalCompletion && _iterator.return) {
              _iterator.return();
            }
          } finally {
            if (_didIteratorError) {
              throw _iteratorError;
            }
          }
        }
      }
      console.log(f);
      this.setState({ formData: f });
      this.props.form.setFieldsValue(this.state.formData);
      //this.props.form.setFieldsValue(this.state.formData);
    }
  }, {
    key: 'getFormValue',
    value: function getFormValue() {
      return this.state.data;
    }

    // componentWillReceiveProps(nextProps) {
    //   const { data } = this.state
    //   const newdata = nextProps.data.toString()
    //   if (data.toString() !== newdata) {
    //     this.setState({
    //       data: nextProps.data

    //     });

    //   }

    // }

  }, {
    key: 'changeEvent',
    value: function changeEvent(e) {
      // record.age=e.target.value; 
      console.log(e.target.id, e.target.value);
      var id = e.target.id;
      var index = id.split('-')[0];
      var field = id.split('-')[1];
      this.state.data[index][field] = e.target.value;
    }
  }, {
    key: 'SelectChangeEvent',
    value: function SelectChangeEvent(name, value) {
      var id = name;
      var index = id.split('-')[0];
      var field = id.split('-')[1];
      this.state.data[index][field] = value;
    }
  }, {
    key: 'CheckBoxChangeEvent',
    value: function CheckBoxChangeEvent(e) {
      var id = e.target.id;
      var index = id.split('-')[0];
      var field = id.split('-')[1];
      this.state.data[index][field] = e.target.checked ? 1 : 0;
    }
  }, {
    key: 'componentDidUpdate',
    value: function componentDidUpdate() {
      //this.props.form.setFieldsValue(this.state.FormData);
    }
  }, {
    key: 'buttonClick',
    value: function buttonClick() {
      console.log(this.props.form.getFieldsValue());
      console.log(this.state.data);
    }
  }, {
    key: 'changeColumn',
    value: function changeColumn() {
      this.refs.table.columns = this.columns1;

      this.arr.push(_react2.default.createElement(_input2.default, null));
    }
  }, {
    key: 'render',
    value: function render() {

      return (
        // <Button onClick={() => this.buttonClick()} >显示结果</Button>
        // <Button onClick={() => this.changeColumn()} >字段变更</Button>
        _react2.default.createElement(_table2.default, { ref: 'table', columns: this.columns, dataSource: this.state.data, size: 'small', bordered: true, pagination: false })
      );
    }
  }]);

  return EditIn;
}(_react2.default.Component);

exports.default = EditIn = _compatible.Form.create()(EditIn);

/***/ }),

/***/ "./src/page/function/EditOut.jsx":
/*!***************************************!*\
  !*** ./src/page/function/EditOut.jsx ***!
  \***************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
  value: true
});

var _table = __webpack_require__(/*! antd/es/table */ "./node_modules/_antd@4.9.1@antd/es/table/index.js");

var _table2 = _interopRequireDefault(_table);

var _input = __webpack_require__(/*! antd/es/input */ "./node_modules/_antd@4.9.1@antd/es/input/index.js");

var _input2 = _interopRequireDefault(_input);

var _select = __webpack_require__(/*! antd/es/select */ "./node_modules/_antd@4.9.1@antd/es/select/index.js");

var _select2 = _interopRequireDefault(_select);

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

__webpack_require__(/*! antd/es/table/style/css */ "./node_modules/_antd@4.9.1@antd/es/table/style/css.js");

__webpack_require__(/*! antd/es/input/style/css */ "./node_modules/_antd@4.9.1@antd/es/input/style/css.js");

__webpack_require__(/*! antd/es/select/style/css */ "./node_modules/_antd@4.9.1@antd/es/select/style/css.js");

var _react = __webpack_require__(/*! react */ "./node_modules/_react@16.12.0@react/index.js");

var _react2 = _interopRequireDefault(_react);

var _reactRouterDom = __webpack_require__(/*! react-router-dom */ "./node_modules/_react-router-dom@4.2.2@react-router-dom/es/index.js");

var _compatible = __webpack_require__(/*! @ant-design/compatible */ "./node_modules/_@ant-design_compatible@1.0.8@@ant-design/compatible/es/index.js");

__webpack_require__(/*! @ant-design/compatible/assets/index.css */ "./node_modules/_@ant-design_compatible@1.0.8@@ant-design/compatible/assets/index.css");

var _HttpService = __webpack_require__(/*! ../../util/HttpService.jsx */ "./src/util/HttpService.jsx");

var _HttpService2 = _interopRequireDefault(_HttpService);

__webpack_require__(/*! ./function.scss */ "./src/page/function/function.scss");

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var Option = _select2.default.Option;

var EditOut = function (_React$Component) {
  _inherits(EditOut, _React$Component);

  function EditOut(props) {
    _classCallCheck(this, EditOut);

    var _this = _possibleConstructorReturn(this, (EditOut.__proto__ || Object.getPrototypeOf(EditOut)).call(this, props));

    _this.columns = [{
      title: '列ID',
      dataIndex: 'out_id',
      key: 'out_id',
      width: '120px',
      className: 'headerRow'
    }, {
      title: '列名',
      dataIndex: 'out_name',
      key: 'out_name',
      className: 'headerRow',
      render: function render(text, record, index) {
        return _react2.default.createElement(
          _compatible.Form,
          null,
          _react2.default.createElement(
            _compatible.Form.Item,
            { style: { margin: 0 } },
            _this.props.form.getFieldDecorator(index + '-' + 'out_name', {
              rules: [{
                required: true,
                message: '\u53C2\u6570\u540D\u662F\u5FC5\u987B\u7684\uFF01'
              }]

            })(_react2.default.createElement(_input2.default, { onChange: function onChange(e) {
                return _this.changeEvent(e);
              } }))
          )
        );
      }
    }, {
      title: '数据类型',
      dataIndex: 'datatype',
      key: 'datatype',
      className: 'headerRow',
      render: function render(text, record, index) {
        return _react2.default.createElement(
          _compatible.Form,
          null,
          _react2.default.createElement(
            _compatible.Form.Item,
            { style: { margin: 0 } },
            _this.props.form.getFieldDecorator(index + '-' + 'datatype', {
              rules: [{
                required: true,
                message: '\u6570\u636E\u7C7B\u578B\u662F\u5FC5\u987B\u7684\uFF01'
              }]
            })(_react2.default.createElement(
              _select2.default,
              { style: { minWidth: '80px' } },
              _react2.default.createElement(
                Option,
                { value: 'string' },
                '\u5B57\u7B26\u4E32'
              ),
              _react2.default.createElement(
                Option,
                { value: 'number' },
                '\u6570\u5B57'
              ),
              _react2.default.createElement(
                Option,
                { value: 'date' },
                '\u65E5\u671F'
              )
            ))
          )
        );
      }
    }, {
      title: '超链接',
      dataIndex: 'datatype',
      key: 'datatype',
      className: 'headerRow',
      render: function render(text, record, index) {
        return _react2.default.createElement(
          _reactRouterDom.Link,
          { to: '/user/UserView/' + record.id },
          '\u914D\u7F6E'
        );
      }
    }];

    _this.props.onRef(_this);
    _this.state = {
      data: [],
      formData: {},
      dictData: [],
      authData: []
    };
    return _this;
  }

  _createClass(EditOut, [{
    key: 'componentDidMount',
    value: function componentDidMount() {}
  }, {
    key: 'setFormValue',
    value: function setFormValue(d) {

      //let arr=d.slice(0);
      this.setState({ data: d });
      var f = {};
      for (var i = 0; i < this.state.data.length; i++) {
        var rowObject = this.state.data[i];
        var keys = Object.getOwnPropertyNames(rowObject);
        var _iteratorNormalCompletion = true;
        var _didIteratorError = false;
        var _iteratorError = undefined;

        try {
          for (var _iterator = keys[Symbol.iterator](), _step; !(_iteratorNormalCompletion = (_step = _iterator.next()).done); _iteratorNormalCompletion = true) {
            var field = _step.value;

            var fieldName = i + '-' + field;
            f[fieldName] = this.state.data[i][field];
          }
        } catch (err) {
          _didIteratorError = true;
          _iteratorError = err;
        } finally {
          try {
            if (!_iteratorNormalCompletion && _iterator.return) {
              _iterator.return();
            }
          } finally {
            if (_didIteratorError) {
              throw _iteratorError;
            }
          }
        }
      }
      console.log(f);
      this.setState({ formData: f });
      this.props.form.setFieldsValue(this.state.formData);
      //this.props.form.setFieldsValue(this.state.formData);
    }
  }, {
    key: 'getFormValue',
    value: function getFormValue() {
      return this.state.data;
    }
  }, {
    key: 'changeEvent',
    value: function changeEvent(e) {
      // record.age=e.target.value; 
      console.log(e.target.id, e.target.value);
      var id = e.target.id;
      var index = id.split('-')[0];
      var field = id.split('-')[1];
      this.state.data[index][field] = e.target.value;
    }
  }, {
    key: 'buttonClick',
    value: function buttonClick() {
      console.log(this.props.form.getFieldsValue());
      console.log(this.state.data);
    }
  }, {
    key: 'changeColumn',
    value: function changeColumn() {
      this.refs.table.columns = this.columns1;

      this.arr.push(_react2.default.createElement(_input2.default, null));
    }
  }, {
    key: 'render',
    value: function render() {

      return (
        // <Button onClick={() => this.buttonClick()} >显示结果</Button>
        // <Button onClick={() => this.changeColumn()} >字段变更</Button>
        _react2.default.createElement(_table2.default, { ref: 'table', columns: this.columns, dataSource: this.state.data, size: 'small', bordered: true, pagination: false })
      );
    }
  }]);

  return EditOut;
}(_react2.default.Component);

exports.default = EditOut = _compatible.Form.create()(EditOut);

/***/ }),

/***/ "./src/page/function/EditTable.jsx":
/*!*****************************************!*\
  !*** ./src/page/function/EditTable.jsx ***!
  \*****************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
    value: true
});
exports.default = undefined;

var _table = __webpack_require__(/*! antd/es/table */ "./node_modules/_antd@4.9.1@antd/es/table/index.js");

var _table2 = _interopRequireDefault(_table);

var _popconfirm = __webpack_require__(/*! antd/es/popconfirm */ "./node_modules/_antd@4.9.1@antd/es/popconfirm/index.js");

var _popconfirm2 = _interopRequireDefault(_popconfirm);

var _input = __webpack_require__(/*! antd/es/input */ "./node_modules/_antd@4.9.1@antd/es/input/index.js");

var _input2 = _interopRequireDefault(_input);

var _inputNumber = __webpack_require__(/*! antd/es/input-number */ "./node_modules/_antd@4.9.1@antd/es/input-number/index.js");

var _inputNumber2 = _interopRequireDefault(_inputNumber);

var _extends = Object.assign || function (target) { for (var i = 1; i < arguments.length; i++) { var source = arguments[i]; for (var key in source) { if (Object.prototype.hasOwnProperty.call(source, key)) { target[key] = source[key]; } } } return target; };

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

__webpack_require__(/*! antd/es/table/style/css */ "./node_modules/_antd@4.9.1@antd/es/table/style/css.js");

__webpack_require__(/*! antd/es/popconfirm/style/css */ "./node_modules/_antd@4.9.1@antd/es/popconfirm/style/css.js");

__webpack_require__(/*! antd/es/input/style/css */ "./node_modules/_antd@4.9.1@antd/es/input/style/css.js");

__webpack_require__(/*! antd/es/input-number/style/css */ "./node_modules/_antd@4.9.1@antd/es/input-number/style/css.js");

var _react = __webpack_require__(/*! react */ "./node_modules/_react@16.12.0@react/index.js");

var _react2 = _interopRequireDefault(_react);

var _reactDom = __webpack_require__(/*! react-dom */ "./node_modules/_react-dom@16.12.0@react-dom/index.js");

var _reactDom2 = _interopRequireDefault(_reactDom);

var _compatible = __webpack_require__(/*! @ant-design/compatible */ "./node_modules/_@ant-design_compatible@1.0.8@@ant-design/compatible/es/index.js");

__webpack_require__(/*! @ant-design/compatible/assets/index.css */ "./node_modules/_@ant-design_compatible@1.0.8@@ant-design/compatible/assets/index.css");

var _radio = __webpack_require__(/*! antd/lib/radio */ "./node_modules/_antd@4.9.1@antd/lib/radio/index.js");

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _toConsumableArray(arr) { if (Array.isArray(arr)) { for (var i = 0, arr2 = Array(arr.length); i < arr.length; i++) { arr2[i] = arr[i]; } return arr2; } else { return Array.from(arr); } }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

function _objectWithoutProperties(obj, keys) { var target = {}; for (var i in obj) { if (keys.indexOf(i) >= 0) continue; if (!Object.prototype.hasOwnProperty.call(obj, i)) continue; target[i] = obj[i]; } return target; }

var data = [];
for (var i = 0; i < 3; i++) {
    data.push({
        key: i.toString(),
        name: 'Edrward ' + i,
        age: 32,
        address: 'London. ' + i
    });
}
var FormItem = _compatible.Form.Item;
var EditableContext = _react2.default.createContext();

var EditableRow = function EditableRow(_ref) {
    var form = _ref.form,
        index = _ref.index,
        props = _objectWithoutProperties(_ref, ['form', 'index']);

    return _react2.default.createElement(
        EditableContext.Provider,
        { value: form },
        _react2.default.createElement('tr', props)
    );
};

var EditableFormRow = _compatible.Form.create()(EditableRow);

var EditableCell = function (_React$Component) {
    _inherits(EditableCell, _React$Component);

    function EditableCell() {
        var _ref2;

        var _temp, _this, _ret;

        _classCallCheck(this, EditableCell);

        for (var _len = arguments.length, args = Array(_len), _key = 0; _key < _len; _key++) {
            args[_key] = arguments[_key];
        }

        return _ret = (_temp = (_this = _possibleConstructorReturn(this, (_ref2 = EditableCell.__proto__ || Object.getPrototypeOf(EditableCell)).call.apply(_ref2, [this].concat(args))), _this), _this.getInput = function () {
            if (_this.props.inputType === 'number') {
                return _react2.default.createElement(_inputNumber2.default, null);
            }
            return _react2.default.createElement(_input2.default, null);
        }, _temp), _possibleConstructorReturn(_this, _ret);
    }

    _createClass(EditableCell, [{
        key: 'render',
        value: function render() {
            var _this2 = this;

            var _props = this.props,
                editing = _props.editing,
                dataIndex = _props.dataIndex,
                title = _props.title,
                inputType = _props.inputType,
                record = _props.record,
                index = _props.index,
                restProps = _objectWithoutProperties(_props, ['editing', 'dataIndex', 'title', 'inputType', 'record', 'index']);

            return _react2.default.createElement(
                EditableContext.Consumer,
                null,
                function (form) {
                    var getFieldDecorator = form.getFieldDecorator;

                    return _react2.default.createElement(
                        'td',
                        restProps,
                        editing ? _react2.default.createElement(
                            FormItem,
                            { style: { margin: 0 } },
                            getFieldDecorator(dataIndex, {
                                rules: [{
                                    required: true,
                                    message: 'Please Input ' + title + '!'
                                }],
                                initialValue: record[dataIndex]
                            })(_this2.getInput())
                        ) : restProps.children
                    );
                }
            );
        }
    }]);

    return EditableCell;
}(_react2.default.Component);

var EditableTable = function (_React$Component2) {
    _inherits(EditableTable, _React$Component2);

    function EditableTable(props) {
        _classCallCheck(this, EditableTable);

        var _this3 = _possibleConstructorReturn(this, (EditableTable.__proto__ || Object.getPrototypeOf(EditableTable)).call(this, props));

        _this3.isEditing = function (record) {
            return record.key === _this3.state.editingKey;
        };

        _this3.cancel = function () {
            _this3.setState({ editingKey: '' });
        };

        _this3.state = { data: props.data, editingKey: '' };
        _this3.columns = [{
            title: '参数ID',
            dataIndex: 'in_id',
            width: '20%',
            editable: true
        }, {
            title: '参数名字',
            dataIndex: 'in_name',
            width: '20%',
            editable: true
        }, {
            title: '参数类型',
            dataIndex: 'datatype',
            width: '13%',
            editable: true
        }, {
            title: '数据字典',
            dataIndex: 'dict',
            width: '20%',
            editable: true
        }, {
            title: '权限',
            dataIndex: 'auth',
            width: '15%',
            editable: true
        }, {
            title: '操作',
            dataIndex: 'operation',
            render: function render(text, record) {
                var editable = _this3.isEditing(record);
                return _react2.default.createElement(
                    'div',
                    null,
                    editable ? _react2.default.createElement(
                        'span',
                        null,
                        _react2.default.createElement(
                            EditableContext.Consumer,
                            null,
                            function (form) {
                                return _react2.default.createElement(
                                    'a',
                                    {
                                        href: 'javascript:;',
                                        onClick: function onClick() {
                                            return _this3.save(form, record.key);
                                        },
                                        style: { marginRight: 8 }
                                    },
                                    '\u4FDD\u5B58'
                                );
                            }
                        ),
                        _react2.default.createElement(
                            _popconfirm2.default,
                            {
                                title: '\u786E\u8BA4\u653E\u5F03\u4FDD\u5B58?',
                                onConfirm: function onConfirm() {
                                    return _this3.cancel(record.key);
                                }
                            },
                            _react2.default.createElement(
                                'a',
                                null,
                                '\u53D6\u6D88'
                            )
                        )
                    ) : _react2.default.createElement(
                        'a',
                        { onClick: function onClick() {
                                return _this3.edit(record.key);
                            } },
                        '\u7F16\u8F91'
                    )
                );
            }
        }];
        return _this3;
    }

    _createClass(EditableTable, [{
        key: 'edit',
        value: function edit(key) {
            this.setState({ editingKey: key });
        }
    }, {
        key: 'save',
        value: function save(form, key) {
            var _this4 = this;

            form.validateFields(function (error, row) {
                if (error) {
                    return;
                }
                var newData = [].concat(_toConsumableArray(_this4.state.data));
                var index = newData.findIndex(function (item) {
                    return key === item.key;
                });
                if (index > -1) {
                    var item = newData[index];
                    newData.splice(index, 1, _extends({}, item, row));
                    _this4.setState({ data: newData, editingKey: '' });
                } else {
                    newData.push(row);
                    _this4.setState({ data: newData, editingKey: '' });
                }
            });
        }
    }, {
        key: 'componentWillReceiveProps',
        value: function componentWillReceiveProps(nextProps) {
            var data = this.state.data;

            var newdata = nextProps.data.toString();
            if (data.toString() !== newdata) {
                this.setState({
                    data: nextProps.data
                });
            }
        }
    }, {
        key: 'render',
        value: function render() {
            var _this5 = this;

            var components = {
                body: {
                    row: EditableFormRow,
                    cell: EditableCell
                }
            };

            var columns = this.columns.map(function (col) {
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
                            editing: _this5.isEditing(record)
                        };
                    }
                });
            });

            return _react2.default.createElement(_table2.default, {
                components: components,

                dataSource: this.state.data,
                columns: columns,
                rowClassName: 'editable-row',
                pagination: false,
                size: 'small'
            });
        }
    }]);

    return EditableTable;
}(_react2.default.Component);

exports.default = EditableTable;

/***/ }),

/***/ "./src/page/function/functionCreator.jsx":
/*!***********************************************!*\
  !*** ./src/page/function/functionCreator.jsx ***!
  \***********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
    value: true
});

var _row = __webpack_require__(/*! antd/es/row */ "./node_modules/_antd@4.9.1@antd/es/row/index.js");

var _row2 = _interopRequireDefault(_row);

var _col = __webpack_require__(/*! antd/es/col */ "./node_modules/_antd@4.9.1@antd/es/col/index.js");

var _col2 = _interopRequireDefault(_col);

var _card = __webpack_require__(/*! antd/es/card */ "./node_modules/_antd@4.9.1@antd/es/card/index.js");

var _card2 = _interopRequireDefault(_card);

var _divider = __webpack_require__(/*! antd/es/divider */ "./node_modules/_antd@4.9.1@antd/es/divider/index.js");

var _divider2 = _interopRequireDefault(_divider);

var _dropdown = __webpack_require__(/*! antd/es/dropdown */ "./node_modules/_antd@4.9.1@antd/es/dropdown/index.js");

var _dropdown2 = _interopRequireDefault(_dropdown);

var _menu = __webpack_require__(/*! antd/es/menu */ "./node_modules/_antd@4.9.1@antd/es/menu/index.js");

var _menu2 = _interopRequireDefault(_menu);

var _message2 = __webpack_require__(/*! antd/es/message */ "./node_modules/_antd@4.9.1@antd/es/message/index.js");

var _message3 = _interopRequireDefault(_message2);

var _button = __webpack_require__(/*! antd/es/button */ "./node_modules/_antd@4.9.1@antd/es/button/index.js");

var _button2 = _interopRequireDefault(_button);

var _tabs = __webpack_require__(/*! antd/es/tabs */ "./node_modules/_antd@4.9.1@antd/es/tabs/index.js");

var _tabs2 = _interopRequireDefault(_tabs);

var _input = __webpack_require__(/*! antd/es/input */ "./node_modules/_antd@4.9.1@antd/es/input/index.js");

var _input2 = _interopRequireDefault(_input);

var _radio = __webpack_require__(/*! antd/es/radio */ "./node_modules/_antd@4.9.1@antd/es/radio/index.js");

var _radio2 = _interopRequireDefault(_radio);

var _select = __webpack_require__(/*! antd/es/select */ "./node_modules/_antd@4.9.1@antd/es/select/index.js");

var _select2 = _interopRequireDefault(_select);

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

__webpack_require__(/*! antd/es/row/style/css */ "./node_modules/_antd@4.9.1@antd/es/row/style/css.js");

__webpack_require__(/*! antd/es/col/style/css */ "./node_modules/_antd@4.9.1@antd/es/col/style/css.js");

__webpack_require__(/*! antd/es/card/style/css */ "./node_modules/_antd@4.9.1@antd/es/card/style/css.js");

__webpack_require__(/*! antd/es/divider/style/css */ "./node_modules/_antd@4.9.1@antd/es/divider/style/css.js");

__webpack_require__(/*! antd/es/dropdown/style/css */ "./node_modules/_antd@4.9.1@antd/es/dropdown/style/css.js");

__webpack_require__(/*! antd/es/menu/style/css */ "./node_modules/_antd@4.9.1@antd/es/menu/style/css.js");

__webpack_require__(/*! antd/es/message/style/css */ "./node_modules/_antd@4.9.1@antd/es/message/style/css.js");

__webpack_require__(/*! antd/es/button/style/css */ "./node_modules/_antd@4.9.1@antd/es/button/style/css.js");

__webpack_require__(/*! antd/es/tabs/style/css */ "./node_modules/_antd@4.9.1@antd/es/tabs/style/css.js");

__webpack_require__(/*! antd/es/input/style/css */ "./node_modules/_antd@4.9.1@antd/es/input/style/css.js");

__webpack_require__(/*! antd/es/radio/style/css */ "./node_modules/_antd@4.9.1@antd/es/radio/style/css.js");

__webpack_require__(/*! antd/es/select/style/css */ "./node_modules/_antd@4.9.1@antd/es/select/style/css.js");

var _react = __webpack_require__(/*! react */ "./node_modules/_react@16.12.0@react/index.js");

var _react2 = _interopRequireDefault(_react);

var _icons = __webpack_require__(/*! @ant-design/icons */ "./node_modules/_@ant-design_icons@4.3.0@@ant-design/icons/es/index.js");

var _compatible = __webpack_require__(/*! @ant-design/compatible */ "./node_modules/_@ant-design_compatible@1.0.8@@ant-design/compatible/es/index.js");

__webpack_require__(/*! @ant-design/compatible/assets/index.css */ "./node_modules/_@ant-design_compatible@1.0.8@@ant-design/compatible/assets/index.css");

var _moment = __webpack_require__(/*! moment */ "./node_modules/_moment@2.29.1@moment/moment.js");

var _moment2 = _interopRequireDefault(_moment);

__webpack_require__(/*! moment/locale/zh-cn */ "./node_modules/_moment@2.29.1@moment/locale/zh-cn.js");

var _reactCodemirror = __webpack_require__(/*! react-codemirror */ "./node_modules/_react-codemirror@1.0.0@react-codemirror/lib/Codemirror.js");

var _reactCodemirror2 = _interopRequireDefault(_reactCodemirror);

__webpack_require__(/*! codemirror/lib/codemirror.css */ "./node_modules/_codemirror@5.59.0@codemirror/lib/codemirror.css");

__webpack_require__(/*! codemirror/mode/sql/sql */ "./node_modules/_codemirror@5.59.0@codemirror/mode/sql/sql.js");

__webpack_require__(/*! codemirror/theme/ambiance.css */ "./node_modules/_codemirror@5.59.0@codemirror/theme/ambiance.css");

var _EditTable = __webpack_require__(/*! ./EditTable.jsx */ "./src/page/function/EditTable.jsx");

var _EditTable2 = _interopRequireDefault(_EditTable);

var _EditIn = __webpack_require__(/*! ./EditIn.jsx */ "./src/page/function/EditIn.jsx");

var _EditIn2 = _interopRequireDefault(_EditIn);

var _EditOut = __webpack_require__(/*! ./EditOut.jsx */ "./src/page/function/EditOut.jsx");

var _EditOut2 = _interopRequireDefault(_EditOut);

var _FunctionService = __webpack_require__(/*! ../../service/FunctionService.jsx */ "./src/service/FunctionService.jsx");

var _FunctionService2 = _interopRequireDefault(_FunctionService);

var _HttpService = __webpack_require__(/*! ../../util/HttpService.jsx */ "./src/util/HttpService.jsx");

var _HttpService2 = _interopRequireDefault(_HttpService);

var _DbService = __webpack_require__(/*! ../../service/DbService.jsx */ "./src/service/DbService.jsx");

var _DbService2 = _interopRequireDefault(_DbService);

__webpack_require__(/*! ./function.scss */ "./src/page/function/function.scss");

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var FormItem = _compatible.Form.Item;
var Option = _select2.default.Option;
var RadioGroup = _radio2.default.Group;
var TextArea = _input2.default.TextArea;
var TabPane = _tabs2.default.TabPane;
var ButtonGroup = _button2.default.ButtonGroup;

var functionService = new _FunctionService2.default();
var dbService = new _DbService2.default();
var options = {

    lineNumbers: true, //显示行号  
    mode: { name: "text/x-mysql" }, //定义mode  
    extraKeys: { "Ctrl": "autocomplete" }, //自动提示配置  
    theme: "default"

};

var formItemLayout = {
    labelCol: {
        xs: { span: 24 },
        sm: { span: 8 }
    },
    wrapperCol: {
        xs: { span: 24 },
        sm: { span: 16 }
    }
};

// moment.locales('zh-cn');

var functionCreator = function (_React$Component) {
    _inherits(functionCreator, _React$Component);

    function functionCreator(props) {
        _classCallCheck(this, functionCreator);

        // alert(this.props.match.params.funcid);
        var _this = _possibleConstructorReturn(this, (functionCreator.__proto__ || Object.getPrototypeOf(functionCreator)).call(this, props));

        _this.state = {};
        _this.func_data = {};
        _this.state = {
            //定义窗体参数
            action: _this.props.match.params.action,
            func_id: _this.props.match.params.id,
            //定义状态
            inData: [],
            outData: [],
            //定义下拉查找的数据
            dbList: [],
            funcClassList: []
        };
        return _this;
    }

    _createClass(functionCreator, [{
        key: 'componentDidMount',
        value: function componentDidMount() {
            var _this2 = this;

            if (this.state.action == 'update') {
                //查询函数定义
                var param = {};
                _HttpService2.default.post("reportServer/function1/getFunctionByID/" + this.state.func_id, null).then(function (res) {
                    if (res.resultCode == "1000") {
                        _this2.setState({
                            inData: res.data.in,
                            outData: res.data.out
                        });
                        _this2.props.form.setFieldsValue(res.data);
                        _this2.inParam.setFormValue(_this2.state.inData);
                        _this2.outParam.setFormValue(_this2.state.outData);

                        _this2.refs.editorsql.codeMirror.setValue(res.data.func_sql);

                        var editorsql = _this2.refs.editorsql;
                        editorsql.codeMirror.setSize('100%', '500px');
                        editorsql.codeMirror.border = "solid  1px";
                    } else _message3.default.error(res.message);
                });
            }

            //查询DB定义
            dbService.getDbList().then(function (res) {
                _this2.setState({ dbList: res });
            });

            //查询函数类别定义
            functionService.getAllFunctionClass().then(function (res) {
                console.log(JSON.stringify(res));
                if (res.resultCode == '1000') {
                    _this2.setState({ funcClassList: res.data });
                } else _message3.default.error(res.message);
            });
        }
    }, {
        key: 'onSaveClick',
        value: function onSaveClick() {
            var _this3 = this;

            //alert("hello");
            //校验参数合法性
            // e.preventDefault();
            // this.props.form.validateFieldsAndScroll((err, values) => {
            //   if (!err) {
            // //let  users=this.props.form.getFieldsValue();
            // //  console.log(this.state);
            // // console.log(values);
            //   _user.saveUserInfo(this.state).then(response => {
            //     alert("修改成功");
            //     window.location.href="#user/userList";
            //   }, errMsg => {
            //       this.setState({
            //       });
            //       localStorge.errorTips(errMsg);
            //   });
            //console.log('Received values of form: ', this.state);
            //   }
            // });

            //调用服务保存

            //this.child.setFormValue(res.data.in);
            var formInfo = this.props.form.getFieldsValue();
            this.setState({
                inData: this.inParam.getFormValue(),
                outData: this.outParam.getFormValue()
            });
            formInfo.type = 'sql';
            formInfo.func_sql = this.refs.editorsql.codeMirror.getValue();
            formInfo.in = this.state.inData;
            formInfo.out = this.state.outData;
            console.log(formInfo);
            if (this.state.action == 'create') {
                _HttpService2.default.post("reportServer/function1/createFunction", JSON.stringify(formInfo)).then(function (res) {
                    if (res.resultCode == "1000") {
                        _message3.default.success('创建成功！');
                        _this3.setState({ action: 'update' });
                        _this3.props.form.setFieldsValue({ func_id: res.data });
                    } else _message3.default.error(res.message);
                });
            } else if (this.state.action == 'update') {
                _HttpService2.default.post("reportServer/function1/updateFunction", JSON.stringify(formInfo)).then(function (res) {
                    if (res.resultCode == "1000") {
                        _message3.default.success('\u66F4\u65B0\u6210\u529F\uFF01');
                    } else _message3.default.error(res.message);
                });
            }

            //message.success(`${userInfo.userName} 保存成功!：${userInfo.userPwd}`)
        }
    }, {
        key: 'onGenerateClick',
        value: function onGenerateClick() {
            var _this4 = this;

            var aSQL = this.refs.editorsql.codeMirror.getValue();

            functionService.getSqlInOut(aSQL).then(function (res) {
                if (res.resultCode = 1000) {
                    alert(JSON.stringify(res.data));
                    _message3.default.success('生成成功!');
                    var ins = [];
                    var outs = [];
                    var _iteratorNormalCompletion = true;
                    var _didIteratorError = false;
                    var _iteratorError = undefined;

                    try {
                        for (var _iterator = res.data[Symbol.iterator](), _step; !(_iteratorNormalCompletion = (_step = _iterator.next()).done); _iteratorNormalCompletion = true) {
                            var item = _step.value;

                            if (item.type == 'in') {
                                var aIn = {
                                    "dict_id": undefined,
                                    "authtype_id": "",
                                    "in_name": item.name,
                                    "dict_name": undefined,
                                    "isformula": 0,
                                    "authtype_desc": "",
                                    "datatype": item.datatype,
                                    "func_id": "",
                                    "in_id": item.id,
                                    "validate": ""
                                };
                                ins.push(aIn);
                            } else if (item.type == 'out') {
                                var aOut = {
                                    "out_name": item.name,
                                    "datatype": item.datatype,
                                    "link": "{}",
                                    "func_id": 36,
                                    "out_id": item.id
                                };
                                outs.push(aOut);
                            }
                        }
                    } catch (err) {
                        _didIteratorError = true;
                        _iteratorError = err;
                    } finally {
                        try {
                            if (!_iteratorNormalCompletion && _iterator.return) {
                                _iterator.return();
                            }
                        } finally {
                            if (_didIteratorError) {
                                throw _iteratorError;
                            }
                        }
                    }

                    _this4.setState({ inData: ins });
                    _this4.setState({ outData: outs });

                    _this4.inParam.setFormValue(_this4.state.inData);
                    _this4.outParam.setFormValue(_this4.state.outData);
                    // this.setState({ inData: res.data });
                } else {
                    _message3.default.error(res.message);
                }
            });
        }
    }, {
        key: 'formatClick',
        value: function formatClick() {
            var _this5 = this;

            var param = {
                sql: this.refs.editorsql.codeMirror.getValue(),
                type: 'oracle'
            };
            _HttpService2.default.post("reportServer/utils/formatSQL", JSON.stringify(param)).then(function (res) {
                if (res.resultCode == "1000") {
                    _this5.refs.editorsql.codeMirror.setValue(res.data);
                } else _message3.default.error(res.message);
            });
        }
    }, {
        key: 'render',
        value: function render() {
            var _this6 = this;

            var getFieldDecorator = this.props.form.getFieldDecorator;

            var formItemLayout = {
                labelCol: { span: 10 },
                wrapperCol: { span: 14 }
            };
            var formItemLayout1 = {
                labelCol: { span: 3 },
                wrapperCol: { span: 10 }
            };

            var formItemLayout2 = {
                labelCol: { span: 5 },
                wrapperCol: { span: 15 }
            };

            var offsetLayout = {
                wrapperCol: {
                    xs: 24,
                    sm: {
                        span: 12,
                        offset: 4
                    }
                }
            };
            var rowObject = {
                minRows: 4, maxRows: 600
            };

            return _react2.default.createElement(
                'div',
                { id: 'page-wrapper', style: { background: '#ECECEC', padding: '0px' } },
                _react2.default.createElement(
                    _card2.default,
                    { title: '\u521B\u5EFA\u51FD\u6570', bodyStyle: { padding: "5px" }, headStyle: { height: '60px' },
                        extra: _react2.default.createElement(
                            _dropdown2.default,
                            { overlay: _react2.default.createElement(
                                    _menu2.default,
                                    { onClick: this.handleMenuClick },
                                    _react2.default.createElement(
                                        _menu2.default.Item,
                                        { key: '1' },
                                        '\u5B58\u50A8\u8FC7\u7A0B'
                                    ),
                                    _react2.default.createElement(
                                        _menu2.default.Item,
                                        { key: '2' },
                                        'http\u8BF7\u6C42'
                                    ),
                                    _react2.default.createElement(
                                        _menu2.default.Item,
                                        { key: '3' },
                                        'WEBService'
                                    )
                                ) },
                            _react2.default.createElement(
                                _button2.default,
                                { style: { marginLeft: 8 } },
                                'SQL ',
                                _react2.default.createElement(_icons.DownOutlined, null)
                            )
                        ) },
                    _react2.default.createElement(
                        _compatible.Form,
                        { layout: 'inline' },
                        _react2.default.createElement(
                            _row2.default,
                            { gutter: 0 },
                            _react2.default.createElement(
                                _col2.default,
                                { span: 10 },
                                _react2.default.createElement(
                                    _card2.default,
                                    { bodyStyle: { padding: '8px' } },
                                    _react2.default.createElement(
                                        'div',
                                        null,
                                        _react2.default.createElement(
                                            _button2.default,
                                            { type: 'primary', icon: _react2.default.createElement(_icons.ToolOutlined, null), onClick: function onClick() {
                                                    return _this6.onGenerateClick();
                                                }, style: { marginRight: "10px" } },
                                            '\u751F\u6210\u51FD\u6570'
                                        ),
                                        _react2.default.createElement(
                                            _button2.default,
                                            { icon: _react2.default.createElement(_icons.SaveOutlined, null), onClick: function onClick() {
                                                    return _this6.onSaveClick();
                                                }, style: { marginRight: "10px" } },
                                            '\u4FDD\u5B58'
                                        ),
                                        _react2.default.createElement(
                                            _button2.default,
                                            { icon: _react2.default.createElement(_compatible.Icon, { type: 'list' }), onClick: function onClick() {
                                                    return window.location = '#/function/functionList';
                                                }, style: { marginRight: "10px" } },
                                            '\u9000\u51FA'
                                        )
                                    ),
                                    _react2.default.createElement(_divider2.default, { style: { margin: "8px 0 8px 0" } }),
                                    _react2.default.createElement(
                                        FormItem,
                                        { label: '\u9009\u62E9\u6570\u636E\u5E93', style: { marginBottom: "10px" } },
                                        getFieldDecorator('func_db', {
                                            rules: [{ required: 'true', message: "必须选择数据库" }]
                                        })(_react2.default.createElement(
                                            _select2.default,
                                            { setValue: this.form, style: { width: '160px' } },
                                            this.state.dbList.map(function (item) {
                                                return _react2.default.createElement(
                                                    Option,
                                                    { key: item.name, value: item.name },
                                                    item.name
                                                );
                                            })
                                        ))
                                    ),
                                    _react2.default.createElement(
                                        _tabs2.default,
                                        { type: 'card', tabBarExtraContent: _react2.default.createElement(_button2.default, { icon: _react2.default.createElement(_icons.ProfileOutlined, null), onClick: function onClick() {
                                                    return _this6.formatClick();
                                                }, style: { color: "blue" } }) },
                                        _react2.default.createElement(
                                            TabPane,
                                            { tab: '\u8F93\u5165SQL', key: '1' },
                                            _react2.default.createElement(_reactCodemirror2.default, { ref: 'editorsql', value: '', style: { height: '600px', width: '450px', border: "1px" }, options: options })
                                        )
                                    )
                                )
                            ),
                            _react2.default.createElement(
                                _col2.default,
                                { span: 14 },
                                _react2.default.createElement(
                                    _card2.default,
                                    { bodyStyle: { padding: '5px' } },
                                    _react2.default.createElement(
                                        _row2.default,
                                        null,
                                        _react2.default.createElement(
                                            _col2.default,
                                            { span: 12 },
                                            _react2.default.createElement(
                                                FormItem,
                                                { label: ' \u51FD\u6570\u7C7B\u522B' },
                                                getFieldDecorator('class_id', {
                                                    rules: [{ required: true, message: '函数名称是必须的' }]
                                                })(_react2.default.createElement(
                                                    _select2.default,
                                                    { style: { minWidth: '170px' } },
                                                    this.state.funcClassList.map(function (item) {
                                                        return _react2.default.createElement(
                                                            Option,
                                                            { key: item.class_id, value: item.class_id },
                                                            item.class_name
                                                        );
                                                    })
                                                ))
                                            )
                                        ),
                                        _react2.default.createElement(
                                            _col2.default,
                                            { span: 12 },
                                            _react2.default.createElement(
                                                FormItem,
                                                { label: '\u51FD\u6570ID' },
                                                getFieldDecorator('func_id', {})(_react2.default.createElement(_input2.default, { disabled: true }))
                                            )
                                        )
                                    ),
                                    _react2.default.createElement(
                                        _row2.default,
                                        null,
                                        _react2.default.createElement(
                                            _col2.default,
                                            { span: 24 },
                                            _react2.default.createElement(
                                                FormItem,
                                                { label: ' \u51FD\u6570\u540D\u79F0' },
                                                getFieldDecorator('func_name', {
                                                    rules: [{ required: true, message: '函数名称是必须的' }]
                                                })(_react2.default.createElement(_input2.default, { style: { minWidth: '300px' } }))
                                            )
                                        )
                                    ),
                                    _react2.default.createElement(
                                        _row2.default,
                                        null,
                                        _react2.default.createElement(
                                            _col2.default,
                                            { span: 24 },
                                            _react2.default.createElement(
                                                FormItem,
                                                { label: '\u51FD\u6570\u8BF4\u660E', style: { marginLeft: '14px' } },
                                                getFieldDecorator('func_desc', {})(_react2.default.createElement(TextArea, { placeholder: '\u6B64\u51FD\u6570\u4E3B\u8981\u5B8C\u6210\u4EC0\u4E48\u529F\u80FD...', autosize: { minRows: 1, maxRows: 6 }, style: { width: "490px" } }))
                                            )
                                        )
                                    ),
                                    _react2.default.createElement(
                                        _tabs2.default,
                                        { type: 'card', style: { marginTop: '15px' }, onChange: this.tabOnChange },
                                        _react2.default.createElement(
                                            TabPane,
                                            { tab: '\u8F93\u5165\u53C2\u6570', key: '1' },
                                            _react2.default.createElement(_EditIn2.default, { onRef: function onRef(ref) {
                                                    return _this6.inParam = ref;
                                                } })
                                        ),
                                        _react2.default.createElement(
                                            TabPane,
                                            { tab: '\u8F93\u51FA\u53C2\u6570', key: '2', forceRender: true },
                                            _react2.default.createElement(_EditOut2.default, { onRef: function onRef(ref) {
                                                    return _this6.outParam = ref;
                                                } })
                                        )
                                    )
                                )
                            )
                        )
                    )
                )
            );
        }
    }]);

    return functionCreator;
}(_react2.default.Component);

exports.default = functionCreator = _compatible.Form.create({})(functionCreator);

/***/ }),

/***/ "./src/service/DbService.jsx":
/*!***********************************!*\
  !*** ./src/service/DbService.jsx ***!
  \***********************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
    value: true
});
exports.default = undefined;

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _HttpService = __webpack_require__(/*! ../util/HttpService.jsx */ "./src/util/HttpService.jsx");

var _HttpService2 = _interopRequireDefault(_HttpService);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

var DbService = function () {
    function DbService() {
        _classCallCheck(this, DbService);
    }

    _createClass(DbService, [{
        key: "getDbList",

        // 获取函数列表
        value: function getDbList() {
            var url = "reportServer/DBConnection/ListAll";
            var param = {};

            return _HttpService2.default.post(url, param);
        }
    }, {
        key: "getDb",
        value: function getDb(name) {
            var url = "reportServer/DBConnection/GetByName";
            return _HttpService2.default.post(url, name);
        }
    }, {
        key: "saveDb",
        value: function saveDb(dbinfo) {
            if (dbinfo._name == 'null') {
                var url = "reportServer/DBConnection/save";
                return _HttpService2.default.post(url, JSON.stringify(dbinfo));
            } else {
                var _url = "reportServer/DBConnection/update";
                return _HttpService2.default.post(_url, JSON.stringify(dbinfo));
            }
        }
    }, {
        key: "deleteDb",
        value: function deleteDb(name) {
            var url = "reportServer/DBConnection/Delete";
            return _HttpService2.default.post(url, name);
        }
    }, {
        key: "testDb",
        value: function testDb(dbinfo) {
            var url = "reportServer/DBConnection/test";
            return _HttpService2.default.post(url, JSON.stringify(dbinfo));
        }
    }]);

    return DbService;
}();

exports.default = DbService;

/***/ })

}]);
//# sourceMappingURL=41.js.map