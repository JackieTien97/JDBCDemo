package com.cn.jdbc;

import java.sql.*;

public class UpdateUser {
	
	public void update(UserVo userVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update users set id = ?, name = ?, age = ?, tel = ?, address = ? where id = ?";
		
		try {
			conn = JDBC_Connection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userVo.getId());
			pstmt.setString(2, userVo.getName());
			pstmt.setInt(3, userVo.getAge());
			pstmt.setString(4, userVo.getTel());
			pstmt.setString(5, userVo.getAddress());
			pstmt.setInt(6, userVo.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Connection.free(null, conn, pstmt);
		}
	}

	public static void main(String[] args) {
		UpdateUser updateUser = new UpdateUser();
		int id = 107;
		String name = "Jack";
		int age = 19;
		String tel = "123-465465";
		String address = "银河系";
		QueryById queryById = new QueryById();
		UserVo userVo = new UserVo();
		userVo = queryById.queryById(id);
		if(userVo != null) {
			UserVo vo = new UserVo();
			vo.setId(id);
			vo.setName(name);
			vo.setAge(age);
			vo.setTel(tel);
			vo.setAddress(address);
			updateUser.update(vo);
			System.out.println("修改成功！修改了id值为" + id + "的数据");
		}
		else {
			System.out.println("修改失败！原因：id为" + id + "的数据不存在！");
		}
	}

}
