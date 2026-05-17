package comitheima.mapper;

import comitheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
//    @Results({
//            @Result(column = "create_time", property = "createTime"),
//            @Result(column = "update_time", property = "updateTime")
//    })//或者在sql语句中直接起别名
    @Select("SELECT id, name, create_time, update_time FROM dept order by update_time desc;")
    List<Dept> findAll();

    @Delete("DELETE FROM dept WHERE id=#{id};")
    void delete(Integer id);

    @Insert("INSERT INTO dept(name, create_time, update_time) VALUES (#{name},#{createTime},#{updateTime});")
    void add(Dept dept);

    @Select("SELECT * FROM dept WHERE id= #{id};")
    Dept getById(Integer id);

    @Update("UPDATE dept SET name=#{name}, update_time=#{updateTime} WHERE id=#{id};")
    void update(Dept dept);
}
