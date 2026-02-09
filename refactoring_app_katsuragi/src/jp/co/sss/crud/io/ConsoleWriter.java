package jp.co.sss.crud.io;

import java.util.List;

import jp.co.sss.crud.dto.Employee;
import jp.co.sss.crud.util.ConstantMsg;

public class ConsoleWriter {

	public static void showMenu() {
		/**
		 * メニューの表示
		 */
		System.out.println(ConstantMsg.MENU_BY_TITLE);
		System.out.println(ConstantMsg.MENU_ALL);
		System.out.println(ConstantMsg.MENU_BY_EMPID);
		System.out.println(ConstantMsg.MENU_BY_DEPTID);
		System.out.println(ConstantMsg.MENU_BY_INSERT);
		System.out.println(ConstantMsg.MENU_BY_UPDATE);
		System.out.println(ConstantMsg.MENU_BY_DELETE);
		System.out.println(ConstantMsg.MENU_BY_EXIT);
		System.out.println(ConstantMsg.MENU_BY_NUMBER);

	}

	/**
	 * 社員名の表示
	 */

	public static void showGuideEmpName() {

		System.out.println(ConstantMsg.GUIDE_EMPID);
	}

	/**
	 * 性別選択の表示
	 */

	public static void showGuideGender() {

		System.out.println(ConstantMsg.GUIDE_GENDER);

	}

	/**
	 * 誕生日入力時の入力方法の記載
	 */

	public static void showGuideBirthday() {

		System.out.println(ConstantMsg.GUIDE_BIRTHDAY);
	}

	/**
	 * 検索する部署IDを入力
	 */
	public static void showGuideDeptId() {

		System.out.println(ConstantMsg.GUIDE_DEPTID);

	}

	public static void showEmployees(List<Employee> employees) {
		if (employees.isEmpty()) {
			showMsgNotFound();
		} else {
			showHeader();
			for (Employee employee : employees) {

				System.out.println(employee);

			}
			System.out.println(ConstantMsg.EMPTY_LINE);

		}

	}

	/**
	 * 該当者がいない場合エラーメッセージを表示
	 */

	public static void showMsgNotFound() {
		System.out.println(ConstantMsg.MSG_NOT_FOUND);

	}

	/**
	 * 対象者がいない場合エラーメッセージを表示
	 */
	public static void showMsgTargetFound() {
		System.out.println(ConstantMsg.MSG_TARGET_FOUND);

	}

	//社員情報入力成功メッセージ
	public static void showMsgInsertComplete() {
		System.out.println(ConstantMsg.MSG_INSERT_COMPLETE);
	}
	//社員情報更新成功メッセージ

	public static void showMsgUpdateComplete(int updateComplete) {
		System.out.println(ConstantMsg.MSG_UPDATE_COMPLETE);
	}

	//社員情報削除成功メッセージ
	public static void showMsgDeleteComplete(int deleteComplete) {
		System.out.println(ConstantMsg.MSG_DELETE_COMPLETE);
	}

	//社員情報一覧取得メッセージ
	public static void showHeader() {
		System.out.println(ConstantMsg.MSG_INFO_LIST);
	}

	//社員情報削除ID確認メッセージ
	public static void showDeleteEmpId() {
		System.out.println(ConstantMsg.MSG_DELETE_EMPID);
	}

	//更新社員情報確認メッセージ
	public static void showUpdeteEmpId() {
		System.out.println(ConstantMsg.MSG_UPDATE_EMPID);
	}

	/**
	 * @param msg
	 * エラーメッセージ
	 */
	public static void showErrorMsg(String msg) {
		System.out.println(ConstantMsg.SYSTEM_ERROR_MSG);

		System.out.println(msg);

	}
}
