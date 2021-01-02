import Vue from 'vue'
import App from './app.vue'
import router from './router'
import axios  from 'axios'
import filter from './filter/filter'

Vue.config.productionTip = false;
Vue.prototype.$ajax = axios;

// 解决每次ajax请求，对应的sessionId不一致的问题
axios.defaults.withCredentials = true;

/**
 * axios拦截器
 */
axios.interceptors.request.use(function (config) {
    console.log("请求：", config);
    let token = Tool.getLoginUser().token;
    if (Tool.isNotEmpty(token)) {
        config.headers.token = token;
        console.log("请求headers增加token:", token);
    }
    return config;
}, error => {
    console.log(error)
});
axios.interceptors.response.use(function (response) {
    console.log("返回结果：", response);
    return response;
}, error => {
    console.log("请求之后出现的错误=========");
    console.log(error);
    console.log("请求之后出现的错误=========");
});

/**
 * 全局过滤器
 */
Object.keys(filter).forEach(key => {
    Vue.filter(key, filter[key])
});

// 路由登录拦截
// from跳转之前 跳转之后
router.beforeEach((to, from, next) => {
    // 要不要对meta.loginRequire属性做监控拦截
    if (to.matched.some(function (item) {
        return item.meta.loginRequire
    })) {
        let loginUser = Tool.getLoginUser();
        if (Tool.isEmpty(loginUser)) {
            next();
        } else {
            next();
        }
    } else {
        next();
    }
});

new Vue({
    router,
    render: h => h(App),
}).$mount('#app');

console.log("环境：", process.env.NODE_ENV);