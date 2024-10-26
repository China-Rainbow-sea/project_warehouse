# springboot_vue


# 在 springboot_vue 项目, 进行前端代码开发 

# 在 springboot_furn 项目, 进行后端代码开发

**在项目的 package.json 文件当中，进行如下的配置，可以
  //    --open 表示项目启动会自动打开浏览器，同时打开该项目
  //    "serve": "vue-cli-service serve --open"

** 在项目的 vue.config.js 文件当中配置，vue 的启动端口号(默认是8080)，防止冲突**
```
js
module.exports = {
  devServer:{
    port:10000 // 配置 vue 的启动端口，vue 默认是 8080端口，防止冲突这里我们改掉
  }
}

```
启动测试：端口修改成功
http://localhost:10000/


**我们在项目的src/assets/css/目录下创建一个名为“global.css” 作为一个全局样式，
之后，我们的全局样式都写在这个位置就行**
```
css

/*该 assets/css 是一个全局的样式，之后我们的全局样式可以写在这里*/
*{
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

```

> 注意我们引入的全局样式“global.css” 一定一定一定要写入到 src/main.js 文件当中进行一个导入，不然是无法使用上的
```
js
import '@/assets/css/global.css'  
```

**注意：我们安装了  element-plus 想要使用的话，我们同样需要将其导入到项目当中
，同样是在 src/main.js 文件当中进行一个引入: element-plus 
** 
```
js

// 引入我们导入的 element-plus 插件
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

// 两种方式，
createApp(App).use(store).use(router).use(ElementPlus).mount('#app')
// app.use(ElementPlus)

```

# 因为request.put/get/post...都是ajax异步请求
异步简单的说：
就是前端发送请求给了，后端。
但是后端还没有处理完，前端发送的数据，返回给前端。
前端自己就往下面继续执行了，并不会等到后端返回了数据后再执行的。
然后，等后端返回了数据之后，再次发送请求，重新开始就，返回再执行了后端前面提交给前端的数据。