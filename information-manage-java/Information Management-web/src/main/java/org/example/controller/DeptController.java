package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.example.popj.Dept;
import org.example.popj.Result;
import org.example.service.DeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j // 可以代替 Logger log=
@RestController
public class DeptController {
    // 日志
//    private static final Logger log= LoggerFactory.getLogger(DeptController.class);

    /*
    * 获取所有部门信息
     */

    @Autowired
    private DeptService deptService;

    @RequestMapping(value = "/depts", method = RequestMethod.GET)
//    @GetMapping("/depts") 可代替上面的
    public Result findAll(){
        List<Dept> depts = deptService.findAll();
        return Result.success(depts);
        // createTime updateTime 没有被封装 因为属性名和字段名不一致

    }

    /*
    * 删除部门信息
     */
    @DeleteMapping(value = "/depts")
    // 接受参数的方法 1.使用HttpServletRequest
//    public Result deleteDept(HttpServletRequest request){
//        int id = Integer.parseInt(request.getParameter("id"));
//        List<Dept> depts = deptService.deleteDept(id);
//        System.out.println("删除部门信息：id="+id);
//        return Result.success();
//    }

    //  2.使用@RequestParam 若缺少参数 则返回错误 400
      // 若请求的字段名称和方法的参数名一致 则可以省略 @RequestParam（）
    public Result deleteDept(@RequestParam(value = "id", required = false) int id){
        deptService.deleteDept(id);
        log.debug("删除部门信息：id="+id); // 使用日志
//        System.out.println("删除部门信息：id="+id);
        return Result.success();
    }

    /*
    * 添加部门信息
     */
    @PostMapping(value = "/depts")
     //接受json格式的参数，使用实体对象来接受
    public Result addDept(@RequestBody Dept dept){
        deptService.addDept(dept);
//        System.out.println("增加部门："+dept);
        log.debug("增加部门："+dept);
        return Result.success(dept);
    }

    /**
    * 查询部门信息 by id
     */
    @GetMapping(value = "/depts/{id}")
    // 接受路径参数 @PathVariable
    //若多个路径参数 也可以使用 @PathVariable int a, @PathVariable int b
    public Result findById(@PathVariable int id){
        Dept dept = deptService.findById(id);
        log.debug("查询部门信息：{}", id);
        return Result.success(dept);
    }

    /*
     * 修改部门信息
     */
    @PutMapping(value = "/depts")
    public Result updateDept(@RequestBody Dept dept){
        deptService.updateDept(dept);
        return Result.success();
    }



/*
    System.out.println("增加部门："+dept); 是用来记录日志的
    日志：就是应用程序运行过程中所发生的事件信息
    日志可以用来1，数据追踪 2，性能优化 3，问题排查 4，系统监控 等
    日志技术：1，java.util.logging（jul） 2，log4j (支持多种输出目标)
    3，logback （推荐） 4，slf4j 提供了接口和抽象类，是一种规范，具体实现由logback完成
    logback的使用：1，导入logback的jar包 2，创建logback.xml文件
    3，使用logback的api

 */

}
