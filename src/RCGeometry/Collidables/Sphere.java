//Chrys Adams

package RCGeometry.Collidables;

import RCGeometry.*;
import RCGeometry.Collidables.*;
import DIB.Color;

import java.lang.Math;

public class Sphere extends Collidable{
	Vector c;
	double r;
	public Sphere(Vector center, double radius, Color color){
		super(color);
		c=center;
		r=radius;
	}
	public double getCollision(Ray r){
		double a;
		double b;
		double c;
		Vector d;
		Vector s;
		d=r.getDirection();
		s=r.getStart();
		a=(d.getX()*d.getX()+d.getY()*d.getY()+d.getZ()*d.getZ());
		b=2*(d.getX()*(s.getX()-this.c.getX())+ d.getY()*(s.getY()-this.c.getY())+d.getZ()*(s.getZ()-this.c.getZ()));
		c=(s.getX()-this.c.getX())*(s.getX()-this.c.getX())+(s.getY()-this.c.getY())*(s.getY()-this.c.getY())+(s.getZ()-this.c.getZ())*(s.getZ()-this.c.getZ())-(this.r*this.r);
		double disc = b*b-4*a*c;
		if(disc<0){
			return 0;
		}
		if(disc==0){
			return -b/(2*a);
		}
		double one=(-b+Math.sqrt(disc))/(2*a);
		double two=(-b-Math.sqrt(disc))/(2*a);
		if(one<two){
			if(one>=0){
				return one;
			}
		}
		if(two>=0){
			return two;
		}
		return 0;
	}
	public Vector getNormal(Vector v){
		return v.sub(c).normalize();
	}
}