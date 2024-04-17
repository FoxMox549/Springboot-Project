<template>
    <div>
        <div style="margin-bottom: 5px">
            <el-input v-model="name" placeholder="Search name" suffix-icon="el-icon-search" style="width: 200px;"
                      @keyup.enter.native="loadPost"></el-input>
            <el-select v-model="sex" placeholder="Select gender" style="margin-left: 5px">
              <el-option
                  v-for="item in sexs"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
              </el-option>
            </el-select>
            <el-button type="primary" style="margin-left: 5px;" @click="loadPost">Search</el-button>
            <el-button type="success" @click="resetParam" style="margin-left: 5px;">Reset</el-button>
        </div>
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
           <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="pageNum"
                :page-sizes="[5, 10, 20, 30]"
                :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
           </el-pagination>
    </div>
</template>

<script>
export default {
    name: "MainComponent",
      data() {
        return {
          tableData: [],
          pageSize:10,
          pageNum:1,
          total:0,
          name:'',
          sex:'',
          sexs:[
            {
              value: '1',
              label: 'Male'
            }, {
              value: '0',
              label: 'Female'
            },
          ]
        }
      },
      methods:{
        handleSizeChange(val) {
          console.log(`${val} items per page`);
          this.pageNum = 1
          this.pageSize = val
          this.loadPost()
        },
        handleCurrentChange(val) {
          console.log(`Current page: ${val}`);
          this.pageNum = val
          this.loadPost()
        },
        loadGet(){
          this.$axios.get(this.$httpUrl+'/user/list').then(res=>res.data).then(res=>{
            console.log(res)
          })
        },
        resetParam(){
          this.name=''
          this.sex=''
        },
        loadPost() {
          this.$axios.post(this.$httpUrl+'/user/listPageC1',{
            pageSize:this.pageSize,
            pageNum:this.pageNum,
            param:{
              name:this.name,
              sex:this.sex,
            }
          }).then(res => res.data).then(res => {
            console.log(res)
            if(res.code==100){
              this.tableData = res.data
              this.total=res.total
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