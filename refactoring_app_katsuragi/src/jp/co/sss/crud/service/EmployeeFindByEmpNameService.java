package jp.co.sss.crud.service;

import java.util.List;

import jp.co.sss.crud.db.EmployeeDAO;
import jp.co.sss.crud.dto.Employee;
import jp.co.sss.crud.io.ConsoleWriter;
import jp.co.sss.crud.io.EmployeeNameReader;

public class EmployeeFindByEmpNameService {

	public void execute() throws Exception {

		ConsoleWriter.showGuideEmpName();
		EmployeeNameReader reader = new EmployeeNameReader();
		String empName = reader.read();

		EmployeeDAO employeeDAO = new EmployeeDAO();
		List<Employee> employees = employeeDAO.findByEmployeeName(empName);

		ConsoleWriter.showEmployees(employees);
	}

}
