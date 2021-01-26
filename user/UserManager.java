package com.trisysit.user;

import java.util.List;

public interface UserManager {
	int saveOrUpdate(User user);

	List<User> getUserList(String id);

	User getUserDetails(String id);

	boolean deleteUserById(String id);
}
