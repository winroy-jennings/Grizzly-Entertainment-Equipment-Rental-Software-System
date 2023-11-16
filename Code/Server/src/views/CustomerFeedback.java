package views;

import views.Client;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import models.Message;

public class CustomerFeedback {
private JFrame frame;
private JLabel idLabel;	
private JLabel categoryLabel;
private JLabel dateLabel;
private JLabel informationLabel;
private JTextField idText;
private JLabel customerLabel;
private JTextField customerText; 
private static JTextField dateText;
private JTextArea informationText;
private JButton submitButton;
private JButton previousButton;

public JMenuBar optionsBar;
public JMenu serviceMen, subMenu;
public JMenuItem menuItm;
	Message message = new Message();
	
	public CustomerFeedback()
	{
		frame = new JFrame("Leave a Feedback");
		frame.setResizable(false);
		frame.setBounds(700, 300, 584, 531);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(150, 150, 150));
		
		
		customerLabel = new JLabel ("Customer Identification:");
		customerLabel.setBounds(20, 10, 290, 35);
		customerLabel.setForeground(Color.black);
		customerLabel.setFont(new Font("Serif", Font.BOLD, 16));
		frame.getContentPane().add(customerLabel);
		
		idLabel = new JLabel ("Message Identification ");
		idLabel.setFont(new Font("Serif", Font.BOLD, 16));
		idLabel.setForeground(Color.BLACK);
		idLabel.setBounds(20,50,290,35);
		frame.getContentPane().add(idText);
		
		idText = new JTextField();
		idText.setFont(new Font("Serif",Font.PLAIN,14));
		Random rand = new Random();
		idText.setText(String.format("%04",rand.nextInt( 10000)));
		idText.setBounds(116, 50, 130, 30);
		idText.setEditable(false);
		frame.getContentPane().add(idText);
		
		dateLabel = new JLabel("Date: ");
		dateLabel.setFont(new Font("Serif",Font.BOLD,16));
		dateLabel.setForeground(Color.black);
		dateLabel.setBounds(20, 130, 290, 35);
		frame.getContentPane().add(dateLabel);
		
		dateText = new JTextField();
		dateText.setFont(new Font("Serif", Font.PLAIN, 16));
		dateText.setBounds(117, 130, 130, 130);
		dateText.setText("mm/dd/yyyy");
		dateText.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e)
			{
				dateText.setText("");
			}
			@Override
			public void focusLost(FocusEvent e)
			{
				
			}
		});
		
		frame.getContentPane().add(dateText);
		
		
		informationLabel = new JLabel("Feedback:");
		informationLabel.setForeground(Color.black);
		informationLabel.setBounds(20,170,290,35);
		informationLabel.setFont(new Font("Serif",Font.PLAIN,16));
		frame.getContentPane().add(informationLabel);
		
		informationText = new JTextArea();
		informationText.setBounds(117, 170, 290, 35);
		informationText.setSize(330,170);
		informationText.setFont(new Font("Serif",Font.PLAIN,16));
		frame.getContentPane().add(informationText);
		
		submitButton = new JButton("Submit");
		submitButton.setFont(new Font("Serif", Font.BOLD,18));
		submitButton.setForeground(Color.white);
		submitButton.setBackground(new Color(96,96,96));
		submitButton.setBounds(390, 370, 100, 30);
		submitButton.addActionListener(new ActionListener()
				{
					public void actionPerformed1(ActionEvent e)
					{
						if(idText.getText().equals("")||informationText.getText().equals("")||dateText.getText().equals(""))
						{
							JOptionPane.showMessageDialog(null,"Feedback Information is missing","Submit Status",
									JOptionPane.WARNING_MESSAGE);
						}
						else
						{
							Client client = new Client();
							message.setSender(customerText.getText());
							message.setMessage(idText.getText());
							message.setMessage(informationText.getText());
							message.setDate(dateText.getText());
							
							
							client.sendAction("Add feedback");
							client.sendMessages(message);//client class 
							
						}
					}

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
		frame.getContentPane().add(submitButton);
		
		previousButton = new JButton ("Previous");
		previousButton.setFont(new Font("Serif", Font.BOLD, 18));
		previousButton.setForeground(Color.white);
		previousButton.setBackground(new Color(96, 96, 96));
		previousButton.setBounds(90, 370, 100, 30);
		previousButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new CustomerDashboard();//from briana 
				frame.dispose();
			}
		});
		
		frame.getContentPane().add(previousButton);
		
		
		//CREATE THE MENU BAR
		
		optionsBar = new JMenuBar();
		serviceMen = new JMenu("Service:");
		serviceMen.setFont(new Font("Serif",Font.BOLD, 14));
		serviceMen.setMnemonic(KeyEvent.VK_A);
		serviceMen.getAccessibleContext().setAccessibleDescription(null);
		serviceMen.setBounds(250, 70, 50, 15);
		serviceMen.setOpaque(true);
		optionsBar.add(serviceMen);
		
		//MENU ITEM 
		
		menuItm = new JMenuItem("View customer complaint",KeyEvent.VK_T);
		menuItm.setFont(new Font("Serif",Font.BOLD, 14));
		menuItm.setBackground(new Color(255, 255, 255));
		menuItm.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItm.getAccessibleContext().setAccessibleDescription("Complaint");
		menuItm.addActionListener(new ActionListener()
				{
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}	
			});
		serviceMen.add(menuItm);
		
		menuItm = new JMenuItem("Query Account Status",KeyEvent.VK_T);
		menuItm.setFont(new Font("Serif",Font.BOLD, 14));
		menuItm.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK));
		menuItm.getAccessibleContext().setAccessibleDescription("Complaint");
		menuItm.setBackground(new Color(255, 255, 255));
		menuItm.addActionListener(new ActionListener() {
			@Override
			
			public void actionPerformed(ActionEvent e)
			{
				frame.dispose();
			}
				});
		
		serviceMen.add(menuItm);
		
		menuItm = new JMenuItem("View Past Paymnets", KeyEvent.VK_T);
		menuItm.setFont(new Font("Serif", Font.BOLD, 14));
		menuItm.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5, ActionEvent.ALT_MASK));
		menuItm.getAccessibleContext().setAccessibleDescription("Complaint");
		menuItm.setBackground(new Color(255, 255, 255));
		menuItm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				frame.dispose();
		}
		});
		
		serviceMen.add(menuItm);
		
		serviceMen = new JMenu("Previous");
		serviceMen.setFont(new Font("Serif", Font.BOLD, 14));
		serviceMen.setMnemonic(KeyEvent.VK_A);
		serviceMen.getAccessibleContext().setAccessibleDescription("");
		serviceMen.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e)
			{
				frame.dispose();
				new CustomerDashboard();//from briana 
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				//TODOD Auto-Generated method sub
			}
			
			@Override
			public void mouseEntered(MouseEvent e)
			{
				//TODOD Auto-Generated method sub
			}
			@Override
			public void mouseExited(MouseEvent e)
			{
				//TODOD Auto-Generated method sub
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		optionsBar.add(serviceMen);
		
		class MenuListener
		{
			MenuListener listener = new MenuListener();
		}
		
		frame.add(optionsBar);
		frame.setJMenuBar(optionsBar);
		frame.setVisible(true);
	}
	public static void main(String args [])
	{
		new CustomerFeedback();
	}
}
