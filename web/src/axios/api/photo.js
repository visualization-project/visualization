import { post, get } from '../index'

// 添加
const apiChartBookAdd = (params) => post('/chart-book/add', params)
// 列表
const apiChartBookList = (params) => get('/chart-book/list', params)
// 删除
const apiChartBookDelete = (params) => post('/chart-book/delete/' + params)
// 修改
const apiChartBookUpdate = (params) => post('/chart-book/update', params)

export {
  apiChartBookAdd,
  apiChartBookList,
  apiChartBookDelete,
  apiChartBookUpdate
}
