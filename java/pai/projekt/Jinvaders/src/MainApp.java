package Jinvaders;

import jcurses.util.Message;
import jcurses.util.Rectangle;
import jcurses.system.Toolkit;
import jcurses.system.CharColor;
import jcurses.widgets.Panel;
import jcurses.widgets.Window;
import jcurses.widgets.TextComponent;
import jcurses.widgets.LayoutManager;
import jcurses.widgets.DefaultLayoutManager;



class MainApp{


	public static void main(String[] args) throws InterruptedException{
		
		if(args.length != 2){
			System.out.println("To few arquments!");
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e){e.printStackTrace();}
		} 
		else{
			Toolkit.init();
			// Toolkit.changeColors(new Rectangle(82,24), new CharColor(CharColor.BLACK, CharColor.GREEN));
	
			View gameView = new View(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
			gameView.initPlayer();
	
	
			Toolkit.startPainting();
			Toolkit.endPainting();
	
		
			// Toolkit.shutdown();
		}
	}
}