// src/router/index.js
import { createRouter, createWebHistory} from 'vue-router'



// 导入你的页面组件（替换成实际组件路径）
import IndexView from '@/views/index.vue'
import ClassView from '@/views/class.vue'
import DeptView from '@/views/dept.vue'
import EmpView from '@/views/emp.vue'
import LogView from '@/views/log.vue'
import StuView from '@/views/stu.vue'
import EmpReportView from '@/views/report/emp.vue'
import StuReportView from '@/views/report/stu.vue'
import LayoutView from '@/layout/index.vue'
import LoginView from '@/views/login.vue'



// 创建路由实例
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL), // vite 项目用这个
  // 如果是 vue-cli 项目，history: createWebHistory(process.env.BASE_URL)
  routes: [
    {
      path: '/',
      name: '',
      component: LayoutView,
      redirect: '/index',
      children: [
        {
          path: 'index',
          name: 'index',
          component: IndexView
        },
        {
          path: 'class',
          name: 'class',
          component: () => import('@/views/class.vue')
        },
        {
          path: 'dept',
          name: 'dept',
          component: () => import('@/views/dept.vue') // 路由懒加载
        },
        {
          path: 'emp',
          name: 'emp',
          component: EmpView
        },
        {
          path: 'log',
          name: 'log',
          component: LogView
        },
        {
          path:'stu',
          name:'stu',
          component: StuView
        },
        {
          path: 'empReport',
          name: 'empReport',
          component: EmpReportView
        },
        {
          path:'stuReport',
          name:'stuReport',
          component: StuReportView
        }
      ]
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView
    }

  ]
})

export default router