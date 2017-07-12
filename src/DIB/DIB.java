//Chrys Adams

package DIB;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class DIB{
	private int xSize;
	private int ySize;
	private ByteBuffer header;
	private ByteBuffer image;
	public DIB(int x, int y){
		xSize = x;
		ySize = y;
		header = ByteBuffer.allocate(40);
		header.order(ByteOrder.LITTLE_ENDIAN);
		header.putInt(40);//size of header
		header.putInt(x);
		header.putInt(y);
		header.putShort((short)1);//number of panes
		header.putShort((short)32);// number of bits per pixel
		header.putInt(0);//no compression
		header.putInt(x*y*4);
		header.putInt(2835);
		header.putInt(2835);
		header.putInt(0);
		header.putInt(0);
		image = ByteBuffer.allocate(x*y*4);
		image.order(ByteOrder.LITTLE_ENDIAN);
		this.setTo((byte)255,(byte)255,(byte)255,(byte)255);
	}
	public void setTo(byte r,byte g, byte b, byte a){
		int i=0;
		for(;i<xSize*ySize*4;i=i+4){
			image.put(i,b);
			image.put(i+1,g);
			image.put(i+2,r);
			image.put(i+3,a);
		}
	}
	public void setTo(Color c){
		setTo(c.getR(),c.getG(),c.getB(),c.getA());
	}
	public void set(int x, int y, byte r,byte g, byte b, byte a){
		if(x>xSize){
			return;
		}
		if(x<0){
			return;
		}
		if(y>ySize){
			return;
		}
		if(y<0){
			return;
		}
		int i = ((ySize-1-y)*xSize)+x;
		i*=4;
		image.put(i,b);
		image.put(i+1,g);
		image.put(i+2,r);
		image.put(i+3,a);
	}
	public void set(int x, int y, Color c){
		set(x,y,c.getR(),c.getG(),c.getB(),c.getA());
	}
	public void save(String name){
		try{
			ByteBuffer BMPHeader = ByteBuffer.allocate(14);
			BMPHeader.order(ByteOrder.LITTLE_ENDIAN);
			BMPHeader.put((byte)66);
			BMPHeader.put((byte)77);
			BMPHeader.putInt((xSize*ySize*4)+40+14);
			BMPHeader.putInt(0);
			BMPHeader.putInt(14+40);
			FileOutputStream out = new FileOutputStream(name);
			out.write(BMPHeader.array(),0,14);
			out.write(header.array(),0,40);
			out.write(image.array(),0,xSize*ySize*4);
			out.close();
		}catch(FileNotFoundException e){
			System.out.println("File not found.");
		}catch(IOException e){
			System.out.println("IO Exception.");
		}
	}
	public static void main(String[] args){
		DIB test = new DIB(800,600);
		
		test.set(100,100,(byte)0,(byte)0,(byte)255,(byte)255);
		test.save("test.bmp");
	}
}