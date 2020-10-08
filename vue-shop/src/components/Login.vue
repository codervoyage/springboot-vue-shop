<template>

  <div id="boxContainer">
    <div id="box">
      <h3 id="ph">请登录</h3>

       <el-form id="loginForm" :rules="loginRules" ref="loginFormRef"  :model="loginFrom">
         <div id="box1">
             <el-form-item class="efi" label="账号" prop="username">
               <el-input class="loginInput" v-model="loginFrom.username"></el-input>
             </el-form-item>

           <el-form-item class="efi" label="密码" prop="password">
             <el-input class="loginInput" type="password" v-model="loginFrom.password"></el-input>
           </el-form-item>
         </div>
           <el-form-item id="box2">
               <el-button type="primary" @click="submitForm()">登录</el-button>
               <el-button>重置</el-button>
           </el-form-item>
       </el-form>

    </div>
  </div>

</template>

<script>
export default {
  name: 'Login',

  data(){
    return{
      loginFrom:{
        username:'admin',
        password:'admin123'
      },
      loginRules:{
        username:[
          { required: true, message: '请输入username', trigger: 'blur' }
        ],
        password:[
          { required: true, message: '请输入password', trigger: 'blur' }
        ]
      }
    }
  },
  methods:{
    submitForm(){
      this.$refs.loginFormRef.validate(async valid =>{
        console.log(valid);
        if(!valid) return
        const {data} = await this.$http.post('login',this.loginFrom)
        if (data.meta.status === 200) {
          this.$message.success("登录成功");
          this.$router.push("/home");
        } else {
          this.$message.error(data.meta.msg);
        }

      })

    }
  }
}
</script>

<style scoped>
#boxContainer {
  background: rgb(45, 58, 75);
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
#box {
  width: 400px;
  height: 350px;
/*  border: 1px solid black;*/
  display: flex;
  justify-content: center;
  align-items: center;
  flex-flow: wrap;
}
#box1{
  width: 400px;
}
#loginForm{
  display: flex;
  justify-content: center;
  flex-flow: wrap;

}
#box2{
display: flex;
  justify-content: flex-end;
  width: 380px;
}
#ph{
  color: aliceblue;
  width: 200px;
  text-align: center;
}
.loginInput{
  width: 340px;
}

</style>