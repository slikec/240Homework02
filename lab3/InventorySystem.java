package lab3;

import Stack.LinkedStack;
import Queue.ArrayQueue;


public class InventorySystem {
	
	private static LinkedStack<Bun> bunItems;
	private static LinkedStack<Patty> pattyItems;
	private static LinkedStack<Lettuce> lettuceItems;
	private static LinkedStack<Tomato> tomatoItems;
	private static LinkedStack<Onion> onionItems;
	private static LinkedStack<Cheese> cheeseItems;
	private static ArrayQueue<Integer> customersLine;
	private static int lostCustomersDay;
	
	private static int countItemOne;
	private static int countItemTwo;
	private static int countItemThree;
	private static int countItemFour;
	private static int countItemFive;
	private static int countItemSix;
	
	private static int wasteBun;
	private static int wasteCheese;
	private static int wastePatty;
	private static int wasteLettuce;
	private static int wasteTomato;
	private static int wasteOnion;
	
	public static void main(String[] args){
		
		setFoodStorage();
		setUpCustomersLine();
		for(int date = 301; date <= 331; date++){
			resetData();
			if((date - 1) % 3 == 0){
				shipmentStart();
			}//end if
			customersOrderFood();
			sortFoodItems();
			printOutData(date);
		}//end for
	}//end main

	private static void setFoodStorage(){
		bunItems = new LinkedStack<>();
		pattyItems = new LinkedStack<>();
		lettuceItems = new LinkedStack<>();
		tomatoItems = new LinkedStack<>();
		onionItems = new LinkedStack<>();
		cheeseItems = new LinkedStack<>();
	}//end setFoodStorage
	
	private static void setUpCustomersLine(){
		customersLine = new ArrayQueue<>(50);
	}//end setUpCustomersLine
	
	private static void resetData(){
		wasteBun = 0;
		wastePatty = 0;
		wasteLettuce = 0;
		wasteTomato = 0;
		wasteOnion = 0;
		wasteCheese = 0;
		
		countItemOne = 0;
		countItemTwo = 0;
		countItemThree = 0;
		countItemFour = 0;
		countItemFive = 0;
		countItemSix = 0;
		lostCustomersDay = 0;
	}//end resetData
	
	private static void shipmentStart(){
		addFood(1000, 700, bunItems, 1);
		addFood(1000, 700, pattyItems, 2);
		addFood(1000, 700, lettuceItems, 3);
		addFood(1000, 700, tomatoItems, 4);
		addFood(1000, 700, onionItems, 5);
		addFood(1000, 700, cheeseItems, 6);
	}//end shipmentStart
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	private static void addFood(int max, int min, LinkedStack stack, int itemsType){
		int foodNum = (int)(Math.random() * max + min);
		for(int i = 0; i < foodNum; i++){
			switch(itemsType){
				case 1: stack.push(new Bun());
						break;
						
				case 2: stack.push(new Patty());
						break;
						
				case 3: stack.push(new Lettuce());
						break;
						
				case 4: stack.push(new Tomato());
						break;
						
				case 5: stack.push(new Onion());
						break;
						
				case 6: stack.push(new Cheese());
						break;
			}//end switch
		}//end for
	}//end addFood
	
	public static int customersLine(){
		for(int i = 1; i < 10; i++){
			int customersLine = (int)(Math.random() * 100 + 1);
			if(customersLine > 50){
			lostCustomersDay = lostCustomersDay + (customersLine - 50);
			}//end if
			else
				return lostCustomersDay;
		}//end for
		return lostCustomersDay;
	}//end customersArrival
	
	private static void customersOrderFood() {
		while (!customersLine.isEmpty()) {
			int orderTypes = customersLine.getFront();
			switch(orderTypes) {
			case 1:
				orderBurger();
				break;
			case 2:
				orderCheeseBurger();
				break;
			case 3:
				orderVeganLettuceWrapBurger();
				break;
			case 4:
				orderBurgerNoOnion();
				break;
			case 5:
				orderCheeseBurgerNoOnion();
				break;
			case 6:
				orderBurgerNoTomato();
				break;
			}//end switch
			customersLine.dequeue();
		}//end while
	}//end customersOrderFood
	
	private static void orderBurger() {
		if (bunItems.getItems() >= 1 && pattyItems.getItems() >= 1 && lettuceItems.getItems() >= 1 && tomatoItems.getItems() >= 1
				&& onionItems.getItems() >= 1){
			bunItems.pop();
			pattyItems.pop();
			lettuceItems.pop();
			tomatoItems.pop();
			onionItems.pop();
			
			countItemOne++;
		}//end if 
		else
			lostCustomersDay++;

	}//end orderBurger
	
	private static void orderCheeseBurger() {
		if (cheeseItems.getItems() >= 1 && bunItems.getItems() >= 1 && pattyItems.getItems() >= 1 && lettuceItems.getItems() >= 1
				&& tomatoItems.getItems() >= 1 && onionItems.getItems() >= 1){
			cheeseItems.pop();
			bunItems.pop();
			pattyItems.pop();
			lettuceItems.pop();
			tomatoItems.pop();
			onionItems.pop();
			
			countItemTwo++;
		}//end if
		else
			lostCustomersDay++;

	}//end orderCheeseBurger
	
	private static void orderVeganLettuceWrapBurger() {
		if (lettuceItems.getItems() >= 2 && tomatoItems.getItems() >= 1 && onionItems.getItems() >=1) {
			lettuceItems.pop();
			lettuceItems.pop();
			tomatoItems.pop();
			onionItems.pop();
			
			countItemThree++;
		}//end if 
		else
			lostCustomersDay++;

	}//end orderVeganLettuceWrapBurger
	
	private static void orderBurgerNoOnion() {
		if (bunItems.getItems() >= 1 && pattyItems.getItems() >= 1 && lettuceItems.getItems() >= 1 && tomatoItems.getItems() >= 1){
			bunItems.pop();
			pattyItems.pop();
			lettuceItems.pop();
			tomatoItems.pop();
			
			countItemFour++;
		} //end if
		else
			lostCustomersDay++;
	}//end orderBurgerNoOnion
	
	private static void orderCheeseBurgerNoOnion() {
		if (cheeseItems.getItems() >= 1 && bunItems.getItems() >= 1 && pattyItems.getItems() >= 1 && lettuceItems.getItems() >= 1 
				&& tomatoItems.getItems() >= 1){
			cheeseItems.pop();
			bunItems.pop();
			pattyItems.pop();
			lettuceItems.pop();
			tomatoItems.pop();
			
			countItemFive++;
		}//end if 
		else
			lostCustomersDay++;
	}//end orderCheeseBurgerNoOnion
	
	private static void orderBurgerNoTomato() {
		if (bunItems.getItems() >= 1 && pattyItems.getItems() >= 1 && lettuceItems.getItems() >= 1 && onionItems.getItems() >= 1){
			bunItems.pop();
			pattyItems.pop();
			lettuceItems.pop();
			onionItems.pop();
			
			countItemSix++;
		}//end if 
		else
			lostCustomersDay++;
	}//end orderBurgerNoTomato
	
	//miss wasteFood
	private static void sortFoodItems() {
		
	}
	
	
	private static void printOutData(int date){
		System.out.println("----------" + "Date:" + date + "----------");
		System.out.println("WasteBun        : " + wasteBun);
		System.out.println("WastePatty		: " + wastePatty);
		System.out.println("WasteLettuce	: " + wasteLettuce);
		System.out.println("WasteTomato 	: " + wasteTomato);
		System.out.println("WasteOnion		: " + wasteOnion);
		System.out.println("WasteCheese		: " + wasteCheese);
		
		System.out.println("CoutItemOne  	: " + countItemOne);
		System.out.println("CoutItemTwo  	: " + countItemTwo);
		System.out.println("CoutItemThree	: " + countItemThree);
		System.out.println("CoutItemFour 	: " + countItemFour);
		System.out.println("CoutItemFive 	: " + countItemFive);
		System.out.println("CoutItemSix  	: " + countItemSix);
		System.out.println("LostCustomersDay:" + lostCustomersDay);
		System.out.println();
	}//end printOut
}//end InventorySystem
