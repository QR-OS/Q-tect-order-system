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

export const enterOrder = () => (to, from, next) => {
  if (store.state.auth.accessToken && from.name == "Bucket") {
    return next();
  }
  next("/");
};
