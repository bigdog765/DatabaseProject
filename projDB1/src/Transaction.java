
public class Transaction {
	protected String email;
    protected String password;
    protected String fName;
    protected String lName;
    protected int age;
    protected double usd;
    protected double pps;
 
    public Transaction() {
    }
 
    public Transaction(String from, String to,double ppsA,double usdA,String when) {
        this.from = from;
        this.to = to;
        this.ppsA = ppsA;
        this.usdA = usdA;
        this.when = when;
    }
 
    
   
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public String getfName() {
        return fName;
    }
 
    public void setfName(String fName) {
        this.fName = fName;
    }
 
    public String getlName() {
        return lName;
    }
 
    public void setlName(String lName) {
        this.lName = lName;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public double getUSD() {
        return usd;
    }
    public void setUSD(double usd) {
        this.usd = usd;
    }
    public double getPPS() {
        return pps;
    }
    public void setPPS(double pps) {
        this.pps = pps;
    }
}
