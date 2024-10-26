const {defineConfig} = require('@vue/cli-service')
module.exports = defineConfig({
    transpileDependencies: true

})


module.exports = {
    devServer: {
        port: 10000, // 配置 vue 的启动端口，vue 默认是 8080端口，防止冲突这里我们改掉
        proxy: { // 设置代理，必须填
            "/api": { // 设置拦截器 拦截器格式 斜杠+拦截器名字，名字可以自己定义
                target: 'http://localhost:9090', // 代理的目标地址，就是/api 代替 http:localhost:9090
                changeOrigin: true, // 是否设置同源，设置的是: true,实现跨域
                pathRewrite: {  // 路径重写
                    'api': '' // 选择忽略拦截器里面的单词
                }
            }
        }
        /*
        特别说明:
         1. 一定要确定 request.post("api/save") 被代理后的 url ，是项目后台服务对应提供的 API 接口 url,否则报404
         2. 当跨域执行时请求，浏览器还是提示: http://localhost:10000/api/xxx,所以不要认为是 api 没有替换你的配置。

        * */
    }

}

