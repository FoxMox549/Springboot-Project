<template>
  <div style="display: flex;line-height: 60px;">
    <div style="margin-top: 5px;">
        <i class="el-icon-s-fold" style="font-size: 20px;"></i>
    </div>
    <div style="flex: 1;text-align: center;font-size: 25px;">
        <span>Welcome to Warehouse Manager System</span>
    </div>
    <el-dropdown>
      <i class="el-icon-setting" style="margin-left: 1px;"></i>
      <span>Tester</span><el-dropdown-menu slot="dropdown">
          <el-dropdown-item @click.native="toUser">Personal center</el-dropdown-item>
          <el-dropdown-item @click.native="logout">Log out</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
export default {
  name: "HeaderComponent",
  methods:{
    toUser(){
      console.log('to_user')
    },
    logout(){
      console.log('logout')
    }
  }
}
</script>

<style scoped>

</style>