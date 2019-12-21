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
        <li v-for="item in 10" :key="item">
          <el-tag class="li-tag">字段1</el-tag>
        </li>
      </ul>
    </el-aside>
    <el-container class="content">
      <el-header style="height: 100px;">
        <ul>
          <li class="list">维度</li>
          <li>
            <el-tag>字段1</el-tag>
          </li>
          <li class="list">数值</li>
          <li>
            <el-tag>字段2</el-tag>
          </li>
        </ul>
      </el-header>
      <el-main style="height: 400px;">
        <Content :json="json"></Content>
      </el-main>
    </el-container>
    <el-aside width="100px" class="right">
      <ul>
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
import BaseCharts from '@/libs/charts.js'
import vueJsonEditor from 'vue-json-editor'
import { mapMutations } from 'vuex'
import { SET_CHARTS_JSON } from '@/store/mutation-types.js'

// 基础图表类型
const chartType = ['line', 'bar', 'pie']

export default {
  components: { Content, vueJsonEditor },
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
      editJson: {} // 编辑时显示
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
      for (let i = 0; i < chartType.length; i++) {
        let chart = document.getElementById(chartType[i])
        chart.style.backgroundColor = '#fff'
      }
      const chart = document.getElementById(charts)
      chart.style.backgroundColor = '#ccc'
      this.chart = charts
      this.json = BaseCharts[this.chart]
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
      // console.log('value:', value)
    },
    onError (value) {
      console.log('value:', value)
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
      margin: 5px 0;
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
