package comitheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import comitheima.mapper.EmpExprMapper;
import comitheima.mapper.EmpMapper;
import comitheima.pojo.*;
import comitheima.service.EmpLogService;
import comitheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;//事务的注解在service层
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

    @Transactional(rollbackFor ={ Exception.class})//事务管理的注解
    @Override
    public void save(Emp emp){
        //两个表都需要插入
        try {
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);


            List<EmpExpr> exprList = emp.getExprList();
            if(!CollectionUtils.isEmpty(exprList)){
                //遍历集合，为empId赋值
                exprList.forEach(expr->expr.setEmpId(emp.getId()));//这里能get到emp的id是因为在empMapper中使用了Options注解，先获取到了emp的id
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            EmpLog empLog = new EmpLog(null,LocalDateTime.now(),"新增员工"+emp);
            empLogService.insertLog(empLog);//不管上面的操作成功与否，都要记录操作日志，所以用try...finally 然后try作为事务，一荣俱荣
            //与上面的代码在同一个事务中，如果回滚，则都会回滚，所以需要对插入日志开启新事务
        }


    }

    @Transactional(rollbackFor ={ Exception.class})
    @Override
    public void delete(List<Integer> ids){
        //1.批量删除员工的基本信息
        empMapper.deleteByIds(ids);

        //2.批量删除员工对应的员工入职信息
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getInfo(Integer id){
        return empMapper.getById(id);
    }

    @Transactional(rollbackFor ={ Exception.class})
    @Override
    public void update(Emp emp){
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        //2.根据ID修改员工的工作经历信息
        //2.1 先删除原有的工作经历
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));//这里是把emp的id转换成list类型 因为之前写过delete方法，其形参为List<Integer>

        //2.2 再添加这个员工新的工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            //遍历集合，为empId赋值
            exprList.forEach(expr->expr.setEmpId(emp.getId()));//设定这段工作经历归属于哪个员工
            empExprMapper.insertBatch(exprList);
        }
    }

}
