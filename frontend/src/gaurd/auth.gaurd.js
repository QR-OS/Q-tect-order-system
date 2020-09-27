import store from "../store/index";

export const requireAuth = () => (to, from, next) => {
  if (store.state.auth.accessToken) {
    return next();
  }
  next("/login");
};

export const shouldNotBeAuthorized = () => (to, from, next) => {
  if (!store.state.auth.accessToken) {
    return next();
  }
  next("/");
};
