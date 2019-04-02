package dev.mateusbandeira.killmeplz;

import org.mini2Dx.core.collisions.PointQuadTree;
import org.mini2Dx.core.collisions.QuadTree;
import org.mini2Dx.core.collisions.RegionQuadTree;
import org.mini2Dx.core.engine.geom.CollisionPoint;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.geom.Rectangle;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.GameScreen;
import org.mini2Dx.core.screen.ScreenManager;

import com.badlogic.gdx.utils.Array;

import entity.AmmoRefueler;
import entity.Player;

public class IngameScreen extends BasicGameScreen {
	public static final int ID = 1;
	
	private Player player;
	private AmmoRefueler refueler1;
	private static final String MESSAGE = "Tank reloaded!";
	@Override
	public void initialise(GameContainer gc) {
		player = new Player();
		refueler1 = new AmmoRefueler();

	}

	@Override
	public void update(GameContainer gc, ScreenManager<? extends GameScreen> screenManager, float delta) {
		player.update();
		refueler1.update();

	}

	@Override
	public void interpolate(GameContainer gc, float alpha) {
		player.interpolate(alpha);
		refueler1.interpolate(alpha);

	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		refueler1.render(g);
		player.render(g);
		
		if(refueler1.checkCollision(player))
			g.drawString(MESSAGE, refueler1.getPoint().getX() - 32f, refueler1.getPoint().getY() + 48f);

	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return this.ID;
	}

}
