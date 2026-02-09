package jp.co.sss.crud.dto;

import jp.co.sss.crud.util.ConstantMsg;

public class Employee {

	private Integer empId;
	private String empName;
	private Integer gender;
	private String birthday;
	private Department department;

	/**
	 * @param empId
	 * @param empName
	 * @param gender
	 * @param birthday
	 * @param department
	 */
	public Employee(Integer empId, String empName, Integer gender, String birthday, Department department) {
		this.empId = empId;
		this.empName = empName;
		this.gender = gender;
		this.birthday = birthday;
		this.department = department;
	}

	/**
	 * @return empId
	 */
	public Integer getEmpId() {
		return empId;
	}

	/**
	 * @param empId セットする empId
	 */
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	/**
	 * @return empName
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * @param empName セットする empName
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * @return gender
	 */
	public Integer getGender() {
		return gender;
	}

	/**
	 * @param gender セットする gender
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}

	/**
	 * @return birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday セットする birthday
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * @param department セットする department
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		String gender_ja = "";
		if (this.gender == ConstantMsg.GENDER_MALE) {
			gender_ja = "男性";
		} else if (this.gender == ConstantMsg.GENDER_FEMALE) {
			gender_ja = "女性";
		} else if (this.gender == ConstantMsg.GENDER_OTHER) {
			gender_ja = "回答なし";
		} else if (this.gender == ConstantMsg.GENDER_UNKNOWN) {
			gender_ja = "その他";
		}

		return empId + "\t" + empName + "\t" + gender_ja + "\t" + birthday
				+ "\t" + department.getDeptName();
	}

}
