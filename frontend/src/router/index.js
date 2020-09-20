import { requireAuth, shouldNotBeAuthorized } from "../gaurd/auth.gaurd.js";
import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import About from "../views/About.vue";
import Login from "../views/Login.vue";
import Register from "../views/Register.vue";
import SelectRegisterType from "../views/SelectRegisterType.vue";
import FindIdPw from "../views/FindIdPw.vue";
import MyPage from "../views/MyPage.vue";

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
    component: Login,
    beforeEnter: shouldNotBeAuthorized()
  },
  {
    path: "/register",
    name: "Register",
    component: Register,
    props: true,
    beforeEnter: shouldNotBeAuthorized()
  },
  {
    path: "/selectregistertype",
    name: "SelectRegisterType",
    component: SelectRegisterType,
    beforeEnter: shouldNotBeAuthorized()
  },
  {
    path: "/findidpw",
    name: "FindIdPw",
    component: FindIdPw,
    beforeEnter: shouldNotBeAuthorized()
  },
  {
    path: "/mypage",
    name: "MyPage",
    component: MyPage,
    beforeEnter: requireAuth()
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
