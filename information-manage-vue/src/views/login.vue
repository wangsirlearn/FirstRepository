<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const username = ref('')
const password = ref('')
const rememberMe = ref(false)

const handleLogin = () => {
  // 这里可以添加登录逻辑
  console.log('登录信息:', {
    username: username.value,
    password: password.value,
    rememberMe: rememberMe.value
  })
  // 登录成功后跳转到首页
  router.push('/index')
}
</script>
<template>
  <div class="login-container">
    <div class="login-form-wrapper">
      <h2 class="login-title">tlias数据管理系统</h2>
      <el-form :model="{ username, password }" class="login-form" label-position="top">
        <el-form-item label="用户名">
          <el-input v-model="username" placeholder="请输入用户名" prefix-icon="UserFilled"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="password" type="password" placeholder="请输入密码" prefix-icon="Lock"></el-input>
        </el-form-item>
        <el-form-item>
          <el-checkbox v-model="rememberMe">记住我</el-checkbox>
          <el-link type="primary" :underline="false" class="forgot-password">忘记密码？</el-link>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" class="login-button" @click="handleLogin">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>
<style scoped>
.login-container {
  width: 80%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  /* background-color: #f5f7fa; */
  margin: 50px auto;
}

.login-form-wrapper {
  width: 400px;
  padding: 40px;
  background-color: #ffffff;
  border-radius: 8px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.login-title {
  text-align: center;
  margin-bottom: 30px;
  color: #303133;
  font-size: 24px;
 
}

.login-form {
  width: 100%;
}

.forgot-password {
  float: right;
}

.login-button {
  width: 100%;
  height: 40px;
  font-size: 16px;
}
</style>