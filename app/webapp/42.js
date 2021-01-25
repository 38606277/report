(window["webpackJsonp"] = window["webpackJsonp"] || []).push([[42],{

/***/ "./node_modules/_css-loader@1.0.0@css-loader/index.js!./node_modules/_postcss-loader@3.0.0@postcss-loader/src/index.js!./node_modules/_sass-loader@7.1.0@sass-loader/lib/loader.js!./src/page/dataApp/Hindex.css":
/*!*****************************************************************************************************************************************************************************************************!*\
  !*** ./node_modules/_css-loader@1.0.0@css-loader!./node_modules/_postcss-loader@3.0.0@postcss-loader/src!./node_modules/_sass-loader@7.1.0@sass-loader/lib/loader.js!./src/page/dataApp/Hindex.css ***!
  \*****************************************************************************************************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(/*! ../../../node_modules/_css-loader@1.0.0@css-loader/lib/css-base.js */ "./node_modules/_css-loader@1.0.0@css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, ".Hbox {\n  width: 100px;\n  height: 300px;\n  position: fixed;\n  top: 10px;\n  right: 100px;\n  z-index: 999921219;\n  display: -webkit-box;\n  display: -ms-flexbox;\n  display: flex; }\n\n.Hbox1 {\n  width: 100px;\n  height: 100px;\n  background-color: blue;\n  color: white;\n  text-align: center;\n  line-height: 100px; }\n\n.Hbox2 {\n  width: 100px;\n  height: 100px;\n  text-align: center;\n  line-height: 100px;\n  background-color: red;\n  color: white; }\n", ""]);

// exports


/***/ }),

/***/ "./src/page/dataApp/Hindex.css":
/*!*************************************!*\
  !*** ./src/page/dataApp/Hindex.css ***!
  \*************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(/*! !../../../node_modules/_css-loader@1.0.0@css-loader!../../../node_modules/_postcss-loader@3.0.0@postcss-loader/src!../../../node_modules/_sass-loader@7.1.0@sass-loader/lib/loader.js!./Hindex.css */ "./node_modules/_css-loader@1.0.0@css-loader/index.js!./node_modules/_postcss-loader@3.0.0@postcss-loader/src/index.js!./node_modules/_sass-loader@7.1.0@sass-loader/lib/loader.js!./src/page/dataApp/Hindex.css");
if(typeof content === 'string') content = [[module.i, content, '']];
// Prepare cssTransformation
var transform;

var options = {"hmr":true}
options.transform = transform
// add the styles to the DOM
var update = __webpack_require__(/*! ../../../node_modules/_style-loader@0.19.1@style-loader/lib/addStyles.js */ "./node_modules/_style-loader@0.19.1@style-loader/lib/addStyles.js")(content, options);
if(content.locals) module.exports = content.locals;
// Hot Module Replacement
if(false) {}

/***/ }),

/***/ "./src/page/dataApp/mcorp copy.jsx":
/*!*****************************************!*\
  !*** ./src/page/dataApp/mcorp copy.jsx ***!
  \*****************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
  value: true
});

var _tooltip = __webpack_require__(/*! antd/es/tooltip */ "./node_modules/_antd@4.9.1@antd/es/tooltip/index.js");

var _tooltip2 = _interopRequireDefault(_tooltip);

var _table = __webpack_require__(/*! antd/es/table */ "./node_modules/_antd@4.9.1@antd/es/table/index.js");

var _table2 = _interopRequireDefault(_table);

var _extends = Object.assign || function (target) { for (var i = 1; i < arguments.length; i++) { var source = arguments[i]; for (var key in source) { if (Object.prototype.hasOwnProperty.call(source, key)) { target[key] = source[key]; } } } return target; };

var _slicedToArray = function () { function sliceIterator(arr, i) { var _arr = []; var _n = true; var _d = false; var _e = undefined; try { for (var _i = arr[Symbol.iterator](), _s; !(_n = (_s = _i.next()).done); _n = true) { _arr.push(_s.value); if (i && _arr.length === i) break; } } catch (err) { _d = true; _e = err; } finally { try { if (!_n && _i["return"]) _i["return"](); } finally { if (_d) throw _e; } } return _arr; } return function (arr, i) { if (Array.isArray(arr)) { return arr; } else if (Symbol.iterator in Object(arr)) { return sliceIterator(arr, i); } else { throw new TypeError("Invalid attempt to destructure non-iterable instance"); } }; }();

__webpack_require__(/*! antd/es/tooltip/style/css */ "./node_modules/_antd@4.9.1@antd/es/tooltip/style/css.js");

__webpack_require__(/*! antd/es/table/style/css */ "./node_modules/_antd@4.9.1@antd/es/table/style/css.js");

var _react = __webpack_require__(/*! react */ "./node_modules/_react@16.12.0@react/index.js");

var _react2 = _interopRequireDefault(_react);

var _reactDom = __webpack_require__(/*! react-dom */ "./node_modules/_react-dom@16.12.0@react-dom/index.js");

var _reactDom2 = _interopRequireDefault(_reactDom);

var _insertCss = __webpack_require__(/*! insert-css */ "./node_modules/_insert-css@2.0.0@insert-css/index.js");

var _insertCss2 = _interopRequireDefault(_insertCss);

var _x = __webpack_require__(/*! @antv/x6 */ "./node_modules/_@antv_x6@1.11.0@@antv/x6/es/index.js");

__webpack_require__(/*! ./Hindex.css */ "./src/page/dataApp/Hindex.css");

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

var dataSource = [{
  key: '1',
  name: '胡彦斌',
  age: 32,
  address: '西湖区湖底公园1号'
}, {
  key: '2',
  name: '胡彦祖',
  age: 42,
  address: '西湖区湖底公园1号'
}];

var columns = [{
  title: '姓名',
  dataIndex: 'name',
  key: 'name'
}, {
  title: '年龄',
  dataIndex: 'age',
  key: 'age'
}, {
  title: '住址',
  dataIndex: 'address',
  key: 'address'
}];
var bow = function bow() {
  return _react2.default.createElement(_table2.default, { dataSource: dataSource, columns: columns });
};

var at = {
  attrs: {
    line: {
      stroke: '#808080',
      strokeWidth: 1,
      targetMarker: ''
    }
  }
};
var fn = function fn() {};

exports.default = function () {
  var box = (0, _react.useRef)();
  var tiem = (0, _react.useRef)();

  var _useState = (0, _react.useState)(_extends({}, at)),
      _useState2 = _slicedToArray(_useState, 2),
      nat = _useState2[0],
      setnat = _useState2[1];

  var _useState3 = (0, _react.useState)(['#fe854f', '#7c68fc', '#73d13d']),
      _useState4 = _slicedToArray(_useState3, 2),
      colorData = _useState4[0],
      setdata = _useState4[1];

  var _useState5 = (0, _react.useState)(true),
      _useState6 = _slicedToArray(_useState5, 2),
      o = _useState6[0],
      seto = _useState6[1];

  var aty = {
    markup: [{
      tagName: 'path',
      selector: 'wrap',
      groupSelector: 'lines'
    }, {
      tagName: 'path',
      selector: 'line1',
      groupSelector: 'lines'
    }, {
      tagName: 'path',
      selector: 'line2',
      groupSelector: 'lines'
    }, {
      tagName: 'path',
      selector: 'line3',
      groupSelector: 'lines'
    }],
    attrs: {
      lines: {
        connection: true,
        strokeDasharray: '10,20',
        strokeLinejoin: 'round',
        fill: 'none'
      },
      line1: {
        stroke: colorData[0],
        strokeWidth: 2
      },
      line2: {
        stroke: colorData[1],
        strokeDashoffset: 10,
        strokeWidth: 2
      },
      line3: {
        strokeWidth: 2,
        strokeDashoffset: 20,
        stroke: colorData[2],
        targetMarker: {
          tagName: 'path',
          stroke: '#73d13d',
          strokeWidth: 1,
          d: 'M 0 -4 0 -10 -12 0 0 10 0 4'
        }
      }
    }
  };

  var _useState7 = (0, _react.useState)(_extends({}, aty)),
      _useState8 = _slicedToArray(_useState7, 2),
      yat = _useState8[0],
      setyat = _useState8[1];

  var st = function st() {
    console.log(tiem.current);
    clearInterval(tiem.current);
    seto(!o);
  };
  var add = function add() {
    clearInterval(tiem.current);
  };
  (0, _react.useEffect)(function () {
    box.current.style.height = '1000px';
    box.current.style.widht = '2000px';
    tiem.current = setInterval(function () {
      var _console;

      //  console.log(1)
      var arr = [colorData[1], colorData[2], colorData[0]];
      setdata([].concat(arr));
      var aty = {
        markup: [{
          tagName: 'path',
          selector: 'wrap',
          groupSelector: 'lines'
        }, {
          tagName: 'path',
          selector: 'line1',
          groupSelector: 'lines'
        }, {
          tagName: 'path',
          selector: 'line2',
          groupSelector: 'lines'
        }, {
          tagName: 'path',
          selector: 'line3',
          groupSelector: 'lines'
        }],
        attrs: {
          lines: {
            connection: true,
            strokeDasharray: '10,20',
            strokeLinejoin: 'round',
            fill: 'none'
          },
          line1: {
            stroke: arr[0],
            strokeWidth: 2
          },
          line2: {
            stroke: arr[1],
            strokeDashoffset: 10,
            strokeWidth: 2
          },
          line3: {
            strokeWidth: 2,
            strokeDashoffset: 20,
            stroke: arr[2],
            targetMarker: {
              tagName: 'path',
              stroke: '#73d13d',
              strokeWidth: 1,
              d: 'M 0 -4 0 -10 -12 0 0 10 0 4'
            }
          }
        }
      };
      setyat(aty);
      (_console = console).log.apply(_console, arr);
    }, 500);
    _x.Graph.registerNode('algo-node', {
      inherit: 'rect',
      attrs: {
        body: {
          strokeWidth: 1,
          stroke: '#108ee9',
          fill: '#fff',
          rx: 15,
          ry: 15
        }
      },
      portMarkup: [{
        tagName: 'foreignObject',
        selector: 'fo',
        attrs: {
          width: 6, //控制小圆大小
          height: 6, //控制小圆大小
          x: -3,
          y: -3,
          magnet: 'true'
        },
        children: [{
          ns: _x.Dom.ns.xhtml,
          tagName: 'body',
          selector: 'foBody',
          attrs: {
            xmlns: _x.Dom.ns.xhtml
          },
          style: {
            width: '100%',
            height: '100%'
          },
          children: [{
            tagName: 'div',
            selector: 'content',
            style: {
              width: '100%',
              height: '100%'
            }
          }]
        }]
      }]
    }, true);

    // 定义边
    _x.Graph.registerConnector('algo-edge', function (source, target) {
      // console.log(source,target)
      var offset = 4;
      var control = 80;
      var v1 = { x: source.x, y: source.y + offset + control };
      var v2 = { x: target.x, y: target.y - offset - control };

      return 'M ' + source.x + ' ' + source.y + '\n                 L ' + source.x + ' ' + (source.y + offset) + '\n                 C ' + v1.x + ' ' + v1.y + ' ' + v2.x + ' ' + v2.y + ' ' + target.x + ' ' + (target.y - offset) + '\n                 L ' + target.x + ' ' + target.y + '\n                ';
    }, true);

    // 初始化画布
    var graph = new _x.Graph({
      grid: true,
      container: box.current,
      onPortRendered: function onPortRendered(args) {
        // console.log(args)
        var port = args.port;
        var contentSelectors = args.contentSelectors;
        var container = contentSelectors && contentSelectors.content;
        if (container) {
          _reactDom2.default.render(_react2.default.createElement(
            _tooltip2.default,
            { title: 'port' },
            _react2.default.createElement('div', { className: 'my-port' + (port.connected ? ' connected' : '') })
          ), container);
        }
      },

      highlighting: {
        nodeAvailable: {
          name: 'className',
          args: {
            className: 'available'
          }
        },
        magnetAvailable: {
          name: 'className',
          args: {
            className: 'available'
          }
        },
        magnetAdsorbed: {
          name: 'className',
          args: {
            className: 'adsorbed'
          }
        }
      },
      connecting: {
        snap: true,
        allowBlank: false,
        allowLoop: false,
        highlight: true,
        sourceAnchor: 'bottom',
        targetAnchor: 'center',
        connectionPoint: 'anchor',
        connector: 'algo-edge',
        createEdge: function createEdge() {
          return graph.createEdge({
            attrs: {
              line: {
                strokeDasharray: '5 5',
                stroke: '#808080',
                strokeWidth: 1,
                targetMarker: {
                  name: 'block',
                  args: {
                    size: '6'
                  }
                }
              }
            }
          });
        },
        validateMagnet: function validateMagnet(_ref) {
          var magnet = _ref.magnet;

          return magnet.getAttribute('port-group') !== 'in';
        },
        validateConnection: function validateConnection(_ref2) {
          var sourceView = _ref2.sourceView,
              targetView = _ref2.targetView,
              sourceMagnet = _ref2.sourceMagnet,
              targetMagnet = _ref2.targetMagnet;

          // 只能从输出链接桩创建连接
          if (!sourceMagnet || sourceMagnet.getAttribute('port-group') === 'in') {
            return false;
          }

          // 只能连接到输入链接桩
          if (!targetMagnet || targetMagnet.getAttribute('port-group') !== 'in') {
            return false;
          }

          // 判断目标链接桩是否可连接
          var portId = targetMagnet.getAttribute('port');
          var node = targetView.cell;
          var port = node.getPort(portId);
          if (port && port.connected) {
            return false;
          }

          return true;
        }
      }
    });

    graph.on('edge:connected', function (args) {
      // console.log(args.currentCel)
      var edge = args.edge;
      var node = args.currentCel;
      var elem = args.currentMagnet;
      var portId = elem.getAttribute('port');

      // 触发 port 重新渲染
      node.setPortProp(portId, 'connected', true);

      // 更新连线样式
      edge.attr({
        line: {
          strokeDasharray: '',
          targetMarker: ''
        }
      });
    });

    var a3 = graph.addNode({
      x: 380,
      y: 180,
      width: 160,
      height: 30,
      shape: 'algo-node',
      label: '算法二',
      ports: {
        items: [{ group: 'in', id: 'in1' }, { group: 'in', id: 'in2' }, { group: 'out', id: 'out1' }, { group: 'out', id: 'out2' }],
        groups: {
          in: {
            position: { name: 'top' },
            zIndex: 1
          },
          out: {
            position: { name: 'bottom' },
            zIndex: 1
          }
        }
      }
    });

    var source = graph.addNode({
      x: 200,
      y: 50,
      width: 160,
      height: 30,
      shape: 'algo-node',
      label: '算法1',
      ports: {
        items: [{ group: 'in', id: 'in1' }, { group: 'in', id: 'in2' }, { group: 'out', id: 'out1' }, { group: 'out', id: 'out2' }],
        groups: {
          in: {
            position: { name: 'top' },
            zIndex: 1
          },
          out: {
            position: { name: 'bottom' },
            zIndex: 1
          }
        }
      }
    });

    var target = graph.addNode({
      x: 120,
      y: 260,
      width: 160,
      height: 30,
      shape: 'algo-node',
      // label: '算法3',
      attrs: {
        body: {
          fill: '#f5f5f5',
          stroke: '#d9d9d9'
        }
      },
      ports: {
        items: [{ group: 'in', id: 'in1', connected: true }, { group: 'in', id: 'in2' }, { group: 'out', id: 'out1' }],
        groups: {
          in: {
            position: { name: 'top' },
            zIndex: 1
          },
          out: {
            position: { name: 'bottom' },
            zIndex: 1
          }
        }
      },
      html: function html() {
        var div = document.createElement('div');
        div.innerHTML = '1111';
        return div;
      }
    });
    graph.addEdge(_extends({
      source: { cell: source, port: 'out1' },
      target: { cell: target, port: 'in1' }
    }, o ? _extends({}, yat) : _extends({}, nat)));
    graph.addEdge(_extends({
      source: { cell: source, port: 'out1' },
      target: { cell: a3, port: 'in1' }
    }, !o ? _extends({}, yat) : _extends({}, nat)));
    (0, _insertCss2.default)('\n            .x6-node [magnet="true"] {\n              cursor: crosshair;\n              transition: none;\n            }\n\n            .x6-node [magnet="true"]:hover {\n              opacity: 1;\n            }\n\n            .x6-node [magnet="true"][port-group="in"] {\n              cursor: move;\n            }\n\n            .my-port {\n              width: 100%;\n              height: 100%;\n              border: 1px solid #808080;\n              border-radius: 100%;\n              background: #eee;\n            }\n\n            .my-port.connected {\n              width: 0;\n              height: 0;\n              margin-top: 5px;\n              margin-left: 1px;\n              border-width: 5px 4px 0;\n              border-style: solid;\n              border-color: #808080 transparent transparent;\n              border-radius: 0;\n              background-color: transparent;\n            }\n\n            .x6-port-body.available {\n              overflow: visible;\n            }\n\n            .x6-port-body.available body {\n              overflow: visible;\n            }\n\n            .x6-port-body.available body > div::before {\n              content: " ";\n              float: left;\n              width: 20px;\n              height: 20px;\n              margin-top: -5px;\n              margin-left: -5px;\n              border-radius: 50%;\n              background-color: rgba(57, 202, 116, 0.6);\n              box-sizing: border-box;\n            }\n\n            .x6-port-body.available body > div::after {\n              content: " ";\n              float: left;\n              clear: both;\n              width: 10px;\n              height: 10px;\n              margin-top: -15px;\n              border-radius: 50%;\n              background-color: #fff;\n              border: 1px solid #39ca74;\n              position: relative;\n              z-index: 10;\n              box-sizing: border-box;\n            }\n\n            .x6-port-body.adsorbed {\n              overflow: visible;\n            }\n\n            .x6-port-body.adsorbed body {\n              overflow: visible;\n            }\n\n            .x6-port-body.adsorbed body > div::before {\n              content: " ";\n              float: left;\n              width: 28px;\n              height: 28px;\n              margin-top: -9px;\n              margin-left: -9px;\n              border-radius: 50%;\n              background-color: rgba(57, 202, 116, 0.6);\n              box-sizing: border-box;\n            }\n\n            .x6-port-body.adsorbed body > div::after {\n              content: " ";\n              float: left;\n              clear: both;\n              width: 10px;\n              height: 10px;\n              margin-top: -19px;\n              border-radius: 50%;\n              background-color: #fff;\n              border: 1px solid #39ca74;\n              position: relative;\n              z-index: 10;\n              box-sizing: border-box;\n            }\n        ');
    return function () {
      clearInterval(tiem.current);
    };
  }, [colorData, o]);
  return _react2.default.createElement(
    'div',
    null,
    _react2.default.createElement('div', { ref: box, id: 'connected' }),
    _react2.default.createElement(
      'div',
      { className: 'Hbox' },
      _react2.default.createElement(
        'div',
        { className: 'Hbox1', onClick: function onClick() {
            return add();
          } },
        '\u505C\u6B62'
      ),
      _react2.default.createElement(
        'div',
        { className: 'Hbox2', onClick: function onClick() {
            return st();
          } },
        '\u5207\u6362'
      )
    )
  );
};

/***/ })

}]);
//# sourceMappingURL=42.js.map