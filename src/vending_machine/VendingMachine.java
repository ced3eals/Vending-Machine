package vending_machine;

import java.util.concurrent.TimeUnit;

public class VendingMachine {
	VendingMachineState hasMoney;
	VendingMachineState noMoney;

	VendingMachineState goodItemId;
	VendingMachineState badItemId;

	VendingMachineState noItemsLeft; //If there is no item left
	
	VendingMachineState vmstate;
	
	double money = 0;
	String[] itemsInMachine = {"Coffee", "Tea", "Candy", "Pepsi"}; //A table where I have stored all the vending machine items
	int[] itemsId = {1 ,2 ,3, 4};
	int[] quantity = {5,5,5,5}; //A table where we can find items quantity
	double[] itemsPrices = {2.5, 3, 5, 3}; //A table where we can find different items prices 
	boolean correctItem = false;
	
	
	public VendingMachine() {
		hasMoney = new HasMoney(this); // If we entered money
		noMoney = new NoMoney(this); // If we have not entered money and want to buy
		goodItemId = new GoodItemId(this); //If the selection of the item is good/or bad
		noItemsLeft = new NoItemsLeft(this); //If there is no item left
		badItemId = new BadItemId(this); //If the item you have chosen is not correct
		
		
		
		vmstate = noMoney;
		
		for(int i=0; i<quantity.length; i++) {
			if(quantity[i] <=0) {
				vmstate = noItemsLeft;
			}
		}
	}
		
		void setVendingMachineState(VendingMachineState newVendingMachineState){
			vmstate = newVendingMachineState;
		}
		
		public void setMoneyEntered(double newMoney) {
			money = newMoney;
		}
		public void setQuantityLeft(int itemId, int itemQte) {
			quantity[itemId] = itemQte;
		}
		
		public void setStock(int itemsQuantityEntered, int itemId) {
			if(quantity[itemId] <=0) {
				quantity[itemId] = itemsQuantityEntered;
			}
		}
		
		//Adding a timer
		public void myTimer(int time) {
		try {
			for (int i = 0; i< time; i++) {
				System.out.print(".");
				TimeUnit.SECONDS.sleep(1);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(".");
		}
		
		
		public void insertMoney(double moneyEntered) {
			money = moneyEntered;
			vmstate.insertMoney(moneyEntered);
		}
		
		public void ejectMoney() {
			money = 0;
			vmstate.ejectMoney();
		}
		
		public void chooseAnItem(int itemId) {
			vmstate.chooseAnItem(itemId);
		}
		
		public void buyAnItem(double moneyEntered, int item) {
			vmstate.buyAnItem(moneyEntered, item);
		}
		
		
		public VendingMachineState getHasMoneyState() {
			return hasMoney;
		}
		public VendingMachineState getNoMoneyState() {
			return noMoney;
		}
		public VendingMachineState getGoodItemIdState() {
			return goodItemId;
		}
		public VendingMachineState getBadItemState() {
			return badItemId;
		}
		public VendingMachineState getNoItemLeftState() {
			return noItemsLeft;
		}
		
	}
