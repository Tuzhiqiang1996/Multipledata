import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);
const routes = [
  {
    path: "/Login",
    name: "Login",
    component: () => import(/* webpackChunkName: "Login" */ "@/view/Login.vue")
  },
  {
    path: "/home",
    name: "home",
    component: () => import(/* webpackChunkName: "home" */ "@/page/index.vue"),
    meta: {
      requireAuth: true
    }
  },
  {
    path: "/",
    name: "list",
    component: () => import(/* webpackChunkName: "home" */ "@/page/orderlisttwo.vue"),
    meta: {
      requireAuth: true
    }
  },
  {
    path: "*",
    redirect: "/404"
  },
  {
    path: "/404",
    name: "404",
    component: () => import(/* webpackChunkName: "404 */ "@/view/notFound.vue")
  }
];
const router = new Router({
  // mode: "history",
  base: process.env.BASE_URL,
  routes
});

//避免重复跳转产生问题
const VueRouterPush = Router.prototype.push;
Router.prototype.push = function push(to) {
  return VueRouterPush.call(this, to).catch(err => err);
};
// router.beforeEach((to, from, next) => {
//   if (to.meta.requireAuth) {
//     // 判断该路由是否需要登录权限

//     if (localStorage.getItem("token") && sessionStorage.getItem("userInfo")) {
//       // 通过vuex state获取当前的token/name是否存在
//       next();
//     } else {
//       next({
//         path: "/",
//         query: { redirect: to.fullPath } // 将跳转的路由path作为参数，登录成功后跳转到该路由
//       });
//     }
//   } else {
//     next();
//   }
// });
export default router;
