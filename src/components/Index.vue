<template>
  <el-container style="height: 100%; border: 1px solid #eee">
    <el-aside :width="aside_width" style="background-color: rgb(238, 241, 246);height: 100%;margin-left: -1px;">
        <Asidecomponent :isCollapse="isCollapse"></Asidecomponent>
    </el-aside>

    <el-container style="height: 100%;">
      <el-header style="text-align: right; font-size: 12px; height: 100%;border-bottom: #c6c6c6 1px solid;">
          <HeaderComponent @doCollapse="doCollapse" :icon="icon"></HeaderComponent>
      </el-header>

      <el-main style="height: 100%;">
          <MainComponent></MainComponent>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
    import Asidecomponent from "./AsideComponent.vue";
    import HeaderComponent from "./HeaderComponent.vue";
    import MainComponent from "./MainComponent.vue";


    export default {
        name: "IndexComponent",
        components: {Asidecomponent,HeaderComponent,MainComponent},
        data(){
            return {
                isCollapse: false,
                aside_width:'200px',
                icon:'el-icon-s-fold'
            }
        },
        methods:{
            doCollapse(){
                console.log(1111)

                this.isCollapse = !this.isCollapse
                if(!this.isCollapse){ // fold
                    this.aside_width='200px'
                    this.icon='el-icon-s-fold'
                }else{ // unfold
                    this.aside_width='64px'
                    this.icon='el-icon-s-unfold'
                }

            }
        }
    }
</script>

<style scoped>
    .el-header {
       #background-color: #B3C0D1;
       color: #333;
       line-height: 60px;
    }

    .el-main-{
      padding: 5px;
    }

    .el-aside {
       color: #333;
    }
</style>