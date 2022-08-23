package com.ammar.bezier.display;

 

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;

import javax.swing.JFrame;

import com.ammar.bezier.Player;
import com.ammar.bezier.gfx.bezier;

public class Display extends JFrame {

	Player player;
 
	public double x = 300, y = 600, angle, length = 100;
	private Canvas canvas;
	
	  @Override
	    public void paint(Graphics gr)
	    {
		
 	       System.err.println(x);
	  
	       

	    }
	public Display(int width, int height, Player player)
	{
		this.player = player;
		 
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		 add(canvas);
		 pack();
		 setResizable(false);
		 setLocationRelativeTo(null);
		
		setVisible(true);
		
	}
	public JFrame getFrame()
	{
		return this;
	}
	public Canvas getCanvas()
	{
		return canvas;
	}
	
}
 