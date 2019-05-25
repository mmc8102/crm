package cn.wolfcode.crm2.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.wolfcode.crm2.domain.Employee;
import cn.wolfcode.crm2.query.QueryObject;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

	Employee getEmployeeForLogin(@Param("username")String username, @Param("password")String password);

	Long queryForCount(QueryObject qo);
	List<Employee> queryForList(QueryObject qo);

	void updateState(Long id);

	void insertRelation(@Param("eid")Long eid, @Param("rid")Long rid);

	List<Long> queryByEid(Long id);

	void deleteRelation(Long id);

	List<Employee> queryForDept();
}