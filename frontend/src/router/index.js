import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import About from "../views/About.vue";
import Login from "../components/Login.vue";
import Register from "../components/Register.vue";
import SelectRegisterType from "../components/SelectRegisterType.vue";
import StoreInfo from "../views/store/StoreInfo.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home
  },
  {
    path: "/about",
    name: "About",
    component: About
  },
  {
    path: "/login",
    name: "Login",
    component: Login
  },
  {
    path: "/register",
    name: "Register",
    component: Register,
    props: true
  },
  {
    path: "/selectregistertype",
    name: "SelectRegisterType",
    component: SelectRegisterType
  },
  {
    path: "/store",
    name: "StoreInfo",
    component: StoreInfo
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
