package comitheima.service;

import comitheima.pojo.Emp;
import comitheima.pojo.EmpQueryParam;
import comitheima.pojo.LoginInfo;
import comitheima.pojo.PageResult;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
//    PageResult<Emp> page(Integer page, Integer pageSize,String name, Integer gender,
//                      LocalDate begin,LocalDate end);
    PageResult<Emp> page(EmpQueryParam eqp);

    void save(Emp emp);

    void delete(List<Integer> ids);//批量删除员工信息

    Emp getInfo(Integer id);

    void update(Emp emp);

    LoginInfo login(Emp emp);
}
