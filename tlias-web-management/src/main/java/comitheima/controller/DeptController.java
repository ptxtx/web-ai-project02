package comitheima.controller;

import comitheima.pojo.Dept;
import comitheima.pojo.Result;
import comitheima.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;//面向接口开发
    @RequestMapping(value = "/depts",method= RequestMethod.GET)//写成GetMapping 底层封装了method=GET
    public Result list(){//返回类型为给前端响应的数据
        System.out.println("查询全部部门数据");
       List<Dept> deptList= deptService.findAll();
       return Result.success(deptList);
    }

    @DeleteMapping("/depts")
    //删除需要接收请求参数 id
    public Result delete(HttpServletRequest  request){
        String id = request.getParameter("id");
        System.out.println("删除id为"+id+"的部门数据");
        Integer id1= Integer.parseInt(id);
        deptService.delete(id1);
        return Result.success();
    }

    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept){
        System.out.println("添加部门数据"+dept);
        deptService.add(dept);
        return Result.success();
    }

    @GetMapping("/depts/{id}")
    public Result getInfo(@PathVariable("id")Integer deptId){
        System.out.println("查询id为"+deptId+"的部门数据");
        Dept dept=deptService.getById(deptId);
        return Result.success(dept);
    }

    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept){
        System.out.println("修改部门数据"+dept);
        deptService.update(dept);
        return Result.success();
    }

}
