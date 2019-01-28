package vending_machine;


import javax.swing.*;

public class Main {
	
	public static void main(String[] args) throws InterruptedException {

		
		GUIVendingMachine gui = new GUIVendingMachine("WELCOME TO THE BEST VENDING MACHINE");
		gui.setSize(500, 1000);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setLocationRelativeTo(null);
		gui.setVisible(true);
		gui.setFocusable(true);
		
		
		
	}
}
