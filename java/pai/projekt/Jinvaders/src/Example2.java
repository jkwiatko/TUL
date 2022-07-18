package Jinvaders;

import jcurses.system.*;
import jcurses.widgets.*;
import jcurses.util.*;
import jcurses.event.*;

public class Crap2 extends Window implements ItemListener, ActionListener,
    ValueChangedListener, WindowListener, WidgetsConstants {
  static Crap2 window = null;
  static TextField textfield = null;
  static Button button = null;

  public Crap2(int width, int height) {
    super(width, height, false, "JCurses Test");
    this.setShadow(false);
  }

  public static void main(String[] args) throws Exception {
    window = new Crap2(82, 24);
    window.init();
  }

  public void init() {
    DefaultLayoutManager mgr = new DefaultLayoutManager();
    window.getRootPanel().setPanelColors(new CharColor((short)0,(short)3));
    mgr.bindToContainer(window.getRootPanel());
    mgr.addWidget(
        new Label("Hello World!",
                  new CharColor(CharColor.BLACK, CharColor.GREEN)),
                  0, 0, 20, 10,
                  WidgetsConstants.ALIGNMENT_LEFT,
                  WidgetsConstants.ALIGNMENT_TOP);

    // textfield = new TextField(10);
    // mgr.addWidget(textfield, 0, 0, 20, 20,
    //     WidgetsConstants.ALIGNMENT_CENTER,
    //     WidgetsConstants.ALIGNMENT_CENTER);

    // button = new Button("Quit");
    // mgr.addWidget(button, 0, 0, 20, 30,
    //     WidgetsConstants.ALIGNMENT_CENTER,
    //     WidgetsConstants.ALIGNMENT_CENTER);

    // button.setShortCut('q');
    // button.addListener(this);
    // window.addListener((WindowListener) this);
    window.show();
  }

  public void actionPerformed(ActionEvent event) {
    Widget w = event.getSource();
    if (w == button) {
      new Message("HowTo", "You are about to quit", "OK").show();
      window.close();
    }
  }

  public void stateChanged(ItemEvent e) {  }

  public void valueChanged(ValueChangedEvent e) {  }

  public void windowChanged(WindowEvent event) {
    if (event.getType() == WindowEvent.CLOSING) {
      event.getSourceWindow().close();
      // Toolkit.clearScreen(new CharColor(CharColor.WHITE, CharColor.BLACK));
    }
  }
}