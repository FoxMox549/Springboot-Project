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
            <el-button type="primary" style="margin-left: 5px;" @click="add">Add New</el-button>
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
                            disable-transitions>{{ scope.row.sex === 1 ? 'Male' : 'Female' }}
                    </el-tag>
                </template>
            </el-table-column>
            <el-table-column prop="roleId" label="Role" width="120">
                <template slot-scope="scope">
                    <el-tag
                            :type="scope.row.roleId === 0 ? 'danger' : (scope.row.roleId === 1 ? 'primary' : 'success')"
                            disable-transitions>{{
                            scope.row.roleId === 0 ? 'Super Admin' :
                                    (scope.row.roleId === 1 ? 'Admin' : 'User')
                        }}
                    </el-tag>
                </template>
            </el-table-column>
            <el-table-column prop="phone" label="Phone Number" width="180">
            </el-table-column>
            <el-table-column prop="operate" label="Operate">
                <template slot-scope="scope">
                    <el-button size="small" type="success" @click="mod(scope.row)">Edit</el-button>
                    <el-popconfirm title="Sure Delete？" @confirm="del(scope.row.id)" style="margin-left: 8px">
                        <el-button slot="reference" size="small" type="danger">Delete</el-button>
                    </el-popconfirm>
                </template>
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

        <el-dialog
                title="Hint"
                :visible.sync="centerDialogVisible"
                width="30%"
                center>

            <el-form ref="form" :rules="rules" :model="form" label-width="100px">
                <el-form-item label="Account" prop="no">
                    <el-col :span="20">
                        <el-input v-model="form.no"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="Password" prop="password">
                    <el-col :span="20">
                        <el-input v-model="form.password"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="Name" prop="name">
                    <el-col :span="20">
                        <el-input v-model="form.name"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="Age" prop="age">
                    <el-col :span="20">
                        <el-input v-model="form.age"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="Gender">
                    <el-radio-group v-model="form.sex">
                        <el-radio label="1">Male</el-radio>
                        <el-radio label="0">Female</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="Phone" prop="phone">
                    <el-col :span="20">
                        <el-input v-model="form.phone"></el-input>
                    </el-col>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
    <el-button @click="centerDialogVisible = false">N O</el-button>
    <el-button type="primary" @click="save">Y E S</el-button>
  </span>
        </el-dialog>
    </div>
</template>

<script>
export default {
    name: "UserManage",
    data() {
        let checkAge = (rule, value, callback) => {
            if (value > 150) {
                callback(new Error('Age input is too large'));
            } else {
                callback();
            }
        };
        let checkDuplicate = (rule, value, callback) => {
            if (this.form.id) {
                return callback();
            }
            this.$axios.get(this.$httpUrl + '/user/findByNo?no=' + this.form.no).then(res => res.data).then(res => {
                if (res.code !== 100) {
                    callback()
                } else {
                    callback(new Error('Account already exists'));
                }
            })
        };
        return {
            tableData: [],
            pageSize: 10,
            pageNum: 1,
            total: 0,
            name: '',
            sex: '',
            sexs: [
                {
                    value: '1',
                    label: 'Male'
                }, {
                    value: '0',
                    label: 'Female'
                },
            ],
            centerDialogVisible: false,
            form: {
                id: '',
                no: '',
                name: '',
                password: '',
                age: '',
                phone: '',
                sex: '0',
                roleId: '2',
            },
            rules: {
                no: [
                    {required: true, message: 'Please enter account', trigger: 'blur'},
                    {min: 3, max: 8, message: '3 - 8 letters', trigger: 'blur'},
                    {validator: checkDuplicate, trigger: 'blur'}
                ],
                name: [
                    {required: true, message: 'Please enter name', trigger: 'blur'},
                ],
                password: [
                    {required: true, message: 'Please enter name', trigger: 'blur'},
                    {min: 3, max: 8, message: '3 - 8 letters', trigger: 'blur'}
                ],
                age: [
                    {required: true, message: 'Please enter age', trigger: 'blur'},
                    {min: 1, max: 3, message: '3 - 8 letters', trigger: 'blur'},
                    {pattern: /^([1-9][0-9]*){1,3}$/, message: 'age must be integer number', trigger: 'blur'},
                    {validator: checkAge, trigger: 'blur'}
                ],
                phone: [
                    {required: true, message: 'Phone number cannot be empty', trigger: 'blur'},
                    {
                        // American format: (XXX) XXX-XXXX or XXX-XXX-XXXX
                        // China format: 1 followed by a number of 3-9, followed by 9 numbers
                        pattern: /^\(?([0-9]{3})\)?[- ]?([0-9]{3})[- ]?([0-9]{4})$| ^1[3-9][0-9]{9}$/,
                        message: 'Please input a correct phone number', trigger: 'blur'
                    },
                ]
            }
        }
    },
    methods: {
        // to resetForm
        resetForm() {
            this.$refs.form.resetFields();
        },
        // to delete data when press Delete button
        del(id) {
            console.log(id)
            this.$axios.get(this.$httpUrl + '/user/del?id=' + id).then(res => res.data).then(res => {
                console.log(res)
                if (res.code == 100) {

                    this.$message({
                        message: 'Successfully Deleted!',
                        type: 'success'
                    });
                    this.loadPost() // refresh page
                } else {
                    this.$message({
                        message: 'Failed to Delete!',
                        type: 'error'
                    });
                }

            })
        },
        // to modify data when press Edit button
        mod(row) {
            console.log(row);

            // to display data in form
            this.centerDialogVisible = true
            this.$nextTick(() => {
                // assign values to form
                //this.form = row
                this.form.id = row.id
                this.form.no = row.no
                this.form.name = row.name
                this.form.password = ''
                this.form.age = row.age + ''
                this.form.sex = row.sex + ''
                this.form.phone = row.phone
                this.form.roleId = row.roleId
            })
        },
        // for Add New button
        add() {
            this.centerDialogVisible = true
            this.$nextTick(() => {
                this.resetForm()
            })
        },
        // after clicked Add New button, press Yes will do save
        save() {
            this.$refs.form.validate(valid => {
                // check the condition if this form exist then do backend modify
                if (valid) {
                    if (this.form.id) {
                        this.doMod();
                    } else { // check the condition if this form does not exist then do backend save
                        this.doSave();
                    }
                } else {
                    console.log('error submit!');
                    return false;
                }
            });
        },
        doSave() {
            this.$axios.post(this.$httpUrl + '/user/save', this.form).then(res => res.data).then(res => {
                console.log(res)
                if (res.code == 100) {

                    this.$message({
                        message: 'Successfully Saved!',
                        type: 'success'
                    });
                    this.centerDialogVisible = false
                    this.loadPost() // refresh page
                    this.resetForm()
                } else {
                    this.$message({
                        message: 'Failed to Save!',
                        type: 'error'
                    });
                }

            })
        },
        doMod() {
            this.$axios.post(this.$httpUrl + '/user/update', this.form).then(res => res.data).then(res => {
                console.log(res)
                if (res.code == 100) {

                    this.$message({
                        message: 'Successfully Saved!',
                        type: 'success'
                    });
                    this.centerDialogVisible = false
                    this.loadPost() // refresh page
                    this.resetForm()
                } else {
                    this.$message({
                        message: 'Failed to Save!',
                        type: 'error'
                    });
                }

            })
        },
        handleSizeChange(val) {
            console.log(`${val} items per page`);
            this.pageNum = 1
            this.pageSize = val
            this.loadPost() // refresh page
        },
        handleCurrentChange(val) {
            console.log(`Current page: ${val}`);
            this.pageNum = val
            this.loadPost() // refresh page
        },
        loadGet() {
            this.$axios.get(this.$httpUrl + '/user/list').then(res => res.data).then(res => {
                console.log(res)
            })
        },
        resetParam() {
            this.name = ''
            this.sex = ''
        },
        loadPost() { // get page
            this.$axios.post(this.$httpUrl + '/user/listPageC1', {
                pageSize: this.pageSize,
                pageNum: this.pageNum,
                param: {
                    name: this.name,
                    sex: this.sex,
                    roleId:'2'
                }
            }).then(res => res.data).then(res => {
                console.log(res)
                if (res.code == 100) {
                    this.tableData = res.data
                    this.total = res.total
                } else {
                    alert('acquire data failed')
                }

            })
        }
    },
    beforeMount() {
        //this.loadGet();
        this.loadPost() // refresh page
    }
}
</script>

<style scoped>

</style>