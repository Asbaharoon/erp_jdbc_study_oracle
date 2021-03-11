package erp_jdbc_study_oracle.dao;

import java.sql.Connection;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import erp_jdbc_study_oracle.dao.impl.DepartmentDaoImpl;
import erp_jdbc_study_oracle.ds.OracleDataSource;
import erp_jdbc_study_oracle.dto.Department;
import erp_jdbc_study_oracle.util.LogUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentDaoTest {
	private DepartmentDao dao;
	private Connection con;
	
	@Before
	public void setUp() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		dao = DepartmentDaoImpl.getInstance();
		con = OracleDataSource.getConnection();
	}

	@After
	public void tearDown() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		dao = null;
		con = null;
	} 
	
	@Test
	public void test01SelectDepartmentByAll() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		List<Department> list = dao.selectDepartmentByAll(con);
		Assert.assertNotNull(list);
		for(Department d : list) LogUtil.prnLog(d);
	}
	
	@Test
	public void test02SelectDepartmentByNo() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = dao.selectDepartmentByNo(con, new Department(1));
		Assert.assertNotNull(department);
		LogUtil.prnLog(department);
	}

	@Test
	public void test03InsertDepartment() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		int res = dao.insertDepartment(con, new Department(5, "마케팅", 30));
		Assert.assertEquals(1, res);
		LogUtil.prnLog(String.format("A new Department with id %d has been inserted.",res));
		for(Department d : dao.selectDepartmentByAll(con))LogUtil.prnLog(d);
	}
	
	@Test
	public void test04UpdateDepartment() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		int res = dao.updateDepartment(con, new Department(5, "모바일개발", 60));
		Assert.assertEquals(1, res);
		LogUtil.prnLog(String.format("A Department with id %d has been updated.", res));
		for(Department d : dao.selectDepartmentByAll(con))LogUtil.prnLog(d);
	}
	
	@Test
	public void test05DeleteDepartment() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		int res = dao.deleteDepartment(con, new Department(5));
		Assert.assertEquals(1, res);
		LogUtil.prnLog(String.format("A new Department with id %d has been deleted.",res));
		for(Department d : dao.selectDepartmentByAll(con))LogUtil.prnLog(d);
	}


}
