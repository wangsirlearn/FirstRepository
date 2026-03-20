package org.example.controller;


import lombok.extern.slf4j.Slf4j;
import org.example.popj.Clazz;
import org.example.popj.ClazzPageResult;
import org.example.popj.ClazzQuery;
import org.example.popj.Result;
import org.example.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
public class ClazzController {
    @Autowired
    private ClazzService clazzService;
    /**
     * 获取班级信息 分页
     */
    @GetMapping("/clazzs")
    public Result findPage(ClazzQuery clazzQuery){
        ClazzPageResult clazzPageResult = clazzService.findPage(clazzQuery);
        log.info("获取班级信息分页");
        return Result.success(clazzPageResult);
    }
    /**
     * 删除班级
     */
    @DeleteMapping("/clazzs/{id}")
    public Result delete(@PathVariable int id){
        log.debug("删除班级");
        clazzService.deleteClazz(id);
        return Result.success();
    }

    /**
     * 添加班级
     */
    @PostMapping("/clazzs")
    public Result add(@RequestBody Clazz clazz){
        log.debug("添加班级");
        clazzService.addClazz(clazz);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/clazzs/{id}")
    public Result findById(@PathVariable Integer id){
        log.debug("查询班级信息："+id);
        Clazz clazz = clazzService.findById(id);
        return Result.success(clazz);
    }

    /**
     * 修改班级信息
     */
    @PutMapping("/clazzs")
    public Result update(@RequestBody Clazz clazz){
        log.debug("修改班级信息："+clazz);
        clazzService.updateClazz(clazz);
        return Result.success();
    }

    /**
     * 获取所有班级信息
     */
    @GetMapping("/clazzs/list")
    public Result findAll(){
        log.debug("获取所有班级信息");
        List<Clazz> clazzs = clazzService.findAll();
        return Result.success(clazzs);
    }

}
