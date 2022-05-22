package cn.wolfcode.service;

import cn.wolfcode.domain.Department;
import cn.wolfcode.qo.QueryDepartment;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IDepartmentService extends IService<Department> {
    Page<Department> getPage(QueryDepartment queryDepartment);



}
