package comitheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/*
分页查询结果封装类

前端给后端传递：当前页码；每页展示记录数

后端返回：总记录数；当前页数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {
    private Long total;//总记录数
    private List<T> rows;//当前页数据
}
