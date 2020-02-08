import { post, get } from '../index'

// 添加
const apiChartBookAdd = (params) => post('/chart-book/add', params)
// 列表
const apiChartBookList = (params) => get('/chart-book/list', params)

export {
  apiChartBookAdd,
  apiChartBookList
}
