

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
@WebServlet("/UserDAO")
public class UserDAO {     
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	
	public UserDAO() {

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
    
    
    public List<User> listAllUser() throws SQLException {
        List<User> listUser = new ArrayList<User>();        
        String sql = "SELECT * FROM User";      
        connect_func();      
        statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int age = resultSet.getInt("age");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            String fN = resultSet.getString("firstName");
            String lN = resultSet.getString("lastName");
  
             
            User User = new User(email, password, fN, lN, age, 1000, 0);
            listUser.add(User);
        }        
        resultSet.close();
        statement.close();         
        disconnect();        
        return listUser;
    }
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    public boolean checkDuplicate(User User) throws SQLException {
    	
    	//List<User> listUser = new ArrayList<User>();        
        String sql = "SELECT * FROM User";      
        connect_func();      
        statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        System.out.println(User.email);
        while (resultSet.next()) {
        	String email = resultSet.getString("email");
        	if((User.email).equals(email)) {
        		return true;
        		
        	}
        }
        resultSet.close();
        statement.close();         
        return false;
        
    	
    }

public boolean checkForPassword(String userEmail, String userPassword) throws SQLException {
	
	//List<User> listUser = new ArrayList<User>();        
    String sql = "SELECT * FROM User";      
    connect_func();      
    statement =  (Statement) connect.createStatement();
    ResultSet resultSet = statement.executeQuery(sql);
    
    
    while (resultSet.next()) {
    	String email = resultSet.getString("email");
    	String password = resultSet.getString("password");
    	if((userEmail).equals(email) && (userPassword).equals(password)) {
    		return true;
    		
    	}
    }
    resultSet.close();
    statement.close();         
    return false;
    
	
}
         
    public int insert(User User) throws SQLException {
    	connect_func(); 
    	
    	System.out.println("************testZ****************");
    	if(checkDuplicate(User))
    		return -1;
    	
    	
		String sql = "insert into  User(email, password, firstName, lastName, age, USDAmount, PPSAmount) values (?, ?, ?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, User.email);
		preparedStatement.setString(2, User.password);
		preparedStatement.setString(3, User.fName);
		preparedStatement.setString(4, User.lName);
		preparedStatement.setInt(5, User.age);
		preparedStatement.setDouble(6, User.usd);
		preparedStatement.setDouble(7, User.pps);
//		preparedStatement.executeUpdate();
		
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
//        disconnect();
        return 1;
    }     
     
   
	
    public User getUser(int id) throws SQLException {
    	User User = null;
        String sql = "SELECT * FROM student WHERE id = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, id);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
        	int age = resultSet.getInt("age");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            String fN = resultSet.getString("firstName");
            String lN = resultSet.getString("lastName");
             
            User = new User(email, password, fN, lN, age,1000,0);
            //test
        }
         
        resultSet.close();
        statement.close();
         
        return User;
    }
    public ArrayList<Transaction> getUserTransactions(String name) throws SQLException {
  
    	Transaction t = null;
    	ArrayList<Transaction> resultList = new ArrayList<Transaction>();
    	String sql1 = "SELECT * FROM Transactions WHERE SenderEmail = \""+ name +"\" OR ReceiverEmail = \""+name+"\"";
    	
    	
    	connect_func();
    	statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql1);
        
        
        while (resultSet.next()) {
        	String from = resultSet.getString("SenderEmail");
        	String to = resultSet.getString("ReceiverEmail");
        	String time = resultSet.getString("timeOfTrans");
        	double ppsA = resultSet.getDouble("PPSAmount");
        	double usdA = resultSet.getDouble("USDAmount");
        	String type = resultSet.getString("typeOfTrans");
        	String id = resultSet.getString("transactionID");
        	
        	t = new Transaction(from,to,time,ppsA,usdA,type,id);
        	resultList.add(t);
        	
        }
        resultSet.close();
        statement.close();
    	
    	return resultList;
    	
    	
    }
}
