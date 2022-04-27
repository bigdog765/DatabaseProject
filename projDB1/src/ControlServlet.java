

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
import java.text.DecimalFormat;
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
 //test
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
            case "/LikeTweet":
            	System.out.println("The action is: Liked a tweet");
            	LikeATweet(request, response);
            	break;
            case "/FollowTweet":
            	System.out.println("The action is: follow a user");
            	followTweet(request, response);
            	break;
            case "/CommentTweet":
            	System.out.println("The action is: go to comment page");
            	commentTweet(request, response);
            	break;
            case "/PostComment":
            	System.out.println("The action is: comment on a post");
            	postComment(request, response);
            	break;
            case "/Post":
            	System.out.println("The action is: post a tweet");
            	post(request, response);
            	break;
            case "/tipUser":
            	System.out.println("The action is: tip a user");
            	userTip(request, response);
            	break;
            case "/Tip":
            	System.out.println("The action is: tip a user");
            	tip(request, response);
            	break;
            case "/buyPPS":
            	System.out.println("The action is: buy from root");
            	buy(request, response);
            	break;
            case "/sellPPS":
            	System.out.println("The action is: sell to root");
            	sell(request, response);
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
            //part3
            case "/bigInfluencers":
            	bigInfluencers(request, response);
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
    
    
    
    private void bigInfluencers(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    	String bI = UserDAO.bI();
    	request.setAttribute("bI", "The user with the most followers is: " + bI);
    	
    	request.getRequestDispatcher("rootInterface.jsp").forward(request, response);
    }
    private void LikeATweet(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    	request.setAttribute("LikeResult", "Post Liked");
    	
    	String postLiked =request.getParameter("like");
    	String liker = (String)ses.getAttribute("user");
    	
        System.out.print(postLiked);
        
        if(ses.getAttribute(postLiked) == null) {
        	UserDAO.userLike(postLiked, liker);
            ses.setAttribute(postLiked, true);
        }
        else request.setAttribute("LikeResult", "You have already liked this post");
        

        
    	request.getRequestDispatcher("tweetInterface.jsp").forward(request, response);
    }
    private void followTweet(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    	request.setAttribute("FollowResult", "You are now following this user");
    	
    	String followeePostID =request.getParameter("follow");
    	
    	String liker = (String)ses.getAttribute("user");
    	System.out.print(followeePostID);
    	
    	if(ses.getAttribute(followeePostID) == null) {
    		UserDAO.userFollow(followeePostID, liker);
            ses.setAttribute(followeePostID, true);
        }
        else request.setAttribute("FollowResult", "You are already following this user");
    	
    	
    	
    	
    	request.getRequestDispatcher("tweetInterface.jsp").forward(request, response);
    }
    private void commentTweet(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    	
    	request.getRequestDispatcher("comment.jsp").forward(request, response);
    }
    private void postComment(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    	
    	String comment = request.getParameter("comment");
    	request.setAttribute("CommentResult", "Comment was posted!");
    	request.getRequestDispatcher("comment.jsp").forward(request, response);
    }
    private void post(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    	
    	String post = request.getParameter("Tweet");
    	request.setAttribute("TweetResult", "Tweet was posted!");
    	request.getRequestDispatcher("tweet.jsp").forward(request, response);
    }
    private void userTip(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    	
    	String userTipped = request.getParameter("tip");
    	System.out.println(userTipped);
    	ses.setAttribute("userTipped", userTipped);
    	
    	request.getRequestDispatcher("tip.jsp").forward(request, response);
    }
    private void tip(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException  {
    	String user = (String)ses.getAttribute("user");
    	
    	
    	//get users balance in dollars
    	double balance = UserDAO.buy(user);

    	String tipAmount = request.getParameter("tipAmount");
    	int s = Integer.parseInt(tipAmount);
    	System.out.println(s);
    	if(balance == 0 || (double)s > balance) {
        	request.setAttribute("resultTip", "Sorry, insufficent funds");
        	System.out.println("You dont have enough money");
        }
    	else {
    		//insert this transaction into the database
    		String p = (String)ses.getAttribute("userTipped");
    		String id = String.format("%x",(int)(Math.random()*10000000)); 
    		
    		UserDAO.insertTransaction(user,p, "-time-",s, s/100,"tip",id);
    		DecimalFormat df = new DecimalFormat();
    		df.setMaximumFractionDigits(2);
    		request.setAttribute("resultTip", "Thanks for tipping " + s + " shares of PPS to " + p);
    	}
    	
    	request.getRequestDispatcher("tip.jsp").forward(request, response);
	}
    
    
    
    private void buy(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException  {
    	String user = (String)ses.getAttribute("user");
    	double balance = UserDAO.buy(user);

    	String shares = request.getParameter("ppsShares");
    	int s = Integer.parseInt(shares);
    	System.out.println(s);
    	if(balance == 0 || (double)s * 0.01 > balance) {
        	request.setAttribute("resultBuy", "Sorry, insufficent funds");
        	System.out.println("You dont have enough money");
        }
    	else {
    		//insert this transaction into the database
    		ses.setAttribute("tuples", 1);
    		UserDAO.updateBuy(user, (double)s * 0.01);
    		
    		
    		
    		String id = String.format("%x",(int)(Math.random()*10000000)); 
    		UserDAO.insertTransaction(user,"root", "-time-",s, s/100,"buy",id);
    		DecimalFormat df = new DecimalFormat();
    		df.setMaximumFractionDigits(2);
    		request.setAttribute("resultBuy", "Congrats on your purchase of " + s + " shares of PPS for " + "$"+df.format((double)s / 100));
    	}
    	
    	request.getRequestDispatcher("userInterface.jsp").forward(request, response);
	}
    private void sell(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException  {
    	String user = (String)ses.getAttribute("user");
    	double balance = UserDAO.sell(user);

    	String shares = request.getParameter("ppsSharesSell");
    	int s = Integer.parseInt(shares);
    	
    	if(balance == 0 || (double)s > balance) {
        	request.setAttribute("resultSell", "Sorry, insufficent funds");
            
        }
        
    	else {
    	
    		//insert this transaction into the database
    	ses.setAttribute("tuples", 1);
    	UserDAO.updateSell(user, (double)s * 0.01);
    		
    		String id = String.format("%x",(int)(Math.random()*10000000)); 
    		UserDAO.insertTransaction("root",user, "-time-",s, s/100,"sell",id);
    		DecimalFormat df = new DecimalFormat();
    		df.setMaximumFractionDigits(2);
    		request.setAttribute("resultSell", "Congrats on your selling of " + s + " shares of PPS. You recieved " + "$"+df.format((double)s / 100));
    	//} un comment this out !!!!
    	
    	
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
        String pwR = request.getParameter("pwR");
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
        else if(!pw.equals(pwR)) {
        	request.setAttribute("Duplicate", "Sorry, passwords dont match");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
        else {
        	ses = request.getSession();
    		ses.setAttribute("user", id);
    		ses.setAttribute("tuples", 0);
    		ses.setAttribute("numOfTrans", 0);
    		
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
    		ses.setAttribute("tuples", 0);
    		ses.setAttribute("numOfTrans", 0);
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
