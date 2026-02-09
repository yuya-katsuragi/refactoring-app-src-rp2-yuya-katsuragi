package jp.co.sss.crud.main;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import jp.co.sss.crud.io.ConsoleWriter;
import jp.co.sss.crud.io.MenuNoReader;
import jp.co.sss.crud.service.EmployeeAllFindService;
import jp.co.sss.crud.service.EmployeeDeleteService;
import jp.co.sss.crud.service.EmployeeFindByDeptIdService;
import jp.co.sss.crud.service.EmployeeFindByEmpNameService;
import jp.co.sss.crud.service.EmployeeRegisterService;
import jp.co.sss.crud.service.EmployeeUpdateService;
import jp.co.sss.crud.util.ConstantMsg;
import jp.co.sss.crud.util.ConstantValue;

/**
 * 社員情報管理システム開始クラス 社員情報管理システムはこのクラスから始まる。<br/>
 * メニュー画面を表示する。
 *
 * @author System Shared
 *
 */
public class MainSystem {
	/**
	 * 社員管理システムを起動
	 *
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws ParseException 
	 */
	public static void main(String[] args)
			throws IOException, ClassNotFoundException, SQLException, ParseException {
		MenuNoReader menuReader = new MenuNoReader();
		int menuNo = ConstantValue.MENU_TITLE;

		do {
			try {
				// メニューの表示
				ConsoleWriter.showMenu();

				// メニュー番号の入力

				menuNo = menuReader.read();

				// 機能の呼出
				// 3. 機能の呼出 (各Serviceクラスに委譲)
				switch (menuNo) {
				case ConstantValue.MENU_ALL_SEARCH:
					new EmployeeAllFindService().execute();
					break;

				case ConstantValue.MENU_EMPNAME_SEARCH:
					new EmployeeFindByEmpNameService().execute();
					break;

				case ConstantValue.MENU_DEPTID_SEARCH:
					new EmployeeFindByDeptIdService().execute();
					break;

				case ConstantValue.MENU_INSERT:
					new EmployeeRegisterService().execute();
					break;

				case ConstantValue.MENU_UPDATE:
					new EmployeeUpdateService().execute();
					break;

				case ConstantValue.MENU_DELETE:
					new EmployeeDeleteService().execute();
					break;

				case ConstantValue.MENU_EXIT:
					// 終了時は何もしない（ループ条件で判定)
					break;

				}
			} catch (Exception e) {
				// エラーメッセージを表示
				ConsoleWriter.showErrorMsg(e.getMessage());

			}
		} while (menuNo != ConstantValue.MENU_EXIT);

		System.out.println(ConstantMsg.MSG_SYSTEM_EXIT);
	}
}