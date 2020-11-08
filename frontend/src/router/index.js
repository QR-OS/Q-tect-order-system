import {
  requireAuth,
  shouldNotBeAuthorized,
  enterOrder,
} from "../gaurd/auth.gaurd.js";
import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import About from "../views/About.vue";
import Login from "../views/login/Login.vue";
import Register from "../views/login/Register.vue";
import SelectRegisterType from "../views/login/SelectRegisterType.vue";
import StoreInfo from "../views/store/StoreInfo.vue";
import FindIdPw from "../views/login/FindIdPw.vue";
import MyPage from "../views/mypage/MyPage.vue";
import CategoryStoreList from "../views/store/CategoryStoreList.vue";
import CheckPassword from "../views/mypage/CheckPassword.vue";
import Product from "../components/manager/Product.vue";
import SearchStoreList from "../views/store/SearchStoreList.vue";
import StoreMain from "../views/store/StoreMain.vue";
import Bucket from "../views/order/Bucket.vue";
import Order from "../views/order/Order.vue";
import OrderState from "../views/order/OrderState.vue";
import OrderHistory from "../views/order/OrderHistory.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/about",
    name: "About",
    component: About,
  },
  {
    path: "/login",
    name: "Login",
    component: Login,
    beforeEnter: shouldNotBeAuthorized(),
  },
  {
    path: "/register",
    name: "Register",
    component: Register,
    beforeEnter: shouldNotBeAuthorized(),
  },
  {
    path: "/selectregistertype",
    name: "SelectRegisterType",
    component: SelectRegisterType,
    beforeEnter: shouldNotBeAuthorized(),
  },
  {
    path: "/product",
    name: "Product",
    component: Product,
  },
  {
    path: "/findidpw",
    name: "FindIdPw",
    component: FindIdPw,
    beforeEnter: shouldNotBeAuthorized(),
  },
  {
    path: "/checkpw",
    name: "CheckPw",
    component: CheckPassword,
    beforeEnter: requireAuth(),
  },
  {
    path: "/mypage",
    name: "MyPage",
    component: MyPage,
    beforeEnter: requireAuth(),
  },
  {
    path: "/store",
    name: "StoreInfo",
    component: StoreInfo,
  },
  {
    path: "/categorystorelist",
    name: "CategoryStoreList",
    component: CategoryStoreList,
    props: true,
  },
  {
    path: "/searchstorelist",
    name: "SearchStoreList",
    component: SearchStoreList,
    props: true,
  },
  {
    path: "/storemain",
    name: "StoreMain",
    component: StoreMain,
    beforeEnter: requireAuth(),
  },
  {
    path: "/bucket",
    name: "Bucket",
    component: Bucket,
    beforeEnter: requireAuth(),
  },
  {
    path: "/order",
    name: "Order",
    component: Order,
    beforeEnter: enterOrder(),
  },
  {
    path: "/orderstate",
    name: "OrderState",
    component: OrderState,
    beforeEnter: requireAuth(),
  },
  {
    path: "/orderhistory",
    name: "OrderHistory",
    component: OrderHistory,
    beforeEnter: requireAuth(),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
