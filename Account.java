/*
 *	Author:  
 *  Date: 
*/

import pkg.*;
import java.util.Scanner;
import java.util.Random;


class Account{
	public String name;
	public double money;
	
	public Account(){
		money = 0.0;
	}
	public Account(double money, String name){
		this.money = money;
		this.name = name;
	}
	
		public void addMoney(double newMoney){
			money += newMoney; 
		}
		public double getMoney(){
			return money;
		}
		public String getName(){
			return name;
		}
		public void changeName(String name){
			this.name = name;
		}

}
