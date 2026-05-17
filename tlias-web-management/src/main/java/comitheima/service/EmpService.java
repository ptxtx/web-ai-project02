package comitheima.service;

import comitheima.pojo.Emp;
import comitheima.pojo.PageResult;

public interface EmpService {
    PageResult<Emp> page(Integer page, Integer pageSize);
}
