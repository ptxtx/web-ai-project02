package comitheima.controller;

import comitheima.pojo.Emp;
import comitheima.pojo.EmpQueryParam;
import comitheima.pojo.PageResult;
import comitheima.pojo.Result;
import comitheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/emps")
@RestController
@Slf4j
public class EmpController {
    @Autowired
    private EmpService empService;
    /*
    * 查询员工分页数据
     */
    @GetMapping
//    public Result page(@RequestParam(defaultValue = "1") Integer page,
//                       @RequestParam (defaultValue = "10") Integer pageSize,
//                       String name, Integer gender,
//                       @DateTimeFormat (pattern = "yyyy-MM-dd") LocalDate begin,//要根据前端传过来的日期格式 说明pattern 格式进行转换
//                       @DateTimeFormat (pattern = "yyyy-MM-dd") LocalDate end){
//        log.info("分页查询:{},{},{},{},{},{}",page,pageSize,name,gender,begin,end);
//        PageResult<Emp> pageResult=empService.page(page,pageSize,name,gender,begin,end);
//        return Result.success(pageResult);
//    }
    public Result page(EmpQueryParam eqp) {
        log.info("分页查询:{}",eqp);
        PageResult<Emp> pageResult=empService.page(eqp);
        return Result.success(pageResult);
    }
    @PostMapping
    public Result sava(@RequestBody Emp emp){//将前端给的JSON格式的数据封装到对象中
        log.info("新增员工：{}",emp);
        empService.save(emp);
        return Result.success();
    }

    @DeleteMapping
    public Result delete( @RequestParam List<Integer> ids){// 如果参数传递的是List集合，则形参需要RequestParam注解
        log.info("批量删除员工：{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    @GetMapping("/{id}")//代表查询
    public Result getInfo(@PathVariable Integer id){//将路径的{id} 绑定给形参
        log.info("查询员工id为：{}",id);
        Emp emp=empService.getInfo(id);//返回emp 这样才能输出出来 响应数据
        return Result.success(emp);
    }

    @PutMapping//修改员工
    public Result update(@RequestBody Emp emp){
        log.info("修改员工：{}",emp);
        empService.update(emp);
        return Result.success();
    }





}
