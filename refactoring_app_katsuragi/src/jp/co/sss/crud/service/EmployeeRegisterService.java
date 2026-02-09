package jp.co.sss.crud.service;

import jp.co.sss.crud.db.EmployeeDAO;
import jp.co.sss.crud.dto.Department;
import jp.co.sss.crud.dto.Employee;
import jp.co.sss.crud.io.ConsoleWriter;
import jp.co.sss.crud.io.EmployeeBirthdayReader;
import jp.co.sss.crud.io.EmployeeDeptIdReader;
import jp.co.sss.crud.io.EmployeeGenderReader;
import jp.co.sss.crud.io.EmployeeNameReader;

public class EmployeeRegisterService {
	public void execute() throws Exception {

		ConsoleWriter.showGuideEmpName();
		String empName = new EmployeeNameReader().read();

		ConsoleWriter.showGuideGender();
		int gender = new EmployeeGenderReader().read();

		ConsoleWriter.showGuideBirthday();
		String birthday = new EmployeeBirthdayReader().read();

		ConsoleWriter.showGuideDeptId();
		int deptId = new EmployeeDeptIdReader().read();

		Employee employee = new Employee();

		employee.setEmpName(empName);
		employee.setGender(gender);
		employee.setBirthday(birthday);

		Department dept = new Department();
		dept.setDeptId(deptId);
		employee.setDepartment(dept);

		EmployeeDAO employeeDAO = new EmployeeDAO();
		employeeDAO.insert(employee);

		ConsoleWriter.showMsgInsertComplete();

	}

}
