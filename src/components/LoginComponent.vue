<template>
    <div class="loginBody">
        <div class="loginDiv">
            <div class="login-content">
                <h1 class="login-title">User Login</h1>
                <el-form :model="loginForm" label-width="100px"
                         :rules="rules" ref="loginForm">
                    <el-form-item label="Username" prop="no">
                        <el-input style="width: 200px" type="text" v-model="loginForm.no"
                                  autocomplete="off" size="small"></el-input>
                    </el-form-item>
                    <el-form-item label="Password" prop="password">
                        <el-input style="width: 200px" type="password" v-model="loginForm.password"
                                  show-password autocomplete="off" size="small"
                                  @keyup.enter.native="confirm"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="confirm" :disabled="confirm_disabled" style="margin-left: 60px">Login</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
    </div>
</template>


<script>
export default {
    name: "LoginComponent",
    data() {
        return {
            confirm_disabled: false,
            loginForm: { // default display
                no: '',
                password: ''
            },
            rules: { // a rule to check whether it is entered
                no: [
                    {required: true, message: 'Please Enter Account', trigger: 'blur'}
                ],
                password: [
                    {required: true, message: 'Please Enter Password', trigger: 'blur'}
                ],
            }
        }
    },
    methods: {

        confirm() { // submit data to the backend
            this.confirm_disabled = true;
            this.$refs.loginForm.validate((valid) => {
                if (valid) { // valid is true for success, false for failure
                    // verify the username and the password in backend
                    this.$axios.post(this.$httpUrl + '/user/login', this.loginForm).then(res => res.data).then(res => {
                        console.log(res)
                        if (res.code == 100) {
                            // store
                            sessionStorage.setItem("CurUser", JSON.stringify(res.data))

                            // encountered errors that need fix
                            /*sessionStorage.setItem("CurUser", JSON.stringify(res.data.user))
                            console.log(res.data.menu)
                            this.$store.commit("setMenu",res.data.menu)*/

                            // jump to main page
                            this.$router.replace('/Index');
                        } else {
                            this.confirm_disabled = false;
                            alert('Verification failed, wrong username or password!');
                            return false;
                        }
                    });
                } else {
                    this.confirm_disabled = false;
                    console.log('Verification failed');
                    return false;
                }
            });

        }
    }
}
</script>

<style scoped>
.loginBody {
    position: absolute;
    width: 100%;
    height: 100%;
    background-color: #B3C0D1;
}

.loginDiv {
    position: absolute;
    top: 50%;
    left: 50%;
    margin-top: -200px;
    margin-left: -250px;
    width: 450px;
    height: 330px;
    background: #fff;
    border-radius: 5%;

}

.login-title {
    margin: 20px 0;
    text-align: center;
}

.login-content {
    width: 400px;
    height: 250px;
    position: absolute;
    top: 25px;
    left: 25px;
}
</style>