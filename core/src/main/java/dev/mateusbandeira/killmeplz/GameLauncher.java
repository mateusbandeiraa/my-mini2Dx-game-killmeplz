package dev.mateusbandeira.killmeplz;

import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

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
