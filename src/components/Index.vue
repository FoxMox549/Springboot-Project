

<template>
  <el-container style="height: 100%; border: 1px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246);height: 100%;">
        <Asidecomponent></Asidecomponent>
    </el-aside>

    <el-container style="height: 100%;">
      <el-header style="text-align: right; font-size: 12px; height: 100%;border-bottom: #c6c6c6 1px solid;">
          <HeaderComponent></HeaderComponent>
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