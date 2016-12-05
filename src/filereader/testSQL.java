package filereader;

import java.sql.*;

import login.Account;

class testSQL{  
	public static void main(String args[]){  
	
		/*
		try{  
			//Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/cstest","root","Orange-Squash28"); 
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from account");  
			while(rs.next()) 
				System.out.println(rs.getString(1)+" | "+rs.getString(2)
				+" | "+rs.getString(3)+" | "+rs.getString(4)
				+" | "+rs.getString(5)+" | "+rs.getString(6)
				+" | "+rs.getString(7)+" | "+rs.getString(8));
			con.close();  
		}catch(Exception e){ 
			System.out.println(e);
		} 

		
		ChatReader chatReader = new ChatReader("Chat5Test.txt");
		
		try{
			
			chatReader.write("Test A");
				
			System.out.println(chatReader.read());
			
		} catch(Exception e) {
			System.out.println(e);
		}
		
		*/
	}
}  
	



/*
import java.sql.*;

public class testSQL {

	public static void main(String [] args){
		System.out.println(getData("select * from prof."));
	}
	
	public static String getData(String query) {
        String output = "";
        try {
            String connectionUrl = "jdbc:sqlserver://localhost:1234;databaseName=123;user=123;password=123";
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                con = DriverManager.getConnection(connectionUrl);
                String SQL = "select smth from tableName where smth";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    output =  (String) rs.getObject(1);
                }
                rs.close();
        }
        catch (Exception e) {

            return "ERROR while retrieving data: " + e.getMessage();
        }
        return output;
    }
	
}
*/
