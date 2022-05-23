<template>
  <div id = "echart-example">
    <dialog open id = "dialog-histoire"></dialog>
  </div>
</template>

<script>
import * as echarts from 'echarts';
import axios from 'axios';

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
        "dynamic-column", 
        "Resources/dynamic_column.png", 
        `${div.clientWidth * 0.05}px`,
        `${div.clientHeight * 0.25 + 256}px`,
        this.onClickColumnButton
      );
      this.createButton(
        div, 
        "data-table", 
        "Resources/data_table.png", 
        `${div.clientWidth * 0.1 + 64}px`,
        `${div.clientHeight * 0.05}px`,
        this.onClickColumnButton
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
    createDialog(parent, sid, left, top) {
      var dialog = document.createElement('dialog');
      parent.appendChild(dialog);
      var header = document.createElement('div');
      dialog.appendChild(header);
      header.style.position = "absolute";
      header.style.left = "-0.5px";
      header.style.top = "-1px";
      header.style.width = "640.5px";
      header.style.height = "20px";
      header.style.backgroundImage = "url(Resources/dialog_header.png)";
      this.setMovement(header, dialog);

      dialog.id = sid;
      dialog.style.width = "640px";
      dialog.style.height = "400px";
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
        "column-dialog",
        `${echart_example.clientWidth * 0.1}px`,
        `${echart_example.clientHeight * 0.1}px`
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
        `${echart_example.clientHeight * 0.1}px`
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
        `${echart_example.clientHeight * 0.1}px`
      );
      var dialog = document.getElementById("word-cloud");

      var echart = document.createElement('div');
      echart.style.position = "absolute";
      echart.style.width = "600px"
      echart.style.height = "300px";
      echart.style.left = "5%";
      echart.style.bottom = "0px";
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
