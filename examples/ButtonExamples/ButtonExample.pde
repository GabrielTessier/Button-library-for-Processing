import gabriel.ButtonManager.*;

ButtonManager buttonManager;

Button add, reset, set1000;
int i = 0;

void setup() {
  size(1000, 1000);
  
  buttonManager = new ButtonManager(this);
  
  add = new Button("add", width/2-100, height/2-200, 200, 200, RECT);
  add.getButtonStyle().setTextSize(40);
  
  add.getButtonStyle().setTextColor(255,0,0)
                      .setStrokeColor(0,0,0)
                      .setBackgroundColor(0,255,255);
                      
                      
  add.getButtonStyle().setOverTextColor(0,255,0)
                      .setOverStrokeColor(0,0,255)
                      .setOverBackgroundColor(255,0,255);
                      
  add.setCallback("incremente", null, null);
  
  reset = new Button("reset", width/2-50, height/2+50, 100, 50, RECT);
  reset.setButtonStyle(add.getButtonStyle());
  reset.setCallback("setCounter", new Class[] {int.class}, new Object[] {0});
  
  set1000 = new Button("set", width/2-50, height/2+150, 100, 50, RECT);
  set1000.setButtonStyle(add.getButtonStyle());
  set1000.setCallback("setCounter", new Class[] {int.class}, new Object[] {1000});
}

void draw() {
  background(204);
  
  fill(0);
  text(i, width/2, 100);
  
  add.act();
  reset.act();
  set1000.act();
  
  buttonManager.always();
}

void incremente() {
  println("COUCOU ", i);
  i++;
}

void setCounter(int new_val) {
  i = new_val;
}
