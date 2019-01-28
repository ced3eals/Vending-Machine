package vending_machine;

public class NoItemsLeft  implements VendingMachineState {

	
	VendingMachine vmachine;
	public NoItemsLeft(VendingMachine newVendingMachine) {
		vmachine = newVendingMachine;
	}
	
	
	@Override
	public void insertMoney(double moneyEntered) {
		System.out.println("There is no items left");
		
	}

	@Override
	public void ejectMoney() {
		System.out.println("There is no items left");
		
	}

	@Override
	public void chooseAnItem(int itemId) {
		System.out.println("There is no items left");
		
	}

	@Override
	public void buyAnItem(double moneyEntered, int item) {
		System.out.println("There is no items left");
		
	}

}
