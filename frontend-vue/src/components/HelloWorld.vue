<template>
  <div id = "echart-example"></div>
</template>

<script>
import * as echarts from 'echarts'

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
      div.style.position = "relative";
      var columnBotton = document.createElement('button');
      div.appendChild(columnBotton);
      columnBotton.style.backgroundImage = "url('Resources/column_chart.png')";
      columnBotton.style.backgroundSize = "64px 64px";
      columnBotton.style.position = "relative";
      columnBotton.style.top = `${div.style.height * 0.1}px`;
      columnBotton.style.left = `${div.style.width * 0.1}px`;
      columnBotton.style.width = "64px";
      columnBotton.style.height = "64px";
      columnBotton.style.border = "none";
      //columnBotton.textContent = "column chart";
      columnBotton.onclick = this.onclick;
    },
    onclick() {
      console.log("click");
      var dialog = document.createElement('dialog');
      dialog.id = "dialog";
      dialog.style.width = "640px";
      dialog.style.height = "640px";
      var div = document.getElementById('echart-example');
      div.appendChild(dialog);
      dialog.onclose = this.onclose;
    },
    onclose() {
      var div = document.getElementById('echart-example');
      var dialog = document.getElementById('dialog');
      div.removeChild(dialog);
    },
    onClickColumnButton() {
      var echart_example = document.getElementById("echart-example");
      var echart1 = document.createElement('div');
      echart_example.appendChild(echart1);
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
    div.style.width = `90%`;
    div.style.height = `90%`;
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
</style>
