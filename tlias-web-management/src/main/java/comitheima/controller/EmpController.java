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
}
