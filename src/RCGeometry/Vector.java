//Chrys Adams

package RCGeometry;

import java.lang.Math;

public class Vector{
	private double x;
	private double y;
	private double z;
	public Vector(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public Vector(Vector other){
		x=other.getX();
		y=other.getY();
		z=other.getZ();
	}
	public double dot(Vector b){
		return (x*b.getX())+(y*b.getY())+(z*b.getZ());
	}
	public Vector cross(Vector b){
		return new Vector(y*b.getZ()-z*b.getY(),z*b.getX()-x*b.getZ(),x*b.getY()-y*b.getX());
	}
	public Vector normalize(){
		Vector r = new Vector(0,0,0);
		double dist = mag();
		r.setX(x/dist);
		r.setY(y/dist);
		r.setZ(z/dist);
		return r;
	}
	public Vector scaleTo(double length){
		Vector r = new Vector(0,0,0);
		double dist = mag();
		r.setX(x*(length/dist));
		r.setY(y*(length/dist));
		r.setZ(z*(length/dist));
		return r;
	}
	public Vector scale(double length){
		Vector r = new Vector(0,0,0);
		r.setX(x*length);
		r.setY(y*length);
		r.setZ(z*length);
		return r;
	}
	public double dist(Vector b){
		return Math.sqrt(Math.pow(b.getX()-x,2)+Math.pow(b.getY()-y,2)+Math.pow(b.getZ()-z,2));
	}
	public double mag(){
		return Math.sqrt(x*x+y*y+z*z);
	}
	public Vector add(Vector b){
		Vector r = new Vector(0,0,0);
		r.setX(x+b.x);
		r.setY(y+b.y);
		r.setZ(z+b.z);
		return r;
	}
	public Vector sub(Vector b){
		Vector r = new Vector(0,0,0);
		r.setX(x-b.x);
		r.setY(y-b.y);
		r.setZ(z-b.z);
		return r;
	}
	public String toString(){
		String r = new String("(");
		r+=x+",";
		r+=y+",";
		r+=z+")";
		return r;
	}
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	public double getZ(){
		return z;
	}
	public void setX(double in){
		x=in;
	}
	public void setY(double in){
		y=in;
	}
	public void setZ(double in){
		z=in;
	}
}