import java.awt.List;
import java.util.Scanner;

public class AccountBook {
	private RecordNode head = null;
	private RecordNode tail = null;
	private double balance = 0;
	
	/**
	 * Insert a record node into the account book. The money amount can be either
	 * negative, meaning the user spent money, or positive, meaning the user
	 * received money. If in the account book there are records on the same day, you
	 * need to insert the record after the last of them; Otherwise, you need to
	 * insert the record between records on earlier days and those on later days.
	 * 
	 * @param day
	 *The day of the record to be inserted.
	 * @param amount
	 *The money amount of the record.
	 */
	public void insertRecord(int day, double amount) {
		RecordNode node = new RecordNode(day, amount);
		node.setDay(day);
		if (day < 1 || day > 31) {
			System.out.println("WARNING: Invalid day number."); 
		}
		node.setAmount(amount);
		if (head == null) {
			head = node;
			tail = node;
		}	
		else if (head != null){ 
			RecordNode current = head;
			RecordNode previous = null;
			while(current != null) { 
				if(current.getDay() > day) {	
					node.setNext(current);
					previous.setNext(node);
					return;
				}
				previous = current;
				current = current.getNext();
			}	
				tail.setNext(node);
				tail = node;
		}
	}
	
	/**
	 * Prepend a record into the account book. The day of the record should be the
	 * same as the EARLIEST record in the book. If there haven't been any records in
	 * the book yet, you should show user the warning message "WARNING: Unable to
	 * prepend a record, for no records in the account book yet." by printing it to
	 * the console.
	 * 
	 * @param amount
	 *The money amount of the record to be prepended.
	 */
	public void prependRecord(double amount) {
		if(head == null) { 
			System.out.println("WARNING: Unable to prepend a record, for no records in the account book yet.");
		}     
		else { 
			RecordNode node = new RecordNode(amount);
			RecordNode current = head;  
			node.setNext(current);
			node.setDay(current.getDay());
			head = node; 
		}	
	}
	
	/**
	 * Append a record into the account book. Similar as above, the day of the
	 * record should be the same as the LATEST record. If there haven't no records
	 * in the book yet, you should show user the warning message "WARNING: Unable to
	 * append a record, for no records in the account book yet.".
	 * 
	 * @param amount
	 *The money amount of the record to be appended.
	 */
	public void appendRecord(double amount) {
		if(head == null) { 
			System.out.println("WARNING: Unable to append a record, for no records in the account book yet.");
		}     
		else {
			RecordNode node = new RecordNode(amount);
			RecordNode current = tail;
			current.setNext(node); 
			node.setDay(current.getDay());
			tail = node; 
			node.setNext(null); 
	}
}	
	/**
	 * Remove a record from the account book. The two arguments identify which
	 * record to remove. E.g., with day being 4 and seq_num being 2, the user
	 * would like to delete the second record on the 4th day. If the number of
	 * records on day is smaller than seq_num, you show user the warning message
	 * "WARNING: Unable to remove a record, for not enough records on the day
	 * specified.".
	 * 
	 * @param day
	 *The day of the record to be removed.
	 * @param seq_num
	 *The sequence number of the record within the day of it.
	 */
	public void removeRecord(int day, int seq_num) {
		 RecordNode currentNode = head;
	        RecordNode prevNode = null;
	        int counter = 0;
	    	if (seq_num < 1) {
	     		System.out.println("WARNING: Invalid sequence number.");
	     		return;
	     		}	
	        if (day < 1 || day > 31) {
	     		System.out.println("WARNING: Invalid day number.");
	     		return;
	     	}	     	
	     	while (currentNode != null) {
	            if (currentNode.getDay() == day) {
	                counter++;
	            } else {
	            	counter = 0;
	            }
	            if ((counter == seq_num) && (currentNode.getDay() == day)) {
	                if (prevNode == null) {
	                    balance -= currentNode.getAmount();
	                    head = currentNode.getNext();
	                    return;
	                }
	                if (currentNode.getNext() == null) {
	                    balance -= currentNode.getAmount();
	                    prevNode.setNext(null);
	                    tail = prevNode;
	                    return;
	                }
	                if (currentNode == head) {
	                    balance -= currentNode.getAmount();
	                    head = currentNode.getNext();
	              
	                    return;
	                } else {
	                    balance=balance- currentNode.getAmount();
	                    prevNode.setNext(currentNode.getNext());
	                    return;
	                }
	            }
	            prevNode = currentNode;
	            currentNode = currentNode.getNext();

	        }
	        System.out.println("WARNING: Unable to remove a record, for not enough records on the day specified.");
	}
	/**
	 * Modify a record in the account book. Similar as above, day and seq_num
	 * identify which record to modify, while amount indicates the excepted money
	 * amount of the record after modification E.g., with the three arguments being
	 * 4 2 100 respectively, the user would like to modify the second record on the
	 * 4th day, and change the amount to 100. If the number of records on day is
	 * smaller than seq_num , you should show user the warning message "WARNING:
	 * Unable to modify a record, for not enough records on the day specified.".
	 * 
	 * @param day
	 *The day of the record to be modified.
	 * @param seq_num
	 *The sequence number of the record within the day of it.
	 * @param amount
	 *The amount of the record after modified.
	 */
	public void modifyRecord(int day, int seq_num, double amount) {
		if (day > 31 || day < 1) {
			System.out.println("WARNING: Invalid day. Please set day to valid number (1 to 31).");
			return;
		}
		if (head == null){
			return;
		}
		RecordNode node = new RecordNode(day);
		node = head;
		int counter = 0;
		while (node.getDay() != day) {
			counter++;
		}
		for (int i = 0; i < counter; i++) {
			node = node.getNext();
		}
		for (int i = 0; i <= seq_num - 1; i++) {
			node = node.getNext();
		}
		double changedAmount = node.getAmount();
		node.setAmount(amount);
		balance = balance + amount - changedAmount;
	}
	
	/**
	 * Show user the overall balance by printing some leading textual prompt
	 * followed by the balance to the console, e.g., "Balance: -90.95". The balance
	 * should be initialized as 0 at first, and accumulates as the user
	 * insert/prepend/append/remove/modify records.
	 */
	public void showBalance() {
		System.out.printf("Balance: $%.2f\n", balance);
	}
	/**
	 * Display all the records so far as well as the overall balance. If there
	 * haven't been no records in the book yet, you should display "No records in
	 * the book yet." before displaying the account balance.
	 */
	public void display() {
		if (head == null) {
			System.out.println("No records in the book yet.");
			System.out.println();
			showBalance();
			return;
		}
		RecordNode node = head;
		System.out.println("Day      Amount");
		System.out.println("================");
		while (node != null) {
			System.out.print(node.getDay());
			System.out.printf("        $%.2f\n", node.getAmount());
			node = node.getNext();
		}
			System.out.println();
			showBalance();
	}
	/**
	 * Show the records and accumulated balance on the day specified. If in the
	 * account book there haven't been any records on the day specified yet, you
	 * should display "No records on the day yet." before displaying the accumulated
	 * balance.
	 * 
	 * @param day
	 *The day of the summary to be shown.
	 */
	public void showDaySummary(int day) {
		balance = 0;
		RecordNode currentNode = new RecordNode(day);
		currentNode = head;

if (day > 31 || day < 1) {
			System.out.println("WARNING: Invalid day. Please set day to valid number (1 to 31).");
			return;
		}
		if (head == null || currentNode.getDay()!= day){
			System.out.println("No records on the display yet.");
			System.out.println();
			balance = 0;
			System.out.printf("Accumulated balance:$%.2f\n", balance);
			return;
		}
		while(currentNode.getDay() != day) {
			currentNode = currentNode.getNext();
			if (currentNode == null) {
				break;
			}
		}
		System.out.println("Day     Amount \n==================");
		while(currentNode.getDay()== day && currentNode != null) {
			double dayBalance = currentNode.getAmount();
			
			System.out.println(currentNode.getDay() + "      " + dayBalance);
			balance = balance + currentNode.getAmount();
			currentNode = currentNode.getNext();
			if (currentNode == null) {
				break;
			}
		}
		System.out.println();
		System.out.printf("Accumulated balance:$%.2f\n", balance);
	}
	
	public static void main(String[] args) {

		Scanner scnr = new Scanner(System.in);
		AccountBook account = new AccountBook();
		String[] command;
		do {
		System.out.println("=====Account Book Program===== "
				+ "\n 1. Enter 'i' to insert a record into the account book "
				+ "\n 2. Enter 'p' to prepend a record into the account book "
				+ "\n 3. Enter 'a' to append a record into the account book "
				+ "\n 4. Enter 'r' to remove a record from the account book	"
				+ "\n 5. Enter 'm' to modify a record in the account book	"
				+ "\n 6. Enter 'b' to show the overall balance	"
				+ "\n 7. Enter 'd' to display all the records and the overall balance	"
				+ "\n 8. Enter 's' to show the records and accumulated balance on a day	"
				+ "\n 0. Enter 'q' to quit the program	\n==========================");
		System.out.print("Please enter your command:");
			 command = scnr.nextLine().split(" ");
		
			if (command[0].equalsIgnoreCase("i")) {
				int day1 = Integer.parseInt(command[1]);
				double amount1 = Double.parseDouble(command[2]);
				account.insertRecord(day1, amount1);
			}
			if (command[0].equalsIgnoreCase("m")) {
				int day1 = Integer.parseInt(command[1]);
				int seq_num1 = Integer.parseInt(command[2]);
				double amount1 = Double.parseDouble(command[3]);
				account.modifyRecord(day1, seq_num1, amount1);
			}

			if (command[0].equalsIgnoreCase("d")) {
				account.display();
			}

		}while (!(command[0].equalsIgnoreCase("q")));
	}

}
