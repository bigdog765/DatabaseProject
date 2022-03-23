
public class Transaction {
	protected String from;
    protected String to;
    protected double ppsA;
    protected double usdA;
    protected String when;
    protected String type;
    protected String id;
 
    public Transaction() {
    }
 
    public Transaction(String from, String to,String when, double ppsA,double usdA,String type, String id) {
        this.from = from;
        this.to = to;
        this.ppsA = ppsA;
        this.usdA = usdA;
        this.when = when;
        this.type = type;
        this.id = id;
    }
    public String getFrom() {
    	return from;
    }
    public String getTo() {
    	return to;
    }
    public String getWhen() {
    	return when;
    }
    public double getppsA() {
    	return ppsA;
    }
    public double getusdA() {
    	return usdA;
    }
    public String getType() {
    	return type;
    }
    public String getID() {
    	return id;
    }
 
    
}
