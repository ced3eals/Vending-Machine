package vending_machine;

public class HasMoney implements VendingMachineState {
	VendingMachine vmachine;
	public HasMoney(VendingMachine newVendingMachine) {
		vmachine = newVendingMachine;
	}

	@Override
	public void insertMoney(double moneyEntered) {
		System.out.println("You have already inserted money, try to buy an item");		
		
	}

	@Override
	public void ejectMoney() {
		System.out.println("Your money is now ejected !");
		vmachine.setVendingMachineState(vmachine.getNoMoneyState());
		
	}

	@Override
	public void chooseAnItem(int itemId) {
		if (itemId >= 0 && itemId < vmachine.itemsId.length) {
			System.out.println("You selected the n°" + itemId + " - " + vmachine.itemsInMachine[itemId]);
			System.out.print("Buying" );
			//vmachine.myTimer(3);
			//vmachine.correctItem = true;
			vmachine.setVendingMachineState(vmachine.getGoodItemIdState());
		} else {
			System.out.println("Please retry using a correct item id");
			vmachine.correctItem = false;
			vmachine.setVendingMachineState(vmachine.getBadItemState());
		}
	}

	@Override
	public void buyAnItem(double moneyEntered, int item) {
		System.out.println("Please insert money please!");
		vmachine.setVendingMachineState(vmachine.getNoMoneyState());

	}

}
