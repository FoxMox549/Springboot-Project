<template>
    <el-menu
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b"
            style="height: 100%;"
            default-active="/Home"
            :collapse="isCollapse"
            :collapse-transition="false"
            router
    >
        <el-menu-item index="/Home">
            <i class="el-icon-s-home"></i>
            <span slot="title">Home Page</span>
        </el-menu-item>

        <el-menu-item :index="'/' + item.menuclick" v-for="(item,i) in menu" :key="i">
            <i :class="item.menuicon"></i>
            <span slot="title">{{item.menuname}}</span>
        </el-menu-item>
    </el-menu>
</template>

<script>
export default {
    name: "AsideComponent",
    data() {
        return {
            //isCollapse: false

            /*menu:[
                {
                    menuClick: 'Admin',
                    menuName:'Admin Manage',
                    menuIcon:'el-icon-s-custom',
                },
                {
                    menuClick: 'User',
                    menuName:'User Manage',
                    menuIcon:'el-icon-user-solid',
                }
            ]*/
        }
    },
    computed:{
        "menu":{
            get(){
                return this.$store.state.menu
            }
        }
    },
    props: {
        isCollapse: Boolean
    }
}
</script>

<style scoped>

</style>