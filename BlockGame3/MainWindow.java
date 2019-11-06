package BlockGame3;

import BlockGame3.SimpleOne;
import BlockGame3.MediumTwo;
import BlockGame3.CrazyThree;
import BlockGame3.Counter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MainWindow extends JFrame implements ActionListener{

	public static MainWindow main_window;
	public static int main_windowWidth = 256;
	public static int main_windowLength = 298 + 80;
	//public volatile static boolean is_simple = false, is_medium = false, is_crazy = false;
	public volatile static boolean is_running = false;
	public volatile static boolean is_reset = false;
	public volatile static boolean is_suspend = false;
	public volatile static boolean is_stop = false;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//MainWindow window = new MainWindow();
		//main_window = window;
		EventQueue.invokeLater(() -> {
			MainWindow window = new MainWindow();
			main_window = window;
			window.drawClient();
			});
		Counter counter = new Counter();
		Thread counter_thread = new Thread(counter);
		//SwingUtilities.invokeLater(counter_thread);
		

		counter_thread.start();
		
	}
	
	//public static int main_windowWidth = 256;
	//public static int main_windowLength = 298 + 80;
	public volatile JLabel label;
	volatile JMenuItem item_start, item_suspend, item_stop, item_exit;
	JMenuItem item_simple, item_medium, item_crazy;
	public volatile boolean simple_choose = false, medium_choose = false, crazy_choose = false;
	public void drawLabel() {
		label = new JLabel("00 : 00", JLabel.CENTER);
		this.add(BorderLayout.NORTH,label);

	}
	public void drawMenu() {
		//menuitem name
		//menu bar
		JMenuBar menu_bar = new JMenuBar();
		JMenu menu_1,menu_2,menu_3,menu_4;
		menu_1 = new JMenu("Game");
		menu_2 = new JMenu("list 2");
		menu_3 = new JMenu("list 3");
		menu_4 = new JMenu("level");
		//menu
		menu_bar.add(menu_1);
		menu_bar.add(menu_2);
		menu_bar.add(menu_3);
		menu_bar.add(menu_4);
		//menuitem
		item_start = new JMenuItem("start");
		item_suspend = new JMenuItem("suspend");
		item_suspend.setEnabled(false);
		item_stop = new JMenuItem("stop");
		item_stop.setEnabled(false);
		item_exit = new JMenuItem("exit");
		menu_1.add(item_start);
		menu_1.add(item_suspend);
		menu_1.add(item_stop);
		menu_1.add(item_exit);
		
		//degree of difficulty
		
		item_simple = new JMenuItem("simple");
		item_medium = new JMenuItem("medium");
		item_crazy = new JMenuItem("crazy");
		menu_4.add(item_simple);
		menu_4.add(item_medium);
		menu_4.add(item_crazy);
		
		//listener
		item_start.addActionListener(this);
		item_start.setActionCommand("start");
		item_suspend.addActionListener(this);
		item_suspend.setActionCommand("suspend");
		item_stop.addActionListener(this);
		item_stop.setActionCommand("stop");
		item_exit.addActionListener(this);
		item_exit.setActionCommand("exit");
		
		
		
		item_simple.addActionListener(this);
		item_simple.setActionCommand("simple");
		item_medium.addActionListener(this);
		item_medium.setActionCommand("medium");
		item_crazy.addActionListener(this);
		item_crazy.setActionCommand("crazy");
		//insert menu bar into window
		this.setJMenuBar(menu_bar);
	}
	public void drawClient(){

		drawLabel();
		drawMenu();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(main_windowWidth, main_windowLength);
		this.setResizable(false);
		this.setLocation(200, 100);
		this.setVisible(true);
		//
		//Counter counter = new Counter();
		//Thread counter_thread = new Thread(counter);
		//SwingUtilities.invokeLater(counter_thread);
		

		//counter_thread.run();
		
	}
	//Event thread
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("start")) {
			
			if(simple_choose == true) {
				//
				//
				
				//let the other thread ends its life
				simple_choose = false;
				medium_choose = false;
				crazy_choose = false;
				//
				is_running = true;
				is_reset = true;
				MainWindow.is_reset = true;
				try {
					Thread.sleep(30);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//is_running = true;
				//is_stop = false;
				
				//is_reset = false;
				//
				//item_start.setEnabled(false);
				item_suspend.setEnabled(true);
				item_stop.setEnabled(true);
				//
				
				//state set
				simple_choose = true;
				EventQueue.invokeLater(() -> new Thread( new SimpleOne()).start());
				
				
			}else if(medium_choose == true) {
				
				//
				simple_choose = false;
				medium_choose = false;
				crazy_choose = false;
				//
				is_running = true;
				is_reset = true;
				
				//
				try {
					Thread.sleep(10);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//is_running = true;
				//is_stop = false;
				//item_start.setEnabled(false);
				item_suspend.setEnabled(true);
				item_stop.setEnabled(true);
				//
				medium_choose = true;
				EventQueue.invokeLater(() -> new Thread( new MediumTwo()).start());
				
			}else if(crazy_choose == true) {
				
				//
				simple_choose = false;
				medium_choose = false;
				crazy_choose = false;
				//
				is_running = true;
				is_reset = true;
				
				//
				
				try {
					Thread.sleep(10);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				item_suspend.setEnabled(true);
				item_stop.setEnabled(true);
				//
				//is_running = true;
				//is_stop = false;
				//crazy_three = new CrazyThree();
				//crazy_thread = new Thread(crazy_three);
				crazy_choose = true;
				//EventQueue.invokeLater(crazy_thread);
				EventQueue.invokeLater(() -> new Thread( new CrazyThree()).start());
				
			}
		}else if(e.getActionCommand().equals("simple")) {
			simple_choose = true;
			item_simple.setText("* simple");
			item_medium.setText("medium");
			item_crazy.setText("crazy");
			medium_choose = false;
			crazy_choose = false;
			
			
			
		}else if(e.getActionCommand().equals("medium")) {
			medium_choose = true;
			item_medium.setText("* medium");
			item_simple.setText("simple");
			item_crazy.setText("crazy");
			simple_choose = false;
			crazy_choose = false;
			
		
			
		}else if(e.getActionCommand().equals("crazy")) {
			crazy_choose = true;
			item_crazy.setText("* crazy");
			item_simple.setText("simple");
			item_medium.setText("medium");
			simple_choose = false;
			medium_choose = false;
		}else if(e.getActionCommand().equals("exit")) {
			System.exit(0);
		}else if(e.getActionCommand().equals("suspend")) {
			is_suspend =true;
			MainWindow.is_running = false;
			item_start.setActionCommand("continue");
			
		}else if(e.getActionCommand().equals("stop")) {
			MainWindow.is_running = false;
			item_start.setActionCommand("start");
			is_stop = true;

		}else if(e.getActionCommand().equals("continue")) {
			is_running = true;
			is_suspend = false;
			item_suspend.setEnabled(true);
			item_stop.setEnabled(true);
			item_start.setActionCommand("start");
		}
	}

}
