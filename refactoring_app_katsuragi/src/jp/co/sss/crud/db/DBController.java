package jp.co.sss.crud.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import jp.co.sss.crud.util.ConstantMsg;
import jp.co.sss.crud.util.ConstantSQL;

/**
 * DB操作処理用のクラス
 *
 * @author System Shared
 */
public class DBController {

	/** インスタンス化を禁止 */
	private DBController() {
	}

	/**
	 * 全ての社員情報を検索
	 *
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException           DB処理でエラーが発生した場合に送出
	 */
	public static void findAll() throws ClassNotFoundException, SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// DBに接続
			connection = DBManager.getConnection();

			// ステートメントを作成
			preparedStatement = connection.prepareStatement(ConstantSQL.SQL_ALL_SELECT);

			// SQL文を実行
			resultSet = preparedStatement.executeQuery();

			//resultSetの結果Setがない場合はfalse
			if (!resultSet.isBeforeFirst()) {
				System.out.println(ConstantMsg.MSG_NOT_FOUND);
				return;
			}

			// レコードを出力
			System.out.println(ConstantMsg.MSG_INFO_LIST);
			while (resultSet.next()) {
				System.out.print(resultSet.getString("emp_id") +
						ConstantMsg.HORIZONTAL_TAB);
				System.out.print(resultSet.getString("emp_name") +
						ConstantMsg.HORIZONTAL_TAB);

				int gender = Integer.parseInt(resultSet.getString("gender"));
				if (gender == ConstantMsg.GENDER_OTHER) {
					System.out.print(ConstantMsg.MSG_NO_ANSWER +
							ConstantMsg.HORIZONTAL_TAB);
				} else if (gender == ConstantMsg.GENDER_MALE) {
					System.out.print(ConstantMsg.MSG_MALE +
							ConstantMsg.HORIZONTAL_TAB);

				} else if (gender == ConstantMsg.GENDER_FEMALE) {
					System.out.print(ConstantMsg.MSG_FAMALE +
							ConstantMsg.HORIZONTAL_TAB);

				} else if (gender == ConstantMsg.GENDER_UNKNOWN) {
					System.out.print(ConstantMsg.MSG_OTHER +
							ConstantMsg.HORIZONTAL_TAB);

				}

				System.out.print(resultSet.getString("birthday") +
						ConstantMsg.HORIZONTAL_TAB);
				System.out.println(resultSet.getString("dept_name"));
			}

			System.out.println("");
		} finally {
			// ResultSetをクローズ
			DBManager.close(resultSet);
			// Statementをクローズ
			DBManager.close(preparedStatement);
			// DBとの接続を切断
			DBManager.close(connection);
		}
	}

	/**
	 * 社員名に該当する社員情報を検索
	 *
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException           DB処理でエラーが発生した場合に送出
	 * @throws IOException            入力処理でエラーが発生した場合に送出
	 */
	public static void findByEmpName() throws ClassNotFoundException, SQLException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 検索ワード
		String searchWord = br.readLine();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// DBに接続
			connection = DBManager.getConnection();

			// SQL文を準備
			StringBuffer sql = new StringBuffer(ConstantSQL.SQL_SELECT_BASIC);
			sql.append(ConstantSQL.SQL_SELECT_BY_EMP_NAME);

			// ステートメントの作成
			preparedStatement = connection.prepareStatement(sql.toString());

			// 検索条件となる値をバインド
			preparedStatement.setString(1, "%" + searchWord + "%");

			// SQL文を実行
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.isBeforeFirst()) {
				System.out.println(ConstantMsg.MSG_NOT_FOUND);
				return;
			}

			System.out.println(ConstantMsg.MSG_INFO_LIST);
			while (resultSet.next()) {
				System.out.print(resultSet.getString("emp_id"));
				System.out.print(ConstantMsg.HORIZONTAL_TAB);

				System.out.print(resultSet.getString("emp_name"));
				System.out.print(ConstantMsg.HORIZONTAL_TAB);

				String genderString = resultSet.getString("gender");
				int gender = Integer.parseInt(genderString);
				if (gender == ConstantMsg.GENDER_OTHER) {
					System.out.print(ConstantMsg.MSG_NO_ANSWER);
				} else if (gender == ConstantMsg.GENDER_MALE) {
					System.out.print(ConstantMsg.MSG_MALE);

				} else if (gender == ConstantMsg.GENDER_FEMALE) {
					System.out.print(ConstantMsg.MSG_FAMALE);

				} else if (gender == ConstantMsg.GENDER_UNKNOWN) {
					System.out.print(ConstantMsg.MSG_OTHER);

				}

				System.out.print(ConstantMsg.HORIZONTAL_TAB);
				System.out.print(resultSet.getString("birthday"));
				System.out.print(ConstantMsg.HORIZONTAL_TAB);

				System.out.println(resultSet.getString("dept_name"));
			}

			System.out.println(ConstantMsg.EMPTY_LINE);

		} finally {
			// クローズ処理
			DBManager.close(resultSet);
			// Statementをクローズ
			DBManager.close(preparedStatement);
			// DBとの接続を切断
			DBManager.close(connection);
		}
	}

	/**
	 * 部署IDに該当する社員情報を検索
	 *
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException           DB処理でエラーが発生した場合に送出
	 * @throws IOException            入力処理でエラーが発生した場合に送出
	 */
	public static void findByDeptId(String deptId) throws ClassNotFoundException, SQLException, IOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// DBに接続
			connection = DBManager.getConnection();

			// SQL文を準備
			StringBuffer sql = new StringBuffer(ConstantSQL.SQL_SELECT_BASIC);
			sql.append(ConstantSQL.SQL_SELECT_BY_DEPT_ID);

			// ステートメントの作成
			preparedStatement = connection.prepareStatement(sql.toString());

			// 検索条件となる値をバインド
			preparedStatement.setInt(1, Integer.parseInt(deptId));

			// SQL文を実行
			resultSet = preparedStatement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				System.out.println(ConstantMsg.MSG_NOT_FOUND);
				return;
			}

			System.out.println(ConstantMsg.MSG_INFO_LIST);
			while (resultSet.next()) {
				System.out.print(resultSet.getString("emp_id"));
				System.out.print(ConstantMsg.HORIZONTAL_TAB);

				System.out.print(resultSet.getString("emp_name"));
				System.out.print(ConstantMsg.HORIZONTAL_TAB);

				String genderString = resultSet.getString("gender");
				int gender = Integer.parseInt(genderString);
				if (gender == ConstantMsg.GENDER_OTHER) {
					System.out.print(ConstantMsg.MSG_NO_ANSWER);
				} else if (gender == ConstantMsg.GENDER_MALE) {
					System.out.print(ConstantMsg.MSG_MALE);

				} else if (gender == ConstantMsg.GENDER_FEMALE) {
					System.out.print(ConstantMsg.MSG_FAMALE);

				} else if (gender == ConstantMsg.GENDER_UNKNOWN) {
					System.out.print(ConstantMsg.MSG_OTHER);

				}

				System.out.print(ConstantMsg.HORIZONTAL_TAB);
				System.out.print(resultSet.getString("birthday"));
				System.out.print(ConstantMsg.HORIZONTAL_TAB);

				String deptIdString = resultSet.getString("dept_id");
				int deptIdInt = Integer.parseInt(deptIdString);
				if (deptIdInt == ConstantMsg.DEPT_SALES) {
					System.out.println(ConstantMsg.MSG_DEPT_SALES);
				} else if (deptIdInt == ConstantMsg.DEPT_ACCOUNTING) {
					System.out.println(ConstantMsg.MSG_DEPT_ACCOUNTING);
				} else if (deptIdInt == ConstantMsg.DEPT_GENERAL_AFFAIRS) {
					System.out.println(
							ConstantMsg.MSG_DEPT_GENERAL_AFFAIRS);

				}
			}

			System.out.println(ConstantMsg.EMPTY_LINE);
		} finally {
			// クローズ処理
			DBManager.close(resultSet);
			// Statementをクローズ
			DBManager.close(preparedStatement);
			// DBとの接続を切断
			DBManager.close(connection);
		}
	}

	/**
	 * 社員情報を1件登録
	 * 
	 * @param empName 社員名
	 * @param gender 性別
	 * @param birthday 生年月日
	 * @param deptId 部署ID
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException            DB処理でエラーが発生した場合に送出
	 * @throws IOException             入力処理でエラーが発生した場合に送出
	 * @throws ParseException 
	 */
	public static void insert(String empName, String gender, String birthday, String deptId)
			throws ClassNotFoundException, SQLException, IOException, ParseException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			// DBに接続
			connection = DBManager.getConnection();

			// ステートメントを作成
			preparedStatement = connection.prepareStatement(ConstantSQL.SQL_INSERT);

			// 入力値をバインド
			preparedStatement.setString(1, empName);
			preparedStatement.setInt(2, Integer.parseInt(gender));
			SimpleDateFormat sdf = new SimpleDateFormat(
					ConstantMsg.FORMAT_DATE);
			preparedStatement.setObject(3, sdf.parse(birthday), Types.DATE);
			preparedStatement.setInt(4, Integer.parseInt(deptId));

			// SQL文を実行
			preparedStatement.executeUpdate();

			// 登録完了メッセージを出力
			System.out.println(ConstantMsg.MSG_INSERT_COMPLETE);
		} finally {
			DBManager.close(preparedStatement);
			DBManager.close(connection);
		}
	}

	/**
	 * 社員情報を1件更新
	 * 
	 * @param empId 社員ID
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException            DB処理でエラーが発生した場合に送出
	 * @throws IOException             入力処理でエラーが発生した場合に送出
	 * @throws ParseException 
	 */
	public static void update(String empId)
			throws ClassNotFoundException, SQLException, IOException, ParseException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			// データベースに接続
			connection = DBManager.getConnection();

			// ステートメントの作成
			preparedStatement = connection.prepareStatement(ConstantSQL.SQL_UPDATE);

			System.out.print(ConstantMsg.GUIDE_EMPID);
			String empName = br.readLine();
			// 性別を入力
			System.out.print(ConstantMsg.GUIDE_GENDER);
			String gender = br.readLine();
			// 誕生日を入力
			System.out.print(ConstantMsg.GUIDE_BIRTHDAY);
			String birthday = br.readLine();

			// 部署IDを入力
			System.out.print(ConstantMsg.GUIDE_DEPTID);
			String deptId = br.readLine();

			// 入力値をバインド
			preparedStatement.setString(1, empName);
			preparedStatement.setInt(2, Integer.parseInt(gender));
			SimpleDateFormat sdf = new SimpleDateFormat(
					ConstantMsg.FORMAT_DATE);
			preparedStatement.setObject(3, sdf.parse(birthday), Types.DATE);
			preparedStatement.setInt(4, Integer.parseInt(deptId));
			preparedStatement.setInt(5, Integer.parseInt(empId));

			// SQL文の実行(失敗時は戻り値0)
			preparedStatement.executeUpdate();

		} finally {
			// クローズ処理
			DBManager.close(preparedStatement);
			// DBとの接続を切断
			DBManager.close(connection);
		}
	}

	/**
	 * 社員情報を1件削除
	 *
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException           DB処理でエラーが発生した場合に送出
	 * @throws IOException            入力処理でエラーが発生した場合に送出
	 */
	public static void delete() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			// データベースに接続
			connection = DBManager.getConnection();
			String empId = br.readLine();

			// ステートメントの作成
			preparedStatement = connection.prepareStatement(ConstantSQL.SQL_DELETE);

			// 社員IDをバインド
			preparedStatement.setInt(1, Integer.parseInt(empId));

			// SQL文の実行(失敗時は戻り値0)
			preparedStatement.executeUpdate();

			System.out.println(ConstantMsg.MSG_DELETE_COMPLETE);

		} catch (Exception e) {
			e.printStackTrace();

		}

		finally {
			// Statementをクローズ
			try {
				DBManager.close(preparedStatement);
				DBManager.close(connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// DBとの接続を切断
		}
	}
}
