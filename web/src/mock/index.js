const Mock = require('mockjs')
const util = require('./util')

Mock.mock('/chart-book/list', (res, req) => {
  const list = util.getJsonFile('../json/photoList.json')
  return list
})
