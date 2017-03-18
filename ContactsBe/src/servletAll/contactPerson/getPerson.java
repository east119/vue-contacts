package servletAll.contactPerson;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import servletAll.common.sql_data;
import servletAll.contactPerson.Person;

/**
 * Servlet implementation class getPerson
 */
@WebServlet("/getPerson")
public class getPerson extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getPerson() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// 设置请求头格式
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		// 设置CORS
		response.setHeader("Access-Control-Allow-Origin", "*");
		// 设置编码
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		sql_data db = new sql_data();

		try {

			String sql = "SELECT * FROM `personlist`";

			ResultSet rs = db.executeQuery(sql);

			ArrayList<Object> list = new ArrayList<>();

			while (rs.next()) {

				String birthday = rs.getString("birthday");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String phoneNumber = rs.getString("phoneNumber");
				String email = rs.getString("email");
				String battery = rs.getString("battery");
				String site = rs.getString("site");
				String homeNumber = rs.getString("homeNumber");
				int id = rs.getInt("id");
				Person person = new Person();
				person.setBirthday(birthday);
				person.setName(name);
				person.setAddress(address);
				person.setPhoneNumber(phoneNumber);
				person.setEmail(email);
				person.setBattery(battery);
				person.setSite(site);
				person.setId(id);
				person.setHomeNumber(homeNumber);
				list.add(person);
			}
			String jsonString = JSON.toJSONString(list);
			out.println(jsonString);

			rs.close();
		} catch (SQLException se) {

			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		// String path = getServletContext().getRealPath("/");
		// System.out.println(path);
	}

}