<script setup>
    
    import { ref, onMounted } from 'vue'
    import axios from 'axios'
    import { ElMessage } from 'element-plus'
    import {findDept,addDept} from '@/api/dept'    
    import { findDeptById, updateDept, deleteDept } from '../api/dept'
    // import { de } from 'element-plus/es/locale/index.mjs'
    
    // import { ElDialog } from 'element-plus'
    // import { ElForm, ElFormItem, ElInput } from 'element-plus'
    // import { ElButton } from 'element-plus'
    // import { ElTable, ElTableColumn } from 'element-plus'
    // import { ElMessage } from 'element-plus'
    

    

    const clusterName = ref('')
    const deptList= ref();
    const centerDialogVisible = ref(false)
    const input = ref('')
    const confirmButton=ref('')
    const deptForm = ref(null)
    const currentDeptId = ref(null)  // 存储当前操作的部门ID
    //钩子函数
    onMounted(()=>{
        search()
    })
    // 搜索部门列表
    // const search = async ()=>{
    //     const dept = await axios.get('https://m1.apifoxmock.com/m1/7837906-7586570-default/depts')
    //     console.log(dept.data);
    //     console.log(dept);
    //     if (dept.data.code) { // JS隐式类型转换 0为false 其余数字是true  "" null undefined都是false
    //         deptList.value = dept.data.data
    //     }
    // }
      //优化后的部门列表搜索函数
      const search = async ()=>{
        const dept = await findDept()
        console.log(dept.data);
        console.log(dept);
        if (dept.code) { // JS隐式类型转换 0为false 其余数字是true  "" null undefined都是false
            deptList.value = dept.data
        }
    };
    const save = () => {
        if (confirmButton.value === '确认新增') {
            confirmAdd()
        } else if (confirmButton.value === '确认修改') {
            confirmEdit()
        } else if (confirmButton.value === '确认删除') {
            confirmDelete()
        }
    }
    //新增部门
    
    const add = async () => {
        clusterName.value = "新增部门"
        confirmButton.value = '确认新增'
        centerDialogVisible.value = true
    };
    //确认新增部门函数
    const confirmAdd = async () => {
        // 先验证输入是否为空
        if (!input.value || input.value.trim() === '') {
            alert('请输入部门名称')
            return
        }
        
        try {
            // 传递对象格式 { name: '部门名称' }
            let res = await addDept({ name: input.value })
            console.log('API响应:', res);
            
            if (res.code) {
                ElMessage.success('新增部门成功')
                centerDialogVisible.value = false
                input.value = '' // 清空输入框
                search() // 刷新列表
            } else {
                ElMessage.error('新增部门失败: ' + (res.msg || '未知错误'))
            }
        } catch (error) {
            console.error('请求错误:', error)
            ElMessage.error('请求失败，请检查网络或联系管理员')
        }
    }
   
    // 根据部门ID查询部门 并显示到弹窗中 
    const handleEdit = (index, row) => {
        const queryDeptById = async () => {
            const {id} = row
            currentDeptId.value = id  // 保存当前部门ID
            const res = await findDeptById({ id })
            if (res.code) {
                clusterName.value = "修改部门"
                confirmButton.value = '确认修改'
                centerDialogVisible.value = true
                input.value = res.data.name // 显示当前部门名称
            } else {
                ElMessage.error('修改失败: ' + (res.msg || '未知错误'))
            }
        }
        queryDeptById()

    }
    
    //修改部门
    const confirmEdit = async () => {
        // 验证输入和部门ID
        if (!input.value || input.value.trim() === '') {
            ElMessage.error('请输入部门名称')
            return
        }
        if (!currentDeptId.value) {
            ElMessage.error('未选择要修改的部门')
            return
        }
        
        try {
            // 传递对象格式 { id: 部门ID, name: '部门名称' }
            let res = await updateDept({ id: currentDeptId.value, name: input.value })
            console.log('API响应:', res);
            
            if (res.code) {
                ElMessage.success('修改部门成功')
                centerDialogVisible.value = false
                input.value = '' // 清空输入框
                currentDeptId.value = null // 清空当前部门ID
                search() // 刷新列表
            } else {
                ElMessage.error('修改部门失败: ' + (res.msg || '未知错误'))
            }
        } catch (error) {
            ElMessage.error('请求失败，请检查网络或联系管理员')
        }
    }
    // 根据部门ID查询部门 并显示到弹窗中 
    
    const handleDelete = (index, row) => {
         const queryDeptById = async () => {
            const {id} = row
            currentDeptId.value = id  // 保存当前部门ID
            const res = await findDeptById({ id })
            if (res.code) {
                clusterName.value = "删除部门"
                confirmButton.value = '确认删除'
                centerDialogVisible.value = true
                input.value = res.data.name // 显示当前部门名称
            } else {
                ElMessage.error('删除失败: ' + (res.msg || '未知错误'))
            }
        }
        queryDeptById()
    }
    // 删除部门
    const confirmDelete = async () => {
        // 验证是否有选中的部门ID
        if (!currentDeptId.value) {
            ElMessage.error('未选择要删除的部门')
            return
        }
        
        try {
            const res = await deleteDept({id: currentDeptId.value})
            console.log('API响应:', res);
            
            if (res.code) {
                ElMessage.success('删除部门成功')
                centerDialogVisible.value = false
                input.value = '' // 清空输入框
                currentDeptId.value = null // 清空当前部门ID
                search() // 刷新列表
            } else {
                ElMessage.error('修改部门失败: ' + (res.msg || '未知错误'))
            }
        } catch (error) {
            ElMessage.error('请求失败，请检查网络或联系管理员')
        }
    }
    
    
</script>
<template>
    <h2>部门管理</h2>
    <div class="button-example">
        <div class="button-row">
            <!-- <el-button type="primary">新增部门</el-button> -->
             <el-button type="primary" plain @click="add()">
                    新增部门
                </el-button>

                <el-dialog
                    v-model="centerDialogVisible"
                    :title="clusterName"
                    width="500"
                    align-center
                >
                    <!-- <span>
                        <span style="margin-right: 10px;">部门名称：</span>
                        <el-input v-model="input" style="width: 240px" placeholder="请输入部门名称"  class="input-deptName"/>
                   </span> -->
                   <el-form :model="deptForm"  ref="deptFormRef">
                        <el-form-item label="部门名称" prop="name" label-width="80px">
                            <el-input v-model="input" autocomplete="off" placeholder="请输入部门名称"  class="input-deptName"></el-input>
                        </el-form-item>
                    </el-form>
                    <template #footer>
                    <div class="dialog-footer">
                        <el-button @click="centerDialogVisible = false">取消</el-button>
                        <el-button type="primary" @click="save()">
                        {{ confirmButton }}
                        </el-button>
                    </div>
                    </template>
                </el-dialog>
                
                
        </div>
    </div>
    <div>
        <el-table :data="deptList" stripe style="width: 100%" class="table-deptList">
            <el-table-column type="index" label="ID" width="100" align="center" />
            <el-table-column prop="name" label="部门名称" width="300" align="center" />
            <el-table-column prop="createTime" label="创建时间" width="300" align="center" />
            <el-table-column prop="updateTime" label="更新时间" width="300" align="center" />
            <el-table-column label="操作" align="center">
                <template #default="scope">
                    <el-button type="primary" @click="handleEdit(scope.$index, scope.row)">
                    修改
                    </el-button>
                    <el-button
                    type="danger"
                    @click="handleDelete(scope.$index, scope.row)"
                    >
                    删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
    

</template>
<style scoped>
    .button-example {
    display: flex;
    flex-direction: column;
    gap: 1rem;
    }

    .button-row {
    display: flex;
    flex-wrap: wrap;
    gap: 1rem;
    align-items: center;
    }

    .button-row > * {
    margin: 0;
    }
    .table-deptList{
        margin-top: 20px;
    }
    
</style>