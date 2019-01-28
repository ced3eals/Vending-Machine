package vending_machine;

public class NoMoney implements VendingMachineState {
	VendingMachine vmachine;
	public NoMoney(VendingMachine newVendingMachine) {
		vmachine = newVendingMachine;
	}

	@Override
	public void insertMoney(double moneyEntered) {
		// TODO Auto-generated method stub
		vmachine.money = moneyEntered;

		System.out.println("You inserted " + moneyEntered + "€. Please choose your item now");
		vmachine.setVendingMachineState(vmachine.getHasMoneyState());
	}

	@Override
	public void ejectMoney() {
		System.out.println("Please insert money first");
		
	}

	@Override
	public void chooseAnItem(int itemId) {
		System.out.println("Please insert money first");
		
	}

	@Override
	public void buyAnItem(double moneyEntered, int item) {
		System.out.println("Please insert money first");
		
	}

}
