package jp.co.sss.crud.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;

import jp.co.sss.crud.db.DBController;
import jp.co.sss.crud.util.ConstantMsg;

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
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int menuNo = 0;

		do {
			// メニューの表示
			System.out.println(ConstantMsg.MENU_TITLE);
			System.out.println(ConstantMsg.MENU_ALL);
			System.out.println(ConstantMsg.MENU_BY_EMPID);
			System.out.println(ConstantMsg.MENU_BY_DEPTID);
			System.out.println(ConstantMsg.MENU_BY_INSERT);
			System.out.println(ConstantMsg.MENU_BY_UPDATE);
			System.out.println(ConstantMsg.MENU_BY_DELETE);
			System.out.println(ConstantMsg.MENU_BY_EXIT);
			System.out.print(ConstantMsg.MENU_BY_NUMBER);

			// メニュー番号の入力
			String menuNoStr = br.readLine();
			menuNo = Integer.parseInt(menuNoStr);

			// 機能の呼出
			switch (menuNo) {
			case ConstantMsg.MENU_ALL_SEARCH:
				// 全件表示機能の呼出
				DBController.findAll();
				break;

			case ConstantMsg.MENU_EMPID_SEARCH:
				// 社員名検索
				System.out.print(ConstantMsg.GUIDE_EMPID);

				// 検索機能の呼出
				DBController.findByEmployeeName();
				break;

			case ConstantMsg.MENU_DEPTID_SEARCH:
				// 検索する部署IDを入力
				System.out.print(ConstantMsg.GUIDE_DEPTID);
				String deptId = br.readLine();

				// 検索機能の呼出
				DBController.findByDeptId(deptId);
				break;

			case ConstantMsg.MENU_INSERT:
				// 登録する値を入力
				System.out.print(ConstantMsg.GUIDE_EMPID);
				String empName = br.readLine();
				System.out.print(ConstantMsg.GUIDE_GENDER);
				String gender = br.readLine();
				System.out.print(ConstantMsg.GUIDE_BIRTHDAY);
				String birthday = br.readLine();
				System.out.print(ConstantMsg.GUIDE_DEPTID);
				String insertDeptId = br.readLine();

				// 登録機能の呼出
				DBController.insert(empName, gender, birthday, insertDeptId);
				break;

			case ConstantMsg.MENU_UPDATE:
				// 更新する社員IDを入力
				System.out.print(ConstantMsg.MSG_UPDATE_DEPTID);

				// 更新する値を入力する
				String updateEmpId = br.readLine();
				Integer.parseInt(updateEmpId);

				// 更新機能の呼出
				DBController.update(updateEmpId);
				System.out.println(ConstantMsg.MSG_UPDATE_COMPLETE);

				break;

			case ConstantMsg.MENU_DELETE:
				// 削除する社員IDを入力
				System.out.print(ConstantMsg.MSG_DELETE_DEPTID);

				// 削除機能の呼出
				DBController.delete();
				break;

			}
		} while (menuNo != ConstantMsg.MENU_EXIT);

		System.out.println(ConstantMsg.MSG_SYSTEM_EXIT);
	}
}
