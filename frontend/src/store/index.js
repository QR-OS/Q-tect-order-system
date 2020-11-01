import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

import auth from "./auth";
import bucket from "./bucket";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {},
  mutations: {},
  actions: {},
  modules: { auth, bucket },
  plugins: [
    createPersistedState({
      paths: ["auth", "bucket"],
    }),
  ],
});
