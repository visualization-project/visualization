module.exports = {
  css: {
    loaderOptions: { // 向 CSS 相关的 loader 传递选项
      less: {
        javascriptEnabled: true
      }
    }
  },
  // lintOnSave: false,
  devServer: {
    port: 8080,
    host: '0.0.0.0',
    hot: true,
    proxy: {
      '/api': {
        target: 'http://192.168.43.105:21001/', // 测试
        ws: true,
        changeOrigin: true,
        pathRewrite: {
          '^/api': ''
        }
      }
    }
  }
}
