<template>
    <el-table :data="tableData"
            :header-cell-style="{ background: '#f0f2fb', color: '#555'}"
            border
        >
        <el-table-column prop="id" label="ID" width="60">
        </el-table-column>
        <el-table-column prop="no" label="Account" width="180">
        </el-table-column>
        <el-table-column prop="name" label="Name" width="180">
        </el-table-column>
        <el-table-column prop="age" label="Age" width="80">
        </el-table-column>
        <el-table-column prop="sex" label="Gender" width="100">
            <template slot-scope="scope">
                <el-tag
                    :type="scope.row.sex === 1 ? 'primary' : 'success'"
                    disable-transitions>{{scope.row.sex === 1 ? 'Male' : 'Female'}}</el-tag>
            </template>
        </el-table-column>
        <el-table-column prop="roleId" label="Role" width="120">
            <template slot-scope="scope">
                <el-tag
                    :type="scope.row.roleId === 0 ? 'danger' : (scope.row.roleId === 1 ? 'primary' : 'success')"
                    disable-transitions>{{scope.row.roleId === 0 ? 'Super Admin' :
                                         (scope.row.roleId === 1 ? 'Admin' : 'User')}}</el-tag>
            </template>
        </el-table-column>
        <el-table-column prop="phone" label="Phone Number" width="180">
        </el-table-column>
        <el-table-column prop="operate" label="Operate">
            <el-button size="small" type="success">Edit</el-button>
            <el-button size="small" type="danger">Delete</el-button>
        </el-table-column>
    </el-table>
</template>

<script>
export default {
    name: "MainComponent",
      data() {
        return {
          tableData: []
        }
      },
      methods:{
        loadGet(){
          this.$axios.get(this.$httpUrl+'/user/list').then(res=>res.data).then(res=>{
            console.log(res)
          })
        },
        loadPost() {
          this.$axios.post(this.$httpUrl+'/user/listP',{}).then(res => res.data).then(res => {
            console.log(res)
            if(res.code==100){
              this.tableData = res.data
            }else{
                alert('acquire data failed')
            }

          })
        }
      },
      beforeMount() {
        //this.loadGet();
        this.loadPost()
      }
}
</script>

<style scoped>

</style>