package employeeLogin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String user = request.getParameter("username");
		String pass = request.getParameter("password");

		PrintWriter out = response.getWriter();

		response.setContentType("text/html");

		try {

			Driver driver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hcltraining", "root",
					"HCLSQL04*");
			PreparedStatement pStat = conn.prepareStatement("Select * From login Where username = ? and password = ?");
			pStat.setString(1, user);
			pStat.setString(2, pass);

			ResultSet rs = pStat.executeQuery();

			if (rs.next()) {
				out.println("Hello, " + user);
			} else {
				out.println("Please enter the correct credentials or sign up for a new account.");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		boolean loginSuccess = true;

		if (user.isEmpty() || pass.isEmpty()) {
			loginSuccess = false;
		}

		if (loginSuccess = true) {
			response.sendRedirect("./html/welcome.html");
		} else if (loginSuccess = false) {
			response.sendRedirect("./html/loginError.html");
		}

		// out.println("Username: " + user + "<br/>");
		// out.println("Password: " + pass);
	}

}
