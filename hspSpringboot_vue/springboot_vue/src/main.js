import {createApp} from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import '@/assets/css/global.css'

import 'element-plus/dist/index.css'
import '@/assets/css/global.css'


// 引入我们导入的 element-plus 插件
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import zhCn from 'element-plus/es/locale/lang/zh-cn'

// 两种方式，
createApp(App).use(store).use(router).use(ElementPlus, {locale:zhCn}).mount('#app')
// app.use(ElementPlus)