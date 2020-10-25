import jwt from "jsonwebtoken";
import axios from "axios";

export default {
  namespaced: true,
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
    async login(context, token) {
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

    restore(context) {
      try {
        const accessToken = localStorage.getItem("accessToken");
        let user = localStorage.getItem("user");
        if (!accessToken || !user) {
          return false;
        }
        user = JSON.parse(user);
        axios.defaults.headers.common[
          "Authorization"
        ] = `Bearer ${accessToken}`;
        context.commit("SET_ACCESS_TOKEN", accessToken);
        context.commit("SET_USER", user);
      } catch (err) {
        context.dispatch("logout");
        return false;
      }
    },
  },

  getters: {
    isLoggedIn(state) {
      return !!state.accessToken;
    },
  },
};
