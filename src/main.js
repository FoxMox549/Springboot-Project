import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import locale from 'element-ui/lib/locale/lang/en'
import 'element-ui/lib/theme-chalk/index.css';
import './assets/global.css';
import axios from 'axios';
import VueRouter from 'vue-router';
import router from './router';

Vue.prototype.$axios = axios;
Vue.prototype.$httpUrl = 'http://localhost:8090'
Vue.config.productionTip = false
//Vue.use(ElementUI);
Vue.use(ElementUI, {locale, size:'medium'});
Vue.use(VueRouter);

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')