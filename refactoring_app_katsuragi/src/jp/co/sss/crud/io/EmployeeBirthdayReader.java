package jp.co.sss.crud.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EmployeeBirthdayReader {

	public String read() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 生年月日は "1990/01/01" などの文字列として受け取る
		return br.readLine();
	}
}
