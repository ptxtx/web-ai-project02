package comitheima.mapper;

import comitheima.pojo.Emp;
import comitheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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


   @Options(useGeneratedKeys = true,keyProperty = "id")//插入成功后，将主键值回填到emp对象中
   @Insert("insert into emp(username,name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)" +
           "values(#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
   public void insert(Emp emp);


    void deleteByIds(List<Integer> ids);

    Emp getById(Integer id);

    void updateById(Emp emp);

    /*
    * 统计员工职位人数
     */
    @MapKey("pos")//指定pos作为key
    List<Map<String,Object>> countEmpJobData();

    /*
    * 统计员工性别人数
     */
    @MapKey("gender")
    List<Map<String, Object>> countEmpGenderData();


    @Select("select id,username,name from emp where username=#{username} and password=#{password}")
    Emp selectByUsernameAndPassword(Emp emp);
}
