const HtmlWebpackExternalsPlugin = require("html-webpack-externals-plugin");

module.exports = {
  configureWebpack: {
    plugins: [
      new HtmlWebpackExternalsPlugin({
        externals: [
          {
            module: "daum-postcode-api",
            entry: "http://dmaps.daum.net/map_js_init/postcode.v2.js",
            global: "daum-postcode-api"
          }
        ]
      })
    ]
  },
  transpileDependencies: ["vuetify"],
  publicPath: "/",
  outputDir: "../backend/public/",
  devServer: {
    proxy: {
      "/api": {
        target: "http://localhost:3000",
        changeOrigin: true
      }
    }
  }
};
