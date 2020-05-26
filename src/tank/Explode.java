package tank;

import java.awt.Graphics;

import abstractfactory.BaseExplode;

public class Explode extends BaseExplode {

	private  int x, y;
	
	public static int WIDTH = ResourceMgr.explodes[0].getWidth();
	public static int HEIGHT = ResourceMgr.explodes[0].getHeight();
	
	TankFrame tf = null;
	
	private int step = 0;
	
	public Explode(int x, int y, TankFrame tf) {
		this.x = x;
		this.y = y;
		this.tf = tf;
		
//		new Thread(()->new Audio("audio/explode.wav").loop()).start();
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(ResourceMgr.explodes[step++], x, y, null);
		if(step >= ResourceMgr.explodes.length)
			tf.explodes.remove(this);
	}
	
}
