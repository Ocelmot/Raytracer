//Chrys Adams

package DIB;

public class Color{
	private byte r;
	private byte g;
	private byte b;
	private byte a;
	public Color(Color c){
		r=c.getR();
		g=c.getG();
		b=c.getB();
		a=c.getA();
	}
	public Color(byte r, byte g, byte b){
		this.r=r;
		this.b=b;
		this.g=g;
		this.a=(byte)255;
	}
	public Color(byte r, byte g, byte b, byte a){
		this.r=r;
		this.b=b;
		this.g=g;
		this.a=a;
	}
	public Color(int r, int g, int b){
		if(r>255)r=255;
		if(g>255)g=255;
		if(b>255)b=255;
		this.r=(byte)r;
		this.g=(byte)g;
		this.b=(byte)b;
		this.a=(byte)255;
	}
	public Color(int r, int g, int b, int a){
		if(r>255)r=255;
		if(g>255)g=255;
		if(b>255)b=255;
		if(a>255)a=255;
		this.r=(byte)r;
		this.g=(byte)g;
		this.b=(byte)b;
		this.a=(byte)a;
	}
	public Color add(Color c){
		int r1 = ((int)r&0xff)+((int)c.getR()&0xff);
		if(r1>255) r1=255;
		int g1 = ((int)g&0xff)+((int)c.getG()&0xff);
		if(g1>255) g1=255;
		int b1 = ((int)b&0xff)+((int)c.getB()&0xff);
		if(b1>255) b1=255;
		int a1 = ((int)a&0xff)+((int)c.getA()&0xff);
		if(a1>255) a1=255;
		
		return new Color(r1,g1,b1,a1);
	}
	public Color multiply(Color c){
		return new Color(
			(byte)((((int)r&0xff) * ((int)c.getR()&0xff))/255),
			(byte)((((int)g&0xff) * ((int)c.getG()&0xff))/255),
			(byte)((((int)b&0xff) * ((int)c.getB()&0xff))/255)
		);
	}
	public Color scale(double s){
		int r1=(int)(s*((int)r&0xff));
		int g1=(int)(s*((int)g&0xff));
		int b1=(int)(s*((int)b&0xff));
		if(r1>255)r1=255;
		if(g1>255)g1=255;
		if(b1>255)b1=255;
		return new Color(r1,g1,b1);
	}
	public String toString(){
		return "C("+((int)r&0xff)+","+((int)g&0xff)+","+((int)b&0xff)+")";
	}
	public byte getR(){
		return r;
	}
	public byte getG(){
		return g;
	}
	public byte getB(){
		return b;
	}
	public byte getA(){
		return a;
	}
	public void debug(Color previous){
		if(((int)r&0xff)<((int)previous.getR()&0xff)){
			System.out.println("error in color: "+this+" and "+previous);
		}
	}
}