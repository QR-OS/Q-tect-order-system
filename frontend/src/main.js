import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import axios from "axios";
import LoadScript from "vue-plugin-load-script";

Vue.use(LoadScript);

Vue.config.productionTip = false;
axios.defaults.baseURL = "/api";
axios.defaults.headers.common["Cache-Control"] = "no-cache";
new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount("#app");
