

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
	
	public Transaction t = null;
	public ArrayList<Transaction> resultList = new ArrayList<Transaction>();
	
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
            String zip = resultSet.getString("ZipCode");
             
            User User = new User(email, password, fN, lN, age,zip, 1000, 0);
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
    	
    	
		String sql = "insert into  User(email, password, firstName, lastName, age, ZipCode, USDAmount, PPSAmount) values (?, ?, ?, ?, ?, ?, ?,?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, User.email);
		preparedStatement.setString(2, User.password);
		preparedStatement.setString(3, User.fName);
		preparedStatement.setString(4, User.lName);
		preparedStatement.setInt(5, User.age);
		preparedStatement.setString(6, User.zip);
		preparedStatement.setDouble(7, User.usd);
		preparedStatement.setDouble(8, User.pps);
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
            String zip = resultSet.getString("ZipCode");
             
            User = new User(email, password, fN, lN, age, zip, 1000,0);
            //test
        }
         
        resultSet.close();
        statement.close();
         
        return User;
    }
    public void insertTransaction(String sender, String reciever, String time, int pps, double usd, String type, String id)throws SQLException {
    	connect_func();
    	t = new Transaction(sender,reciever,time,pps,usd,type,id);
    	String sql = "insert into  Transactions(SenderEmail, ReceiverEmail, timeOfTrans, PPSAmount, USDAmount, typeofTrans, transactionID) values (?, ?, ?, ?, ?, ?, ?)";
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, t.getFrom());
		preparedStatement.setString(2, t.getTo());
		preparedStatement.setString(3, "2022-03-8 04:45:22"); //arbitrary time
		preparedStatement.setDouble(4, t.getppsA());
		preparedStatement.setDouble(5, t.getusdA());
		preparedStatement.setString(6, t.getType());
		preparedStatement.setString(7, t.getID());
		
		preparedStatement.executeUpdate();
		preparedStatement.close();
		disconnect();
		
    	resultList.add(t);
    }
    public ArrayList<Transaction> getUserTransactions(String name) throws SQLException {
  
    	
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
    
    
    
    public double buy(String user) throws SQLException{
    	double balance = 0;
    	String sql1 = "SELECT * FROM User WHERE Email = \""+ user+"\"";
    	connect_func();
    	statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql1);
        while (resultSet.next()) {
        	balance = Double.parseDouble(resultSet.getString("USDAmount"));
        	System.out.println(balance);
        }
        
        
        resultSet.close();
        statement.close();
        return balance;
    }
    public double sell(String user) throws SQLException{
    	double balance = 0;
    	String sql1 = "SELECT * FROM User WHERE Email = \""+ user+"\"";
    	connect_func();
    	statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql1);
        while (resultSet.next()) {
        	balance = Double.parseDouble(resultSet.getString("PPSAmount"));
        	System.out.println(balance);
        }
        
        
        resultSet.close();
        statement.close();
        return balance;
    }
    public void updateBuy(String user, double pps) throws SQLException{ //USER BUYS PPS FROM THE ROOT
    	System.out.println(pps);
    	String root = "root";
    	String currentPPS = "SELECT ppsAmount from User WHERE Email = \""+ user +"\"";
    	String currentUSD = "SELECT USDAmount from User WHERE email = \""+ user +"\"";
    	String RcurrentPPS = "SELECT ppsAmount from User WHERE Email = \""+ root +"\"";
    	String RcurrentUSD = "SELECT USDAmount from User WHERE email = \""+ root +"\"";
    	
    	
    	
    	connect_func();
    	double currentPPSDouble = 0;
    	double currentUSDDouble = 0;
    	double RcurrentPPSDouble = 0;
    	double RcurrentUSDDouble = 0;
    	System.out.println("TESTTTT");
    	statement = (Statement) connect.createStatement();
    	 ResultSet resultSet = statement.executeQuery(currentPPS);
    	 while (resultSet.next()) {
    		 currentPPSDouble = Double.parseDouble(resultSet.getString("PPSAmount"));
         	
         }
    	 resultSet = statement.executeQuery(currentUSD);
    	 while (resultSet.next()) {
    		 currentUSDDouble = Double.parseDouble(resultSet.getString("USDAmount"));
         	
         }
    	 //root
    	resultSet = statement.executeQuery(RcurrentPPS);
    	 while (resultSet.next()) {
    		 RcurrentPPSDouble = Double.parseDouble(resultSet.getString("PPSAmount"));
         	
         }
    	 resultSet = statement.executeQuery(RcurrentUSD);
    	 while (resultSet.next()) {
    		 RcurrentUSDDouble = Double.parseDouble(resultSet.getString("USDAmount"));
         	
         }
    	
    	 System.out.println("*********" +  currentPPSDouble + "******"+ currentUSDDouble);
    	
    	String sql1 = "UPDATE User SET PPSAmount = " +(currentPPSDouble + (pps * 100))+ ", USDAmount = " + (currentUSDDouble - pps)+ "WHERE Email = \""+ user +"\"";
    	String sql2 = "UPDATE User SET PPSAmount = " +(RcurrentPPSDouble - (pps * 100))+ ", USDAmount = " + (RcurrentUSDDouble + pps)+ "WHERE Email = \""+ root +"\"";
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql1);
    	preparedStatement.executeUpdate();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql2);
    	preparedStatement.executeUpdate();
		preparedStatement.close();
		
    	resultSet.close();
        statement.close();
		disconnect();
    }
    public void updateSell(String user, double pps) throws SQLException{
    	System.out.println(pps);
    	String currentPPS = "SELECT ppsAmount from User WHERE Email = \""+ user +"\"";
    	String currentUSD = "SELECT USDAmount from User WHERE email = \""+ user +"\"";
    	
    	
    	
    	connect_func();
    	double currentPPSDouble = 0;
    	double currentUSDDouble = 0;
    	System.out.println("TESTTTT");
    	statement = (Statement) connect.createStatement();
    	 ResultSet resultSet = statement.executeQuery(currentPPS);
    	 while (resultSet.next()) {
    		 currentPPSDouble = Double.parseDouble(resultSet.getString("PPSAmount"));
         	
         }
    	 resultSet = statement.executeQuery(currentUSD);
    	 while (resultSet.next()) {
    		 currentUSDDouble = Double.parseDouble(resultSet.getString("USDAmount"));
         	
         }
    	
    	 System.out.println("*********" +  currentPPSDouble + "******"+ currentUSDDouble);
    	
    	String sql = "UPDATE User SET PPSAmount = " +(currentPPSDouble - (pps* 100))+ ", USDAmount = " + (currentUSDDouble + pps)+ "WHERE Email = \""+ user +"\"";
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    	preparedStatement.executeUpdate();
		preparedStatement.close();
		
    	resultSet.close();
        statement.close();
		disconnect();
    }

	public void userLike(String id, String liker) throws SQLException{
		
		String mod1 = "UPDATE Post SET numOfLikes = numOfLikes + 1 WHERE postID = \""+ id +"\"";
		preparedStatement = (PreparedStatement) connect.prepareStatement(mod1);
    	preparedStatement.executeUpdate();
		preparedStatement.close();
		
    	
        
		//disconnect();
		
	}
public void userFollow(String id, String liker) throws SQLException{
		
	//get follower email from postId
	String p = new String();
	String us = "SELECT Email FROM post WHERE postID = \""+ id+"\"";
	statement = (Statement) connect.createStatement();
	ResultSet resultSet = statement.executeQuery(us);
	while (resultSet.next()) {
    	p = resultSet.getString("Email");
    	
    }
	
	String ins = "insert into Follows(followerEmail, followeeEmail) values";	
	String f1 = ins +"(\""+ liker +"\", \""+p+"\")";
	
	statement.executeUpdate(f1);
	
	resultSet.close();
    statement.close();
		//String f1 = ins + 
		//preparedStatement = (PreparedStatement) connect.prepareStatement(mod1);
    	//preparedStatement.executeUpdate();
		//preparedStatement.close();
		
    	
        
		//disconnect();
		
}
 
//PAART3
public String bI() throws SQLException{
	String result = new String();
	String bI = "SELECT followerEmail, max(CountFollowers) from (SELECT followerEmail, count(followeeEmail) CountFollowers FROM follows group by followerEmail) as myalias";
	statement = (Statement) connect.createStatement();
	ResultSet resultSet = statement.executeQuery(bI);
	while (resultSet.next()) {
    	result = resultSet.getString("followerEmail");
    	
    }
	resultSet.close();
    statement.close();
    return result;
}
public String bW() throws SQLException{
	String result = new String();
	String bW = "select P.email, P.PPSAmount from user P where P.PPSAmount = (select max(PPSAmount)from (SELECT Email, PPSAmount FROM user where not email = 'root')as a1);";
	statement = (Statement) connect.createStatement();
	ResultSet resultSet = statement.executeQuery(bW);
	while (resultSet.next()) {
    	result = resultSet.getString("P.email");
    	
    }
	resultSet.close();
    statement.close();
    return result;
}
public String fB() throws SQLException{
	String result = new String();
	String fB = "select T.ReceiverEmail, count(T.SenderEmail) from transactions T where typeofTrans = 'buy' group by T.ReceiverEmail having count(T.SenderEmail) = ( select max(C) from (select ReceiverEmail, count(SenderEmail) as C from transactions where typeofTrans = 'buy' group by ReceiverEmail) as a1);";
	statement = (Statement) connect.createStatement();
	ResultSet resultSet = statement.executeQuery(fB);
	while (resultSet.next()) {
    	result = resultSet.getString("T.ReceiverEmail");
    	
    }
	resultSet.close();
    statement.close();
    return result;
}
public String gF() throws SQLException{
	String result = new String();
	String gF = "(select followeeEmail from follows F, User U where F.followerEmail = U.Email group by followeeEmail and U.Email in (select Email from user where Email != 'root'));";
	statement = (Statement) connect.createStatement();
	ResultSet resultSet = statement.executeQuery(gF);
	while (resultSet.next()) {
    	result = resultSet.getString("followeeEmail");
    	
    }
	resultSet.close();
    statement.close();
    return result;
}
public ArrayList<String> dH() throws SQLException{
	
	ArrayList<String> arrResult = new ArrayList<String>();
	
	String dH = "select Email from user where Email not in (select senderEmail from transactions where typeofTrans = 'sell' or senderEmail = 'root');";
	statement = (Statement) connect.createStatement();
	ResultSet resultSet = statement.executeQuery(dH);
	while (resultSet.next()) {
		arrResult.add(resultSet.getString("Email"));
    	
    }
	resultSet.close();
    statement.close();
    return arrResult;
}
}



