package com.cn.jdbc;

import java.sql.*;

public class DeleteUser {
	
	public void deleteUser(int id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = JDBC_Connection.getConnection();
			pstmt = conn.prepareStatement("delete from users where id = ?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			System.out.println("删除成功！删除了ID值为" + id + "的数据");
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Connection.free(null, conn, pstmt);
		}
	}

	public static void main(String[] args) {
		DeleteUser deleteUser = new DeleteUser();
		int id = 2;
		UserVo userVo = new UserVo();
		QueryById queryById = new QueryById();
		userVo = queryById.queryById(id);
		if(userVo != null) {
			deleteUser.deleteUser(id);
		}
		else {
			System.out.println("删除失败！原因：id为" + id + "的数据不存在！");
		}
	}

}
