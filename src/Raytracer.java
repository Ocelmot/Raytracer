//Chrys Adams

import DIB.*;
import RCGeometry.*;
import RCGeometry.Collidables.*;

import java.util.LinkedList;
import java.lang.Double;

public class Raytracer{
	public static void main(String[] args){

		Camera cam  = new Camera(new Vector(0,0,0),new Vector(5,0,0),new Vector(0,1,0),1920,1080);
		Scene s = new Scene();
		s.setBackground(new Color(0,0,0));

		s.addShape(new Sphere(new Vector(5,-2,0),1.5,new Color(0,255,0)));
		s.addShape(new Sphere(new Vector(5,0.5,0),0.5,new Color(0,255,255)));
		s.addShape(new Triangle(new Vector(8.5,1,0), new Vector(7,-1,1), new Vector(7,-1,-1), new Color(255,0,0)));


		Collidable sh = new Sphere(new Vector(5,0,-2),1,new Color(255,0,0));
		sh.setReflectiveness(0.8);
		s.addShape(sh);

		s.addLight(new Light(new Vector(5,6,0),new Color(255,255,255)));

		DIB image = cam.renderScene(s);

		System.out.println("Saving image to file...");
		image.save("out/test.bmp");
	}
}
