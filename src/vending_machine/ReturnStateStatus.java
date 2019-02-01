package vending_machine;

public class ReturnStateStatus {
	public String ReturnStateStatus (VendingMachine atm) {
		String status = null;
		if (atm.vmstate == atm.getBadItemState()) {
			status = "The current state is the get badItemState";
		}
		else if (atm.vmstate == atm.getHasMoneyState()) {
			status = "The current state is the Has money state";
		}
		else if (atm.vmstate == atm.getGoodItemIdState()) {
			status = "The current state is the Good item state";
		}
		else if (atm.vmstate == atm.getNoItemLeftState()) {
			status = "The current state is the No items left state";
		}
		else if (atm.vmstate == atm.getNoMoneyState()) {
			status = "The current state is the No money state";
		}
		return status;
	}

}
