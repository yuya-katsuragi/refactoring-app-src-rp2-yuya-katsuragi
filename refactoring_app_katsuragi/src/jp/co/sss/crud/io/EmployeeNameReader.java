package jp.co.sss.crud.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EmployeeNameReader {

	public String read() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		return br.readLine();
	}

}
