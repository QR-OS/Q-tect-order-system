import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";
import jwt from "jsonwebtoken";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    accessToken: "",
    user: {},
  },
  mutations: {
    SET_ACCESS_TOKEN(state, token) {
      state.accessToken = token;
    },
    SET_USER(state, user) {
      state.user = user;
    },
    SET_EDIT_TOKEN(state, token) {
      state.editToken = token;
    },
  },
  actions: {
    login(context, token) {
      const user = jwt.decode(token);
      context.commit("SET_ACCESS_TOKEN", token);
      localStorage.setItem("accessToken", token);
      context.commit("SET_USER", user);
      localStorage.setItem("user", JSON.stringify(user));
    },
    async logout(context) {
      context.commit("SET_ACCESS_TOKEN", null);
      localStorage.removeItem("accessToken");
      localStorage.removeItem("user");
    },
  },
  modules: {},
  plugins: [
    createPersistedState({
      paths: ["accessToken"],
    }),
  ],
});
