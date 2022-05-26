
package main.newpackage;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import Graficos.Assets;
import input.Teclado;
import Estados.EstadoJuego;
import ObjetosJuego.Constantes;


public class Juego2 extends JFrame implements Runnable{
	
	private static final long serialVersionUID = 1L;
	
	private Canvas canvas;
	private Thread thread;
	private boolean running = false;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private final int FPS = 60;
	private double TARGETTIME = 1000000000/FPS;
	private double delta = 0;
	private int AVERAGEFPS = FPS;
	
	private EstadoJuego gameState;
	private Teclado keyBoard;
	
	public Juego2()
	{
		setTitle("Space Ship Game");
		setSize(Constantes.WIDTH, Constantes.HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		
		canvas = new Canvas();
		keyBoard = new Teclado();
		
		canvas.setPreferredSize(new Dimension(Constantes.WIDTH, Constantes.HEIGHT));
		canvas.setMaximumSize(new Dimension(Constantes.WIDTH, Constantes.HEIGHT));
		canvas.setMinimumSize(new Dimension(Constantes.WIDTH, Constantes.HEIGHT));
		canvas.setFocusable(true);
		
		add(canvas);
		canvas.addKeyListener(keyBoard);
		setVisible(true);
	}
	
	

	public static void main(String[] args) {
		new Juego2().start();
	}
	
	
	private void update(){
		keyBoard.update();
		gameState.update();
	}

	private void draw(){
		bs = canvas.getBufferStrategy();
		
		if(bs == null)
		{
			canvas.createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		
		//-----------------------
		
		g.setColor(Color.BLACK);
		
		g.fillRect(0, 0, Constantes.WIDTH, Constantes.HEIGHT);
		
		gameState.draw(g);
		
		g.setColor(Color.WHITE);
		
		g.drawString(""+AVERAGEFPS, 10, 20);
		
		//---------------------
		
		g.dispose();
		bs.show();
	}
	
	private void init()
	{
		Assets.init();
		gameState = new EstadoJuego();
	}
	
	
	@Override
	public void run() {
		
		long now = 0;
		long lastTime = System.nanoTime();
		int frames = 0;
		long time = 0;
		
		init();
		
		while(running)
		{
			now = System.nanoTime();
			delta += (now - lastTime)/TARGETTIME;
			time += (now - lastTime);
			lastTime = now;
			
			
			
			if(delta >= 1)
			{		
				update();
				draw();
				delta --;
				frames ++;
			}
			if(time >= 1000000000)
			{
				AVERAGEFPS = frames;
				frames = 0;
				time = 0;
				
			}
			
			
		}
		
		stop();
	}
	
	public void start(){
		
		thread = new Thread(this);
		thread.start();
		running = true;
		
		
	}
	public void stop(){
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}