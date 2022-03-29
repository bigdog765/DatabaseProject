

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
 
/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @author www.codejava.net
 * 
 */
public class ControlServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private InitializeDB InitializeDB;
    private UserDAO UserDAO;
    public HttpSession ses = null;
 
    public void init() {
        InitializeDB = new InitializeDB();
        UserDAO = new UserDAO();
        System.out.println("Server started");
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("doPost started: 000000000000000000000000000");
        doGet(request, response);
        System.out.println("doPost finished: 11111111111111111111111111");
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("doGet started: 000000000000000000000000000"); 
     
        String action = request.getServletPath();
        System.out.println(action);
        try {
        	
            switch (action) {
            case "/userLogin": //check if username exists & if password matches
            	System.out.println("The action is: userLogin");
            	searchUser(request, response);
            	
            	break;
            case "/trans":
            	System.out.println("The action is: get transactions");
            	getTransactions(request, response);
            	break;
            case "/buyPPS":
            	System.out.println("The action is: buy from root");
            	buy(request, response);
            	break;
            case "/insert": //When a new user signs up
                System.out.println("The action is: insert");
            	   insertUser(request, response);
            	   
                break;
            case "/initialize": //When root clicks button
                System.out.println("The action is: initialize database");
            	   deleteTables(request, response);
            	   insertTables(request, response);
            	   insertTuples(request, response);
                break;
            
            default:
                System.out.println("Not sure which action, we will treat it as the list action");
                          	
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
        System.out.println("doGet finished: 111111111111111111111111111111111111");
    }
    

    private void getTransactions(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException  {
    	String user = (String)ses.getAttribute("user");
    	System.out.println(user);
    	
    	ArrayList<Transaction> T = new ArrayList<Transaction>();
    	T = UserDAO.getUserTransactions(user);
    	
    	ses.setAttribute("numOfTrans", T.size());
    	for(int i = 0; i < T.size(); i++) {
    		
    		ses.setAttribute("tranFrom["+ Integer.toString(i) +"]", T.get(i).getFrom());
    		ses.setAttribute("tranTo["+ Integer.toString(i) +"]", T.get(i).getTo());
    		ses.setAttribute("tranWhen["+ Integer.toString(i) +"]", T.get(i).getWhen());
    		ses.setAttribute("tranID["+ Integer.toString(i) +"]", T.get(i).getID());
    		ses.setAttribute("tranpps["+ Integer.toString(i) +"]", T.get(i).getppsA());
    		ses.setAttribute("tranusd["+ Integer.toString(i) +"]", T.get(i).getusdA());
    	} 	
    	request.getRequestDispatcher("userInterface.jsp").forward(request, response);
	}
    
    
    private void buy(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException  {
    	String user = (String)ses.getAttribute("user");
    	double balance = UserDAO.buy(user);

    	String shares = request.getParameter("ppsShares");
    	int s = Integer.parseInt(shares);
    	if(balance == 0 || s * 0.01 > balance) {
        	request.setAttribute("result", "Sorry, insufficent funds");
            
        }
    	else {
    		request.setAttribute("result", "Congrats on your purchase of " + s + " shares of PPS for " + "$"+(double)s / 100);
    	}
    	
    	request.getRequestDispatcher("userInterface.jsp").forward(request, response);
	}

	// after the data of a User are inserted, this method will be called to insert the new User into the DB
    // 
    private void insertUser(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
        System.out.println("insertUser started: 000000000000000000000000000");
     
        String id = request.getParameter("email");
        String pw = request.getParameter("pw");
        String firstN = request.getParameter("fN");
        String lastN = request.getParameter("lN");
        String age = request.getParameter("age");
        String zip = request.getParameter("zip");
        
        int b = Integer.parseInt(age);
        System.out.println("TESTTTT");
        User newUser = new User(id, pw, firstN, lastN, b,zip, 0, 0);
        
        int dup = UserDAO.insert(newUser); //this method will return -1 if a duplicate username is detected
        
        System.out.println("Ask the browser to call the list action next automatically");
        
        if(dup == -1) {
        	request.setAttribute("Duplicate", "Sorry, username already taken");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
        else {
        	ses = request.getSession();
    		ses.setAttribute("user", id);
    		
        	RequestDispatcher dispatcher = request.getRequestDispatcher("result.jsp");       
            dispatcher.forward(request, response);
        }
        System.out.println("insertUser finished: 11111111111111111111111111");   
    }
    
    private void searchUser(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    	String id = request.getParameter("email");
    	String pw = request.getParameter("pw");
    	
    	if(UserDAO.checkForPassword(id,pw) ||id.equals("root")) { //CHANGE BACK
    		ses = request.getSession();
    		ses.setAttribute("user", id);
    		ses.setAttribute("pw", pw);
            RequestDispatcher dispatcher = request.getRequestDispatcher("result.jsp");       
            dispatcher.forward(request, response);
            
        }
        else {
        	request.setAttribute("InvalidUN", "Username and/or password is invalid");
        	request.getRequestDispatcher("login.jsp").forward(request, response);
        	
        }
    }
    
    
    private void deleteTables(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException {
    	
    	InitializeDB.deleteTables();
    	System.out.println("All tables deleted");
    	

    }
    private void insertTables(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException {
    	
    	InitializeDB.insertTables();
    	System.out.println("All tables inserted");
    	

    }
    private void insertTuples(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException {
    	
    	InitializeDB.insertTuples();
    	System.out.println("All tuples inserted");
    	request.setAttribute("ins", "All tables have been reset, with new tuples added");
        request.getRequestDispatcher("rootInterface.jsp").forward(request, response);

    }
 
    

}
