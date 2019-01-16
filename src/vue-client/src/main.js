// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import ElementUI from 'element-ui'
import axios from 'axios'
import qs from 'qs'
import 'element-ui/lib/theme-chalk/index.css'
import App from './App'
import router from './router'
//elemenetui使用
Vue.use(ElementUI)
//使用axios向后台发送数据
Vue.use(axios)
//配置后台路径
axios.defaults.baseURL = 'localhost:9000/';
axios.defaults.headers['Content-Type'] = 'application/x-www-form-urlencoded'
Vue.prototype.$http = axios;
//使用方法 Qs.stringify(data);
// Qs.parse(data)
Vue.prototype.$qs = qs;
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
