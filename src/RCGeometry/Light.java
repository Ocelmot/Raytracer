//Chrys Adams

package RCGeometry;

import RCGeometry.*;
import DIB.Color;

public class Light{
	private Vector p;
	private Color c;
	public Light(Vector p, Color c){
		this.p=p;
		this.c=c;
	}
	public Vector getPoint(){
		return new Vector(p);
	}
	public Color getColor(){
		return new Color(c);
	}


}