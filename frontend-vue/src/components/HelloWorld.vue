<template>
  <div id = "echart-example">
    <dialog open id = "dialog-histoire"></dialog>
  </div>
</template>

<script>
import * as echarts from 'echarts';
import axios from 'axios';
import 'echarts-wordcloud';
import $ from 'jquery';

export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },
  // data() {
  //   return {
  //     audio: new Audio()
  //   }
  // },
  methods: {
    initialize() {
      var div = document.getElementById('echart-example'); 
      div.id = 'echart-example';
      div.style.backgroundImage = "url('Resources/background3.png')";
      div.style.width = `${window.innerWidth * 0.99}px`;
      div.style.height = `${window.innerHeight * 0.965}px`;
      var dialog = document.createElement('dialog');
      div.appendChild(dialog);
      dialog.style.width = "640px";
      dialog.style.height = "400px";
      dialog.style.position = "absolute";
      dialog.style.left = `0px`;
      dialog.style.top = `${div.clientHeight * 0.1}px`;
      dialog.style.padding = "0";
      dialog.style.borderStyle = "solid";
      dialog.style.borderWidth = "5px";
      dialog.style.borderColor = "rgb(141,82,200)";
      dialog.style.backgroundColor = "rgb(255,248,255)";

      var p = document.createElement('p');
      p.style.textAlign = "left";
      p.style.position = "absolute";
      p.style.left = `20px`;
      dialog.appendChild(p);
      p.appendChild(document.createElement('br'));
      p.appendChild(document.createElement('br'));

      var text = document.createElement('span');
      text.style.position = "absulote";
      text.style.color = "#1600FD";
      text.textContent = "本作品的可视化由vue, cubism呈现,\n";
      text.style.fontSize = "25px";
      p.appendChild(text);
      p.appendChild(document.createElement('br'));

      text = document.createElement('span');
      text.style.position = "absulote";
      text.style.color = "#1600FD";
      text.style.fontSize = "25px";
      text.textContent = "操作,交互方式及素材设计参考《主播女孩重度依赖》,\n";
      p.appendChild(text);
      p.appendChild(document.createElement('br'));

      text = document.createElement('span');
      text.style.position = "absulote";
      text.style.color = "#1600FD";
      text.style.fontSize = "25px";
      text.textContent = "live2d形象包括CubismSDK提供的样例模型以及参考\n";
      p.appendChild(text);
      p.appendChild(document.createElement('br'));

      text = document.createElement('span');
      text.style.position = "absulote";
      text.style.color = "#1600FD";
      text.style.fontSize = "25px";
      text.textContent = "《超次元游戏海王星:重生3》中的角色自主创作的模型.\n";
      p.appendChild(text);
      p.appendChild(document.createElement('br'));

      text = document.createElement('span');
      text.style.position = "absulote";
      text.style.color = "#1600FD";
      text.style.fontSize = "25px";
      text.textContent = "关掉这个开始吧.(该操作不可返回！！！)\n";
      p.appendChild(text);
      p.appendChild(document.createElement('br'));

      var header = document.createElement('div');
      dialog.appendChild(header);
      header.style.position = "absolute";
      header.style.left = "-0.5px";
      header.style.top = "-1px";
      header.style.width = "640.5px";
      header.style.height = "27px";
      header.style.backgroundImage = "url(Resources/dialog_header.png)";

      text = document.createElement('span');
      header.appendChild(text);
      text.style.position = "absulote";
      text.style.color = "#1600FD";
      text.textContent = "Caution";
      text.style.position = "absolute";
      text.style.fontSize = "25px";
      text.style.left = "5px";

      var button = document.createElement("button");
      dialog.appendChild(button);
      button.id = "close-column";
      button.style.backgroundImage = "url('Resources/close_button.png')";
      button.style.backgroundSize = "24px 24px";
      button.style.backgroundColor = "transparent";
      button.style.position = "absolute";
      button.style.right = `4px`;
      button.style.top = `2px`;
      button.style.width = "24px";
      button.style.height = "24px";
      button.style.backgroundRepeat = "no-repeat";
      button.style.border = "none";
      var _this = this;
      button.onclick = function() {
        _this.loadScene();
        dialog.remove();
      }
      this.setMovement(header, dialog);
      dialog.open = true;

      var dialog_histoire = document.getElementById("dialog-histoire");
      dialog_histoire.style.width = "480px";
      dialog_histoire.style.height = "320px";
      dialog_histoire.style.position = "absolute";
      dialog_histoire.style.left = `0px`;
      dialog_histoire.style.top = `${div.clientHeight * 0.15}px`;
      dialog_histoire.style.padding = "0";
      dialog_histoire.style.borderStyle = "solid";
      dialog_histoire.style.borderWidth = "5px";
      dialog_histoire.style.borderColor = "rgb(141,82,200)";

      header = document.createElement('div');
      dialog_histoire.appendChild(header);
      header.style.position = "absolute";
      header.style.left = "-0.5px";
      header.style.top = "-1px";
      header.style.width = "480.5px";
      header.style.height = "20px";
      header.style.backgroundImage = "url(Resources/dialog_header.png)";
      this.setMovement(header, dialog_histoire);
    },
    createButton(parent, sid, url, left, top, onclick) {
      var button = document.createElement('button');
      parent.appendChild(button);
      button.id = sid;
      button.style.backgroundImage = `url(${url})`;
      button.style.backgroundSize = "64px 64px";
      button.style.backgroundColor = "transparent";
      button.style.position = "absolute";
      button.style.left = left;
      button.style.top = top;
      button.style.width = "64px";
      button.style.height = "64px";
      button.style.border = "none";
      button.onclick = onclick;      
    },
    loadScene() {
      var audio = document.getElementById("audio1");
      audio.src = "BGM/bgm7.mp3";
      var div = document.getElementById('echart-example');
      this.createButton(
        div, 
        "column-button", 
        "Resources/column_chart.png", 
        `${div.clientWidth * 0.05}px`,
        `${div.clientHeight * 0.05}px`,
        this.onClickColumnButton
      );
      this.createButton(
        div, 
        "line-chart", 
        "Resources/line_chart.png", 
        `${div.clientWidth * 0.05}px`,
        `${div.clientHeight * 0.1 + 64}px`,
        this.onClickLineButton
      );
      this.createButton(
        div, 
        "pie-chart", 
        "Resources/pie_chart.png", 
        `${div.clientWidth * 0.05}px`,
        `${div.clientHeight * 0.15 + 128}px`,
        this.onClickColumnButton
      );
      this.createButton(
        div, 
        "word-button", 
        "Resources/word_cloud.png", 
        `${div.clientWidth * 0.05}px`,
        `${div.clientHeight * 0.20 + 192}px`,
        this.onClickWordCloud
      );
      this.createButton(
        div, 
        "dynamic-button", 
        "Resources/dynamic_column.png", 
        `${div.clientWidth * 0.05}px`,
        `${div.clientHeight * 0.25 + 256}px`,
        this.onClickDynamicButton
      );
      this.createButton(
        div, 
        "data-table", 
        "Resources/data_table.png", 
        `${div.clientWidth * 0.1 + 64}px`,
        `${div.clientHeight * 0.05}px`,
        this.onClickTableButton
      );
    },
    setMovement(element, dom) {
      // 获取原有属性 ie dom元素.currentStyle 火狐谷歌 window.getComputedStyle(dom元素, null);
      const getStyle = (function() {
        if (window.document.currentStyle) {
          return (dom, attr) => dom.currentStyle[attr]
        } else {
          return (dom, attr) => getComputedStyle(dom, false)[attr]
        }
      })();

      element.onmousedown = (e) => {
        const disX = e.clientX - element.offsetLeft;
        const disY = e.clientY - element.offsetTop;
        const dragDomWidth = dom.offsetWidth;
        const dragDomHeight = dom.offsetHeight;
        const screenWidth = document.body.clientWidth;
        const screenHeight = document.documentElement.clientHeight;
        const minDragDomLeft = dom.offsetLeft;
        const maxDragDomLeft = screenWidth - dom.offsetLeft - dragDomWidth;
        const minDragDomTop = dom.offsetTop;
        const maxDragDomTop = screenHeight - dom.offsetTop - dragDomHeight;
        let styL = getStyle(dom, "left");
        let styT = getStyle(dom, "top");
        if (styL.includes('%')) {
          styL = +document.body.clientWidth * (+styL.replace(/%/g, '') / 100)
          styT = +document.body.clientHeight * (+styT.replace(/%/g, '') / 100)
        } else {
          styL = +styL.replace(/\px/g, '')
          styT = +styT.replace(/\px/g, '')
        }

        document.onmousemove = function(e) {
          // 通过事件委托，计算移动的距离
          let left = e.clientX - disX
          let top = e.clientY - disY

          // 边界处理
          if (-(left) > minDragDomLeft) {
            left = -minDragDomLeft
          } else if (left > maxDragDomLeft) {
            left = maxDragDomLeft
          }

          if (-(top) > minDragDomTop) {
            top = -minDragDomTop
          } else if (top > maxDragDomTop - 20) {
            top = maxDragDomTop - 20
          }

          // 移动当前元素
          dom.style.left = `${left + styL}px`;
          dom.style.top = `${top + styT}px`;

          // emit onDrag event
          // vnode.child.$emit('dragdom')
        }

        document.onmouseup = function() {
          document.onmousemove = null
          document.onmouseup = null
        }
      }
    },
    createDialog(parent, sid, left, top, width, height, headerText) {
      var dialog = document.createElement('dialog');
      parent.appendChild(dialog);
      var header = document.createElement('div');
      dialog.appendChild(header);
      header.style.position = "absolute";
      header.style.left = "-0.5px";
      header.style.top = "-1px";
      header.style.width = `${width + 1}px`;
      header.style.height = "20px";
      if (sid == "table-dialog") {
        header.style.width = `${width + 2}px`;
        header.style.height = "22px";
      }
      header.style.backgroundImage = "url(Resources/dialog_header.png)";
      this.setMovement(header, dialog);

      var text = document.createElement('span');
      header.appendChild(text);
      text.style.position = "absulote";
      text.style.color = "#1600FD";
      text.textContent = headerText;
      text.style.position = "absolute";
      text.style.fontSize = "18px";
      text.style.left = "5px";

      dialog.id = sid;
      dialog.style.width = `${width}px`;
      dialog.style.height = height;
      dialog.style.position = "absulote";
      dialog.style.left = left;
      dialog.style.top = top;
      dialog.style.padding = "0";
      dialog.style.borderStyle = "solid";
      dialog.style.borderWidth = "5px";
      dialog.style.borderColor = "rgb(141,82,200)";
      dialog.onclose = this.onclose;
      dialog.open = true;

      var button = document.createElement("button");
      dialog.appendChild(button);
      button.style.backgroundImage = "url('Resources/close_button.png')";
      button.style.backgroundSize = "16px 16px";
      button.style.backgroundColor = "transparent";
      button.style.position = "absolute";
      button.style.right = `3px`;
      button.style.top = `2px`;
      button.style.width = "16px";
      button.style.height = "16px";
      button.style.border = "none";
      button.onclick = function() {
        dialog.remove();
      }
    },
    onClickColumnButton() {
      if (document.getElementById('column-dialog') !== null) {
        return;
      }

      var echart_example = document.getElementById('echart-example');
      this.createDialog(
        echart_example,
        "column-dialog",
        `${echart_example.clientWidth * 0.1}px`,
        `${echart_example.clientHeight * 0.1}px`,
        640,
        "400px",
        "Column Chart"
      );
      var dialog = document.getElementById("column-dialog");

      var echart = document.createElement('div');
      echart.style.position = "absolute";
      echart.style.width = "600px"
      echart.style.height = "300px";
      echart.style.left = "5%";
      echart.style.bottom = "5%";
      dialog.appendChild(echart);
      var myChart = echarts.init(echart);

      axios.get("http://10.25.204.52:8080/data/col")
      .then(response => {
        // 绘制图表
        myChart.setOption({
          title: {
            text: 'World Population'
          },
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              type: 'shadow'
            }
          },
          legend: {},
          grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
          },
          xAxis: {
            type: 'value',
            boundaryGap: [0, 0.01]
          },
          yAxis: {
            type: 'category',
            data: response.data.framework
          },
          series: [
            {
              name: 'Stars',
              type: 'bar',
              data: [18203, 23489, 29034, 104970, 131744, 630230]
            },
            {
              name: '2012',
              type: 'bar',
              data: [19325, 23438, 31000, 121594, 134141, 681807]
            }
          ]
        });
      })
      .catch(function (error) { // 请求失败处理
        console.log(error);
        // 绘制图表
        myChart.setOption({
          title: {
            text: 'ECharts 入门示例'
          },
          tooltip: {},
          xAxis: {
            data: ['衬衫', '羊毛衫', '雪纺衫', '裤子', '高跟鞋', '袜子']
          },
          yAxis: {},
          series: [
            {
              name: '销量',
              type: 'bar',
              data: [5, 20, 36, 10, 10, 20]
            }
          ]
        });        
      })


    },
    onClickLineButton() {
      if (document.getElementById('line-dialog') !== null) {
        return;
      }
      var echart_example = document.getElementById('echart-example');
      this.createDialog(
        echart_example,
        "line-dialog",
        `${echart_example.clientWidth * 0.1}px`,
        `${echart_example.clientHeight * 0.1}px`,
        640,
        "400px",
        "Line Chart"
      );
      var dialog = document.getElementById("line-dialog");

      var echart = document.createElement('div');
      echart.style.position = "absolute";
      echart.style.width = "600px"
      echart.style.height = "300px";
      echart.style.left = "5%";
      echart.style.bottom = "5%";
      dialog.appendChild(echart);
      var myChart = echarts.init(echart);
      axios.get("http://10.25.204.52:8080/data/line")
      .then(response => {
        var frameworks = response.data.frameworks;
        var years = response.data.years;
        var popularity = response.data.popularity;
        // console.log(`$$$$$$$\n${response.data.stars}`)
        // 绘制图表
        myChart.setOption({
          title: {
            text: 'Stacked Line'
          },
          tooltip: {
            trigger: 'axis'
          },
          legend: {
            data: frameworks
          },
          grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
          },
          toolbox: {
            feature: {
              saveAsImage: {}
            }
          },
          xAxis: {
            type: 'category',
            boundaryGap: false,
            data: years
          },
          yAxis: {
            type: 'value'
          },
          series: [
            {
              name: frameworks[0],
              type: 'line',
              stack: 'Total',
              data: popularity[0]
            },
            {
              name: frameworks[1],
              type: 'line',
              stack: 'Total',
              data: popularity[1]
            }
          ]
        });
      })
      .catch(function (error) { // 请求失败处理
        console.log(error);
        myChart.setOption({
          title: {
            text: 'Stacked Line'
          },
          tooltip: {
            trigger: 'axis'
          },
          legend: {
            data: ['Email', 'Union Ads', 'Video Ads', 'Direct', 'Search Engine']
          },
          grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
          },
          toolbox: {
            feature: {
              saveAsImage: {}
            }
          },
          xAxis: {
            type: 'category',
            boundaryGap: false,
            data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
          },
          yAxis: {
            type: 'value'
          },
          series: [
            {
              name: 'Email',
              type: 'line',
              stack: 'Total',
              data: [120, 132, 101, 134, 90, 230, 210]
            },
            {
              name: 'Union Ads',
              type: 'line',
              stack: 'Total',
              data: [220, 182, 191, 234, 290, 330, 310]
            },
            {
              name: 'Video Ads',
              type: 'line',
              stack: 'Total',
              data: [150, 232, 201, 154, 190, 330, 410]
            },
            {
              name: 'Direct',
              type: 'line',
              stack: 'Total',
              data: [320, 332, 301, 334, 390, 330, 320]
            },
            {
              name: 'Search Engine',
              type: 'line',
              stack: 'Total',
              data: [820, 932, 901, 934, 1290, 1330, 1320]
            }
          ]
        })
      });
    },
    onClickPieButton() {
      if (document.getElementById('pie-dialog') !== null) {
        return;
      }
      axios.get("http://10.25.204.52:8080/data/test")
      .then(response => (
        console.log(`$$$$$$$\n${response.data.stars}`)
      ))
      .catch(function (error) { // 请求失败处理
        console.log(error);
      })
      var echart_example = document.getElementById('echart-example');
      this.createDialog(
        echart_example,
        "pie-dialog",
        `${echart_example.clientWidth * 0.1}px`,
        `${echart_example.clientHeight * 0.1}px`,
        640,
        "400px",
        "Pie Chart"
      );
      var dialog = document.getElementById("pie-dialog");

      var echart = document.createElement('div');
      echart.style.position = "absolute";
      echart.style.width = "600px"
      echart.style.height = "300px";
      echart.style.left = "5%";
      echart.style.bottom = "5%";
      dialog.appendChild(echart);
      var myChart = echarts.init(echart);
      // 绘制图表
      myChart.setOption({
        title: {
          text: 'Referer of a Website',
          subtext: 'Fake Data',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          left: 'left'
        },
        series: [
          {
            name: 'Access From',
            type: 'pie',
            radius: '50%',
            data: [
              { value: 1048, name: 'Search Engine' },
              { value: 735, name: 'Direct' },
              { value: 580, name: 'Email' },
              { value: 484, name: 'Union Ads' },
              { value: 300, name: 'Video Ads' }
            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      });
    },
    onClickWordCloud() {
      if (document.getElementById('word-cloud') !== null) {
        return;
      }
      axios.get("http://10.25.204.52:8080/data/test")
      .then(response => (
        console.log(`$$$$$$$\n${response.data.stars}`)
      ))
      .catch(function (error) { // 请求失败处理
        console.log(error);
      })
      var echart_example = document.getElementById('echart-example');
      this.createDialog(
        echart_example,
        "word-cloud",
        `${echart_example.clientWidth * 0.1}px`,
        `${echart_example.clientHeight * 0.1}px`,
        640,
        "400px",
        "Word Cloud"
      );
      var dialog = document.getElementById("word-cloud");

      var echart = document.createElement('div');
      echart.style.position = "absolute";
      echart.style.width = "600px"
      echart.style.height = "300px";
      echart.style.left = "5%";
      echart.style.top = "50px";
      dialog.appendChild(echart);
      var myChart = echarts.init(echart);
      var keywords = [{"name":"男神","value":2.64},
                      {"name":"好身材","value":4.03},
                      {"name":"校草","value":24.95},
                      {"name":"酷","value":4.04},
                      {"name":"时尚","value":5.27},
                      {"name":"阳光活力","value":5.80},
                      {"name":"初恋","value":3.09},
                      {"name":"英俊潇洒","value":24.71},
                      {"name":"霸气","value":6.33},
                      {"name":"腼腆","value":2.55},
                      {"name":"蠢萌","value":3.88},
                      {"name":"青春","value":8.04},
                      {"name":"网红","value":5.87},
                      {"name":"萌","value":6.97},
                      {"name":"认真","value":2.53},
                      {"name":"古典","value":2.49},
                      {"name":"温柔","value":3.91},
                      {"name":"有个性","value":3.25},
                      {"name":"可爱","value":9.93},
                      {"name":"幽默诙谐","value":3.65}]
      myChart.setOption({
        series: [{
            type: 'wordCloud',
            //maskImage: maskImage,
            sizeRange: [15, 80],
            rotationRange: [0, 0],
            rotationStep: 45,
            gridSize: 8,
            shape: 'pentagon',
            width: '100%',
            height: '100%',
              textStyle: {
                normal: {
                    color: function () {
                        return 'rgb(' + [
                            Math.round(Math.random() * 160),
                            Math.round(Math.random() * 160),
                            Math.round(Math.random() * 160)
                        ].join(',') + ')';
                    },
                    fontFamily: 'sans-serif',
                    fontWeight: 'normal'
                },
                emphasis: {
                    shadowBlur: 10,
                    shadowColor: '#333'
                }
            },
            data: keywords
        }]
      });
    },
    onClickDynamicButton() {
      if (document.getElementById('dynamic-column') !== null) {
        return;
      }
      var echart_example = document.getElementById('echart-example');
      this.createDialog(
        echart_example,
        "dynamic-column",
        `${echart_example.clientWidth * 0.1}px`,
        `${echart_example.clientHeight * 0.05}px`,
        640,
        "500px",
        "Dynamic Column"
      );

      var dialog = document.getElementById("dynamic-column");

      var echart = document.createElement('div');
      echart.style.position = "absolute";
      echart.style.width = "600px"
      echart.style.height = "400px";
      echart.style.left = "5%";
      echart.style.top = "50px";
      dialog.appendChild(echart);
      var myChart = echarts.init(echart);

      var ROOT_PATH =
        'https://fastly.jsdelivr.net/gh/apache/echarts-website@asf-site/examples';
      var option;

      const updateFrequency = 2000;
      const dimension = 0;
      const countryColors = {
        Australia: '#00008b',
        Canada: '#f00',
        China: '#ffde00',
        Cuba: '#002a8f',
        Finland: '#003580',
        France: '#ed2939',
        Germany: '#000',
        Iceland: '#003897',
        India: '#f93',
        Japan: '#bc002d',
        'North Korea': '#024fa2',
        'South Korea': '#000',
        'New Zealand': '#00247d',
        Norway: '#ef2b2d',
        Poland: '#dc143c',
        Russia: '#d52b1e',
        Turkey: '#e30a17',
        'United Kingdom': '#00247d',
        'United States': '#b22234'
      };
      $.when(
        $.getJSON('https://fastly.jsdelivr.net/npm/emoji-flags@1.3.0/data.json'),
        $.getJSON(ROOT_PATH + '/data/asset/data/life-expectancy-table.json')
      ).done(function (res0, res1) {
        const flags = res0[0];
        const data = res1[0];
        const years = [];
        for (let i = 0; i < data.length; ++i) {
          if (years.length === 0 || years[years.length - 1] !== data[i][4]) {
            years.push(data[i][4]);
          }
        }
        function getFlag(countryName) {
          if (!countryName) {
            return '';
          }
          return (
            flags.find(function (item) {
              return item.name === countryName;
            }) || {}
          ).emoji;
        }
        let startIndex = 10;
        let startYear = years[startIndex];
        option = {
          grid: {
            top: 10,
            bottom: 30,
            left: 150,
            right: 80
          },
          xAxis: {
            max: 'dataMax',
            axisLabel: {
              formatter: function (n) {
                return Math.round(n) + '';
              }
            }
          },
          dataset: {
            source: data.slice(1).filter(function (d) {
              return d[4] === startYear;
            })
          },
          yAxis: {
            type: 'category',
            inverse: true,
            max: 10,
            axisLabel: {
              show: true,
              fontSize: 14,
              formatter: function (value) {
                return value + '{flag|' + getFlag(value) + '}';
              },
              rich: {
                flag: {
                  fontSize: 25,
                  padding: 5
                }
              }
            },
            animationDuration: 300,
            animationDurationUpdate: 300
          },
          series: [
            {
              realtimeSort: true,
              seriesLayoutBy: 'column',
              type: 'bar',
              itemStyle: {
                color: function (param) {
                  return countryColors[param.value[3]] || '#5470c6';
                }
              },
              encode: {
                x: dimension,
                y: 3
              },
              label: {
                show: true,
                precision: 1,
                position: 'right',
                valueAnimation: true,
                fontFamily: 'monospace'
              }
            }
          ],
          // Disable init animation.
          animationDuration: 0,
          animationDurationUpdate: updateFrequency,
          animationEasing: 'linear',
          animationEasingUpdate: 'linear',
          graphic: {
            elements: [
              {
                type: 'text',
                right: 160,
                bottom: 60,
                style: {
                  text: startYear,
                  font: 'bolder 80px monospace',
                  fill: 'rgba(100, 100, 100, 0.25)'
                },
                z: 100
              }
            ]
          }
        };
        // console.log(option);
        myChart.setOption(option);
        for (let i = startIndex; i < years.length - 1; ++i) {
          (function (i) {
            setTimeout(function () {
              updateYear(years[i + 1]);
            }, (i - startIndex) * updateFrequency);
          })(i);
        }
        function updateYear(year) {
          let source = data.slice(1).filter(function (d) {
            return d[4] === year;
          });
          option.series[0].data = source;
          option.graphic.elements[0].style.text = year;
          myChart.setOption(option);
        }
      });
      option && myChart.setOption(option);
    },
    onClickTableButton() {
      if (document.getElementById('table-dialog') !== null) {
        return;
      }
      let _this = this;

      function setTr(parent, backgroundColor, width, text, fontSize, textColor, url) {
        var td = document.createElement('td');
        td.style.backgroundColor = backgroundColor;
        td.style.width = width;
        td.style.maxWidth = width;
        parent.appendChild(td);
        if (url !== null) {
          var a = document.createElement('a');
          a.textContent = text;
          a.onclick = () => {
            window.open(url);
          }
          a.style.maxWidth = width;
          a.style.fontSize = fontSize;
          a.style.color = "#5569fd"; // "#5569fd"
          td.appendChild(a);
        } else {
          var span = document.createElement('span');
          span.textContent = text;
          span.style.fontSize = fontSize;
          span.style.color = textColor;
          td.append(span);
          if (text == "Repositories") {
            span.onclick = () => {
              _this.createDialog(
                document.getElementById('table-dialog'),
                "repository-select",
                "50px",
                "50px",
                500,
                "300px",
                "Select Framworks"
              )
              var repository_select = document.getElementById('repository-select');
              var form = document.createElement('form');
              repository_select.appendChild(form);
              form.method = "get";
              form.textContent = "Java Frameworks";
              form.style.position = "absolute";
              form.style.top = "50px";
              form.style.left = "50px";
              form.style.maxWidth = "400";
              form.appendChild(document.createElement('br'));

              function createSelection(text) {
                var label = document.createElement('label');
                label.textContent = text;
                var input = document.createElement('input');
                input.name = "Framework";
                input.id = text;
                input.type = "checkbox";
                input.value = text;
                label.appendChild(input);
                form.appendChild(label);
              }
              createSelection("Spring");
              createSelection("Struts");
              createSelection("Spark");
              createSelection("GWT");
              createSelection("Dropwizard");

              var button = document.createElement('button');
              repository_select.appendChild(button);
              button.style.position = "absolute";
              button.style.bottom = "50px";
              button.style.left = "150px";
              button.style.backgroundImage = "url(Resources/ok_button.png)";
              button.style.width = "200px";
              button.style.height = "50px";
              
              var span = document.createElement('span');
              span.textContent = "OK";
              span.style.color = "#1600FD";
              span.style.fontSize = "23px";
              button.appendChild(span);

              button.onclick = () => {
                axios.get("http://10.25.204.52:8080/data/table",{
                  params: {
                    Spring: document.getElementById("Spring").checked,
                    Struts: document.getElementById("Struts").checked,
                    Spark: document.getElementById("Spark").checked,
                    GWT: document.getElementById("GWT").checked,
                    Dropwizard: document.getElementById("Dropwizard").checked
                  }
                })
                .then(response => {
                  console.log(`$$$$$$$\n${response.data}`)
                  var data = response.data;
                  for (var i = 0; i < data.length; i++) {
                    tr = document.createElement('tr');
                    table.append(tr);
                    setTr(table, "#d7f4ff", "250px", data[i].repoName, "20px", "#000000", data[i].url);
                    setTr(table, "#d7f4ff", "75px", data[i].stars, "20px", "#000000", null);
                    setTr(table, "#d7f4ff", "75px", data[i].forks, "20px", "#000000", null);
                    setTr(table, "#d7f4ff", "100px", data[i].create_date, "20px", "#000000", null);
                    setTr(table, "#d7f4ff", "100px", data[i].update_date, "20px", "#000000", null);
                  }
                })
                repository_select.remove();
              }
            }
          }          
        }
      }      

      var echart_example = document.getElementById('echart-example');
      this.createDialog(
        echart_example,
        "table-dialog",
        `${echart_example.clientWidth * 0.1}px`,
        `${echart_example.clientHeight * 0.1}px`,
        640,
        "400px",
        "Repository Table"
      );
      var dialog = document.getElementById("table-dialog");
      dialog.style.overflow = "auto";

      var echart = document.createElement('div');
      echart.style.position = "absolute";
      echart.style.width = "600px";
      echart.style.height = "300px";
      echart.style.left = "2%";
      echart.style.top = "25px";
      dialog.appendChild(echart);

      var table = document.createElement('table');
      table.style.maxHeight = `${echart.clientHeight * 0.8}`;
      echart.appendChild(table);
      var tr = document.createElement('tr');
      table.appendChild(tr);
      setTr(table, "#b8ecfb", "250px", "Repositories", "25px", "#000000", null);
      setTr(table, "#b8ecfb", "75px", "Stars", "25px", "#000000", null);
      setTr(table, "#b8ecfb", "75px", "Forks", "25px", "#000000", null);
      setTr(table, "#b8ecfb", "100px", "Date", "25px", "#000000", null);
      setTr(table, "#b8ecfb", "100px", "Update", "25px", "#000000", null);

      axios.get("http://10.25.204.52:8080/data/table")
      .then(response => {
        console.log(`$$$$$$$\n${response.data}`)
        var data = response.data;
        for (var i = 0; i < data.length; i++) {
          tr = document.createElement('tr');
          table.append(tr);
          setTr(table, "#d7f4ff", "250px", data[i].repoName, "20px", "#000000", data[i].url);
          setTr(table, "#d7f4ff", "75px", data[i].stars, "20px", "#000000", null);
          setTr(table, "#d7f4ff", "75px", data[i].forks, "20px", "#000000", null);
          setTr(table, "#d7f4ff", "100px", data[i].create_date, "20px", "#000000", null);
          setTr(table, "#d7f4ff", "100px", data[i].update_date, "20px", "#000000", null);
        }
      })
      .catch(function (error) { // 请求失败处理
        console.log(error);
        tr = document.createElement('tr');
        table.append(tr);
        setTr(table, "#d7f4ff", "250px", "Repositories", "20px", "#000000", "https://github.com/QAQGaeBolg/histoire_analyzer");
        setTr(table, "#d7f4ff", "75px", "Stars", "20px", "#000000", null);
        setTr(table, "#d7f4ff", "75px", "Forks", "20px", "#000000", null);
        setTr(table, "#d7f4ff", "100px", "Date", "20px", "#000000", null);      
        setTr(table, "#d7f4ff", "100px", "Update", "20px", "#000000", null);  
      });
    }
  },
  mounted() {
    var audio = document.getElementById("audio1");
    document.onmousedown = () => {
      audio.play();
    };
    this.initialize();
  },
  update() {
      var div = document.getElementById('echart-example'); 
      div.style.width = `${window.innerWidth * 0.9}px`;
      div.style.height = `${window.innerHeight * 0.9}px`;
      div.style.marginLeft = `${window.innerWidth * 0.05}px`;
      div.style.position = "relative";
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
#echart-example {
  position: "absolute";
}
</style>
