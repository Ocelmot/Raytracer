//Chrys Adams

package RCGeometry.Collidables;

import DIB.Color;
import RCGeometry.*;

public abstract class Collidable{
	private Color a;
	private Color d;
	private Color s;
	private double sc;
	private double reflective;
	public Collidable(Color c){
		a=new Color(c);
		d=new Color(c);
		s=new Color(255,255,255);
		sc=50;
		reflective = 0;
	}
	public abstract double getCollision(Ray r);
	public abstract Vector getNormal(Vector v);
	public Color getAmbient(){
		return new Color(a);
	}
	public Color getDiffuse(){
		return new Color(d);
	}
	public Color getSpecular(){
		return new Color(s);
	}
	public double getShiny(){
		return sc;
	}
	public double getReflectiveness(){
		return reflective;
	}
	public void setAmbient(Color a){
		this.a=a;
	}
	public void setDiffuse(Color d){
		this.d=d;
	}
	public void setSpecular(Color s){
		this.s=s;
	}
	public void setShiny(double s){
		sc=s;
	}
	public void setReflectiveness(double ref){
		if(ref<0) ref = 0;
		if(ref>1) ref = 1;
		reflective = ref;
	}

}