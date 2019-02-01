package vending_machine;

public interface VendingMachineState {
	void insertMoney(double moneyEntered);
	void ejectMoney();
	void chooseAnItem(int itemId);
	void buyAnItem(double moneyEntered, int item);
		
}