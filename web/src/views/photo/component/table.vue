<template>
  <el-table :data="tableData" style="width: 100%">
    <el-table-column
      v-for="(item, index) in tableHead"
      :key="index"
      :prop="item.prop"
      :label="item.label"
      :width="item.width">
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  props: {
    tableHead: {
      type: Array,
      default: () => []
    },
    tableData: {
      type: Array,
      default: () => []
    }
  },
  data () {
    return {}
  },
  watch: {
    // getChartsJson: {
    //   handler (val) {
    //     this.getCharts(val)
    //   },
    //   deep: true
    // }
  },
  computed: {
    // ...mapGetters([
    //   'getChartsJson'
    // ])
  }
}
</script>

<style>
</style>
