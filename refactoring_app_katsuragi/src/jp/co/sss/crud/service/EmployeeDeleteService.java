package jp.co.sss.crud.service;

import jp.co.sss.crud.db.EmployeeDAO;
import jp.co.sss.crud.io.ConsoleWriter;
import jp.co.sss.crud.io.EmployeeEmpIdReader;

public class EmployeeDeleteService {

	public void execute() throws Exception {

		EmployeeEmpIdReader reader = new EmployeeEmpIdReader();

		// インスタンス、メソッドを呼ぶ
		int empId = reader.read();

		//DAOを呼び削除実行
		EmployeeDAO dao = new EmployeeDAO();
		int resultCount = dao.delete(empId);

		if (resultCount > 0) {
			ConsoleWriter.showMsgDeleteComplete(resultCount);
		} else {
			ConsoleWriter.showMsgTargetFound();

		}
	}

}
