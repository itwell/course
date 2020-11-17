import Vue from 'vue'
import App from './app.vue'
import router from './router'
import axios  from 'axios'

Vue.config.productionTip = false;
Vue.prototype.$ajax = axios;


/**
 * axios拦截器
 */
axios.interceptors.request.use(function (config) {
    console.log("请求：", config);
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




new Vue({
    router,
    render: h => h(App),
}).$mount('#app');

console.log("环境：", process.env.NODE_ENV);