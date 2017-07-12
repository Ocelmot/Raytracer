# Raytracer
A raytracer written in Java

### Build
  Build the raytracer from the top directory with
``` bash
ant compile
```
Or build and run with just
``` bash
ant
```

### Output
You will find a sample rendering out/test.bmp

### Theory
A raytracer is a method of rendering geometry to images. The final color of each pixel in the image to be rendered is calculated by creating a ray that starts at the camera's focal point and intersects the pixel.
The color of each ray is determined by the color of the closest object that intersects the ray.

The color may be adjusted by the presence of nearby light sources.
Light sources that have an uninterrupted path to the object add their color to the surface of the object.

If the surface is reflective, a new ray is generated which follows the angle of reflection away from the object's surface.
The color of this new ray is then used as the color of the object at this point on its surface.

If the ray does not collide with any object, the color of the background is used instead.

### Overview
To render images in the manner Raytracer.java has three main components.
The Camera, the Scene, and the contents of the Scene - Lights, and the Collidable geometry to be rendered.
The Camera determines the focal point, direction, and resolution of the image to be rendered.
Therefore it needs this information when constructed.

The parameters in order are: the position of the focal point, the direction it's facing, its rotation, and the x and y resolution to render its image.
```java
Camera cam  = new Camera(new Vector(0,0,0), new Vector(5,0,0), new Vector(0,1,0), 1920, 1080);
```
Next the Scene is created.
```java
Scene s = new Scene();
```
The Scene is basically a container to hold the various Collidable geometry and Light objects.
It has three important methods:

Setting the background color.
```java
s.setBackground(new Color(0,0,0));
```
Adding some Collidable geometry.
```java
s.addShape(sh);
```
And adding a Light source.
```java
s.addLight(new Light(new Vector(5,6,0), new Color(255,255,255)));
```
The final step is to pass the scene to a camera to render it to a DIB object, and save that image.
```java
DIB image = cam.renderScene(s);
image.save("out/test.bmp");
```
### Collidables
Any class that extends the abstract class Collidable may be added to the scene.
At the moment there are two such classes: Sphere and Triangle.
A collidable object must be able to tell the renderer whether collided with a ray, and how far along the ray the collision occurs.
``` java
public abstract double getCollision(Ray r);
```
It must also be able to tell the renderer the vector normal to the point of collision.
``` java
public abstract Vector getNormal(Vector v);
```
