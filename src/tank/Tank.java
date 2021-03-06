package tank;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Tank {

	private int x, y;
	private Dir dir = Dir.DOWN;
	private static final int SPEED = Integer.parseInt((String)PropertyMgr.get("tankSpeed"));
	
	public static final int WIDTH = ResourceMgr.goodTankU.getWidth();
	public static final int HEIGHT = ResourceMgr.goodTankU.getHeight();
	
	private boolean moving = true;//Tank移动或停止的判断标志
	private boolean living = true;//Tanks寿命的判断标志
	
	private Group group = Group.BAD;
	
	private TankFrame tf = null;
	Rectangle rect = new Rectangle();
	
	private Random random = new Random();
	
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}
	
	public Tank(int x, int y, Dir dir, Group group, TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.tf = tf;
		
		rect.x = this.x;
		rect.y = this.y;
		rect.width = WIDTH;
		rect.height = HEIGHT;
	}

	//把换坦克的方法定义到Tank类中
	public void paint(Graphics g) {
		if(!living)
//			return;
			tf.tanks.remove(this);//Tank死后需要从List中移除
		
		switch(dir){
		case LEFT:
			g.drawImage(this.group==Group.GOOD?ResourceMgr.goodTankL:ResourceMgr.badTankL, x, y, null);
			break;
		case RIGHT:
			g.drawImage(this.group==Group.GOOD?ResourceMgr.goodTankR:ResourceMgr.badTankR, x, y, null);
			break;
		case UP:
			g.drawImage(this.group==Group.GOOD?ResourceMgr.goodTankU:ResourceMgr.badTankU, x, y, null);
			break;
		case DOWN:
			g.drawImage(this.group==Group.GOOD?ResourceMgr.goodTankD:ResourceMgr.badTankD, x, y, null);
			break;
		}
		
		move();
	}

	private void move() {
		if(!moving)
			return;
		
		switch(dir) {
		case LEFT:
			x -= SPEED;
			break;
		case RIGHT:
			x += SPEED;
			break;
		case UP:
			y -= SPEED;
			break;
		case DOWN:
			y += SPEED;
			break;
		default:
			break;
		}		
		
		if(this.group == Group.BAD && random.nextInt(100) > 95)
			this.fire();
		if(this.group == Group.BAD && random.nextInt(100) > 95)
			randomDir();
		
		boundsCheck();
		
		rect.x = this.x;
		rect.y = this.y;
	}

	private void randomDir() {
		this.dir = Dir.values()[random.nextInt(4)];
	}
	
	private void boundsCheck() {
		if(this.x < 2)
			this.x = 2;
		if(this.x > TankFrame.GAME_WIDTH - Tank.WIDTH - 2)
			this.x = TankFrame.GAME_WIDTH - Tank.WIDTH - 2;
		if(this.y < 32)
			this.y = 32;
		if(this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2)
			this.y = TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2;
	}

	public void fire() {
		int bX = this.x + Tank.WIDTH/2 -Bullet.WIDTH/2;
		int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
		tf.bullets.add(new Bullet(bX, bY, this.dir, this.group, this.tf));
	}

	public void die() {
		this.living = false;
	}
	
}
