<template>
  <div style="height:100%;width:100%;" id="charts"></div>
</template>

<script>
import Echarts from 'echarts'
import { mapGetters } from 'vuex'
export default {
  props: {
    json: {
      type: Object,
      default: () => {}
    }
  },
  data () {
    return {
      charts: ''
    }
  },
  watch: {
    json: {
      handler (val) {
        this.json = val
        this.getCharts(val)
      },
      deep: true
    },
    getChartsJson: {
      handler (val) {
        this.getCharts(val)
      },
      deep: true
    }
  },
  computed: {
    ...mapGetters([
      'getChartsJson'
    ])
  },
  mounted () {
  },
  methods: {
    getCharts (json = {}) {
      this.charts = Echarts.init(document.getElementById('charts'))
      if (Object.getOwnPropertyNames(json) !== 0) {
        this.charts.clear()
        this.charts.setOption(json)
      }
    }
  }
}
</script>

<style>
</style>
