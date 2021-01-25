(window["webpackJsonp"] = window["webpackJsonp"] || []).push([[18],{

/***/ "./node_modules/_@ant-design_icons-svg@4.1.0@@ant-design/icons-svg/lib/asn/UpOutlined.js":
/*!***********************************************************************************************!*\
  !*** ./node_modules/_@ant-design_icons-svg@4.1.0@@ant-design/icons-svg/lib/asn/UpOutlined.js ***!
  \***********************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";

// This icon file is generated automatically.
Object.defineProperty(exports, "__esModule", { value: true });
var UpOutlined = { "icon": { "tag": "svg", "attrs": { "viewBox": "64 64 896 896", "focusable": "false" }, "children": [{ "tag": "path", "attrs": { "d": "M890.5 755.3L537.9 269.2c-12.8-17.6-39-17.6-51.7 0L133.5 755.3A8 8 0 00140 768h75c5.1 0 9.9-2.5 12.9-6.6L512 369.8l284.1 391.6c3 4.1 7.8 6.6 12.9 6.6h75c6.5 0 10.3-7.4 6.5-12.7z" } }] }, "name": "up", "theme": "outlined" };
exports.default = UpOutlined;


/***/ }),

/***/ "./node_modules/_@ant-design_icons@4.3.0@@ant-design/icons/UpOutlined.js":
/*!*******************************************************************************!*\
  !*** ./node_modules/_@ant-design_icons@4.3.0@@ant-design/icons/UpOutlined.js ***!
  \*******************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = void 0;
  
  var _UpOutlined = _interopRequireDefault(__webpack_require__(/*! ./lib/icons/UpOutlined */ "./node_modules/_@ant-design_icons@4.3.0@@ant-design/icons/lib/icons/UpOutlined.js"));
  
  function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { 'default': obj }; }
  
  var _default = _UpOutlined;
  exports.default = _default;
  module.exports = _default;

/***/ }),

/***/ "./node_modules/_@ant-design_icons@4.3.0@@ant-design/icons/lib/icons/UpOutlined.js":
/*!*****************************************************************************************!*\
  !*** ./node_modules/_@ant-design_icons@4.3.0@@ant-design/icons/lib/icons/UpOutlined.js ***!
  \*****************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var _interopRequireDefault = __webpack_require__(/*! @babel/runtime/helpers/interopRequireDefault */ "./node_modules/_@babel_runtime@7.12.5@@babel/runtime/helpers/interopRequireDefault.js");

var _interopRequireWildcard = __webpack_require__(/*! @babel/runtime/helpers/interopRequireWildcard */ "./node_modules/_@babel_runtime@7.12.5@@babel/runtime/helpers/interopRequireWildcard.js");

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.default = void 0;

var React = _interopRequireWildcard(__webpack_require__(/*! react */ "./node_modules/_react@16.12.0@react/index.js"));

var _UpOutlined = _interopRequireDefault(__webpack_require__(/*! @ant-design/icons-svg/lib/asn/UpOutlined */ "./node_modules/_@ant-design_icons-svg@4.1.0@@ant-design/icons-svg/lib/asn/UpOutlined.js"));

var _AntdIcon = _interopRequireDefault(__webpack_require__(/*! ../components/AntdIcon */ "./node_modules/_@ant-design_icons@4.3.0@@ant-design/icons/lib/components/AntdIcon.js"));

// GENERATE BY ./scripts/generate.ts
// DON NOT EDIT IT MANUALLY
var UpOutlined = function UpOutlined(props, ref) {
  return /*#__PURE__*/React.createElement(_AntdIcon.default, Object.assign({}, props, {
    ref: ref,
    icon: _UpOutlined.default
  }));
};

UpOutlined.displayName = 'UpOutlined';

var _default = /*#__PURE__*/React.forwardRef(UpOutlined);

exports.default = _default;

/***/ }),

/***/ "./node_modules/_antd@4.9.1@antd/es/_util/hooks/usePatchElement.js":
/*!*************************************************************************!*\
  !*** ./node_modules/_antd@4.9.1@antd/es/_util/hooks/usePatchElement.js ***!
  \*************************************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "default", function() { return usePatchElement; });
/* harmony import */ var _babel_runtime_helpers_toConsumableArray__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @babel/runtime/helpers/toConsumableArray */ "./node_modules/_@babel_runtime@7.12.5@@babel/runtime/helpers/toConsumableArray.js");
/* harmony import */ var _babel_runtime_helpers_toConsumableArray__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_babel_runtime_helpers_toConsumableArray__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var _babel_runtime_helpers_slicedToArray__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @babel/runtime/helpers/slicedToArray */ "./node_modules/_@babel_runtime@7.12.5@@babel/runtime/helpers/slicedToArray.js");
/* harmony import */ var _babel_runtime_helpers_slicedToArray__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(_babel_runtime_helpers_slicedToArray__WEBPACK_IMPORTED_MODULE_1__);
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! react */ "./node_modules/_react@16.12.0@react/index.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_2___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_2__);



function usePatchElement() {
  var _React$useState = react__WEBPACK_IMPORTED_MODULE_2__["useState"]([]),
      _React$useState2 = _babel_runtime_helpers_slicedToArray__WEBPACK_IMPORTED_MODULE_1___default()(_React$useState, 2),
      elements = _React$useState2[0],
      setElements = _React$useState2[1];

  function patchElement(element) {
    // append a new element to elements (and create a new ref)
    setElements(function (originElements) {
      return [].concat(_babel_runtime_helpers_toConsumableArray__WEBPACK_IMPORTED_MODULE_0___default()(originElements), [element]);
    }); // return a function that removes the new element out of elements (and create a new ref)
    // it works a little like useEffect

    return function () {
      setElements(function (originElements) {
        return originElements.filter(function (ele) {
          return ele !== element;
        });
      });
    };
  }

  return [elements, patchElement];
}

/***/ }),

/***/ "./node_modules/_antd@4.9.1@antd/es/input-number/index.js":
/*!****************************************************************!*\
  !*** ./node_modules/_antd@4.9.1@antd/es/input-number/index.js ***!
  \****************************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @babel/runtime/helpers/extends */ "./node_modules/_@babel_runtime@7.12.5@@babel/runtime/helpers/extends.js");
/* harmony import */ var _babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var _babel_runtime_helpers_defineProperty__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @babel/runtime/helpers/defineProperty */ "./node_modules/_@babel_runtime@7.12.5@@babel/runtime/helpers/defineProperty.js");
/* harmony import */ var _babel_runtime_helpers_defineProperty__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(_babel_runtime_helpers_defineProperty__WEBPACK_IMPORTED_MODULE_1__);
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! react */ "./node_modules/_react@16.12.0@react/index.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_2___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_2__);
/* harmony import */ var classnames__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! classnames */ "./node_modules/_classnames@2.2.6@classnames/index.js");
/* harmony import */ var classnames__WEBPACK_IMPORTED_MODULE_3___default = /*#__PURE__*/__webpack_require__.n(classnames__WEBPACK_IMPORTED_MODULE_3__);
/* harmony import */ var rc_input_number__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! rc-input-number */ "./node_modules/_rc-input-number@6.1.2@rc-input-number/es/index.js");
/* harmony import */ var _ant_design_icons_UpOutlined__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ant-design/icons/UpOutlined */ "./node_modules/_@ant-design_icons@4.3.0@@ant-design/icons/UpOutlined.js");
/* harmony import */ var _ant_design_icons_UpOutlined__WEBPACK_IMPORTED_MODULE_5___default = /*#__PURE__*/__webpack_require__.n(_ant_design_icons_UpOutlined__WEBPACK_IMPORTED_MODULE_5__);
/* harmony import */ var _ant_design_icons_DownOutlined__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @ant-design/icons/DownOutlined */ "./node_modules/_@ant-design_icons@4.3.0@@ant-design/icons/DownOutlined.js");
/* harmony import */ var _ant_design_icons_DownOutlined__WEBPACK_IMPORTED_MODULE_6___default = /*#__PURE__*/__webpack_require__.n(_ant_design_icons_DownOutlined__WEBPACK_IMPORTED_MODULE_6__);
/* harmony import */ var _config_provider__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ../config-provider */ "./node_modules/_antd@4.9.1@antd/es/config-provider/index.js");
/* harmony import */ var _config_provider_SizeContext__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ../config-provider/SizeContext */ "./node_modules/_antd@4.9.1@antd/es/config-provider/SizeContext.js");



var __rest = undefined && undefined.__rest || function (s, e) {
  var t = {};

  for (var p in s) {
    if (Object.prototype.hasOwnProperty.call(s, p) && e.indexOf(p) < 0) t[p] = s[p];
  }

  if (s != null && typeof Object.getOwnPropertySymbols === "function") for (var i = 0, p = Object.getOwnPropertySymbols(s); i < p.length; i++) {
    if (e.indexOf(p[i]) < 0 && Object.prototype.propertyIsEnumerable.call(s, p[i])) t[p[i]] = s[p[i]];
  }
  return t;
};








var InputNumber = /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_2__["forwardRef"](function (props, ref) {
  var _classNames;

  var _React$useContext = react__WEBPACK_IMPORTED_MODULE_2__["useContext"](_config_provider__WEBPACK_IMPORTED_MODULE_7__["ConfigContext"]),
      getPrefixCls = _React$useContext.getPrefixCls,
      direction = _React$useContext.direction;

  var size = react__WEBPACK_IMPORTED_MODULE_2__["useContext"](_config_provider_SizeContext__WEBPACK_IMPORTED_MODULE_8__["default"]);

  var className = props.className,
      customizeSize = props.size,
      customizePrefixCls = props.prefixCls,
      readOnly = props.readOnly,
      others = __rest(props, ["className", "size", "prefixCls", "readOnly"]);

  var prefixCls = getPrefixCls('input-number', customizePrefixCls);
  var upIcon = /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_2__["createElement"](_ant_design_icons_UpOutlined__WEBPACK_IMPORTED_MODULE_5___default.a, {
    className: "".concat(prefixCls, "-handler-up-inner")
  });
  var downIcon = /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_2__["createElement"](_ant_design_icons_DownOutlined__WEBPACK_IMPORTED_MODULE_6___default.a, {
    className: "".concat(prefixCls, "-handler-down-inner")
  });
  var mergeSize = customizeSize || size;
  var inputNumberClass = classnames__WEBPACK_IMPORTED_MODULE_3___default()((_classNames = {}, _babel_runtime_helpers_defineProperty__WEBPACK_IMPORTED_MODULE_1___default()(_classNames, "".concat(prefixCls, "-lg"), mergeSize === 'large'), _babel_runtime_helpers_defineProperty__WEBPACK_IMPORTED_MODULE_1___default()(_classNames, "".concat(prefixCls, "-sm"), mergeSize === 'small'), _babel_runtime_helpers_defineProperty__WEBPACK_IMPORTED_MODULE_1___default()(_classNames, "".concat(prefixCls, "-rtl"), direction === 'rtl'), _babel_runtime_helpers_defineProperty__WEBPACK_IMPORTED_MODULE_1___default()(_classNames, "".concat(prefixCls, "-readonly"), readOnly), _classNames), className);
  return /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_2__["createElement"](rc_input_number__WEBPACK_IMPORTED_MODULE_4__["default"], _babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_0___default()({
    ref: ref,
    className: inputNumberClass,
    upHandler: upIcon,
    downHandler: downIcon,
    prefixCls: prefixCls,
    readOnly: readOnly
  }, others));
});
InputNumber.defaultProps = {
  step: 1
};
/* harmony default export */ __webpack_exports__["default"] = (InputNumber);

/***/ }),

/***/ "./node_modules/_antd@4.9.1@antd/es/modal/ActionButton.js":
/*!****************************************************************!*\
  !*** ./node_modules/_antd@4.9.1@antd/es/modal/ActionButton.js ***!
  \****************************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @babel/runtime/helpers/extends */ "./node_modules/_@babel_runtime@7.12.5@@babel/runtime/helpers/extends.js");
/* harmony import */ var _babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var _babel_runtime_helpers_slicedToArray__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @babel/runtime/helpers/slicedToArray */ "./node_modules/_@babel_runtime@7.12.5@@babel/runtime/helpers/slicedToArray.js");
/* harmony import */ var _babel_runtime_helpers_slicedToArray__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(_babel_runtime_helpers_slicedToArray__WEBPACK_IMPORTED_MODULE_1__);
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! react */ "./node_modules/_react@16.12.0@react/index.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_2___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_2__);
/* harmony import */ var _button__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../button */ "./node_modules/_antd@4.9.1@antd/es/button/index.js");
/* harmony import */ var _button_button__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../button/button */ "./node_modules/_antd@4.9.1@antd/es/button/button.js");






var ActionButton = function ActionButton(props) {
  var clickedRef = react__WEBPACK_IMPORTED_MODULE_2__["useRef"](false);
  var ref = react__WEBPACK_IMPORTED_MODULE_2__["useRef"]();

  var _React$useState = react__WEBPACK_IMPORTED_MODULE_2__["useState"](false),
      _React$useState2 = _babel_runtime_helpers_slicedToArray__WEBPACK_IMPORTED_MODULE_1___default()(_React$useState, 2),
      loading = _React$useState2[0],
      setLoading = _React$useState2[1];

  react__WEBPACK_IMPORTED_MODULE_2__["useEffect"](function () {
    var timeoutId;

    if (props.autoFocus) {
      var $this = ref.current;
      timeoutId = setTimeout(function () {
        return $this.focus();
      });
    }

    return function () {
      if (timeoutId) {
        clearTimeout(timeoutId);
      }
    };
  }, []);

  var handlePromiseOnOk = function handlePromiseOnOk(returnValueOfOnOk) {
    var closeModal = props.closeModal;

    if (!returnValueOfOnOk || !returnValueOfOnOk.then) {
      return;
    }

    setLoading(true);
    returnValueOfOnOk.then(function () {
      // It's unnecessary to set loading=false, for the Modal will be unmounted after close.
      // setState({ loading: false });
      closeModal.apply(void 0, arguments);
    }, function (e) {
      // Emit error when catch promise reject
      // eslint-disable-next-line no-console
      console.error(e); // See: https://github.com/ant-design/ant-design/issues/6183

      setLoading(false);
      clickedRef.current = false;
    });
  };

  var onClick = function onClick() {
    var actionFn = props.actionFn,
        closeModal = props.closeModal;

    if (clickedRef.current) {
      return;
    }

    clickedRef.current = true;

    if (!actionFn) {
      closeModal();
      return;
    }

    var returnValueOfOnOk;

    if (actionFn.length) {
      returnValueOfOnOk = actionFn(closeModal); // https://github.com/ant-design/ant-design/issues/23358

      clickedRef.current = false;
    } else {
      returnValueOfOnOk = actionFn();

      if (!returnValueOfOnOk) {
        closeModal();
        return;
      }
    }

    handlePromiseOnOk(returnValueOfOnOk);
  };

  var type = props.type,
      children = props.children,
      prefixCls = props.prefixCls,
      buttonProps = props.buttonProps;
  return /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_2__["createElement"](_button__WEBPACK_IMPORTED_MODULE_3__["default"], _babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_0___default()({}, Object(_button_button__WEBPACK_IMPORTED_MODULE_4__["convertLegacyProps"])(type), {
    onClick: onClick,
    loading: loading,
    prefixCls: prefixCls
  }, buttonProps, {
    ref: ref
  }), children);
};

/* harmony default export */ __webpack_exports__["default"] = (ActionButton);

/***/ }),

/***/ "./node_modules/_antd@4.9.1@antd/es/modal/ConfirmDialog.js":
/*!*****************************************************************!*\
  !*** ./node_modules/_antd@4.9.1@antd/es/modal/ConfirmDialog.js ***!
  \*****************************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _babel_runtime_helpers_defineProperty__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @babel/runtime/helpers/defineProperty */ "./node_modules/_@babel_runtime@7.12.5@@babel/runtime/helpers/defineProperty.js");
/* harmony import */ var _babel_runtime_helpers_defineProperty__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_babel_runtime_helpers_defineProperty__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! react */ "./node_modules/_react@16.12.0@react/index.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_1__);
/* harmony import */ var classnames__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! classnames */ "./node_modules/_classnames@2.2.6@classnames/index.js");
/* harmony import */ var classnames__WEBPACK_IMPORTED_MODULE_2___default = /*#__PURE__*/__webpack_require__.n(classnames__WEBPACK_IMPORTED_MODULE_2__);
/* harmony import */ var _Modal__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./Modal */ "./node_modules/_antd@4.9.1@antd/es/modal/Modal.js");
/* harmony import */ var _ActionButton__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./ActionButton */ "./node_modules/_antd@4.9.1@antd/es/modal/ActionButton.js");
/* harmony import */ var _util_devWarning__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../_util/devWarning */ "./node_modules/_antd@4.9.1@antd/es/_util/devWarning.js");
/* harmony import */ var _config_provider__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ../config-provider */ "./node_modules/_antd@4.9.1@antd/es/config-provider/index.js");








var ConfirmDialog = function ConfirmDialog(props) {
  var icon = props.icon,
      onCancel = props.onCancel,
      onOk = props.onOk,
      close = props.close,
      zIndex = props.zIndex,
      afterClose = props.afterClose,
      visible = props.visible,
      keyboard = props.keyboard,
      centered = props.centered,
      getContainer = props.getContainer,
      maskStyle = props.maskStyle,
      okText = props.okText,
      okButtonProps = props.okButtonProps,
      cancelText = props.cancelText,
      cancelButtonProps = props.cancelButtonProps,
      direction = props.direction,
      prefixCls = props.prefixCls,
      rootPrefixCls = props.rootPrefixCls,
      bodyStyle = props.bodyStyle,
      _props$closable = props.closable,
      closable = _props$closable === void 0 ? false : _props$closable,
      closeIcon = props.closeIcon,
      modalRender = props.modalRender,
      focusTriggerAfterClose = props.focusTriggerAfterClose;
  Object(_util_devWarning__WEBPACK_IMPORTED_MODULE_5__["default"])(!(typeof icon === 'string' && icon.length > 2), 'Modal', "`icon` is using ReactNode instead of string naming in v4. Please check `".concat(icon, "` at https://ant.design/components/icon")); // 支持传入{ icon: null }来隐藏`Modal.confirm`默认的Icon

  var okType = props.okType || 'primary';
  var contentPrefixCls = "".concat(prefixCls, "-confirm"); // 默认为 true，保持向下兼容

  var okCancel = 'okCancel' in props ? props.okCancel : true;
  var width = props.width || 416;
  var style = props.style || {};
  var mask = props.mask === undefined ? true : props.mask; // 默认为 false，保持旧版默认行为

  var maskClosable = props.maskClosable === undefined ? false : props.maskClosable;
  var autoFocusButton = props.autoFocusButton === null ? false : props.autoFocusButton || 'ok';
  var transitionName = props.transitionName || 'zoom';
  var maskTransitionName = props.maskTransitionName || 'fade';
  var classString = classnames__WEBPACK_IMPORTED_MODULE_2___default()(contentPrefixCls, "".concat(contentPrefixCls, "-").concat(props.type), _babel_runtime_helpers_defineProperty__WEBPACK_IMPORTED_MODULE_0___default()({}, "".concat(contentPrefixCls, "-rtl"), direction === 'rtl'), props.className);
  var cancelButton = okCancel && /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_1__["createElement"](_ActionButton__WEBPACK_IMPORTED_MODULE_4__["default"], {
    actionFn: onCancel,
    closeModal: close,
    autoFocus: autoFocusButton === 'cancel',
    buttonProps: cancelButtonProps,
    prefixCls: "".concat(rootPrefixCls, "-btn")
  }, cancelText);
  return /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_1__["createElement"](_Modal__WEBPACK_IMPORTED_MODULE_3__["default"], {
    prefixCls: prefixCls,
    className: classString,
    wrapClassName: classnames__WEBPACK_IMPORTED_MODULE_2___default()(_babel_runtime_helpers_defineProperty__WEBPACK_IMPORTED_MODULE_0___default()({}, "".concat(contentPrefixCls, "-centered"), !!props.centered)),
    onCancel: function onCancel() {
      return close({
        triggerCancel: true
      });
    },
    visible: visible,
    title: "",
    transitionName: transitionName,
    footer: "",
    maskTransitionName: maskTransitionName,
    mask: mask,
    maskClosable: maskClosable,
    maskStyle: maskStyle,
    style: style,
    width: width,
    zIndex: zIndex,
    afterClose: afterClose,
    keyboard: keyboard,
    centered: centered,
    getContainer: getContainer,
    closable: closable,
    closeIcon: closeIcon,
    modalRender: modalRender,
    focusTriggerAfterClose: focusTriggerAfterClose
  }, /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_1__["createElement"]("div", {
    className: "".concat(contentPrefixCls, "-body-wrapper")
  }, /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_1__["createElement"](_config_provider__WEBPACK_IMPORTED_MODULE_6__["default"], {
    prefixCls: rootPrefixCls
  }, /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_1__["createElement"]("div", {
    className: "".concat(contentPrefixCls, "-body"),
    style: bodyStyle
  }, icon, props.title === undefined ? null : /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_1__["createElement"]("span", {
    className: "".concat(contentPrefixCls, "-title")
  }, props.title), /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_1__["createElement"]("div", {
    className: "".concat(contentPrefixCls, "-content")
  }, props.content))), /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_1__["createElement"]("div", {
    className: "".concat(contentPrefixCls, "-btns")
  }, cancelButton, /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_1__["createElement"](_ActionButton__WEBPACK_IMPORTED_MODULE_4__["default"], {
    type: okType,
    actionFn: onOk,
    closeModal: close,
    autoFocus: autoFocusButton === 'ok',
    buttonProps: okButtonProps,
    prefixCls: "".concat(rootPrefixCls, "-btn")
  }, okText))));
};

/* harmony default export */ __webpack_exports__["default"] = (ConfirmDialog);

/***/ }),

/***/ "./node_modules/_antd@4.9.1@antd/es/modal/Modal.js":
/*!*********************************************************!*\
  !*** ./node_modules/_antd@4.9.1@antd/es/modal/Modal.js ***!
  \*********************************************************/
/*! exports provided: destroyFns, default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "destroyFns", function() { return destroyFns; });
/* harmony import */ var _babel_runtime_helpers_defineProperty__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @babel/runtime/helpers/defineProperty */ "./node_modules/_@babel_runtime@7.12.5@@babel/runtime/helpers/defineProperty.js");
/* harmony import */ var _babel_runtime_helpers_defineProperty__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_babel_runtime_helpers_defineProperty__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var _babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @babel/runtime/helpers/extends */ "./node_modules/_@babel_runtime@7.12.5@@babel/runtime/helpers/extends.js");
/* harmony import */ var _babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(_babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_1__);
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! react */ "./node_modules/_react@16.12.0@react/index.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_2___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_2__);
/* harmony import */ var rc_dialog__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! rc-dialog */ "./node_modules/_rc-dialog@8.4.6@rc-dialog/es/index.js");
/* harmony import */ var classnames__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! classnames */ "./node_modules/_classnames@2.2.6@classnames/index.js");
/* harmony import */ var classnames__WEBPACK_IMPORTED_MODULE_4___default = /*#__PURE__*/__webpack_require__.n(classnames__WEBPACK_IMPORTED_MODULE_4__);
/* harmony import */ var _ant_design_icons_CloseOutlined__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ant-design/icons/CloseOutlined */ "./node_modules/_@ant-design_icons@4.3.0@@ant-design/icons/CloseOutlined.js");
/* harmony import */ var _ant_design_icons_CloseOutlined__WEBPACK_IMPORTED_MODULE_5___default = /*#__PURE__*/__webpack_require__.n(_ant_design_icons_CloseOutlined__WEBPACK_IMPORTED_MODULE_5__);
/* harmony import */ var _useModal__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./useModal */ "./node_modules/_antd@4.9.1@antd/es/modal/useModal/index.js");
/* harmony import */ var _locale__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ./locale */ "./node_modules/_antd@4.9.1@antd/es/modal/locale.js");
/* harmony import */ var _button__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ../button */ "./node_modules/_antd@4.9.1@antd/es/button/index.js");
/* harmony import */ var _button_button__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! ../button/button */ "./node_modules/_antd@4.9.1@antd/es/button/button.js");
/* harmony import */ var _locale_provider_LocaleReceiver__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! ../locale-provider/LocaleReceiver */ "./node_modules/_antd@4.9.1@antd/es/locale-provider/LocaleReceiver.js");
/* harmony import */ var _config_provider__WEBPACK_IMPORTED_MODULE_11__ = __webpack_require__(/*! ../config-provider */ "./node_modules/_antd@4.9.1@antd/es/config-provider/index.js");



var __rest = undefined && undefined.__rest || function (s, e) {
  var t = {};

  for (var p in s) {
    if (Object.prototype.hasOwnProperty.call(s, p) && e.indexOf(p) < 0) t[p] = s[p];
  }

  if (s != null && typeof Object.getOwnPropertySymbols === "function") for (var i = 0, p = Object.getOwnPropertySymbols(s); i < p.length; i++) {
    if (e.indexOf(p[i]) < 0 && Object.prototype.propertyIsEnumerable.call(s, p[i])) t[p[i]] = s[p[i]];
  }
  return t;
};











var mousePosition;
var destroyFns = []; // ref: https://github.com/ant-design/ant-design/issues/15795

var getClickPosition = function getClickPosition(e) {
  mousePosition = {
    x: e.pageX,
    y: e.pageY
  }; // 100ms 内发生过点击事件，则从点击位置动画展示
  // 否则直接 zoom 展示
  // 这样可以兼容非点击方式展开

  setTimeout(function () {
    mousePosition = null;
  }, 100);
}; // 只有点击事件支持从鼠标位置动画展开


if (typeof window !== 'undefined' && window.document && window.document.documentElement) {
  document.documentElement.addEventListener('click', getClickPosition, true);
}

var Modal = function Modal(props) {
  var _classNames;

  var _React$useContext = react__WEBPACK_IMPORTED_MODULE_2__["useContext"](_config_provider__WEBPACK_IMPORTED_MODULE_11__["ConfigContext"]),
      getContextPopupContainer = _React$useContext.getPopupContainer,
      getPrefixCls = _React$useContext.getPrefixCls,
      direction = _React$useContext.direction;

  var handleCancel = function handleCancel(e) {
    var onCancel = props.onCancel;

    if (onCancel) {
      onCancel(e);
    }
  };

  var handleOk = function handleOk(e) {
    var onOk = props.onOk;

    if (onOk) {
      onOk(e);
    }
  };

  var renderFooter = function renderFooter(locale) {
    var okText = props.okText,
        okType = props.okType,
        cancelText = props.cancelText,
        confirmLoading = props.confirmLoading;
    return /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_2__["createElement"](react__WEBPACK_IMPORTED_MODULE_2__["Fragment"], null, /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_2__["createElement"](_button__WEBPACK_IMPORTED_MODULE_8__["default"], _babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_1___default()({
      onClick: handleCancel
    }, props.cancelButtonProps), cancelText || locale.cancelText), /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_2__["createElement"](_button__WEBPACK_IMPORTED_MODULE_8__["default"], _babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_1___default()({}, Object(_button_button__WEBPACK_IMPORTED_MODULE_9__["convertLegacyProps"])(okType), {
      loading: confirmLoading,
      onClick: handleOk
    }, props.okButtonProps), okText || locale.okText));
  };

  var customizePrefixCls = props.prefixCls,
      footer = props.footer,
      visible = props.visible,
      wrapClassName = props.wrapClassName,
      centered = props.centered,
      getContainer = props.getContainer,
      closeIcon = props.closeIcon,
      _props$focusTriggerAf = props.focusTriggerAfterClose,
      focusTriggerAfterClose = _props$focusTriggerAf === void 0 ? true : _props$focusTriggerAf,
      restProps = __rest(props, ["prefixCls", "footer", "visible", "wrapClassName", "centered", "getContainer", "closeIcon", "focusTriggerAfterClose"]);

  var prefixCls = getPrefixCls('modal', customizePrefixCls);
  var defaultFooter = /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_2__["createElement"](_locale_provider_LocaleReceiver__WEBPACK_IMPORTED_MODULE_10__["default"], {
    componentName: "Modal",
    defaultLocale: Object(_locale__WEBPACK_IMPORTED_MODULE_7__["getConfirmLocale"])()
  }, renderFooter);
  var closeIconToRender = /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_2__["createElement"]("span", {
    className: "".concat(prefixCls, "-close-x")
  }, closeIcon || /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_2__["createElement"](_ant_design_icons_CloseOutlined__WEBPACK_IMPORTED_MODULE_5___default.a, {
    className: "".concat(prefixCls, "-close-icon")
  }));
  var wrapClassNameExtended = classnames__WEBPACK_IMPORTED_MODULE_4___default()(wrapClassName, (_classNames = {}, _babel_runtime_helpers_defineProperty__WEBPACK_IMPORTED_MODULE_0___default()(_classNames, "".concat(prefixCls, "-centered"), !!centered), _babel_runtime_helpers_defineProperty__WEBPACK_IMPORTED_MODULE_0___default()(_classNames, "".concat(prefixCls, "-wrap-rtl"), direction === 'rtl'), _classNames));
  return /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_2__["createElement"](rc_dialog__WEBPACK_IMPORTED_MODULE_3__["default"], _babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_1___default()({}, restProps, {
    getContainer: getContainer === undefined ? getContextPopupContainer : getContainer,
    prefixCls: prefixCls,
    wrapClassName: wrapClassNameExtended,
    footer: footer === undefined ? defaultFooter : footer,
    visible: visible,
    mousePosition: mousePosition,
    onClose: handleCancel,
    closeIcon: closeIconToRender,
    focusTriggerAfterClose: focusTriggerAfterClose
  }));
};

Modal.useModal = _useModal__WEBPACK_IMPORTED_MODULE_6__["default"];
Modal.defaultProps = {
  width: 520,
  transitionName: 'zoom',
  maskTransitionName: 'fade',
  confirmLoading: false,
  visible: false,
  okType: 'primary'
};
/* harmony default export */ __webpack_exports__["default"] = (Modal);

/***/ }),

/***/ "./node_modules/_antd@4.9.1@antd/es/modal/confirm.js":
/*!***********************************************************!*\
  !*** ./node_modules/_antd@4.9.1@antd/es/modal/confirm.js ***!
  \***********************************************************/
/*! exports provided: default, withWarn, withInfo, withSuccess, withError, withConfirm, globalConfig */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "default", function() { return confirm; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "withWarn", function() { return withWarn; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "withInfo", function() { return withInfo; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "withSuccess", function() { return withSuccess; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "withError", function() { return withError; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "withConfirm", function() { return withConfirm; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "globalConfig", function() { return globalConfig; });
/* harmony import */ var _babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @babel/runtime/helpers/extends */ "./node_modules/_@babel_runtime@7.12.5@@babel/runtime/helpers/extends.js");
/* harmony import */ var _babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! react */ "./node_modules/_react@16.12.0@react/index.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_1__);
/* harmony import */ var react_dom__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! react-dom */ "./node_modules/_react-dom@16.12.0@react-dom/index.js");
/* harmony import */ var react_dom__WEBPACK_IMPORTED_MODULE_2___default = /*#__PURE__*/__webpack_require__.n(react_dom__WEBPACK_IMPORTED_MODULE_2__);
/* harmony import */ var _ant_design_icons_InfoCircleOutlined__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @ant-design/icons/InfoCircleOutlined */ "./node_modules/_@ant-design_icons@4.3.0@@ant-design/icons/InfoCircleOutlined.js");
/* harmony import */ var _ant_design_icons_InfoCircleOutlined__WEBPACK_IMPORTED_MODULE_3___default = /*#__PURE__*/__webpack_require__.n(_ant_design_icons_InfoCircleOutlined__WEBPACK_IMPORTED_MODULE_3__);
/* harmony import */ var _ant_design_icons_CheckCircleOutlined__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @ant-design/icons/CheckCircleOutlined */ "./node_modules/_@ant-design_icons@4.3.0@@ant-design/icons/CheckCircleOutlined.js");
/* harmony import */ var _ant_design_icons_CheckCircleOutlined__WEBPACK_IMPORTED_MODULE_4___default = /*#__PURE__*/__webpack_require__.n(_ant_design_icons_CheckCircleOutlined__WEBPACK_IMPORTED_MODULE_4__);
/* harmony import */ var _ant_design_icons_CloseCircleOutlined__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ant-design/icons/CloseCircleOutlined */ "./node_modules/_@ant-design_icons@4.3.0@@ant-design/icons/CloseCircleOutlined.js");
/* harmony import */ var _ant_design_icons_CloseCircleOutlined__WEBPACK_IMPORTED_MODULE_5___default = /*#__PURE__*/__webpack_require__.n(_ant_design_icons_CloseCircleOutlined__WEBPACK_IMPORTED_MODULE_5__);
/* harmony import */ var _ant_design_icons_ExclamationCircleOutlined__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @ant-design/icons/ExclamationCircleOutlined */ "./node_modules/_@ant-design_icons@4.3.0@@ant-design/icons/ExclamationCircleOutlined.js");
/* harmony import */ var _ant_design_icons_ExclamationCircleOutlined__WEBPACK_IMPORTED_MODULE_6___default = /*#__PURE__*/__webpack_require__.n(_ant_design_icons_ExclamationCircleOutlined__WEBPACK_IMPORTED_MODULE_6__);
/* harmony import */ var _locale__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ./locale */ "./node_modules/_antd@4.9.1@antd/es/modal/locale.js");
/* harmony import */ var _Modal__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ./Modal */ "./node_modules/_antd@4.9.1@antd/es/modal/Modal.js");
/* harmony import */ var _ConfirmDialog__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! ./ConfirmDialog */ "./node_modules/_antd@4.9.1@antd/es/modal/ConfirmDialog.js");


var __rest = undefined && undefined.__rest || function (s, e) {
  var t = {};

  for (var p in s) {
    if (Object.prototype.hasOwnProperty.call(s, p) && e.indexOf(p) < 0) t[p] = s[p];
  }

  if (s != null && typeof Object.getOwnPropertySymbols === "function") for (var i = 0, p = Object.getOwnPropertySymbols(s); i < p.length; i++) {
    if (e.indexOf(p[i]) < 0 && Object.prototype.propertyIsEnumerable.call(s, p[i])) t[p[i]] = s[p[i]];
  }
  return t;
};










var defaultRootPrefixCls = 'ant';

function getRootPrefixCls() {
  return defaultRootPrefixCls;
}

function confirm(config) {
  var div = document.createElement('div');
  document.body.appendChild(div); // eslint-disable-next-line @typescript-eslint/no-use-before-define

  var currentConfig = _babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_0___default()(_babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_0___default()({}, config), {
    close: close,
    visible: true
  });

  function destroy() {
    var unmountResult = react_dom__WEBPACK_IMPORTED_MODULE_2__["unmountComponentAtNode"](div);

    if (unmountResult && div.parentNode) {
      div.parentNode.removeChild(div);
    }

    for (var _len = arguments.length, args = new Array(_len), _key = 0; _key < _len; _key++) {
      args[_key] = arguments[_key];
    }

    var triggerCancel = args.some(function (param) {
      return param && param.triggerCancel;
    });

    if (config.onCancel && triggerCancel) {
      config.onCancel.apply(config, args);
    }

    for (var i = 0; i < _Modal__WEBPACK_IMPORTED_MODULE_8__["destroyFns"].length; i++) {
      var fn = _Modal__WEBPACK_IMPORTED_MODULE_8__["destroyFns"][i]; // eslint-disable-next-line @typescript-eslint/no-use-before-define

      if (fn === close) {
        _Modal__WEBPACK_IMPORTED_MODULE_8__["destroyFns"].splice(i, 1);
        break;
      }
    }
  }

  function render(_a) {
    var okText = _a.okText,
        cancelText = _a.cancelText,
        prefixCls = _a.prefixCls,
        props = __rest(_a, ["okText", "cancelText", "prefixCls"]);
    /**
     * https://github.com/ant-design/ant-design/issues/23623
     * Sync render blocks React event. Let's make this async.
     */


    setTimeout(function () {
      var runtimeLocale = Object(_locale__WEBPACK_IMPORTED_MODULE_7__["getConfirmLocale"])();
      react_dom__WEBPACK_IMPORTED_MODULE_2__["render"]( /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_1__["createElement"](_ConfirmDialog__WEBPACK_IMPORTED_MODULE_9__["default"], _babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_0___default()({}, props, {
        prefixCls: prefixCls || "".concat(getRootPrefixCls(), "-modal"),
        rootPrefixCls: getRootPrefixCls(),
        okText: okText || (props.okCancel ? runtimeLocale.okText : runtimeLocale.justOkText),
        cancelText: cancelText || runtimeLocale.cancelText
      })), div);
    });
  }

  function close() {
    for (var _len2 = arguments.length, args = new Array(_len2), _key2 = 0; _key2 < _len2; _key2++) {
      args[_key2] = arguments[_key2];
    }

    currentConfig = _babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_0___default()(_babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_0___default()({}, currentConfig), {
      visible: false,
      afterClose: destroy.bind.apply(destroy, [this].concat(args))
    });
    render(currentConfig);
  }

  function update(configUpdate) {
    if (typeof configUpdate === 'function') {
      currentConfig = configUpdate(currentConfig);
    } else {
      currentConfig = _babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_0___default()(_babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_0___default()({}, currentConfig), configUpdate);
    }

    render(currentConfig);
  }

  render(currentConfig);
  _Modal__WEBPACK_IMPORTED_MODULE_8__["destroyFns"].push(close);
  return {
    destroy: close,
    update: update
  };
}
function withWarn(props) {
  return _babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_0___default()({
    type: 'warning',
    icon: /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_1__["createElement"](_ant_design_icons_ExclamationCircleOutlined__WEBPACK_IMPORTED_MODULE_6___default.a, null),
    okCancel: false
  }, props);
}
function withInfo(props) {
  return _babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_0___default()({
    type: 'info',
    icon: /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_1__["createElement"](_ant_design_icons_InfoCircleOutlined__WEBPACK_IMPORTED_MODULE_3___default.a, null),
    okCancel: false
  }, props);
}
function withSuccess(props) {
  return _babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_0___default()({
    type: 'success',
    icon: /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_1__["createElement"](_ant_design_icons_CheckCircleOutlined__WEBPACK_IMPORTED_MODULE_4___default.a, null),
    okCancel: false
  }, props);
}
function withError(props) {
  return _babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_0___default()({
    type: 'error',
    icon: /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_1__["createElement"](_ant_design_icons_CloseCircleOutlined__WEBPACK_IMPORTED_MODULE_5___default.a, null),
    okCancel: false
  }, props);
}
function withConfirm(props) {
  return _babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_0___default()({
    type: 'confirm',
    icon: /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_1__["createElement"](_ant_design_icons_ExclamationCircleOutlined__WEBPACK_IMPORTED_MODULE_6___default.a, null),
    okCancel: true
  }, props);
}
function globalConfig(_ref) {
  var rootPrefixCls = _ref.rootPrefixCls;

  if (rootPrefixCls) {
    defaultRootPrefixCls = rootPrefixCls;
  }
}

/***/ }),

/***/ "./node_modules/_antd@4.9.1@antd/es/modal/index.js":
/*!*********************************************************!*\
  !*** ./node_modules/_antd@4.9.1@antd/es/modal/index.js ***!
  \*********************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _Modal__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./Modal */ "./node_modules/_antd@4.9.1@antd/es/modal/Modal.js");
/* harmony import */ var _confirm__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./confirm */ "./node_modules/_antd@4.9.1@antd/es/modal/confirm.js");



function modalWarn(props) {
  return Object(_confirm__WEBPACK_IMPORTED_MODULE_1__["default"])(Object(_confirm__WEBPACK_IMPORTED_MODULE_1__["withWarn"])(props));
}

var Modal = _Modal__WEBPACK_IMPORTED_MODULE_0__["default"];

Modal.info = function infoFn(props) {
  return Object(_confirm__WEBPACK_IMPORTED_MODULE_1__["default"])(Object(_confirm__WEBPACK_IMPORTED_MODULE_1__["withInfo"])(props));
};

Modal.success = function successFn(props) {
  return Object(_confirm__WEBPACK_IMPORTED_MODULE_1__["default"])(Object(_confirm__WEBPACK_IMPORTED_MODULE_1__["withSuccess"])(props));
};

Modal.error = function errorFn(props) {
  return Object(_confirm__WEBPACK_IMPORTED_MODULE_1__["default"])(Object(_confirm__WEBPACK_IMPORTED_MODULE_1__["withError"])(props));
};

Modal.warning = modalWarn;
Modal.warn = modalWarn;

Modal.confirm = function confirmFn(props) {
  return Object(_confirm__WEBPACK_IMPORTED_MODULE_1__["default"])(Object(_confirm__WEBPACK_IMPORTED_MODULE_1__["withConfirm"])(props));
};

Modal.destroyAll = function destroyAllFn() {
  while (_Modal__WEBPACK_IMPORTED_MODULE_0__["destroyFns"].length) {
    var close = _Modal__WEBPACK_IMPORTED_MODULE_0__["destroyFns"].pop();

    if (close) {
      close();
    }
  }
};

Modal.config = _confirm__WEBPACK_IMPORTED_MODULE_1__["globalConfig"];
/* harmony default export */ __webpack_exports__["default"] = (Modal);

/***/ }),

/***/ "./node_modules/_antd@4.9.1@antd/es/modal/useModal/HookModal.js":
/*!**********************************************************************!*\
  !*** ./node_modules/_antd@4.9.1@antd/es/modal/useModal/HookModal.js ***!
  \**********************************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @babel/runtime/helpers/extends */ "./node_modules/_@babel_runtime@7.12.5@@babel/runtime/helpers/extends.js");
/* harmony import */ var _babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var _babel_runtime_helpers_slicedToArray__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @babel/runtime/helpers/slicedToArray */ "./node_modules/_@babel_runtime@7.12.5@@babel/runtime/helpers/slicedToArray.js");
/* harmony import */ var _babel_runtime_helpers_slicedToArray__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(_babel_runtime_helpers_slicedToArray__WEBPACK_IMPORTED_MODULE_1__);
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! react */ "./node_modules/_react@16.12.0@react/index.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_2___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_2__);
/* harmony import */ var _ConfirmDialog__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../ConfirmDialog */ "./node_modules/_antd@4.9.1@antd/es/modal/ConfirmDialog.js");
/* harmony import */ var _locale_default__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../../locale/default */ "./node_modules/_antd@4.9.1@antd/es/locale/default.js");
/* harmony import */ var _locale_provider_LocaleReceiver__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../../locale-provider/LocaleReceiver */ "./node_modules/_antd@4.9.1@antd/es/locale-provider/LocaleReceiver.js");
/* harmony import */ var _config_provider__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ../../config-provider */ "./node_modules/_antd@4.9.1@antd/es/config-provider/index.js");








var HookModal = function HookModal(_ref, ref) {
  var afterClose = _ref.afterClose,
      config = _ref.config;

  var _React$useState = react__WEBPACK_IMPORTED_MODULE_2__["useState"](true),
      _React$useState2 = _babel_runtime_helpers_slicedToArray__WEBPACK_IMPORTED_MODULE_1___default()(_React$useState, 2),
      visible = _React$useState2[0],
      setVisible = _React$useState2[1];

  var _React$useState3 = react__WEBPACK_IMPORTED_MODULE_2__["useState"](config),
      _React$useState4 = _babel_runtime_helpers_slicedToArray__WEBPACK_IMPORTED_MODULE_1___default()(_React$useState3, 2),
      innerConfig = _React$useState4[0],
      setInnerConfig = _React$useState4[1];

  var _React$useContext = react__WEBPACK_IMPORTED_MODULE_2__["useContext"](_config_provider__WEBPACK_IMPORTED_MODULE_6__["ConfigContext"]),
      direction = _React$useContext.direction,
      getPrefixCls = _React$useContext.getPrefixCls;

  var prefixCls = getPrefixCls('modal');
  var rootPrefixCls = getPrefixCls();

  function close() {
    setVisible(false);

    for (var _len = arguments.length, args = new Array(_len), _key = 0; _key < _len; _key++) {
      args[_key] = arguments[_key];
    }

    var triggerCancel = args.some(function (param) {
      return param && param.triggerCancel;
    });

    if (innerConfig.onCancel && triggerCancel) {
      innerConfig.onCancel();
    }
  }

  react__WEBPACK_IMPORTED_MODULE_2__["useImperativeHandle"](ref, function () {
    return {
      destroy: close,
      update: function update(newConfig) {
        setInnerConfig(function (originConfig) {
          return _babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_0___default()(_babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_0___default()({}, originConfig), newConfig);
        });
      }
    };
  });
  return /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_2__["createElement"](_locale_provider_LocaleReceiver__WEBPACK_IMPORTED_MODULE_5__["default"], {
    componentName: "Modal",
    defaultLocale: _locale_default__WEBPACK_IMPORTED_MODULE_4__["default"].Modal
  }, function (modalLocale) {
    return /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_2__["createElement"](_ConfirmDialog__WEBPACK_IMPORTED_MODULE_3__["default"], _babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_0___default()({
      prefixCls: prefixCls,
      rootPrefixCls: rootPrefixCls
    }, innerConfig, {
      close: close,
      visible: visible,
      afterClose: afterClose,
      okText: innerConfig.okText || (innerConfig.okCancel ? modalLocale.okText : modalLocale.justOkText),
      direction: direction,
      cancelText: innerConfig.cancelText || modalLocale.cancelText
    }));
  });
};

/* harmony default export */ __webpack_exports__["default"] = (/*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_2__["forwardRef"](HookModal));

/***/ }),

/***/ "./node_modules/_antd@4.9.1@antd/es/modal/useModal/index.js":
/*!******************************************************************!*\
  !*** ./node_modules/_antd@4.9.1@antd/es/modal/useModal/index.js ***!
  \******************************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "default", function() { return useModal; });
/* harmony import */ var _babel_runtime_helpers_slicedToArray__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @babel/runtime/helpers/slicedToArray */ "./node_modules/_@babel_runtime@7.12.5@@babel/runtime/helpers/slicedToArray.js");
/* harmony import */ var _babel_runtime_helpers_slicedToArray__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_babel_runtime_helpers_slicedToArray__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! react */ "./node_modules/_react@16.12.0@react/index.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_1__);
/* harmony import */ var _util_hooks_usePatchElement__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../_util/hooks/usePatchElement */ "./node_modules/_antd@4.9.1@antd/es/_util/hooks/usePatchElement.js");
/* harmony import */ var _HookModal__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./HookModal */ "./node_modules/_antd@4.9.1@antd/es/modal/useModal/HookModal.js");
/* harmony import */ var _confirm__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../confirm */ "./node_modules/_antd@4.9.1@antd/es/modal/confirm.js");





var uuid = 0;
function useModal() {
  var _usePatchElement = Object(_util_hooks_usePatchElement__WEBPACK_IMPORTED_MODULE_2__["default"])(),
      _usePatchElement2 = _babel_runtime_helpers_slicedToArray__WEBPACK_IMPORTED_MODULE_0___default()(_usePatchElement, 2),
      elements = _usePatchElement2[0],
      patchElement = _usePatchElement2[1];

  function getConfirmFunc(withFunc) {
    return function hookConfirm(config) {
      uuid += 1;
      var modalRef = /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_1__["createRef"]();
      var closeFunc;
      var modal = /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_1__["createElement"](_HookModal__WEBPACK_IMPORTED_MODULE_3__["default"], {
        key: "modal-".concat(uuid),
        config: withFunc(config),
        ref: modalRef,
        afterClose: function afterClose() {
          closeFunc();
        }
      });
      closeFunc = patchElement(modal);
      return {
        destroy: function destroy() {
          if (modalRef.current) {
            modalRef.current.destroy();
          }
        },
        update: function update(newConfig) {
          if (modalRef.current) {
            modalRef.current.update(newConfig);
          }
        }
      };
    };
  }

  return [{
    info: getConfirmFunc(_confirm__WEBPACK_IMPORTED_MODULE_4__["withInfo"]),
    success: getConfirmFunc(_confirm__WEBPACK_IMPORTED_MODULE_4__["withSuccess"]),
    error: getConfirmFunc(_confirm__WEBPACK_IMPORTED_MODULE_4__["withError"]),
    warning: getConfirmFunc(_confirm__WEBPACK_IMPORTED_MODULE_4__["withWarn"]),
    confirm: getConfirmFunc(_confirm__WEBPACK_IMPORTED_MODULE_4__["withConfirm"])
  }, /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_1__["createElement"](react__WEBPACK_IMPORTED_MODULE_1__["Fragment"], null, elements)];
}

/***/ }),

/***/ "./node_modules/_antd@4.9.1@antd/es/tag/CheckableTag.js":
/*!**************************************************************!*\
  !*** ./node_modules/_antd@4.9.1@antd/es/tag/CheckableTag.js ***!
  \**************************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @babel/runtime/helpers/extends */ "./node_modules/_@babel_runtime@7.12.5@@babel/runtime/helpers/extends.js");
/* harmony import */ var _babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var _babel_runtime_helpers_defineProperty__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @babel/runtime/helpers/defineProperty */ "./node_modules/_@babel_runtime@7.12.5@@babel/runtime/helpers/defineProperty.js");
/* harmony import */ var _babel_runtime_helpers_defineProperty__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(_babel_runtime_helpers_defineProperty__WEBPACK_IMPORTED_MODULE_1__);
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! react */ "./node_modules/_react@16.12.0@react/index.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_2___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_2__);
/* harmony import */ var classnames__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! classnames */ "./node_modules/_classnames@2.2.6@classnames/index.js");
/* harmony import */ var classnames__WEBPACK_IMPORTED_MODULE_3___default = /*#__PURE__*/__webpack_require__.n(classnames__WEBPACK_IMPORTED_MODULE_3__);
/* harmony import */ var _config_provider__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../config-provider */ "./node_modules/_antd@4.9.1@antd/es/config-provider/index.js");



var __rest = undefined && undefined.__rest || function (s, e) {
  var t = {};

  for (var p in s) {
    if (Object.prototype.hasOwnProperty.call(s, p) && e.indexOf(p) < 0) t[p] = s[p];
  }

  if (s != null && typeof Object.getOwnPropertySymbols === "function") for (var i = 0, p = Object.getOwnPropertySymbols(s); i < p.length; i++) {
    if (e.indexOf(p[i]) < 0 && Object.prototype.propertyIsEnumerable.call(s, p[i])) t[p[i]] = s[p[i]];
  }
  return t;
};





var CheckableTag = function CheckableTag(_a) {
  var _classNames;

  var customizePrefixCls = _a.prefixCls,
      className = _a.className,
      checked = _a.checked,
      onChange = _a.onChange,
      onClick = _a.onClick,
      restProps = __rest(_a, ["prefixCls", "className", "checked", "onChange", "onClick"]);

  var _React$useContext = react__WEBPACK_IMPORTED_MODULE_2__["useContext"](_config_provider__WEBPACK_IMPORTED_MODULE_4__["ConfigContext"]),
      getPrefixCls = _React$useContext.getPrefixCls;

  var handleClick = function handleClick(e) {
    if (onChange) {
      onChange(!checked);
    }

    if (onClick) {
      onClick(e);
    }
  };

  var prefixCls = getPrefixCls('tag', customizePrefixCls);
  var cls = classnames__WEBPACK_IMPORTED_MODULE_3___default()(prefixCls, (_classNames = {}, _babel_runtime_helpers_defineProperty__WEBPACK_IMPORTED_MODULE_1___default()(_classNames, "".concat(prefixCls, "-checkable"), true), _babel_runtime_helpers_defineProperty__WEBPACK_IMPORTED_MODULE_1___default()(_classNames, "".concat(prefixCls, "-checkable-checked"), checked), _classNames), className);
  return /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_2__["createElement"]("span", _babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_0___default()({}, restProps, {
    className: cls,
    onClick: handleClick
  }));
};

/* harmony default export */ __webpack_exports__["default"] = (CheckableTag);

/***/ }),

/***/ "./node_modules/_antd@4.9.1@antd/es/tag/index.js":
/*!*******************************************************!*\
  !*** ./node_modules/_antd@4.9.1@antd/es/tag/index.js ***!
  \*******************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _babel_runtime_helpers_defineProperty__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @babel/runtime/helpers/defineProperty */ "./node_modules/_@babel_runtime@7.12.5@@babel/runtime/helpers/defineProperty.js");
/* harmony import */ var _babel_runtime_helpers_defineProperty__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_babel_runtime_helpers_defineProperty__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var _babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @babel/runtime/helpers/extends */ "./node_modules/_@babel_runtime@7.12.5@@babel/runtime/helpers/extends.js");
/* harmony import */ var _babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(_babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_1__);
/* harmony import */ var _babel_runtime_helpers_slicedToArray__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @babel/runtime/helpers/slicedToArray */ "./node_modules/_@babel_runtime@7.12.5@@babel/runtime/helpers/slicedToArray.js");
/* harmony import */ var _babel_runtime_helpers_slicedToArray__WEBPACK_IMPORTED_MODULE_2___default = /*#__PURE__*/__webpack_require__.n(_babel_runtime_helpers_slicedToArray__WEBPACK_IMPORTED_MODULE_2__);
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! react */ "./node_modules/_react@16.12.0@react/index.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_3___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_3__);
/* harmony import */ var classnames__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! classnames */ "./node_modules/_classnames@2.2.6@classnames/index.js");
/* harmony import */ var classnames__WEBPACK_IMPORTED_MODULE_4___default = /*#__PURE__*/__webpack_require__.n(classnames__WEBPACK_IMPORTED_MODULE_4__);
/* harmony import */ var omit_js__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! omit.js */ "./node_modules/_omit.js@2.0.2@omit.js/es/index.js");
/* harmony import */ var _ant_design_icons_CloseOutlined__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @ant-design/icons/CloseOutlined */ "./node_modules/_@ant-design_icons@4.3.0@@ant-design/icons/CloseOutlined.js");
/* harmony import */ var _ant_design_icons_CloseOutlined__WEBPACK_IMPORTED_MODULE_6___default = /*#__PURE__*/__webpack_require__.n(_ant_design_icons_CloseOutlined__WEBPACK_IMPORTED_MODULE_6__);
/* harmony import */ var _CheckableTag__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ./CheckableTag */ "./node_modules/_antd@4.9.1@antd/es/tag/CheckableTag.js");
/* harmony import */ var _config_provider__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ../config-provider */ "./node_modules/_antd@4.9.1@antd/es/config-provider/index.js");
/* harmony import */ var _util_colors__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! ../_util/colors */ "./node_modules/_antd@4.9.1@antd/es/_util/colors.js");
/* harmony import */ var _util_wave__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! ../_util/wave */ "./node_modules/_antd@4.9.1@antd/es/_util/wave.js");




var __rest = undefined && undefined.__rest || function (s, e) {
  var t = {};

  for (var p in s) {
    if (Object.prototype.hasOwnProperty.call(s, p) && e.indexOf(p) < 0) t[p] = s[p];
  }

  if (s != null && typeof Object.getOwnPropertySymbols === "function") for (var i = 0, p = Object.getOwnPropertySymbols(s); i < p.length; i++) {
    if (e.indexOf(p[i]) < 0 && Object.prototype.propertyIsEnumerable.call(s, p[i])) t[p[i]] = s[p[i]];
  }
  return t;
};









var PresetColorRegex = new RegExp("^(".concat(_util_colors__WEBPACK_IMPORTED_MODULE_9__["PresetColorTypes"].join('|'), ")(-inverse)?$"));
var PresetStatusColorRegex = new RegExp("^(".concat(_util_colors__WEBPACK_IMPORTED_MODULE_9__["PresetStatusColorTypes"].join('|'), ")$"));

var InternalTag = function InternalTag(_a, ref) {
  var _classNames;

  var customizePrefixCls = _a.prefixCls,
      className = _a.className,
      style = _a.style,
      children = _a.children,
      icon = _a.icon,
      color = _a.color,
      onClose = _a.onClose,
      closeIcon = _a.closeIcon,
      _a$closable = _a.closable,
      closable = _a$closable === void 0 ? false : _a$closable,
      props = __rest(_a, ["prefixCls", "className", "style", "children", "icon", "color", "onClose", "closeIcon", "closable"]);

  var _React$useContext = react__WEBPACK_IMPORTED_MODULE_3__["useContext"](_config_provider__WEBPACK_IMPORTED_MODULE_8__["ConfigContext"]),
      getPrefixCls = _React$useContext.getPrefixCls,
      direction = _React$useContext.direction;

  var _React$useState = react__WEBPACK_IMPORTED_MODULE_3__["useState"](true),
      _React$useState2 = _babel_runtime_helpers_slicedToArray__WEBPACK_IMPORTED_MODULE_2___default()(_React$useState, 2),
      visible = _React$useState2[0],
      setVisible = _React$useState2[1];

  react__WEBPACK_IMPORTED_MODULE_3__["useEffect"](function () {
    if ('visible' in props) {
      setVisible(props.visible);
    }
  }, [props.visible]);

  var isPresetColor = function isPresetColor() {
    if (!color) {
      return false;
    }

    return PresetColorRegex.test(color) || PresetStatusColorRegex.test(color);
  };

  var tagStyle = _babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_1___default()({
    backgroundColor: color && !isPresetColor() ? color : undefined
  }, style);

  var presetColor = isPresetColor();
  var prefixCls = getPrefixCls('tag', customizePrefixCls);
  var tagClassName = classnames__WEBPACK_IMPORTED_MODULE_4___default()(prefixCls, (_classNames = {}, _babel_runtime_helpers_defineProperty__WEBPACK_IMPORTED_MODULE_0___default()(_classNames, "".concat(prefixCls, "-").concat(color), presetColor), _babel_runtime_helpers_defineProperty__WEBPACK_IMPORTED_MODULE_0___default()(_classNames, "".concat(prefixCls, "-has-color"), color && !presetColor), _babel_runtime_helpers_defineProperty__WEBPACK_IMPORTED_MODULE_0___default()(_classNames, "".concat(prefixCls, "-hidden"), !visible), _babel_runtime_helpers_defineProperty__WEBPACK_IMPORTED_MODULE_0___default()(_classNames, "".concat(prefixCls, "-rtl"), direction === 'rtl'), _classNames), className);

  var handleCloseClick = function handleCloseClick(e) {
    e.stopPropagation();

    if (onClose) {
      onClose(e);
    }

    if (e.defaultPrevented) {
      return;
    }

    if (!('visible' in props)) {
      setVisible(false);
    }
  };

  var renderCloseIcon = function renderCloseIcon() {
    if (closable) {
      return closeIcon ? /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_3__["createElement"]("span", {
        className: "".concat(prefixCls, "-close-icon"),
        onClick: handleCloseClick
      }, closeIcon) : /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_3__["createElement"](_ant_design_icons_CloseOutlined__WEBPACK_IMPORTED_MODULE_6___default.a, {
        className: "".concat(prefixCls, "-close-icon"),
        onClick: handleCloseClick
      });
    }

    return null;
  };

  var isNeedWave = 'onClick' in props || children && children.type === 'a';
  var tagProps = Object(omit_js__WEBPACK_IMPORTED_MODULE_5__["default"])(props, ['visible']);
  var iconNode = icon || null;
  var kids = iconNode ? /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_3__["createElement"](react__WEBPACK_IMPORTED_MODULE_3__["Fragment"], null, iconNode, /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_3__["createElement"]("span", null, children)) : children;
  var tagNode = /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_3__["createElement"]("span", _babel_runtime_helpers_extends__WEBPACK_IMPORTED_MODULE_1___default()({}, tagProps, {
    ref: ref,
    className: tagClassName,
    style: tagStyle
  }), kids, renderCloseIcon());
  return isNeedWave ? /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_3__["createElement"](_util_wave__WEBPACK_IMPORTED_MODULE_10__["default"], null, tagNode) : tagNode;
};

var Tag = /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_3__["forwardRef"](InternalTag);
Tag.displayName = 'Tag';
Tag.CheckableTag = _CheckableTag__WEBPACK_IMPORTED_MODULE_7__["default"];
/* harmony default export */ __webpack_exports__["default"] = (Tag);

/***/ }),

/***/ "./node_modules/_rc-dialog@8.4.6@rc-dialog/es/Dialog/Content/MemoChildren.js":
/*!***********************************************************************************!*\
  !*** ./node_modules/_rc-dialog@8.4.6@rc-dialog/es/Dialog/Content/MemoChildren.js ***!
  \***********************************************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! react */ "./node_modules/_react@16.12.0@react/index.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_0__);

/* harmony default export */ __webpack_exports__["default"] = (/*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_0__["memo"](function (_ref) {
  var children = _ref.children;
  return children;
}, function (_, _ref2) {
  var shouldUpdate = _ref2.shouldUpdate;
  return !shouldUpdate;
}));

/***/ }),

/***/ "./node_modules/_rc-dialog@8.4.6@rc-dialog/es/Dialog/Content/index.js":
/*!****************************************************************************!*\
  !*** ./node_modules/_rc-dialog@8.4.6@rc-dialog/es/Dialog/Content/index.js ***!
  \****************************************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _babel_runtime_helpers_esm_objectSpread2__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @babel/runtime/helpers/esm/objectSpread2 */ "./node_modules/_@babel_runtime@7.12.5@@babel/runtime/helpers/esm/objectSpread2.js");
/* harmony import */ var _babel_runtime_helpers_esm_extends__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @babel/runtime/helpers/esm/extends */ "./node_modules/_@babel_runtime@7.12.5@@babel/runtime/helpers/esm/extends.js");
/* harmony import */ var _babel_runtime_helpers_esm_slicedToArray__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @babel/runtime/helpers/esm/slicedToArray */ "./node_modules/_@babel_runtime@7.12.5@@babel/runtime/helpers/esm/slicedToArray.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! react */ "./node_modules/_react@16.12.0@react/index.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_3___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_3__);
/* harmony import */ var classnames__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! classnames */ "./node_modules/_classnames@2.2.6@classnames/index.js");
/* harmony import */ var classnames__WEBPACK_IMPORTED_MODULE_4___default = /*#__PURE__*/__webpack_require__.n(classnames__WEBPACK_IMPORTED_MODULE_4__);
/* harmony import */ var rc_motion__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! rc-motion */ "./node_modules/_rc-motion@2.4.1@rc-motion/es/index.js");
/* harmony import */ var _util__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ../../util */ "./node_modules/_rc-dialog@8.4.6@rc-dialog/es/util.js");
/* harmony import */ var _MemoChildren__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ./MemoChildren */ "./node_modules/_rc-dialog@8.4.6@rc-dialog/es/Dialog/Content/MemoChildren.js");









var sentinelStyle = {
  width: 0,
  height: 0,
  overflow: 'hidden',
  outline: 'none'
};
var Content = /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_3__["forwardRef"](function (props, ref) {
  var closable = props.closable,
      prefixCls = props.prefixCls,
      width = props.width,
      height = props.height,
      footer = props.footer,
      title = props.title,
      closeIcon = props.closeIcon,
      style = props.style,
      className = props.className,
      visible = props.visible,
      forceRender = props.forceRender,
      bodyStyle = props.bodyStyle,
      bodyProps = props.bodyProps,
      children = props.children,
      destroyOnClose = props.destroyOnClose,
      modalRender = props.modalRender,
      motionName = props.motionName,
      ariaId = props.ariaId,
      onClose = props.onClose,
      onVisibleChanged = props.onVisibleChanged,
      onMouseDown = props.onMouseDown,
      onMouseUp = props.onMouseUp,
      mousePosition = props.mousePosition;
  var sentinelStartRef = Object(react__WEBPACK_IMPORTED_MODULE_3__["useRef"])();
  var sentinelEndRef = Object(react__WEBPACK_IMPORTED_MODULE_3__["useRef"])();
  var dialogRef = Object(react__WEBPACK_IMPORTED_MODULE_3__["useRef"])(); // ============================== Ref ===============================

  react__WEBPACK_IMPORTED_MODULE_3__["useImperativeHandle"](ref, function () {
    return {
      focus: function focus() {
        var _sentinelStartRef$cur;

        (_sentinelStartRef$cur = sentinelStartRef.current) === null || _sentinelStartRef$cur === void 0 ? void 0 : _sentinelStartRef$cur.focus();
      },
      changeActive: function changeActive(next) {
        var _document = document,
            activeElement = _document.activeElement;

        if (next && activeElement === sentinelEndRef.current) {
          sentinelStartRef.current.focus();
        } else if (!next && activeElement === sentinelStartRef.current) {
          sentinelEndRef.current.focus();
        }
      }
    };
  }); // ============================= Style ==============================

  var _React$useState = react__WEBPACK_IMPORTED_MODULE_3__["useState"](),
      _React$useState2 = Object(_babel_runtime_helpers_esm_slicedToArray__WEBPACK_IMPORTED_MODULE_2__["default"])(_React$useState, 2),
      transformOrigin = _React$useState2[0],
      setTransformOrigin = _React$useState2[1];

  var contentStyle = {};

  if (width !== undefined) {
    contentStyle.width = width;
  }

  if (height !== undefined) {
    contentStyle.height = height;
  }

  if (transformOrigin) {
    contentStyle.transformOrigin = transformOrigin;
  }

  function onPrepare() {
    var elementOffset = Object(_util__WEBPACK_IMPORTED_MODULE_6__["offset"])(dialogRef.current);
    setTransformOrigin(mousePosition ? "".concat(mousePosition.x - elementOffset.left, "px ").concat(mousePosition.y - elementOffset.top, "px") : '');
  } // ============================= Render =============================


  var footerNode;

  if (footer) {
    footerNode = /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_3__["createElement"]("div", {
      className: "".concat(prefixCls, "-footer")
    }, footer);
  }

  var headerNode;

  if (title) {
    headerNode = /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_3__["createElement"]("div", {
      className: "".concat(prefixCls, "-header")
    }, /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_3__["createElement"]("div", {
      className: "".concat(prefixCls, "-title"),
      id: ariaId
    }, title));
  }

  var closer;

  if (closable) {
    closer = /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_3__["createElement"]("button", {
      type: "button",
      onClick: onClose,
      "aria-label": "Close",
      className: "".concat(prefixCls, "-close")
    }, closeIcon || /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_3__["createElement"]("span", {
      className: "".concat(prefixCls, "-close-x")
    }));
  }

  var content = /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_3__["createElement"]("div", {
    className: "".concat(prefixCls, "-content")
  }, closer, headerNode, /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_3__["createElement"]("div", Object(_babel_runtime_helpers_esm_extends__WEBPACK_IMPORTED_MODULE_1__["default"])({
    className: "".concat(prefixCls, "-body"),
    style: bodyStyle
  }, bodyProps), children), footerNode);
  return /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_3__["createElement"](rc_motion__WEBPACK_IMPORTED_MODULE_5__["default"], {
    visible: visible,
    onVisibleChanged: onVisibleChanged,
    onAppearPrepare: onPrepare,
    onEnterPrepare: onPrepare,
    forceRender: forceRender,
    motionName: motionName,
    removeOnLeave: destroyOnClose,
    ref: dialogRef
  }, function (_ref, motionRef) {
    var motionClassName = _ref.className,
        motionStyle = _ref.style;
    return /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_3__["createElement"]("div", {
      key: "dialog-element",
      role: "document",
      ref: motionRef,
      style: Object(_babel_runtime_helpers_esm_objectSpread2__WEBPACK_IMPORTED_MODULE_0__["default"])(Object(_babel_runtime_helpers_esm_objectSpread2__WEBPACK_IMPORTED_MODULE_0__["default"])(Object(_babel_runtime_helpers_esm_objectSpread2__WEBPACK_IMPORTED_MODULE_0__["default"])({}, motionStyle), style), contentStyle),
      className: classnames__WEBPACK_IMPORTED_MODULE_4___default()(prefixCls, className, motionClassName),
      onMouseDown: onMouseDown,
      onMouseUp: onMouseUp
    }, /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_3__["createElement"]("div", {
      tabIndex: 0,
      ref: sentinelStartRef,
      style: sentinelStyle,
      "aria-hidden": "true"
    }), /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_3__["createElement"](_MemoChildren__WEBPACK_IMPORTED_MODULE_7__["default"], {
      shouldUpdate: visible || forceRender
    }, modalRender ? modalRender(content) : content), /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_3__["createElement"]("div", {
      tabIndex: 0,
      ref: sentinelEndRef,
      style: sentinelStyle,
      "aria-hidden": "true"
    }));
  });
});
Content.displayName = 'Content';
/* harmony default export */ __webpack_exports__["default"] = (Content);

/***/ }),

/***/ "./node_modules/_rc-dialog@8.4.6@rc-dialog/es/Dialog/Mask.js":
/*!*******************************************************************!*\
  !*** ./node_modules/_rc-dialog@8.4.6@rc-dialog/es/Dialog/Mask.js ***!
  \*******************************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "default", function() { return Mask; });
/* harmony import */ var _babel_runtime_helpers_esm_extends__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @babel/runtime/helpers/esm/extends */ "./node_modules/_@babel_runtime@7.12.5@@babel/runtime/helpers/esm/extends.js");
/* harmony import */ var _babel_runtime_helpers_esm_objectSpread2__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @babel/runtime/helpers/esm/objectSpread2 */ "./node_modules/_@babel_runtime@7.12.5@@babel/runtime/helpers/esm/objectSpread2.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! react */ "./node_modules/_react@16.12.0@react/index.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_2___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_2__);
/* harmony import */ var classnames__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! classnames */ "./node_modules/_classnames@2.2.6@classnames/index.js");
/* harmony import */ var classnames__WEBPACK_IMPORTED_MODULE_3___default = /*#__PURE__*/__webpack_require__.n(classnames__WEBPACK_IMPORTED_MODULE_3__);
/* harmony import */ var rc_motion__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! rc-motion */ "./node_modules/_rc-motion@2.4.1@rc-motion/es/index.js");





function Mask(props) {
  var prefixCls = props.prefixCls,
      style = props.style,
      visible = props.visible,
      maskProps = props.maskProps,
      motionName = props.motionName;
  return /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_2__["createElement"](rc_motion__WEBPACK_IMPORTED_MODULE_4__["default"], {
    key: "mask",
    visible: visible,
    motionName: motionName,
    leavedClassName: "".concat(prefixCls, "-mask-hidden")
  }, function (_ref) {
    var motionClassName = _ref.className,
        motionStyle = _ref.style;
    return /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_2__["createElement"]("div", Object(_babel_runtime_helpers_esm_extends__WEBPACK_IMPORTED_MODULE_0__["default"])({
      style: Object(_babel_runtime_helpers_esm_objectSpread2__WEBPACK_IMPORTED_MODULE_1__["default"])(Object(_babel_runtime_helpers_esm_objectSpread2__WEBPACK_IMPORTED_MODULE_1__["default"])({}, motionStyle), style),
      className: classnames__WEBPACK_IMPORTED_MODULE_3___default()("".concat(prefixCls, "-mask"), motionClassName)
    }, maskProps));
  });
}

/***/ }),

/***/ "./node_modules/_rc-dialog@8.4.6@rc-dialog/es/Dialog/index.js":
/*!********************************************************************!*\
  !*** ./node_modules/_rc-dialog@8.4.6@rc-dialog/es/Dialog/index.js ***!
  \********************************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "default", function() { return Dialog; });
/* harmony import */ var _babel_runtime_helpers_esm_extends__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @babel/runtime/helpers/esm/extends */ "./node_modules/_@babel_runtime@7.12.5@@babel/runtime/helpers/esm/extends.js");
/* harmony import */ var _babel_runtime_helpers_esm_objectSpread2__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @babel/runtime/helpers/esm/objectSpread2 */ "./node_modules/_@babel_runtime@7.12.5@@babel/runtime/helpers/esm/objectSpread2.js");
/* harmony import */ var _babel_runtime_helpers_esm_slicedToArray__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @babel/runtime/helpers/esm/slicedToArray */ "./node_modules/_@babel_runtime@7.12.5@@babel/runtime/helpers/esm/slicedToArray.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! react */ "./node_modules/_react@16.12.0@react/index.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_3___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_3__);
/* harmony import */ var classnames__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! classnames */ "./node_modules/_classnames@2.2.6@classnames/index.js");
/* harmony import */ var classnames__WEBPACK_IMPORTED_MODULE_4___default = /*#__PURE__*/__webpack_require__.n(classnames__WEBPACK_IMPORTED_MODULE_4__);
/* harmony import */ var rc_util_es_KeyCode__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! rc-util/es/KeyCode */ "./node_modules/_rc-util@5.5.1@rc-util/es/KeyCode.js");
/* harmony import */ var rc_util_es_Dom_contains__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! rc-util/es/Dom/contains */ "./node_modules/_rc-util@5.5.1@rc-util/es/Dom/contains.js");
/* harmony import */ var _Mask__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ./Mask */ "./node_modules/_rc-dialog@8.4.6@rc-dialog/es/Dialog/Mask.js");
/* harmony import */ var _util__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ../util */ "./node_modules/_rc-dialog@8.4.6@rc-dialog/es/util.js");
/* harmony import */ var _Content__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! ./Content */ "./node_modules/_rc-dialog@8.4.6@rc-dialog/es/Dialog/Content/index.js");











function Dialog(props) {
  var _props$prefixCls = props.prefixCls,
      prefixCls = _props$prefixCls === void 0 ? 'rc-dialog' : _props$prefixCls,
      zIndex = props.zIndex,
      _props$visible = props.visible,
      visible = _props$visible === void 0 ? false : _props$visible,
      _props$keyboard = props.keyboard,
      keyboard = _props$keyboard === void 0 ? true : _props$keyboard,
      _props$focusTriggerAf = props.focusTriggerAfterClose,
      focusTriggerAfterClose = _props$focusTriggerAf === void 0 ? true : _props$focusTriggerAf,
      _props$switchScrollin = props.switchScrollingEffect,
      switchScrollingEffect = _props$switchScrollin === void 0 ? function () {} : _props$switchScrollin,
      title = props.title,
      wrapStyle = props.wrapStyle,
      wrapClassName = props.wrapClassName,
      wrapProps = props.wrapProps,
      onClose = props.onClose,
      afterClose = props.afterClose,
      transitionName = props.transitionName,
      animation = props.animation,
      _props$closable = props.closable,
      closable = _props$closable === void 0 ? true : _props$closable,
      _props$mask = props.mask,
      mask = _props$mask === void 0 ? true : _props$mask,
      maskTransitionName = props.maskTransitionName,
      maskAnimation = props.maskAnimation,
      _props$maskClosable = props.maskClosable,
      maskClosable = _props$maskClosable === void 0 ? true : _props$maskClosable,
      maskStyle = props.maskStyle,
      maskProps = props.maskProps;
  var lastOutSideActiveElementRef = Object(react__WEBPACK_IMPORTED_MODULE_3__["useRef"])();
  var wrapperRef = Object(react__WEBPACK_IMPORTED_MODULE_3__["useRef"])();
  var contentRef = Object(react__WEBPACK_IMPORTED_MODULE_3__["useRef"])();

  var _React$useState = react__WEBPACK_IMPORTED_MODULE_3__["useState"](visible),
      _React$useState2 = Object(_babel_runtime_helpers_esm_slicedToArray__WEBPACK_IMPORTED_MODULE_2__["default"])(_React$useState, 2),
      animatedVisible = _React$useState2[0],
      setAnimatedVisible = _React$useState2[1]; // ========================== Init ==========================


  var ariaIdRef = Object(react__WEBPACK_IMPORTED_MODULE_3__["useRef"])();

  if (!ariaIdRef.current) {
    ariaIdRef.current = "rcDialogTitle".concat(Object(_util__WEBPACK_IMPORTED_MODULE_8__["getUUID"])());
  } // ========================= Events =========================


  function onDialogVisibleChanged(newVisible) {
    if (newVisible) {
      // Try to focus
      if (!Object(rc_util_es_Dom_contains__WEBPACK_IMPORTED_MODULE_6__["default"])(wrapperRef.current, document.activeElement)) {
        var _contentRef$current;

        lastOutSideActiveElementRef.current = document.activeElement;
        (_contentRef$current = contentRef.current) === null || _contentRef$current === void 0 ? void 0 : _contentRef$current.focus();
      }
    } else {
      // Clean up scroll bar & focus back
      setAnimatedVisible(false);
      switchScrollingEffect();

      if (mask && lastOutSideActiveElementRef.current && focusTriggerAfterClose) {
        try {
          lastOutSideActiveElementRef.current.focus({
            preventScroll: true
          });
        } catch (e) {// Do nothing
        }

        lastOutSideActiveElementRef.current = null;
      }

      afterClose === null || afterClose === void 0 ? void 0 : afterClose();
    }
  }

  function onInternalClose(e) {
    onClose === null || onClose === void 0 ? void 0 : onClose(e);
  } // >>> Content


  var contentClickRef = Object(react__WEBPACK_IMPORTED_MODULE_3__["useRef"])(false);
  var contentTimeoutRef = Object(react__WEBPACK_IMPORTED_MODULE_3__["useRef"])(); // We need record content click incase content popup out of dialog

  var onContentMouseDown = function onContentMouseDown() {
    clearTimeout(contentTimeoutRef.current);
    contentClickRef.current = true;
  };

  var onContentMouseUp = function onContentMouseUp() {
    contentTimeoutRef.current = setTimeout(function () {
      contentClickRef.current = false;
    });
  }; // >>> Wrapper
  // Close only when element not on dialog


  var onWrapperClick = null;

  if (maskClosable) {
    onWrapperClick = function onWrapperClick(e) {
      if (contentClickRef.current) {
        contentClickRef.current = false;
      } else if (wrapperRef.current === e.target) {
        onInternalClose(e);
      }
    };
  }

  function onWrapperKeyDown(e) {
    if (keyboard && e.keyCode === rc_util_es_KeyCode__WEBPACK_IMPORTED_MODULE_5__["default"].ESC) {
      e.stopPropagation();
      onInternalClose(e);
      return;
    } // keep focus inside dialog


    if (visible) {
      if (e.keyCode === rc_util_es_KeyCode__WEBPACK_IMPORTED_MODULE_5__["default"].TAB) {
        contentRef.current.changeActive(!e.shiftKey);
      }
    }
  } // ========================= Effect =========================


  Object(react__WEBPACK_IMPORTED_MODULE_3__["useEffect"])(function () {
    if (visible) {
      setAnimatedVisible(true);
      switchScrollingEffect();
    }
  }, [visible]); // Remove direct should also check the scroll bar update

  Object(react__WEBPACK_IMPORTED_MODULE_3__["useEffect"])(function () {
    return function () {
      switchScrollingEffect();
      clearTimeout(contentTimeoutRef.current);
    };
  }, []); // ========================= Render =========================

  return /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_3__["createElement"]("div", {
    className: "".concat(prefixCls, "-root")
  }, /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_3__["createElement"](_Mask__WEBPACK_IMPORTED_MODULE_7__["default"], {
    prefixCls: prefixCls,
    visible: mask && visible,
    motionName: Object(_util__WEBPACK_IMPORTED_MODULE_8__["getMotionName"])(prefixCls, maskTransitionName, maskAnimation),
    style: Object(_babel_runtime_helpers_esm_objectSpread2__WEBPACK_IMPORTED_MODULE_1__["default"])({
      zIndex: zIndex
    }, maskStyle),
    maskProps: maskProps
  }), /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_3__["createElement"]("div", Object(_babel_runtime_helpers_esm_extends__WEBPACK_IMPORTED_MODULE_0__["default"])({
    tabIndex: -1,
    onKeyDown: onWrapperKeyDown,
    className: classnames__WEBPACK_IMPORTED_MODULE_4___default()("".concat(prefixCls, "-wrap"), wrapClassName),
    ref: wrapperRef,
    onClick: onWrapperClick,
    role: "dialog",
    "aria-labelledby": title ? ariaIdRef.current : null,
    style: Object(_babel_runtime_helpers_esm_objectSpread2__WEBPACK_IMPORTED_MODULE_1__["default"])(Object(_babel_runtime_helpers_esm_objectSpread2__WEBPACK_IMPORTED_MODULE_1__["default"])({
      zIndex: zIndex
    }, wrapStyle), {}, {
      display: !animatedVisible ? 'none' : null
    })
  }, wrapProps), /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_3__["createElement"](_Content__WEBPACK_IMPORTED_MODULE_9__["default"], Object(_babel_runtime_helpers_esm_extends__WEBPACK_IMPORTED_MODULE_0__["default"])({}, props, {
    onMouseDown: onContentMouseDown,
    onMouseUp: onContentMouseUp,
    ref: contentRef,
    closable: closable,
    ariaId: ariaIdRef.current,
    prefixCls: prefixCls,
    visible: visible,
    onClose: onInternalClose,
    onVisibleChanged: onDialogVisibleChanged,
    motionName: Object(_util__WEBPACK_IMPORTED_MODULE_8__["getMotionName"])(prefixCls, transitionName, animation)
  }))));
}

/***/ }),

/***/ "./node_modules/_rc-dialog@8.4.6@rc-dialog/es/DialogWrap.js":
/*!******************************************************************!*\
  !*** ./node_modules/_rc-dialog@8.4.6@rc-dialog/es/DialogWrap.js ***!
  \******************************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _babel_runtime_helpers_esm_extends__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @babel/runtime/helpers/esm/extends */ "./node_modules/_@babel_runtime@7.12.5@@babel/runtime/helpers/esm/extends.js");
/* harmony import */ var _babel_runtime_helpers_esm_slicedToArray__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @babel/runtime/helpers/esm/slicedToArray */ "./node_modules/_@babel_runtime@7.12.5@@babel/runtime/helpers/esm/slicedToArray.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! react */ "./node_modules/_react@16.12.0@react/index.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_2___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_2__);
/* harmony import */ var rc_util_es_PortalWrapper__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! rc-util/es/PortalWrapper */ "./node_modules/_rc-util@5.5.1@rc-util/es/PortalWrapper.js");
/* harmony import */ var _Dialog__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./Dialog */ "./node_modules/_rc-dialog@8.4.6@rc-dialog/es/Dialog/index.js");




 // fix issue #10656

/*
 * getContainer remarks
 * Custom container should not be return, because in the Portal component, it will remove the
 * return container element here, if the custom container is the only child of it's component,
 * like issue #10656, It will has a conflict with removeChild method in react-dom.
 * So here should add a child (div element) to custom container.
 * */

var DialogWrap = function DialogWrap(props) {
  var visible = props.visible,
      getContainer = props.getContainer,
      forceRender = props.forceRender,
      _props$destroyOnClose = props.destroyOnClose,
      destroyOnClose = _props$destroyOnClose === void 0 ? false : _props$destroyOnClose,
      _afterClose = props.afterClose;

  var _React$useState = react__WEBPACK_IMPORTED_MODULE_2__["useState"](visible),
      _React$useState2 = Object(_babel_runtime_helpers_esm_slicedToArray__WEBPACK_IMPORTED_MODULE_1__["default"])(_React$useState, 2),
      animatedVisible = _React$useState2[0],
      setAnimatedVisible = _React$useState2[1];

  react__WEBPACK_IMPORTED_MODULE_2__["useEffect"](function () {
    if (visible) {
      setAnimatedVisible(true);
    }
  }, [visible]); // 渲染在当前 dom 里；

  if (getContainer === false) {
    return /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_2__["createElement"](_Dialog__WEBPACK_IMPORTED_MODULE_4__["default"], Object(_babel_runtime_helpers_esm_extends__WEBPACK_IMPORTED_MODULE_0__["default"])({}, props, {
      getOpenCount: function getOpenCount() {
        return 2;
      } // 不对 body 做任何操作。。

    }));
  } // Destroy on close will remove wrapped div


  if (!forceRender && destroyOnClose && !animatedVisible) {
    return null;
  }

  return /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_2__["createElement"](rc_util_es_PortalWrapper__WEBPACK_IMPORTED_MODULE_3__["default"], {
    visible: visible,
    forceRender: forceRender,
    getContainer: getContainer
  }, function (childProps) {
    return /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_2__["createElement"](_Dialog__WEBPACK_IMPORTED_MODULE_4__["default"], Object(_babel_runtime_helpers_esm_extends__WEBPACK_IMPORTED_MODULE_0__["default"])({}, props, {
      destroyOnClose: destroyOnClose,
      afterClose: function afterClose() {
        _afterClose === null || _afterClose === void 0 ? void 0 : _afterClose();
        setAnimatedVisible(false);
      }
    }, childProps));
  });
};

DialogWrap.displayName = 'Dialog';
/* harmony default export */ __webpack_exports__["default"] = (DialogWrap);

/***/ }),

/***/ "./node_modules/_rc-dialog@8.4.6@rc-dialog/es/index.js":
/*!*************************************************************!*\
  !*** ./node_modules/_rc-dialog@8.4.6@rc-dialog/es/index.js ***!
  \*************************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _DialogWrap__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./DialogWrap */ "./node_modules/_rc-dialog@8.4.6@rc-dialog/es/DialogWrap.js");

/* harmony default export */ __webpack_exports__["default"] = (_DialogWrap__WEBPACK_IMPORTED_MODULE_0__["default"]);

/***/ }),

/***/ "./node_modules/_rc-dialog@8.4.6@rc-dialog/es/util.js":
/*!************************************************************!*\
  !*** ./node_modules/_rc-dialog@8.4.6@rc-dialog/es/util.js ***!
  \************************************************************/
/*! exports provided: getMotionName, getUUID, offset */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "getMotionName", function() { return getMotionName; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "getUUID", function() { return getUUID; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "offset", function() { return offset; });
// =============================== Motion ===============================
function getMotionName(prefixCls, transitionName, animationName) {
  var motionName = transitionName;

  if (!motionName && animationName) {
    motionName = "".concat(prefixCls, "-").concat(animationName);
  }

  return motionName;
} // ================================ UUID ================================

var uuid = -1;
function getUUID() {
  uuid += 1;
  return uuid;
} // =============================== Offset ===============================

function getScroll(w, top) {
  var ret = w["page".concat(top ? 'Y' : 'X', "Offset")];
  var method = "scroll".concat(top ? 'Top' : 'Left');

  if (typeof ret !== 'number') {
    var d = w.document;
    ret = d.documentElement[method];

    if (typeof ret !== 'number') {
      ret = d.body[method];
    }
  }

  return ret;
}

function offset(el) {
  var rect = el.getBoundingClientRect();
  var pos = {
    left: rect.left,
    top: rect.top
  };
  var doc = el.ownerDocument;
  var w = doc.defaultView || doc.parentWindow;
  pos.left += getScroll(w);
  pos.top += getScroll(w, true);
  return pos;
}

/***/ }),

/***/ "./node_modules/_rc-input-number@6.1.2@rc-input-number/es/InputNumber.js":
/*!*******************************************************************************!*\
  !*** ./node_modules/_rc-input-number@6.1.2@rc-input-number/es/InputNumber.js ***!
  \*******************************************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _babel_runtime_helpers_esm_defineProperty__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @babel/runtime/helpers/esm/defineProperty */ "./node_modules/_@babel_runtime@7.12.5@@babel/runtime/helpers/esm/defineProperty.js");
/* harmony import */ var _babel_runtime_helpers_esm_objectWithoutProperties__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @babel/runtime/helpers/esm/objectWithoutProperties */ "./node_modules/_@babel_runtime@7.12.5@@babel/runtime/helpers/esm/objectWithoutProperties.js");
/* harmony import */ var _babel_runtime_helpers_esm_objectSpread2__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @babel/runtime/helpers/esm/objectSpread2 */ "./node_modules/_@babel_runtime@7.12.5@@babel/runtime/helpers/esm/objectSpread2.js");
/* harmony import */ var _babel_runtime_helpers_esm_classCallCheck__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @babel/runtime/helpers/esm/classCallCheck */ "./node_modules/_@babel_runtime@7.12.5@@babel/runtime/helpers/esm/classCallCheck.js");
/* harmony import */ var _babel_runtime_helpers_esm_createClass__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @babel/runtime/helpers/esm/createClass */ "./node_modules/_@babel_runtime@7.12.5@@babel/runtime/helpers/esm/createClass.js");
/* harmony import */ var _babel_runtime_helpers_esm_inherits__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @babel/runtime/helpers/esm/inherits */ "./node_modules/_@babel_runtime@7.12.5@@babel/runtime/helpers/esm/inherits.js");
/* harmony import */ var _babel_runtime_helpers_esm_createSuper__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @babel/runtime/helpers/esm/createSuper */ "./node_modules/_@babel_runtime@7.12.5@@babel/runtime/helpers/esm/createSuper.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! react */ "./node_modules/_react@16.12.0@react/index.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_7___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_7__);
/* harmony import */ var classnames__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! classnames */ "./node_modules/_classnames@2.2.6@classnames/index.js");
/* harmony import */ var classnames__WEBPACK_IMPORTED_MODULE_8___default = /*#__PURE__*/__webpack_require__.n(classnames__WEBPACK_IMPORTED_MODULE_8__);
/* harmony import */ var rc_util_es_KeyCode__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! rc-util/es/KeyCode */ "./node_modules/_rc-util@5.5.1@rc-util/es/KeyCode.js");








/* eslint-disable react/prop-types */




function noop() {}

function preventDefault(e) {
  e.preventDefault();
}

var defaultParser = function defaultParser(input) {
  return input.replace(/[^\w.-]+/g, '');
};
/**
 * When click and hold on a button - the speed of auto changin the value.
 */


var SPEED = 200;
/**
 * When click and hold on a button - the delay before auto changin the value.
 */

var DELAY = 600;
/**
 * Max Safe Integer -- on IE this is not available, so manually set the number in that case.
 * The reason this is used, instead of Infinity is because numbers above the MSI are unstable
 */

var MAX_SAFE_INTEGER = Number.MAX_SAFE_INTEGER || Math.pow(2, 53) - 1;

var isValidProps = function isValidProps(value) {
  return value !== undefined && value !== null;
};

var isEqual = function isEqual(oldValue, newValue) {
  return newValue === oldValue || typeof newValue === 'number' && typeof oldValue === 'number' && isNaN(newValue) && isNaN(oldValue);
};

var InputNumber = /*#__PURE__*/function (_React$Component) {
  Object(_babel_runtime_helpers_esm_inherits__WEBPACK_IMPORTED_MODULE_5__["default"])(InputNumber, _React$Component);

  var _super = Object(_babel_runtime_helpers_esm_createSuper__WEBPACK_IMPORTED_MODULE_6__["default"])(InputNumber);

  function InputNumber(props) {
    var _this;

    Object(_babel_runtime_helpers_esm_classCallCheck__WEBPACK_IMPORTED_MODULE_3__["default"])(this, InputNumber);

    _this = _super.call(this, props);

    _this.onKeyDown = function (e) {
      var _this$props = _this.props,
          onKeyDown = _this$props.onKeyDown,
          onPressEnter = _this$props.onPressEnter;

      if (e.keyCode === rc_util_es_KeyCode__WEBPACK_IMPORTED_MODULE_9__["default"].UP) {
        var ratio = _this.getRatio(e);

        _this.up(e, ratio, null);

        _this.stop();
      } else if (e.keyCode === rc_util_es_KeyCode__WEBPACK_IMPORTED_MODULE_9__["default"].DOWN) {
        var _ratio = _this.getRatio(e);

        _this.down(e, _ratio, null);

        _this.stop();
      } else if (e.keyCode === rc_util_es_KeyCode__WEBPACK_IMPORTED_MODULE_9__["default"].ENTER && onPressEnter) {
        onPressEnter(e);
      } // Trigger user key down


      _this.recordCursorPosition();

      _this.lastKeyCode = e.keyCode;

      if (onKeyDown) {
        for (var _len = arguments.length, args = new Array(_len > 1 ? _len - 1 : 0), _key = 1; _key < _len; _key++) {
          args[_key - 1] = arguments[_key];
        }

        onKeyDown.apply(void 0, [e].concat(args));
      }
    };

    _this.onKeyUp = function (e) {
      var onKeyUp = _this.props.onKeyUp;

      _this.stop();

      _this.recordCursorPosition(); // Trigger user key up


      if (onKeyUp) {
        for (var _len2 = arguments.length, args = new Array(_len2 > 1 ? _len2 - 1 : 0), _key2 = 1; _key2 < _len2; _key2++) {
          args[_key2 - 1] = arguments[_key2];
        }

        onKeyUp.apply(void 0, [e].concat(args));
      }
    };

    _this.onChange = function (e) {
      var onChange = _this.props.onChange;

      if (_this.state.focused) {
        _this.inputting = true;
      }

      _this.rawInput = _this.props.parser(_this.getValueFromEvent(e));

      _this.setState({
        inputValue: _this.rawInput
      });

      onChange(_this.toNumber(_this.rawInput)); // valid number or invalid string
    };

    _this.onMouseUp = function () {
      var onMouseUp = _this.props.onMouseUp;

      _this.recordCursorPosition();

      if (onMouseUp) {
        onMouseUp.apply(void 0, arguments);
      }
    };

    _this.onFocus = function () {
      var _this$props2;

      _this.setState({
        focused: true
      });

      (_this$props2 = _this.props).onFocus.apply(_this$props2, arguments);
    };

    _this.onBlur = function () {
      var onBlur = _this.props.onBlur;
      _this.inputting = false;

      _this.setState({
        focused: false
      });

      var value = _this.getCurrentValidValue(_this.state.inputValue);

      var newValue = _this.setValue(value, noop);

      if (onBlur) {
        var originValue = _this.input.value;

        var displayValue = _this.getInputDisplayValue({
          focus: false,
          value: newValue
        });

        _this.input.value = displayValue;
        onBlur.apply(void 0, arguments);
        _this.input.value = originValue;
      }
    };

    _this.getRatio = function (e) {
      var ratio = 1;

      if (e.metaKey || e.ctrlKey) {
        ratio = 0.1;
      } else if (e.shiftKey) {
        ratio = 10;
      }

      return ratio;
    };

    _this.getFullNum = function (num) {
      if (isNaN(num)) {
        return num;
      }

      if (!/e/i.test(String(num))) {
        return num;
      }

      return Number(num).toFixed(18).replace(/\.?0+$/, '');
    };

    _this.getPrecision = function (value) {
      if (isValidProps(_this.props.precision)) {
        return _this.props.precision;
      }

      var valueString = String(value);

      if (valueString.indexOf('e-') >= 0) {
        return parseInt(valueString.slice(valueString.indexOf('e-') + 2), 10);
      }

      var precision = 0;

      if (valueString.indexOf('.') >= 0) {
        precision = valueString.length - valueString.indexOf('.') - 1;
      }

      return precision;
    };

    _this.getInputDisplayValue = function (state) {
      var _ref = state || _this.state,
          focused = _ref.focused,
          inputValue = _ref.inputValue,
          value = _ref.value;

      var inputDisplayValue;

      if (focused) {
        inputDisplayValue = inputValue;
      } else {
        inputDisplayValue = _this.toPrecisionAsStep(value);
      }

      if (inputDisplayValue === undefined || inputDisplayValue === null) {
        inputDisplayValue = '';
      }

      var inputDisplayValueFormat = _this.formatWrapper(inputDisplayValue);

      if (isValidProps(_this.props.decimalSeparator)) {
        inputDisplayValueFormat = inputDisplayValueFormat.toString().replace('.', _this.props.decimalSeparator);
      }

      return inputDisplayValueFormat;
    };

    _this.recordCursorPosition = function () {
      // Record position
      try {
        _this.cursorStart = _this.input.selectionStart;
        _this.cursorEnd = _this.input.selectionEnd;
        _this.currentValue = _this.input.value;
        _this.cursorBefore = _this.input.value.substring(0, _this.cursorStart);
        _this.cursorAfter = _this.input.value.substring(_this.cursorEnd);
      } catch (e) {// Fix error in Chrome:
        // Failed to read the 'selectionStart' property from 'HTMLInputElement'
        // http://stackoverflow.com/q/21177489/3040605
      }
    };

    _this.restoreByAfter = function (str) {
      if (str === undefined) return false;
      var fullStr = _this.input.value;
      var index = fullStr.lastIndexOf(str);
      if (index === -1) return false;
      var prevCursorPos = _this.cursorBefore.length;

      if (_this.lastKeyCode === rc_util_es_KeyCode__WEBPACK_IMPORTED_MODULE_9__["default"].DELETE && _this.cursorBefore.charAt(prevCursorPos - 1) === str[0]) {
        _this.fixCaret(prevCursorPos, prevCursorPos);

        return true;
      }

      if (index + str.length === fullStr.length) {
        _this.fixCaret(index, index);

        return true;
      }

      return false;
    };

    _this.partRestoreByAfter = function (str) {
      if (str === undefined) return false; // For loop from full str to the str with last char to map. e.g. 123
      // -> 123
      // -> 23
      // -> 3

      return Array.prototype.some.call(str, function (_, start) {
        var partStr = str.substring(start);
        return _this.restoreByAfter(partStr);
      });
    }; // '1.' '1x' 'xx' '' => are not complete numbers


    _this.isNotCompleteNumber = function (num) {
      return isNaN(num) || num === '' || num === null || num && num.toString().indexOf('.') === num.toString().length - 1;
    };

    _this.stop = function () {
      if (_this.autoStepTimer) {
        clearTimeout(_this.autoStepTimer);
      }
    };

    _this.down = function (e, ratio, recursive) {
      _this.pressingUpOrDown = true;

      _this.step('down', e, ratio, recursive);
    };

    _this.up = function (e, ratio, recursive) {
      _this.pressingUpOrDown = true;

      _this.step('up', e, ratio, recursive);
    };

    _this.saveInput = function (node) {
      _this.input = node;
    };

    var value = props.value;

    if (value === undefined) {
      value = props.defaultValue;
    }

    _this.state = {
      focused: props.autoFocus
    };

    var validValue = _this.getValidValue(_this.toNumber(value));

    _this.state = Object(_babel_runtime_helpers_esm_objectSpread2__WEBPACK_IMPORTED_MODULE_2__["default"])(Object(_babel_runtime_helpers_esm_objectSpread2__WEBPACK_IMPORTED_MODULE_2__["default"])({}, _this.state), {}, {
      inputValue: _this.toPrecisionAsStep(validValue),
      value: validValue
    });
    return _this;
  }

  Object(_babel_runtime_helpers_esm_createClass__WEBPACK_IMPORTED_MODULE_4__["default"])(InputNumber, [{
    key: "componentDidMount",
    value: function componentDidMount() {
      this.componentDidUpdate(null);
    }
  }, {
    key: "componentDidUpdate",
    value: function componentDidUpdate(prevProps) {
      var _this$props3 = this.props,
          value = _this$props3.value,
          onChange = _this$props3.onChange,
          max = _this$props3.max,
          min = _this$props3.min;
      var focused = this.state.focused; // Don't trigger in componentDidMount

      if (prevProps) {
        if (!isEqual(prevProps.value, value) || !isEqual(prevProps.max, max) || !isEqual(prevProps.min, min)) {
          var validValue = focused ? value : this.getValidValue(value);
          var nextInputValue;

          if (this.pressingUpOrDown) {
            nextInputValue = validValue;
          } else if (this.inputting) {
            nextInputValue = this.rawInput;
          } else {
            nextInputValue = this.toPrecisionAsStep(validValue);
          }

          this.setState({
            // eslint-disable-line
            value: validValue,
            inputValue: nextInputValue
          });
        } // Trigger onChange when max or min change
        // https://github.com/ant-design/ant-design/issues/11574


        var nextValue = 'value' in this.props ? value : this.state.value; // ref: null < 20 === true
        // https://github.com/ant-design/ant-design/issues/14277

        if ('max' in this.props && prevProps.max !== max && typeof nextValue === 'number' && nextValue > max && onChange) {
          onChange(max);
        }

        if ('min' in this.props && prevProps.min !== min && typeof nextValue === 'number' && nextValue < min && onChange) {
          onChange(min);
        }
      } // Restore cursor


      try {
        // Firefox set the input cursor after it get focused.
        // This caused that if an input didn't init with the selection,
        // set will cause cursor not correct when first focus.
        // Safari will focus input if set selection. We need skip this.
        if (this.cursorStart !== undefined && this.state.focused) {
          // In most cases, the string after cursor is stable.
          // We can move the cursor before it
          if ( // If not match full str, try to match part of str
          !this.partRestoreByAfter(this.cursorAfter) && this.state.value !== this.props.value) {
            // If not match any of then, let's just keep the position
            // TODO: Logic should not reach here, need check if happens
            var pos = this.getInputDisplayValue(this.state).length; // If not have last string, just position to the end

            if (!this.cursorAfter) {
              pos = this.input.value.length;
            } else if (this.lastKeyCode === rc_util_es_KeyCode__WEBPACK_IMPORTED_MODULE_9__["default"].BACKSPACE) {
              pos = this.cursorStart - 1;
            } else if (this.lastKeyCode === rc_util_es_KeyCode__WEBPACK_IMPORTED_MODULE_9__["default"].DELETE) {
              pos = this.cursorStart;
            }

            this.fixCaret(pos, pos);
          } else if (this.currentValue === this.input.value) {
            // Handle some special key code
            switch (this.lastKeyCode) {
              case rc_util_es_KeyCode__WEBPACK_IMPORTED_MODULE_9__["default"].BACKSPACE:
                this.fixCaret(this.cursorStart - 1, this.cursorStart - 1);
                break;

              case rc_util_es_KeyCode__WEBPACK_IMPORTED_MODULE_9__["default"].DELETE:
                this.fixCaret(this.cursorStart + 1, this.cursorStart + 1);
                break;

              default: // Do nothing

            }
          }
        }
      } catch (e) {// Do nothing
      } // Reset last key


      this.lastKeyCode = null; // pressingUpOrDown is true means that someone just click up or down button

      if (!this.pressingUpOrDown) {
        return;
      }

      if (this.props.focusOnUpDown && this.state.focused) {
        if (document.activeElement !== this.input) {
          this.focus();
        }
      }
    }
  }, {
    key: "componentWillUnmount",
    value: function componentWillUnmount() {
      this.stop();
    }
  }, {
    key: "getCurrentValidValue",
    value: function getCurrentValidValue(value) {
      var val = value;

      if (val === '') {
        val = '';
      } else if (!this.isNotCompleteNumber(parseFloat(val))) {
        val = this.getValidValue(val);
      } else {
        val = this.state.value;
      }

      return this.toNumber(val);
    }
  }, {
    key: "getValueFromEvent",
    value: function getValueFromEvent(e) {
      // optimize for chinese input expierence
      // https://github.com/ant-design/ant-design/issues/8196
      var value = e.target.value.trim().replace(/。/g, '.');

      if (isValidProps(this.props.decimalSeparator)) {
        value = value.replace(this.props.decimalSeparator, '.');
      }

      return value;
    }
  }, {
    key: "getValidValue",
    value: function getValidValue(value) {
      var min = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : this.props.min;
      var max = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : this.props.max;
      var val = parseFloat(value); // https://github.com/ant-design/ant-design/issues/7358

      if (isNaN(val)) {
        return value;
      }

      if (val < min) {
        val = min;
      }

      if (val > max) {
        val = max;
      }

      return val;
    }
  }, {
    key: "setValue",
    value: function setValue(v, callback) {
      // trigger onChange
      var precision = this.props.precision;
      var newValue = this.isNotCompleteNumber(parseFloat(v)) ? null : parseFloat(v);
      var _this$state$value = this.state.value,
          value = _this$state$value === void 0 ? null : _this$state$value;
      var _this$state$inputValu = this.state.inputValue,
          inputValue = _this$state$inputValu === void 0 ? null : _this$state$inputValu; // https://github.com/ant-design/ant-design/issues/7363
      // https://github.com/ant-design/ant-design/issues/16622

      var newValueInString = typeof newValue === 'number' ? newValue.toFixed(precision) : "".concat(newValue);
      var changed = newValue !== value || newValueInString !== "".concat(inputValue);

      if (!('value' in this.props)) {
        this.setState({
          value: newValue,
          inputValue: this.toPrecisionAsStep(v)
        }, callback);
      } else {
        // always set input value same as value
        inputValue = this.toPrecisionAsStep(this.state.value);
        this.setState({
          inputValue: inputValue
        }, callback);
      }

      if (changed) {
        this.props.onChange(newValue);
      }

      return newValue;
    } // step={1.0} value={1.51}
    // press +
    // then value should be 2.51, rather than 2.5
    // if this.props.precision is undefined
    // https://github.com/react-component/input-number/issues/39

  }, {
    key: "getMaxPrecision",
    value: function getMaxPrecision(currentValue) {
      var ratio = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : 1;
      var _this$props4 = this.props,
          precision = _this$props4.precision,
          step = _this$props4.step;

      if (isValidProps(precision)) {
        return precision;
      }

      var ratioPrecision = this.getPrecision(ratio);
      var stepPrecision = this.getPrecision(step);
      var currentValuePrecision = this.getPrecision(currentValue);

      if (!currentValue) {
        return ratioPrecision + stepPrecision;
      }

      return Math.max(currentValuePrecision, ratioPrecision + stepPrecision);
    }
  }, {
    key: "getPrecisionFactor",
    value: function getPrecisionFactor(currentValue) {
      var ratio = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : 1;
      var precision = this.getMaxPrecision(currentValue, ratio);
      return Math.pow(10, precision);
    }
  }, {
    key: "focus",
    value: function focus() {
      this.input.focus();
      this.recordCursorPosition();
    }
  }, {
    key: "blur",
    value: function blur() {
      this.input.blur();
    }
  }, {
    key: "select",
    value: function select() {
      this.input.select();
    }
  }, {
    key: "formatWrapper",
    value: function formatWrapper(num) {
      // http://2ality.com/2012/03/signedzero.html
      // https://github.com/ant-design/ant-design/issues/9439
      if (this.props.formatter) {
        return this.props.formatter(num);
      }

      return num;
    }
  }, {
    key: "toPrecisionAsStep",
    value: function toPrecisionAsStep(num) {
      if (this.isNotCompleteNumber(num) || num === '') {
        return num;
      }

      var precision = Math.abs(this.getMaxPrecision(num));

      if (!isNaN(precision)) {
        return Number(num).toFixed(precision);
      }

      return num.toString();
    }
  }, {
    key: "toNumber",
    value: function toNumber(num) {
      var precision = this.props.precision;
      var focused = this.state.focused; // num.length > 16 => This is to prevent input of large numbers

      var numberIsTooLarge = num && num.length > 16 && focused;

      if (this.isNotCompleteNumber(num) || numberIsTooLarge) {
        return num;
      }

      if (isValidProps(precision)) {
        return Math.round(num * Math.pow(10, precision)) / Math.pow(10, precision);
      }

      return Number(num);
    }
  }, {
    key: "upStep",
    value: function upStep(val, rat) {
      var step = this.props.step;
      var precisionFactor = this.getPrecisionFactor(val, rat);
      var precision = Math.abs(this.getMaxPrecision(val, rat));
      var result = ((precisionFactor * val + precisionFactor * step * rat) / precisionFactor).toFixed(precision);
      return this.toNumber(result);
    }
  }, {
    key: "downStep",
    value: function downStep(val, rat) {
      var step = this.props.step;
      var precisionFactor = this.getPrecisionFactor(val, rat);
      var precision = Math.abs(this.getMaxPrecision(val, rat));
      var result = ((precisionFactor * val - precisionFactor * step * rat) / precisionFactor).toFixed(precision);
      return this.toNumber(result);
    }
  }, {
    key: "step",
    value: function step(type, e) {
      var _this2 = this;

      var ratio = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : 1;
      var recursive = arguments.length > 3 ? arguments[3] : undefined;
      this.stop();

      if (e) {
        e.persist();
        e.preventDefault();
      }

      var props = this.props;

      if (props.disabled) {
        return;
      }

      var value = this.getCurrentValidValue(this.state.inputValue) || 0;

      if (this.isNotCompleteNumber(value)) {
        return;
      }

      var val = this["".concat(type, "Step")](value, ratio);
      var outOfRange = val > props.max || val < props.min;

      if (val > props.max) {
        val = props.max;
      } else if (val < props.min) {
        val = props.min;
      }

      this.setValue(val, null);
      if (props.onStep) props.onStep(val, {
        offset: ratio,
        type: type
      });
      this.setState({
        focused: true
      }, function () {
        _this2.pressingUpOrDown = false;
      });

      if (outOfRange) {
        return;
      }

      this.autoStepTimer = setTimeout(function () {
        _this2[type](e, ratio, true);
      }, recursive ? SPEED : DELAY);
    }
  }, {
    key: "fixCaret",
    value: function fixCaret(start, end) {
      if (start === undefined || end === undefined || !this.input || !this.input.value) {
        return;
      }

      try {
        var currentStart = this.input.selectionStart;
        var currentEnd = this.input.selectionEnd;

        if (start !== currentStart || end !== currentEnd) {
          this.input.setSelectionRange(start, end);
        }
      } catch (e) {// Fix error in Chrome:
        // Failed to read the 'selectionStart' property from 'HTMLInputElement'
        // http://stackoverflow.com/q/21177489/3040605
      }
    }
  }, {
    key: "render",
    value: function render() {
      var _classNames;

      var _this$props5 = this.props,
          prefixCls = _this$props5.prefixCls,
          disabled = _this$props5.disabled,
          readOnly = _this$props5.readOnly,
          useTouch = _this$props5.useTouch,
          autoComplete = _this$props5.autoComplete,
          upHandler = _this$props5.upHandler,
          downHandler = _this$props5.downHandler,
          className = _this$props5.className,
          max = _this$props5.max,
          min = _this$props5.min,
          style = _this$props5.style,
          title = _this$props5.title,
          onMouseEnter = _this$props5.onMouseEnter,
          onMouseLeave = _this$props5.onMouseLeave,
          onMouseOver = _this$props5.onMouseOver,
          onMouseOut = _this$props5.onMouseOut,
          required = _this$props5.required,
          onClick = _this$props5.onClick,
          tabIndex = _this$props5.tabIndex,
          type = _this$props5.type,
          placeholder = _this$props5.placeholder,
          id = _this$props5.id,
          inputMode = _this$props5.inputMode,
          pattern = _this$props5.pattern,
          step = _this$props5.step,
          maxLength = _this$props5.maxLength,
          autoFocus = _this$props5.autoFocus,
          name = _this$props5.name,
          onPaste = _this$props5.onPaste,
          onInput = _this$props5.onInput,
          rest = Object(_babel_runtime_helpers_esm_objectWithoutProperties__WEBPACK_IMPORTED_MODULE_1__["default"])(_this$props5, ["prefixCls", "disabled", "readOnly", "useTouch", "autoComplete", "upHandler", "downHandler", "className", "max", "min", "style", "title", "onMouseEnter", "onMouseLeave", "onMouseOver", "onMouseOut", "required", "onClick", "tabIndex", "type", "placeholder", "id", "inputMode", "pattern", "step", "maxLength", "autoFocus", "name", "onPaste", "onInput"]);

      var _this$state = this.state,
          value = _this$state.value,
          focused = _this$state.focused;
      var classes = classnames__WEBPACK_IMPORTED_MODULE_8___default()(prefixCls, (_classNames = {}, Object(_babel_runtime_helpers_esm_defineProperty__WEBPACK_IMPORTED_MODULE_0__["default"])(_classNames, className, !!className), Object(_babel_runtime_helpers_esm_defineProperty__WEBPACK_IMPORTED_MODULE_0__["default"])(_classNames, "".concat(prefixCls, "-disabled"), disabled), Object(_babel_runtime_helpers_esm_defineProperty__WEBPACK_IMPORTED_MODULE_0__["default"])(_classNames, "".concat(prefixCls, "-focused"), focused), _classNames));
      var dataOrAriaAttributeProps = {};
      Object.keys(rest).forEach(function (key) {
        if (key.substr(0, 5) === 'data-' || key.substr(0, 5) === 'aria-' || key === 'role') {
          dataOrAriaAttributeProps[key] = rest[key];
        }
      });
      var editable = !readOnly && !disabled; // focus state, show input value
      // unfocus state, show valid value

      var inputDisplayValue = this.getInputDisplayValue(null);
      var upDisabled = (value || value === 0) && (isNaN(value) || Number(value) >= max);
      var downDisabled = (value || value === 0) && (isNaN(value) || Number(value) <= min);
      var isUpDisabled = upDisabled || disabled || readOnly;
      var isDownDisabled = downDisabled || disabled || readOnly;
      var upClassName = classnames__WEBPACK_IMPORTED_MODULE_8___default()("".concat(prefixCls, "-handler"), "".concat(prefixCls, "-handler-up"), Object(_babel_runtime_helpers_esm_defineProperty__WEBPACK_IMPORTED_MODULE_0__["default"])({}, "".concat(prefixCls, "-handler-up-disabled"), isUpDisabled));
      var downClassName = classnames__WEBPACK_IMPORTED_MODULE_8___default()("".concat(prefixCls, "-handler"), "".concat(prefixCls, "-handler-down"), Object(_babel_runtime_helpers_esm_defineProperty__WEBPACK_IMPORTED_MODULE_0__["default"])({}, "".concat(prefixCls, "-handler-down-disabled"), isDownDisabled));
      var upEvents = useTouch ? {
        onTouchStart: isUpDisabled ? noop : this.up,
        onTouchEnd: this.stop
      } : {
        onMouseDown: isUpDisabled ? noop : this.up,
        onMouseUp: this.stop,
        onMouseLeave: this.stop
      };
      var downEvents = useTouch ? {
        onTouchStart: isDownDisabled ? noop : this.down,
        onTouchEnd: this.stop
      } : {
        onMouseDown: isDownDisabled ? noop : this.down,
        onMouseUp: this.stop,
        onMouseLeave: this.stop
      };
      return /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_7___default.a.createElement("div", {
        className: classes,
        style: style,
        title: title,
        onMouseEnter: onMouseEnter,
        onMouseLeave: onMouseLeave,
        onMouseOver: onMouseOver,
        onMouseOut: onMouseOut,
        onFocus: function onFocus() {
          return null;
        },
        onBlur: function onBlur() {
          return null;
        }
      }, /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_7___default.a.createElement("div", {
        className: "".concat(prefixCls, "-handler-wrap")
      }, /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_7___default.a.createElement("span", Object.assign({
        unselectable: "on"
      }, upEvents, {
        role: "button",
        "aria-label": "Increase Value",
        "aria-disabled": isUpDisabled,
        className: upClassName
      }), upHandler || /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_7___default.a.createElement("span", {
        unselectable: "on",
        className: "".concat(prefixCls, "-handler-up-inner"),
        onClick: preventDefault
      })), /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_7___default.a.createElement("span", Object.assign({
        unselectable: "on"
      }, downEvents, {
        role: "button",
        "aria-label": "Decrease Value",
        "aria-disabled": isDownDisabled,
        className: downClassName
      }), downHandler || /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_7___default.a.createElement("span", {
        unselectable: "on",
        className: "".concat(prefixCls, "-handler-down-inner"),
        onClick: preventDefault
      }))), /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_7___default.a.createElement("div", {
        className: "".concat(prefixCls, "-input-wrap")
      }, /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_7___default.a.createElement("input", Object.assign({
        role: "spinbutton",
        "aria-valuemin": min,
        "aria-valuemax": max,
        "aria-valuenow": value,
        required: required,
        type: type,
        placeholder: placeholder,
        onPaste: onPaste,
        onClick: onClick,
        onMouseUp: this.onMouseUp,
        className: "".concat(prefixCls, "-input"),
        tabIndex: tabIndex,
        autoComplete: autoComplete,
        onFocus: this.onFocus,
        onBlur: this.onBlur,
        onKeyDown: editable ? this.onKeyDown : noop,
        onKeyUp: editable ? this.onKeyUp : noop,
        autoFocus: autoFocus,
        maxLength: maxLength,
        readOnly: readOnly,
        disabled: disabled,
        max: max,
        min: min,
        step: step,
        name: name,
        title: title,
        id: id,
        onChange: this.onChange,
        ref: this.saveInput,
        value: this.getFullNum(inputDisplayValue),
        pattern: pattern,
        inputMode: inputMode,
        onInput: onInput
      }, dataOrAriaAttributeProps))));
    }
  }]);

  return InputNumber;
}(react__WEBPACK_IMPORTED_MODULE_7___default.a.Component);

InputNumber.defaultProps = {
  focusOnUpDown: true,
  useTouch: false,
  prefixCls: 'rc-input-number',
  max: MAX_SAFE_INTEGER,
  min: -MAX_SAFE_INTEGER,
  step: 1,
  style: {},
  onChange: noop,
  onKeyDown: noop,
  onPressEnter: noop,
  onFocus: noop,
  onBlur: noop,
  parser: defaultParser,
  required: false,
  autoComplete: 'off'
};
/* harmony default export */ __webpack_exports__["default"] = (InputNumber);

/***/ }),

/***/ "./node_modules/_rc-input-number@6.1.2@rc-input-number/es/index.js":
/*!*************************************************************************!*\
  !*** ./node_modules/_rc-input-number@6.1.2@rc-input-number/es/index.js ***!
  \*************************************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _InputNumber__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./InputNumber */ "./node_modules/_rc-input-number@6.1.2@rc-input-number/es/InputNumber.js");

/* harmony default export */ __webpack_exports__["default"] = (_InputNumber__WEBPACK_IMPORTED_MODULE_0__["default"]);

/***/ })

}]);
//# sourceMappingURL=18.js.map