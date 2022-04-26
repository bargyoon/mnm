
module.exports = {
  // presets: [
  //   '@vue/cli-plugin-babel/preset'
  // ]
  outputDir: "../src/main/resources/static",
  indexPath: "../static/index.html",
  devServer: {
    // proxy: {
    //   '/mnm': { target: 'http://localhost:9090', changeOrigin: true },
    // }
    proxy: 'http://localhost:9090',
  },
  chainWebpack: config => {
    const svgRule = config.module.rule("svg");
    svgRule.uses.clear();
    svgRule.use("vue-svg-loader").loader("vue-svg-loader");
  }
}
