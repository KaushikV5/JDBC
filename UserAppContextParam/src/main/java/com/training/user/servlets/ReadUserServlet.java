package com.training.user.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateUserServlet
 */
public class ReadUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;

	@Override
	public void init(ServletConfig config) {
		try {
			ServletContext servletContext = config.getServletContext();
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(servletContext.getInitParameter("dbUrl"),
					servletContext.getInitParameter("dbUser"), servletContext.getInitParameter("dbPassword"));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Statement statement = connection.createStatement();
			ResultSet executeQuery = statement.executeQuery("select * from user");
			PrintWriter writer = response.getWriter();
			writer.print("<table>");
			writer.print("<tr>");
			writer.print("<th>");
			writer.println("FirstName");
			writer.print("</th>");
			writer.print("<th>");
			writer.print("LastName");
			writer.print("</th>");
			writer.print("<th>");
			writer.print("Email");
			writer.print("</th>");
			writer.print("</tr>");
			while (executeQuery.next()) {
				writer.println("<tr>");
				writer.println("<td>");
				writer.print(executeQuery.getString(1));
				writer.println("</td>");
				writer.println("<td>");
				writer.print(executeQuery.getString(2));
				writer.println("</td>");
				writer.println("<td>");
				writer.print(executeQuery.getString(3));
				writer.println("</td>");
				writer.println("</tr>");
			}
			writer.print("</table>");

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
