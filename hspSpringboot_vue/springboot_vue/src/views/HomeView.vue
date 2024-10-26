<template>
    <!-- 删除了    <div class="home"> 当中的 class="home" 属性上的赋值-->
    <!--    <div>-->
    <!--        <img alt="Vue logo" src="../assets/logo.png">-->
    <!--        <HelloWorld msg="Welcome to Your Vue.js App"/>-->
    <!--    </div>-->
    <div>

        <!--         新增，其它的按钮实现 -->
        <div style="margin:10px 0">
            <el-button type="primary" @click="add">新增</el-button>  <!-- primary 表示默认是显示/选中这个 按钮 -->
            <el-button>其它</el-button>
        </div>

        <!--        搜索按钮 -->
        <div style="margin: 10px 0">
            <!--              文本框当中显示的透明提示内容 -->
            <el-input v-model="search" placeholder=" 请 输 入 关 键 字 " style="width: 30%"></el-input>
            <el-button style="margin-left: 10px" type="primary" @click="list">查询</el-button>
        </div>


        <!--        <el-button>我的按钮  </el-button>-->
        <!--   el-table 表示的一个表格，显示的标题栏上的内容的操作，二级标题的内容 -->
        <el-table :data="tableData" stripe style="width: 90%">
            <el-table-column prop="id" label="ID" sortable></el-table-column>
            <el-table-column prop="name" label="家居名"></el-table-column>
            <el-table-column prop="maker" label="厂家"></el-table-column>
            <el-table-column prop="price" label="价格"></el-table-column>
            <el-table-column prop="sales " label="销量"></el-table-column>
            <el-table-column prop="stock" label="库存"></el-table-column>
            <el-table-column fixed="right" label="操作" width="100">
                <!-- //响应删除点击-->
                <template #default="scope">
                    <el-button size="mini" @click="handleEdit(scope.row)" type="text">编辑</el-button>
                    <!-- 增加 popcomfirm 控件，确认删除 -->
                    <el-popconfirm title="确定删除吗？" @confirm="handleDel(scope.row.id)">
                        <template #reference>
                            <el-button size="mini" type="text">删除</el-button>
                        </template>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>


        <!--        引入分页组件，可以根据自己的需要进行定制-->
        <div style="margin: 10px 0">
            <el-pagination
                    @size-change="handlePageSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="currentPage"
                    :page-sizes="[5,10,15]"
                    :page-size="pageSize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total">

            </el-pagination>

        </div>

        <!--  添加家居的弹窗
        说明:
        1.el-dialog: v-model="dialogVisible" 表示对话框，和 dialogVisible 变量双向绑定，
           控制是否显示对话框
        2. el-form :model="form" 表示表单数据和 form 数据变量双向绑定
        3. el-input v-model="form.name" 表示表单的 input 控件，名字为 name 需要和 后台 Javebean[Furn]属性一致才行
         这样后台才会进行数据的封装
        -->
        <el-dialog title="提示" v-model="dialogVisible" width="30%">
            <!--
                        1.指定将创建的规则 rules 应用到 form这个表单
                        2.并指定各个字段规则和哪个el-form-item关联，通过prop指定
                        3.一定要注意 prop 和 各个规则名对应，否则不生效
            -->
            <el-form :model="form" :rules="rules" ref="form" label-width="120px">
                <el-form-item label="家居名" prop="name">
                    <el-input v-model="form.name" style="width: 50%"></el-input>
                    <!--                    显示返回的后端校验信息 -vue的插入表达式-->
                    {{validMsg.name}}
                </el-form-item>
                <el-form-item label="厂商" prop="maker">
                    <el-input v-model="form.maker" style="width: 80%"></el-input>
                    {{this.validMsg.maker}}
                </el-form-item>
                <el-form-item label="价格" prop="price">
                    <el-input v-model="form.price" style="width: 80%"></el-input>
                    {{this.validMsg.price}}
                </el-form-item>
                <el-form-item label="销量" prop="sales">
                    <el-input v-model="form.sales" style="width: 80%"></el-input>
                    {{this.validMsg.sales}}
                </el-form-item>
                <el-form-item label="库存" prop="stock">
                    <el-input v-model="form.stock" style="width: 80%"></el-input>
                    {{this.validMsg.stock}}
                </el-form-item>
            </el-form>
            <template #footer>
           <span class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="save">确 定</el-button>
           </span>
            </template>
        </el-dialog>


    </div>
</template>

<script>
    // @ is an alias to /src
    // import HelloWorld from '@/components/HelloWorld.vue'

    // 引入 request 组件
    import request from "@/utils/request";

    export default {
        name: 'HomeView',
        components: {
            // HelloWorld

        },

        //  前端显示的测试的内容  数据
        data() {
            return {
                validMsg: {},// 关联我们的后端校验信息
                currentPage: 1, // 当前页
                pageSize: 5,  // 每页显示几条记录
                total: 10,  // 一共有多少条记录，会通过请求获取到表中对应的记录数
                form: {}, // 表单数据
                dialogVisible: false, // 控制对话框是否显示，默认 false(隐藏对话框,后面点击新增按钮,执行add方法,设置为 true 就显示了)
                search: "",
                tableData: [],
                // 添加前端，检错规则
                rules: { // 提交表单的验证规则
                    name: [
                        {required: true, message: '请输入称家居名', trigger: 'blur'}
                    ],
                    maker: [
                        {required: true, message: '请输入称制造商', trigger: 'blur'}
                    ],
                    price: [
                        {required: true, message: '请输入价格', trigger: 'blur'},
                        // 正则表达式
                        {pattern: /^(([1-9]\d*)|(0))(\.\d+)?$/, message: '请输入数字', trigger: 'blur'}
                    ],
                    sales: [
                        {required: true, message: '请输入销量', trigger: 'blur'}, {
                            pattern: /^(([1-9]\d*)|(0))$/,
                            message: '请输入数字',
                            trigger: 'blur'
                        }
                    ],
                    stock: [
                        {required: true, message: '请输入库存', trigger: 'blur'}, {
                            pattern: /^(([1-9]\d*)|(0))$/,
                            message: '请输入数字',
                            trigger: 'blur'
                        }
                    ]
                }
            }
        },

        created() { // 在次方法中，调用list(),完成表格数据的显示
            this.list();
        },

        methods: {

            // 编辑按钮上的操作触发的方法
            handleEdit() {

            },

            // add方法，显示添加的对话框
            add() {
                this.dialogVisible = true; // 显示对话框
                this.form = {} // 每次显示添加的对话框时，清空表单数据
                // 将上次表单验证的信息清空
                this.$refs['form'].resetFields()//将上传验证消息，清空
                this.validMsg = {}
            },
            save() {  // 添加 ，修改

                if (this.form.id) {  // id 存在就是修改
                    // 因为request.put/get/post...都是ajax异步请求

                    request.put("api/update", this.form).then(
                        res => {
                            if (res.code === "200") { // 修改成功
                                // 需求提示-成功的消息框
                                this.$message({
                                    type: "success",
                                    message: "更新成功"
                                })
                            } else { // 修改失败
                                // 需求提示-失败的消息框
                                this.$message(
                                    {
                                        type: "error",
                                        message: "更新失败" // 也可以使用 res.msg 来提示
                                    }
                                )
                            }

                            // 刷新家居列表
                            this.list()
                            // 不显示对话框
                            this.dialogVisible = false
                        }
                    )

                } else { // 执行添加
                    this.$refs['form'].validate((valid) => {
                        // 返回的是 true 说明验证通过了，可以新增
                        // "前端校验通过"
                        // 为了配合后端校验，这里我们先让前端校验通过，测试完毕后，再修改回来
                        valid = true
                        alert(valid)
                        if (valid) {
                            // api/save 真实对应的请求地址url 是 http://localhost:9090/save
                            request.post("api/save", this.form).then(
                                res => { // 是箭头函数-前端技术栈讲过
                                    if (res.code === "200") { // 表示前后端校验都通过了，可以保存，提交到数据库当中了
                                        // res 就是后端程序返回给前端的结果
                                        console.log("res=", res)
                                        this.dialogVisible = false;
                                        this.list() // 添加成功后，及时刷新列表
                                    } else if (res.code === "400") {  // 走到这里说明后端校验失败了，
                                        // 取出返回的校验信息
                                        console.log("validMsg=", validMsg)
                                        console.log("res.data.name=", res.data.name)
                                        this.validMsg.name = res.data.name
                                        this.validMsg.sales = res.data.sales
                                        this.validMsg.price = res.data.price
                                        this.validMsg.maker = res.data.maker
                                        this.validMsg.stock = res.data.stock
                                    }
                                }
                            )
                            /* 因为异步，所以不可以放在这个位置上进行。刷新判断
                              this.list()
                                 // 不显示对话框
                                 this.dialogVisible = false*/
                        } else {
                            this.$message({
                                type: "error",
                                message: "验证没有通过，不提交" // 也可以是 message:res.msg 来提示
                            })

                            console.log("validMsg=", validMsg)
                            console.log("res.data.name=", res.data.name)

                            return false;
                        }

                    })
                }


            },
            list() {  // 显示家居信息，后面考虑检索条件
                /*request.get("api/furns").then(res => {
                    // 将返回的数据和 tableData绑定
                    // this.tableData = res.
                    console.log("res=", res)
                    // 将返回的数据和tableData绑定，显示在浏览器上，
                    // 注意是两个 data.data 比较深
                    // 注意：这里我们在 utils/request.js 当中有了 let res = response.data; 一个data所以这里少一个
                    this.tableData = res.data
                })*/

                // 显示家居信息，考虑分页查询+条件查询
                // request.get("/api/furnBySearchPage", {
                // 分页查询+条件查询，优化2
                request.get("/api/furnBySearchPage2", {
                    params: {
                        pageNum: this.currentPage,
                        pageSize: this.pageSize,
                        search: this.search // 模糊查询
                    }
                }).then(res => {
                    console.log("res=", res)
                    // 将返回的数据和 tableData绑定
                    this.tableData = res.data.records
                    // 修改 total
                    this.total = res.data.total
                })
            },


            //  新写一个方法handLeEdit2(row)
            // 1. 通过row.id 发出请求Get
            // 2. 返回对应的家居信息
            // 3. 返回的家居信息取出，绑定到 this.form
            // add方法，显示添加的对话框
            add() {
                this.dialogVisible = true
                // 每次显示添加的对话框时，清空表单数据
                this.form = {}
            },
            // 处理删除
            handleDel(id) {
                // alert("id = " + id)
                // 使用request发出请求，删除当前的家居
                // 如果显示和关闭 vue 方法参数提示->settings->hints
                request.delete("api/del/" + id).then(res => {
                    // 判断
                    if (res.code === "200") { // 删除成功
                        this.$message({
                            type: "success",
                            message: "删除成功"
                        })
                    } else { // 删除失败
                        this.$message({
                            type: "error",
                            message: res.msg
                        })
                    }
                    // 刷新居家列表
                    this.list()
                })
            },

            handleEdit(row) { //  先写一个空方法
                console.log("row1=", row)
                //JSON.stringify(row) 转成 json 字符串
                console.log("row2=", JSON.stringify(row))
                // 把 json 字符串，转成json对象
                console.log("row3=", JSON.parse(JSON.stringify(row)))

                // 把得到数据和form绑定，显示到对话框中
                /*
                1.JSON.parse(JSON.stringify(row)) 就是对行数据进行探讨拷贝
                2. 这样点击的表格当前行的数据和弹出框的数据是独立的
                 */
                this.form = JSON.parse(JSON.stringify(row))
                this.dialogVisible = true; // 回显对话框

            },

            // 1. 通过 row.id,发出请求 Get
            // 2. 返回对应家居信息
            // 3. 返回的家居信息取出，绑定到 this.form 当中
            handleEdit2(row) {
                request.get("/api/find/" + row.id).then(res => {
                    if (res.code === "200") {  // 查询成功
                        // 取出数据绑定 this.form
                        this.form = res.data

                    } else {
                        this.$message({
                            type: "error",
                            message: "更新失败" // 也可以message: res.msg
                        })
                    }
                })
                this.dialogVisible = true // 显示对话框
            },

            // 处理 pageSize 选择多少条记录的，按钮的变化
            handlePageSizeChange(pageSize) {
                // alert(pageSize)
                this.pageSize = pageSize
                this.list() // 刷新家居列表
            },

            // //处理当前页变化,  比如点击分页连接,或者 go to 第几页
            // 处理 handleCurrentChange 的变化
            // 选择 currentPage 第几页的页数
            handleCurrentChange(pageNum) {
                // alert(currentPage)
                this.currentPage = pageNum
                this.list() // 刷新页面
            }


        }

    }
</script>
