package comitheima.mapper;

import comitheima.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpExprMapper {
    /*
    批量保存员工的工作经历
     */
    public void insertBatch(List<EmpExpr> exprList);
}
