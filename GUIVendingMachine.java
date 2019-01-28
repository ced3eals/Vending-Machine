package vending_machine;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.KeyEvent;


public class GUIVendingMachine extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	VendingMachine atm = new VendingMachine();
	String choice, moneyEntered;
    ReturnStateStatus returnStat = new ReturnStateStatus();
    
    //Money indicator
    JTextField moneyIndicator = new JTextField("Your Money : " + atm.money + "€");
    
    //To show the current state
    JTextField outputCurrentState = new JTextField(returnStat.ReturnStateStatus(atm));
    
    //For the quantity
    Object[][] rowData = { 
			{atm.itemsInMachine[0], atm.quantity[0]},
			{atm.itemsInMachine[1], atm.quantity[1]},
			{atm.itemsInMachine[2], atm.quantity[2]},
			{atm.itemsInMachine[3], atm.quantity[3]},
		};
		
	Object[] colNames = {"Item", "Quantity" };
	
	JTable tableWithQuantity = new JTable(rowData, colNames);
	    
	
	public class itemChooser implements KeyListener {
		public void keyPressed(KeyEvent e){
			switch(e.getKeyCode())
			{
				case KeyEvent.VK_C: //Coffee
					goodyTextButtons[0].doClick();
					break;
				case KeyEvent.VK_T: //Tea
					goodyTextButtons[1].doClick();
					break;
				case KeyEvent.VK_R: //Redbull
					goodyTextButtons[2].doClick();
					break;
				case KeyEvent.VK_P: //Pepsi
					goodyTextButtons[3].doClick();
					break;
				default:
					break;
			}
		}
		
		public void keyReleased(KeyEvent e){}
		public void keyTyped(KeyEvent e){}
	}
	
	public class focusSet extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			requestFocusInWindow();
			System.out.println("nothing");
		}
	}

    // ActionListener class to display Goody prices
    private class DisplayPricesActionListener implements ActionListener{
        final JTextField priceField;	// Where to diplay the price
        final JButton[] goodyImages;	// What button objects can be clicked

        
        public DisplayPricesActionListener(JTextField priceField,
                JButton[] goodyImages) {
            super();
            this.priceField = priceField;
            this.goodyImages = goodyImages;
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i=0; i<goodyNames.length; i++){	// Check which button was clicked
                // and display appropriate price
                if (e.getSource().equals(goodyImages[i])){
                    DecimalFormat df = new DecimalFormat("#.00##");
                    String priceToDisplay = df.format(goodyPrices[i]);
                    priceField.setText(priceToDisplay + "€");
                }
            }	
        }
    }

    // ActionListener class to display "here is your goody" when 
    // the right price amount is entered
    private class PayForGoodyActionListener implements  ActionListener {

        final JButton[] selectButtons;		// Array of buttons to choose items
        final JButton whereButton;
        final JTextField outputChangeTF;	// Text Field where "here is your goody is displayed" 
        final JTextField outputCurrentState;

        
        public PayForGoodyActionListener(JButton[] selectButtons,
                JButton whereButton,
                JTextField inputPriceTF, JTextField outputChangeTF, JTextField outputCurrentState) {
            super();
            this.whereButton = whereButton;
            this.selectButtons = selectButtons;
            this.outputCurrentState = outputCurrentState;
            this.outputChangeTF = outputChangeTF;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
        	
            int selectedButtonPos = -1;
            for (int i=0; i<selectButtons.length; i++){
                if (e.getSource().equals(selectButtons[i])){
                    selectedButtonPos = i;
                    break;
                }
            }

            try{

            	//outputCurrentState.setText(returnStat.ReturnStateStatus(atm));
                outputChangeTF.setText("Correct");
                outputChangeTF.setForeground(Color.blue);
            	
            	atm.chooseAnItem(selectedButtonPos);
            	outputCurrentState.setText(returnStat.ReturnStateStatus(atm));

            	
            	atm.buyAnItem(atm.money, selectedButtonPos);
            	outputCurrentState.setText(returnStat.ReturnStateStatus(atm));
            	outputCurrentState.setForeground(Color.magenta);
            	
            	
            	whereButton.setText("Here is your " + goodyTips[selectedButtonPos]);
            	if(atm.quantity[selectedButtonPos] == 0 ) {
                    outputChangeTF.setText("/!\\ No " + atm.itemsInMachine[selectedButtonPos] + " items left, automatic refilling...");
                    outputChangeTF.setForeground(Color.red);
                	outputCurrentState.setText(returnStat.ReturnStateStatus(atm));

            	}
            	moneyIndicator.setText("Your credit is now : " + atm.money + "€");
            	tableWithQuantity.setValueAt(atm.quantity[selectedButtonPos], selectedButtonPos, 1);;

            } catch (Exception exception) {}
        }

    }
    
	java.net.URL coffee = getClass().getResource("coffee.png");		
	java.net.URL tea = getClass().getResource("tea.png");		
	java.net.URL redbull = getClass().getResource("redbull.png");		
	java.net.URL pepsi = getClass().getResource("pepsi.png");		


    final int goodyType = 4;
    final String[] goodyImageNames = {
    		"coffee.png",
    		"tea.png",
    		"redbull.png",
    		"pepsi.png",
    		};
    final String[] goodyNames = atm.itemsInMachine;
    final String[] goodyTips = atm.itemsInMachine;
    
    
    final double[] goodyPrices = atm.itemsPrices;
    
    public static JButton[] goodyTextButtons;

    public GUIVendingMachine(String s) {
        super(s);
		itemChooser itemSelect = new itemChooser();
		focusSet focus = new focusSet();
		addMouseListener(focus);
		addKeyListener(itemSelect);
				
	    
        // Image of a dollar bill
		java.net.URL euroUrl = getClass().getResource("euros.png");		
        ImageIcon euroImage1 = new ImageIcon(euroUrl);
        ImageIcon euroImage2 = new ImageIcon(euroImage1.getImage().getScaledInstance(
                    100, 100, java.awt.Image.SCALE_SMOOTH));			
        JButton oneDollarBotton = new JButton(euroImage2);

        // The panel of price
        JPanel pPrice = new JPanel();
        pPrice.setLayout(new BoxLayout(pPrice, BoxLayout.Y_AXIS));
        
        
        JPanel pPriceText = new JPanel();
        pPriceText.setLayout(new FlowLayout());
        
        
        JLabel priceTextLabel = new JLabel("Click on item to see prices");
        JTextField amountTextField = new JTextField("Select one item");
        amountTextField.setEditable(false);

        pPriceText.add(priceTextLabel);
        pPriceText.add(amountTextField);
        
        //Enter the money
        JTextField enterMoneyTextField = new JTextField("Enter Money Here...");
        enterMoneyTextField.addMouseListener(new MouseAdapter() {
        	 @Override
        	  public void mouseClicked(MouseEvent e) {
        		 enterMoneyTextField.setText("");
        	  }
        });
        
        pPrice.add(pPriceText);
        pPrice.add(enterMoneyTextField);
        
        
        // The panel of "return amount"
        JTextField returnAmountTextField = new JTextField("Correct");
        returnAmountTextField.setForeground(Color.blue);
        
        
        //To insert the money
        JButton money_input = new JButton("Insert Money");
        money_input.addActionListener(new ActionListener() {

        	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				returnAmountTextField.setText("Correct");
		        returnAmountTextField.setForeground(Color.blue);
		        
				String priceEntered = enterMoneyTextField.getText();
				atm.money = Double.parseDouble(priceEntered);
				atm.insertMoney(Double.parseDouble(priceEntered));
            	outputCurrentState.setText(returnStat.ReturnStateStatus(atm));
            	outputCurrentState.setForeground(Color.blue);
            	moneyIndicator.setText("Your credit is now : " + atm.money + "€");

				}
        	
        });
        


        
        //To eject the money
        JButton money_eject = new JButton("  Eject Money");
        money_eject.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
            	returnAmountTextField.setText("Here is your money: " + atm.money + "€");
				atm.ejectMoney();
				outputCurrentState.setText(returnStat.ReturnStateStatus(atm));
            	outputCurrentState.setForeground(Color.red);
            	moneyIndicator.setText("Your credit is now : " + atm.money + "€");

				}
        	
        });
        


        
        
        //The state
        outputCurrentState.setEditable(false);
        outputCurrentState.add(new JLabel ("Current State "));

        
        // The "Choose an item" panel
        JPanel pChooseOne = new JPanel();
        pChooseOne.setLayout(new GridLayout(5,1));
        pChooseOne.setCursor(new Cursor(Cursor.HAND_CURSOR));
        goodyTextButtons = new JButton[goodyType];
        pChooseOne.setBorder(new TitledBorder("Choose an item"));
        
        
        JPanel qtyTable = new JPanel();
        qtyTable.setLayout(new BoxLayout(qtyTable, BoxLayout.Y_AXIS));
        qtyTable.setBorder(new TitledBorder("Quantity table"));
        qtyTable.add(tableWithQuantity);
        //tableWithQuantity

        // Add the above four panels together
        JPanel p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
        p1.setPreferredSize(new Dimension (250, 500));
        
        p1.add(oneDollarBotton);
        p1.add(pPrice);
        p1.add(money_input);
        p1.add(money_eject);
        p1.add(pChooseOne);
        p1.add(moneyIndicator);
        
        p1.add(qtyTable);
        
        p1.add(outputCurrentState);
        p1.add(returnAmountTextField);

        // Items panel
        JPanel itemsInMachine = new JPanel();
        itemsInMachine.setLayout(new GridLayout(4,1,0,0));
        itemsInMachine.setBorder(new TitledBorder("Items in the machine"));
        ((TitledBorder) itemsInMachine.getBorder()).setTitleColor(Color.BLACK);
        int goodyImgWidth = 200, goodyImgHeight = 200;
        JButton[] goodyImageButtons = new JButton[goodyType];
        DisplayPricesActionListener displayPriceListener = new DisplayPricesActionListener(amountTextField, goodyImageButtons);
        for (int i = 0; i < goodyImageButtons.length; i++) {
        	
        	//Get images
    		java.net.URL imgUrl = getClass().getResource(goodyImageNames[i]);		

            ImageIcon image1 = new ImageIcon(imgUrl);
            ImageIcon image2 = new ImageIcon(image1.getImage().getScaledInstance(
                        (int)goodyImgWidth, (int)goodyImgHeight, java.awt.Image.SCALE_SMOOTH));			
            goodyImageButtons[i] = new JButton(image2);
            goodyImageButtons[i].setContentAreaFilled(false);
            goodyImageButtons[i].setBorderPainted(true);
            itemsInMachine.add(goodyImageButtons[i]);
            goodyImageButtons[i].addActionListener(displayPriceListener);
        }

        // The panel of "where you get your item"
        JButton whereButton = new JButton("This is where you get your item!");
        whereButton.setPreferredSize(new Dimension(250, 100));
        whereButton.setBackground(Color.BLACK);
        whereButton.setForeground(Color.WHITE);



        // Add everything onto the frame
        this.add(itemsInMachine, BorderLayout.CENTER);
        this.add(p1, BorderLayout.EAST);
        this.add(whereButton, BorderLayout.SOUTH);

        // the action listener
        PayForGoodyActionListener payGoodyListener = 
        new PayForGoodyActionListener(goodyTextButtons, whereButton,
                    enterMoneyTextField, returnAmountTextField, outputCurrentState);
        for (int i = 0; i < goodyType; i++) {
            goodyTextButtons[i] = new JButton(goodyNames[i]);
            goodyTextButtons[i].setToolTipText(goodyTips[i]);
            // ActionListener attached to each button
            goodyTextButtons[i].addActionListener(payGoodyListener); 

			switch(goodyNames[i])
			{
				case("W"):
					goodyTextButtons[i].setMnemonic(KeyEvent.VK_W);
					break;
				case("S"):
					goodyTextButtons[i].setMnemonic(KeyEvent.VK_S);
					break;
				case("P"):
					goodyTextButtons[i].setMnemonic(KeyEvent.VK_P);
					break;
				case("C"):
					goodyTextButtons[i].setMnemonic(KeyEvent.VK_C);
					break;
				default:
					break;
			}
            pChooseOne.add(goodyTextButtons[i]);
        }

    }

}
