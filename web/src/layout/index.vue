<template>
  <el-container>
    <el-aside width="200px">
      <el-menu
      :default-active="$route.path"
      router
      class="el-menu-vertical-demo">
      <el-menu-item index="/photo">
        <i class="el-icon-menu"></i>
        <span slot="title">图册页</span>
      </el-menu-item>
    </el-menu>
    </el-aside>
    <el-container>
      <el-header style="line-height:60px;">可视化后台管理系统</el-header>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  methods: {
  }
}
</script>

<style>
</style>
