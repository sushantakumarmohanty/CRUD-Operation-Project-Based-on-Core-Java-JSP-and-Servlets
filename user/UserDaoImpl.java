package com.trisysit.user;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.trisysit.dataBaseUtil.DataBaseUtil;
import com.trisysit.util.AppUtil;

public class UserDaoImpl implements UserDao{

	@Override
	public int saveOrUpdate(User user) {
		int result = 0;
		try {
		Connection con = DataBaseUtil.getConnection();
		StringBuffer query = null;
		if (user != null && AppUtil.isNotEmpty(user.getId())) {
			query = new StringBuffer("update  user SET  name = ?, age=?, gender=?  where id=?");
		} else {
			query = new StringBuffer("insert into user(name, age, gender, id) values (?, ?, ?, ?)");
		}
		PreparedStatement ps = con.prepareStatement(query.toString());
		ps.setString(1, user.getName());
		ps.setInt(2, user.getAge());
		ps.setString(3, user.getGender());
		if(!AppUtil.isNotEmpty(user.getId())) {
			ps.setString(4, UUID.randomUUID().toString());
		}else {
			ps.setString(4, user.getId());
		}
		result = ps.executeUpdate();
		con.close();
	} catch (Exception ex) {
		ex.printStackTrace();
	}
	return result;
	}

	@Override
	public List<User> getUserList(String id) {
		List<User> userList = new ArrayList<User>();
		try {
			Connection con = DataBaseUtil.getConnection();
			StringBuffer query = new StringBuffer("select * from user where 1=1");
			if(AppUtil.isNotEmpty(id)) {
				query.append(" and id='" + id +"'");
			}
			PreparedStatement ps = con.prepareStatement(query.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User userDetail = new User();
				userDetail.setId(rs.getString("id"));
				userDetail.setName(rs.getString("name"));
				userDetail.setAge(rs.getInt("age"));
				userDetail.setGender(rs.getString("gender"));
				userList.add(userDetail);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public User getUserDetails(String id) {
		List<User>users=getUserList(id);
		if(users!=null && users.size()>0) {
			return	users.get(0);
		}
		return null;
	}

	@Override
	public boolean deleteUserById(String id) {
		boolean result=false;
		try {
			Connection con = DataBaseUtil.getConnection();
			StringBuffer query = new StringBuffer("delete from user where id = ?");
			PreparedStatement ps = con.prepareStatement(query.toString());
			ps.setString(1, id);
			result=ps.execute();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


}
