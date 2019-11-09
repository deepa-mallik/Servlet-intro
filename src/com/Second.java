package com;

import java.sql.*;
import java.io.*;
import javax.servlet. *;
import javax.servlet.annotation.WebServlet;

@WebServlet("/Second")
public class Second extends GenericServlet {
	private static final long serialVersionUID = 1L;
   
	public void init() {
		System.out.println("init");
	}
	
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String a = request.getParameter("t1");
		String b = request.getParameter("t2");
		String c = request.getParameter("t3");
		String d = request.getParameter("t4");
		String e = request.getParameter("t5");
		String button = request.getParameter("b1");
		
		out.println("Emp id ="+a);
		out.println("<br>");
		out.println("Emp name ="+b);
		out.println("<br>");
		out.println("Emp address ="+c);
		out.println("<br>");
		out.println("Emp email ="+d);
		out.println("<br>");
		out.println("Emp phone no ="+e);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","123456789");
			
			if(button.equals("insert")) {
				
				int x = Integer.parseInt(a);
				int y = Integer.parseInt(e);
				
				PreparedStatement st = con.prepareStatement("insert into employee values(?,?,?,?,?)");
				st.setInt(1, x);
				st.setString(2, b);
				st.setString(3, c);
				st.setString(4, d);
				st.setInt(5, y);
				st.execute();
				out.println("row inserted");
			}
			
			if(button.equals("update")) {
				
				int x = Integer.parseInt(a);
				int y = Integer.parseInt(e);
				PreparedStatement st = con.prepareStatement("update employee set name = ?, address = ?, email = ?, phone_no = ? where id = ?");
				
				st.setString(1,b);
				st.setString(2,c);
				st.setString(3,d);
				st.setInt(4,y);
				st.setInt(5,x);
				st.execute();
				out.println("row updated");	
			}
			
			if(button.equals("delete")) {
				
				int x = Integer.parseInt(a);
				//int y = Integer.parseInt(e);
				PreparedStatement st = con.prepareStatement("delete from employee where id = ?");
				
				st.setInt(1,x);
				st.execute();
				out.println("row updated");
			}
			
			if(button.equals("select")) {
				
				int x = Integer.parseInt(a);
			
				PreparedStatement st = con.prepareStatement("select * from employee  where id = ?");
				
				st.setInt(1,x);
				ResultSet rs = st.executeQuery();
				while(rs.next()) {
					System.out.println(rs.getInt(1)+" " + rs.getString(2)+" "+rs.getString(3)+ " " + 
				rs.getString(4)+" " + rs.getInt(5));
				}
				out.println("row updated");	
			}
		} 
		
		
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void destroy() {
		System.out.println("destroy");
	}
}
