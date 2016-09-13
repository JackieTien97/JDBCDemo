package com.cn.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Query {
	
	public List<UserVo> showUser() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<UserVo> list = new ArrayList<>();
		try {
			conn = JDBC_Connection.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from users");
			
			while(rs.next()) {
				UserVo userVo = new UserVo();
				userVo.setId(rs.getInt("id"));
				userVo.setName(rs.getString("name"));
				userVo.setAge(rs.getInt("age"));
				userVo.setTel(rs.getString("tel"));
				userVo.setAddress(rs.getString("address"));
				
				list.add(userVo);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Connection.free(rs, conn, stmt);
		}
		
		return list;
	}
	

	public static void main(String[] args) {
		Query query = new Query();
		List<UserVo> list = query.showUser();
		
		if(!list.isEmpty()) {
			System.out.print("id\t");
			System.out.print("name\t");
			System.out.print("age\t");
			System.out.print("tel\t");
			System.out.print("address\t");
			System.out.println();
			for(int i = 0; i < list.size(); i++) {
				System.out.print(list.get(i).getId() + "\t");
				System.out.print(list.get(i).getName() + "\t");
				System.out.print(list.get(i).getAge() + "\t");
				System.out.print(list.get(i).getTel() + "\t");
				System.out.print(list.get(i).getAddress() + "\t");
				System.out.println();
			}
		}
	}

}
