import request from '@/utils/request'

//查询部门列表
export const findDept =()=>{
    return request.get('/depts')
}
//新增部门
export const addDept = (data) => {
    console.log('发送新增部门请求，数据:', data)
    return request.post('/depts', data)
}
//根据部门ID查询部门
export const findDeptById = (id) => {
    return request.get(`/depts/${id.id}`)
}
//修改部门
export const updateDept = (data) => {
    console.log('发送修改部门请求，数据:', data)
    return request.put('/depts', data)  
}
//删除部门
export const deleteDept = (id) => {
    return request.delete(`/depts?id=${id.id}`)
}
