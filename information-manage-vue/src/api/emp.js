import request from '@/utils/request'

//分页查询员工列表
export const findEmp =(params)=>{
    return request.get('/emps',{params})
}
//条件查询员工列表
export const findEmpByCondition =(params)=>{
    console.log(params);
    
    return request.get('/emps/search',{params})
}
//新增员工
export const addEmp = (data) => {
    console.log('发送新增员工请求，数据:', data)
    return request.post('/emps', data)
}
//根据ID查询员工
export const findEmpById = (id) => {
    return request.get(`/emps/${id.id}`)
}
//修改员工
export const updateEmp = (data) => {
    console.log('发送修改员工请求，数据:', data)
    return request.put('/emps', data)  
}
//删除员工
export const deleteEmp = (id) => {
    console.log('发送删除员工请求，ID:', id)
    return request.delete(`/emps?id=${id}`)
}
//查询员工列表
export const findAllEmp =()=>{
    return request.get('/emps/list')
}