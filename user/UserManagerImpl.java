package com.trisysit.user;

import java.util.List;

public class UserManagerImpl implements UserManager{

	@Override
	public int saveOrUpdate(User user) {
		UserDao userDao=new UserDaoImpl();
		return userDao.saveOrUpdate(user);
	}

	@Override
	public List<User> getUserList(String id) {
		UserDao userDao=new UserDaoImpl();
		return userDao.getUserList(id);
	}

	@Override
	public User getUserDetails(String id) {
		UserDao userDao=new UserDaoImpl();
		return userDao.getUserDetails(id);
	}

	@Override
	public boolean deleteUserById(String id) {
		UserDao userDao=new UserDaoImpl();
		return userDao.deleteUserById(id);
	}

}
