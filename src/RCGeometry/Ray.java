//Chrys Adams

package RCGeometry;

public class Ray{
	private Vector s;
	private Vector d;
	public Ray(Vector s, Vector d){
		this.s=s;
		this.d=d.normalize();
	}
	public Vector getPoint(double l){
		return s.add(d.scale(l));
	}
	public Vector getStart(){
		return new Vector(s);
	}
	public Vector getDirection(){
		return new Vector(d);
	}
}