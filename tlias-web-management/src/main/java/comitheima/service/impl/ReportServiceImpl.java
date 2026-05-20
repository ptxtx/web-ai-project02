package comitheima.service.impl;

import comitheima.mapper.EmpMapper;
import comitheima.pojo.JobOption;
import comitheima.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public JobOption getEmpJobData(){
        //1.调用Mapper接口，获取统计数据
        List<Map<String, Object>> list = empMapper.countEmpJobData();

        //2.组装结果并返回
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();
        return new JobOption(jobList,dataList);
    }

    @Override
    public List<Map<String,Object>> getEmpGenderData(){
        return empMapper.countEmpGenderData();
    }
}
