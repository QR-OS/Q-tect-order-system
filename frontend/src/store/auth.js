import createPersistedState from "vuex-persistedstate";
import jwt from "jsonwebtoken";
import axios from "axios";

export default {
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
      axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
      context.commit("SET_USER", user);
      localStorage.setItem("user", JSON.stringify(user));
    },
    async logout(context) {
      delete axios.defaults.headers.common["Authorization"];
      context.commit("SET_ACCESS_TOKEN", null);
      localStorage.removeItem("accessToken");
      context.commit("SET_USER", null);
      localStorage.removeItem("user");
    },
  },
  plugins: [
    createPersistedState({
      paths: ["accessToken", "user"],
    }),
  ],
};
