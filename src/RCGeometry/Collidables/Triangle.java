//Chrys Adams

package RCGeometry.Collidables;

import DIB.*;

import RCGeometry.*;
import RCGeometry.Collidables.*;
import DIB.Color;

import java.lang.Math;

public class Triangle extends Collidable{
	Vector a;
	Vector b;
	Vector c;
	public Triangle(Vector a, Vector b, Vector c, Color color){
		super(color);
		this.a=a;
		this.b=b;
		this.c=c;
	}
	public double getCollision(Ray r){
		double dist;
		double num;
		double denom;
		
		num=(a.sub(r.getStart())).dot(this.getNormal(null));
		denom=r.getDirection().dot(this.getNormal(null));
		
		if (num==0){
			//System.out.println("ray is coplanar");
			return 0;
		}
		if (denom==0){
			//System.out.println("Ray is parallel");
			return 0;
		}
		
		dist = num/denom;
		
		Vector collide = r.getPoint(dist).sub(r.getStart());
		
		Vector v1;
		Vector v2;
		Vector normal;
		
		v1 = a.sub(r.getStart());
		v2 = b.sub(r.getStart());
		normal = v1.cross(v2);
		if(collide.dot(normal)<=0){
			return 0;
		}
		v1 = b.sub(r.getStart());
		v2 = c.sub(r.getStart());
		normal = v1.cross(v2);
		if(collide.dot(normal)<=0){
			return 0;
		}
		v1 = c.sub(r.getStart());
		v2 = a.sub(r.getStart());
		normal = v1.cross(v2);
		if(collide.dot(normal)<=0){
			return 0;
		}
		
		return dist;
	}
	public Vector getNormal(Vector v){
		Vector normal;
		Vector v1;
		Vector v2;
		
		v1=b.sub(a);
		v2=c.sub(a);
		
		normal=v2.cross(v1);
		
		return normal.normalize();
	}
}