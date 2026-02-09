package jp.co.sss.crud.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sss.crud.dto.Department;
import jp.co.sss.crud.dto.Employee;
import jp.co.sss.crud.exception.SystemErrorException;
import jp.co.sss.crud.util.ConstantMsg;
import jp.co.sss.crud.util.ConstantSQL;

/**
 * 社員情報管理DAOクラス
 * データベースへの接続、SQLの実行、DTOへのデータ詰め替えを担当し
 * IEmployeeDAOインターフェースを実装。
 */
public class EmployeeDAO implements IEmployeeDAO {

	/**
	 * ResultSetからEmployeeオブジェクトを生成する
	 * @param resultSet 実行結果を保持するResultSet
	 * @return Employee 部署情報（Department）を含んだ社員DTO
	 * @throws SQLException SQL実行エラー時にスロー
	 */
	private Employee createEmployee(ResultSet resultSet) throws SQLException {
		Department department = new Department();
		department.setDeptId(resultSet.getInt("dept_id"));
		department.setDeptName(resultSet.getString("dept_name"));

		return new Employee(
				resultSet.getInt("emp_id"),
				resultSet.getString("emp_name"),
				resultSet.getInt("gender"),
				resultSet.getString("birthday"),
				department);
	}

	/**
	 * 社員情報を全件取得する。
	 * @return List<Employee> 全社員のリスト
	 * @throws SystemErrorException 接続失敗等エラー時に独自例外としてスロー
	 */
	@Override
	public List<Employee> findAll() throws SystemErrorException {
		List<Employee> employees = new ArrayList<>();
		try (Connection connection = DBManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ConstantSQL.SQL_ALL_SELECT);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				employees.add(createEmployee(resultSet));
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new SystemErrorException(ConstantMsg.SYSTEM_ERROR_MSG, e);
		}
		return employees;
	}

	/**
	 * 指定された名前（部分一致）で社員を検索する。
	 * @param searchName 検索キーワード
	 * @return List<Employee> 該当する社員のリスト
	 * @throws SystemErrorException システムエラー時にスロー
	 */
	@Override
	public List<Employee> findByEmployeeName(String searchName) throws SystemErrorException {
		List<Employee> employees = new ArrayList<>();

		// SQL文を結合して準備
		StringBuffer sql = new StringBuffer(ConstantSQL.SQL_SELECT_BASIC);
		sql.append(ConstantSQL.SQL_SELECT_BY_EMP_NAME);

		try (Connection connection = DBManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())) {

			preparedStatement.setString(1, "%" + searchName + "%");

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					employees.add(createEmployee(resultSet));
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new SystemErrorException(ConstantMsg.SYSTEM_ERROR_MSG, e);
		}
		return employees;
	}

	/**
	 * 指定された部署IDで社員を検索する。
	 * @param deptId 部署ID
	 * @return List<Employee> 該当する社員のリスト
	 * @throws SystemErrorException システムエラー時にスロー
	 */
	@Override
	public List<Employee> findByDeptId(int deptId) throws SystemErrorException {
		List<Employee> employees = new ArrayList<>();

		// SQL文を結合して準備
		StringBuffer sql = new StringBuffer(ConstantSQL.SQL_SELECT_BASIC);
		sql.append(ConstantSQL.SQL_SELECT_BY_DEPT_ID);

		try (Connection connection = DBManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())) {

			preparedStatement.setInt(1, deptId);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					employees.add(createEmployee(resultSet));
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new SystemErrorException(ConstantMsg.SYSTEM_ERROR_MSG, e);
		}
		return employees;
	}

	/**
	 * @param employee 登録する社員情報
	 * @throws SystemErrorException システムエラー時にスロー
	 */
	@Override
	public void insert(Employee employee) throws SystemErrorException {
		try (Connection connection = DBManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ConstantSQL.SQL_INSERT)) {
			preparedStatement.setString(1, employee.getEmpName());
			preparedStatement.setInt(2, employee.getGender());
			preparedStatement.setDate(3, employee.getBirthdayAsDate());
			preparedStatement.setInt(4, employee.getDepartment().getDeptId());
			preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			throw new SystemErrorException(ConstantMsg.SYSTEM_ERROR_MSG, e);
		}
	}

	/**
	 * 社員情報を更新する。
	 * @param employee 更新する社員情報
	 * @return Integer 更新された行数
	 * @throws SystemErrorException システムエラー時にスロー
	 */
	@Override
	public Integer update(Employee employee) throws SystemErrorException {
		try (Connection connection = DBManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ConstantSQL.SQL_UPDATE)) {
			// パラメータを順番通りに設定
			preparedStatement.setString(1, employee.getEmpName());
			preparedStatement.setInt(2, employee.getGender());
			preparedStatement.setDate(3, employee.getBirthdayAsDate());
			preparedStatement.setInt(4, employee.getDepartment().getDeptId());
			preparedStatement.setInt(5, employee.getEmpId());
			return preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			throw new SystemErrorException(ConstantMsg.SYSTEM_ERROR_MSG, e);
		}
	}

	/**
	 * 社員情報を削除する。
	 * @param empId 削除対象の社員ID
	 * @return Integer 削除された行数
	 * @throws SystemErrorException システムエラー時にスロー
	 */
	@Override
	public Integer delete(Integer empId) throws SystemErrorException {
		try (Connection connection = DBManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ConstantSQL.SQL_DELETE)) {
			preparedStatement.setInt(1, empId);
			return preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			throw new SystemErrorException(ConstantMsg.SYSTEM_ERROR_MSG, e);
		}
	}
}