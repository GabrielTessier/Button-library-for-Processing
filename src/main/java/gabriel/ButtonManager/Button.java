package gabriel.ButtonManager;

import processing.core.PApplet;
import processing.core.PVector;
import processing.core.*;

public class Button {
	
	ButtonManager cb;
	PApplet app;
	
	String text;
	float posX;
	float posY;
	float sizeX;
	float sizeY;
	
	ButtonStyle buttonStyle;
	
	Thread thread;
	String callback;
	Class[] myclass;
	Object[] object;
	
	public Button(String text, float posX, float posY, float sizeX, float sizeY, ButtonStyle buttonStyle) {
		initButton(text, posX, posY, sizeX, sizeY);
		this.buttonStyle = buttonStyle;
	}
	
	public Button(String text, float posX, float posY, float sizeX, float sizeY, int form) {
		initButton(text, posX, posY, sizeX, sizeY);
		buttonStyle = new ButtonStyle();
		buttonStyle.setStyleWithOtherStyle(cb.getDefaultButtonStyle());
		buttonStyle.form = form;
	}
	private void initButton(String text, float posX, float posY, float sizeX, float sizeY) {
		cb = ButtonManager.getInstance();
		if (cb == null) throw new noCoreButtonException();
		app = cb.getParent();
		this.posX = posX;
		this.posY = posY;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.text = text;
	}
	
	public ButtonStyle getButtonStyle() {
		return buttonStyle;
	}
	public Button setButtonStyle(ButtonStyle bs) {
		buttonStyle = bs;
		return this;
	}
	
	public String getText()
	{
		return text;
	}
	
	public Button setText(String text)
	{
		this.text = text;
		return this;
	}
	
	public Button setPos(float posX, float posY, float sizeX, float sizeY)
	{
		this.posX = posX;
		this.posY = posY;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		return this;
	}
	
	public float[] getPos()
	{
		return new float[] {posX, posY, sizeX, sizeY};
	}
	
	
	public Button setThread(Thread thread)
	{
		this.thread = thread;
		return this;
	}
	
	public Thread getThread()
	{
		return thread;
	}
	
	public Button setCallback(String callback, Class[] myclass, Object[] object)
	{
		this.callback = callback;
		this.myclass = myclass;
		this.object = object;
		return this;
	}
	
	public void act()
	{
		drawForm();
		drawText();
		if (thread != null) {
			if (cb.buttonPressed(posX, posY, sizeX, sizeY)) {
				thread.start();
			}
		}else if (callback != null) {
			callback(callback, myclass, object);
		}
	}
	
	private void drawForm() {
		Color bc = buttonStyle.backgroundColor;
		if (cb.mouseOver(posX, posY, sizeX, sizeY) && buttonStyle.overBackgroundColor!=null) bc = buttonStyle.overBackgroundColor;
		if (bc == null) app.noFill();
		else app.fill(bc.getR(), bc.getG(), bc.getB());
		
		Color sc = buttonStyle.strokeColor;
		if (cb.mouseOver(posX, posY, sizeX, sizeY) && buttonStyle.overStrokeColor!=null) sc = buttonStyle.overStrokeColor;
		if (sc == null) app.noStroke();
		else app.stroke(sc.getR(), sc.getG(), sc.getB());
	
		if (buttonStyle.form==app.RECT) app.rect(posX, posY, sizeX, sizeY, buttonStyle.cornerForm);
		else if (buttonStyle.form==app.ELLIPSE) app.ellipse(posX+sizeX/2, posY+sizeY/2, sizeX, sizeY);
		app.stroke(0);
	}
	
	private void drawText() {
		Color tc = buttonStyle.textColor;
		if (cb.mouseOver(posX, posY, sizeX, sizeY) && buttonStyle.overTextColor!=null) tc = buttonStyle.overTextColor;
		if (tc == null) app.noFill();
		else app.fill(tc.getR(), tc.getG(), tc.getB());
		
		app.textSize(buttonStyle.textSize);
		app.textAlign(app.CENTER, app.CENTER);
		
		if (buttonStyle.form==app.RECT) app.text(text, posX+sizeX/2+buttonStyle.textOffset.x, posY+sizeY/2+buttonStyle.textOffset.y);
		else if (buttonStyle.form==app.ELLIPSE) app.text(text, posX+sizeX/2+buttonStyle.textOffset.x, posY+sizeY/2-(sizeY*10/100)+buttonStyle.textOffset.y);
	}
	
	private void callback(String callback, Class[] myclass, Object[] object) {
		if (cb.buttonPressed(posX, posY, sizeX, sizeY)) {
			if (callback!="")buttonCallback(callback, myclass, object);
		}
	}
	private void buttonCallback(String callback, Class[] myclass, Object[] object) {
		try {
			app.getClass().getMethod(callback, myclass).invoke(app, object);
		}
		catch (Exception e) {
			app.println(e);
		}
	}
	
	public void forceStartExe() {
		if (thread != null) {
			thread.start();
		}else if (callback != null) {
			buttonCallback(callback, myclass, object);
		}
	}
}
