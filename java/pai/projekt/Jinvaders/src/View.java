package Jinvaders;

import jcurses.system.CharColor;
import jcurses.widgets.Window;
import jcurses.widgets.Panel;
import jcurses.widgets.Label;
import jcurses.widgets.WidgetsConstants;
import jcurses.widgets.DefaultLayoutManager;
import jcurses.event.ItemListener;
import jcurses.event.ActionListener;
import jcurses.event.ValueChangedListener;
import jcurses.event.WindowListener;
import jcurses.event.ItemEvent;
import jcurses.event.ValueChangedEvent;
import jcurses.event.ActionEvent;
import jcurses.event.WindowEvent;

class View{

	int width,height;

    GameWindow wPlayer 			= null;
	GameWindow wPlayerMissile 	= null;
	GameWindow wAliens 			= null;
	GameWindow wAliensMissile 	= null;	
	GameWindow wBunkers 		= null;
	GameWindow wGameOver 		= null;
	GameWindow wUfo 			= null;
	GameWindow wStatus 			= null;
	GameWindow wTitleScreen 	= null;


	Panel wBattleField;
	Panel wEmpty;
	Panel wScores;	

	View(int theWidth, int theHeight){
		width = theWidth;
		height = theHeight; 
	}

	
	public void initPlayer() {
	wPlayer = new GameWindow(width,height);
    DefaultLayoutManager mgr = new DefaultLayoutManager();
    wPlayer.getRootPanel().setPanelColors(new CharColor((short)0,(short)3));
    mgr.bindToContainer(wPlayer.getRootPanel());
    mgr.addWidget(
        new Label("/-^-\\",
                  new CharColor(CharColor.BLACK, CharColor.YELLOW)),
                  width/2, height-2, 5, 1,
                  WidgetsConstants.ALIGNMENT_BOTTOM,
                  WidgetsConstants.ALIGNMENT_CENTER);
    wPlayer.show();
	}
}

class GameWindow extends Window implements ItemListener, ActionListener, 
    ValueChangedListener, WindowListener, WidgetsConstants{

	GameWindow(int width, int height){
	super(width, height, false, "GameWindow");
	this.setShadow(false);
	}

	public void stateChanged(ItemEvent e) {  }

  	public void valueChanged(ValueChangedEvent e) {  }

  	public void actionPerformed(ActionEvent event) {  }

  	public void windowChanged(WindowEvent event) {
	    if (event.getType() == WindowEvent.CLOSING) {
	      event.getSourceWindow().close();
	      // Toolkit.clearScreen(new CharColor(CharColor.WHITE, CharColor.BLACK));
	    }
	}
}


