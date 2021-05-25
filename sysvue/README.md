# blogvue

> A Vue.js project

## Build Setup

``` bash
# install dependencies
npm install

# serve with hot reload at localhost:8080
npm run dev

# build for production with minification
npm run build

# build for production and view the bundle analyzer report
npm run build --report
```

For a detailed explanation on how things work, check out the [guide](http://vuejs-templates.github.io/webpack/) and [docs for vue-loader](http://vuejs.github.io/vue-loader).


## 问题
刷新页面 报404
router  中
  // mode: "history",
  base: process.env.BASE_URL,
  routes
});
将模式改成#的

在请求头中
headers: {
                // Authorization: localStorage.getItem("token"),
              },
              将其注销掉
