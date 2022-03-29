



public class User {
    protected String email;
    protected String password;
    protected String fName;
    protected String lName;
    protected String zip;
    protected int age;
    protected double usd;
    protected double pps;
 
    public User() {
    }
 
    public User(String email, String password, double usd, double pps) {
        this.email = email;
        this.password = password;
        this.usd = usd;
        this.pps = pps;
    }
 
    public User(String email, String password, String fName, String lName, int age,String zip, double usd, double pps) {
        this.email = email; this.password = password; this.fName = fName; this.lName = lName; this.age = age; this.zip = zip;
        this.usd = 1000;
        this.pps = 0;
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
    public String getZip() {
        return zip;
    }
 
    public void setZip(String zip) {
        this.zip = zip;
    }
    
    
    
}