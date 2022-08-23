package com.ammar.bezier.gfx;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.util.Random;
import java.util.Vector;

import com.ammar.bezier.display.Display;
import com.ammar.bezier.iinput.MouseManager;

class bouns{
	double x, y;
	Random rand = new Random();
	double dx,dy;
	double co = 0;
	double delta;

	public bouns(double x, double y)
	{
		this.x=x;
		this.y=y;
		
		delta = 0.01;
	  
	}
	public void tick()
	{
		if(co==380)delta*=-1;
		
		 
		co+=delta;
		dy = Math.sin(co)*4;
	    
	    
		this.y+=dy;
		this.x+=dx;
}
	public void tick2()
	{
		if(co==380)delta*=-1;
		  
		co-=delta;
		dy = Math.sin(co)*4;
	 
		this.y+=dy;
	 
	 
	}
}
class Leaf
{
	private float x;
	private float y;
	private double val;
	private double valy;
	private int by;
	private double inc;
	private Color color;
	private Random rand = new Random();
	Point p1 = new Point(30,30);
	Point p2 = new Point(40,20);
	Point p3 = new Point(55,30);
	Point p4 = new Point(40,40);

	public Leaf()
	{
		x =  rand.ints (80, 500).findFirst().getAsInt();
		y =  rand.ints (200, 250).findFirst().getAsInt();
		by =  rand.ints (20, 100).findFirst().getAsInt();
		val = 0; 
		inc = rand.nextDouble( );
		valy = rand.nextInt(8);
		p1 = new Point((int)x,(int)y);
		p2 = new Point((int)x+10,(int)y-10);
		p3 = new Point((int)x+25,(int)y);
		p4 = new Point((int)x+10,(int)y+10);
		if(x<150)
		color = new Color(252,115,115);
		else if(x<250)
		color = new Color(243,99,176);
		else if(x<350)
		color = new Color(255,255,115);
		else if(x>450)	
		color = new Color(255,178,102);
	}
	public void render(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
	  for(double t=0.0;t<1.001;t+=0.01)
	  {
		   double X = lerp(p1.x,p2.x,t);
		   double Y = lerp(p1.y,p2.y,t);
		   double X2 = lerp(p2.x,p3.x,t);
		   double Y2 = lerp(p2.y,p3.y,t);
		   g2d.fillOval(p1.x, p1.y-8, 22, 15);
		   g2d.setColor(color);
		   g2d.setStroke(new BasicStroke(2));
		   x = (int)lerp(X,X2,t);
		   y = (int)lerp(Y,Y2,t);
 		   g.drawLine((int)x,(int)y,(int)x,(int)y);
		   X = lerp(p1.x,p4.x,t);
		   Y = lerp(p1.y,p4.y,t);
		   X2 = lerp(p4.x,p3.x,t);
		   Y2 = lerp(p4.y,p3.y,t);


		   x = (int)lerp(X,X2,t);
		   y = (int)lerp(Y,Y2,t);
		   g2d.drawLine((int)x,(int)y,(int)x,(int)y);
		   g2d.drawArc((int)p1.x-6,(int)p1.y,10,(int)12,90,90);
	  }
	}
	public void tick()
	{
		double a = Math.cos(val)*by;
	 
		p1.x+=a;
		p2.x+=a;
		p3.x+=a;
		p4.x+=a;
		p1.y+=valy;
		p2.y+=valy;
		p3.y+=valy;
		p4.y+=valy;
		val+=inc;
	 
//	 
	}
	private double lerp(double p1, double p2, double t)
 	{
 		return p1+(p2-p1)*t;
 	 }
}
public class bezier {
	
	boolean o = true;
	int counter =1;
	private Display display;
	Random rand = new Random();
	Leaf[] leafs= new Leaf[15];
	//////////////////////RECURSION/////////////////////
	public bezier(Display display)
	{
		for (int i = 0; i < leafs.length; i++) {
			
			leafs[i] = new Leaf()  ;
		}
		this.display = display;
	}
	public void perDraw(Graphics g)
	{
	
		Graphics2D g2d = (Graphics2D)g;
		 draw(300,600,0,150,g2d);
		
	}
	public void render(Graphics g)
	{
		for (int i = 0; i < leafs.length; i++) {
			
			leafs[i].render(g);
		}
	}
	public void tick()
	{
		for (int i = 0; i < leafs.length; i++) {
			
			leafs[i].tick();
		}
	}
	public void draw( double x,double y,double angle, double length, Graphics2D g2d)
	{
		
		 double x2 =  x- length*Math.sin( angle);
		 double y2 =  y- length*Math.cos( angle);
		 
		 g2d.setStroke(new BasicStroke((int)(length/20)));
		 if(length>10) {				
	         
			 g2d.setColor(new Color(0.18f,0.181f,0.19f,(float)(length/190)+0.013f ));
			 
			 g2d.drawLine((int)x, (int)y, (int)x2,(int) y2);
 
	       
	          
	          
		 }
		   if(length>6) {
		 
	       draw(x2, y2, angle+75, length*0.75, g2d);//100
	       draw(x2, y2, angle+125, length*0.67, g2d);//125
	          draw(x2, y2, angle-100, length*0.67, g2d);//125
	          draw(x2, y2, angle-150, length*0.67, g2d);//125
       

	     	 double x3 =  x- length*3*Math.sin( angle);
			 double y3 =  y- length*2*Math.cos( angle);
        	  g2d.setColor(new Color(1.0f,0.0f,0.949f,(float)(length/200) ));
        	  if(length<18)
	          g2d.drawLine((int)x , (int)y , (int)x3    ,(int)y3 );
	     
	       
		 }
	}	
	 
	private Point quaratic(bouns p0, bouns p1, bouns p2 , double t, Graphics2D g2d)
 	{
 		double x1 = lerp( p0.x,p1.x,t);
 		double y1 = lerp(p0.y,p1.y,t);
 		double x2 = lerp( p1.x,p2.x,t);
 		double y2 = lerp(p1.y,p2.y,t);
 		double x3 = lerp( x1,x2,t);
 		double y3 = lerp(y1,y2,t);
 		g2d.drawLine((int)x1,(int)y1  ,(int)x2 ,(int)y2);
 
 		return new Point((int)x3,(int)y3);
 	}
	private double lerp(double p1, double p2, double t)
	{
		return p1+(p2-p1)*t;
	}}

	
//class bouns{
//	double x, y;
//	Random rand = new Random();
//	double dx,dy;
// 
//	public bouns(double x, double y)
//	{
//		this.x=x;
//		this.y=y;
//		dx=rand.nextInt(7);
//		dy=rand.nextInt(7);
//		if(dx>3)dx=3-dx;
//		if(dy>3)dy=3-dy;
//	 
//	  
//	}
//	public void tick()
//	{
//		this.y+=dy*0.5;
//		this.x+=dx*0.5;
//		if(x>=600||x<0)dx*=-1;
//		if(y>=600||y<0)dy*=-1;
//	}
//}
//public class bezier {
//	bouns p0 = new bouns(60,300);
//	bouns p1 = new bouns(300,10);
//	bouns p2 = new bouns(100,20);
//	bouns p3 = new bouns(200,300);
//	bouns p4 = new bouns(200,550);
//	
//	private Random rand = new Random();
//	public void render(Graphics g)
//	{
//		 
//		p0.tick();
//	 
//	 
//		p3.tick();
//		p2.tick();
//		p1.tick();
//		p4.tick();
//		Graphics2D g2d = (Graphics2D)g;
//		g2d.setColor(Color.WHITE);
//		for(double t=0;t<=1.001;t+=0.004)
//		{
//			 
// 			g2d.setColor( new Color((int)(10*t) ,(int)(120*t),(int)(250*t)));
//			Point f = quaratic(p0,p1,p2,t,g2d);
//			Point f2 = quaratic(p1,p2,p3,t,g2d);
//			Point f3 = quaratic(p2,p3,p4,t,g2d);
//			 
// 			
// 			g2d.drawLine((int)f.x,(int)f.y  ,(int)f2.x ,(int)f2.y);
// 			g2d.drawLine((int)f2.x,(int)f2.y  ,(int)f3.x ,(int)f3.y);
//// 			
//// 			g2d.drawLine((int)x1 ,(int) y1  ,(int) x1 ,(int) y1);
//// 			g2d.drawLine((int) x2,(int)  y2  ,(int) x2 ,(int) y2);
//// 		    g2d.drawLine((int)x1,(int)y1,(int)x1,(int)y1);
// 			
// 		}
// 	}
// 	private Point quaratic(bouns p0, bouns p1, bouns p2 , double t, Graphics2D g2d)
// 	{
// 		double x1 = lerp( p0.x,p1.x,t);
// 		double y1 = lerp(p0.y,p1.y,t);
// 		double x2 = lerp( p1.x,p2.x,t);
// 		double y2 = lerp(p1.y,p2.y,t);
// 		double x3 = lerp( x1,x2,t);
// 		double y3 = lerp(y1,y2,t);
// 		g2d.drawLine((int)x1,(int)y1  ,(int)x2 ,(int)y2);
// 
// 		return new Point((int)x3,(int)y3);
// 	}
//	private double lerp(double p1, double p2, double t)
//	{
//		return p1+(p2-p1)*t;
//	}}
//}