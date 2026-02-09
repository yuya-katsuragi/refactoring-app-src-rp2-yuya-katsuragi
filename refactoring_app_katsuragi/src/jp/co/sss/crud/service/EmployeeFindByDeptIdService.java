package jp.co.sss.crud.service;

import java.util.List;

import jp.co.sss.crud.db.EmployeeDAO;
import jp.co.sss.crud.dto.Employee;
import jp.co.sss.crud.io.ConsoleWriter;
import jp.co.sss.crud.io.EmployeeDeptIdReader;

/**
 * 部署IDの入力処理を担当するクラス
 * （BufferedReaderを使用して実装）
 */
public class EmployeeFindByDeptIdService {
	/**
	 * コンソールから入力を受け取り、int型に変換して返す
	 * @return 入力された部署ID
	 * @throws IOException 入力エラー時に発生
	 * @throws NumberFormatException 数値以外が入力された場合に発生
	 */
	/**
	 * @throws Exception IOExceptionやNumberFormatExceptionなどをMainへ投げる
	 */
	public void execute() throws Exception {
		ConsoleWriter.showGuideDeptId();

		EmployeeDeptIdReader reader = new EmployeeDeptIdReader();
		int deptId = reader.read();

		EmployeeDAO dao = new EmployeeDAO();
		List<Employee> employees = dao.findByDeptId(deptId);

		ConsoleWriter.showEmployees(employees);
	}
}