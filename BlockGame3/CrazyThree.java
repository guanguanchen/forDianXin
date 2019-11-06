
/**
 * 7 x 7
 */
package BlockGame3;

import BlockGame3.MainWindow;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CrazyThree implements ActionListener, Runnable{

	JLabel label;
	//MainWindow.main_windowWidth = 256 + 160 + 160;
	//MainWindow.main_windowLength = 298 + 80 + 160 + 160;
	private final Dimension button_size = new Dimension(80, 80);
	private final String my_title = "from create_window";
	//java.util.List<JButton> eight_button = new ArrayList<JButton>(); //may be redundant
	java.util.List<Integer> random_to_button = new ArrayList<Integer>();  //assign num to button
	java.util.List<Integer> block_be_chosen_random = new ArrayList<Integer>(); // assign num to block
	HashMap<Integer, Integer> num_to_block = new HashMap<Integer, Integer>(); //the button's name maps a block identifier
	HashMap<Integer, JButton> block_to_button = new HashMap<Integer, JButton>();//the block identifier maps a button
	
	public void run() {
		
		createWindow();
		MainWindow.main_window.repaint();
		while(MainWindow.main_window.crazy_choose){
			
		}
		for(int i = 0; i< 49; i++) {
			if(block_to_button.get(i) != null) {
			
			MainWindow.main_window.remove(block_to_button.get(i));
			}
		}
		random_to_button.clear();
		block_be_chosen_random.clear();
		num_to_block.clear();
		block_to_button.clear();
	}
	// a model
	/* public void createLabel() {
	 
		label = new JLabel("lala", JLabel.CENTER);
		Font big_font = new Font("serif", Font.BOLD, 20);
		label.setFont(big_font);
		MainWindow.main_window.add(BorderLayout.NORTH, label);
	}
	*/
	public void createButton(int x, int y, int block, Integer num){
		JButton button = new JButton(num.toString());
		//eight_button.add(button);
		num_to_block.put(num, block);
		block_to_button.replace(block, button);
		button.setSize(button_size);
		button.setLocation(x, y);
		button.addActionListener(this);
		MainWindow.main_window.getContentPane().add(button);
	}
	public void createWindow(){
		MainWindow.main_window.repaint();
		MainWindow.main_windowWidth = 256 + 160 + 160;
		MainWindow.main_windowLength = 298 + 80 + 160 + 160;
		MainWindow.main_window.setLayout(null);
		MainWindow.main_window.setSize(MainWindow.main_windowWidth, MainWindow.main_windowLength);
		MainWindow.main_window.setLocation(200, 100);
		MainWindow.main_window.setTitle(this.my_title);
		MainWindow.main_window.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		//MainWindow.main_window.setBackground(Color.GREEN);
		createManyButton();
		//MainWindow.main_window.setVisible(true);
	}
	//assign identifier randomly
	//only in fixed location
	//you can change
	public void createManyButton(){
		int j = 1;
		while(j < 49) {
			random_to_button.add(j);
			j++;
		}
		j = 0;
		while(j < 49) {
			block_to_button.put(j, null);
			block_be_chosen_random.add(j);
			j++;
		}
		Random rand = new Random();
		int temp;
		//先分配9个随机量，然后随机减掉一个
		temp = rand.nextInt(block_be_chosen_random.size());
		block_be_chosen_random.remove(temp);
		for(int i = 0; i < 48; i++){
			temp = block_be_chosen_random.get(i);
			if(temp < 7){
				j = rand.nextInt(random_to_button.size());
				createButton(80 * (temp - 0), 80, temp, random_to_button.get(j));
				random_to_button.remove(j);
			}else if(block_be_chosen_random.get(i) < 14){
				j = rand.nextInt(random_to_button.size());
				createButton(80 * (temp - 7), 160, temp, random_to_button.get(j));
				random_to_button.remove(j);
			}else if(block_be_chosen_random.get(i) < 21){
				j = rand.nextInt(random_to_button.size());
				createButton(80 * (temp - 14), 240, temp, random_to_button.get(j));
				random_to_button.remove(j);
			}else if(block_be_chosen_random.get(i) < 28) {
				j = rand.nextInt(random_to_button.size());
				createButton(80 * (temp - 21), 320, temp, random_to_button.get(j));
				random_to_button.remove(j);
			}else if(block_be_chosen_random.get(i) < 35) {
				j = rand.nextInt(random_to_button.size());
				createButton(80 * (temp - 28), 400, temp, random_to_button.get(j));
				random_to_button.remove(j);
			}else if(block_be_chosen_random.get(i) < 42) {
				j = rand.nextInt(random_to_button.size());
				createButton(80 * (temp - 35), 480, temp, random_to_button.get(j));
				random_to_button.remove(j);
			}else if(block_be_chosen_random.get(i) < 49) {
				j = rand.nextInt(random_to_button.size());
				createButton(80 * (temp - 42), 560, temp, random_to_button.get(j));
				random_to_button.remove(j);
			}
			MainWindow.main_window.validate();
			
		}
	}
	
	public void actionPerformed(ActionEvent e){
		//get the identifier you are clicking
		int num_click = Integer.parseInt(e.getActionCommand());
		//transform the number identifier to block identifier
		int block_click = num_to_block.get(num_click);
		
		ButtonMove(num_click,block_click);
	}
	//这里判断就是以格子位置为单位，不以按钮上的数字
	//按钮上的数字已经转换为格子位置了
	public void ButtonMove(int num_click,int block_identifier) { //up
		if(MainWindow.is_running ==true && MainWindow.is_suspend != true && MainWindow.is_stop != true) {
		if(block_identifier > 6) {
			if(block_to_button.get(block_identifier - 7) == null) {
				block_to_button.get(block_identifier).setLocation(block_to_button.get(block_identifier).getX(), block_to_button.get(block_identifier).getY() - 80);
				block_to_button.replace(block_identifier - 7, block_to_button.get(block_identifier));
				block_to_button.replace(block_identifier, null);
				num_to_block.replace(num_click, block_identifier - 7);
				isWin();
				return;
			}
		}if(block_identifier < 42) { //down
			if(block_to_button.get(block_identifier + 7) == null) {
				block_to_button.get(block_identifier).setLocation(block_to_button.get(block_identifier).getX(), block_to_button.get(block_identifier).getY() + 80);
				block_to_button.replace(block_identifier + 7, block_to_button.get(block_identifier));
				block_to_button.replace(block_identifier, null);
				num_to_block.replace(num_click, block_identifier + 7);
				isWin();
				return;
			}
		}if(block_identifier != 0 && block_identifier != 7 && block_identifier != 14 && block_identifier != 21 
			&& block_identifier != 28 && block_identifier != 35 && block_identifier != 42 ) { //left
			if(block_to_button.get(block_identifier - 1) == null && block_identifier - 1 >= 0) {
				block_to_button.get(block_identifier).setLocation(block_to_button.get(block_identifier).getX() - 80, block_to_button.get(block_identifier).getY());
				block_to_button.replace(block_identifier - 1, block_to_button.get(block_identifier));
				block_to_button.replace(block_identifier, null);
				num_to_block.replace(num_click, block_identifier - 1);
				isWin();
				return;
			}
		}if(block_identifier != 6 && block_identifier != 13 && block_identifier != 20 && block_identifier != 27 
			&& block_identifier != 34 && block_identifier != 41 && block_identifier != 48) { //right
			if(block_to_button.get(block_identifier + 1) == null) {
				block_to_button.get(block_identifier).setLocation(block_to_button.get(block_identifier).getX() + 80, block_to_button.get(block_identifier).getY());
				block_to_button.replace(block_identifier + 1, block_to_button.get(block_identifier));
				block_to_button.replace(block_identifier, null);
				num_to_block.replace(num_click, block_identifier + 1);
				isWin();
				return;
			}
		}
		}
	}
	
	//You can write what you want
	public void isWin() {
		int i;
		for(i = 0; i < 47 && num_to_block.get(i + 1) == i; i++){
			
		}
		if(i == 48) {
			Object[] options = { "确定", "取消" };
			int response = JOptionPane.showOptionDialog(MainWindow.main_window, "您确认要退出吗", "",
					JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[0]);
			if (response == 0) {
				System.out.println("退出");
				System.exit(0);
			}else {
				System.exit(0);
			}
		}else {
			
		}
	}

}
