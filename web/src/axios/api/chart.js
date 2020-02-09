import { post, get } from '../index'

// 列表
const apiChartList = () => get('/chart-instance/list')

export default {
  apiChartList
}
