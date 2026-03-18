<template>
  <div class="login-container">
    <div class="login-box">
      <div class="login-header">
        <img src="@/assets/logo.svg" alt="logo" class="logo" />
        <h2>校园班牌管理系统</h2>
        <p>School Class Board Management System</p>
      </div>
      
      <el-form ref="loginFormRef" :model="loginForm" :rules="loginRules" class="login-form">
        <el-form-item prop="username">
          <el-input
            v-model="loginForm.username"
            placeholder="请输入账号"
            prefix-icon="User"
            size="large"
          />
        </el-form-item>
        
        <el-form-item prop="password">
          <el-input
            v-model="loginForm.password"
            type="password"
            placeholder="请输入密码"
            prefix-icon="Lock"
            size="large"
            show-password
            @keyup.enter="handleLogin"
          />
        </el-form-item>
        
        <el-form-item>
          <el-checkbox v-model="loginForm.remember">记住密码</el-checkbox>
        </el-form-item>
        
        <el-form-item>
          <el-button
            type="primary"
            size="large"
            :loading="loading"
            class="login-btn"
            @click="handleLogin"
          >
            登 录
          </el-button>
        </el-form-item>
      </el-form>
      
      <div class="login-footer">
        <p>默认账号: admin / 123456</p>
      </div>
    </div>
  </div>
</template>

<script setup>
/**
 * 登录页面
 * 
 * @author School System
 * @version 1.0.0
 */
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'

const router = useRouter()
const loginFormRef = ref(null)
const loading = ref(false)

const loginForm = reactive({
  username: 'admin',
  password: '123456',
  remember: false
})

const loginRules = {
  username: [
    { required: true, message: '请输入账号', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' }
  ]
}

const handleLogin = async () => {
  if (!loginFormRef.value) return
  
  await loginFormRef.value.validate(async (valid) => {
    if (valid) {
      loading.value = true
      
      try {
        // TODO: 调用登录API
        // 模拟登录
        setTimeout(() => {
          localStorage.setItem('token', 'mock-token-' + Date.now())
          localStorage.setItem('username', loginForm.username)
          ElMessage.success('登录成功')
          router.push('/')
          loading.value = false
        }, 1000)
      } catch (error) {
        loading.value = false
        ElMessage.error('登录失败，请检查账号密码')
      }
    }
  })
}
</script>

<style lang="scss" scoped>
.login-container {
  width: 100%;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  
  .login-box {
    width: 400px;
    padding: 40px;
    background: #fff;
    border-radius: 10px;
    box-shadow: 0 10px 40px rgba(0, 0, 0, 0.2);
    
    .login-header {
      text-align: center;
      margin-bottom: 30px;
      
      .logo {
        width: 60px;
        height: 60px;
        margin-bottom: 15px;
      }
      
      h2 {
        font-size: 24px;
        color: #333;
        margin-bottom: 10px;
      }
      
      p {
        font-size: 14px;
        color: #999;
      }
    }
    
    .login-form {
      .login-btn {
        width: 100%;
      }
    }
    
    .login-footer {
      text-align: center;
      margin-top: 20px;
      
      p {
        font-size: 12px;
        color: #999;
      }
    }
  }
}
</style>
