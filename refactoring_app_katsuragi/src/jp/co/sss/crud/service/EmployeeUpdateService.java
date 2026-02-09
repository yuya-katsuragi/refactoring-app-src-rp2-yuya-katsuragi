package jp.co.sss.crud.service;

import jp.co.sss.crud.db.EmployeeDAO;
import jp.co.sss.crud.dto.Department;
import jp.co.sss.crud.dto.Employee;
import jp.co.sss.crud.io.ConsoleWriter;
import jp.co.sss.crud.io.EmployeeBirthdayReader;
import jp.co.sss.crud.io.EmployeeDeptIdReader;
import jp.co.sss.crud.io.EmployeeEmpIdReader;
import jp.co.sss.crud.io.EmployeeGenderReader;
import jp.co.sss.crud.io.EmployeeNameReader;

public class EmployeeUpdateService {
	public void execute() throws Exception {
		// 1. 更新対象の社員IDを入力
		ConsoleWriter.showUpdeteEmpId(); // 「更新する社員IDを入力してください」
		int empId = new EmployeeEmpIdReader().read();

		// 2. 更新後の情報を入力
		ConsoleWriter.showGuideEmpName();
		String empName = new EmployeeNameReader().read();

		ConsoleWriter.showGuideGender();
		int gender = new EmployeeGenderReader().read();

		ConsoleWriter.showGuideBirthday();
		String birthday = new EmployeeBirthdayReader().read();

		ConsoleWriter.showGuideDeptId();
		int deptId = new EmployeeDeptIdReader().read();

		// 3. DTOの組み立て (デフォルトコンストラクタを使用)
		Employee employee = new Employee();
		employee.setEmpId(empId);
		employee.setEmpName(empName);
		employee.setGender(gender);
		employee.setBirthday(birthday);

		Department dept = new Department();
		dept.setDeptId(deptId);
		employee.setDepartment(dept);

		// 4. DAOの実行
		EmployeeDAO dao = new EmployeeDAO();
		int resultCount = dao.update(employee);

		// 5. 結果表示
		if (resultCount > 0) {
			ConsoleWriter.showMsgUpdateComplete(resultCount);
		} else {
			ConsoleWriter.showMsgTargetFound();
		}
	}
}