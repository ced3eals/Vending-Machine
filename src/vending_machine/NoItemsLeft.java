package vending_machine;

public class NoItemsLeft  implements VendingMachineState {

	
	VendingMachine vmachine;
	public NoItemsLeft(VendingMachine newVendingMachine) {
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
		if(vmachine.quantity[itemId] > 0 ) {
			vmachine.setVendingMachineState(vmachine.getGoodItemIdState());
		} else {
			System.out.println("There is no items left");
		}
		
	}

	@Override
	public void buyAnItem(double moneyEntered, int item) {
		if(vmachine.quantity[item] > 0 ) {
			vmachine.setVendingMachineState(vmachine.getGoodItemIdState());
		} else {
			System.out.println("There is no items left");
		}
	}

}
