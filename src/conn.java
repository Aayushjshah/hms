//package hms;
import java.sql.*;  

public class conn{  
	Connection c;
	Statement s;
public conn(){  
	try{  
		// Class.forName("com.mysql.jdbc.Driver");  
		c=DriverManager.getConnection(  
		"jdbc:mysql://localhost:3306/jprojecthms","root","root");  
		
		s=c.createStatement();  
		// ResultSet rs=s.executeQuery("select * from login");  
		// while(rs.next()){
		// 	System.out.println(rs.getString(1));
		// }
		}catch(Exception e){ System.out.println(e);}  
	}  
}
  