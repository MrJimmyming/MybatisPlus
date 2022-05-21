package cn.wolfcode;

import cn.wolfcode.mapper.EmployeeMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.naming.Name;
import java.sql.Wrapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class queryTest {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    public void queryTest(){
        QueryWrapper<Employee> query = Wrappers.<Employee>query();
        query.select("name","age");
        System.out.println(employeeMapper.selectList(query));
    }
    @Test
    public void queryTest2(){
        QueryWrapper<Employee> query = Wrappers.<Employee>query();
        //query.select()
        query.select(Employee.class,tableFieldInfo -> tableFieldInfo.getProperty().startsWith("a"));
        List<Map<String, Object>> maps = employeeMapper.selectMaps(query);
        List<Employee> employees = employeeMapper.selectList(query);
        employees.forEach(System.out::println);
    }
    @Test
    public void queryOrderByTest2(){
        QueryWrapper<Employee> query = Wrappers.<Employee>query();
       query.orderByAsc("age","id");
       //开关 是否顺序排序 排序的列
        query.orderBy(true,true,"age");
    }
    @Test
    public void queryGroupByTest(){
        //需求： 以部门id进行分组查询，查每个部门员工个数
        //havingSql直接写
        //QueryWrapper<Employee> query = Wrappers.<Employee>query().groupBy("dept_id")
               // .select("dept_id 部门,count(id) 数量").having("count(id) > '3'");
        //havingSql 使用占位符
        //人数4-5之间
        //SELECT dept_id 部门,count(id) 数量 FROM employee WHERE delete_statue=0 GROUP BY dept_id HAVING count(id) >= ? and count(id) <= ?
        QueryWrapper<Employee> query = Wrappers.<Employee>query().groupBy("dept_id")
                .select("dept_id 部门,count(id) 数量").having("count(id) >= {0} and count(id) <= {1}",4,5);
        System.out.println(employeeMapper.selectMaps(query));
    }

    @Test
    public  void queryByCondition(){
        //查询一个等于条件
        //SELECT id,name,password,email,age,admin,dept_id,delete_statue FROM employee WHERE delete_statue=0 AND (name = ?)
       employeeMapper.selectList(Wrappers.<Employee>query().eq("name","dafei")).forEach(System.out::println);
        System.out.println("======================================");
        //eqal必须满足所有条件 null Is Null 是否忽略nulll false 忽略
        //SELECT id,name,password,email,age,admin,dept_id,delete_statue FROM employee WHERE delete_statue=0 AND (name IS NULL AND age = ?)
       HashMap<String, Object> map = new HashMap<>();
        map.put("name",null);
        map.put("age",25);
        employeeMapper.selectList(Wrappers.<Employee>query().allEq(map,false)).forEach(System.out::println);
    }
    @Test
    public  void queryByGt(){
        //查询一个大于条件
        employeeMapper.selectList(
                //gt >  ge >=
                //SELECT id,name,password,email,age,admin,dept_id,delete_statue FROM employee WHERE delete_statue=0 AND (age > ?)
                Wrappers.<Employee>query().ge("age",25)).forEach(System.out::println);
        System.out.println("===================");
        employeeMapper.selectList(
                //is null
                //SELECT id,name,password,email,age,admin,dept_id,delete_statue FROM employee WHERE delete_statue=0 AND (age IS NOT NULL)
                              Wrappers.<Employee>query().isNotNull("age")).forEach(System.out::println);
        System.out.println("===================");
        employeeMapper.selectList(
                //in notIn
                //SELECT id,name,password,email,age,admin,dept_id,delete_statue FROM employee WHERE delete_statue=0 AND (age IN (?,?))
                Wrappers.<Employee>query().in("age",40,35)).forEach(System.out::println);
        System.out.println("===================");
        employeeMapper.selectList(
                //insql 直接拼接 不再预处理
                //SELECT id,name,password,email,age,admin,dept_id,delete_statue FROM employee WHERE delete_statue=0 AND (age IN (35,25,54))
                Wrappers.<Employee>query().inSql("age","35,25,54")).forEach(System.out::println);
    }

    @Test
    public void testLike(){
       // 需求： 查询name中含有fei字样的员工
        employeeMapper.selectList(
                //like 自动补充 %值%
                //Preparing: SELECT id,name,password,email,age,admin,dept_id,delete_statue FROM employee WHERE delete_statue=0 AND (name LIKE ?)
                //%fei%(String)
                Wrappers.<Employee>query().like("name","fei")).forEach(System.out::println);
        employeeMapper.selectList(
                //likeLeft 左边补充 %值
                //Preparing: SELECT id,name,password,email,age,admin,dept_id,delete_statue FROM employee WHERE delete_statue=0 AND (name LIKE ?)
                //%fei(String)
                Wrappers.<Employee>query().likeLeft("name","fei")).forEach(System.out::println);
    }
    @Test
    public void orTest(){
        employeeMapper.selectList(
                //SELECT id,name,password,email,age,admin,dept_id,delete_statue FROM employee WHERE delete_statue=0 AND (name LIKE ? OR name = ?)
        Wrappers.<Employee>query().likeLeft("name","fei").or().eq("name","admin")
                ).forEach(System.out::println);
        System.out.println("===========================");
        employeeMapper.selectList(
                //SELECT id,name,password,email,age,admin,dept_id,delete_statue FROM employee WHERE delete_statue=0 AND ((name LIKE ? OR name = ?))
                //注意括号 or()传入内部类的区别
                Wrappers.<Employee>query().or(query-> query.likeLeft("name","fei").or().eq("name","admin")))
        .forEach(System.out::println);
    }

}
