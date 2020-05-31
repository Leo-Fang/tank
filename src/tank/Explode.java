package tank;

import java.awt.Graphics;

public class Explode extends GameObject {

	private  int x, y;
	
	public static int WIDTH = ResourceMgr.explodes[0].getWidth();
	public static int HEIGHT = ResourceMgr.explodes[0].getHeight();
	
	GameModel gm = null;
	
	private int step = 0;
	
	public Explode(int x, int y, GameModel gm) {
		this.x = x;
		this.y = y;
		this.gm = gm;
		
	}

	public void paint(Graphics g) {
		g.drawImage(ResourceMgr.explodes[step++], x, y, null);
		if(step >= ResourceMgr.explodes.length)
			gm.remove(this);
	}
	
}
