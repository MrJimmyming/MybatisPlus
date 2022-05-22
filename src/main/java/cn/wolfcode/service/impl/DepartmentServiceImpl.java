package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Department;
import cn.wolfcode.mapper.DepartmentMapper;
import cn.wolfcode.qo.QueryDepartment;
import cn.wolfcode.service.IDepartmentService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    @Override
    public Page<Department> getPage(QueryDepartment queryDepartment) {
       return super.page(new Page<Department>(queryDepartment.getCurrentPage(),queryDepartment.getPageSize()),
                Wrappers.<Department>lambdaQuery().like(StringUtils.hasText(queryDepartment.getKeyword()),Department::getName,queryDepartment.getKeyword()))
       ;
    }
}
