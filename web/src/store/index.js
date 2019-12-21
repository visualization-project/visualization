import Vue from 'vue'
import Vuex from 'vuex'
import { SET_CHARTS_JSON } from './mutation-types'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    chartsJson: {}
  },
  getters: {
    getChartsJson: (state) => {
      return state.chartsJson
    }
  },
  mutations: {
    // 设置图表属性
    [SET_CHARTS_JSON] (state, json) {
      console.log(json)
      state.chartsJson = json
    }
  },
  actions: {
  },
  modules: {
  }
})
