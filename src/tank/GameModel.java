package tank;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import tank.cor.ColliderChain;

public class GameModel {

	Tank myTank = new Tank(200, 400, Dir.DOWN, Group.GOOD, this);
//	List<Bullet> bullets = new ArrayList<>();
//	List<Tank> tanks = new ArrayList<>();
//	List<Explode> explodes = new ArrayList<>();
	
	private List<GameObject> objects = new ArrayList<>();
	
	ColliderChain chain = new ColliderChain();
	
	public GameModel(){
		int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));
		
		//初始化地方坦克
		for (int i = 0; i < initTankCount; i++) {
			add(new Tank(10+i*80, 200, Dir.DOWN, Group.BAD, this));
		}
	}
	
	public void add(GameObject go) {
		this.objects.add(go);
	}
	
	public void remove(GameObject go) {
		this.objects.remove(go);
	}
	
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.WHITE);
//		g.drawString("子弹的数量"+bullets.size(), 10, 60);
//		g.drawString("敌人的数量"+tanks.size(), 10, 80);
//		g.drawString("爆炸的数量"+explodes.size(), 10, 100);
		g.setColor(c);
		
		myTank.paint(g);
		//画子弹
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).paint(g);
		}
		
		//碰撞检测
//		for (int i = 0; i < bullets.size(); i++) {
//			for (int j = 0; j < tanks.size(); j++) {
//				bullets.get(i).collideWith(tanks.get(j));
//			}
//		}
		
		for (int i = 0; i < objects.size()-1; i++) {
			for (int j = i+1; j < objects.size(); j++) {
				GameObject o1 = objects.get(i);
				GameObject o2 = objects.get(j);
				chain.collide(o1, o2);
			}
		}
		
	}

	public Tank getMainTank() {
		return myTank;	
	}

}
