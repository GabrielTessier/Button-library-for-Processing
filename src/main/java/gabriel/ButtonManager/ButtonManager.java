package gabriel.ButtonManager;

import processing.core.*;

public class ButtonManager {
	
	public final static String VERSION = "##library.prettyVersion##";
	
	private PApplet parent;
	private static ButtonManager instance;
	
	private boolean isClick;
	private static final int OUT = 0;
	private static final int IN = 1;
	
	ButtonStyle defaultButtonStyle;
	
	public ButtonManager(PApplet theParent) {
		if(instance != null) throw new SingleInstanceException("CoreButton");
		welcome();
		instance = this;
		parent = theParent;
		defaultButtonStyle = new ButtonStyle();
	}
	
	public static ButtonManager getInstance()
	{
		return instance;
	}
	
	public PApplet getParent()
	{
		return parent;
	}
	
	private void welcome() {
		System.out.println("##library.name## ##library.prettyVersion## by ##author.name##");
	}
	
	/**
	 * return the version of the Library.
	 * 
	 * @return String
	 */
	public static String version() {
		return VERSION;
	}
	
	public void always() {
		isClick = parent.mousePressed;
	}
	
	public boolean getIsClick() {
		return isClick;
	}
	
	public ButtonStyle getDefaultButtonStyle() {
		return defaultButtonStyle;
	}
	
	public boolean buttonPressed(float x, float y, float tx, float ty) {
		return buttonPressed(x, y, tx, ty, IN);
	}

	public boolean buttonPressed(float x, float y, float tx, float ty, int io) {
		if (parent.mousePressed && isClick==false) {
			boolean in;
			if (mouseOver(x, y, tx, ty)) in = true;
			else in = false;
			
			if (io==IN) {
				if (in) return true;
				else return false;
			} else {
				if (in) return false;
				else return true;
			}
		}
		return false;
	}
	public boolean mouseOver(float x, float y, float tx, float ty) {
		if (parent.mouseX>=x && parent.mouseX<=x+tx && parent.mouseY>=y && parent.mouseY<=y+ty) return true;
		else return false;
	}
	
	public Button[] buttonsVerticalAlign(Button[] buttons, int sizeX, int sizeY, int offY) {
	  for (int i=0; i<buttons.length; i++) {
	    buttons[i].setPos(parent.width/2-sizeX/2, parent.height/2-(sizeY*buttons.length)/2-(offY*(buttons.length-1))/2+sizeY*i+offY*i, sizeX, sizeY);
	  }
	  return buttons;
	}
	public Button[] buttonsHorizontalAlign(Button[] buttons, int sizeX, int sizeY, int offX) {
	  for (int i=0; i<buttons.length; i++) {
	    buttons[i].setPos(parent.width/2-(sizeX*buttons.length)/2-(offX*(buttons.length-1))/2+sizeX*i+offX*i, parent.height/2-sizeY/2, sizeX, sizeY);
	  }
	  return buttons;
	}
	
	public int calculTextSize(String text, int off, int l) {
	  int res = 1;
	  parent.textSize(res);
	  while (parent.textWidth(text) < l+off) {
	    res++;
	    parent.textSize(res);
	  }
	  return res-1;
	}

	public int calculTextSize(String text, int l) {
	  return calculTextSize(text, 0, l);
	}
}

