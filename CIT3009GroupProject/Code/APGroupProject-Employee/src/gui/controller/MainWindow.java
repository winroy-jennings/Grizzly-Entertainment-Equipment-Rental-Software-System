/*
 Lecturer/Tutot/Lab Teacher: Mr. Christopher Panther
 Occurrence: UN1
 Group Member Names and ID Numbers:
 Briana Taylor - 2100212
 Winroy Jennings - 2106397
 Shade Mcleod - 2102952
 Aneska Bryan - 2102374
 */
package gui.controller;
//Author of this class: Winroy Jennings
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9121897686417576677L;
	private JDesktopPane desktop;
	private JMenuBar menuBar;

	private JMenu messages;
	private JMenu schedule;

	private JMenuItem viewAllMessages;
	private JMenuItem scheduleEquipment;

	public MainWindow() {
		initializeComponents();
		addMenuItemsToMenu();
		addMenusToMenuBar();
		addComponentsToWindow();
		registerListeners();
		setWindowProperties();
	}

	public void initializeComponents() {
		desktop = new JDesktopPane();
		menuBar = new JMenuBar();

		messages = new JMenu("Messages");
		messages.setMnemonic('A');

		schedule = new JMenu("Schedule");
		schedule.setMnemonic('S');

		viewAllMessages = new JMenuItem("View All Message");
		scheduleEquipment = new JMenuItem("Schedule Equipment");
	}

	public void addMenuItemsToMenu() {
		messages.add(viewAllMessages);
		schedule.add(scheduleEquipment);
	}

	public void addMenusToMenuBar() {
		menuBar.add(messages);
		menuBar.add(schedule);
	}

	public void addComponentsToWindow() {
		this.add(desktop);
	}

	public void registerListeners() {
		viewAllMessages.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				desktop.add(new ViewAllMessages());
			}
		});

		scheduleEquipment.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				desktop.add(new ScheduleEquipment());
			}
		});

		scheduleEquipment.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ScheduleEquipment();
			}
		});
	}

	public void setWindowProperties() {
		this.setTitle("Grizzly Entertainment Equipment Rental Employee System");
		this.setJMenuBar(menuBar);
		this.setSize(1020, 700);
		this.setResizable(true);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow main = new MainWindow();
					main.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
