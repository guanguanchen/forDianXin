package BlockGame3;

import BlockGame3.MainWindow;

class Counter implements Runnable
{
	int min = 0, sec = 0;
	
	
	/*public Counter(MainWindow window) {
		this.window = window;
	}
	*/
	public void run(){
		while(true) {
			if(MainWindow.is_running == true) {
				infiniteCall();
			}
			//System.out.println("q");
		}
	}
	public void infiniteCall(){
			
		while(MainWindow.is_running == true && MainWindow.is_reset == false ){
			if(sec == 59){
				if(min == 59) {
					System.exit(0);
				}
			sec = 0;
			min += 1;
			}else{
			sec += 1;
			}
			MainWindow.main_window.label.setText(String.format("%1$02d : %2$02d", min, sec));
			
			try{
				Thread.sleep(1000);
			}catch(InterruptedException err){
				err.printStackTrace();
			}
			
		}
		//ֱ�ӿ�ʼ��һ����Ϸʱ������ǰ����ӳ�һ�룬��˴�һ�뿪ʼ��ʱ
		//����ж��Ƿ����ڵ�ǰ������Ϸ�ٴε��
		//�е�󣬿������������취
		if(MainWindow.is_reset == true) {
			
			if(this.sec != 0) {
				this.min = 0;
				this.sec = 1;
			}
			
			//MainWindow.main_window.label.setText(String.format("%1$02d : %2$02d", min, sec));
			//this hasn't ant effect
			//sleeping time too long 
			MainWindow.is_reset = false;
			MainWindow.is_stop = false;
			//System.out.println("11");
		}
		//System.out.println("p");
	}

}