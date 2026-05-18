package comitheima.mapper;

import comitheima.pojo.Emp;
import comitheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

    //------------------------原始分页查询实现-----------
//    @Select("SELECT COUNT(*) FROM emp")
//    public Long count();
//    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id " +
//            "order by e.update_time desc limit #{start},#{pageSize};")//Emp中不能封装d.name  所以得增加属性
//    public List<Emp> list(Integer start, Integer pageSize);

//        @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id " +
//            "order by e.update_time desc")//Emp中不能封装d.name  所以得增加属性


//        public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);
   public List<Emp> list(EmpQueryParam eqp);



}
