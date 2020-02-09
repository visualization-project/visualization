<template>
  <div style="text-align:left;">
    <div>
      <el-form :inline="true" :model="formSearch" class="demo-form-inline">
        <el-form-item label="选择图册">
          <el-select v-model="formSearch.photo" placeholder="请选择" clearable>
            <el-option v-for="item in photoOptions" :key="item.id" :label="item.name" :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <!-- <el-form-item label="选择图表">
          <el-select v-model="formSearch.chart" placeholder="请选择" clearable>
            <el-option v-for="item in chartOptions" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item> -->
        <el-form-item>
          <el-button type="primary" @click="handleSubmit">确认</el-button>
          <el-button type="primary" @click="openDialog">新增/编辑</el-button>
          <el-button type="danger" @click="handleDelPhoto">删除</el-button>
          <el-button type="primary" @click="handlePreview">预览</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div>
      <grid-layout :layout.sync="layout" :col-num="12" :row-height="30" :is-draggable="true" :is-resizable="true" :is-mirrored="false" :vertical-compact="true" :margin="[5, 5]" :use-css-transforms="true">
        <grid-item
          v-for="item in layout"
          :x="item.x"
          :y="item.y"
          :w="item.w"
          :h="item.h"
          :i="item.i"
          :key="item.i"
          @moved="onMovedEvent"
          @resized="onResizedEvent">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span>{{item.i}}</span>
              <el-button style="float: right; padding: 3px 0" type="text">
                <i class="el-icon-edit"></i>
                <i class="el-icon-delete delete"></i>
              </el-button>
            </div>
            <div v-for="o in 4" :key="o" class="text item">
              {{item.i + o }}
            </div>
          </el-card>
        </grid-item>
      </grid-layout>
    </div>
    <el-dialog title="新增/编辑" :visible.sync="dialogAddPhoto" width="30%">
      <el-form :inline="true" :model="formPhoto" class="demo-form-inline">
        <el-form-item label="图表名称">
          <el-input v-model="formPhoto.name" placeholder="请输入" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleAdd">确定</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import VueGridLayout from 'vue-grid-layout'
//
import { apiChartBookAdd, apiChartBookList, apiChartBookDelete, apiChartBookUpdate } from '@/axios/api/photo.js'
// import { apiChartList } from '@/axios/api/chart.js'

export default {
  components: {
    GridLayout: VueGridLayout.GridLayout,
    GridItem: VueGridLayout.GridItem
  },
  data () {
    return {
      // 查询
      formSearch: {
        photo: ''
      },
      // 新增
      formPhoto: {
        name: '',
        modelData: ''
      },
      formFlag: false,
      updateData: {},
      photoOptions: [
        { label: '图册1', value: '1' },
        { label: '图册2', value: '2' },
        { label: '图册3', value: '3' }
      ],
      layout: [
        { 'x': 0, 'y': 0, 'w': 2, 'h': 2, 'i': '0' },
        { 'x': 2, 'y': 0, 'w': 2, 'h': 4, 'i': '1' },
        { 'x': 4, 'y': 0, 'w': 2, 'h': 5, 'i': '2' }
      ],
      i: 0,
      dialogAddPhoto: false
    }
  },
  created () {
  },
  mounted () {
    this.getPhotoList()
  },
  methods: {
    // 打开弹窗
    openDialog () {
      if (this.formSearch.photo) {
        this.formFlag = true
        this.updateData = this.photoOptions.filter(item => item.id === this.formSearch.photo)[0]
        this.formPhoto.name = this.updateData.name
      } else {
        this.formFlag = false
      }
      this.dialogAddPhoto = true
    },
    // 图表
    handleSubmit () {
      this.i += 1
      this.layout.push({ 'x': 4 + this.i, 'y': 0, 'w': 2, 'h': 5, 'i': '5' + this.i })
      this.$message({ message: '添加成功!', type: 'success' })
    },
    // 获取图册列表
    getPhotoList () {
      apiChartBookList().then(res => {
        this.photoOptions = res.result
      })
    },
    // 新建/编辑图册
    handleAdd () {
      if (this.formPhoto.name === '') {
        this.$message({ message: '请输入名称!', type: 'error' })
        return
      }
      if (this.formFlag) {
        this.updateData.name = this.formPhoto.name
        apiChartBookUpdate(this.updateData).then(res => {
          this.$message({ message: '修改成功!', type: 'success' })
        })
      } else {
        apiChartBookAdd(this.formPhoto).then(res => {
          this.$message({ message: '添加成功!', type: 'success' })
        })
      }
      this.dialogAddPhoto = false
    },
    // 删除
    handleDelPhoto () {
      if (!this.formSearch.photo) {
        this.$message({ type: 'warning', message: '请选择图册!' })
        return
      }
      this.$confirm('此操作将永久删除该图册, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        center: true
      }).then(() => {
        apiChartBookDelete(this.formSearch.photo).then(res => {
          this.$message({ type: 'success', message: '删除成功!' })
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    // 预览
    handlePreview () {
      let routeData = this.$router.resolve({
        name: 'preview',
        query: { photo: this.formSearch.photo }
      })
      window.open(routeData.href, '_blank')
    },
    // movedEvent 移动后的事件
    onMovedEvent (i, newX, newY) {
      console.log('MOVED i=' + i + ', X=' + newX + ', Y=' + newY)
    },
    // resizedEvent 调整大小后的事件
    onResizedEvent (i, newH, newW, newHPx, newWPx) {
      console.log('RESIZED i=' + i + ', H=' + newH + ', W=' + newW + ', H(px)=' + newHPx + ', W(px)=' + newWPx)
    }
  }
}
</script>

<style lang="scss" scoped>
  .delete {
    padding: 0 3px;
    color: red;
  }
</style>
