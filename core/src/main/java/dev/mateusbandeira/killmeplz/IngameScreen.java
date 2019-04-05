package dev.mateusbandeira.killmeplz;

import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.GameScreen;
import org.mini2Dx.core.screen.ScreenManager;
import org.mini2Dx.core.screen.transition.FadeInTransition;
import org.mini2Dx.core.screen.transition.FadeOutTransition;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import dev.mateusbandeira.killmeplz.entity.AmmoRefueler;
import dev.mateusbandeira.killmeplz.entity.Player;

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
		// Verifica se o jogador pressionou TAB para abrir o menu.
		if (Gdx.input.isKeyPressed(Input.Keys.TAB)) {
			screenManager.enterGameScreen(2, new FadeOutTransition(), new FadeInTransition());
		}
		player.update(delta);
		refueler1.update();

	}

	@Override
	public void interpolate(GameContainer gc, float alpha) {
		player.interpolate(gc, alpha);
		refueler1.interpolate(alpha);

	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		refueler1.render(g);
		player.render(g);

		// Verifica se existe colisão entre o jogador e o Refueler.
		if (refueler1.checkCollision(player)) {
			g.drawString(MESSAGE, refueler1.getPoint().getX() - 32f, refueler1.getPoint().getY() + 48f);
		}

	}

	public Player getPlayer() {
		return this.player;
	}

	@Override
	public int getId() {
		return IngameScreen.ID;
	}

}
