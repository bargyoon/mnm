import Vue from 'vue'
import {BootstrapVue, IconsPlugin} from 'bootstrap-vue'
//index.html에 테마 css로 대체
//import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap-vue/dist/bootstrap-vue.css"
import './assets/css/common/style.css';
// import './assets/css/font/style.css';
import './assets/css/common/styles.css';
import App from './App.vue';
import router from './router/';
import store from './store/';


Vue.use(BootstrapVue);
Vue.use(IconsPlugin);


Vue.config.productionTip = false

new Vue({
    router,
    store,
    render: h => h(App),
}).$mount('#app')
