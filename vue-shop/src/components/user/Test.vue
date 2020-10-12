<template>
  <div style="width: 400px">
    <el-form ref="updatePasswordForm" :model="formInfo" :rules="formInfoRules" label-width="100px">
      <el-form-item label="原密码" prop="oldPassword">
        <el-input v-model="formInfo.oldPassword"></el-input>
      </el-form-item>
      <el-form-item label="新密码" prop="newPassword">
        <el-input v-model="formInfo.newPassword"></el-input>
      </el-form-item>
      <el-form-item label="确认新密码" prop="newPwd">
        <el-input v-model="formInfo.newPwd"></el-input>
      </el-form-item>
      <el-form-item class="btn">
        <el-button type="primary" @click="updatePassword">修改</el-button>
        <el-button type="info" @click="reset">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: 'Test',
  data () {
    return {
      formInfo: {
        userId: 0,
        oldPassword: '',
        newPassword: '',
        newPwd: ''
      },
      formInfoRules: {
        oldPassword: [{
          required: true,
          message: '请输入原密码',
          trigger: 'blur'
        }],
        newPassword: [{
          required: true,
          message: '请输入新密码',
          trigger: 'blur'
        }],
        newPwd: [{
          required: true,
          message: '请再次输入新密码',
          trigger: 'blur'
        }]
      }
    }
  },
  methods: {
    // 点击按钮重置input框
    reset () {
      this.$refs['updatePasswordForm'].resetFields()
    },
    //修改密码
    updatePassword () {
      this.$refs['updatePasswordForm'].validate(valid => {
        if (!valid) return
        if (window.sessionStorage.getItem('userPassword') !== this.formInfo.oldPassword) {
          this.$notify.error({
            title: '提示',
            message: '原密码输入错误'
          })
        } else {
          if (this.formInfo.newPassword !== this.formInfo.newPwd) {
            this.$notify.warning({
              title: '提示',
              message: '两次密码输入不一致'
            })
          } else {
            this.formInfo.userId = Number(window.sessionStorage.getItem('userId'))
            this.$http.post('/updatePassword', this.formInfo).then(res => {
              if (res.data.meta.status === 200) {
                this.$message.success(res.data.meta.msg)
                this.$router.push('/login')
              }
            }).catch(err => {
              this.$message.error('请求出错')
            })
          }
        }
      })
    }

  }
}
</script>

<style scoped>
.btn {
  display: flex;
  justify-content: flex-end;
}
</style>