import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import About from "../views/About.vue";
import Login from "../views/Login.vue";
import Register from "../views/Register.vue";
import SelectRegisterType from "../views/SelectRegisterType.vue";
import Product from "../components/manager/Product";

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
    path: "/product",
    name: "Product",
    component: Product
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
