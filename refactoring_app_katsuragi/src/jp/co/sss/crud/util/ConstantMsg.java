package jp.co.sss.crud.util;

public class ConstantMsg {

	//メニュー番号
	public static final int MENU_TITLE = 0;
	public static final int MENU_ALL_SEARCH = 1;
	public static final int MENU_EMPID_SEARCH = 2;
	public static final int MENU_DEPTID_SEARCH = 3;
	public static final int MENU_INSERT = 4;
	public static final int MENU_UPDATE = 5;
	public static final int MENU_DELETE = 6;
	public static final int MENU_EXIT = 7;

	public static final int GENDER_OTHER = 0;
	public static final int GENDER_MALE = 1;
	public static final int GENDER_FEMALE = 2;
	public static final int GENDER_UNKNOWN = 9;

	public static final int DEPT_SALES = 1;
	public static final int DEPT_ACCOUNTING = 2;
	public static final int DEPT_GENERAL_AFFAIRS = 3;

	// メニューの表示
	public static final String MENU_BY_TITLE = "=== 社員管理システム ===";
	public static final String MENU_ALL = "1.全件表示";
	public static final String MENU_BY_EMPID = "2.社員名検索";
	public static final String MENU_BY_DEPTID = "3.部署ID検索";
	public static final String MENU_BY_INSERT = "4.新規登録";
	public static final String MENU_BY_UPDATE = "5.更新";
	public static final String MENU_BY_DELETE = "6.削除";
	public static final String MENU_BY_EXIT = "7.終了";
	public static final String MENU_BY_NUMBER = "メニュー番号を入力してください：";

	public static final String MSG_NOT_FOUND = "該当者はいませんでした";

	public static final String MSG_INSERT_COMPLETE = "社員情報を登録しました";

	public static final String MSG_UPDATE_COMPLETE = "社員情報を更新しました";

	public static final String MSG_DELETE_COMPLETE = "社員情報を削除しました";

	//新規登録時メッセージ
	public static final String GUIDE_EMPID = "社員名：";
	public static final String GUIDE_GENDER = "性別(0:回答なし, 1:男性, 2:女性, 9:その他):";
	public static final String GUIDE_BIRTHDAY = "生年月日(西暦年/月/日):";
	public static final String GUIDE_DEPTID = "部署ID(1:営業部、2:経理部、3:総務部):";

	public static final String MSG_UPDATE_DEPTID = "更新する社員の社員IDを入力してください：";

	public static final String MSG_DELETE_DEPTID = "削除する社員の社員IDを入力してください：";

	public static final String MSG_SYSTEM_EXIT = "システムを終了します。";

	public static final String MSG_INFO_LIST = "社員ID\t社員名\t性別\t生年月日\t部署名";
	//空文字スペース用
	public static final String HORIZONTAL_TAB = "\t";
	public static final String EMPTY_LINE = "";

	//性別選択メッセージ
	public static final String MSG_NO_ANSWER = "回答なし";
	public static final String MSG_MALE = "男性";
	public static final String MSG_FAMALE = "女性";
	public static final String MSG_OTHER = "その他";

	//部署名メッセージ
	public static final String MSG_DEPT_SALES = "営業部";
	public static final String MSG_DEPT_ACCOUNTING = "経理部";
	public static final String MSG_DEPT_GENERAL_AFFAIRS = "総務部";

	public static final String FORMAT_DATE = "yyyy/MM/dd";

	{

	}

}
