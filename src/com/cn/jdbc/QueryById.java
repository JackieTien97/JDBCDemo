package com.cn.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryById {
	
	public UserVo queryById(int id) {
		UserVo userVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBC_Connection.getConnection();
			pstmt = conn.prepareStatement("select * from users where id = ?");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				userVo = new UserVo();
				userVo.setId(rs.getInt("id"));
				userVo.setName(rs.getString("name"));
				userVo.setAge(rs.getInt("age"));
				userVo.setTel(rs.getString("tel"));
				userVo.setAddress(rs.getString("address"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Connection.free(rs, conn, pstmt);
		}
		
		return userVo;
	}

	public static void main(String[] args) {
		QueryById byId = new QueryById();
		int id = 207;
		UserVo vo = byId.queryById(id);
		if(vo != null) {
			System.out.print("id\t");
			System.out.print("name\t");
			System.out.print("age\t");
			System.out.print("tel\t");
			System.out.print("address\t");
			System.out.println();
			System.out.print(vo.getId() + "\t");
			System.out.print(vo.getName() + "\t");
			System.out.print(vo.getAge() + "\t");
			System.out.print(vo.getTel() + "\t");
			System.out.print(vo.getAddress() + "\t");
			System.out.println();
		}
		else {
			System.out.println("id为" + id + "的用户不存在！");
		}
	}

}
