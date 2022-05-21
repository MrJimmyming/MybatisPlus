package cn.wolfcode.service;

import cn.wolfcode.Employee;
import cn.wolfcode.qo.queryEmployee;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IEmployeeService extends IService<Employee> {
    Page<Employee> getPage(queryEmployee queryEmployee);



}
