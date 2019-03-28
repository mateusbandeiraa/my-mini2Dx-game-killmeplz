package dev.mateusbandeira.killmeplz;

import java.util.ArrayList;

import org.mini2Dx.core.collisions.PointQuadTree;
import org.mini2Dx.core.collisions.QuadTree;
import org.mini2Dx.core.engine.geom.CollisionPoint;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.geom.Rectangle;
import org.mini2Dx.core.graphics.Graphics;

import com.badlogic.gdx.utils.Array;

import entity.AmmoRefueler;
import entity.Player;

public class GameLauncher extends BasicGame {
	public static final String GAME_IDENTIFIER = "dev.mateusbandeira.killmeplz";
	
	private Player player;
	private AmmoRefueler refueler1;
	@Override
	public void initialise() {
		player = new Player();
		refueler1 = new AmmoRefueler();
	}

	@Override
	public void update(float delta) {
		
		QuadTree<CollisionPoint> collisions = new PointQuadTree<>(4, 2, 0, 0, 640, 320);
		collisions.add(player.getPoint());
		
		Array<CollisionPoint> collisionsInArea = new Array<CollisionPoint>();
		collisions.getElementsWithinArea(collisionsInArea, new Rectangle(512, 8, 32, 32));
		
		System.out.println(collisionsInArea);
		
		
		player.update();
		refueler1.update();
	}

	@Override
	public void interpolate(float alpha) {
		player.interpolate(alpha);
		refueler1.interpolate(alpha);
	}

	@Override
	public void render(Graphics g) {
		refueler1.render(g);
		player.render(g);
	}
}
