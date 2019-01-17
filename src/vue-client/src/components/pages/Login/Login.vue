<template>
<div class="background">
  <p style="text-align: center">LinkDisk</p>
  <div class="suibian1">
    <el-form :model="RegistForm" v-show="this.RegistFlag" status-icon :rules="rules2" ref="RegistForm" label-width="80px" class="demo-ruleForm" >
      <el-form-item label="用户名" prop="userName" style="color: red">
        <el-input type="text" v-model="RegistForm.userName" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="pass" style="color: red">
    <el-input type="password" v-model="RegistForm.pass" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item label="确认密码" prop="checkPass">
    <el-input type="password" v-model="RegistForm.checkPass" autocomplete="off"></el-input>
    </el-form-item>
    <!--<el-form-item label="年龄" prop="age">-->
    <!--<el-input v-model.number="RegistForm.age"></el-input>-->
    <!--</el-form-item>-->
      <el-form-item>
        <el-button @click="resetForm('RegistForm')" style="margin-left: -25px ">重置</el-button>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <el-button type="primary" @click="Regist('RegistForm')" size="middle" style="margin-left: -25px ">注册</el-button>
        <!--<el-button type="primary" @click="Login('RegistForm')">登录</el-button>-->
      </el-form-item>

    </el-form>

    <el-form :model="LoginForm" v-show="this.LoginFlag" status-icon :rules="rules" ref="LoginForm" label-width="80px" class="demo-ruleForm" >
      <el-form-item label="用户名" prop="userName" style="color: red">
        <el-input type="text" v-model="LoginForm.userName" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="pass" style="color: red">
        <el-input type="password" v-model="LoginForm.pass" autocomplete="off"></el-input>
      </el-form-item>
      <!--<el-form-item label="确认密码" prop="checkPass">-->
        <!--<el-input type="password" v-model="RegistForm.checkPass" autocomplete="off"></el-input>-->
      <!--</el-form-item>-->
      <!--<el-form-item label="年龄" prop="age">-->
      <!--<el-input v-model.number="RegistForm.age"></el-input>-->
      <!--</el-form-item>-->
      <el-form-item>
        <el-button type="primary" @click="showRegist()" size="middle" style="margin-left: -25px ">注册</el-button>
        <el-button @click="resetForm('LoginForm')">重置</el-button>
        <el-button type="primary" @click="Login('LoginForm')">登录</el-button>
      </el-form-item>

    </el-form>

  </div>


</div>
</template>

<script>
  export default {
    name: 'Login',
    data() {
      var checkAge = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('年龄不能为空'));
        }
        setTimeout(() => {
          if (!Number.isInteger(value)) {
            callback(new Error('请输入数字值'));
          } else {
            if (value < 18) {
              callback(new Error('必须年满18岁'));
            } else {
              callback();
            }
          }
        }, 1000);
      };
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.RegistForm.checkPass !== '') {
            this.$refs.RegistForm.validateField('checkPass');
          }
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {

        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.RegistForm.pass) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      var validateLoginPass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {

          callback();
        }
      };

      var checkUserLoginName = (rule, value, callback) => { //登录检查用户名是否为空
        if (value === '') {
          return callback(new Error('用户名不能为空'));
        }else{
          callback();

        };




      };
      var checkUserName = (rule, value, callback) => { //注册查询后台用户名是否存在
        if (value === '') {
          return callback(new Error('用户名不能为空'));
        }else{
          let userName = this.RegistForm.userName;
          let param = new URLSearchParams();
          param.append("userName",userName);
          this.$http.post('/User/Login/checkUserName',param).then((res) =>{
            //console.log(res)
            //console.log(typeof(res) )
            if(res.data.entity == 1){
              callback();
            }else{
              return callback(new Error('用户名已存在...'))
            }
          })


        };
      };
      return {
        LoginFlag : true,
        RegistFlag:false,
        LoginForm: {
          userName:'',
          pass:''
        },
        RegistForm: {
          userName:'',
          pass: '',
          checkPass: '',
        },
        rules: {
          userName: [
            { validator: checkUserLoginName, trigger: 'blur' }
          ],
          pass: [
            { validator: validateLoginPass, trigger: 'blur' }
          ],
        },
        rules2: {
          pass: [
            { validator: validatePass, trigger: 'blur' }
          ],
          checkPass: [
            { validator: validatePass2, trigger: 'blur' }
          ],
          userName: [
            { validator: checkUserName, trigger: 'blur' }
          ]
        }
      };
    },
    methods: {
      showRegist(){
        this.RegistFlag=true;
        this.LoginFlag=false
      },
      Login(formName){
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('login!');
            console.log(this.LoginForm)
          } else {
            console.log('error login!!');
            return false;
          }
        });
      },
      Regist(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            let _this = this;
            let params = new URLSearchParams();
            let userName = _this.RegistForm.userName,
                passWord = _this.RegistForm.pass;
            params.append('userName',userName);
            params.append('passWord',passWord);
            this.$http.post('/User/Login/Regist',params).then((res) =>{
              console.log(res)
              if(res.data.status == "200"){
                this.RegistFlag=false;
                this.resetForm('RegistForm');
                this.RegistForm =  {
                  userName:'',
                  pass:'',
                  checkPass: ''
                },
                  this.LoginForm.userName=res.data.entity.userName;
                  this.LoginFlag=true;
              }else{

              }
            })
          } else {
            //console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }
</script>

<style >
.background{

  background:url(/static/image/aurora.jpg) ;
  background-size:cover;
  height: 100vh;
  width: 100%;
}
  .suibian1{
    width: 300px;
    height: 400px;
    left: 50%;
    top: 50%;
    position: absolute;
    margin-left: -150px;
    margin-top: -200px;
    /*background-color: white;*/
    /*border: 1px solid #000;*/
    color: red;

  }
  .demo-ruleForm{

  }
.el-form-item__label {
  text-align: right;
  float: left;
  font-size: 14px;
  color: darkorange;
  line-height: 40px;
  padding: 0 12px 0 0;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  font-weight: bold;
}



</style>
