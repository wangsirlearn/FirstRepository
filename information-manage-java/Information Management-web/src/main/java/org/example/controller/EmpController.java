package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.Utils.OSSUtil;
import org.example.popj.Emp;
import org.example.popj.EmpQuery;
import org.example.popj.PageResult;
import org.example.popj.Result;
import org.example.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    @Autowired
    private OSSUtil ossUtil;


    /**
    * 查询员工信息 分页查询
     */

    @GetMapping("/emps")
    public Result findSplit(@RequestParam int page,@RequestParam int pageSize){
        //page:当前页码 pageSize:每页显示的记录数
        log.debug("分页查询员工信息：page="+page+" pageSize="+pageSize);
        PageResult emps = empService.findSplit(page, pageSize);
        return Result.success(emps);
    }
    /**
     * 条件分页查询
     */
//    @GetMapping("/emps/search")
//    public Result findSplit(String name,int gender, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end, @RequestParam int page, @RequestParam int pageSize){
//        // @DateTimeFormat(pattern = "yyyy-MM-dd") 规定格式
//
//        //请求参数太多了 ，使用对象封装 进行优化
//        log.debug("条件分页查询员工信息：name="+name+" gender="+gender+" begin="+begin+" end="+end+" page="+page+" pageSize="+pageSize);
//        PageResult emps = empService.findSplitSpecial(name, gender, begin, end, page, pageSize);
//        return Result.success(emps);
//    }

    @GetMapping("/emps/search")
    public Result findSplit(EmpQuery  query){
        // @DateTimeFormat(pattern = "yyyy-MM-dd") 规定格式

        //请求参数太多了 ，使用对象封装 进行优化
        log.info("条件分页查询员工信息："+query);
        PageResult emps = empService.findSplitSpecial(query);
        return Result.success(emps);
    }

    /**
     * 添加员工信息
     */
    @PostMapping("/emps")
    public Result save(@RequestBody Emp emp){
        log.debug("添加员工信息："+emp);
        empService.save(emp);
        return Result.success();
    }

    /**
     * 上传文件 本地存储
     */
//    @PostMapping("/upload")
//    public Result upload(@RequestParam("file") MultipartFile file) throws Exception {
//        log.debug("上传文件："+file.getOriginalFilename());
//        //获取文件名字
//        String name = file.getOriginalFilename();
//        //获得新的文件名 文件名一致会覆盖
//        String newName = UUID.randomUUID().toString()+"."+name.substring(name.lastIndexOf("."));
//        //保存文件 本地存储  直接将文件保存到服务器的本地磁盘
//        file.transferTo(new File("D:/upload/"+newName));
//        //Spring boot 默认上传文件大小为1M
//        return Result.success();
//    }
    /**
      * 上传文件 阿里云 OSS
      */
    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) throws Exception {
        log.debug("上传文件：" + file.getOriginalFilename());
        //file.getBytes() 获取文件字节数组
        String url = ossUtil.upload(file.getBytes(), file.getOriginalFilename());
        // 返回文件访问路径
        return Result.success(url);
    }

    /**
     * 批量删除员工数据
     */
    @DeleteMapping("/emps")
    public Result remove(@RequestParam List<Integer> ids){
        log.debug("批量删除员工信息："+ids);
        empService.remove(ids);
        return Result.success();
    }


    /**
     * 根据id查询员工数据
     */
    @GetMapping("/emps/{id}")
    public Result findById(@PathVariable Integer id){
        log.debug("查询员工信息："+id);
        Emp emp = empService.findById(id);
        return Result.success(emp);
    }

    /**
     * 修改员工数据
     */
    @PutMapping("/emps")
    public Result update(@RequestBody Emp emp){
        log.debug("修改员工信息："+emp);
        empService.update(emp);
        return Result.success();
    }

    /**
     * 查询所有员工数据
     */
    @GetMapping("/emps/list")
    public Result findAll(){

        log.info("查询所有员工信息");
        List<Emp> emps = empService.findAll();
        log.info("查询结果："+emps);
        return Result.success(emps);
    }

}
