package vending_machine;

public class GoodItemId implements VendingMachineState {
	
	VendingMachine vmachine;
	public GoodItemId(VendingMachine newVendingMachine) {
		vmachine = newVendingMachine;
	}
	
	
	@Override
	public void insertMoney(double moneyEntered) {
		System.out.println("You have already inserted money");
		vmachine.setVendingMachineState(vmachine.getHasMoneyState());
	}

	@Override
	public void ejectMoney() {
		System.out.println("Your money is now ejected !");
		vmachine.setVendingMachineState(vmachine.getNoMoneyState());
	}

	@Override
	public void chooseAnItem(int itemId) {
		
	}

	@Override
	public void buyAnItem(double moneyEntered, int item) {
		if (vmachine.money < vmachine.itemsPrices[item] ) {
			System.out.println("Sorry, you don't have enough money :(");
		
		} else {
			 if(vmachine.quantity[item]<=0) {
				 vmachine.setVendingMachineState(vmachine.getNoItemLeftState());
					System.out.println("There is no item left");
					//Just add a time here
					System.out.print("Adding some new items");
					vmachine.myTimer(3);
					vmachine.setStock(5, item);
					System.out.println("We just added 5 items into " + vmachine.itemsInMachine[item] );
					 //vmachine.setVendingMachineState(vmachine.getGoodItemIdState());

			 
			 
			 }
			 vmachine.setVendingMachineState(vmachine.getGoodItemIdState());
			 vmachine.setMoneyEntered(vmachine.money - vmachine.itemsPrices[item]);// Give the change
			 
			 vmachine.setQuantityLeft(item, vmachine.quantity[item] - 1); // Decrease the quantity
			 
			System.out.println("You just bought a " + vmachine.itemsInMachine[item] + "! Thanks for your purchase");


			}
		
		}
		
	}
