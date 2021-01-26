package com.trisysit.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trisysit.util.AppUtil;

public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = request.getParameter("page");
		String id = request.getParameter("id");
		System.out.println(page);
		if(AppUtil.isNotEmpty(page)) {
			switch(page) {
			case "add":
				if (AppUtil.isNotEmpty(id)) {
					//get data for edit user
					User editUser = getUserDetailsById(id);
					request.setAttribute("user", editUser);
				}
				request.getServletContext().getRequestDispatcher("/WEB-INF/pages/user/addUser.jsp").forward(request,
						response);
				break;
			case "details":
				User userDetail = getUserDetailsById(id);
				request.setAttribute("userDetail", userDetail);
				request.getServletContext().getRequestDispatcher("/WEB-INF/pages/user/detailsPage.jsp").forward(request,
						response);
				break;
			case "delete":
				UserManager deletUser = new UserManagerImpl();
				deletUser.deleteUserById(id);
				response.sendRedirect("user");
				break;
			}
		}else {
			UserManager userManager = new UserManagerImpl();
			List<User> users = userManager.getUserList(null);
			request.setAttribute("users", users);
			request.getServletContext().getRequestDispatcher("/WEB-INF/pages/user/userList.jsp").forward(request,
					response);
		}
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = new User();
		user.setId(request.getParameter("id"));
		user.setName(request.getParameter("name"));
		if(AppUtil.isNotEmpty(request.getParameter("age"))) {
			user.setAge(Integer.parseInt(request.getParameter("age").trim()));
		}
		user.setGender(request.getParameter("gender"));
		UserManager userManager = new UserManagerImpl();
		int result = userManager.saveOrUpdate(user);
		if (result > 0) {
			response.sendRedirect("user");
		}
		//todo:: for else condition

	}

	private User getUserDetailsById(String id) {
		UserManager userManager = new UserManagerImpl();
		User userDetail = userManager.getUserDetails(id);
		return userDetail;
	}

}
