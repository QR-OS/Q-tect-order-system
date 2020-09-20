import store from "../store";

export const requireAuth = () => (to, from, next) => {
  if (store.state.accessToken) {
    return next();
  }
  next("/login");
};

export const shouldNotBeAuthorized = () => (to, from, next) => {
  if (!store.state.accessToken) {
    return next();
  }
  next("/");
};
