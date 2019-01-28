package vending_machine;

public class BadItemId  implements VendingMachineState {
	
	VendingMachine vmachine;
	public BadItemId(VendingMachine newVendingMachine) {
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
		System.out.println("Your money is now ejected !");
		vmachine.setVendingMachineState(vmachine.badItemId);
		
	}

	@Override
	public void buyAnItem(double moneyEntered, int item) {
		System.out.println("You can't buy anything cause you have not entered a good item id !");		
	}

}
