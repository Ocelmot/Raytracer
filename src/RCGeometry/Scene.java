//Chrys Adams

package RCGeometry;

import DIB.*;
import RCGeometry.*;
import RCGeometry.Collidables.*;

import java.util.LinkedList;
import java.lang.Double;


public class Scene{
	private LinkedList<Collidable> shapes;
	private LinkedList<Light> lights;
	
	private Color ambient;
	private Color background;
	
	public Scene(){
		shapes = new LinkedList<Collidable>();
		lights = new LinkedList<Light>();
		
		ambient = new Color(20,20,20);
		background = new Color(255,255,255);
	}
	public void setBackground(Color c){
		background = c;
	}
	public void addShape(Collidable c){
		shapes.add(c);
	}
	public void addLight(Light l){
		lights.add(l);
	}
	public Color render(Ray r, int depth){
	
				if(depth<=0) return background;
	
				Collidable o=null;
				
				double dist=Double.POSITIVE_INFINITY;
				for(Collidable obj : shapes){			//find object to render
					double d = obj.getCollision(r);
					if((d>0.002)&&(d<dist)){
						dist=d;
						o=obj;
					}
				}
				if(o==null){
					return background;
				}
				
				
				
				
				Vector eye = r.getDirection().scaleTo(-1);
				Vector N = o.getNormal(r.getPoint(dist));
				
				Color c= new Color(0,0,0);
				c=c.add(o.getAmbient().multiply(ambient));
				
				
				
				

				
				
				
				
				
				for(Light l : lights){					//Phong shading
					
					boolean skip = false;
					for(Collidable obj : shapes){
						double d = obj.getCollision(new Ray(r.getPoint(dist),l.getPoint().sub(r.getPoint(dist)).normalize()));
						if(d>0.000002&&d<r.getPoint(dist).dist(l.getPoint())){
							skip=true;
							break;
						}
					}
					if(skip){
						continue;
					}
					
					
					Vector L = l.getPoint().sub(r.getPoint(dist));
					L=L.normalize();
					
					
					
					if(L.dot(N)<=0){
						continue;
					}
					
					Vector R = (N.scale(N.dot(L)*2)).sub(L);
					R = R.normalize();
					Vector V = eye;
				
					c=c.add(o.getDiffuse().multiply(l.getColor()).scale(L.dot(N)));
					c=c.add(o.getSpecular().multiply(l.getColor()).scale(Math.pow(R.dot(V),o.getShiny())));
				}
				
				
								//refelction
				if(o.getReflectiveness()!=0){
					Vector camreflect = (N.scale(N.dot(eye)*2)).sub(eye);
					Ray nr = new Ray(r.getPoint(dist),camreflect.normalize());
					Color reflection = this.render(nr,depth-1);
				
					//c = c.scale(1-o.getReflectiveness()).add(reflection.scale(o.getReflectiveness()));
					//c = c.multiply(reflection);
					//c = c.add(reflection);
					c = c.add(reflection.scale(o.getReflectiveness()));
				}
				
				
				
				
				
				
				
				
				
				return c;
	
	}
}