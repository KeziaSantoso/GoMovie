import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Main {
	Scanner scan = new Scanner(System.in);
	ArrayList<String> namesList = new ArrayList<>();
	ArrayList<String> genresList = new ArrayList<>();
	ArrayList<Double> ratingsList = new ArrayList<>();
	ArrayList<String> idList = new ArrayList<>();
	ArrayList<String> statusList = new ArrayList<>();
	
	public Main() {
		Integer chosenMenu = 0;
		String randomIdNumber = "";
		do {
			printGreetings();
			chosenMenu = chooseMenu();
			
			if(chosenMenu == 1) {
				menu1();
			}else if(chosenMenu == 2) {
				menu2();
			}else if(chosenMenu == 3) {
				randomIdNumber = transactionId();
				menu3(randomIdNumber);
			}else if(chosenMenu == 4) {
				menu4();
			}
		}while(chosenMenu != 5);
		
		System.out.println("Goodbye!");
	}
	
	void printGreetings() {
		System.out.println("GoMovie");
		System.out.println("=======");
		System.out.println("1. Add New Movie");
		System.out.println("2. View Movie");
		System.out.println("3. Rent a Movie");
		System.out.println("4. Return a movie");
		System.out.println("5. Exit");
	}
	
	Integer chooseMenu() {
		int choose = 0;
		
		System.out.print(">> ");
		choose = scan.nextInt();
		scan.nextLine();
		
		return choose;
	}
	
	void menu1() {
		String name = "";
		
		do {
			System.out.print("Movie name [5-20 characters]: ");
			name = scan.nextLine();
			
			if(name.length() < 5 || name.length() > 20) {
				System.out.println("Movie's name must be between 5-20 characters!");
			}
		}while(name.length() < 5 || name.length() > 20);
		
		String genre ;
		
		do {
			System.out.print("Movie's genre [Action | Drama | Horror] [case sensitive]: ");
			genre = scan.nextLine();
			
			if(!genre.equals("Action") && !genre.equals("Drama") && !genre.equals("Horror")) {
				System.out.println("Movie genre must be either Action, Drama, or Horror!");
			}
		}while(!genre.equals("Action") && !genre.equals("Drama") && !genre.equals("Horror"));
		
		double rating = 0;
		
		do {
			System.out.print("Movie rating [0.0-5.0]: ");
			rating = scan.nextDouble();
			scan.nextLine();
			
			if(rating < 0 || rating > 5) {
				System.out.println("Movie rating must be between 0.0-5.0!");
			}
		}while(rating < 0 || rating > 5);
		
		namesList.add(name);
		genresList.add(genre);
		ratingsList.add(rating);
		statusList.add("Available");
		
		System.out.println("Movie added successfully!");
	}
	
	void menu2() {
		if(namesList.isEmpty()) {
			System.out.println("Movie list is empty!");
		}else {
			for(int i = 0; i < namesList.size() - 1; i++) {
				for(int j = 0; j < namesList.size() - i - 1; j++) {
					if(ratingsList.get(j).compareTo(ratingsList.get(j + 1)) < 0) {
						Collections.swap(namesList, j, j + 1);
						Collections.swap(genresList, j, j + 1);
						Collections.swap(ratingsList, j, j + 1);
						Collections.swap(statusList, j, j + 1);
					}
				}
			}
			
			for(int i = 0; i < namesList.size(); i++) {
				System.out.println( "| " + (i + 1) + " | " + namesList.get(i) + " | " + genresList.get(i) + " | " + ratingsList.get(i) + " | " + statusList.get(i) + " |");
			}
		}
	}
	
	String transactionId() {
		Random number = new Random();
		
		int randomNumber1 = number.nextInt(10);
		String rN1 = String.valueOf(randomNumber1);
		
		int randomNumber2 = number.nextInt(10);
		String rN2 = String.valueOf(randomNumber2);
		
		int randomNumber3 = number.nextInt(10);
		String rN3 = String.valueOf(randomNumber3);
		
		String randomTransactionId = rN1 + rN2 + rN3;
		
		idList.add(randomTransactionId);
		
		return randomTransactionId;
	}
	
	void menu3(String idNumber) {
		if(namesList.isEmpty()) {
			System.out.println("Movie list is empty!");
		}else {
			menu2();
			
			int rentChoice = 0;
			
			do {
				System.out.print("Which movie do you want to rent? [1-" + namesList.size() + "]\n>>");
				rentChoice = scan.nextInt();
				scan.nextLine();
			}while(rentChoice < 1 || rentChoice > namesList.size());
			
			rentChoice -= 1;
			namesList.get(rentChoice);
			genresList.get(rentChoice);
			ratingsList.get(rentChoice);
			idList.get(rentChoice);
			
			int rentDuration = 0;
			
			do {
				System.out.print("How many days do you want to borrow the movie> [1-7]\n>>");
				rentDuration = scan.nextInt();
				scan.nextLine();
			}while(rentDuration < 1 || rentDuration > 7);
			
			double basePrice = 1000 * ratingsList.get(rentChoice);
			double additionalPrice = 2000 * rentDuration;
			double price = basePrice + additionalPrice;
			
			System.out.println("Rent Movie");
			System.out.println("==========");
			System.out.println("ID: TR-" + idList.get(rentChoice));
			System.out.println("Movie: " + namesList.get(rentChoice));
			System.out.println("Genre: " + genresList.get(rentChoice));
			System.out.println("Rating: " + ratingsList.get(rentChoice));
			System.out.println("Price: " + price);
			
			statusList.set(rentChoice, "Not available");
		}
	}
	
	void menu4() {
		if(namesList.isEmpty()) {
			System.out.println("Movie list is empty!");
		}else {
			menu2();
			
			int returnChoice = 0;
			
			do {
				System.out.print("Which movie do you want to return? [1-1]\n>> ");
				returnChoice = scan.nextInt();
				scan.nextLine();
			}while(returnChoice < 1 || returnChoice > namesList.size());
			
			returnChoice -= 1;
			statusList.set(returnChoice, "Available");
			
			System.out.println("Movie movie returned successfully!");
		}
	}
	public static void main(String[] args) {
		new Main();
	}

}
