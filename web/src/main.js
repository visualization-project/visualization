import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './style/reset.css'
import './style/index.scss'
import './assets/iconfont/iconfont.css'
// 格式化 json 工具
import 'jsoneditor/dist/jsoneditor.min.css'

Vue.use(Element)
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
