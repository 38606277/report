(window["webpackJsonp"] = window["webpackJsonp"] || []).push([[44],{

/***/ "./src/page/function/FunctionClass.jsx":
/*!*********************************************!*\
  !*** ./src/page/function/FunctionClass.jsx ***!
  \*********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
  value: true
});

var _card = __webpack_require__(/*! antd/es/card */ "./node_modules/_antd@4.9.1@antd/es/card/index.js");

var _card2 = _interopRequireDefault(_card);

var _table = __webpack_require__(/*! antd/es/table */ "./node_modules/_antd@4.9.1@antd/es/table/index.js");

var _table2 = _interopRequireDefault(_table);

var _tooltip = __webpack_require__(/*! antd/es/tooltip */ "./node_modules/_antd@4.9.1@antd/es/tooltip/index.js");

var _tooltip2 = _interopRequireDefault(_tooltip);

var _message2 = __webpack_require__(/*! antd/es/message */ "./node_modules/_antd@4.9.1@antd/es/message/index.js");

var _message3 = _interopRequireDefault(_message2);

var _divider = __webpack_require__(/*! antd/es/divider */ "./node_modules/_antd@4.9.1@antd/es/divider/index.js");

var _divider2 = _interopRequireDefault(_divider);

var _popconfirm = __webpack_require__(/*! antd/es/popconfirm */ "./node_modules/_antd@4.9.1@antd/es/popconfirm/index.js");

var _popconfirm2 = _interopRequireDefault(_popconfirm);

var _input = __webpack_require__(/*! antd/es/input */ "./node_modules/_antd@4.9.1@antd/es/input/index.js");

var _input2 = _interopRequireDefault(_input);

var _extends = Object.assign || function (target) { for (var i = 1; i < arguments.length; i++) { var source = arguments[i]; for (var key in source) { if (Object.prototype.hasOwnProperty.call(source, key)) { target[key] = source[key]; } } } return target; };

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

__webpack_require__(/*! antd/es/card/style/css */ "./node_modules/_antd@4.9.1@antd/es/card/style/css.js");

__webpack_require__(/*! antd/es/table/style/css */ "./node_modules/_antd@4.9.1@antd/es/table/style/css.js");

__webpack_require__(/*! antd/es/tooltip/style/css */ "./node_modules/_antd@4.9.1@antd/es/tooltip/style/css.js");

__webpack_require__(/*! antd/es/message/style/css */ "./node_modules/_antd@4.9.1@antd/es/message/style/css.js");

__webpack_require__(/*! antd/es/divider/style/css */ "./node_modules/_antd@4.9.1@antd/es/divider/style/css.js");

__webpack_require__(/*! antd/es/popconfirm/style/css */ "./node_modules/_antd@4.9.1@antd/es/popconfirm/style/css.js");

__webpack_require__(/*! antd/es/input/style/css */ "./node_modules/_antd@4.9.1@antd/es/input/style/css.js");

var _react = __webpack_require__(/*! react */ "./node_modules/_react@16.12.0@react/index.js");

var _react2 = _interopRequireDefault(_react);

var _reactRouterDom = __webpack_require__(/*! react-router-dom */ "./node_modules/_react-router-dom@4.2.2@react-router-dom/es/index.js");

var _FunctionService = __webpack_require__(/*! ../../service/FunctionService.jsx */ "./src/service/FunctionService.jsx");

var _FunctionService2 = _interopRequireDefault(_FunctionService);

var _pagination = __webpack_require__(/*! antd/lib/pagination */ "./node_modules/_antd@4.9.1@antd/lib/pagination/index.js");

var _pagination2 = _interopRequireDefault(_pagination);

var _compatible = __webpack_require__(/*! @ant-design/compatible */ "./node_modules/_@ant-design_compatible@1.0.8@@ant-design/compatible/es/index.js");

__webpack_require__(/*! @ant-design/compatible/assets/index.css */ "./node_modules/_@ant-design_compatible@1.0.8@@ant-design/compatible/assets/index.css");

var _LogcalStorge = __webpack_require__(/*! ../../util/LogcalStorge.jsx */ "./src/util/LogcalStorge.jsx");

var _LogcalStorge2 = _interopRequireDefault(_LogcalStorge);

__webpack_require__(/*! ./function.scss */ "./src/page/function/function.scss");

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _toConsumableArray(arr) { if (Array.isArray(arr)) { for (var i = 0, arr2 = Array(arr.length); i < arr.length; i++) { arr2[i] = arr[i]; } return arr2; } else { return Array.from(arr); } }

function _defineProperty(obj, key, value) { if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

function _objectWithoutProperties(obj, keys) { var target = {}; for (var i in obj) { if (keys.indexOf(i) >= 0) continue; if (!Object.prototype.hasOwnProperty.call(obj, i)) continue; target[i] = obj[i]; } return target; }

var FormItem = _compatible.Form.Item;
var Search = _input2.default.Search;
var localStorge = new _LogcalStorge2.default();
var _function = new _FunctionService2.default();

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

var FunctionClass = function (_React$Component2) {
  _inherits(FunctionClass, _React$Component2);

  function FunctionClass(props) {
    _classCallCheck(this, FunctionClass);

    var _this3 = _possibleConstructorReturn(this, (FunctionClass.__proto__ || Object.getPrototypeOf(FunctionClass)).call(this, props));

    _this3.isEditing = function (record) {
      return record.key === _this3.state.editingKey;
    };

    _this3.cancel = function () {
      _this3.setState({ editingKey: '' });
    };

    _this3.state = {
      list: [],
      pageNum: 1,
      perPage: 10,
      listType: 'list',
      className: '',
      editingKey: ''
    };
    _this3.columns = [{
      title: 'ID',
      dataIndex: 'class_id',
      key: 'class_id',
      className: 'headerRow'
    }, {
      title: '类别名称',
      dataIndex: 'class_name',
      key: 'class_name',
      className: 'headerRow',
      editable: true
    }, {
      title: '操作',
      dataIndex: '操作',
      className: 'headerRow',
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
                title: '\u786E\u5B9A\u53D6\u6D88\u5417?',
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
            'div',
            null,
            _react2.default.createElement(
              'a',
              { onClick: function onClick() {
                  return _this3.edit(record.key);
                } },
              '\u7F16\u8F91'
            ),
            _react2.default.createElement(_divider2.default, { type: 'vertical' }),
            _react2.default.createElement(
              'a',
              { onClick: function onClick() {
                  return _this3.deleteFunctionClss('' + record.key);
                }, href: 'javascript:;' },
              '\u5220\u9664'
            )
          )
        );
      }
    }];
    return _this3;
  }

  _createClass(FunctionClass, [{
    key: 'componentDidMount',
    value: function componentDidMount() {
      this.loadFunctionClassList();
    }
  }, {
    key: 'loadFunctionClassList',
    value: function loadFunctionClassList() {
      var _this4 = this;

      var listParam = {};
      listParam.pageNum = this.state.pageNum;
      listParam.perPage = this.state.perPage;

      _function.getAllFunctionClass(listParam).then(function (response) {
        _this4.setState({ list: response.data });
      }, function (errMsg) {
        _this4.setState({
          list: []
        });
        // _mm.errorTips(errMsg);
      });
    }
    // 页数发生变化的时候

  }, {
    key: 'onPageNumChange',
    value: function onPageNumChange(pageNum) {
      var _this5 = this;

      this.setState({
        pageNum: pageNum
      }, function () {
        _this5.loadFunctionClassList();
      });
    }
    // 数据变化的时候

  }, {
    key: 'onValueChange',
    value: function onValueChange(e) {
      var name = e.target.name,
          value = e.target.value.trim();
      this.setState(_defineProperty({}, name, value));
    }
  }, {
    key: 'deleteFunctionClss',
    value: function deleteFunctionClss(id) {
      var _this6 = this;

      if (confirm('确认删除吗？')) {
        var obj = { class_id: id };
        _function.deleteFunctionClss(obj).then(function (response) {
          _message3.default.success("删除成功");
          _this6.loadFunctionClassList();
        }, function (errMsg) {
          _message3.default.error("删除失败");
        });
      }
    }
  }, {
    key: 'saveClick',
    value: function saveClick(value) {
      var _this7 = this;

      if (value != '' && value != null) {
        var obj = { class_name: value };
        _function.saveFunctionClass(obj).then(function (response) {
          if (response.resultCode == "1000") {
            _message3.default.success("保存成功");
            _this7.setState({ className: '' });
          }
          _this7.loadFunctionClassList();
        });
      }
    }
  }, {
    key: 'onValueChange',
    value: function onValueChange(e) {
      var name = e.target.name,
          value = e.target.value.trim();
      this.setState(_defineProperty({}, name, value));
    }
  }, {
    key: 'edit',
    value: function edit(key) {
      this.setState({ editingKey: key });
    }
  }, {
    key: 'save',
    value: function save(form, key) {
      var _this8 = this;

      form.validateFields(function (error, row) {
        if (error) {
          return;
        }
        var newData = [].concat(_toConsumableArray(_this8.state.list));
        var index = newData.findIndex(function (item) {
          return key === item.key;
        });
        if (index > -1) {
          var item = newData[index];
          var obj = row;
          obj.class_id = key;
          _function.updateFunctionClass(obj).then(function (response) {
            if (response.resultCode == "1000") {
              newData.splice(index, 1, _extends({}, item, row));
              _this8.setState({ list: newData, editingKey: '' });
            } else {
              _message3.default.error("保存失败");
              _this8.loadFunctionClassList();
            }
          });
        } else {
          newData.push(row);
          _this8.setState({ list: newData, editingKey: '' });
        }
      });
    }
  }, {
    key: 'render',
    value: function render() {
      var _this9 = this;

      this.state.list.map(function (item, index) {
        item.key = item.class_id;
      });
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
              inputType: col.dataIndex === 'text',
              dataIndex: col.dataIndex,
              title: col.title,
              editing: _this9.isEditing(record)
            };
          }
        });
      });

      return _react2.default.createElement(
        'div',
        { id: 'page-wrapper' },
        _react2.default.createElement(
          _card2.default,
          { title: '\u7C7B\u522B\u7BA1\u7406' },
          _react2.default.createElement(
            _tooltip2.default,
            null,
            _react2.default.createElement(Search, {
              placeholder: '\u8F93\u5165\u65B0\u7C7B\u522B\u540D\u79F0',
              enterButton: '\u65B0\u5EFA\u7C7B\u522B',
              size: 'large',
              style: { width: 300, marginBottom: '10px' },
              name: 'className',
              onSearch: function onSearch(value) {
                return _this9.saveClick(value);
              }
            })
          ),
          _react2.default.createElement(_table2.default, { dataSource: this.state.list, columns: columns, pagination: false,
            components: components, rowClassName: 'editable-row' })
        )
      );
    }
  }]);

  return FunctionClass;
}(_react2.default.Component);

exports.default = FunctionClass;

/***/ })

}]);
//# sourceMappingURL=44.js.map