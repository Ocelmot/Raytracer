//Chrys Adams

package RCGeometry;

import DIB.DIB;

public class Camera{
	private Vector eye;
	private Vector dir;
	private Vector up;
	private Vector left;
	private int xSize;
	private int ySize;
	private double fov;
	public Camera(Vector eye, Vector dir, Vector up, int xSize, int ySize){
		this.eye = eye;
		this.dir = dir.normalize();
		this.up = up;
		left = dir.cross(up);
		this.xSize = xSize;
		this.ySize = ySize;
		up = dir.cross(left);
		fov = Math.toRadians(70);
		left = left.scaleTo(Math.tan(fov/2)/(xSize/2));
		this.up = this.up.scaleTo(Math.tan((fov/2)*((double)ySize/(double)xSize))/(ySize/2));
	}
	public Ray getRay(int x, int y){
		x=-(x-(xSize/2));
		y=-(y-(ySize/2));
		Vector r = new Vector(dir);
		r=r.add(left.scale(x));
		r=r.add(up.scale(y));
		return new Ray(eye,r);
	}
	public Vector getDir(){
		return new Vector(dir);
	}
	public DIB renderScene(Scene s){
		DIB img = new DIB(xSize,ySize);
		for(int y=0;y<ySize;y++){
			for(int x=0;x<xSize;x++){
				img.set(x,y,s.render(getRay(x,y),15));
			}
		}
		return img;
	}
	public void debug(){
		System.out.println(eye);
		System.out.println(dir);
		System.out.println(up);
		System.out.println(left);
		System.out.println("---test data---");
		System.out.println("ray for x=400 y=300");
		Ray r = getRay(400,300);
		System.out.println(r.getStart());
		System.out.println(r.getDirection());
	}
}
