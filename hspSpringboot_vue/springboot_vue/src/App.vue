<template>
    <div>
        <Header/>
        <!--        Home-->
        <!--        <el-button>我的按钮</el-button>-->

        <!--     主体:    弹性布局 -->
        <div style="display: flex">
            <!--            侧边栏上的实现-->
            <Aside/>
            <router-view style="flex: 1"></router-view>
        </div>

    </div>
</template>

<style>

</style>

<script>
    import Header from "@/components/Header";
    import Aside from "@/components/Aside";

    export default {
        name: "Layout",
        components: {
            Aside,  // 左侧导航栏上的显示组件
            Header
        }
    }
</script>
