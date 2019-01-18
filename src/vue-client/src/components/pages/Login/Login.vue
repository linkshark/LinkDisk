<template>
<div class="background">
  <div class="container" >
    <p style="text-align: center">LinkDisk</p>
    <p class="shit" v-show="this.LoginFlag">登录</p>
    <p class="shit" v-show="this.RegistFlag">注册</p>
  </div>

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
        <el-button type="warning" @click="showLogin()" style="margin-left: -25px ">返回</el-button>
        <el-button type="info" @click="resetForm('RegistForm')" >重置</el-button>
        <el-button type="primary" @click="Regist('RegistForm')" size="middle" >注册</el-button>
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
        <el-button type="info" @click="resetForm('LoginForm')">重置</el-button>
        <el-button type="primary" @click="Login('LoginForm')">登录</el-button>
      </el-form-item>
      <span class="showbuliaole">是否记住密码</span>
      <el-tooltip :content="'记住密码: ' + rememberFlag" placement="top">
        <el-switch
          v-model="rememberFlag"
          active-color="#13ce66"
          inactive-color=gray
          active-value= 1
          inactive-value= 0>
        </el-switch>
      </el-tooltip>

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
        rememberFlag:0,
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
      showLogin(){
        this.RegistFlag=false;
        this.LoginFlag=true;
      },
      Login(formName){
        this.$refs[formName].validate((valid) => {
          if (valid) {
            // alert('login!');
            // console.log(this.LoginForm)
            let params = new URLSearchParams();
            params.append("userName",this.LoginForm.userName)
            params.append("passWord",this.LoginForm.pass)
            this.$http.post('/User/Login/Login',params).then((res) =>{
              if(res.data.status == 200){
                console.log(this.rememberFlag)
                if(this.rememberFlag==1){
                  this.$Cookies.set("userName",this.LoginForm.userName,{ expires: 7 });
                  this.$Cookies.set("passWord",this.LoginForm.pass,{ expires: 7 })
                }else{
                  //debugger;
                  this.$Cookies.remove("userName");
                  this.$Cookies.remove("passWord")
                }
              }
            })
          } else {
            this.$message({
              showClose: true,
              center: true,
              message: '有东西写错了 >_<¦¦¦ 很尴尬~!!!',
              type:'error'
            });
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
                this.$message({
                  showClose: true,
                  center: true,
                  type: 'success',
                  message: '注册成功'
                });
                this.RegistFlag=false;
                this.resetForm('RegistForm');
                this.RegistForm =  {
                  userName:'',
                  pass:'',
                  checkPass: ''
                },
                  this.LoginForm.userName=res.data.entity.userName;
                   this.LoginForm.pass="";
                  this.LoginFlag=true;
              }else{
                this.$message({
                  showClose: true,
                  center: true,
                  message: '注册失败,后台程序猿要加班了!',
                  type:'error'
                });
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
      },
    },
    mounted () {
      //this.$Cookies.set("沈超琦","大帅哥",)
      console.log(this.$Cookies.get("userName"))
      if(this.$Cookies.get("userName")!=undefined){
        alert("免密登录成功")
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
  overflow: hidden;
}
  .suibian1{
    width: 300px;
    height: 400px;
    left: 50%;
    top: 50%;
    position: absolute;
    margin-left: -180px;
    margin-top: -100px;
    /*background-color: white;*/
    /*border: 1px solid #000;*/
    color: red;

  }
  .demo-ruleForm{

  }
  .demo{
    background: #666666;
    /*width: 440px;*/
    /*padding: 30px;*/
    font: bold 55px/100% "微软雅黑", "Lucida Grande", "Lucida Sans", Helvetica, Arial, Sans;;
    color: #fff;
    text-transform: uppercase;
    text-shadow: -1px -1px rgba(197, 223, 248,0.8),-2px -2px rgba(197, 223, 248,0.8),-3px -3px rgba(197, 223, 248,0.8),-4px -4px rgba(197, 223, 248,0.8),-5px -5px rgba(197, 223, 248,0.8),-6px -6px rgba(197, 223, 248,0.8);
  }
.container {
  width: 600px;
  margin: 200px auto 0;
  /*margin-left: 775px;*/
  /*margin-top: 300px;*/
}
.shit {
  width: 200px;
  margin: 50px auto 0;
  font-size: 2em;
  color: #8b77ff;
  font-weight: bold;
}
/*.container2{*/
  /*width: 600px;*/
  /*margin: 50px auto 0;*/
/*}*/
p {
  font-family: 'Audiowide';
  text-align: center;
  color: #A62339;
  font-size: 8em;
  font-weight: bold;
  -webkit-transition: all 1.5s ease;
  transition: all 1.5s ease;
}
p:hover {
  color: #fff;
  -webkit-animation: Glow 1.5s ease infinite alternate;
  animation: Glow 1.5s ease infinite alternate;

}
@-webkit-keyframes Glow {
  from {
    text-shadow: 0 0 10px #fff,
    0 0 20px #fff,
    0 0 30px #fff,
    0 0 40px #A62339,
    0 0 70px #A62339,
    0 0 80px #A62339,
    0 0 100px #A62339,
    0 0 150px #A62339;
  }
  to {
    text-shadow: 0 0 5px #fff,
    0 0 10px #fff,
    0 0 15px #fff,
    0 0 20px #A62339,
    0 0 35px #A62339,
    0 0 40px #A62339,
    0 0 50px #A62339,
    0 0 75px #A62339;
  }
}
@keyframes Glow {
  from {
    text-shadow: 0 0 10px #fff,
    0 0 20px #fff,
    0 0 30px #fff,
    0 0 40px #A62339,
    0 0 70px #A62339,
    0 0 80px #A62339,
    0 0 100px #A62339,
    0 0 150px #A62339;
  }
  to {
    text-shadow: 0 0 5px #fff,
    0 0 10px #fff,
    0 0 15px #fff,
    0 0 20px #A62339,
    0 0 35px #A62339,
    0 0 40px #A62339,
    0 0 50px #A62339,
    0 0 75px #A62339;
  }
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
.showbuliaole{
  margin-left: 95px;
}



</style>
