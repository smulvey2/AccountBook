
public class RecordNode {

	private int day;
	private double amount;
	private RecordNode next = null;
	
	public RecordNode(){
		
	}
	
	public RecordNode(int day, double amount){
		this.day = day;
		this.amount = amount;
	}
	
	public RecordNode(double amount){
		this.amount = amount;
	}
	
	public void setDay(int day) {
		this.day = day;
	}
	
	public int getDay() {
		return day;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setNext(RecordNode next) {
		this.next = next;
	}
	
	public RecordNode getNext() {
		return next;
	}
}
