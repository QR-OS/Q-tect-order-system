import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import axios from "axios";
import VueLogger from 'vuejs-logger';

Vue.config.productionTip = false;
axios.defaults.baseURL = "/api";
axios.defaults.headers.common["Cache-Control"] = "no-cache";
store.dispatch("auth/restore");

const options = {
  isEnabled : true,
  stringifyArguments : false,
  showLogLevel : true,
  showMethodName : true,
  separator : '|',
  showConsoleColors : true
};

Vue.use(VueLogger, options);

new Vue({
  router,
  store,
  vuetify,
  render: (h) => h(App),
}).$mount("#app");
