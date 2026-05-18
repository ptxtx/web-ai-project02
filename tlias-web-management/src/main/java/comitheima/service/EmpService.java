package comitheima.service;

import comitheima.pojo.Emp;
import comitheima.pojo.EmpQueryParam;
import comitheima.pojo.PageResult;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public interface EmpService {
//    PageResult<Emp> page(Integer page, Integer pageSize,String name, Integer gender,
//                      LocalDate begin,LocalDate end);
PageResult<Emp> page(EmpQueryParam eqp);
}
