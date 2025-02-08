package gabriel.ButtonManager;

//import gabriel.button.*;

import processing.core.PApplet;
import processing.core.PVector;
import processing.data.JSONArray;
import processing.data.JSONObject;

public class ButtonStyle {
	ButtonManager cb;
	PApplet app;
	
	int form;
	int cornerForm;
	
	Color backgroundColor;
	Color textColor;
	Color strokeColor;
	Color overBackgroundColor;
	Color overTextColor;
	Color overStrokeColor;
	
	int textSize;
	PVector textOffset;
	
	public ButtonStyle() {
		initButtonStyle();
	}
	public ButtonStyle(String fileName) {
		initButtonStyle();
		setStyleWithFile(fileName);
	}
	
	private void initButtonStyle() {
		this.cb = ButtonManager.getInstance();
		this.app = cb.getParent();
		
		this.form = app.RECT;
		this.cornerForm = 0;
		
		this.backgroundColor = new Color(0, 0, 0);
		this.textColor = new Color(255, 255, 255);
		this.strokeColor = new Color(0, 0, 0);
		this.overBackgroundColor = new Color(0, 0, 0);
		this.overTextColor = new Color(255, 255, 255);
		this.overStrokeColor = new Color(0, 0, 0);
		
		this.textSize = 10;
		this.textOffset = new PVector(0, 0);
	}
	
	public void setStyleWithOtherStyle(ButtonStyle otherStyle) {
    	form = otherStyle.form;
    	cornerForm = otherStyle.cornerForm;
    	
    	if (otherStyle.backgroundColor != null) backgroundColor = otherStyle.backgroundColor;
    	if (otherStyle.textColor != null) textColor = otherStyle.textColor;
    	if (otherStyle.strokeColor != null) strokeColor = otherStyle.strokeColor;
    	if (otherStyle.overBackgroundColor != null) overBackgroundColor = otherStyle.overBackgroundColor;
    	if (otherStyle.overTextColor != null) overTextColor = otherStyle.overTextColor;
    	if (otherStyle.overStrokeColor != null) overStrokeColor = otherStyle.overStrokeColor;
    	
    	textSize = otherStyle.textSize;
    	if (otherStyle.textOffset != null) textOffset = new PVector(otherStyle.textOffset.x, otherStyle.textOffset.y);
    }
	
	
    public void setStyleWithFile(String fileName) {
    	JSONObject json = app.loadJSONObject(fileName);

        String name = json.getString("name");
        
        try {
        	int buttonForm = json.getInt("form");
            if (buttonForm==0) this.form = app.RECT;
            else if (buttonForm==1) this.form = app.ELLIPSE;
        }catch (Exception e) {}
        
        try {
        	cornerForm = json.getInt("cornerForm");
        }catch (Exception e) {}
        
        try {
	        JSONObject bc = json.getJSONObject("backgroundColor");
	        if (bc != null) backgroundColor.setRgb(bc.getInt("r"), bc.getInt("g"), bc.getInt("b"));
        }catch (Exception e) {}
	        
        try {
	        JSONObject tc = json.getJSONObject("textColor");
	        if (tc != null) textColor.setRgb(tc.getInt("r"), tc.getInt("g"), tc.getInt("b"));
        }catch (Exception e) {}
	        
        try {
	        JSONObject sc = json.getJSONObject("strokeColor");
	        if (sc != null) strokeColor.setRgb(sc.getInt("r"), sc.getInt("g"), sc.getInt("b"));
        }catch (Exception e) {}
	        
        try {
	        JSONObject obc = json.getJSONObject("overBackgroundColor");
	        if (obc != null) overBackgroundColor.setRgb(obc.getInt("r"), obc.getInt("g"), obc.getInt("b"));
        }catch (Exception e) {}
	        
        try {
	        JSONObject otc = json.getJSONObject("overTextColor");
	        if (otc != null) overTextColor.setRgb(otc.getInt("r"), otc.getInt("g"), otc.getInt("b"));
        }catch (Exception e) {}
	        
        try {
	        JSONObject osc = json.getJSONObject("overStrokeColor");
	        if (osc != null) overStrokeColor.setRgb(osc.getInt("r"), osc.getInt("g"), osc.getInt("b"));
        }catch (Exception e) {}
        
        try {
        	textSize = json.getInt("textSize");
        }catch (Exception e) {}
        
        try {
	        JSONObject to = json.getJSONObject("textOffset");
	        if (to != null) textOffset = new PVector(to.getInt("x"), to.getInt("y"));
        }catch (Exception e) {}
    }
	
	public ButtonStyle setTextColor(int r, int g, int b)
	{
		textColor = new Color(r,g,b);
		return this;
	}
	
	public ButtonStyle setStrokeColor(int r, int g, int b)
	{
		strokeColor = new Color(r,g,b);
		return this;
	}
	
	public ButtonStyle setBackgroundColor(int r, int g, int b)
	{
		backgroundColor = new Color(r,g,b);
		return this;
	}
	
	public ButtonStyle setOverBackgroundColor(int r, int g, int b)
	{
		overBackgroundColor = new Color(r,g,b);
		return this;
	}
    public ButtonStyle setOverTextColor(int r, int g, int b)
    {
    	overTextColor = new Color(r,g,b);
    	return this;
    }
    public ButtonStyle setOverStrokeColor(int r, int g, int b)
    {
    	overStrokeColor = new Color(r,g,b);
    	return this;
    }
	
	public ButtonStyle setTextOffset(PVector newTextOffset)
	{
		textOffset = newTextOffset;
		return this;
	}
	
	public ButtonStyle setCornerForm(int form)
	{
		cornerForm = form;
		return this;
	}
	
	public ButtonStyle setTextSize(int newTextSize)
	{
		textSize = newTextSize;
		return this;
	}
}
