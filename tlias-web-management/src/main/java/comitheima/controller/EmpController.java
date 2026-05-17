package comitheima.controller;

import comitheima.pojo.Emp;
import comitheima.pojo.PageResult;
import comitheima.pojo.Result;
import comitheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam (defaultValue = "10") Integer pageSize){
        log.info("分页查询："+page+","+pageSize);
        PageResult<Emp> pageResult=empService.page(page,pageSize);
        return Result.success(pageResult);
    }
}
