package comitheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import comitheima.mapper.EmpMapper;
import comitheima.pojo.Emp;
import comitheima.pojo.EmpQueryParam;
import comitheima.pojo.PageResult;
import comitheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    //--------------------原始分页查询
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize){
//        Long total = empMapper.count();
//
//        Integer start = (page-1)*pageSize;
//        List<Emp> rows = empMapper.list(start, pageSize);
//        return new PageResult<Emp>(total,rows);
//    }
    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end){
//        //1.设置分页参数
//        PageHelper.startPage(page,pageSize);
//        //2.执行查询
//        List<Emp> empList = empMapper.list(name,gender,begin,end);
//
//        Page<Emp> p=(Page<Emp>) empList;
//        return new PageResult<Emp>(p.getTotal(),p.getResult());
//    }
    public PageResult<Emp> page(EmpQueryParam eqp){
        //1.设置分页参数
        PageHelper.startPage(eqp.getPage(),eqp.getPageSize());
        //2.执行查询
        List<Emp> empList = empMapper.list(eqp);

        Page<Emp> p=(Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(),p.getResult());
    }

}
