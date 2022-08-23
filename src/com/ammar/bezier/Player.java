package com.ammar.bezier;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.ammar.bezier.display.Display;
import com.ammar.bezier.gfx.bezier;
import com.ammar.bezier.iinput.MouseManager;
 
 

public class Player implements Runnable{
	final static int WIDTH = 600;
	static final int HEIGHT = 600;
	private boolean running;
	private int time = 0;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private Display display;
	private Thread thread;
	private bezier bez;
	private MouseManager mouseManager;
	int count = 0;
	 
	private BufferedImage bufferedImage;
	public bezier get()
	{
		return bez;
	}
	public Player()
	{
		display = new Display(WIDTH, HEIGHT, this);
		bez= new bezier(display);
		mouseManager = new MouseManager();
		display.getCanvas(). addMouseListener(mouseManager);   
		display.getFrame(). addMouseListener(mouseManager); 
		display.getCanvas(). addMouseMotionListener(mouseManager);   
		display.getFrame(). addMouseMotionListener(mouseManager);
		try {
			bufferedImage = ImageIO.read(Player.class.getResource("/texture/1.jpg"));
		 
		} catch (IOException e) {
		 
			e.printStackTrace();
		}
	}
	public void render()
	{
		 
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {display.getCanvas().createBufferStrategy(3);return;}
		g = bs.getDrawGraphics();
		 
		//////////
		 
 
		g.drawImage(bufferedImage, 0, 0, WIDTH, HEIGHT, null);
		bez.render(g);
 		bez.tick();

		bez.perDraw(g);
	 
		bs.show();
		g.dispose();
	}
	@Override
	public void run()
	{
		 int fps = 60;
	        double timePerTick = 1000000000 / fps;
	        double dalta = 0;
	        long now;
	        long lastTime = System.nanoTime();
	        int counter = 0;
	        double ti=0;
	        while(running)
	        {
	            now = System.nanoTime();
	            dalta += (now - lastTime) / timePerTick;
	            ti += (now - lastTime);
	            lastTime = now;
	           if(dalta>=1)
	           {
	        	   
	                    render();
	                     
	                dalta--;
	                counter++;
	           }
	               
                
	            if(ti>=1000000000)
	            {
	            	
	             System.err.println(counter);
	            	ti=0;
	            	counter = 0;
	            	time++;
	             
	            	if(time==60)time = 1;
	            }
	            }
 	  
	        }
 
	private void tick() {
 
	}
	
	
	public synchronized void start()
	{
		if(running)return;
		thread = new Thread(this);
		running = true;
		thread.start();
	}
	public synchronized void stop() throws InterruptedException
	{
		if(!running)return;
		thread.join();
		running = false;
	}
	
	
	
}