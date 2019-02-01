package vending_machine;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class SetMoneyTest {
	
	private VendingMachine atm= new VendingMachine();

	@Test
	void testHasMoney() {
		atm.insertMoney(50.0);
		ReturnStateStatus returnStat = new ReturnStateStatus();
		String expected = "The current state is the Has money state";
		String actual = returnStat.ReturnStateStatus(atm);
		assertEquals(expected , actual);
	}
	
	@Test
	void testNoMoney() {
		ReturnStateStatus returnStat = new ReturnStateStatus();
		
		
		String expected = "The current state is the No money state";
		String actual = returnStat.ReturnStateStatus(atm);
		assertEquals(expected , actual);
	}
	
	@Test
	void testGoodItem() {
		atm.insertMoney(50.0);
		atm.chooseAnItem(0);
		ReturnStateStatus returnStat = new ReturnStateStatus();
		
		String expected = "The current state is the Good item state";
		String actual = returnStat.ReturnStateStatus(atm);
		assertEquals(expected , actual);
	}

	
	@Test
	void testNoItem() {
		atm.quantity[0] = 0;
		atm.insertMoney(10.0);
		atm.chooseAnItem(0);
		atm.buyAnItem(10, 0);
		ReturnStateStatus returnStat = new ReturnStateStatus();

		
		String expected = "The current state is the No items left state";
		String actual = returnStat.ReturnStateStatus(atm);
		assertEquals(expected , actual);
	}


}
