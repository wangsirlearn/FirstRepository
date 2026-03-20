<script setup>
  import {
  Document,
  EditPen,
  SwitchButton,
  Promotion,
  Menu,
  HomeFilled,
  UserFilled,
  Tools,
  HelpFilled,
  Avatar,
  Histogram,
  InfoFilled,
  Share
  } from '@element-plus/icons-vue'

  // 如果没有配置自动按需引入，那么需要这样手动引入 引入图标组件 按需引入引入的第三方库


  // import RouterView from "vue-router";
  // import keepAlive from 'vue-router/keep-alive'

  

</script>
<template>
    <div class="common-layout">
    <el-container>
      <el-header class="header">
        <span class="title">tlias数据管理系统</span>
        <span class="right_tool">
            <a href="">
                <el-icon><EditPen /></el-icon>修改密码 &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
            </a>
            <a href="">
                <el-icon><SwitchButton /></el-icon>退出登录
            </a>
        </span>
      </el-header>
      <el-container>
        <el-aside width="200px" class="aside" >
          <el-menu router>
              <!-- 首页菜单 -->
              <el-menu-item index="/index" >
                <el-icon><Promotion /></el-icon> 首页
              </el-menu-item>
              
              <!-- 班级管理菜单 -->
              <el-sub-menu index="/manage">
                <template #title>
                  <el-icon><Menu /></el-icon> 班级学员管理
                </template>
                <el-menu-item index="/class">
                  <el-icon><HomeFilled /></el-icon>班级管理
                </el-menu-item>
                <el-menu-item index="/stu">
                  <el-icon><UserFilled /></el-icon>学员管理
                </el-menu-item>
              </el-sub-menu>
              
              <!-- 系统信息管理 -->
              <el-sub-menu index="/system">
                <template #title>
                  <el-icon><Tools /></el-icon>系统信息管理
                </template>
                <el-menu-item index="/dept">
                  <el-icon><HelpFilled /></el-icon>部门管理
                </el-menu-item>
                <el-menu-item index="/emp">
                  <el-icon><Avatar /></el-icon>员工管理
                </el-menu-item>
              </el-sub-menu>
              
              <!-- 数据统计管理 -->
              <el-sub-menu index="/report">
                <template #title>
                  <el-icon><Histogram /></el-icon>数据统计管理
                </template>
                <el-menu-item index="/empReport">
                  <el-icon><InfoFilled /></el-icon>员工信息统计
                </el-menu-item>
                <el-menu-item index="/stuReport">
                  <el-icon><Share /></el-icon>学员信息统计
                </el-menu-item>
                <el-menu-item index="/log">
                  <el-icon><Document /></el-icon>日志信息统计
                </el-menu-item>
              </el-sub-menu>
              </el-menu>
            </el-aside>
            <el-main class="main">
              <!--不需要注册的原因： <router-view> 渲染的是路由匹配到的组件（也就是你在 router/index.js 里配置的 component 对应的组件），这些组件本身会定义 name 选项，这个 name 就是 keep-alive 识别的依据，和 “当前组件是否注册该组件” 无关。 -->
              <router-view v-slot="{ Component }">
                <keep-alive :include="['index', 'class', 'stu', 'dept', 'emp', 'empReport', 'stuReport', 'log']" max="10">
                  <component :is="Component" />
                </keep-alive>
              </router-view>
              

              <!-- <keep-alive :include="['index', 'class', 'stu', 'dept', 'emp', 'empReport', 'stuReport', 'log']" max="10">
                <router-view></router-view>
              </keep-alive>  旧的用法-->
            </el-main>
          </el-container>
    </el-container>
  </div>
</template>
<style scoped>
  *{
    margin: 0;
    box-sizing: border-box;
  }
  
  a{
    text-decoration: none;
  }
  
  .aside{
    width: 220px;
    height: 700px;
    border-right: 1px black solid;
  }
  #app > div > section > section > aside > div > div > ul{
    width: 200px;
    margin-left: 10px;
    border: 0px whitesmoke;
    
  }
  #app > div > section > header{
    transition:linear(whitesmoke);
    line-height: 60px;
    display: flex;
    justify-content: space-between;
  }
  

</style>