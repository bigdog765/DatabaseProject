
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * Servlet implementation class Connect
 */
@WebServlet("/InitializeDB")
public class InitializeDB {     
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	
	public InitializeDB() {

    }
	  
    /**
     * @see HttpServlet#HttpServlet()
     */
    protected void connect_func() throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
  			      .getConnection("jdbc:mysql://127.0.0.1:3306/testdb?"
  			          + "useSSL=false&user=john&password=pass1234");
            System.out.println(connect);
        }
    }
    
    
    public void deleteTables() throws SQLException, IOException, ServletException {
    	
    	connect_func();
    	try {
    		String sql1 = "DROP TABLE IF EXISTS User";
    		String sql2 = "DROP TABLE IF EXISTS Transactions";    		
    		String sql3 = "DROP TABLE IF EXISTS Follows";
    		String sql4 = "DROP TABLE IF EXISTS Post";		
    		String sql5 = "DROP TABLE IF EXISTS Liked";
    		String sql6 = "DROP TABLE IF EXISTS Comment";

    		// Statements allow to issue SQL queries to the database
    		statement = connect.createStatement();

    		statement.executeUpdate(sql2);
    		statement.executeUpdate(sql3);
    		statement.executeUpdate(sql5);
    		statement.executeUpdate(sql6);
    		statement.executeUpdate(sql4);   		
    				
    		
    		statement.executeUpdate(sql1);	
    	}
    	catch (Exception e) {
            System.out.println(e);
       }
    	
    }
    
public void insertTables() throws SQLException, IOException, ServletException {
    	
    	connect_func();
    	try {
String sql1 = "CREATE TABLE User " +
                    
    			   " (Email VARCHAR(30), " +
    			   " Password VARCHAR(40),"+
                   " firstName VARCHAR(20), " + 
                   " lastName VARCHAR(30), " +
                   " Age INTEGER,"+
                   " USDAmount DOUBLE,"+
                   " PPSAmount DOUBLE,"+
                   " PRIMARY KEY ( Email ))";
String sql2 = "CREATE TABLE Transactions " +
                    
    			   " (SenderEmail VARCHAR(30), " +
    			   " ReceiverEmail VARCHAR(30), " +
    			   " timeOfTrans DATETIME,"+
    			   " PPSAmount DOUBLE," +
                   " USDAmount DOUBLE,"+
                   " typeofTrans VARCHAR(10),"+
                   " transactionID VARCHAR(20),"+
                   " PRIMARY KEY ( transactionID ),"+
                   " FOREIGN KEY ( SenderEmail ) REFERENCES User(Email))";

String sql3 = "CREATE TABLE Follows " +
        
    			   " (followerEmail VARCHAR(30), " +
    			   " followeeEmail VARCHAR(30), " +
                   " PRIMARY KEY ( followerEmail, followeeEmail ),"+
                   " FOREIGN KEY ( followerEmail ) REFERENCES User(Email),"+
                   " FOREIGN KEY ( followeeEmail ) REFERENCES User(Email))";
String sql4 = "CREATE TABLE Post " +
        
    			   " (Email VARCHAR(30), " +
    			   " type CHAR(1),"+
                   " numOfLikes INT, " + 
                   " content VARCHAR(350), " +
                   " postID VARCHAR(20),"+
                   " PRIMARY KEY ( postID ),"+
                   " FOREIGN KEY ( Email ) REFERENCES User(Email))";

String sql5 = "CREATE TABLE Liked " +
        
    			   " (Email VARCHAR(30), " +
    			   " postID VARCHAR(20),"+
                   " FOREIGN KEY ( email ) REFERENCES User(Email),"+
                   " FOREIGN KEY ( postID ) REFERENCES Post(postID))";
String sql6 = "CREATE TABLE Comment " +
        
    			   " (Email VARCHAR(30), " +
    			   " postID VARCHAR(20),"+
    			   " commentID VARCHAR(20),"+
                   " content VARCHAR(350), " + 
                   " FOREIGN KEY ( email ) REFERENCES User(Email),"+
                   " FOREIGN KEY ( postID ) REFERENCES Post(postID))";
  			

    		// Statements allow to issue SQL queries to the database
    		statement = connect.createStatement();

    		statement.executeUpdate(sql1);
    		statement.executeUpdate(sql2);
    		statement.executeUpdate(sql3);
    		statement.executeUpdate(sql4);
    		statement.executeUpdate(sql5);
    		statement.executeUpdate(sql6);
    	}
    	catch (Exception e) {
            System.out.println(e);
       }
    	
    }
public void insertTuples() throws SQLException, IOException, ServletException {
	
	connect_func();
	try {
		String userInsert = "insert into User(Email, Password, firstName, lastName, Age, USDAmount, PPSAmount) values "; 
		
		String us0 = userInsert +"(\"root\", \"pass1234\", \"ROOT\",\"ROOT\",\"0\",\"0\",\"1000000000\")";
		String us1 = userInsert +"(\"pf@gmail.com\", \"pass1000684\", \"Parker\",\"Collymore\",\"21\",\"1000\",\"0\")";
		String us2 = userInsert +"(\"ab34@gmail.com\", \"pass88\", \"Abby\",\"Stoll\",\"31\",\"1000\",\"0\")";
		String us3 = userInsert +"(\"yu33@outlook.com\", \"pass2212\", \"Jen\",\"Adler\",\"19\",\"1000\",\"0\")";
		String us4 = userInsert +"(\"jen_t@gmail.com\", \"pass10001000\", \"Kathy\",\"Ford\",\"23\",\"1000\",\"0\")";
		String us5 = userInsert +"(\"hy88@yahoo.com\", \"pass100062\", \"Ted\",\"Pierce\",\"88\",\"1000\",\"0\")";
		String us6 = userInsert +"(\"emp555@wayne.edu\", \"pass55\", \"Andy\",\"Gray\",\"18\",\"1000\",\"0\")";
		String us7 = userInsert +"(\"gary88@gmail.com\", \"pass988\", \"Gary\",\"Hayes\",\"41\",\"1000\",\"0\")";
		String us8 = userInsert +"(\"johndata@outlook.com\", \"pass321\", \"John\",\"Wilson\",\"27\",\"1000\",\"0\")";
		String us9 = userInsert +"(\"sql123@gmail.com\", \"pass12\", \"Jesus\",\"Christ\",\"96\",\"1000\",\"0\")";
		String us10 = userInsert +"(\"klkg6543@wayne.edu\", \"pass46\", \"Megan\",\"Miller\",\"62\",\"1000\",\"0\")";
		  statement.executeUpdate(us0);
	      statement.executeUpdate(us1);
	      statement.executeUpdate(us2);
	      statement.executeUpdate(us3);
	      statement.executeUpdate(us4);
	      statement.executeUpdate(us5);
	      statement.executeUpdate(us6);
	      statement.executeUpdate(us7);
	      statement.executeUpdate(us8);
	      statement.executeUpdate(us9);
	      statement.executeUpdate(us10);
	      
	      
	    String transactionInsert = "insert into Transactions(SenderEmail, ReceiverEmail, timeOfTrans, PPSAmount, USDAmount,typeofTrans, transactionID) values ";
	    
	    String tr1 = transactionInsert +"(\"pf@gmail.com\", \"hy88@yahoo.com\", \"2022-03-8 05:45:22\", \"3\", \"120\",\"buy\",\"59150e43468fea17\")";
		String tr2 = transactionInsert +"(\"ab34@gmail.com\", \"yu33@outlook.com\", \"2022-03-8 04:45:22\", \"10\", \"88\",\"buy\",\"d3ff8769940ec23f\")";
		String tr3 = transactionInsert +"(\"yu33@outlook.com\", \"johndata@outlook.com\", \"2022-03-8 05:48:22\",\"20\", \"3\",\"sell\",\"21a31d3c7eb7905c\")";
		String tr4 = transactionInsert +"(\"jen_t@gmail.com\",\"hy88@yahoo.com\", \"2022-03-8 02:45:22\", \"12\", \"7\",\"buy\",\"9782f31962d483a4\")";
		String tr5 = transactionInsert +"(\"hy88@yahoo.com\", \"gary88@gmail.com\", \"2022-03-8 05:33:22\",\"15\", \"88\",\"sell\",\"c967a86fcf1e1528\")";
		String tr6 = transactionInsert +"(\"emp555@wayne.edu\", \"sql123@gmail.com\",\"2022-03-8 05:59:22\", \"9\", \"18\",\"buy\",\"b4a0b4cf6cbc1a40\")";
		String tr7 = transactionInsert +"(\"gary88@gmail.com\", \"hy88@yahoo.com\", \"2022-03-8 05:11:22\", \"7\", \"410\",\"buy\",\"7f3a73773836c2c2\")";
		String tr8 = transactionInsert +"(\"johndata@outlook.com\", \"hy88@yahoo.com\", \"2022-03-8 02:45:22\", \"16\", \"8\",\"tip\",\"ed34a8074b039c9d\")";
		String tr9 = transactionInsert +"(\"sql123@gmail.com\", \"jen_t@gmail.com\", \"2022-03-8 08:45:22\", \"11\", \"55\",\"buy\",\"d5cba5706d657e74\")";
		String tr10 = transactionInsert +"(\"klkg6543@wayne.edu\", \"ab34@gmail.com\", \"2022-03-8 05:16:22\", \"18\", \"72\",\"buy\",\"9ad71db2e2ecd5f7\")";
		  statement.executeUpdate(tr1);
	      statement.executeUpdate(tr2);
	      statement.executeUpdate(tr3);
	      statement.executeUpdate(tr4);
	      statement.executeUpdate(tr5);
	      statement.executeUpdate(tr6);
	      statement.executeUpdate(tr7);
	      statement.executeUpdate(tr8);
	      statement.executeUpdate(tr9);
	      statement.executeUpdate(tr10);
	      
	      
		    
      String followsInsert = "insert into Follows(followerEmail, followeeEmail) values ";
	    
	    String f1 = followsInsert +"(\"pf@gmail.com\", \"ab34@gmail.com\")";
		String f2 = followsInsert +"(\"ab34@gmail.com\", \"jen_t@gmail.com\")";
		String f3 = followsInsert +"(\"yu33@outlook.com\", \"hy88@yahoo.com\")";
		String f4 = followsInsert +"(\"jen_t@gmail.com\", \"sql123@gmail.com\")";
		String f5 = followsInsert +"(\"hy88@yahoo.com\", \"gary88@gmail.com\")";
		String f6 = followsInsert +"(\"emp555@wayne.edu\", \"hy88@yahoo.com\")";
		String f7 = followsInsert +"(\"gary88@gmail.com\", \"yu33@outlook.com\")";
		String f8 = followsInsert +"(\"johndata@outlook.com\", \"johndata@outlook.com\")";
		String f9 = followsInsert +"(\"sql123@gmail.com\", \"hy88@yahoo.com\")";
		String f10 = followsInsert +"(\"klkg6543@wayne.edu\", \"jen_t@gmail.com\")";
		  statement.executeUpdate(f1);
	      statement.executeUpdate(f2);
	      statement.executeUpdate(f3);
	      statement.executeUpdate(f4);
	      statement.executeUpdate(f5);
	      statement.executeUpdate(f6);
	      statement.executeUpdate(f7);
	      statement.executeUpdate(f8);
	      statement.executeUpdate(f9);
	      statement.executeUpdate(f10);
			      
      String postInsert = "insert into Post(Email, type, numOfLikes, content, postID) values ";
	    
	    String p1 = postInsert +"(\"pf@gmail.com\",\"t\", \"23\", \"Checkout this picture\",\"59150e43468fea17\")";
		String p2 = postInsert +"(\"ab34@gmail.com\",\"v\", \"12\",\"This video!\",\"d3ff8769940ec23f\")";
		String p3 = postInsert +"(\"yu33@outlook.com\",\"i\", \"50\",\"Look at this haha!\",\"21a31d3c7eb7905c\")";
		String p4 = postInsert +"(\"jen_t@gmail.com\",\"t\", \"30\", \"I hope this post get 31 likes\",\"9782f31965d483a4\")";
		String p5 = postInsert +"(\"hy88@yahoo.com\",\"a\", \"1\", \"My most liked post! :D\",\"c967a86fcf151528\")";
		String p6 = postInsert +"(\"emp555@wayne.edu\",\"a\", \"43\", \"Important Announcement\",\"b4a0b4cf6cbc1a40\")";
		String p7 = postInsert +"(\"gary88@gmail.com\",\"h\", \"21\", \"Can I get some help with my database?\",\"7f3a73773836c2c2\")";
		String p8 = postInsert +"(\"johndata@outlook.com\",\"v\", \"42\", \"John Data reporting\",\"ed34a8084b039c9d\")";
		String p9 = postInsert +"(\"sql123@gmail.com\",\"i\", \"32\", \"Sup\",\"d5cba5706d657ew4\")";
		String p10 = postInsert +"(\"klkg6543@wayne.edu\",\"i\", \"65\", \"See ya later!!!\",\"9ad71db2e2zcd5f7\")";
		  statement.executeUpdate(p1);
	      statement.executeUpdate(p2);
	      statement.executeUpdate(p3);
	      statement.executeUpdate(p4);
	      statement.executeUpdate(p5);
	      statement.executeUpdate(p6);
	      statement.executeUpdate(p7);
	      statement.executeUpdate(p8);
	      statement.executeUpdate(p9);
	      statement.executeUpdate(p10);

      String likedInsert = "insert into Liked(Email, postID) values ";
	    
	    String l1 = likedInsert +"(\"pf@gmail.com\", \"59150e43468fea17\")";
		String l2 = likedInsert +"(\"ab34@gmail.com\", \"d3ff8769940ec23f\")";
		String l3 = likedInsert +"(\"yu33@outlook.com\", \"21a31d3c7eb7905c\")";
		String l4 = likedInsert +"(\"jen_t@gmail.com\", \"9782f31965d483a4\")";
		String l5 = likedInsert +"(\"hy88@yahoo.com\", \"c967a86fcf151528\")";
		String l6 = likedInsert +"(\"emp555@wayne.edu\", \"b4a0b4cf6cbc1a40\")";
		String l7 = likedInsert +"(\"gary88@gmail.com\", \"7f3a73773836c2c2\")";
		String l8 = likedInsert +"(\"johndata@outlook.com\", \"ed34a8084b039c9d\")";
		String l9 = likedInsert +"(\"sql123@gmail.com\",\"d5cba5706d657ew4\")";
		String l10 = likedInsert +"(\"klkg6543@wayne.edu\",\"9ad71db2e2zcd5f7\")";
		  statement.executeUpdate(l1);
	      statement.executeUpdate(l2);
	      statement.executeUpdate(l3);
	      statement.executeUpdate(l4);
	      statement.executeUpdate(l5);
	      statement.executeUpdate(l6);
	      statement.executeUpdate(l7);
	      statement.executeUpdate(l8);
	      statement.executeUpdate(l9);
	      statement.executeUpdate(l10);
					      
		  String commentInsert = "insert into Comment(Email, content,commentID, postID) values ";
		
		String c1 = commentInsert +"(\"pf@gmail.com\",\"Hey gang!\",\"2f7b45b83374156e1a9c\", \"59150e43468fea17\")";
		String c2 = commentInsert +"(\"ab34@gmail.com\",\"I love Wanye State!\",\"c24036dd01557fe76005\", \"d3ff8769940ec23f\")";
		String c3 = commentInsert +"(\"yu33@outlook.com\",\"My database is broken :(\",\"c83aa9ebd445467a8958\", \"21a31d3c7eb7905c\")";
		String c4 = commentInsert +"(\"jen_t@gmail.com\",\"I might be late to class today\",\"7d129eb730cb55c4a00c\", \"9782f31965d483a4\")";
		String c5 = commentInsert +"(\"hy88@yahoo.com\",\"Hello World!\",\"80dee77fe9067a9b5438\", \"c967a86fcf151528\")";
		String c6 = commentInsert +"(\"emp555@wayne.edu\",\"Yes!\",\"38329e678141e6bcb06f\", \"b4a0b4cf6cbc1a40\")";
		String c7 = commentInsert +"(\"gary88@gmail.com\",\"Anyone looking to trade\",\"9f48f087fc75474c49aa\", \"7f3a73773836c2c2\")";
		String c8 = commentInsert +"(\"johndata@outlook.com\",\"Database4lyfe\",\"aeebef42296a61e6d1ab\", \"ed34a8084b039c9d\")";
		String c9 = commentInsert +"(\"sql123@gmail.com\",\"Have a great day!\",\"9aa0326246e17a126e30\", \"d5cba5706d657ew4\")";
		String c10 = commentInsert +"(\"klkg6543@wayne.edu\",\"AFK\",\"77e16253b03357c3bc45\", \"9ad71db2e2zcd5f7\")";
		  statement.executeUpdate(c1);
		  statement.executeUpdate(c2);
		  statement.executeUpdate(c3);
		  statement.executeUpdate(c4);
		  statement.executeUpdate(c5);
		  statement.executeUpdate(c6);
		  statement.executeUpdate(c7);
		  statement.executeUpdate(c8);
		  statement.executeUpdate(c9);
		  statement.executeUpdate(c10);
					      

	}
	catch (Exception e) {
        System.out.println(e);
   }
}
}
