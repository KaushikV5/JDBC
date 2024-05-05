package com.training.user.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateUserServlet
 */
@WebServlet(urlPatterns = "/addServlet")
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;

	@Override
	public void init(ServletConfig config) {
		try {
			ServletContext servletContext = config.getServletContext();
			Enumeration<String> initParameterNames = servletContext.getInitParameterNames();
			while(initParameterNames.hasMoreElements()) {
				System.out.println(servletContext.getInitParameter(initParameterNames.nextElement()));
			}
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(servletContext.getInitParameter("dbUrl"),
					servletContext.getInitParameter("dbUser"), servletContext.getInitParameter("dbPassword"));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String parameter1 = request.getParameter("firstName");
		String parameter2 = request.getParameter("lastName");
		String parameter3 = request.getParameter("email");
		String parameter4 = request.getParameter("password");
		try {
			Statement statement = connection.createStatement();
			int executeUpdate = statement.executeUpdate("insert into user values('" + parameter1 + "','" + parameter2
					+ "','" + parameter3 + "','" + parameter4 + "')");
			PrintWriter writer = response.getWriter();
			if (executeUpdate != 0) {
				writer.println("<H1>USER CREATED</H1>");
			} else {
				writer.println("<H1>NOT CREATED</H1>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
