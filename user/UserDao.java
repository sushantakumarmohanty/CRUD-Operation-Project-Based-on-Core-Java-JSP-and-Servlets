package com.trisysit.user;

import java.util.List;

public interface UserDao {
	int saveOrUpdate(User user);
	List<User> getUserList(String id);
	User getUserDetails(String id);
	boolean deleteUserById(String id);
}
