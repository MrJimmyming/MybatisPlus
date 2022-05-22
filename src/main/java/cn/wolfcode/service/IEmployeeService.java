package cn.wolfcode.service;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.qo.QueryEmployee;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IEmployeeService extends IService<Employee> {
    Page<Employee> getPage(QueryEmployee queryEmployee);



}
