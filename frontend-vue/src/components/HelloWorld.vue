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
  methods: {
    initialize() {
      var div = document.getElementById('echart-example'); 
      div.id = 'echart-example';
      div.style.backgroundImage = "url('Resources/background3.png')";
      div.style.width = `${window.innerWidth * 0.9}px`;
      div.style.height = `${window.innerHeight * 0.9}px`;
      div.style.marginLeft = `${window.innerWidth * 0.05}px`;
      var columnButton = document.createElement('button');
      div.appendChild(columnButton);
      columnButton.id = "column-button";
      columnButton.style.backgroundImage = "url('Resources/column_chart.png')";
      columnButton.style.backgroundSize = "64px 64px";
      columnButton.style.backgroundColor = "transparent";
      columnButton.style.position = "absolute";
      columnButton.style.left = `${div.clientWidth * 0.1}px`;
      columnButton.style.top = `${div.clientHeight * 0.1}px`;
      columnButton.style.width = "64px";
      columnButton.style.height = "64px";
      columnButton.style.border = "none";
      columnButton.onclick = this.onClickColumnButton;
      var dialog_histoire = document.getElementById("dialog-histoire");
      dialog_histoire.style.width = "512px";
      dialog_histoire.style.height = "256px";
      dialog_histoire.style.position = "relative";
      dialog_histoire.style.marginLeft = "10px";
      dialog_histoire.style.marginBottom = "10px";
      dialog_histoire.style.padding = "0";
      dialog_histoire.style.border = "none";
    },
    onclose() {
      var div = document.getElementById('echart-example');
      var dialog = document.getElementById('dialog');
      div.removeChild(dialog);
    },
    onClickColumnButton() {
      console.log("click");
      axios.get("http://116.7.234.239:8080/data/test")
      .then(response => (
        console.log(`$$$$$$$\n${response.data}`)
      ))
      .catch(function (error) { // 请求失败处理
        console.log(error);
      })
      var echart_example = document.getElementById('echart-example');
      var dialog = document.createElement('dialog');
      dialog.id = "column-dialog";
      dialog.style.width = "640px";
      dialog.style.height = "320px";
      dialog.style.position = "absulote";
      dialog.style.left = `${echart_example.clientWidth * 0.1}px`;
      dialog.style.top = `${echart_example.clientHeight * 0.1}px`;
      echart_example.appendChild(dialog);
      dialog.onclose = this.onclose;
      dialog.open = true;
      var echart1 = document.createElement('div');
      echart1.style.position = "absolute";
      echart1.style.width = "600px"
      echart1.style.height = "300px";
      //echart1.style.top = "50%";
      //echart1.style.marginTop = `-${150}px`;
      echart1.style.left = "50%";
      echart1.style.marginLeft = `-${300}px`;
      //echart1.style.marginBottom = "20%";
      echart1.style.bottom = "1%";
      dialog.appendChild(echart1);
      var myChart = echarts.init(echart1);
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
    }
  },
  mounted() {
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
