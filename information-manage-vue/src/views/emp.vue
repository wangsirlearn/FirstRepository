<script setup>
    
    import { findEmp,findEmpByCondition,findAllEmp,addEmp,findEmpById,deleteEmp } from '@/api/emp'
    import { onMounted, ref, toRefs } from 'vue'
    import { ElMessage } from 'element-plus' 
    

    const currentPage4 = ref(1)
    const pageSize4 = ref(10)
    const size = ref('default')
    const background = ref(false)
    const disabled = ref(false)
    const total = ref(100)
    onMounted(()=>{
        resetForm()
    })
    //定义查询员工表单数据
    const searchForm = ref({})
    const tableData = ref([])
    
    //重置查询员工表单
    const resetForm = async () => {
        searchForm.value={
            name:'',
            gender:'',
            dateBegin:'',
            dateEnd:''
        }
        const res=await findAllEmp()
        // if()
        console.log(res);
        // 处理部门信息  慢，可以并行查询所有部门信息
        // for (const emp in res.data) {
        //   console.log(emp);
        //     const deptRes = await findDeptById({id:res.data[emp].deptId})
        //     console.log(deptRes);
        //     if (deptRes.data) {
        //         res.data[emp].deptName = deptRes.data.name
        //     } else {
        //         res.data[emp].deptName = '未知部门'
        //     }
        // }
        // 并行查询所有部门信息 还是慢 建议后端优化
        // const deptPromises = res.data.map(emp => 
        //     findDeptById({ id: emp.deptId }).then(deptRes => ({
        //         emp,
        //         deptName: deptRes.data ? deptRes.data.name : '未知部门'
        //     }))
        // )

        // const results = await Promise.all(deptPromises)

        // // 批量更新部门名称
        // results.forEach(({ emp, deptName }) => {
        //     emp.deptName = deptName
        // })// 并行查询所有部门信息

        total.value=res.data.total
        console.log(total.value);
        currentPage4.value=1
        pageSize4.value=res.data.length
        tableData.value=res.data.rows
        // tableData=testData

        // myOnSubmit()
    }
    //条件查询员工表单
    const onSubmit = async () => {
        // 查询时使用当前分页组件的 page 和 pageSize
        pageSize4.value=10
        const params = {
            name: searchForm.value.name,
            gender: searchForm.value.gender,
            dateBegin: searchForm.value.dateBegin,
            dateEnd: searchForm.value.dateEnd,
            page: currentPage4.value,
            pageSize: pageSize4.value
        }
        if(params.gender==='1'){
            params.gender=parseInt(params.gender)
        }else if(params.gender==='2'){
            params.gender=parseInt(params.gender)
        }else{
            params.gender=''
        }
        console.log(params.gender);
        const res=await findEmpByCondition(params)
        console.log('查询员工列表', res)
        console.log(res.data.total);
        total.value=res.data.total
        tableData.value=res.data.rows
    }
    
    // 页码改变时触发
    const handleCurrentChange = (val) => {
        currentPage4.value = val
        // 检查是否有查询条件
        const hasConditions = searchForm.value.name || searchForm.value.gender || searchForm.value.dateBegin || searchForm.value.dateEnd
        
        if(hasConditions){
            // 有条件时使用条件查询函数
            const params = {
                name: searchForm.value.name,
                gender: searchForm.value.gender,
                dateBegin: searchForm.value.dateBegin,
                dateEnd: searchForm.value.dateEnd,
                page: currentPage4.value,
                pageSize: pageSize4.value
            }
            if(params.gender==='1'){
                params.gender=parseInt(params.gender)
            }else if(params.gender==='2'){
                params.gender=parseInt(params.gender)
            }
            findEmpByCondition(params).then(res => {
                console.log(res);
                if(res && res.data){
                    total.value=res.data.total
                    tableData.value=res.data.rows
                }else{
                    total.value=0
                    tableData.value=[]
                }
            }).catch(error => {
                console.error('查询员工失败:', error)
                ElMessage.error('查询失败，请检查网络或联系管理员')
            })
        }else{
            // 无条件时调用普通查询函数
            const params = {
                page: currentPage4.value,
                pageSize: pageSize4.value
            }
            findEmp(params).then(res => {
                console.log(res);
                if(res && res.data){
                    total.value=res.data.total
                    tableData.value=res.data.rows
                }else{
                    total.value=0
                    tableData.value=[]
                }
            }).catch(error => {
                console.error('查询员工失败:', error)
                ElMessage.error('查询失败，请检查网络或联系管理员')
            })
        }
    }
    
    // 每页条数改变时触发
    const handleSizeChange = (val) => {
        pageSize4.value = val
        currentPage4.value = 1 // 重置到第一页
        // 检查是否有查询条件
        const hasConditions = searchForm.value.name || searchForm.value.gender || searchForm.value.dateBegin || searchForm.value.dateEnd
        
        if(hasConditions){
            // 有条件时使用条件查询函数
            const params = {
                name: searchForm.value.name,
                gender: searchForm.value.gender,
                dateBegin: searchForm.value.dateBegin,
                dateEnd: searchForm.value.dateEnd,
                page: currentPage4.value,
                pageSize: pageSize4.value
            }
            if(params.gender==='1'){
                params.gender=parseInt(params.gender)
            }else if(params.gender==='2'){
                params.gender=parseInt(params.gender)
            }
            findEmpByCondition(params).then(res => {
                if(res && res.data){
                    total.value=res.data.total
                    tableData.value=res.data.rows
                }else{
                    total.value=0
                    tableData.value=[]
                }
            }).catch(error => {
                console.error('查询员工失败:', error)
                ElMessage.error('查询失败，请检查网络或联系管理员')
            })
        }else{
            // 无条件时调用普通查询函数
            const params = {
                page: currentPage4.value,
                pageSize: pageSize4.value
            }
            findEmp(params).then(res => {
                if(res && res.data){
                    total.value=res.data.total
                    tableData.value=res.data.rows
                }else{
                    total.value=0
                    tableData.value=[]
                }
            }).catch(error => {
                console.error('查询员工失败:', error)
                ElMessage.error('查询失败，请检查网络或联系管理员')
            })
        }
    }
    //增加员工
    const dialogVisible = ref(false)
    const dialogTitle = ref('增加员工')
    const employee = ref({
        username: '',
        name: '',
        gender: '',
        phone: '',
        job: '',
        salary: '',
        deptId: '',
        entryDate: '',
        image: '',
        exprList: [
            {
                comp: '',
                job: '',
                beginDate: '',
                endDate: ''
            }
        ]
    })
    const saveEmployee=()=>{
        const params={
            username: employee.value.username,
            name: employee.value.name,
            gender: employee.value.gender,
            phone: employee.value.phone,
            job: employee.value.job,
            salary: employee.value.salary,
            deptId: employee.value.deptId,
            entryDate: employee.value.entryDate,
            image: employee.value.image,
            exprList: employee.value.exprList
        }
        addEmp(params).then(res => {
            console.log(res);
            
            if(res.code===1){
                ElMessage.success('增加成功')
                dialogVisible.value = false
                employee.value={
                    username: '',
                    name: '',
                    gender: '',
                    phone: '',
                    job: '',
                    salary: '',
                    deptId: '',
                    entryDate: '',
                    image: '',
                    exprList: [
                        {
                            comp: '',
                            job: '',
                            beginDate: '',
                            endDate: ''
                        }
                    ]
                }
                resetForm()
            //     findEmp({
            //         page: currentPage4.value,
            //         pageSize: pageSize4.value
            //     }).then(res => {
            //         if(res && res.data){
            //             total.value=res.data.total
            //             tableData.value=res.data.rows
            //         }else{
            //             total.value=0
            //             tableData.value=[]
            //         }
            //     }).catch(error => {
            //         console.error('查询员工失败:', error)
            //         ElMessage.error('查询失败，请检查网络或联系管理员')
            //     })
            // }else{
            //     ElMessage.error('增加失败，请检查网络或联系管理员')
            // }
            }
        }).catch(error => {
            console.error('增加员工失败:', error)
            ElMessage.error('增加失败，请检查网络或联系管理员')
        })
    }
    // -----------------------

    // 处理头像上传成功
    const handleAvatarSuccess = (response, uploadFile) => {
        console.log('头像上传成功:', response)
        if(response && response.data){
            employee.value.image = response.data
        }
    }
    
    // 头像上传前的验证
    const beforeAvatarUpload = (file) => {
        const isJPG = file.type === 'image/jpeg' || file.type === 'image/png'
        const isLt2M = file.size / 1024 / 1024 < 2
        
        if (!isJPG) {
            ElMessage.error('上传头像图片只能是 JPG 或 PNG 格式!')
            return false
        }
        if (!isLt2M) {
            ElMessage.error('上传头像图片大小不能超过 2MB!')
            return false
        }
        return true
    }
    // 处理编辑员工
    const handleEdit = async (index,row) => {
        dialogTitle.value = '编辑员工'
        const id=parseInt(row.id)
        // 调用查询员工详情接口
        const res=await findEmpById({id})
        
        console.log(res);
        if(res && res.data){
            // 确保 exprList 存在
            if(!res.data.exprList || !res.data.exprList.length){
                res.data.exprList = [
                    {
                        comp: '',
                        job: '',
                        beginDate: '',
                        endDate: ''
                    }
                ]
            }
            employee.value = res.data
            switch(employee.value.gender){
                case 1:
                    employee.value.gender='男'
                    break;
                case 2:
                    employee.value.gender='女'
                    break;
                default:
                    employee.value.gender=''
            }
            switch(employee.value.job){
                case 1:
                    employee.value.job='讲师'
                    break;
                case 2:
                    employee.value.job='班主任'
                    break;
                default:
                    employee.value.job=''
            }
            switch(employee.value.deptId){
                case 1:
                    employee.value.deptId='开发部'
                    break;
                case 2:
                    employee.value.deptId='测试部'
                    break;
                default:
                    employee.value.deptId=''
            }
            // console.log(employee.value.deptId);
            
        }
        dialogVisible.value = true
        
    }
    // 处理删除员工   ######
    const handleDelete = async (index, row) => {
        const id = parseInt(row.id)
        console.log(id);
        try {
            await deleteEmp({ id })
            ElMessage.success('删除成功')
            // 刷新员工列表
            resetForm()
        } catch (error) {
            console.error('删除员工失败:', error)
            ElMessage.error('删除失败，请检查网络或联系管理员')
        }
    }
</script>
<template>
    <h2>员工管理</h2>
    <el-form :inline="true" :model="searchForm" class="demo-form-inline">
        <el-form-item label="员工姓名">
        <el-input v-model="searchForm.name" placeholder="请输入员工姓名" clearable  style="width: 150px;" />
        </el-form-item>
        <el-form-item label="性别" size="default">
        <el-select
            v-model="searchForm.gender"
            placeholder="请选择"
            clearable
            style="width: 100px;"
        >
            <el-option label="男" value="1" />
            <el-option label="女" value="2" />
        </el-select>
        </el-form-item>
        <el-form-item label="入职时间">
        <el-date-picker
            v-model="searchForm.dateBegin"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="开始时间"
            clearable
        />
        <span style="margin: 0 5px;">至</span>
        <el-date-picker
            v-model="searchForm.dateEnd"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="结束时间"
            clearable
        />
        </el-form-item>
        <el-form-item>
        <el-button type="primary" @click="onSubmit">查询</el-button>
        <el-button type="info" @click="resetForm">重置</el-button>
        </el-form-item>
    </el-form>
    <div class="button-row">
      <el-button type="primary" style="width: 100px; margin:5px 10px;" @click="dialogVisible = true"><el-icon ><Plus color="black"/></el-icon>增加员工</el-button>
      <el-button type="primary" style="width: 100px;"><el-icon ><Delete color="red"/></el-icon>批量删除</el-button>
           
    </div>
    <!-- 表单组件 -->
     <el-table :data="tableData" style="width: 100%">
        <el-table-column type="selection" width="70px" />
        <el-table-column prop="name" label="姓名" width="120" />
        <el-table-column label="性别" width="120">
            <template #default="scope">{{ scope.row.gender === 1 ? '男' : '女' }}</template>
        </el-table-column>
        <el-table-column prop="image" label="头像" width="120" >
            <template #default="scope">
                <img v-if="scope.row.image" :src="scope.row.image" alt="Avatar" class="avatar" style="width: 50px; height: 50px; border-radius: 50%;"  />
                <span v-else style="color: #999;">暂无头像</span>
            </template>
        </el-table-column>
        <el-table-column  label="所属部门" width="120" >
            <template #default="scope">{{ scope.row.deptName==null?'无部门':scope.row.deptName }}</template>
        </el-table-column>
        <el-table-column  label="职位" width="120" >
            <template #default="scope">
          <span v-if="scope.row.job == 1">班主任</span>
          <span v-else-if="scope.row.job == 2">讲师</span>
          <span v-else-if="scope.row.job == 3">学工主管</span>
          <span v-else-if="scope.row.job == 4">教研主管</span>
          <span v-else-if="scope.row.job == 5">咨询师</span>
          <span v-else>其他</span>
      </template>
        </el-table-column>
        <el-table-column prop="createTime" label="入职时间" width="170" >
            <template #default="scope">{{ scope.row.createTime==null?'无入职时间':scope.row.createTime }}</template>
        </el-table-column>
        <el-table-column prop="updateTime" label="最后操作时间" width="170" >
            <template #default="scope">{{ scope.row.updateTime==null?'无操作时间':scope.row.updateTime }}</template>
        </el-table-column>
        <el-table-column  label="操作">
            <template #default="scope">
                <el-button type="primary" @click="handleEdit(scope.$index, scope.row)">
                编辑
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
    <!-- 分页组件 -->
    <div class="demo-pagination-block" style="margin-top: 20px; padding-left: 150px; text-align: center;">
        <el-pagination
        v-model:current-page="currentPage4"
        v-model:page-size="pageSize4"
        :page-sizes="[10, 20, 30,40]"
        :size="size"
        :disabled="disabled"
        :background="background"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        @current-change="handleCurrentChange"
        @size-change="handleSizeChange"
        />
  </div>
   <!-- 添加员工组件 -->
     <!-- 新增/修改员工的对话框 -->
  <el-dialog v-model="dialogVisible" :title="dialogTitle">
      <el-form ref="employeeFormRef" :model="employee" label-width="80px">
        <!-- 基本信息 -->
        <!-- 第一行 -->
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="用户名">
              <el-input v-model="employee.username" placeholder="请输入员工用户名，2-20个字"></el-input>
            </el-form-item>
          </el-col>
          
          <el-col :span="12">
            <el-form-item label="姓名">
              <el-input v-model="employee.name" placeholder="请输入员工姓名，2-10个字"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <!-- 第二行 -->
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="性别">
              <el-select v-model="employee.gender" placeholder="请选择性别" style="width: 100%;">
                <el-option label="男" value="1"></el-option>
                <el-option label="女" value="2"></el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="手机号">
              <el-input v-model="employee.phone" placeholder="请输入员工手机号"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <!-- 第三行 -->
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="职位">
              <el-select v-model="employee.job" placeholder="请选择职位" style="width: 100%;">
                <el-option label="班主任" value="1"></el-option>
                <el-option label="讲师" value="2"></el-option>
                <el-option label="学工主管" value="3"></el-option>
                <el-option label="教研主管" value="4"></el-option>
                <el-option label="咨询师" value="5"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="薪资">
              <el-input v-model="employee.salary" placeholder="请输入员工薪资"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <!-- 第四行 -->
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="所属部门">
              <el-select v-model="employee.deptId" placeholder="请选择部门" style="width: 100%;">
                <el-option label="研发部" value="1"></el-option>
                <el-option label="市场部" value="2"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="入职日期">
              <el-date-picker v-model="employee.entryDate" type="date" style="width: 100%;" placeholder="选择日期" format="YYYY-MM-DD" value-format="YYYY-MM-DD"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>

        <!-- 第五行 -->
        <el-row :gutter="20">
          <el-col :span="24">
            <el-form-item label="头像">
              <el-upload
                class="avatar-uploader"
                action="/api/upload"
                :show-file-list="false"
                :on-success="handleAvatarSuccess"
                :before-upload="beforeAvatarUpload"
                >
                <img v-if="employee.image" :src="employee.image" class="avatar" />
                <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
              </el-upload>
            </el-form-item>
          </el-col>
        </el-row>
        

        <!-- 工作经历 -->
        <!-- 第六行 -->
        <el-row :gutter="10">
          <el-col :span="24">
            <el-form-item label="工作经历">
              <el-button type="success" size="small" @click="">+ 添加工作经历</el-button>
            </el-form-item>
          </el-col>
        </el-row>
        
        <!-- 第七行 ...  工作经历 -->
        <el-row :gutter="3">
          <el-col :span="10">
            <el-form-item size="small" label="时间" label-width="80px" >
              <!-- <el-date-picker type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" format="YYYY-MM-DD" value-format="YYYY-MM-DD" ></el-date-picker> -->
               <el-date-picker v-model="employee.exprList[0].beginDate" type="date" style="width: 100%; margin-bottom: 5px;" placeholder="开始日期" format="YYYY-MM-DD" value-format="YYYY-MM-DD"></el-date-picker>
               <el-date-picker v-model="employee.exprList[0].endDate" type="date" style="width: 100%; margin-top: 5px;" placeholder="结束日期" format="YYYY-MM-DD" value-format="YYYY-MM-DD"></el-date-picker>
            </el-form-item>
          </el-col>

          <el-col :span="6">
            <el-form-item size="small" label="公司" label-width="60px">
              <el-input v-model="employee.exprList[0].comp" placeholder="请输入公司名称"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="6">
            <el-form-item size="small" label="职位" label-width="60px">
              <el-input v-model="employee.exprList[0].job" placeholder="请输入职位"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="2">
            <el-form-item size="small" label-width="0px">
              <el-button type="danger" >- 删除</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      
        <!-- 底部按钮 -->
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
            <el-button type="primary" @click="saveEmployee">保存</el-button>
          </span>
        </template>

    </el-dialog>
</template>
<style scoped>
    .avatar {
        height: 40px;
    }
    .avatar-uploader .avatar {
        width: 78px;
        height: 78px;
        display: block;
    }
    .avatar-uploader .el-upload {
        border: 1px dashed var(--el-border-color);
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
        transition: var(--el-transition-duration-fast);
    }

    .avatar-uploader .el-upload:hover {
        border-color: var(--el-color-primary);
    }

    .el-icon.avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 78px;
        height: 78px;
        text-align: center;
        /* 添加灰色的虚线边框 */
        border: 1px dashed var(--el-border-color);
    }
</style>