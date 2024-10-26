// 引入 axios 包
// 提示：如果在启动前端项目，提示找不到 axios ，把光标放在 import asious form 'axios'的
// 'axios' 会有一个修复提示，导入 axios ，小伙伴点击，导入即可正常使用
import axios from "axios"
// 通过 axios 创建对象
const request = axios.create({
    timeout: 5000
})
// request  拦截器
// 1.  可以对请求做一些处理
// 2.  比如统一加 token，Content-Type  等
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
    return config
}, error => {
    return Promise.reject(error)
})


// response 拦截器
// 可以在调用接口响应后，统一的处理返回结果
// 执行过程：前端发送信息——>后端接受信息，返回给前端——>先给到 vue 的 response对数据进行
// 一个处理，然后再——>响应给浏览器展示。
request.interceptors.response.use(
    response => {
        // 这里的 response 就是后端返回的结果
        console.log("response", response)
        // 这里将 response 的 data 属性赋给了 res
        // 那么我们在请求方法中，得到结果就是 response.data
        let res = response.data; //取出返回 data 的内容,注意，这里我们多了个 data，所以返回的时候，就要
        // 少一个 data了
        // 如果是返回的文件,就返回
        if (response.config.responseType === 'blob') {
            return res
        }
        // 如果返回的是 string ,就转成 json 对象
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res
        }
        return res;
    },
    error => {
        console.log('err' + error) // 输出错误信息 return Promise.reject(error)
    }
)

//导出 request对象，在其它文件就可以使用
export default request


