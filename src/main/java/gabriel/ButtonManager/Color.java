package gabriel.ButtonManager;

public class Color {
	int r,g,b;
	
	Color(int _r, int _g, int _b){
		r=_r;
		g=_g;
		b=_b;
	}
	
	public int getR() {
		return r;
	}
	public int getG() {
		return g;
	}
	public int getB() {
		return b;
	}
	public void setRgb(int _r, int _g, int _b){
		r=_r;
		g=_g;
		b=_b;
	}
}
