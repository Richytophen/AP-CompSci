/*
 *	Author:  
 *  Date: 
*/

import pkg.*;
import java.util.Scanner;
import java.util.Random;


class starter {
	public static void main(String args[]) {
		boolean check = true;
		Scanner sc = new Scanner(System.in);
		Double accMoney = 0.0;
		String name = new String("");
		
		System.out.println("Welcome to Matthew Hua's financial simulator. This calculator will use the money from the Account class. You can use that money to invest in different stocks and bonds");
	while(check){	
		try{
		System.out.print("How much money would you like to add to your account? (EX: 200.00): \n");
		accMoney = sc.nextDouble();
		break;
	}
		catch(Exception e){
		System.out.println("Enter in a valid value!");
		sc.nextLine();
	}
	
}
	sc.nextLine();
	System.out.print("What's your name? (EX: John Doe)\n");
	name = sc.nextLine();
	Account newAcc = new Account(accMoney,name);
	System.out.println("You've sucessfully created your account " + newAcc.name + "!");
	printState(newAcc);
}
	public static double moneyConvert(double money){
		money = (int)(money*100.0+0.5)/100.0;
		return money;
	}
	
	
	public static void SandP(double money, int time, Account newAcc){
		Scanner sc = new Scanner(System.in);
		int chance = 0;
		double percentage = 0.0;
		int choice = 0;
		double range = 0.0;
		int year = 2024;
		for(int i = 1; i < time+1; i++){
			chance = (int)(Math.random()*100)+1;
			range = 0.0;
			if(chance >= 20){
				range = 0.063 - 0.06;
				percentage = (Math.random()*range)+0.06;
			}
			else if(chance <= 19 && chance >= 10){
				range = 0.083 - 0.072;
				percentage = (Math.random()*range)+0.072;
			}
			else if(chance == 4){
				percentage = 0;
			}
			else{
				range = 0.1;
				percentage = (Math.random()*range)+0.1;
			}
			System.out.println("Currently, the average rate of return in " + year + " for S&P 500 is " + (int)(percentage*1000.0+0.5)/1000.0 + "%");
			money += money*percentage;
			System.out.println("\nYou currently have $" + moneyConvert(money) + " invested and the rate of return for year " + i +  " was %" + (int)(percentage*1000.0+0.5)/1000.0);
			System.out.println("In addition, you currently have $" + moneyConvert(newAcc.money) + " in your account");
			System.out.println("\nWould you like to sell now? (Type 1 if you want to sell and 0 if you don't)");
			choice = sc.nextInt();
			if(choice == 1){
				newAcc.money+=money;
				break;
			}
			year++;
		}
		System.out.println("$" + moneyConvert(money) + " was added to your account.");
		newAcc.money += money;
		
	}
	
	public static void Bonds(Account newAcc){
		Scanner sc = new Scanner(System.in);
		double faceVal = 0.0;
		int maturity = 0;
		int choice = 0;
		double bondPrice = 0.0;
		double interest = 1.05;
		System.out.println("Would you like to purchase a Treasury bond that has a maturity of 10 years? Type '1' for 'YES' and Type '2' for 'NO'");
		choice = sc.nextInt();
		if(choice == 1){
			choice = 0;
			maturity = 10;
			System.out.println("How much would you like the face value of the bond to be?");
			faceVal = sc.nextDouble();
			System.out.println("Prices for bonds are calculated through it's face value by the interest compounded annually.  EX: Face Value/Interest^Maturity");
			bondPrice = faceVal/Math.pow(interest,maturity);
			System.out.println("Would you like to purchase the bond for $" + moneyConvert(bondPrice) + "? Type '1' for 'YES' and '2' for 'NO");
			choice = sc.nextInt();
			if(choice == 1){
				if(newAcc.money - bondPrice < 0){
					System.out.println("Since you do not have enough money to purchase this bond, you will be sent back to the menu.");
					printState(newAcc);
				}
			newAcc.money -= bondPrice;
			System.out.println("By the end of the maturity(10 years) you will gain 5% annual interest of the face value and you will also be paid the face value");
			for(int i = 0; i < maturity; i++){
				System.out.println("On year " + i + " you gained $" + moneyConvert(faceVal*(interest-1)));
				newAcc.money+=faceVal*(interest-1);
			}
			System.out.println("Since you have reached the end of your term, you get your face value back.  $" + moneyConvert(faceVal) + " was added into your account.");
			newAcc.money+=faceVal;
			printState(newAcc);
		}
	}
		else if(choice == 2){
			printState(newAcc);
		}
	}
	public static void Stocks(Account newAcc){
		int stock = 0;
		int year = 2024;
		int chance = 0;
		Scanner sc = new Scanner(System.in);
		double stockValue = 0.0;
		int shares = 0;
		System.out.println("What stock would you like to invest in? (EX: Type 1 for Apple)\n Your options are shown below: \n 1. Apple $189.10 per share \n 2. Nike $92.01 per share \n 3. Amazon $182.77 per share \n 4. Boeing $175.31 per share (VERY RISKY) ");
		stock = sc.nextInt();
		sc.nextLine();
		if (stock == 1){
			stockValue = 189.10;
			System.out.println("You've decided to invest into Apple stock which is worth $190 per share in 2024");
			System.out.println("How many shares would you like to buy?");
			shares = sc.nextInt();
			if(stockValue*shares > newAcc.money){
				System.out.println("Only Invest if you have money!");
				shares = sc.nextInt();
			}
			else{
			newAcc.money = newAcc.money - stockValue*shares;
			System.out.println("You've sucessfully bought " + shares + " shares of Apple!");
			}
			while(true){
				stock = 0;
				chance = (int)(Math.random()*100)+1;
				if(chance >= 40){
					stockValue*=(int)(Math.random()*1.08-1.04)+1.02;
				}
				else if(chance >= 21 && chance <= 39){
					stockValue*=(int)(Math.random()*0.99-0.93)+0.93;
				}
				else{
					stockValue*= 1;
				}
				System.out.println("In the year " + year + " Apple stock is currently $" + moneyConvert(stockValue) + "\n Would you like to sell? (Type '1' for Yes and '2' for NO) \n Your investment is currently at $" + moneyConvert(stockValue*shares));
				stock = sc.nextInt();
				if(stock == 1){
					newAcc.money += stockValue*shares;
					System.out.println("$" + moneyConvert(stockValue*shares) + " was added into your account.");
					break;
				}
				year++;
			}
		}
		if (stock == 2){
			stockValue = 92.01;
			System.out.println("You've decided to invest into Nike stock which is worth $92.01 per share in 2024");
			System.out.println("How many shares would you like to buy?");
			shares = sc.nextInt();
			if(stockValue*shares > newAcc.money){
				System.out.println("Only Invest if you have money! Re-enter a valid value!");
				shares = sc.nextInt();
			}
			else{
			newAcc.money = newAcc.money - stockValue*shares;
			System.out.println("You've sucessfully bought " + shares + " shares of Nike!");
			}
			while(true){
				stock = 0;
				chance = (int)(Math.random()*100)+1;
				if(chance >= 40){
					stockValue*=(int)(Math.random()*0.85-0.78)+0.78;
				}
				else if(chance >= 21 && chance <= 39){
					stockValue*=(int)(Math.random()*1.05-0.98)+0.98;
				}
				else{
					stockValue*= 0.89;
				}
				System.out.println("In the year " + year + " Nike stock is currently $" + moneyConvert(stockValue) + "\n Would you like to sell? (Type '1' for YES and '2' for NO) \n Your investment is currently at $" + moneyConvert(stockValue*shares));
				stock = sc.nextInt();
				if(stock == 1){
					newAcc.money += stockValue*shares;
					System.out.println("$" + moneyConvert(stockValue*shares) + " was added into your account.");
					break;
				}
				year++;
			}
		}
		if (stock == 3){
			stockValue = 182.77;
			System.out.println("You've decided to invest into Amazon stock which is worth $182.77 per share in 2024");
			System.out.println("How many shares would you like to buy?");
			shares = sc.nextInt();
			if(stockValue*shares > newAcc.money){
				System.out.println("Only Invest if you have money! Re-enter a valid value!");
				shares = sc.nextInt();
			}
			else{
			newAcc.money = newAcc.money - stockValue*shares;
			System.out.println("You've sucessfully bought " + shares + " shares of Amazon!");
			}
			while(true){
				stock = 0;
				chance = (int)(Math.random()*100)+1;
				if(chance >= 40){
					stockValue*=(int)(Math.random()*1.27-1.2)+1.2;
				}
				else if(chance >= 21 && chance <= 39){
					stockValue*=(int)(Math.random()*1.19-1.09)+1.09;
				}
				else{
					stockValue*= 1.07;
				}
				System.out.println("In the year " + year + " Amazon stock is currently $" + moneyConvert(stockValue) + "\n Would you like to sell? (Type '1' for YES and '2' for NO) \n Your investment is currently at $" + moneyConvert(stockValue*shares));
				stock = sc.nextInt();
				if(stock == 1){
					newAcc.money += stockValue*shares;
					System.out.println("$" + moneyConvert(stockValue*shares) + " was added into your account.");
					break;
				}
				year++;
			}
		}
		if (stock == 4){
			stockValue = 182.77;
			System.out.println("You've decided to invest into Boeing stock which is worth $182.77 per share in 2024 (Why would you do this)");
			System.out.println("How many shares would you like to buy?");
			shares = sc.nextInt();
			if(stockValue*shares > newAcc.money){
				System.out.println("Only Invest if you have money! Re-enter a valid value!");
				shares = sc.nextInt();
			}
			else{
			newAcc.money = newAcc.money - stockValue*shares;
			System.out.println("You've sucessfully bought " + shares + " shares of Boeing(Big mistake)!");
			}
			while(true){
				stock = 0;
				chance = (int)(Math.random()*100)+1;
				if(chance >= 40){
					stockValue*=(int)(Math.random()*0.82-0.73)+0.73;
				}
				else if(chance >= 21 && chance <= 39){
					stockValue*=(int)(Math.random()*0.93-0.83)+0.83;
				}
				else{
					stockValue*= 1.2;
				}
				System.out.println("In the year " + year + " Boeing stock is currently $" + moneyConvert(stockValue) + "\n Would you like to sell? (Type '1' for YES and '2' for NO) \n Your investment is currently at $" + moneyConvert(stockValue*shares));
				stock = sc.nextInt();
				if(stock == 1){
					newAcc.money += stockValue*shares;
					System.out.println("$" + moneyConvert(stockValue*shares) + " was added into your account.");
					break;
				}
				year++;
			}
		}
	}
	public static void printState(Account newAcc){
		Scanner sc = new Scanner(System.in);
		double amountIn = 0;
		int time = 0;
		int choice = 0;
		while(true){
			try{
				System.out.println("\nYour options are shown below:"); 
				System.out.println("You currently have $" + moneyConvert(newAcc.money) + " in your account");
				System.out.println(" \n 1.Invest in S&P 500 \n 2. Invest in Government Bond \n 3. Invest in company stock \n 4. Exit");
				choice = sc.nextInt();
				break;
			}
			catch(Exception e){
				System.out.println("Enter a valid value!");
				sc.nextLine();
			}
		}
		if(choice == 1){
			while(true){
				System.out.println("How much would you like to invest in the S&P 500?");
				amountIn = sc.nextDouble();
				if(newAcc.money - amountIn < 0){
					System.out.println("Enter only the money you have!");
					sc.nextLine();
					}
				else{
					break;
				}	
			}	
				System.out.println("How many years would you like your money to be invested for?");
				time = sc.nextInt();
				if(time == 0){
					time = 1;
				}
				newAcc.money -= amountIn;
				SandP(amountIn, time, newAcc);
				printState(newAcc);
			}
			if(choice == 2){
				Bonds(newAcc);
				printState(newAcc);
			}
			if(choice == 3){
				Stocks(newAcc);
				printState(newAcc);
			}
			if (choice == 4){
				System.exit(0);
			}
	}
}