<template>
  <el-container>
    <el-aside width="200px">
      <p class="data-list">数据库表</p>
      <el-select v-model="region" placeholder="请选择活动区域">
        <el-option label="表一" value="shanghai"></el-option>
        <el-option label="表二" value="beijing"></el-option>
      </el-select>
      <p class="data-list">字段名</p>
      <ul>
        <draggable class="list-group" :list="list" group="field" @change="log">
          <li v-for="item in list" :key="item.name">
            <el-tag class="li-tag">{{ item.name }}</el-tag>
          </li>
        </draggable>
      </ul>
    </el-aside>
    <el-container class="content">
      <el-header style="height: 100px;">
        <ul>
          <li class="list">维度</li>
          <li class="list-item">
            <draggable class="list-group" :list="list1" group="field" @change="log">
              <el-tag class="tag" v-for="(item, index) in list1" :key="index">{{ item.name }}</el-tag>
            </draggable>
          </li>
          <li class="list">数值</li>
          <li class="list-item">
            <draggable class="list-group" :list="list2" group="field" @change="log">
              <el-tag class="tag" v-for="(item, index) in list2" :key="index">{{ item.name }}</el-tag>
            </draggable>
          </li>
        </ul>
      </el-header>
      <el-main style="height: 400px;margin-top: 20px;">
        <Table v-if="chart === 'table'" :tableHead="json" :tableData="tableData"></Table>
        <Content v-if="chart !== 'table'" :json="json"></Content>
      </el-main>
    </el-container>
    <el-aside width="100px" class="right">
      <ul>
        <li @click="handleChart('table')" class="li" id="table">
          <div class="iconfont iconbiaoge cursor"></div>
        </li>
        <li @click="handleChart('line')" class="li" id="line">
          <div class="iconfont iconzhexiantu cursor"></div>
        </li>
        <li @click="handleChart('bar')" class="li" id="bar">
          <div class="iconfont iconzhuxingtu cursor"></div>
        </li>
        <li @click="handleChart('pie')" class="li" id="pie">
          <div class="iconfont iconzu cursor"></div>
        </li>
        <li>
          <el-button type="primary" @click="handleDialog">属性配置</el-button>
        </li>
      </ul>
    </el-aside>
    <el-dialog title="echarts属性配置" :visible.sync="dialog" width="40%" center @click="handleDialog">
      <vue-json-editor id="vueJsonEditor" v-model="editJson" lang="zh" :show-btns="true" :exapndedOnStart="true" @json-save="onJsonSave" @has-error="onError"></vue-json-editor>
    </el-dialog>
  </el-container>
</template>

<script>
// vue-grid-layout 和 vuedraggable 拖拽组件
import Content from './component/content'
import Table from './component/table'
import BaseCharts from '@/libs/charts.js'
import vueJsonEditor from 'vue-json-editor'
import { mapMutations } from 'vuex'
import { SET_CHARTS_JSON } from '@/store/mutation-types.js'
import draggable from 'vuedraggable'

// 基础图表类型
const chartType = ['line', 'bar', 'pie', 'table']

export default {
  components: { Content, vueJsonEditor, draggable, Table },
  data () {
    return {
      region: '',
      dialog: false, // json 属性设置
      textarea: {},
      editor: '',
      chart: '', // 当前图表对象
      json: {
        msg: 'demo of jsoneditor'
      },
      editJson: {}, // 编辑时显示
      list: [
        { name: '字段1' },
        { name: '字段2' },
        { name: '字段3' },
        { name: '字段4' },
        { name: '字段5' }
      ],
      list1: [],
      list2: [],
      // 表格数据
      tableData: [{
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1517 弄'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1519 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄'
      }]
    }
  },
  watch: {
  },
  mounted () {
  },
  methods: {
    // 映射 Mutations
    ...mapMutations([
      SET_CHARTS_JSON
    ]),
    // 选中当前图表高亮
    handleChart (charts) {
      this.$forceUpdate()
      for (let i = 0; i < chartType.length; i++) {
        let chart = document.getElementById(chartType[i])
        chart.style.backgroundColor = '#fff'
      }
      const chart = document.getElementById(charts)
      chart.style.backgroundColor = '#ccc'
      this.chart = charts
      // this.$set(this.json, BaseCharts[this.chart])
      this.json = JSON.parse(JSON.stringify(BaseCharts[this.chart]))
    },
    // 打开弹窗
    handleDialog () {
      this.dialog = true
      this.editJson = BaseCharts[this.chart]
    },
    // echarts 属性保存
    onJsonSave (value) {
      // sessionStorage.setItem('chartsJson', JSON.stringify(value))
      this.SET_CHARTS_JSON(JSON.parse(JSON.stringify(value)))
      this.editJson = value
      this.dialog = false
    },
    onError (value) {
      console.log('value:', value)
    },
    // 拖拽字段
    log (evt) {
      console.log(this.list1, this.list2)
    }
  }
}
</script>

<style scoped lang="scss">
  .data-list{
    padding: 10px 0;
  }
  .li-tag{
    display: block;
    width: 100%;
    height: 100%;
    cursor: pointer;
  }
  .content{
    text-align: left;
    .list {
      margin: 2px 0;
      height: 20px;
    }
    .list-item {
      height: 32px;
      border: .1px solid #ccc;
      .tag {
        margin-right: 10px;
      }
    }
  }
  .right {
    > ul {
      > li {
        height: 50px;
        > div {
          height: 100%;
          line-height: 50px;
        }
      }
    }
  }
</style>
